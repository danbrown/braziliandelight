package com.dannbrown.discoverplanetzero.datagen

import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import com.dannbrown.databoxlib.registry.datagen.DatagenRootInterface
import com.dannbrown.discover.datagen.DiscoverDatagen
import com.dannbrown.discover.datagen.planets.PlanetGenerator
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonConfiguredFeatures
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.lang.AddonLangGen
import com.dannbrown.discoverplanetzero.datagen.worldgen.AddonPlacedFeatures
import com.dannbrown.discoverplanetzero.datagen.recipe.AddonRecipeGen
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.data.event.GatherDataEvent
import java.util.concurrent.CompletableFuture

class AddonDatagen(output: PackOutput, future: CompletableFuture<HolderLookup.Provider>) : DatapackBuiltinEntriesProvider(output, future, BUILDER, modIds){
  companion object: DatagenRootInterface{
    override val modIds: MutableSet<String> = mutableSetOf(
      *DiscoverDatagen.dependenciesIds,
      AddonContent.MOD_ID
    )
    override val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
      .add(Registries.CONFIGURED_FEATURE, AddonConfiguredFeatures::bootstrap)
      .add(Registries.PLACED_FEATURE, AddonPlacedFeatures::bootstrap)

    override fun gatherData(event: GatherDataEvent) {
      val generator = event.generator
      val packOutput = generator.packOutput
      val lookupProvider = event.lookupProvider
      val existingFileHelper = event.existingFileHelper

      // Builder generators above
      generator.addProvider(event.includeServer(), AddonDatagen(packOutput, lookupProvider))
      // Recipes
      DataboxRecipeProvider.registerGenerators(event.includeServer(), generator, AddonRecipeGen::class)
      // Custom generators
      generator.addProvider(event.includeServer(), PlanetGenerator(AddonContent.MOD_ID, generator, listOf(/* Dimensions goes here*/)))
      // Langs
      AddonLangGen.addStaticLangs(event.includeClient())
    }
  }
}