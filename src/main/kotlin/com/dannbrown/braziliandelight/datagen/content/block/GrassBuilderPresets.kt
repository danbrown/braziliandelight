package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.deltaboxlib.content.block.GenericDoublePlantBlock
import com.dannbrown.deltaboxlib.content.block.GenericGrassBlock
import com.dannbrown.deltaboxlib.content.block.GenericTallGrassBlock
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.BlockstatePresets
import com.dannbrown.deltaboxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.core.BlockPos
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.client.model.generators.ConfiguredModel
import java.util.function.Supplier

// Create TallGrass-like Blocks
object GrassBuilderPresets {
  fun createTallGrassBlock(
    _name: String,
    color: MapColor,
    doubleBlock: Supplier<GenericDoublePlantBlock>,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 0.5f,
    multiplier: Int = 3,
    placeOn:
    ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? =
      null
  ): BlockEntry<GenericTallGrassBlock> {
    return BLOCKS
      .create<GenericTallGrassBlock>(_name)
      .blockFactory { p -> GenericTallGrassBlock(doubleBlock, p, placeOn) }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .transform { t ->
        t
          .blockstate { c, p ->
            p.getVariantBuilder(c.get())
              .partialState()
              .setModels(
                *ConfiguredModel.builder()
                  .modelFile(
                    p.models()
                      .withExistingParent(c.name, p.mcLoc("block/cross"))
                      .texture("cross", p.modLoc("block/${_name}"))
                      .texture("particle", p.modLoc("block/${_name}"))
                      .renderType("cutout_mipped")
                  )
                  .build()
              )
          }
          .item()
          .model { c, p ->
            p.withExistingParent(c.name, p.mcLoc("item/generated"))
              .texture("layer0", p.modLoc("block/${_name}"))
          }
          .build()
      }
      .loot(BlockLootPresets.dropCropLoot(dropItem, seedItem, chance, multiplier))
      .register()
  }


  fun createDoubleTallGrassBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 0.25f,
    multiplier: Int = 2,
    placeOn:
    ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? =
      null,
    prefix: String = "tall_"
  ): BlockEntry<GenericDoublePlantBlock> {
    return BLOCKS
      .create<GenericDoublePlantBlock>("${prefix}${_name}")
      .blockFactory { p -> GenericDoublePlantBlock(p, placeOn) }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .loot(
        BlockLootPresets.dropDoubleCropLoot(
          dropItem,
          seedItem ?: dropItem,
          chance,
          multiplier.toFloat()
        )
      )
      .transform { t ->
        t
          .blockstate { c, p ->
            p.getVariantBuilder(c.get())
              .partialState()
              .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
              .setModels(
                *ConfiguredModel.builder()
                  .modelFile(
                    p.models()
                      .withExistingParent(c.name + "_top", p.mcLoc("block/cross"))
                      .texture("cross", p.modLoc("block/${_name}_top"))
                      .texture("particle", p.modLoc("block/${_name}_top"))
                      .renderType("cutout_mipped")
                  )
                  .build()
              )
              .partialState()
              .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
              .setModels(
                *ConfiguredModel.builder()
                  .modelFile(
                    p.models()
                      .withExistingParent(c.name + "_bottom", p.mcLoc("block/cross"))
                      .texture("cross", p.modLoc("block/${_name}_bottom"))
                      .texture("particle", p.modLoc("block/${_name}_bottom"))
                      .renderType("cutout_mipped")
                  )
                  .build()
              )
          }
          .item()
          .model { c, p ->
            p.withExistingParent(c.name, p.mcLoc("item/generated"))
              .texture("layer0", p.modLoc("block/${_name}_top"))
          }
          .build()
      }
      .register()
  }

  fun createGrassBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<ItemLike>,
    isSticky: Boolean = false,
    isHarmful: Boolean = false,
    isBonemealable: Boolean = false,
    chance: Float = 0.6f,
    multiplier: Int = 2,
    placeOn: ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? = null
  ): BlockEntry<GenericGrassBlock> {
    return BLOCKS
      .create<GenericGrassBlock>(_name)
      .blockFactory { p -> GenericGrassBlock(p, placeOn, isSticky, isHarmful, isBonemealable) }
      .copyFrom { Blocks.GRASS }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.GRASS)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .loot(BlockLootPresets.dropSelfSilkShearsOtherLoot(dropItem, chance, multiplier))
      .transform { t ->
        t.blockstate(BlockstatePresets.simpleCrossBlock(_name))
          .item()
          .model(ItemModelPresets.simpleLayerItem(_name))
          .build()
      }
      .register()
  }

}