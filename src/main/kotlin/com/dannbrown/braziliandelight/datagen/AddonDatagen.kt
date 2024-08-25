package com.dannbrown.braziliandelight.datagen

import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxRecipeProvider
import com.dannbrown.deltaboxlib.registry.datagen.DatagenRootInterface
import com.dannbrown.braziliandelight.datagen.worldgen.AddonConfiguredFeatures
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.compat.AddonModIntegrations
import com.dannbrown.braziliandelight.datagen.advancements.AddonAdvancementsProvider
import com.dannbrown.braziliandelight.datagen.lang.AddonLangGen
import com.dannbrown.braziliandelight.datagen.worldgen.AddonPlacedFeatures
import com.dannbrown.braziliandelight.datagen.recipe.AddonRecipeGen
import com.dannbrown.braziliandelight.datagen.recipe.PlaceholderRecipeGen
import com.dannbrown.braziliandelight.datagen.tags.AddonBiomeTags
import com.dannbrown.braziliandelight.datagen.tags.AddonBlockTags
import com.dannbrown.braziliandelight.datagen.tags.AddonEntityTypeTags
import com.dannbrown.braziliandelight.datagen.tags.AddonFluidTags
import com.dannbrown.braziliandelight.datagen.tags.AddonItemTags
import com.dannbrown.braziliandelight.datagen.tags.AddonWorldPresetTags
import com.dannbrown.braziliandelight.datagen.worldgen.AddonBiomeModifiers
import com.dannbrown.braziliandelight.datagen.worldgen.AddonBiomes
import com.dannbrown.braziliandelight.datagen.worldgen.AddonStructures
import com.dannbrown.braziliandelight.datagen.worldgen.AddonWorldPresets
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.common.data.ForgeAdvancementProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.registries.ForgeRegistries
import java.util.concurrent.CompletableFuture

class AddonDatagen(output: PackOutput, future: CompletableFuture<HolderLookup.Provider>) :
  DatapackBuiltinEntriesProvider(output, future, BUILDER, modIds) {
  companion object : DatagenRootInterface {
    override val modIds: MutableSet<String> = mutableSetOf(
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
      DeltaboxRecipeProvider.registerGenerators(
        event.includeServer(),
        generator,
        AddonRecipeGen::class,
        PlaceholderRecipeGen::class
      )
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

      // advancements
      generator.addProvider(
        event.includeClient(),
        ForgeAdvancementProvider(packOutput, lookupProvider, existingFileHelper, listOf(AddonAdvancementsProvider()))
      )
    }
  }
}