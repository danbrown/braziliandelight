package com.dannbrown.braziliandelight.fabric.init

import com.dannbrown.deltaboxlib.fabric.init.loaders.DeltaboxLibLoadTradesFabric
import com.dannbrown.deltaboxlib.fabric.registrate.RegistrateInitFabric
import com.dannbrown.braziliandelight.init.ModContent
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

object ModContentFabric : ModInitializer, ClientModInitializer {
  val registrateInit = RegistrateInitFabric(ModContent.REGISTRATE)
  override fun onInitialize() {
    ModContent.init()
    registrateInit.init()

    // register datapack entries, like villager trades
    DeltaboxLibLoadTradesFabric.onDatapackReload(ModContent.REGISTRATE)
  }

  @Environment(EnvType.CLIENT)
  override fun onInitializeClient() {
    registrateInit.initClient()
  }
}
