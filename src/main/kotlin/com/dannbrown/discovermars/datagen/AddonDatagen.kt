package com.dannbrown.discovermars.datagen

import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import com.dannbrown.databoxlib.registry.datagen.DatagenRootInterface
import com.dannbrown.discover.datagen.DiscoverDatagen
import com.dannbrown.discover.datagen.planets.PlanetGenerator
import com.dannbrown.discovermars.datagen.worldgen.AddonConfiguredFeatures
import com.dannbrown.discovermars.AddonContent
import com.dannbrown.discovermars.compat.AddonModIntegrations
import com.dannbrown.discovermars.datagen.lang.AddonLangGen
import com.dannbrown.discovermars.datagen.worldgen.AddonPlacedFeatures
import com.dannbrown.discovermars.datagen.recipe.AddonRecipeGen
import com.dannbrown.discovermars.datagen.tags.AddonBiomeTags
import com.dannbrown.discovermars.datagen.tags.AddonBlockTags
import com.dannbrown.discovermars.datagen.tags.AddonEntityTypeTags
import com.dannbrown.discovermars.datagen.tags.AddonFluidTags
import com.dannbrown.discovermars.datagen.tags.AddonItemTags
import com.dannbrown.discovermars.datagen.tags.AddonWorldPresetTags
import com.dannbrown.discovermars.datagen.worldgen.AddonBiomeModifiers
import com.dannbrown.discovermars.datagen.worldgen.AddonBiomes
import com.dannbrown.discovermars.datagen.worldgen.AddonStructures
import com.dannbrown.discovermars.datagen.worldgen.AddonWorldPresets
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
      DataboxRecipeProvider.registerGenerators(event.includeServer(), generator, AddonRecipeGen::class)
      // Custom generators
      generator.addProvider(event.includeServer(), PlanetGenerator(AddonContent.MOD_ID, generator, listOf()))
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