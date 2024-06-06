package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiomeModifiersGen
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.compat.AddonModIntegrations
import com.dannbrown.braziliandelight.datagen.tags.AddonBiomeTags
import com.dannbrown.braziliandelight.init.AddonTags
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier

object AddonBiomeModifiers: AbstractBiomeModifiersGen() {
  override val modId: String = AddonContent.MOD_ID
  // Content
  val ADD_LEMON_TREE = registerKey("add_lemon_tree")
  val ADD_COCONUT_PALM_TREE = registerKey("add_coconut_palm_tree")
  val ADD_ACAI_PALM_TREE = registerKey("add_acai_palm_tree")

  // wild crops
  val ADD_WILD_GARLIC = registerKey("add_wild_garlic")
  val ADD_WILD_COLLARD_GREENS = registerKey("add_wild_collard_greens")
  val ADD_WILD_COFFEE_BERRIES = registerKey("add_wild_coffee_berries")
  val ADD_WILD_CASSAVA = registerKey("add_wild_cassava")
  val ADD_WILD_CORN = registerKey("add_wild_corn")
  val ADD_WILD_GUARANA = registerKey("add_wild_guarana")
  val ADD_WILD_BEANS = registerKey("add_wild_beans")
  val ADD_YERBA_MATE = registerKey("add_yerba_mate")

  override fun bootstrap(context: BootstapContext<BiomeModifier>) {
    val biomeLookup: HolderGetter<Biome> = context.lookup(Registries.BIOME)
    val featureLookup: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)

    // add lemon tree
    val lemonTreePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.LEMON_TREE_PLACED)
    context.register(ADD_LEMON_TREE, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_LEMON_TREE), lemonTreePlaced))

    // add coconut palm tree
    val coconutPalmTreePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.COCONUT_PALM_TREE_PLACED)
    context.register(ADD_COCONUT_PALM_TREE, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_COCONUT_PALM_TREE), coconutPalmTreePlaced))

    // add acai palm tree
    val acaiPalmTreePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.ACAI_PALM_TREE_PLACED)
    context.register(ADD_ACAI_PALM_TREE, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_ACAI_PALM_TREE), acaiPalmTreePlaced))

    // add wild garlic
    val wildGarlicPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_GARLIC_PLACED)
    context.register(ADD_WILD_GARLIC, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_GARLIC), wildGarlicPlaced))

    // add wild collard greens
    val wildCollardGreensPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_COLLARD_GREENS_PLACED)
    context.register(ADD_WILD_COLLARD_GREENS, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_COLLARD_GREENS), wildCollardGreensPlaced))

    // add wild coffee berries
    val wildCoffeeBerriesPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_COFFEE_BERRIES_PLACED)
    context.register(ADD_WILD_COFFEE_BERRIES, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_COFFEE_BERRIES), wildCoffeeBerriesPlaced))

    // add wild cassava
    val wildCassavaPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_CASSAVA_PLACED)
    context.register(ADD_WILD_CASSAVA, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_CASSAVA), wildCassavaPlaced))

    // add wild corn
    val wildCornPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_CORN_PLACED)
    context.register(ADD_WILD_CORN, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_CORN), wildCornPlaced))

    // add wild guarana
    val wildGuaranaPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_GUARANA_PLACED)
    context.register(ADD_WILD_GUARANA, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_GUARANA), wildGuaranaPlaced))

    // add wild beans
    val wildBeansPlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_WILD_BEANS_PLACED)
    context.register(ADD_WILD_BEANS, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_WILD_BEANS), wildBeansPlaced))

    // add yerba mate
    val yerbaMatePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.PATCH_YERBA_MATE_PLACED)
    context.register(ADD_YERBA_MATE, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.HAS_YERBA_MATE), yerbaMatePlaced))

    AddonModIntegrations.bootstrapBiomeModifiers(context)
  }
}