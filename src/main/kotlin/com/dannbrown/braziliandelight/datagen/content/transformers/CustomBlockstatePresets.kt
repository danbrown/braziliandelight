package com.dannbrown.braziliandelight.datagen.content.transformers

import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.databoxlib.registry.transformers.BlockstatePresets
import com.tterrag.registrate.providers.DataGenContext
import com.tterrag.registrate.providers.RegistrateBlockstateProvider
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraftforge.client.model.generators.ConfiguredModel
import vectorwing.farmersdelight.common.block.PieBlock

object CustomBlockstatePresets {

  fun <B : Block> cakeCandleBlock(blockName: String, candleColor: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val lit = state.getValue(AbstractCandleBlock.LIT)
          val suffix = if (lit) "_lit" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.mcLoc("block/template_cake_with_candle"))
                .texture("candle", p.mcLoc("block/${if(candleColor == "") "" else "${candleColor}_"}candle${suffix}"))
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

  fun <B : Block> puddingBlock(): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val uses: Int = state.getValue(PlaceableFoodBlock.USES)
          val suffix = if (uses > 0) "_slice$uses" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.modLoc("block/pudding_base$suffix"))
                .texture("tray_top", p.modLoc("block/tray_top"))
                .texture("tray_bottom", p.modLoc("block/tray_bottom"))
                .texture("inner", p.modLoc("block/${c.name}_inner"))
                .texture("bottom", p.modLoc("block/${c.name}_bottom"))
                .texture("top", p.modLoc("block/${c.name}_top"))
                .texture("side", p.modLoc("block/${c.name}_side"))
                .texture("particle", p.modLoc("block/${c.name}_side"))
            )
            .rotationY((state.getValue(PlaceableFoodBlock.FACING).toYRot().toInt() + 180) % 360)
            .build()
        }
    }
  }

  fun <B : Block> heavyPotBlock(): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val uses: Int = state.getValue(PlaceableFoodBlock.USES)
          val suffix = if (uses > 0) "_level$uses" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.modLoc("block/heavy_pot$suffix"))
                .texture("bottom", p.modLoc("block/cooking_pot_bottom"))
                .texture("hollow", p.modLoc("block/cooking_pot_hollow"))
                .texture("parts", p.modLoc("block/cooking_pot_parts"))
                .texture("side", p.modLoc("block/cooking_pot_side"))
                .texture("top", p.modLoc("block/cooking_pot_top"))
                .texture("inside", p.modLoc("block/${c.name}_inside"))
                .texture("particle", p.modLoc("block/${c.name}_inside"))
                .renderType("cutout_mipped")
            )
            .rotationY((state.getValue(PlaceableFoodBlock.FACING).toYRot().toInt() + 180) % 360)
            .build()
        }
    }
  }

  fun <B : Block> crateBlock(name: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return BlockstatePresets.cubeBottomTopBlock("${name}_side", "crate_bottom", "${name}_top")
  }

  fun <B : Block> bagBlock(name: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.simpleBlock(c.get(),
        p.models()
          .withExistingParent(c.name, p.mcLoc("block/cube"))
          .texture("down", p.modLoc("block/bag_bottom"))
          .texture("east", p.modLoc("block/bag_side"))
          .texture("north", p.modLoc("block/bag_side_tied"))
          .texture("particle", p.modLoc("block/${name}_top"))
          .texture("south", p.modLoc("block/bag_side_tied"))
          .texture("up", p.modLoc("block/${name}_top"))
          .texture("west", p.modLoc("block/bag_side"))
      )
    }
  }
}