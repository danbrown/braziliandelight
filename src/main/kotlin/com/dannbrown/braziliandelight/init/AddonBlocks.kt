package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.CakeBlockOverride
import com.dannbrown.braziliandelight.content.block.CandleCakeBlockOverride
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.databoxlib.registry.transformers.BlockLootPresets
import com.dannbrown.databoxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.material.PushReaction
import net.minecraftforge.eventbus.api.IEventBus
import java.util.function.Supplier

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  val CARROT_CAKE_CANDLE_COLORS = createCandleCakes("carrot_cake") { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CakeBlockOverride> = BLOCKS.create<CakeBlockOverride>("carrot_cake")
    .copyFrom { Blocks.CAKE }
    .blockFactory { p -> CakeBlockOverride(p, { AddonItems.CARROT_CAKE_SLICE.get() }, CARROT_CAKE_CANDLE_COLORS) }
    .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
    .blockstate(CustomBlockstatePresets.cakeBlock())
    .loot(BlockLootPresets.noLoot())
    .transform { t ->
      t.item()
        .model(ItemModelPresets.simpleItem())
        .build()
    }
    .register()


  // This function create a cake with candle for all colors
  private fun createCandleCakes(_name: String, baseCake: Supplier<CakeBlockOverride>): List<Triple<String, CandleBlock, BlockEntry<CandleCakeBlockOverride>>> {
    val returns: MutableList<Triple<String, CandleBlock, BlockEntry<CandleCakeBlockOverride>>> = mutableListOf()
    val colors = listOf(
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

//    for (entry in colors){
//      returns.add(
//        Triple (
//          entry.first,
//          entry.second as CandleBlock,
//          BLOCKS.create<CandleCakeBlockOverride>("${entry.first}_candle_${_name}")
//            .copyFrom { entry.third }
//            .blockFactory { p -> CandleCakeBlockOverride(p, { entry.second as CandleBlock }, baseCake)  }
//            .properties { p -> p.strength(0.5f).sound(SoundType.WOOL).forceSolidOn().pushReaction(PushReaction.DESTROY) }
//            .blockstate(CustomBlockstatePresets.cakeCandleBlock(_name, entry.first))
//            .loot(BlockLootPresets.noLoot())
//            .noItem()
//            .register()
//        )
//      )
//    }

    return returns
  }
}