package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiomeModifiersGen
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.compat.AddonModIntegrations
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

  override fun bootstrap(context: BootstapContext<BiomeModifier>) {
    val biomeLookup: HolderGetter<Biome> = context.lookup(Registries.BIOME)
    val featureLookup: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)

    val isForest: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_FOREST)

    // add lemon tree to forest biomes
    val lemonTreePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.LEMON_TREE_PLACED)
    context.register(ADD_LEMON_TREE, addVegetation(isForest, lemonTreePlaced))

    AddonModIntegrations.bootstrapBiomeModifiers(context)
  }
}