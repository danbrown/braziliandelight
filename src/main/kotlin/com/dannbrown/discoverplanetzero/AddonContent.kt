package com.dannbrown.discoverplanetzero

import com.dannbrown.databoxlib.registry.DataboxRegistrate
import com.dannbrown.discover.compat.vanilla.DiscoverCompostables
import com.dannbrown.discover.compat.vanilla.DiscoverDispenserBehaviors
import com.dannbrown.discover.compat.vanilla.DiscoverFlowerPots
import com.dannbrown.discover.init.DiscoverEntityTypes
import com.dannbrown.discover.init.DiscoverNetworking
import com.dannbrown.discover.init.DiscoverPartialModels
import com.dannbrown.discover.init.DiscoverWoodTypes
import com.dannbrown.discover.init._DiscoverBlockEntities
import com.dannbrown.discoverplanetzero.compat.alexsmobs.AlexsMobsPlugin
import com.dannbrown.discoverplanetzero.compat.vanilla.AddonCompostables
import com.dannbrown.discoverplanetzero.compat.vanilla.AddonDispenserBehaviors
import com.dannbrown.discoverplanetzero.compat.vanilla.AddonFlowerPots
import com.dannbrown.discoverplanetzero.datagen.AddonDatagen
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.dannbrown.discoverplanetzero.init.AddonCreativeTabs
import com.dannbrown.discoverplanetzero.init.AddonItems
import com.dannbrown.discoverplanetzero.init.AddonTrunkPlacerType
import com.dannbrown.discoverplanetzero.init.AddonWoodTypes
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
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
    const val MOD_ID = "discoverplanetzero"
    const val NAME = "Discover: Planet Zero"
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
      AddonTrunkPlacerType.register(modBus)
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
        // WOOD TYPES SHEETS
        AddonWoodTypes.register()
        // FLOWER POTS
        AddonFlowerPots.register()
        // COMPOSTABLES
        AddonCompostables.register()
        // DISPENSER BEHAVIORS
        AddonDispenserBehaviors.registerAll()
      }
    }

    fun onClientSetup(event: FMLCommonSetupEvent) {
      WoodType.register(AddonWoodTypes.JOSHUA)
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


