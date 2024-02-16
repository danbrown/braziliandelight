package com.dannbrown.discovermars

import com.dannbrown.databoxlib.registry.DataboxRegistrate
import com.dannbrown.discovermars.compat.vanilla.AddonCompostables
import com.dannbrown.discovermars.compat.vanilla.AddonDispenserBehaviors
import com.dannbrown.discovermars.compat.vanilla.AddonFlowerPots
import com.dannbrown.discovermars.datagen.AddonDatagen
import com.dannbrown.discovermars.init.AddonBlocks
import com.dannbrown.discovermars.init.AddonCreativeTabs
import com.dannbrown.discovermars.init.AddonItems
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
    const val MOD_ID = "discovermars"
    const val NAME = "Discover: Mars"
    val LOGGER = LogManager.getLogger()

    val REGISTRATE = DataboxRegistrate(MOD_ID)

    // mod compatibility
    val isAlexsMobsLoaded: Boolean = ModList.get()
      .isLoaded("alexsmobs")
    val isEnemyExpansionLoaded: Boolean = ModList.get()
      .isLoaded("enemyexpansion")

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
        // NETWORKING MESSAGES
  //      AddonNetworking.register()
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


