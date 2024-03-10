package com.dannbrown.braziliandelight.datagen.content.transformers

import com.tterrag.registrate.providers.DataGenContext
import com.tterrag.registrate.providers.RegistrateBlockstateProvider
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraftforge.client.model.generators.ConfiguredModel
import vectorwing.farmersdelight.common.block.PieBlock

object CustomBlockstatePresets {

  fun <B : Block> cakeCandleBlock(blockName: String, candleColor: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val lit = state.getValue(CandleCakeBlock.LIT)
          val suffix = if (lit) "_lit" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.mcLoc("block/template_cake_with_candle"))
                .texture("candle", p.mcLoc("block/${candleColor}_candle${suffix}"))
                .texture("inside", p.modLoc("block/${blockName}_inner"))
                .texture("bottom", p.modLoc("block/${blockName}_bottom"))
                .texture("top", p.modLoc("block/${blockName}_top"))
                .texture("side", p.modLoc("block/${blockName}_side"))
                .texture("particle", p.modLoc("block/${blockName}_side"))
            )
            .build()
        }

    }
  }

  fun <B : Block> cakeBlock(): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val bites: Int = state.getValue(CakeBlock.BITES)
          val suffix = if (bites > 0) "_slice$bites" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.mcLoc("block/cake$suffix"))
                .texture("inside", p.modLoc("block/${c.name}_inner"))
                .texture("bottom", p.modLoc("block/${c.name}_bottom"))
                .texture("top", p.modLoc("block/${c.name}_top"))
                .texture("side", p.modLoc("block/${c.name}_side"))
                .texture("particle", p.modLoc("block/${c.name}_side"))
            )
            .build()
        }
    }
  }

  fun <B : Block> pieBlock(): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val bites: Int = state.getValue(PieBlock.BITES)
          val suffix = if (bites > 0) "_slice$bites" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.modLoc("block/pie$suffix"))
                .texture("inner", p.modLoc("block/${c.name}_inner"))
                .texture("bottom", p.modLoc("block/${c.name}_bottom"))
                .texture("top", p.modLoc("block/${c.name}_top"))
                .texture("side", p.modLoc("block/${c.name}_side"))
                .texture("particle", p.modLoc("block/${c.name}_side"))
            )
            .rotationY((state.getValue(PieBlock.FACING).toYRot().toInt() + 180) % 360)
            .build()
        }
    }
  }
}