package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.generators.WorldPresetGen
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.dimension.PlanetZeroDimension
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.presets.WorldPreset

object AddonWorldPresets {
  val DISCOVER_WORLD_PRESET = ResourceKey.create(Registries.WORLD_PRESET, ResourceLocation(AddonContent.MOD_ID, PlanetZeroDimension.dimensionId))

  fun bootstrap(context: BootstapContext<WorldPreset>) {
    context.register(DISCOVER_WORLD_PRESET, WorldPresetGen.createFromDimension(context, PlanetZeroDimension))
  }
}