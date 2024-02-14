package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiomeModifiersGen
import com.dannbrown.discover.ProjectContent
import com.dannbrown.discover.datagen.worldgen.DiscoverPlacedFeatures
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.compat.AddonModIntegrations
import com.dannbrown.discoverplanetzero.init.AddonTags
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.Tags
import net.minecraftforge.common.world.BiomeModifier

object AddonBiomeModifiers: AbstractBiomeModifiersGen() {
  override val modId: String = AddonContent.MOD_ID
  // Content
//  val ADD_JOSHUA_TREE: ResourceKey<BiomeModifier> = registerKey("add_joshua_tree")

  override fun bootstrap(context: BootstapContext<BiomeModifier>) {
    val biomeLookup: HolderGetter<Biome> = context.lookup(Registries.BIOME)
    val featureLookup: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)

    //    val isOverworld: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD)
    //    val isNether: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_NETHER)

    //    // add aluminium ore to overworld
    //    val aluminiumOrePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(DiscoverPlacedFeatures.ALUMINIUM_ORE_PLACED)
    //    context.register(ADD_ALUMINIUM_ORE, addOre(isOverworld, aluminiumOrePlaced))
    //    // add tungsten ore to overworld
    //    val tungstenOrePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(DiscoverPlacedFeatures.TUNGSTEN_ORE_PLACED)
    //    context.register(ADD_TUNGSTEN_ORE, addOre(isOverworld, tungstenOrePlaced))

    // add joshua tree to plains biome
//    val joshuaTreePlaced: Holder<PlacedFeature> = featureLookup.getOrThrow(AddonPlacedFeatures.JOSHUA_PLACED)
//    context.register(ADD_JOSHUA_TREE, addVegetation(biomeLookup.getOrThrow(AddonTags.BIOME.IS_RED_SAND_ARCHES), joshuaTreePlaced))
    // Add mod integrations biome modifiers
    AddonModIntegrations.bootstrapBiomeModifiers(context)
  }
}