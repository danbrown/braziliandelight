package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.CustomCandleCakeBlock
import com.dannbrown.braziliandelight.content.block.LoveAppleTrayBlock
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.registry.ModItems
import java.util.function.Supplier

object FeastBuilderPresets {

  fun createPuddingBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS
      .create<PlaceableFoodBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PlaceableFoodBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.puddingBlock())
      .loot(
        BlockLootPresets.dropItselfOtherConditionLoot(
          { Items.BOWL },
          PlaceableFoodBlock.USES,
          0
        )
      )
      .transform { t ->
        t.item().properties { p -> p.stacksTo(1) }.model(ItemModelPresets.simpleItem()).build()
      }
      .register()
  }

  fun createPlateFoodBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS
      .create<PlaceableFoodBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PlaceableFoodBlock(p, item, true) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.puddingBlock())
      .loot(
        BlockLootPresets.dropItselfOtherConditionLoot(
          { Items.BOWL },
          PlaceableFoodBlock.USES,
          0
        )
      )
      .transform { t ->
        t.item().properties { p -> p.stacksTo(1) }.model(ItemModelPresets.simpleItem()).build()
      }
      .register()
  }

  fun createLoveAppleTrayBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<LoveAppleTrayBlock> {
    return BLOCKS
      .create<LoveAppleTrayBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> LoveAppleTrayBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.loveAppleTrayBlock())
      .loot(
        BlockLootPresets.dropItselfOtherConditionLoot(
          { Items.BOWL },
          LoveAppleTrayBlock.PARTS,
          0
        )
      )
      .transform { t ->
        t.item().properties { p -> p.stacksTo(1) }.model(ItemModelPresets.simpleItem()).build()
      }
      .register()
  }

  fun createPotBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS
      .create<PlaceableFoodBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p ->
        object : PlaceableFoodBlock(p, item, true) {
          override fun getPlateSound(): SoundEvent {
            return SoundEvents.LANTERN_BREAK
          }

          override fun getFoodSound(): SoundEvent {
            return SoundEvents.GENERIC_DRINK
          }

          override fun getShape(
            state: BlockState,
            level: BlockGetter,
            pos: BlockPos,
            context: CollisionContext
          ): VoxelShape {
            return POT_SHAPE
          }
        }
      }
      .properties { p ->
        p.strength(0.5f)
          .forceSolidOn()
          .pushReaction(PushReaction.DESTROY)
          .sound(SoundType.LANTERN)
      }
      .blockstate(CustomBlockstatePresets.heavyPotBlock())
      .loot(
        BlockLootPresets.dropItselfOtherConditionLoot(
          { ModItems.COOKING_POT.get() },
          PlaceableFoodBlock.USES,
          0
        )
      )
      .transform { t ->
        t.item().properties { p -> p.stacksTo(1) }.model(ItemModelPresets.simpleItem()).build()
      }
      .register()
  }

  fun createCheeseBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<PieBlock> {
    return BLOCKS
      .create<PieBlock>(name)
      .copyFrom { Blocks.SLIME_BLOCK }
      .color(color)
      .blockFactory { p -> PieBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.pieBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t -> t.item().model(ItemModelPresets.simpleItem()).build() }
      .register()
  }

  fun createPieBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>
  ): BlockEntry<PieBlock> {
    return BLOCKS
      .create<PieBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PieBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.pieBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t -> t.item().model(ItemModelPresets.simpleItem()).build() }
      .register()
  }

  fun createCakeBlock(
    name: String,
    color: MapColor,
    item: Supplier<Item>,
    candleColors: List<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>>
  ): BlockEntry<CustomCakeBlock> {
    return BLOCKS
      .create<CustomCakeBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> CustomCakeBlock(p, item, candleColors) }
      .properties { p ->
        p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY)
      }
      .blockstate(CustomBlockstatePresets.cakeBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.item().properties { p -> p.stacksTo(1) }.model(ItemModelPresets.simpleItem()).build()
      }
      .register()
  }

  // This function create a cake with candle for all colors
  fun createCandleCakes(
    _name: String,
    baseCake: Supplier<CustomCakeBlock>
  ): List<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>> {
    val returns: MutableList<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>> =
      mutableListOf()
    val colors =
      listOf(
        Triple("", Blocks.CANDLE, Blocks.CANDLE_CAKE),
        Triple("white", Blocks.WHITE_CANDLE, Blocks.WHITE_CANDLE_CAKE),
        Triple("light_gray", Blocks.LIGHT_GRAY_CANDLE, Blocks.LIGHT_GRAY_CANDLE_CAKE),
        Triple("gray", Blocks.GRAY_CANDLE, Blocks.GRAY_CANDLE_CAKE),
        Triple("black", Blocks.BLACK_CANDLE, Blocks.BLACK_CANDLE_CAKE),
        Triple("brown", Blocks.BROWN_CANDLE, Blocks.BROWN_CANDLE_CAKE),
        Triple("red", Blocks.RED_CANDLE, Blocks.RED_CANDLE_CAKE),
        Triple("orange", Blocks.ORANGE_CANDLE, Blocks.ORANGE_CANDLE_CAKE),
        Triple("yellow", Blocks.YELLOW_CANDLE, Blocks.YELLOW_CANDLE_CAKE),
        Triple("lime", Blocks.LIME_CANDLE, Blocks.LIME_CANDLE_CAKE),
        Triple("green", Blocks.GREEN_CANDLE, Blocks.GREEN_CANDLE_CAKE),
        Triple("cyan", Blocks.CYAN_CANDLE, Blocks.CYAN_CANDLE_CAKE),
        Triple("blue", Blocks.BLUE_CANDLE, Blocks.BLUE_CANDLE_CAKE),
        Triple("light_blue", Blocks.LIGHT_BLUE_CANDLE, Blocks.LIGHT_BLUE_CANDLE_CAKE),
        Triple("magenta", Blocks.MAGENTA_CANDLE, Blocks.MAGENTA_CANDLE_CAKE),
        Triple("purple", Blocks.PURPLE_CANDLE, Blocks.PURPLE_CANDLE_CAKE),
        Triple("pink", Blocks.PINK_CANDLE, Blocks.PINK_CANDLE_CAKE),
      )

    for (entry in colors) {
      val blockId = "${_name}_with_${entry.first}_candle"
      returns.add(
        Triple(
          entry.first,
          entry.second as CandleBlock,
          BLOCKS
            .create<CustomCandleCakeBlock>(blockId)
            .copyFrom { entry.third }
            .blockFactory { p ->
              CustomCandleCakeBlock(p, { entry.second as CandleBlock }, baseCake)
            }
            .properties { p ->
              p.strength(0.5f)
                .sound(SoundType.WOOL)
                .forceSolidOn()
                .pushReaction(PushReaction.DESTROY)
            }
            .blockstate(CustomBlockstatePresets.cakeCandleBlock(_name, entry.first))
            .loot(BlockLootPresets.dropOtherLoot { entry.second.asItem() })
            .noItem()
            .register()
        )
      )
    }

    return returns
  }
}