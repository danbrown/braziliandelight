package com.dannbrown.discovermars.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractConfiguredFeaturesGen
import com.dannbrown.discovermars.AddonContent
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature

object AddonConfiguredFeatures: AbstractConfiguredFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID


  override fun bootstrap(context: BootstapContext<ConfiguredFeature<*, *>>) {
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)
    // ----
  }
}