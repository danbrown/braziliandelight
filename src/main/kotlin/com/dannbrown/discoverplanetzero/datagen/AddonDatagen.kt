package com.dannbrown.discoverplanetzero.datagen

import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import com.dannbrown.databoxlib.registry.datagen.DatagenRootInterface
import com.dannbrown.discover.datagen.DiscoverDatagen
import com.dannbrown.discover.datagen.planets.PlanetGenerator
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonConfiguredFeatures
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.compat.AddonModIntegrations
import com.dannbrown.discoverplanetzero.datagen.lang.AddonLangGen
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonPlacedFeatures
import com.dannbrown.discoverplanetzero.datagen.recipe.AddonRecipeGen
import com.dannbrown.discoverplanetzero.datagen.recipe.RenewablesRecipeGen
import com.dannbrown.discoverplanetzero.datagen.tags.AddonBiomeTags
import com.dannbrown.discoverplanetzero.datagen.tags.AddonBlockTags
import com.dannbrown.discoverplanetzero.datagen.tags.AddonEntityTypeTags
import com.dannbrown.discoverplanetzero.datagen.tags.AddonFluidTags
import com.dannbrown.discoverplanetzero.datagen.tags.AddonItemTags
import com.dannbrown.discoverplanetzero.datagen.tags.AddonWorldPresetTags
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonBiomeModifiers
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonBiomes
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonStructures
import com.dannbrown.discoverplanetzero.datagen.worldgen.dimension.PlanetZeroDimension
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonWorldPresets
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.registries.ForgeRegistries
import java.util.concurrent.CompletableFuture

class AddonDatagen(output: PackOutput, future: CompletableFuture<HolderLookup.Provider>) : DatapackBuiltinEntriesProvider(output, future, BUILDER, modIds){
  companion object: DatagenRootInterface{
    override val modIds: MutableSet<String> = mutableSetOf(
      *DiscoverDatagen.dependenciesIds, // base mods
      *AddonModIntegrations.getModIds(), // integrations
      AddonContent.MOD_ID
    )
    override val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
      .add(Registries.CONFIGURED_FEATURE, AddonConfiguredFeatures::bootstrap)
      .add(Registries.PLACED_FEATURE, AddonPlacedFeatures::bootstrap)
      .add(Registries.BIOME, AddonBiomes::bootstrap)
      .add(Registries.DIMENSION_TYPE, PlanetZeroDimension::bootstrapType)
      .add(Registries.LEVEL_STEM, PlanetZeroDimension::bootstrapStem)
      .add(Registries.NOISE_SETTINGS, PlanetZeroDimension::bootstrapNoise)
      .add(Registries.STRUCTURE, AddonStructures::bootstrapStructures)
      .add(Registries.STRUCTURE_SET, AddonStructures::bootstrapSets)
      .add(Registries.WORLD_PRESET, AddonWorldPresets::bootstrap)
      .add(ForgeRegistries.Keys.BIOME_MODIFIERS, AddonBiomeModifiers::bootstrap)

    override fun gatherData(event: GatherDataEvent) {
      val generator = event.generator
      val packOutput = generator.packOutput
      val lookupProvider = event.lookupProvider
      val existingFileHelper = event.existingFileHelper

      // Builder generators above
      generator.addProvider(event.includeServer(), AddonDatagen(packOutput, lookupProvider))
      // Langs
      AddonLangGen.addStaticLangs(event.includeClient())
      // Recipes
      DataboxRecipeProvider.registerGenerators(event.includeServer(), generator, AddonRecipeGen::class, RenewablesRecipeGen::class)
      // Custom generators
      generator.addProvider(event.includeServer(), PlanetGenerator(AddonContent.MOD_ID, generator, listOf(PlanetZeroDimension)))
      // World preset tags
      generator.addProvider(
        event.includeServer(),
        AddonWorldPresetTags(packOutput, lookupProvider.thenApply { r -> append(r, BUILDER) }, existingFileHelper)
      )
      // Biome Tags
      generator.addProvider(
        event.includeServer(),
        AddonBiomeTags(packOutput, lookupProvider.thenApply { r -> append(r, BUILDER) }, existingFileHelper)
      )
      // Entity Type Tags
      generator.addProvider(
        event.includeServer(),
        AddonEntityTypeTags(packOutput, lookupProvider.thenApply { r -> append(r, BUILDER) }, existingFileHelper)
      )
      // FluidTags, BlockTags, ItemTag Gen
      generator.addProvider(event.includeServer(), AddonFluidTags(packOutput, lookupProvider, existingFileHelper))
      val blockTags = AddonBlockTags(packOutput, lookupProvider, existingFileHelper)
      generator.addProvider(event.includeServer(), blockTags)
      generator.addProvider(
        event.includeServer(),
        AddonItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper)
      )
    }
  }
}