package com.dannbrown.braziliandelight.content.presets

import com.dannbrown.braziliandelight.content.blocks.*
import com.dannbrown.braziliandelight.init.ModContent
import com.dannbrown.deltaboxlib.registrate.datagen.model.RegistrateModelTemplates
import com.dannbrown.deltaboxlib.registrate.datagen.model.RegistrateTextureSlots
import com.dannbrown.deltaboxlib.registrate.types.BlockstateFactory
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.core.Direction
import net.minecraft.core.Direction.Axis
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.blockstates.Condition
import net.minecraft.data.models.blockstates.MultiPartGenerator
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.WallSide

object BlockstatePresets {
  val PARTS = TextureSlot.create("parts")
  val HOLLOW = TextureSlot.create("hollow")
  val INNER = TextureSlot.create("inner")
  val TRAY_TOP = TextureSlot.create("tray_top")
  val TRAY_BOTTOM = TextureSlot.create("tray_bottom")
  val HEAVY_POT =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_BACK =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_back"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_FRONT =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_front"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_INVERTED =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_inverted"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  fun potBlock(textureName: String): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to HEAVY_POT,
        1 to HEAVY_POT_BACK,
        2 to HEAVY_POT_INVERTED,
        3 to HEAVY_POT_FRONT
      )

      val models = modelVariants.mapValues { (mix, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(ModContent.MOD_ID, "block/${baseName}_${mix}"),
          TextureMapping()
            .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "cooking_pot_bottom", "", "block/"))
            .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "cooking_pot_side", "", "block/"))
            .put(TextureSlot.TOP, g.optionalTexture(b.get(), "cooking_pot_top", "", "block/"))
            .put(PARTS, g.optionalTexture(b.get(), "cooking_pot_parts", "", "block/"))
            .put(HOLLOW, g.optionalTexture(b.get(), "cooking_pot_hollow", "", "block/"))
            .put(TextureSlot.INSIDE, g.optionalTexture(b.get(), "${textureName}_still", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${textureName}_still", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.properties(MilkPotBlock.FACING, MilkPotBlock.MIXES)
      val rotations = mapOf(
        Direction.NORTH to Rotation.R0,
        Direction.EAST to Rotation.R90,
        Direction.SOUTH to Rotation.R180,
        Direction.WEST to Rotation.R270
      )

      for ((mix, model) in models) {
        for ((facing, yRot) in rotations) {
          val variant = Variant.variant()
            .with(VariantProperties.MODEL, model)
            .let {
              it.with(
                VariantProperties.Y_ROT,
                Rotation.values().first { r -> r == yRot })
            }
          stateGen.select(
            facing, mix,
            variant
          )
        }
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(
          stateGen
        )
      )
    }
  }

  val PIE =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pie"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  val PIE_SLICE_1 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pie_slice_1"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )


  val PIE_SLICE_2 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pie_slice_2"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )


  val PIE_SLICE_3 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pie_slice_3"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  fun pieBlock(): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to PIE,
        1 to PIE_SLICE_1,
        2 to PIE_SLICE_2,
        3 to PIE_SLICE_3
      )

      val models = modelVariants.mapValues { (slice, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(
            ModContent.MOD_ID,
            "block/${baseName}${if (slice > 0) "_slice_${slice}" else ""}"
          ),
          TextureMapping()
            .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "${baseName}_bottom", "", "block/"))
            .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/"))
            .put(TextureSlot.TOP, g.optionalTexture(b.get(), "${baseName}_top", "", "block/"))
            .put(INNER, g.optionalTexture(b.get(), "${baseName}_inner", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.properties(PieBlock.FACING, PieBlock.BITES)
      val rotations = mapOf(
        Direction.NORTH to Rotation.R0,
        Direction.EAST to Rotation.R90,
        Direction.SOUTH to Rotation.R180,
        Direction.WEST to Rotation.R270
      )

      for ((bites, model) in models) {
        for ((facing, yRot) in rotations) {
          val variant = Variant.variant()
            .with(VariantProperties.MODEL, model)
            .apply { if (yRot != Rotation.R0) with(VariantProperties.Y_ROT, yRot) }
          stateGen.select(facing, bites, variant)
        }
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }

  val PUDDING =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pudding_base"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  val PUDDING_SLICE_1 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pudding_base_slice_1"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  val PUDDING_SLICE_2 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pudding_base_slice_2"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  val PUDDING_SLICE_3 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pudding_base_slice_3"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  val PUDDING_SLICE_4 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/pudding_base_slice_4"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      INNER,
      TextureSlot.PARTICLE
    )

  fun puddingBlock(): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to PUDDING,
        1 to PUDDING_SLICE_1,
        2 to PUDDING_SLICE_2,
        3 to PUDDING_SLICE_3,
        4 to PUDDING_SLICE_4,
      )

      val models = modelVariants.mapValues { (slice, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(
            ModContent.MOD_ID,
            "block/${baseName}${if (slice > 0) "_slice_${slice}" else ""}"
          ),
          TextureMapping()
            .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "${baseName}_bottom", "", "block/"))
            .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/"))
            .put(TextureSlot.TOP, g.optionalTexture(b.get(), "${baseName}_top", "", "block/"))
            .put(INNER, g.optionalTexture(b.get(), "${baseName}_inner", "", "block/"))
            .put(TRAY_BOTTOM, g.optionalTexture(b.get(), "tray_bottom", "", "block/"))
            .put(TRAY_TOP, g.optionalTexture(b.get(), "tray_top", "", "block/"))
            .put(INNER, g.optionalTexture(b.get(), "${baseName}_inner", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.properties(PlaceableFoodBlock.FACING, PlaceableFoodBlock.USES)
      val rotations = mapOf(
        Direction.NORTH to Rotation.R0,
        Direction.EAST to Rotation.R90,
        Direction.SOUTH to Rotation.R180,
        Direction.WEST to Rotation.R270
      )

      for ((bites, model) in models) {
        for ((facing, yRot) in rotations) {
          val variant = Variant.variant()
            .with(VariantProperties.MODEL, model)
            .apply { if (yRot != Rotation.R0) with(VariantProperties.Y_ROT, yRot) }
          stateGen.select(facing, bites, variant)
        }
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }

  val APPLE_TRAY =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_1 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_1"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_2 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_2"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_3 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_3"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_4 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_4"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_5 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_5"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  val APPLE_TRAY_PART_6 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/apple_tray_part_6"
      ),
      TRAY_TOP,
      TRAY_BOTTOM,
      PARTS,
      TextureSlot.PARTICLE
    )

  fun loveAppleTrayBlock(): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to APPLE_TRAY,
        1 to APPLE_TRAY_PART_1,
        2 to APPLE_TRAY_PART_2,
        3 to APPLE_TRAY_PART_3,
        4 to APPLE_TRAY_PART_4,
        5 to APPLE_TRAY_PART_5,
        6 to APPLE_TRAY_PART_6
      )

      val models = modelVariants.mapValues { (slice, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(
            ModContent.MOD_ID,
            "block/${baseName}${if (slice > 0) "_part_${slice}" else ""}"
          ),
          TextureMapping()
            .put(PARTS, g.optionalTexture(b.get(), "${baseName}_parts", "", "block/"))
            .put(TRAY_BOTTOM, g.optionalTexture(b.get(), "tray_bottom", "", "block/"))
            .put(TRAY_TOP, g.optionalTexture(b.get(), "tray_top", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${baseName}_parts", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.properties(PlaceableFoodBlock.FACING, LoveAppleTrayBlock.PARTS)
      val rotations = mapOf(
        Direction.NORTH to Rotation.R0,
        Direction.EAST to Rotation.R90,
        Direction.SOUTH to Rotation.R180,
        Direction.WEST to Rotation.R270
      )

      for ((bites, model) in models) {
        for ((facing, yRot) in rotations) {
          val variant = Variant.variant()
            .with(VariantProperties.MODEL, model)
            .apply { if (yRot != Rotation.R0) with(VariantProperties.Y_ROT, yRot) }
          stateGen.select(facing, bites, variant)
        }
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }

  val HEAVY_POT_LEVEL_1 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_level_1"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_LEVEL_2 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_level_2"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_LEVEL_3 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_level_3"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )

  val HEAVY_POT_LEVEL_4 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        ModContent.MOD_ID, "block/heavy_pot_level_4"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      PARTS,
      HOLLOW,
      TextureSlot.PARTICLE
    )


  fun heavyPotBlock(): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to HEAVY_POT,
        1 to HEAVY_POT_LEVEL_1,
        2 to HEAVY_POT_LEVEL_2,
        3 to HEAVY_POT_LEVEL_3,
        4 to HEAVY_POT_LEVEL_4
      )

      val models = modelVariants.mapValues { (slice, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(
            ModContent.MOD_ID,
            "block/${baseName}${if (slice > 0) "_level_${slice}" else ""}"
          ),
          TextureMapping()
            .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "cooking_pot_bottom", "", "block/"))
            .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "cooking_pot_side", "", "block/"))
            .put(TextureSlot.TOP, g.optionalTexture(b.get(), "cooking_pot_top", "", "block/"))
            .put(PARTS, g.optionalTexture(b.get(), "cooking_pot_parts", "", "block/"))
            .put(HOLLOW, g.optionalTexture(b.get(), "cooking_pot_hollow", "", "block/"))
            .put(TextureSlot.INSIDE, g.optionalTexture(b.get(), "${baseName}_inside", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${baseName}_inside", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.properties(PlaceableFoodBlock.FACING, PlaceableFoodBlock.USES)
      val rotations = mapOf(
        Direction.NORTH to Rotation.R0,
        Direction.EAST to Rotation.R90,
        Direction.SOUTH to Rotation.R180,
        Direction.WEST to Rotation.R270
      )

      for ((bites, model) in models) {
        for ((facing, yRot) in rotations) {
          val variant = Variant.variant()
            .with(VariantProperties.MODEL, model)
            .apply { if (yRot != Rotation.R0) with(VariantProperties.Y_ROT, yRot) }
          stateGen.select(facing, bites, variant)
        }
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }

  val CAKE =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_1 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice1"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_2 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice2"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_3 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice3"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_4 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice4"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_5 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice5"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  val CAKE_SLICE_6 =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/cake_slice6"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  fun cakeBlock(): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())
      val modelVariants = mapOf(
        0 to CAKE,
        1 to CAKE_SLICE_1,
        2 to CAKE_SLICE_2,
        3 to CAKE_SLICE_3,
        4 to CAKE_SLICE_4,
        5 to CAKE_SLICE_5,
        6 to CAKE_SLICE_6,
      )

      val models = modelVariants.mapValues { (slice, template) ->
        template.create(
          DeltaboxUtil.resourceLocation(
            ModContent.MOD_ID,
            "block/${baseName}${if (slice > 0) "_level_${slice}" else ""}"
          ),
          TextureMapping()
            .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "${baseName}_bottom", "", "block/"))
            .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/"))
            .put(TextureSlot.TOP, g.optionalTexture(b.get(), "${baseName}_top", "", "block/"))
            .put(TextureSlot.INSIDE, g.optionalTexture(b.get(), "${baseName}_inside", "", "block/"))
            .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${baseName}_side", "", "block/")),
          g.modelOutput
        )
      }

      val stateGen = PropertyDispatch.property(CakeBlock.BITES)

      for ((bites, model) in models) {
        val variant = Variant.variant()
          .with(VariantProperties.MODEL, model)
        stateGen.select(bites, variant)
      }

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }

  val CAKE_CANDLE =
    RegistrateModelTemplates.create(
      DeltaboxUtil.resourceLocation(
        "minecraft", "block/template_cake_with_candle"
      ),
      TextureSlot.BOTTOM,
      TextureSlot.SIDE,
      TextureSlot.TOP,
      TextureSlot.CANDLE,
      TextureSlot.INSIDE,
      TextureSlot.PARTICLE
    )

  fun cakeCandleBlock(blockName: String, candleColor: String): BlockstateFactory {
    return { g, b ->
      val baseName = DeltaboxUtil.getBlockId(b.get())

      val model = CAKE_CANDLE.create(
        DeltaboxUtil.resourceLocation(ModContent.MOD_ID, "block/${baseName}"),
        TextureMapping()
          .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "${blockName}_bottom", "", "block/"))
          .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "${blockName}_side", "", "block/"))
          .put(TextureSlot.TOP, g.optionalTexture(b.get(), "${blockName}_top", "", "block/"))
          .put(
            TextureSlot.CANDLE,
            DeltaboxUtil.resourceLocation(
              "minecraft",
              "block/${if (candleColor != "") "${candleColor}_" else ""}candle"
            )
          )
          .put(TextureSlot.INSIDE, g.optionalTexture(b.get(), "${blockName}_inside", "", "block/"))
          .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${blockName}_side", "", "block/")),
        g.modelOutput
      )

      val model_lit = CAKE_CANDLE.create(
        DeltaboxUtil.resourceLocation(ModContent.MOD_ID, "block/${baseName}_lit"),
        TextureMapping()
          .put(TextureSlot.BOTTOM, g.optionalTexture(b.get(), "${blockName}_bottom", "", "block/"))
          .put(TextureSlot.SIDE, g.optionalTexture(b.get(), "${blockName}_side", "", "block/"))
          .put(TextureSlot.TOP, g.optionalTexture(b.get(), "${blockName}_top", "", "block/"))
          .put(
            TextureSlot.CANDLE,
            DeltaboxUtil.resourceLocation(
              "minecraft",
              "block/${if (candleColor != "") "${candleColor}_" else ""}candle_lit"
            )
          )
          .put(TextureSlot.INSIDE, g.optionalTexture(b.get(), "${blockName}_inside", "", "block/"))
          .put(TextureSlot.PARTICLE, g.optionalTexture(b.get(), "${blockName}_side", "", "block/")),
        g.modelOutput
      )

      val stateGen = PropertyDispatch.property(CandleCakeBlock.LIT)
        .select(true, Variant.variant().with(VariantProperties.MODEL, model_lit))
        .select(false, Variant.variant().with(VariantProperties.MODEL, model))

      g.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(b.get()).with(stateGen)
      )
    }
  }
}