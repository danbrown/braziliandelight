package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.BuddingDoubleCropBlock
import com.dannbrown.braziliandelight.content.block.BuddingVineCropBlock
import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.CustomCandleCakeBlock
import com.dannbrown.braziliandelight.content.block.DoubleCropBlock
import com.dannbrown.braziliandelight.content.block.HeavyCreamPotBlock
import com.dannbrown.braziliandelight.content.block.LoveAppleTrayBlock
import com.dannbrown.braziliandelight.content.block.MilkPotBlock
import com.dannbrown.braziliandelight.content.block.MinasCheesePot
import com.dannbrown.braziliandelight.content.block.NormalCropBlock
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.content.block.VineCropBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockLootPresets
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.databoxlib.content.block.GenericDoublePlantBlock
import com.dannbrown.databoxlib.content.block.GenericTallGrassBlock
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
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.block.BuddingBushBlock
import vectorwing.farmersdelight.common.block.BuddingTomatoBlock
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.block.TomatoVineBlock
import vectorwing.farmersdelight.common.registry.ModBlocks
import vectorwing.farmersdelight.common.registry.ModItems
import java.util.function.Supplier

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  // VIRTUAL
  val MILK_POT = BLOCKS.create<MilkPotBlock>("milk_pot")
    .properties { p -> p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)}
    .blockFactory { p -> MilkPotBlock(p) }
    .blockstate(CustomBlockstatePresets.potBlock("milk"))
    .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
    .noItem()
    .register()

  val HEAVY_CREAM_POT = BLOCKS.create<HeavyCreamPotBlock>("heavy_cream_pot")
    .properties { p -> p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)}
    .blockFactory { p -> HeavyCreamPotBlock(p) }
    .blockstate(CustomBlockstatePresets.potBlock("heavy_cream"))
    .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
    .noItem()
    .register()

  val MINAS_CHEESE_POT = BLOCKS.create<MinasCheesePot>("minas_cheese_pot")
    .properties { p -> p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)}
    .blockFactory { p -> MinasCheesePot(p) }
    .blockstate(CustomBlockstatePresets.potBlock("minas_cheese"))
    .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
    .noItem()
    .register()

  // CRATES & BAGS
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

  // PLACEABLE FOODS
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

  // CROPS
  val BEANS_CROP: BlockEntry<VineCropBlock> = createVineCropBlock(AddonNames.BEAN, MapColor.TERRACOTTA_LIGHT_GRAY, { AddonItems.BEAN_POD.get() }, { AddonItems.BEAN_POD.get() }, { BUDDING_BEANS_CROP.get() })
  val BUDDING_BEANS_CROP: BlockEntry<BuddingVineCropBlock> = createBuddingVineCropBlock(AddonNames.BEAN, MapColor.TERRACOTTA_LIGHT_GRAY, { BEANS_CROP.get() }, { AddonItems.BEAN_POD.get() })

  val COLLARD_GREENS_CROP: BlockEntry<NormalCropBlock> = createNormalCropBlock(AddonNames.COLLARD_GREENS, MapColor.TERRACOTTA_GREEN, { AddonItems.COLLARD_GREENS.get() }, { AddonItems.COLLARD_GREENS_SEED.get() })
  val GARLIC_CROP: BlockEntry<NormalCropBlock> = createNormalCropBlock(AddonNames.GARLIC, MapColor.TERRACOTTA_WHITE, { AddonItems.GARLIC_BULB.get() }, { AddonItems.GARLIC_CLOVE.get() }, false)


  val TALL_SPARSE_DRY_GRASS: BlockEntry<GenericDoublePlantBlock> = createDoubleTallGrassBlock("sparse_dry_grass", MapColor.TERRACOTTA_YELLOW, { Items.BEETROOT_SEEDS} )
  val SPARSE_DRY_GRASS: BlockEntry<GenericTallGrassBlock> = createTallGrassBlock("sparse_dry_grass", MapColor.TERRACOTTA_YELLOW, { TALL_SPARSE_DRY_GRASS.get() }, { Items.BEETROOT_SEEDS })

  val TALL_COFFEE: BlockEntry<DoubleCropBlock> = createDoubleCropBlock("coffee", MapColor.TERRACOTTA_RED, true, { AddonItems.COFFEE_BERRIES.get() }, null, 0.5f, 3)
  val BUDDING_COFFEE: BlockEntry<BuddingDoubleCropBlock> = createBuddingDoubleCropBlock("coffee", MapColor.TERRACOTTA_GREEN, { TALL_COFFEE.get() }, { AddonItems.COFFEE_BERRIES.get() })

  val TALL_CORN: BlockEntry<DoubleCropBlock> = createDoubleCropBlock("corn", MapColor.COLOR_YELLOW, true, { AddonItems.CORN.get() }, null, 0.5f, 3)
  val BUDDING_CORN: BlockEntry<BuddingDoubleCropBlock> = createBuddingDoubleCropBlock("corn", MapColor.TERRACOTTA_GREEN, { TALL_CORN.get() }, { AddonItems.CORN_GRAINS.get() })

  val TALL_GUARANA: BlockEntry<DoubleCropBlock> = createDoubleCropBlock("guarana", MapColor.COLOR_RED, true, { AddonItems.GUARANA_FRUIT.get() }, null, 0.25f, 4)
  val BUDDING_GUARANA: BlockEntry<BuddingDoubleCropBlock> = createBuddingDoubleCropBlock("guarana", MapColor.TERRACOTTA_GREEN, { TALL_GUARANA.get() }, { AddonItems.GUARANA_SEEDS.get() })

  val TALL_CASSAVA: BlockEntry<DoubleCropBlock> = createDoubleCropBlock("cassava", MapColor.TERRACOTTA_BROWN, false, { AddonItems.CASSAVA_ROOT.get() }, null, 0.75f, 3)
  val BUDDING_CASSAVA: BlockEntry<BuddingDoubleCropBlock> = createBuddingDoubleCropBlock("cassava", MapColor.TERRACOTTA_GREEN, { TALL_CASSAVA.get() }, { AddonItems.CASSAVA_ROOT.get() })

