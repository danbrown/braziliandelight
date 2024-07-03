package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.content.block.CoconutBlock
import com.dannbrown.braziliandelight.content.block.FallingCoconutBlock
import com.dannbrown.braziliandelight.content.item.CoconutItem
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.braziliandelight.init.AddonTags
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import java.util.function.Supplier

object CoconutBuilderPresets {
  fun createCoconutBlock(
    name: String,
    color: MapColor,
    age: CoconutBlock.CoconutState,
    nextBlock: Supplier<out Block>? = null
  ): BlockEntry<CoconutBlock> {
    return BLOCKS
      .create<CoconutBlock>(name)
      .copyFrom { Blocks.BAMBOO_BLOCK }
      .blockFactory { p -> CoconutBlock(p, age, nextBlock) }
      .properties { p ->
        p.mapColor(color)
          .strength(0.2f)
          .randomTicks()
          .sound(SoundType.BAMBOO_WOOD)
          .noOcclusion()
          .pushReaction(PushReaction.DESTROY)
      }
      .blockTags(listOf(BlockTags.MINEABLE_WITH_AXE))
      .blockstate(CustomBlockstatePresets.coconutBlock(name))
      .loot(BlockLootPresets.dropItselfLoot())
      .transform { t ->
        if (age == CoconutBlock.CoconutState.BROWN) {
          t
            .item { b, p -> CoconutItem(b, p) }
            .model(ItemModelPresets.simpleItem(name))
            .tag(AddonTags.ITEM.COCONUT)
            .build()
        } else {
          t.item().model(ItemModelPresets.simpleItem(name)).build()
        }
      }
      .register()
  }

  fun createFallingCoconutBlock(
    name: String,
    color: MapColor,
    item: Supplier<ItemLike>
  ): BlockEntry<FallingCoconutBlock> {
    return BLOCKS
      .create<FallingCoconutBlock>("falling_$name")
      .copyFrom { Blocks.BAMBOO_BLOCK }
      .blockFactory { p -> FallingCoconutBlock(p, item) }
      .blockTags(listOf(BlockTags.MINEABLE_WITH_AXE))
      .properties { p ->
        p.mapColor(color)
          .strength(0.2f)
          .sound(SoundType.BAMBOO_WOOD)
          .noOcclusion()
          .pushReaction(PushReaction.DESTROY)
      }
      .blockstate(CustomBlockstatePresets.fallingCoconutBlock(name))
      .loot(BlockLootPresets.dropOtherLoot(item))
      .noItem()
      .register()
  }
}