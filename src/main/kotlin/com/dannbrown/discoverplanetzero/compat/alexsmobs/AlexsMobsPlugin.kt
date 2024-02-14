package com.dannbrown.discoverplanetzero.compat.alexsmobs

import com.dannbrown.databoxlib.registry.datagen.AbstractModPlugin
import com.dannbrown.discover.datagen.worldgen.DiscoverBiomeModifiers
import com.dannbrown.discover.init.DiscoverTags
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.LushRedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RedSandArchesBiome
import com.dannbrown.discoverplanetzero.init.AddonTags
import com.github.alexthe666.alexsmobs.AlexsMobs
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.data.tags.TagsProvider
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier

object AlexsMobsPlugin : AbstractModPlugin(AddonContent.MOD_ID, AlexsMobs.MODID, AddonContent.isAlexsMobsLoaded) {
  // biome tags
  override fun addModBiomeTags(tag: (pTag: TagKey<Biome>) -> TagsProvider.TagAppender<Biome>) {
    tag(AMTagRegistry.SPAWNS_RED_GUSTERS)
      .add(RedSandArchesBiome.BIOME_KEY)
      .add(LushRedSandArchesBiome.BIOME_KEY)
  }

  // block tags
  override fun addModBlockTags(tag: (pTag: TagKey<Block>) -> IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>) {
  }

  // Biome Modifiers
  val ADD_TO_PLANET_ZERO: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_planet_zero")
  val ADD_TO_RED_SAND_ARCHES: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_red_sand_arches")
  val ADD_TO_MOSSY_SLATES: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_mossy_slates")
  val ADD_TO_ROSEATE_DESERT: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_roseate_desert")
  val ADD_TO_SCRAP_WASTELANDS: ResourceKey<BiomeModifier> = registerBiomeModifierKey("add_to_scrap_wastelands")
  override fun bootstrapBiomeModifiers(context: BootstapContext<BiomeModifier>) {
    val biomeLookup: HolderGetter<Biome> = context.lookup(Registries.BIOME)
    val featureLookup: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)
    val isOverworld: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD)
    // Planet Zero
    val isEarth: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_PLANET_ZERO)
    val isRedSandArches: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_RED_SAND_ARCHES)
    val isMossySlates: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_MOSSY_SLATES)
    val isScrapWastelands: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_SCRAP_WASTELANDS)
    val isRoseateDesert: HolderSet<Biome> = biomeLookup.getOrThrow(AddonTags.BIOME.IS_ROSEATE_DESERT)
    // -----
    val isNether: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_NETHER)
    val isEnd: HolderSet<Biome> = biomeLookup.getOrThrow(BiomeTags.IS_END)


    context.register(
      ADD_TO_PLANET_ZERO,
      DiscoverBiomeModifiers.addMob(
        isEarth, listOf(
          SpawnerData(AMEntityRegistry.RATTLESNAKE.get(), 20, 1, 2),
          SpawnerData(AMEntityRegistry.ROCKY_ROLLER.get(), 20, 1, 2),
          SpawnerData(AMEntityRegistry.CENTIPEDE_BODY.get(), 20, 1, 2),
          SpawnerData(AMEntityRegistry.COCKROACH.get(), 40, 3, 4),
        )
      )
    )

    context.register(
      ADD_TO_RED_SAND_ARCHES,
      DiscoverBiomeModifiers.addMob(
        isRedSandArches, listOf(
          SpawnerData(AMEntityRegistry.EMU.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.BALD_EAGLE.get(), 3, 1, 2),
          SpawnerData(AMEntityRegistry.KANGAROO.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.SUNBIRD.get(), 2, 1, 1),
          SpawnerData(AMEntityRegistry.GUSTER.get(), 40, 2, 4),
          SpawnerData(AMEntityRegistry.SUGAR_GLIDER.get(), 80, 2, 4),
        )
      )
    )

    context.register(
      ADD_TO_MOSSY_SLATES,
      DiscoverBiomeModifiers.addMob(
        isMossySlates, listOf(
          SpawnerData(AMEntityRegistry.KOMODO_DRAGON.get(), 4, 1, 2),
        )
      )
    )

    context.register(
      ADD_TO_ROSEATE_DESERT,
      DiscoverBiomeModifiers.addMob(
        isRoseateDesert, listOf(
          SpawnerData(AMEntityRegistry.ROADRUNNER.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.SUNBIRD.get(), 2, 1, 1),
          SpawnerData(AMEntityRegistry.EMU.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.TARANTULA_HAWK.get(), 30, 1, 3),
          SpawnerData(AMEntityRegistry.GUSTER.get(), 40, 2, 4),
        )
      )
    )

    context.register(
      ADD_TO_SCRAP_WASTELANDS,
      DiscoverBiomeModifiers.addMob(
        isScrapWastelands, listOf(
          SpawnerData(AMEntityRegistry.ROADRUNNER.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.JERBOA.get(), 40, 3, 4),
          SpawnerData(AMEntityRegistry.TARANTULA_HAWK.get(), 30, 1, 3),
          SpawnerData(AMEntityRegistry.GUSTER.get(), 40, 2, 4),
        )
      )
    )
    // -----
  }
// -----
}