//    BLOCKS.create<GenericDoublePlantBlock>("tall_" + "sparse_dry_grass").blockFactory { p ->
//    GenericDoublePlantBlock(p) { b, _, _ ->
//      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT)
//    }
//  }
//    .copyFrom { Blocks.TALL_GRASS }
//    .color(MapColor.TERRACOTTA_YELLOW)
//    .properties { p ->
//      p.strength(0.0f)
//        .randomTicks()
//        .noCollission()
//        .noOcclusion()
//    }
//    .loot(BlockLootPresets.dropDoubleCropLoot({ Items.BEETROOT_SEEDS }, { Items.BEETROOT_SEEDS }))
//    .transform { t ->
//      t.blockstate { c, p ->
//        p.getVariantBuilder(c.get())
//          .partialState()
//          .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
//          .setModels(*ConfiguredModel.builder()
//            .modelFile(p.models()
//              .withExistingParent(c.name + "_top", p.mcLoc("block/cross"))
//              .texture("cross", p.modLoc("block/sparse_dry_grass/" + "sparse_dry_grass" + "_top"))
//              .texture("particle", p.modLoc("block/sparse_dry_grass/" + "sparse_dry_grass" + "_top"))
//              .renderType("cutout_mipped"))
//            .build())
//          .partialState()
//          .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
//          .setModels(*ConfiguredModel.builder()
//            .modelFile(p.models()
//              .withExistingParent(c.name + "_bottom", p.mcLoc("block/cross"))
//              .texture("cross", p.modLoc("block/sparse_dry_grass/" + "sparse_dry_grass" + "_bottom"))
//              .texture("particle", p.modLoc("block/sparse_dry_grass/" + "sparse_dry_grass" + "_bottom"))
//              .renderType("cutout_mipped"))
//            .build())
//      }
//        .item()
//        .model { c, p ->
//          p.withExistingParent(c.name, p.mcLoc("item/generated"))
//            .texture("layer0", p.modLoc("block/sparse_dry_grass/sparse_dry_grass_top" ))
//        }
//        .build()
//    }
//    .register()


