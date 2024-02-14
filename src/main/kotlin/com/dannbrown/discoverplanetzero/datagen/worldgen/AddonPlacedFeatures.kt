package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractPlacedFeaturesGen
import com.dannbrown.discoverplanetzero.AddonContent
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object AddonPlacedFeatures: AbstractPlacedFeaturesGen() {
  override val modId: String = AddonContent.MOD_ID

  // @ KEYS

  override fun bootstrap(context: BootstapContext<PlacedFeature>) {
    val configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE)
  }

}