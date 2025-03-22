package com.dannbrown.braziliandelight.init

import com.dannbrown.deltaboxlib.init.DeltaboxRegistrate
import com.dannbrown.deltaboxlib.registrate.util.CreativeTabsUtil
import net.minecraft.world.item.ItemStack

object ModContent {
  const val MOD_ID = "braziliandelight"
  var REGISTRATE = DeltaboxRegistrate(MOD_ID)

  val TAB = REGISTRATE.creativeTab(
    MOD_ID,
    "Brazilian Delight",
    { ItemStack(ModItems.BRAZIL_FLAG.get()) },
    { p, o ->
      CreativeTabsUtil.displayAll(REGISTRATE, p, o)
    })

  fun init() {
    ModConfig.register()
    ModSounds.register()
    ModEffects.register()
    ModTags.register()
    ModBlocks.register()
    ModEntityTypes.register()
    ModItems.register()
    ModParticles.register()
    ModConfiguredFeatures.register()
    ModPlacedFeatures.register()
    ModBlockEntities.register()
    ModModelLayers.register()
    ModPlacerTypes.register()
    ModLang.register()
    ModBiomeModifiers.register()
    ModDispenserBehaviors.register()
    REGISTRATE.buildRegistries()
  }
}