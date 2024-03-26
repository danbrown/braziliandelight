package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.CustomCandleCakeBlock
import com.dannbrown.braziliandelight.content.block.PuddingBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.databoxlib.registry.transformers.BlockLootPresets
import com.dannbrown.databoxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.block.PieBlock
import java.util.function.Supplier

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  val BEAN_POD_CRATE = createCrateBlock("bean_pod", MapColor.COLOR_LIGHT_GREEN, { AddonItems.BEAN_POD.get() }, { DataIngredient.tag(AddonTags.ITEM.BEAN_PODS) })
  val BLACK_BEANS_CRATE = createCrateBlock("black_beans", MapColor.COLOR_BLACK, { AddonItems.BLACK_BEANS.get() }, { DataIngredient.items(AddonItems.BLACK_BEANS.get()) })
  val CARIOCA_BEANS_CRATE = createCrateBlock("carioca_beans", MapColor.TERRACOTTA_ORANGE, { AddonItems.CARIOCA_BEANS.get() }, { DataIngredient.items(AddonItems.CARIOCA_BEANS.get()) })
  val GARLIC_CRATE = createCrateBlock("garlic", MapColor.TERRACOTTA_WHITE, { AddonItems.GARLIC_BULB.get() }, { DataIngredient.tag(AddonTags.ITEM.GARLIC) })
  val ACAI_BERRIES_CRATE = createCrateBlock("acai_berries", MapColor.COLOR_PURPLE, { AddonItems.ACAI_BERRIES.get() }, { DataIngredient.tag(AddonTags.ITEM.ACAI) })
  val GUARANA_FRUIT_CRATE = createCrateBlock("guarana_fruit", MapColor.COLOR_RED, { AddonItems.GUARANA_FRUIT.get() }, { DataIngredient.tag(AddonTags.ITEM.GUARANA) })
  val GREEN_COCONUT_CRATE = createCrateBlock("green_coconut", MapColor.COLOR_GREEN, { AddonItems.GREEN_COCONUT.get() }, { DataIngredient.items(AddonItems.GREEN_COCONUT.get()) })
  val COCONUT_CRATE = createCrateBlock("coconut", MapColor.COLOR_BROWN, { AddonItems.COCONUT.get() }, { DataIngredient.tag(AddonTags.ITEM.COCONUT) })
  val CORN_CRATE = createCrateBlock("corn", MapColor.COLOR_YELLOW, { AddonItems.CORN.get() }, { DataIngredient.tag(AddonTags.ITEM.CORN) })
  val CASSAVA_CRATE = createCrateBlock("cassava", MapColor.COLOR_BROWN, { AddonItems.CASSAVA_ROOT.get() }, { DataIngredient.tag(AddonTags.ITEM.CASSAVA) })
  val COLLARD_GREENS_CRATE = createCrateBlock("collard_greens", MapColor.COLOR_GREEN, { AddonItems.COLLARD_GREENS.get() }, { DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS) })
  val COFFEE_BERRIES_CRATE = createCrateBlock("coffee_berries", MapColor.COLOR_BROWN, { AddonItems.COFFEE_BERRIES.get() }, { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) })
  val COFFEE_BEANS_BAG = crateBagBlock("coffee_beans", MapColor.COLOR_BROWN, { AddonItems.COFFEE_BEANS.get() }, { DataIngredient.tag(AddonTags.ITEM.COFFEE_BEANS) })
  val CORN_GRAINS_BAG = crateBagBlock("corn_grains", MapColor.COLOR_YELLOW, { AddonItems.CORN_GRAINS.get() }, { DataIngredient.items(AddonItems.CORN_GRAINS.get()) })
  val GUARANA_POWDER_BAG = crateBagBlock("guarana_powder", MapColor.COLOR_RED, { AddonItems.GUARANA_POWDER.get() }, { DataIngredient.items(AddonItems.GUARANA_POWDER.get()) })
  val CASSAVA_FLOUR_BAG = crateBagBlock("cassava_flour", MapColor.COLOR_BROWN, { AddonItems.CASSAVA_FLOUR.get() }, { DataIngredient.items(AddonItems.CASSAVA_FLOUR.get()) })
  val CORN_FLOUR_BAG = crateBagBlock("corn_flour", MapColor.COLOR_YELLOW, { AddonItems.CORN_FLOUR.get() }, { DataIngredient.items(AddonItems.CORN_FLOUR.get()) })
  val SALT_BAG = crateBagBlock("salt", MapColor.WOOL, { AddonItems.SALT.get() }, { DataIngredient.tag(AddonTags.ITEM.SALT) })

  val CARROT_CAKE_CANDLE_COLORS = createCandleCakes("carrot_cake") { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CustomCakeBlock> = BLOCKS.create<CustomCakeBlock>("carrot_cake")
    .copyFrom { Blocks.CAKE }
    .blockFactory { p -> CustomCakeBlock(p, { AddonItems.CARROT_CAKE_SLICE.get() }, CARROT_CAKE_CANDLE_COLORS) }
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

  val CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS = createCandleCakes("carrot_cake_with_chocolate") { CARROT_CAKE_WITH_CHOCOLATE.get() }
  val CARROT_CAKE_WITH_CHOCOLATE: BlockEntry<CustomCakeBlock> = BLOCKS.create<CustomCakeBlock>("carrot_cake_with_chocolate")
    .copyFrom { Blocks.CAKE }
    .blockFactory { p -> CustomCakeBlock(p, { AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }, CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS) }
    .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.cakeBlock())
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t
        .lang("Carrot Cake with Chocolate")
        .item()
        .properties { p -> p.stacksTo(1) }
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()

  val MINAS_CHEESE: BlockEntry<PieBlock> = BLOCKS.create<PieBlock>("minas_cheese")
    .copyFrom { Blocks.SLIME_BLOCK }
    .color(MapColor.SAND)
    .blockFactory { p -> PieBlock(p) { AddonItems.MINAS_CHEESE_SLICE.get() } }
    .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.pieBlock())
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t.item()
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()

  val CHICKEN_POT_PIE: BlockEntry<PieBlock> = BLOCKS.create<PieBlock>("chicken_pot_pie")
    .copyFrom { Blocks.CAKE }
    .color(MapColor.SAND)
    .blockFactory { p -> PieBlock(p) { AddonItems.CHICKEN_POT_PIE_SLICE.get() } }
    .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.pieBlock())
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t.item()
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()

  val PUDDING: BlockEntry<PuddingBlock> = BLOCKS.create<PuddingBlock>("pudding")
    .copyFrom { Blocks.CAKE }
    .color(MapColor.SAND)
    .blockFactory { p -> PuddingBlock(p, { AddonItems.PUDDING_SLICE.get() }) }
    .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.puddingBlock())
    .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, PuddingBlock.BITES, 0))
    .transform { t ->
      t.item()
        .properties { p -> p.stacksTo(1) }
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()

  val SALPICAO: BlockEntry<PuddingBlock> = BLOCKS.create<PuddingBlock>("salpicao")
    .copyFrom { Blocks.CAKE }
    .color(MapColor.COLOR_ORANGE)
    .blockFactory { p -> PuddingBlock(p, { AddonItems.PLATE_OF_SALPICAO.get() }, true) }
    .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.puddingBlock())
    .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, PuddingBlock.BITES, 0))
    .transform { t ->
      t.item()
        .properties { p -> p.stacksTo(1) }
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()

  val CUZCUZ_PAULISTA: BlockEntry<PuddingBlock> = BLOCKS.create<PuddingBlock>("cuzcuz_paulista")
  .copyFrom { Blocks.CAKE }
  .color(MapColor.TERRACOTTA_ORANGE)
  .blockFactory { p -> PuddingBlock(p, { AddonItems.PLATE_OF_CUZCUZ_PAULISTA.get() }, true) }
  .properties { p -> p.strength(0.5f).forceSolidOn().pushReaction(PushReaction.DESTROY) }
  .blockstate(CustomBlockstatePresets.puddingBlock())
  .loot(BlockLootPresets.dropItselfOtherConditionLoot({ Items.BOWL }, PuddingBlock.BITES, 0))
  .transform { t ->
    t.item()
      .properties { p -> p.stacksTo(1) }
      .model(ItemModelPresets.simpleItem())
      .build()
  }
  .register()

  // This function creates a crate block
  private fun createCrateBlock(name: String, color: MapColor, item: Supplier<ItemLike>, ingredient: Supplier<DataIngredient>): BlockEntry<Block> {
    return BLOCKS.create<Block>("${name}_crate")
      .copyFrom { Blocks.OAK_PLANKS }
      .color(color)
      .blockstate(CustomBlockstatePresets.crateBlock("${name}_crate"))
      .toolAndTier(BlockTags.MINEABLE_WITH_AXE, null)
      .storageBlock(item, ingredient, false)
      .register()
  }

  private fun crateBagBlock(name: String, color: MapColor, item: Supplier<ItemLike>, ingredient: Supplier<DataIngredient>): BlockEntry<Block> {
    return BLOCKS.create<Block>("${name}_bag")
      .copyFrom { Blocks.WHITE_WOOL }
      .color(color)
      .blockstate(CustomBlockstatePresets.bagBlock("${name}_bag"))
      .toolAndTier(BlockTags.MINEABLE_WITH_AXE, null)
      .storageBlock(item, ingredient, true)
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
      returns.add(
        Triple (
          entry.first,
          entry.second as CandleBlock,
          BLOCKS.create<CustomCandleCakeBlock>("${_name}_with_${entry.first}_candle")
            .copyFrom { entry.third }
            .blockFactory { p -> CustomCandleCakeBlock(p, { entry.second as CandleBlock }, baseCake)  }
            .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
            .blockstate(CustomBlockstatePresets.cakeCandleBlock(_name, entry.first))
            .loot(BlockLootPresets.dropOtherLoot { entry.second.asItem() })
            .transform { t -> t.lang(AddonNames.getNameFromId(_name) + " with${if(entry.first == "") "" else " ${AddonNames.getNameFromId(entry.first)}"}" + " Candle") }
            .noItem()
            .register()
        )
      )
    }

    return returns
  }
}