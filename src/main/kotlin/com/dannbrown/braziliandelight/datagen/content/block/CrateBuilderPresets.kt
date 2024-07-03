package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.MapColor
import java.util.function.Supplier

object CrateBuilderPresets {

  // This function creates a crate block
  fun createCrateBlock(
    name: String,
    color: MapColor,
    item: Supplier<ItemLike>,
    ingredient: Supplier<DataIngredient>
  ): BlockEntry<Block> {
    val blockId = "${name}_crate"
    return BLOCKS
      .create<Block>(blockId)
      .copyFrom { Blocks.OAK_PLANKS }
      .color(color)
      .blockstate(CustomBlockstatePresets.crateBlock(blockId))
      .blockTags(listOf(BlockTags.MINEABLE_WITH_AXE))
      .storageBlock(item, ingredient, false)
      .register()
  }

  // This function creates a bag block
  fun crateBagBlock(
    name: String,
    color: MapColor,
    item: Supplier<ItemLike>,
    ingredient: Supplier<DataIngredient>
  ): BlockEntry<Block> {
    val blockId = "${name}_bag"
    return BLOCKS
      .create<Block>(blockId)
      .copyFrom { Blocks.WHITE_WOOL }
      .color(color)
      .blockstate(CustomBlockstatePresets.bagBlock(blockId))
      .storageBlock(item, ingredient, false)
      .register()
  }

}