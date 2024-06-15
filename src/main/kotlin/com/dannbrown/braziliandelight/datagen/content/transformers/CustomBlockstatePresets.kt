package com.dannbrown.braziliandelight.datagen.content.transformers

import com.dannbrown.braziliandelight.content.block.FallingCoconutBlock
import com.dannbrown.braziliandelight.content.block.LoveAppleTrayBlock
import com.dannbrown.braziliandelight.content.block.MilkPotBlock
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.deltaboxlib.registry.transformers.BlockstatePresets
import com.tterrag.registrate.providers.DataGenContext
import com.tterrag.registrate.providers.RegistrateBlockstateProvider
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import net.minecraft.core.Direction
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
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

  fun <B : Block> loveAppleTrayBlock(): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val uses: Int = state.getValue(LoveAppleTrayBlock.PARTS)
          val suffix = if (uses > 0) "_part$uses" else ""
          ConfiguredModel.builder()
            .modelFile(
              p.models().withExistingParent(c.name + suffix,  p.modLoc("block/apple_tray$suffix"))
                .texture("tray_top", p.modLoc("block/tray_top"))
                .texture("tray_bottom", p.modLoc("block/tray_bottom"))
                .texture("parts", p.modLoc("block/${c.name}_parts"))
                .texture("particle", p.modLoc("block/${c.name}_parts"))
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

  fun <B : Block> cauldronBlock(textureName: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.simpleBlock(c.get(),
        p.models()
          .withExistingParent(c.name, p.mcLoc("block/template_cauldron_full"))
          .texture("content", p.modLoc("block/${textureName}_still"))
          .renderType("cutout_mipped")
      )
    }
  }

  fun <B : Block> potBlock(textureName: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          val mixes = state.getValue(MilkPotBlock.MIXES)
          val isFront = mixes == 1 || mixes == 3
          val isInverted = mixes == 2 || mixes == 1
          val suffix = if (isFront) {
            if (isInverted) "_back" else "_front"
          } else {
            if (isInverted) "_inverted" else ""
          }
          ConfiguredModel.builder()
            .modelFile(
              p.models()
                .withExistingParent(c.name + suffix, p.modLoc("block/heavy_pot$suffix"))
                .texture("bottom", p.modLoc("block/cooking_pot_bottom"))
                .texture("hollow", p.modLoc("block/cooking_pot_hollow"))
                .texture("parts", p.modLoc("block/cooking_pot_parts"))
                .texture("side", p.modLoc("block/cooking_pot_side"))
                .texture("top", p.modLoc("block/cooking_pot_top"))
                .texture("inside", p.modLoc("block/${textureName}_still"))
                .texture("particle", p.modLoc("block/${textureName}_still"))
                .renderType("cutout_mipped")
            )
            .rotationY((state.getValue(MilkPotBlock.FACING).toYRot().toInt() + 180) % 360)
            .build()
        }
    }
  }

  fun <B : Block> coconutBlock(name: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      val baseModel = p.models()
        .withExistingParent(c.name, p.modLoc("block/coconut_block"))
        .texture("texture", p.modLoc("block/$name"))
        .texture("particle", p.modLoc("block/$name"))

      p.getVariantBuilder(c.get())
        .forAllStatesExcept({ state ->
          ConfiguredModel.builder()
            .modelFile(baseModel)
            .rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal * 90)
            .rotationY(((state.getValue(BlockStateProperties.HORIZONTAL_FACING)
              .toYRot()
              .toInt() + 180) + if (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING) 180 else 0) % 360)
            .build()
        }, BlockStateProperties.WATERLOGGED)
    }
  }

  fun <B : Block> fallingCoconutBlock(name: String): NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> {
    return NonNullBiConsumer { c, p ->
      val baseModel = p.models()
        .withExistingParent(c.name, p.modLoc("block/coconut_block"))
        .texture("texture", p.modLoc("block/$name"))
        .texture("particle", p.modLoc("block/$name"))

      p.getVariantBuilder(c.get())
        .forAllStatesExcept({ state ->
          ConfiguredModel.builder()
            .modelFile(baseModel)
            .rotationY((state.getValue(FallingCoconutBlock.FACING).toYRot().toInt() + 180) % 360)
            .build()
        })
    }
  }


  // ----
}