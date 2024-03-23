package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.CustomCandleCakeBlock
import com.dannbrown.braziliandelight.content.block.PuddingBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.databoxlib.init.DataboxTags
import com.dannbrown.databoxlib.registry.transformers.BlockLootPresets
import com.dannbrown.databoxlib.registry.transformers.ItemModelPresets
import com.dannbrown.databoxlib.registry.transformers.RecipePresets
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.block.PieBlock
import java.util.function.Supplier

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  val CARROT_CAKE_CANDLE_COLORS = createCandleCakes("carrot_cake") { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CustomCakeBlock> = BLOCKS.create<CustomCakeBlock>("carrot_cake")
    .copyFrom { Blocks.CAKE }
    .blockFactory { p -> CustomCakeBlock(p, { AddonItems.CARROT_CAKE_SLICE.get() }, CARROT_CAKE_CANDLE_COLORS) }
    .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.cakeBlock())
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t.item()
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
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t.item()
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()


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