//    BLOCKS.create<GenericTallGrassBlock>("sparse_dry_grass").blockFactory { p ->
//    GenericTallGrassBlock({ TALL_SPARSE_DRY_GRASS.get() }, p) { b, _, _ ->
//      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT)
//    }
//  }
//    .copyFrom { Blocks.TALL_GRASS }
//    .color(MapColor.TERRACOTTA_YELLOW)
//    .properties { p ->
//      p.strength(0.0f)
//        .randomTicks()
//        .noCollission()
//        .noOcclusion()
//    }
//    .transform { t ->
//      t.blockstate{ c, p ->
//        p.getVariantBuilder(c.get())
//          .partialState()
//          .setModels(*ConfiguredModel.builder()
//            .modelFile(p.models()
//              .withExistingParent(c.name, p.mcLoc("block/cross"))
//              .texture("cross", p.modLoc("block/sparse_dry_grass/sparse_dry_grass"))
//              .texture("particle", p.modLoc("block/sparse_dry_grass/sparse_dry_grass"))
//              .renderType("cutout_mipped"))
//            .build()
//          )
//      }
//        .item()
//        .model { c, p ->
//          p.withExistingParent(c.name, p.mcLoc("item/generated"))
//            .texture("layer0", p.modLoc("block/sparse_dry_grass/sparse_dry_grass"))
//        }
//        .build()
//    }
//    .loot(BlockLootPresets.dropCropLoot({ Items.BEETROOT_SEEDS }, null, 0.5f, 3))
//    .register()


  // Create TallGrass-like Blocks
  fun createTallGrassBlock(
    _name: String,
    color: MapColor,
    doubleBlock: Supplier<GenericDoublePlantBlock>,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 0.5f,
    multiplier: Int = 3,
    placeOn: ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? = null
  ): BlockEntry<GenericTallGrassBlock> {
    return BLOCKS.create<GenericTallGrassBlock>(_name).blockFactory { p ->
      GenericTallGrassBlock(doubleBlock, p, placeOn)
    }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .transform { t ->
        t.blockstate{ c, p ->
          p.getVariantBuilder(c.get())
            .partialState()
            .setModels(*ConfiguredModel.builder()
              .modelFile(p.models()
                .withExistingParent(c.name, p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/${_name}"))
                .texture("particle", p.modLoc("block/${_name}/${_name}"))
                .renderType("cutout_mipped"))
              .build()
            )
        }
          .item()
          .model { c, p ->
            p.withExistingParent(c.name, p.mcLoc("item/generated"))
              .texture("layer0", p.modLoc("block/${_name}/${_name}"))
          }
          .build()
      }
      .loot(BlockLootPresets.dropCropLoot(dropItem, seedItem, chance, multiplier))
      .register()
  }

  fun createDoubleTallGrassBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 0.25f,
    multiplier: Float = 2f,
    placeOn: ((blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos) -> Boolean)? = null
  ): BlockEntry<GenericDoublePlantBlock> {
    return BLOCKS.create<GenericDoublePlantBlock>("tall_$_name")
      .blockFactory { p -> GenericDoublePlantBlock(p, placeOn)
    }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .loot(BlockLootPresets.dropDoubleCropLoot(dropItem, seedItem?: dropItem, chance, multiplier))
      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get())
            .partialState()
            .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
            .setModels(*ConfiguredModel.builder()
              .modelFile(p.models()
                .withExistingParent(c.name + "_top", p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/${_name}_top"))
                .texture("particle", p.modLoc("block/${_name}/${_name}_top"))
                .renderType("cutout_mipped"))
              .build())
            .partialState()
            .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
            .setModels(*ConfiguredModel.builder()
              .modelFile(p.models()
                .withExistingParent(c.name + "_bottom", p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/${_name}_bottom"))
                .texture("particle", p.modLoc("block/${_name}/${_name}_bottom"))
                .renderType("cutout_mipped"))
              .build())
        }
          .item()
          .model { c, p ->
            p.withExistingParent(c.name, p.mcLoc("item/generated"))
              .texture("layer0", p.modLoc("block/${_name}/${_name}_top"))
          }
          .build()
      }
      .register()
  }

  // Create Double Crops
  fun createBuddingDoubleCropBlock(
    _name: String,
    color: MapColor,
    doubleBlock: Supplier<DoubleCropBlock>,
    seedItem: Supplier<Item>
  ): BlockEntry<BuddingDoubleCropBlock> {
    return BLOCKS.create<BuddingDoubleCropBlock>("budding_${_name}")
      .blockFactory { p -> BuddingDoubleCropBlock(p, doubleBlock, seedItem) }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .transform { t ->
        t.blockstate{ c, p ->
          p.getVariantBuilder(c.get())
            .forAllStates { state ->
              val age: Int = state.getValue(BuddingDoubleCropBlock.AGE)
              val maxAge = BuddingDoubleCropBlock.MAX_AGE
              val suffix = if (maxAge == age) "_budding_stage${maxAge - 1}" else "_budding_stage$age"
              ConfiguredModel.builder()
                .modelFile(
                  p.models()
                    .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                    .texture("cross", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .renderType("cutout_mipped")
                )
                .build()
            }
        }
      }
      .loot(BlockLootPresets.noLoot())
      .noItem()
      .register()
  }

  fun createDoubleCropBlock(
    _name: String,
    color: MapColor,
    isBush: Boolean = false,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 1f,
    multiplier: Int = 1
  ): BlockEntry<DoubleCropBlock> {
    return BLOCKS.create<DoubleCropBlock>("tall_$_name")
      .blockFactory { p -> DoubleCropBlock(p, isBush, dropItem, seedItem, chance, multiplier)
      }
      .copyFrom { Blocks.TALL_GRASS }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }

      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get())
            .forAllStates { state ->
              val age: Int = state.getValue(DoubleCropBlock.AGE)
              val isUpper = state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER
              val suffix = if (isUpper) "_top_stage$age" else "_bottom_stage$age"
              ConfiguredModel.builder()
                .modelFile(
                  p.models()
                    .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                    .texture("cross", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .renderType("cutout_mipped")
                )
                .build()
            }
        }
      }
      .loot(CustomBlockLootPresets.dropDoubleCropLoot(dropItem, null, chance, multiplier))
      .noItem()
      .register()
  }


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

  // CROPS

  fun createBuddingVineCropBlock(_name: String, color: MapColor, cropBlock: Supplier<VineCropBlock>, seedItem: Supplier<Item>): BlockEntry<BuddingVineCropBlock>{
    return BLOCKS.create<BuddingVineCropBlock>("budding_${_name}")
      .blockFactory { p -> BuddingVineCropBlock(p, cropBlock, seedItem) }
      .copyFrom { Blocks.WHEAT }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.CROP)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .blockstate { c, p ->
        p.getVariantBuilder(c.get())
          .forAllStates { state ->
            val isOverStaged = state.getValue(BuddingBushBlock.AGE) == 4
            val suffix = if (isOverStaged) "_stage3" else "_stage" + state.getValue(BuddingTomatoBlock.AGE)
            ConfiguredModel.builder()
              .modelFile(p.models()
                .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/budding_" + _name + suffix))
                .texture("particle", p.modLoc("block/${_name}/budding_" + _name + suffix))
                .renderType("cutout")
              )
              .build()
          }
      }
      .loot(BlockLootPresets.noLoot())
      .noItem()
      .register()
  }

  fun createVineCropBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>,
    cropBlock: Supplier<BuddingVineCropBlock>
  ): BlockEntry<VineCropBlock>{
    return BLOCKS.create<VineCropBlock>("${_name}_vine")
      .blockFactory { p -> VineCropBlock(p, dropItem, seedItem, cropBlock) }
      .copyFrom { Blocks.WHEAT }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.CROP)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .blockstate { c, p ->
        p.getVariantBuilder(c.get())
          .forAllStates { state ->
            val isRopelogged = state.getValue(TomatoVineBlock.ROPELOGGED)
            val vineSuffix = "_vine_stage" + state.getValue(TomatoVineBlock.VINE_AGE)
            val suffix = "_stage" + state.getValue(TomatoVineBlock.VINE_AGE)

            val cropModel = p.models()
              .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
              .texture("cross", p.modLoc("block/${_name}/" + _name + suffix))
              .texture("particle", p.modLoc("block/${_name}/" + _name + suffix))
              .renderType("cutout")

            val ropeCropModel = p.models()
              .withExistingParent(c.name + vineSuffix + "_ropelogged", p.modLoc("block/crop_with_rope"))
              .texture("crop", p.modLoc("block/${_name}/" + _name + vineSuffix))
              .texture("rope_side", p.modLoc("block/${_name}/" + _name + "_coiled_rope"))
              .texture("rope_top", p.modLoc("block/rope_top"))
              .texture("particle", p.modLoc("block/${_name}/" + _name + vineSuffix))
              .renderType("cutout")

            ConfiguredModel.builder()
              .modelFile(if (isRopelogged) ropeCropModel else cropModel)
              .build()
          }
      }
      .loot(BlockLootPresets.dropCropLoot(dropItem, null, 0.5f, 3, 3))
      .noItem()
      .register()
  }

  fun createNormalCropBlock(_name: String, color: MapColor, dropItem: Supplier<Item>, seedItem: Supplier<Item>?, includeSeedOnDrop: Boolean = true, chance: Float = 1f, multiplier: Int = 1): BlockEntry<NormalCropBlock>{
    return BLOCKS.create<NormalCropBlock>("${_name}_crop")
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .blockFactory { p-> NormalCropBlock(p, seedItem ?: dropItem) }
      .blockstate { c, p ->
        p.getVariantBuilder(c.get())
          .forAllStates { state ->
            val suffix = "_stage" + state.getValue(CropBlock.AGE)
            ConfiguredModel.builder()
              .modelFile(p.models()
                .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/" + _name + suffix))
                .texture("particle", p.modLoc("block/${_name}/" + _name + suffix))
                .renderType("cutout")
              )
              .build()
          }
      }
      .loot(BlockLootPresets.dropCropLoot(dropItem, if (includeSeedOnDrop) seedItem else null, chance, multiplier))
      .noItem()
      .register()

  }


}