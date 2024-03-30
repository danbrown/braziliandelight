package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.CustomCandleCakeBlock
import com.dannbrown.braziliandelight.content.block.LoveAppleTrayBlock
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.databoxlib.registry.transformers.BlockLootPresets
import com.dannbrown.databoxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.registry.ModItems
import java.util.function.Supplier

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  val BEAN_POD_CRATE = createCrateBlock(AddonNames.BEAN_POD, MapColor.COLOR_LIGHT_GREEN, { AddonItems.BEAN_POD.get() }, { DataIngredient.tag(AddonTags.ITEM.BEAN_PODS) })
  val BLACK_BEANS_CRATE = createCrateBlock(AddonNames.BLACK_BEANS, MapColor.COLOR_BLACK, { AddonItems.BLACK_BEANS.get() }, { DataIngredient.items(AddonItems.BLACK_BEANS.get()) })
  val CARIOCA_BEANS_CRATE = createCrateBlock(AddonNames.CARIOCA_BEANS, MapColor.TERRACOTTA_ORANGE, { AddonItems.CARIOCA_BEANS.get() }, { DataIngredient.items(AddonItems.CARIOCA_BEANS.get()) })
  val GARLIC_BULB_CRATE = createCrateBlock(AddonNames.GARLIC_BULB, MapColor.TERRACOTTA_WHITE, { AddonItems.GARLIC_BULB.get() }, { DataIngredient.tag(AddonTags.ITEM.GARLIC) })
  val ACAI_BERRIES_CRATE = createCrateBlock(AddonNames.ACAI_BERRIES, MapColor.COLOR_PURPLE, { AddonItems.ACAI_BERRIES.get() }, { DataIngredient.tag(AddonTags.ITEM.ACAI) })
  val GUARANA_FRUIT_CRATE = createCrateBlock(AddonNames.GUARANA_FRUIT, MapColor.COLOR_RED, { AddonItems.GUARANA_FRUIT.get() }, { DataIngredient.tag(AddonTags.ITEM.GUARANA) })
  val GREEN_COCONUT_CRATE = createCrateBlock(AddonNames.GREEN_COCONUT, MapColor.COLOR_GREEN, { AddonItems.GREEN_COCONUT.get() }, { DataIngredient.items(AddonItems.GREEN_COCONUT.get()) })
  val COCONUT_CRATE = createCrateBlock(AddonNames.COCONUT, MapColor.COLOR_BROWN, { AddonItems.COCONUT.get() }, { DataIngredient.tag(AddonTags.ITEM.COCONUT) })
  val CORN_CRATE = createCrateBlock(AddonNames.CORN, MapColor.COLOR_YELLOW, { AddonItems.CORN.get() }, { DataIngredient.tag(AddonTags.ITEM.CORN) })
  val CASSAVA_CRATE = createCrateBlock(AddonNames.CASSAVA, MapColor.COLOR_BROWN, { AddonItems.CASSAVA_ROOT.get() }, { DataIngredient.tag(AddonTags.ITEM.CASSAVA) })
  val COLLARD_GREENS_CRATE = createCrateBlock(AddonNames.COLLARD_GREENS, MapColor.COLOR_GREEN, { AddonItems.COLLARD_GREENS.get() }, { DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS) })
  val COFFEE_BERRIES_CRATE = createCrateBlock(AddonNames.COFFEE_BERRIES, MapColor.COLOR_BROWN, { AddonItems.COFFEE_BERRIES.get() }, { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) })
  val COFFEE_BEANS_BAG = crateBagBlock(AddonNames.COFFEE_BEANS, MapColor.COLOR_BROWN, { AddonItems.COFFEE_BEANS.get() }, { DataIngredient.tag(AddonTags.ITEM.COFFEE_BEANS) })
  val CORN_GRAINS_BAG = crateBagBlock(AddonNames.CORN_GRAINS, MapColor.COLOR_YELLOW, { AddonItems.CORN_GRAINS.get() }, { DataIngredient.items(AddonItems.CORN_GRAINS.get()) })
  val GUARANA_POWDER_BAG = crateBagBlock(AddonNames.GUARANA_POWDER, MapColor.COLOR_RED, { AddonItems.GUARANA_POWDER.get() }, { DataIngredient.items(AddonItems.GUARANA_POWDER.get()) })
  val CASSAVA_FLOUR_BAG = crateBagBlock(AddonNames.CASSAVA_FLOUR, MapColor.COLOR_BROWN, { AddonItems.CASSAVA_FLOUR.get() }, { DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()) })
  val CORN_FLOUR_BAG = crateBagBlock(AddonNames.CORN_FLOUR, MapColor.COLOR_YELLOW, { AddonItems.CORN_FLOUR.get() }, { DataIngredient.items(AddonItems.CORN_FLOUR.get()) })
  val SALT_BAG = crateBagBlock(AddonNames.SALT, MapColor.WOOL, { AddonItems.SALT.get() }, { DataIngredient.tag(AddonTags.ITEM.SALT) })


  val CARROT_CAKE_CANDLE_COLORS = createCandleCakes(AddonNames.CARROT_CAKE) { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CustomCakeBlock> = createCakeBlock(AddonNames.CARROT_CAKE, MapColor.COLOR_ORANGE, { AddonItems.CARROT_CAKE_SLICE.get() }, CARROT_CAKE_CANDLE_COLORS)

  val CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS = createCandleCakes(AddonNames.CARROT_CAKE_WITH_CHOCOLATE) { CARROT_CAKE_WITH_CHOCOLATE.get() }
  val CARROT_CAKE_WITH_CHOCOLATE: BlockEntry<CustomCakeBlock> = createCakeBlock(AddonNames.CARROT_CAKE_WITH_CHOCOLATE, MapColor.COLOR_ORANGE, { AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }, CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS)

  val MINAS_CHEESE: BlockEntry<PieBlock> = createCheeseBlock(AddonNames.MINAS_CHEESE, MapColor.TERRACOTTA_WHITE) { AddonItems.MINAS_CHEESE_SLICE.get() }
  val CHICKEN_POT_PIE: BlockEntry<PieBlock> = createPieBlock(AddonNames.CHICKEN_POT_PIE, MapColor.SAND) { AddonItems.CHICKEN_POT_PIE_SLICE.get() }
  val PUDDING: BlockEntry<PlaceableFoodBlock> = createPuddingBlock(AddonNames.PUDDING, MapColor.COLOR_BROWN) { AddonItems.PUDDING_SLICE.get() }
  val SALPICAO: BlockEntry<PlaceableFoodBlock> = createPlateFoodBlock(AddonNames.SALPICAO, MapColor.COLOR_ORANGE) { AddonItems.PLATE_OF_SALPICAO.get() }
  val CUZCUZ_PAULISTA: BlockEntry<PlaceableFoodBlock> = createPlateFoodBlock(AddonNames.CUZCUZ_PAULISTA, MapColor.TERRACOTTA_ORANGE) { AddonItems.PLATE_OF_CUZCUZ_PAULISTA.get() }
  val FEIJOADA_POT: BlockEntry<PlaceableFoodBlock> = createPotBlock(AddonNames.FEIJOADA_POT, MapColor.COLOR_BLACK) { AddonItems.PLATE_OF_FEIJOADA.get() }
  val GREEN_SOUP_POT: BlockEntry<PlaceableFoodBlock> = createPotBlock(AddonNames.GREEN_SOUP_POT, MapColor.COLOR_GREEN) { AddonItems.PLATE_OF_GREEN_SOUP.get() }
  val FISH_MOQUECA_POT: BlockEntry<PlaceableFoodBlock> = createPotBlock(AddonNames.FISH_MOQUECA_POT, MapColor.COLOR_ORANGE) { AddonItems.PLATE_OF_FISH_MOQUECA.get() }
  val STROGANOFF_POT: BlockEntry<PlaceableFoodBlock> = createPotBlock(AddonNames.STROGANOFF_POT, MapColor.COLOR_RED) { AddonItems.PLATE_OF_STROGANOFF.get() }

  val SWEET_LOVE_APPLE_TRAY: BlockEntry<LoveAppleTrayBlock> = createLoveAppleTrayBlock(AddonNames.SWEET_LOVE_APPLE_TRAY, MapColor.COLOR_RED) { AddonItems.SWEET_LOVE_APPLE.get() }

  // This function creates a crate block
  private fun createCrateBlock(name: String, color: MapColor, item: Supplier<ItemLike>, ingredient: Supplier<DataIngredient>): BlockEntry<Block> {
    val blockId = "${name}_crate"
    return BLOCKS.create<Block>(blockId)
      .copyFrom { Blocks.OAK_PLANKS }
      .color(color)
      .blockstate(CustomBlockstatePresets.crateBlock(blockId))
      .toolAndTier(BlockTags.MINEABLE_WITH_AXE, null)
      .storageBlock(item, ingredient, false)
      .register()
  }

  private fun crateBagBlock(name: String, color: MapColor, item: Supplier<ItemLike>, ingredient: Supplier<DataIngredient>): BlockEntry<Block> {
    val blockId = "${name}_bag"
    return BLOCKS.create<Block>(blockId)
      .copyFrom { Blocks.WHITE_WOOL }
      .color(color)
      .blockstate(CustomBlockstatePresets.bagBlock(blockId))
      .toolAndTier(BlockTags.MINEABLE_WITH_AXE, null)
      .storageBlock(item, ingredient, false)
      .register()
  }

  private fun createPuddingBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS.create<PlaceableFoodBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PlaceableFoodBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.puddingBlock())
      .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, PlaceableFoodBlock.USES, 0))
      .transform { t ->
        t.item()
          .properties { p -> p.stacksTo(1) }
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createPlateFoodBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS.create<PlaceableFoodBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PlaceableFoodBlock(p, item, true) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.puddingBlock())
      .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, PlaceableFoodBlock.USES, 0))
      .transform { t ->
        t.item()
          .properties { p -> p.stacksTo(1) }
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createLoveAppleTrayBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<LoveAppleTrayBlock> {
    return BLOCKS.create<LoveAppleTrayBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> LoveAppleTrayBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.loveAppleTrayBlock())
      .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, LoveAppleTrayBlock.PARTS, 0))
      .transform { t ->
        t.item()
          .properties { p -> p.stacksTo(1) }
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createPotBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<PlaceableFoodBlock> {
    return BLOCKS.create<PlaceableFoodBlock>(name)
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

          override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
            return POT_SHAPE
          }
        }
      }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY).sound(SoundType.LANTERN) }
      .blockstate(CustomBlockstatePresets.heavyPotBlock())
      .loot(BlockLootPresets.dropItselfOtherConditionLoot({ ModItems.COOKING_POT.get() }, PlaceableFoodBlock.USES, 0))
      .transform { t ->
        t.item()
          .properties { p -> p.stacksTo(1) }
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createCheeseBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<PieBlock> {
    return BLOCKS.create<PieBlock>(name)
      .copyFrom { Blocks.SLIME_BLOCK }
      .color(color)
      .blockFactory { p -> PieBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.pieBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.item()
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createPieBlock(name: String, color: MapColor, item: Supplier<Item>): BlockEntry<PieBlock> {
    return BLOCKS.create<PieBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> PieBlock(p, item) }
      .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.pieBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.item()
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  private fun createCakeBlock(name: String, color: MapColor, item: Supplier<Item>, candleColors: List<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>>): BlockEntry<CustomCakeBlock> {
    return BLOCKS.create<CustomCakeBlock>(name)
      .copyFrom { Blocks.CAKE }
      .color(color)
      .blockFactory { p -> CustomCakeBlock(p, item, candleColors) }
      .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
      .blockstate(CustomBlockstatePresets.cakeBlock())
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.item()
          .properties { p -> p.stacksTo(1) }
          .model(ItemModelPresets.simpleItem())
          .build()
      }
      .register()
  }

  // This function create a cake with candle for all colors
  private fun createCandleCakes(_name: String, baseCake: Supplier<CustomCakeBlock>): List<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>> {
    val returns: MutableList<Triple<String, CandleBlock, BlockEntry<CustomCandleCakeBlock>>> = mutableListOf()
    val colors = listOf(
      Triple ("", Blocks.CANDLE, Blocks.CANDLE_CAKE),
      Triple ("white", Blocks.WHITE_CANDLE, Blocks.WHITE_CANDLE_CAKE),
      Triple ("light_gray", Blocks.LIGHT_GRAY_CANDLE, Blocks.LIGHT_GRAY_CANDLE_CAKE),
      Triple ("gray", Blocks.GRAY_CANDLE, Blocks.GRAY_CANDLE_CAKE),
      Triple ("black", Blocks.BLACK_CANDLE, Blocks.BLACK_CANDLE_CAKE),
      Triple ("brown", Blocks.BROWN_CANDLE, Blocks.BROWN_CANDLE_CAKE),
      Triple ("red", Blocks.RED_CANDLE, Blocks.RED_CANDLE_CAKE),
      Triple ("orange", Blocks.ORANGE_CANDLE, Blocks.ORANGE_CANDLE_CAKE),
      Triple ("yellow", Blocks.YELLOW_CANDLE, Blocks.YELLOW_CANDLE_CAKE),
      Triple ("lime", Blocks.LIME_CANDLE, Blocks.LIME_CANDLE_CAKE),
      Triple ("green", Blocks.GREEN_CANDLE, Blocks.GREEN_CANDLE_CAKE),
      Triple ("cyan", Blocks.CYAN_CANDLE, Blocks.CYAN_CANDLE_CAKE),
      Triple ("blue", Blocks.BLUE_CANDLE, Blocks.BLUE_CANDLE_CAKE),
      Triple ("light_blue", Blocks.LIGHT_BLUE_CANDLE, Blocks.LIGHT_BLUE_CANDLE_CAKE),
      Triple ("magenta", Blocks.MAGENTA_CANDLE, Blocks.MAGENTA_CANDLE_CAKE),
      Triple ("purple", Blocks.PURPLE_CANDLE, Blocks.PURPLE_CANDLE_CAKE),
      Triple ("pink", Blocks.PINK_CANDLE, Blocks.PINK_CANDLE_CAKE),
    )

    for (entry in colors){
      val blockId = "${_name}_with_${entry.first}_candle"
      returns.add(
        Triple (
          entry.first,
          entry.second as CandleBlock,
          BLOCKS.create<CustomCandleCakeBlock>(blockId)
            .copyFrom { entry.third }
            .blockFactory { p -> CustomCandleCakeBlock(p, { entry.second as CandleBlock }, baseCake)  }
            .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
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