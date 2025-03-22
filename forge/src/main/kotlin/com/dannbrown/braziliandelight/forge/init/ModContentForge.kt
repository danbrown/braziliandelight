package com.dannbrown.braziliandelight.forge.init


import com.dannbrown.deltaboxlib.forge.registrate.RegistrateInitForge
import com.dannbrown.braziliandelight.init.ModContent
import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.DIST
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ModContent.MOD_ID)
object ModContentForge {
  val registrateInit = RegistrateInitForge(ModContent.REGISTRATE)

  init {
    val modBus = MOD_BUS
    val forgeEventBus = MinecraftForge.EVENT_BUS
    register(modBus, forgeEventBus)
    // client
    if (DIST.isClient) {
      // register main mod client content
      registerClient(modBus, forgeEventBus)
    }
  }

  // RUN SETUP
  private fun commonSetup(event: FMLCommonSetupEvent) {
    event.enqueueWork {
      registrateInit.setup()
    }
  }

  private fun register(modBus: IEventBus, forgeEventBus: IEventBus) {
    // Submit our event bus to let architectury register our content on the right time
    EventBuses.registerModEventBus(ModContent.MOD_ID, MOD_BUS)
    ModContent.init()
    registrateInit.init()

    MOD_BUS.addListener(::commonSetup)
    modBus.addListener(registrateInit::onRegisterEntityAttributes)
  }

  private fun registerClient(modBus: IEventBus, forgeEventBus: IEventBus) {
    modBus.addListener(::clientSetup)
    modBus.addListener(registrateInit::onRegisterBlockBiomeColors)
    modBus.addListener(registrateInit::onRegisterItemBiomeColors)
    modBus.addListener(registrateInit::onRegisterParticleRenders)
    modBus.addListener(registrateInit::onRegisterLayerDefinitions)
    modBus.addListener(registrateInit::onRegisterEntityRenderers)
    modBus.addListener(registrateInit::onRegisterSpawnEggColors)
  }

  private fun clientSetup(event: FMLClientSetupEvent) {
    registrateInit.clientSetup()
  }
}