package com.dannbrown.discoverplanetzero.compat.enemyexpansion

import com.dannbrown.databoxlib.registry.datagen.AbstractModPlugin
import com.dannbrown.discover.datagen.worldgen.DiscoverBiomeModifiers
import com.dannbrown.discover.init.DiscoverTags
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.init.AddonTags
import net.mcreator.enemyexpansion.EnemyexpansionMod
import net.mcreator.enemyexpansion.init.EnemyexpansionModEntities
import net.minecraft.core.*
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.data.tags.TagsProvider
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier

// here we define enemyexpansion mod compatibility to add mobs into discover biomes
object EnemyExpansionPlugin : AbstractModPlugin(AddonContent.MOD_ID, EnemyexpansionMod.MODID, AddonContent.isEnemyExpansionLoaded) {

  // biome tags
  override fun addModBiomeTags(tag: (pTag: TagKey<Biome>) -> TagsProvider.TagAppender<Biome>) {

  }

  // block tags
  override fun addModBlockTags(tag: (pTag: TagKey<Block>) -> IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>) {
  }

  // Biome Modifiers
  val ADD_TO_PLANET_ZERO: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_planet_zero")

  override fun bootstrapBiomeModifiers(context: BootstapContext<BiomeModifier>) {
    val biomeLookup: HolderGetter<Biome> = context.lookup(Registries.BIOME)
    val featureLookup: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)
    // Planet Zero
    val isEarth: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_PLANET_ZERO)
    // -----

    context.register(
      ADD_TO_PLANET_ZERO,
      DiscoverBiomeModifiers.addMob(
        isEarth, listOf(
          SpawnerData(EnemyexpansionModEntities.TARANTULA.get(), 110, 2, 3),
          SpawnerData(EnemyexpansionModEntities.SCORPION.get(), 50, 1, 2),
          SpawnerData(EnemyexpansionModEntities.STARVED.get(), 40, 1, 4),
          SpawnerData(EnemyexpansionModEntities.TROLL.get(), 80, 1, 2),
          SpawnerData(EnemyexpansionModEntities.WASP.get(), 40, 2, 5),
        )
      )
    )

    // -----
  }

// -----
}
