package com.dannbrown.braziliandelight.content.presets

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.braziliandelight.content.blocks.CoconutBlock
import com.dannbrown.braziliandelight.content.blocks.FallingCoconutBlock
import com.dannbrown.braziliandelight.init.ModTags
import com.dannbrown.deltaboxlib.registrate.registry.BlockEntry
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
    return REGISTRATE.block<CoconutBlock>(name)
      .copyFrom { Blocks.BAMBOO_BLOCK }
      .factory { c, p -> CoconutBlock(p, age, nextBlock) }
      .properties { c, p ->
        p.mapColor(color)
          .strength(0.2f)
          .randomTicks()
          .sound(SoundType.BAMBOO_WOOD)
          .noOcclusion()
          .pushReaction(PushReaction.DESTROY)
      }
      .blockTags(BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstatePresets.coconutBlock(name))
      .cutoutRender()
      .item()
      .model { g, i -> g.flatItem(i.get()) }
      .itemTags(*(if (age == CoconutBlock.CoconutState.BROWN) ModTags.ITEM.COCONUT.toTypedArray() else arrayOf()))
      .build()
      .loot { g, b -> g.dropItself(b.get()) }
      .register() as BlockEntry<CoconutBlock>
  }

  fun createFallingCoconutBlock(
    name: String,
    color: MapColor,
    item: Supplier<ItemLike>
  ): BlockEntry<FallingCoconutBlock> {
    return REGISTRATE.block<FallingCoconutBlock>("falling_$name")
      .copyFrom { Blocks.BAMBOO_BLOCK }
      .factory { c, p -> FallingCoconutBlock(p, item) }
      .blockTags(BlockTags.MINEABLE_WITH_AXE)
      .properties { c, p ->
        p.mapColor(color)
          .strength(0.2f)
          .sound(SoundType.BAMBOO_WOOD)
          .noOcclusion()
          .pushReaction(PushReaction.DESTROY)
      }
      .blockstate(BlockstatePresets.fallingCoconutBlock(name))
      .cutoutRender()
      .noItem()
      .loot { g, b -> g.dropAnother(b.get(), item.get()) }
      .register()
  }
}