package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.deltaboxlib.content.block.GenericSaplingBlock
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.BlockstatePresets
import com.dannbrown.deltaboxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.MapColor
import java.util.function.Supplier

object SaplingBuilderPresets {


  fun createSaplingBlock(
    _name: String,
    color: MapColor,
    grower: AbstractTreeGrower,
    blockTags: List<TagKey<Block>> = listOf(),
    placeOn: ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? = null
  ): BlockEntry<GenericSaplingBlock> {
    return BLOCKS
      .create<GenericSaplingBlock>(_name + "_sapling")
      .blockFactory { p -> GenericSaplingBlock(grower, p, placeOn) }
      .blockTags(listOf(BlockTags.SAPLINGS, *blockTags.toTypedArray()))
      .itemTags(listOf(ItemTags.SAPLINGS))
      .copyFrom { Blocks.OAK_SAPLING }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.GRASS)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .transform { t ->
        t.blockstate(BlockstatePresets.simpleCrossBlock(_name + "_sapling"))
          .item()
          .model(ItemModelPresets.simpleLayerItem(_name + "_sapling"))
          .build()
      }
      .register()
  }

  fun createPottedBlock(
    _name: String,
    color: MapColor,
    block: Supplier<Block>,
    suffix: String = "_sapling"
  ): BlockEntry<FlowerPotBlock> {
    return BLOCKS
      .create<FlowerPotBlock>("potted_$_name" + suffix)
      .blockFactory { p ->
        FlowerPotBlock({ Blocks.FLOWER_POT as FlowerPotBlock }, { block.get() }, p)
      }
      .copyFrom { Blocks.POTTED_POPPY }
      .noItem()
      .properties { p -> p.mapColor(color).noOcclusion() }
      .transform { t ->
        t.blockstate(BlockstatePresets.pottedPlantBlock(_name + suffix))
          .loot(BlockLootPresets.pottedPlantLoot { block.get() })
      }
      .register()
  }

}