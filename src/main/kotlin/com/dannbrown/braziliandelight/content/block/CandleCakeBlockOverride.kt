package com.dannbrown.braziliandelight.content.block

import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.CandleCakeBlock
import java.util.function.Supplier

class CandleCakeBlockOverride(props: Properties, private val candleBlock: Supplier<CandleBlock>, private val cakeBlock: Supplier<CakeBlockOverride>)
  : CandleCakeBlock(candleBlock.get(), props.lightLevel { if (it.getValue(LIT)) 0 else 3 }){

}