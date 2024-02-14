package com.dannbrown.discoverplanetzero.datagen.worldgen.biome

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiome
import com.dannbrown.discover.content.core.BiomeFeaturePresets
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.data.worldgen.Carvers
import net.minecraft.data.worldgen.placement.CavePlacements
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements
import net.minecraft.data.worldgen.placement.OrePlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.resources.ResourceLocation
import com.dannbrown.discover.ProjectContent
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonPlacedFeatures

object RedSandArchesBiome : AbstractBiome() {
  override val biomeId = "red_sand_arches"
  override val BIOME_KEY: ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, ResourceLocation(AddonContent.MOD_ID, biomeId))
  override fun createBiome(context: BootstapContext<Biome>): Biome {
    val placedFeatures = context.lookup(Registries.PLACED_FEATURE)
    val caveGetter = context.lookup(Registries.CONFIGURED_CARVER)
    val generationSettings = BiomeGenerationSettings.Builder(placedFeatures, caveGetter)
      // raw generation
      .addFeature(GenerationStep.Decoration.RAW_GENERATION, AddonPlacedFeatures.RED_SAND_BOULDER_PLACED)
      // lakes
      .addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND)
      .addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_SURFACE)
      // local modifcations
      // .....
      // underground structures
      .addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.FOSSIL_LOWER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.FOSSIL_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.MONSTER_ROOM)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, CavePlacements.MONSTER_ROOM_DEEP)
      // surface structures
      // .....
      // strongholds
      // .....
      // underground ores
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRANITE_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GRANITE_LOWER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_LOWER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_LOWER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS_BURIED)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AddonPlacedFeatures.PHOSPHORITE_ORE_UPPER_PLACED)
      .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AddonPlacedFeatures.PHOSPHORITE_ORE_LOWER_PLACED)
      // underground decorations
      .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, CavePlacements.GLOW_LICHEN)
      // fluid springs
      .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_LAVA)
      // vegetal decorations
      .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_CACTUS_DESERT)
      .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2)
      .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AddonPlacedFeatures.JOSHUA_PLACED)
      .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AddonPlacedFeatures.PATCH_DRY_SHRUBS_PLACED)
      .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AddonPlacedFeatures.PATCH_DEAD_GRASSES_PLACED)
      // carvers
      .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
      .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)
      .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON)
    val effects = BiomeSpecialEffects.Builder()
    BiomeFeaturePresets.generateColors(effects, 1186057, 4477507)
      .fogColor(15171680)
      .waterColor(4159204)
      .waterFogColor(329011)
      .skyColor(16686686)
      .foliageColorOverride(16777215)
      .grassColorOverride(9551193)
    val mobSpawnInfo = MobSpawnSettings.Builder()
    BiomeFeaturePresets.addPlanetZeroMobs(mobSpawnInfo)

    return Biome.BiomeBuilder()
      .hasPrecipitation(false)
      .temperature(1.5f)
      .downfall(0f)
      .generationSettings(generationSettings.build())
      .mobSpawnSettings(mobSpawnInfo.build())
      .specialEffects(effects.build())
      .build()
  }
}