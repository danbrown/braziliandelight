package com.dannbrown.discovermars.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractBiomeModifiersGen
import com.dannbrown.discover.ProjectContent
import com.dannbrown.discover.datagen.worldgen.DiscoverPlacedFeatures
import com.dannbrown.discovermars.AddonContent
import com.dannbrown.discovermars.compat.AddonModIntegrations
import com.dannbrown.discovermars.init.AddonTags
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

    AddonModIntegrations.bootstrapBiomeModifiers(context)
  }
}