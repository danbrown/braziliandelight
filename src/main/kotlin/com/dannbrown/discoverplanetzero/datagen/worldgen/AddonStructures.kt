package com.dannbrown.discoverplanetzero.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractStructureGen
import com.dannbrown.discover.content.worldgen.structures.ArchStructure
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.structurePresets.ArchStructurePresets
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType

object AddonStructures: AbstractStructureGen() {
  override val modId = AddonContent.MOD_ID

  // @ STRUCTURES
  val LUSH_RED_ROCK_ARCH = registerKey( "lush_red_rock_arch")
  val RED_ROCK_ARCH = registerKey( "red_rock_arch")
  val GRANITE_RED_ROCK_ARCH = registerKey( "granite_red_rock_arch")

  // @ STRUCTURE SETS
  val RED_ROCK_ARCH_SET = registerSetKey("red_rock_arch_set")
  val LUSH_RED_ROCK_ARCH_SET = registerSetKey("lush_red_rock_arch_set")

  override fun bootstrapStructures(context: BootstapContext<Structure>) {
    val biomes = context.lookup(Registries.BIOME)
    val pools = context.lookup(Registries.TEMPLATE_POOL)
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)
    // -----
    // RED ROCK ARCH
    context.register(RED_ROCK_ARCH, ArchStructurePresets(biomes, lookup).RED_ROCK_ARCH_PRESET)
    context.register(GRANITE_RED_ROCK_ARCH, ArchStructurePresets(biomes, lookup).GRANITE_RED_ROCK_ARCH_PRESET)
    context.register(LUSH_RED_ROCK_ARCH, ArchStructurePresets(biomes, lookup).LUSH_RED_ROCK_ARCH_PRESET)
    // -----
  }

  override fun bootstrapSets(context: BootstapContext<StructureSet>) {
    val structures = context.lookup(Registries.STRUCTURE)
    // RED ROCK ARCH
    context.register(
      RED_ROCK_ARCH_SET,
      StructureSet(
        listOf(
          StructureSet.StructureSelectionEntry(structures.getOrThrow(RED_ROCK_ARCH), 3),
          StructureSet.StructureSelectionEntry(structures.getOrThrow(GRANITE_RED_ROCK_ARCH), 1)
        ),
        RandomSpreadStructurePlacement(15, 8, RandomSpreadType.LINEAR, 498548954)
      )
    )
    context.register(
      LUSH_RED_ROCK_ARCH_SET,
      StructureSet(
        listOf(
          StructureSet.StructureSelectionEntry(structures.getOrThrow(LUSH_RED_ROCK_ARCH), 3),
          StructureSet.StructureSelectionEntry(structures.getOrThrow(RED_ROCK_ARCH), 1),
        ),
        RandomSpreadStructurePlacement(15, 8, RandomSpreadType.LINEAR, 498552986)
      )
    )
  }
}