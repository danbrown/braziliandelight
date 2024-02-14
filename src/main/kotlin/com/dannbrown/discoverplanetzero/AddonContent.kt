package com.dannbrown.discoverplanetzero

import com.dannbrown.databoxlib.registry.DataboxRegistrate
import com.dannbrown.discoverplanetzero.datagen.AddonDatagen
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.dannbrown.discoverplanetzero.init.AddonCreativeTabs
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager


@Mod(AddonContent.MOD_ID)
class AddonContent {
  companion object {
    const val MOD_ID = "discoverplanetzero"
    const val NAME = "Discover: Planet Zero"
    val LOGGER = LogManager.getLogger()

    val REGISTRATE = DataboxRegistrate(MOD_ID)

    fun register(modBus: IEventBus, forgeEventBus: IEventBus) {
      LOGGER.info("$MOD_ID has started!")
      AddonBlocks.register(modBus)
      AddonCreativeTabs.register(modBus)
      // register create content
      REGISTRATE.registerEventListeners(modBus)

      modBus.addListener(EventPriority.LOWEST) { event: GatherDataEvent -> AddonDatagen.gatherData(event) }
    }
  }

  init {
    val eventBus = FMLJavaModLoadingContext.get().modEventBus
    val forgeEventBus = MinecraftForge.EVENT_BUS
    register(eventBus, forgeEventBus)
  }
}


