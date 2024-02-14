package com.dannbrown.discoverplanetzero.datagen.worldgen.biome

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiome
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.resources.ResourceLocation
import com.dannbrown.discover.ProjectContent
import com.dannbrown.discoverplanetzero.AddonContent

object LushRedSandArchesBiome : AbstractBiome() {
  override val biomeId = "lush_red_sand_arches"
  override val BIOME_KEY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, ResourceLocation(AddonContent.MOD_ID, biomeId))
  override fun createBiome(context: BootstapContext<Biome>): Biome {
    return RedSandArchesBiome.createBiome(context)
  }
}