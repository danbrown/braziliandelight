package com.dannbrown.braziliandelight

import com.dannbrown.databoxlib.registry.DataboxRegistrate
import com.dannbrown.braziliandelight.compat.vanilla.AddonCompostables
import com.dannbrown.braziliandelight.compat.vanilla.AddonDispenserBehaviors
import com.dannbrown.braziliandelight.compat.vanilla.AddonFlowerPots
import com.dannbrown.braziliandelight.datagen.AddonDatagen
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.init.AddonItems
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager


@Mod(AddonContent.MOD_ID)
class AddonContent {
  companion object {
    const val MOD_ID = "braziliandelight"
    const val NAME = "Brazilian Delight"
    val LOGGER = LogManager.getLogger()

    val REGISTRATE = DataboxRegistrate(MOD_ID)

    // mod compatibility

    fun register(modBus: IEventBus, forgeEventBus: IEventBus) {
      LOGGER.info("$MOD_ID has started!")
      AddonItems.register(modBus)
      AddonBlocks.register(modBus)
      AddonCreativeTabs.register(modBus)
      // register create content
      REGISTRATE.registerEventListeners(modBus)

      modBus.addListener(::commonSetup)
      modBus.addListener(EventPriority.LOWEST) { event: GatherDataEvent -> AddonDatagen.gatherData(event) }
    }

    // RUN SETUP
    private fun commonSetup(event: FMLCommonSetupEvent) {
      event.enqueueWork {
        // FLOWER POTS
        AddonFlowerPots.register()
        // COMPOSTABLES
        AddonCompostables.register()
        // DISPENSER BEHAVIORS
        AddonDispenserBehaviors.registerAll()
      }
    }

    fun onClientSetup(event: FMLCommonSetupEvent) {
    }

    // ---
  }

  init {
    val eventBus = FMLJavaModLoadingContext.get().modEventBus
    val forgeEventBus = MinecraftForge.EVENT_BUS
    register(eventBus, forgeEventBus)
  }

  @Mod.EventBusSubscriber(modid =MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
  object ClientEvents {
    @SubscribeEvent
    fun onClientSetup(event: FMLCommonSetupEvent?) {
      AddonContent.onClientSetup(event!!)
    }

    @SubscribeEvent
    fun registerLayers(event: EntityRenderersEvent.RegisterLayerDefinitions?) {
    }
  }
}


