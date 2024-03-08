package com.dannbrown.braziliandelight.datagen.worldgen

import com.dannbrown.databoxlib.registry.worldgen.AbstractStructureGen
import com.dannbrown.braziliandelight.AddonContent
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType

object AddonStructures: AbstractStructureGen() {
  override val modId = AddonContent.MOD_ID

  // @ STRUCTURES

  // @ STRUCTURE SETS

  override fun bootstrapStructures(context: BootstapContext<Structure>) {
    val biomes = context.lookup(Registries.BIOME)
    val pools = context.lookup(Registries.TEMPLATE_POOL)
    val lookup = context.lookup(Registries.CONFIGURED_FEATURE)

    // -----
  }

  override fun bootstrapSets(context: BootstapContext<StructureSet>) {
    val structures = context.lookup(Registries.STRUCTURE)

  }
}