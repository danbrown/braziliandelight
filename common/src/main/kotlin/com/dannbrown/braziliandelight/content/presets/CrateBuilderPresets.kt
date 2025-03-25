package com.dannbrown.braziliandelight.content.presets

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.registry.BlockEntry
import com.dannbrown.deltaboxlib.registrate.util.DataIngredient
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.crafting.Ingredient
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
    return REGISTRATE.blockPreset<Block>(blockId)
      .storageBlock(item, ingredient, "")
      .copyFrom { Blocks.OAK_PLANKS }
      .color(color)
      .blockstate(BlockstatePresets.crateBlock())
      .blockTags(BlockTags.MINEABLE_WITH_AXE)
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
    return REGISTRATE.blockPreset<Block>(blockId)
      .storageBlock(item, ingredient, "")
      .copyFrom { Blocks.WHITE_WOOL }
      .color(color)
      .blockstate(BlockstatePresets.bagBlock())
      .register()
  }

}