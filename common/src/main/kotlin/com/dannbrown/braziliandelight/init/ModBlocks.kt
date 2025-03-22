package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.content.blocks.*
import com.dannbrown.braziliandelight.content.presets.BlockstatePresets
import com.dannbrown.braziliandelight.content.presets.FeastBuilderPresets
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.registry.BlockEntry
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

object ModBlocks {
  // VIRTUAL
  val MILK_POT =
    REGISTRATE.block<MilkPotBlock>("milk_pot")
      .properties { c, p ->
        p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
      }
      .factory { c, p -> MilkPotBlock(p) }
      .blockstate(BlockstatePresets.potBlock("milk"))
      .noItem()
      .loot { g, b -> g.dropOther(b.get(), FarmersCompat.getCookingPot().asItem()) }
      .register()

  val HEAVY_CREAM_POT =
    REGISTRATE.block<HeavyCreamPotBlock>("heavy_cream_pot")
      .properties { c, p: BlockBehaviour.Properties ->
        p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
      }
      .factory { c, p -> HeavyCreamPotBlock(p) }
      .blockstate(BlockstatePresets.potBlock("heavy_cream"))
      .noItem()
      .loot { g, b -> g.dropOther(b.get(), FarmersCompat.getCookingPot().asItem()) }
      .register()

  val MINAS_CHEESE_POT =
    REGISTRATE.block<MinasCheesePot>("minas_cheese_pot")
      .properties { c, p ->
        p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
      }
      .factory { c, p -> MinasCheesePot(p) }
      .blockstate(BlockstatePresets.potBlock("minas_cheese"))
      .noItem()
      .loot { g, b -> g.dropOther(b.get(), FarmersCompat.getCookingPot().asItem()) }
      .register()


  // CRATES
//  val BEAN_POD_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.BEAN_POD,
//    MapColor.COLOR_LIGHT_GREEN,
//    { ModItems.BEAN_POD.get() },
//    { DataIngredient.tag(AddonTags.ITEM.BEAN_PODS) })
//  val GARLIC_BULB_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.GARLIC_BULB,
//    MapColor.TERRACOTTA_WHITE,
//    { ModItems.GARLIC_BULB.get() },
//    { DataIngredient.tag(AddonTags.ITEM.GARLIC) })
//  val ACAI_BERRIES_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.ACAI_BERRIES,
//    MapColor.COLOR_PURPLE,
//    { BUDDING_ACAI_BRANCH.get() },
//    { DataIngredient.tag(AddonTags.ITEM.ACAI) })
//  val GUARANA_FRUIT_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.GUARANA_FRUIT,
//    MapColor.COLOR_RED,
//    { ModItems.GUARANA_FRUIT.get() },
//    { DataIngredient.tag(AddonTags.ITEM.GUARANA) })
//  val GREEN_COCONUT_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.GREEN_COCONUT,
//    MapColor.COLOR_GREEN,
//    { GREEN_COCONUT.get() },
//    { DataIngredient.items(GREEN_COCONUT.get()) })
//  val COCONUT_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.COCONUT,
//    MapColor.COLOR_BROWN,
//    { COCONUT.get() },
//    { DataIngredient.tag(AddonTags.ITEM.COCONUT) })
//  val CORN_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.CORN,
//    MapColor.COLOR_YELLOW,
//    { ModItems.CORN.get() },
//    { DataIngredient.tag(AddonTags.ITEM.CORN) })
//  val CASSAVA_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.CASSAVA,
//    MapColor.COLOR_BROWN,
//    { BUDDING_CASSAVA.get() },
//    { DataIngredient.tag(AddonTags.ITEM.CASSAVA) })
//  val COLLARD_GREENS_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.COLLARD_GREENS,
//    MapColor.COLOR_GREEN,
//    { ModItems.COLLARD_GREENS.get() },
//    { DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS) })
//  val COFFEE_BERRIES_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.COFFEE_BERRIES,
//    MapColor.COLOR_BROWN,
//    { ModItems.COFFEE_BERRIES.get() },
//    { DataIngredient.items(ModItems.COFFEE_BERRIES.get()) })
//  val LEMON_CRATE = CrateBuilderPresets.createCrateBlock(
//    ModNames.LEMON,
//    MapColor.COLOR_YELLOW,
//    { ModItems.LEMON.get() },
//    { DataIngredient.items(ModItems.LEMON.get()) })
//
//  // BAGS
//  val BLACK_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
//    ModNames.BLACK_BEANS,
//    MapColor.COLOR_BLACK,
//    { BUDDING_BEANS_CROP.get() },
//    { DataIngredient.items(BUDDING_BEANS_CROP.get()) })
//  val CARIOCA_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
//    ModNames.CARIOCA_BEANS,
//    MapColor.TERRACOTTA_ORANGE,
//    { CARIOCA_BEANS_CROP.get() },
//    { DataIngredient.items(CARIOCA_BEANS_CROP.get()) })
//  val COFFEE_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
//    ModNames.COFFEE_BEANS,
//    MapColor.COLOR_BROWN,
//    { ModItems.COFFEE_BEANS.get() },
//    { DataIngredient.tag(AddonTags.ITEM.COFFEE_BEANS) })
//
//  // PLACEABLE FOODS
  val CARROT_CAKE_CANDLE_COLORS = FeastBuilderPresets.createCandleCakes(ModNames.CARROT_CAKE) { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CustomCakeBlock> = FeastBuilderPresets.createCakeBlock(
    ModNames.CARROT_CAKE,
    MapColor.COLOR_ORANGE,
    { ModItems.CARROT_CAKE_SLICE.get() },
    CARROT_CAKE_CANDLE_COLORS
  )
  val CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS =
    FeastBuilderPresets.createCandleCakes(ModNames.CARROT_CAKE_WITH_CHOCOLATE) { CARROT_CAKE_WITH_CHOCOLATE.get() }
  val CARROT_CAKE_WITH_CHOCOLATE: BlockEntry<CustomCakeBlock> = FeastBuilderPresets.createCakeBlock(
    ModNames.CARROT_CAKE_WITH_CHOCOLATE,
    MapColor.COLOR_ORANGE,
    { ModItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() },
    CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS
  )

  val MINAS_CHEESE: BlockEntry<PieBlock> = FeastBuilderPresets.createCheeseBlock(
    ModNames.MINAS_CHEESE,
    MapColor.TERRACOTTA_WHITE
  ) { ModItems.MINAS_CHEESE_SLICE.get() }
  val CHICKEN_POT_PIE: BlockEntry<PieBlock> = FeastBuilderPresets.createPieBlock(
    ModNames.CHICKEN_POT_PIE,
    MapColor.SAND
  ) { ModItems.CHICKEN_POT_PIE_SLICE.get() }
  val PUDDING: BlockEntry<PlaceableFoodBlock> =
    FeastBuilderPresets.createPuddingBlock(ModNames.PUDDING, MapColor.COLOR_BROWN) { ModItems.PUDDING_SLICE.get() }

  val FEIJOADA_POT: BlockEntry<PotPlaceableFoodBlock> = FeastBuilderPresets.createPotBlock(
    ModNames.FEIJOADA_POT,
    MapColor.COLOR_BLACK
  ) { ModItems.PLATE_OF_FEIJOADA.get() }
  val GREEN_SOUP_POT: BlockEntry<PotPlaceableFoodBlock> = FeastBuilderPresets.createPotBlock(
    ModNames.GREEN_SOUP_POT,
    MapColor.COLOR_GREEN
  ) { ModItems.PLATE_OF_GREEN_SOUP.get() }
  val FISH_MOQUECA_POT: BlockEntry<PotPlaceableFoodBlock> = FeastBuilderPresets.createPotBlock(
    ModNames.FISH_MOQUECA_POT,
    MapColor.COLOR_ORANGE
  ) { ModItems.PLATE_OF_FISH_MOQUECA.get() }
  val STROGANOFF_POT: BlockEntry<PotPlaceableFoodBlock> = FeastBuilderPresets.createPotBlock(
    ModNames.STROGANOFF_POT,
    MapColor.COLOR_RED
  ) { ModItems.PLATE_OF_STROGANOFF.get() }
  val SWEET_LOVE_APPLE_TRAY: BlockEntry<LoveAppleTrayBlock> = FeastBuilderPresets.createLoveAppleTrayBlock(
    ModNames.SWEET_LOVE_APPLE_TRAY,
    MapColor.COLOR_RED
  ) { ModItems.SWEET_LOVE_APPLE.get() }
//
//  // GRASS
//  val TALL_SPARSE_DRY_GRASS: BlockEntry<GenericDoublePlantBlock> = GrassBuilderPresets.createDoubleTallGrassBlock(
//    "sparse_dry_grass",
//    MapColor.TERRACOTTA_YELLOW,
//    { Items.BEETROOT_SEEDS })
//  val SPARSE_DRY_GRASS: BlockEntry<GenericTallGrassBlock> = GrassBuilderPresets.createTallGrassBlock(
//    "sparse_dry_grass",
//    MapColor.TERRACOTTA_YELLOW,
//    { TALL_SPARSE_DRY_GRASS.get() },
//    { Items.BEETROOT_SEEDS })
//
//  // BEANS
//  val BEANS_CROP: BlockEntry<VineCropBlock> =
//    CropBuilderPresets.createVineCropBlock(
//      ModNames.BEAN,
//      MapColor.TERRACOTTA_LIGHT_GRAY,
//      { ModItems.BEAN_POD.get() },
//      { ModItems.BEAN_POD.get() },
//      { BUDDING_BEANS_CROP.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER)
//    )
//  val BUDDING_BEANS_CROP: BlockEntry<BuddingVineCropBlock> =
//    CropBuilderPresets.createBuddingVineCropBlock(
//      ModNames.BEAN,
//      ModNames.BLACK_BEANS,
//      "Budding Beans Crop",
//      "Black Beans",
//      MapColor.TERRACOTTA_LIGHT_GREEN,
//      { BEANS_CROP.get() },
//      { ModItems.BEAN_POD.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      listOf(AddonTags.ITEM.BEANS),
//      { p -> ModItems.foodItem(p, AddonFoodValues.BEAN) }
//    )
//  val CARIOCA_BEANS_CROP: BlockEntry<BuddingVineCropBlock> =
//    CropBuilderPresets.createBuddingVineCropBlock(
//      ModNames.BEAN,
//      ModNames.CARIOCA_BEANS,
//      "Budding Beans Crop",
//      "Carioca Beans",
//      MapColor.TERRACOTTA_LIGHT_GREEN,
//      { BEANS_CROP.get() },
//      { ModItems.BEAN_POD.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      listOf(AddonTags.ITEM.BEANS),
//      { p -> ModItems.foodItem(p, AddonFoodValues.BEAN) }
//    )
//
//  // COLLARD GREENS
//  val COLLARD_GREENS_CROP: BlockEntry<NormalCropBlock> =
//    CropBuilderPresets.createNormalCropBlock(
//      ModNames.COLLARD_GREENS,
//      ModNames.COLLARD_GREENS_SEEDS,
//      "Collard Greens Crops",
//      "Collard Greens Seeds",
//      MapColor.TERRACOTTA_GREEN,
//      { ModItems.COLLARD_GREENS.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      listOf(),
//    )
//
//  // GARLIC
//  val GARLIC_CROP: BlockEntry<NormalCropBlock> =
//    CropBuilderPresets.createNormalCropBlock(
//      ModNames.GARLIC,
//      ModNames.GARLIC_CLOVE,
//      "Garlic Crops",
//      "Garlic Clove",
//      MapColor.TERRACOTTA_WHITE,
//      { ModItems.GARLIC_BULB.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_AUTUMN, AddonTags.BLOCK.SERENE_SEASONS_WINTER),
//      listOf(AddonTags.ITEM.GARLIC),
//      false,
//    )
//
//  // COFFEE
//  val TALL_COFFEE: BlockEntry<DoubleCropBlock> =
//    CropBuilderPresets.createDoubleCropBlock(
//      "coffee",
//      MapColor.TERRACOTTA_RED,
//      true,
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      { ModItems.COFFEE_BERRIES.get() },
//      null,
//      0.5f,
//      3
//    )
//  val BUDDING_COFFEE: BlockEntry<BuddingDoubleCropBlock> =
//    CropBuilderPresets.createBuddingDoubleCropBlock(
//      "coffee",
//      ModNames.COFFEE_SEEDS,
//      "Budding Coffee Crop",
//      "Coffee Seeds",
//      MapColor.TERRACOTTA_RED,
//      { TALL_COFFEE.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      listOf(),
//      { p -> p },
//    )
//
//  // CORN
//  val TALL_CORN: BlockEntry<DoubleCropBlock> =
//    CropBuilderPresets.createDoubleCropBlock(
//      "corn",
//      MapColor.COLOR_YELLOW,
//      true,
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      { ModItems.CORN.get() },
//      null,
//      0.5f,
//      3
//    )
//  val BUDDING_CORN: BlockEntry<BuddingDoubleCropBlock> =
//    CropBuilderPresets.createBuddingDoubleCropBlock(
//      "corn",
//      ModNames.KERNELS,
//      "Budding Corn Crop",
//      "Kernels",
//      MapColor.COLOR_YELLOW,
//      { TALL_CORN.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      listOf(AddonTags.ITEM.KERNELS),
//      { p -> p },
//    )
//  val WHITE_KERNELS_CROP: BlockEntry<BuddingDoubleCropBlock> =
//    CropBuilderPresets.createBuddingDoubleCropBlock(
//      "corn",
//      ModNames.WHITE_KERNELS,
//      "Budding Corn Crop",
//      "White Kernels",
//      MapColor.SNOW,
//      { TALL_CORN.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      listOf(AddonTags.ITEM.KERNELS),
//      { p -> p },
//    )
//
//  // GUARANA
//  val TALL_GUARANA: BlockEntry<DoubleCropBlock> =
//    CropBuilderPresets.createDoubleCropBlock(
//      "guarana",
//      MapColor.COLOR_RED,
//      true,
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      { ModItems.GUARANA_FRUIT.get() },
//      null,
//      0.25f,
//      4
//    )
//  val BUDDING_GUARANA: BlockEntry<BuddingDoubleCropBlock> =
//    CropBuilderPresets.createBuddingDoubleCropBlock(
//      "guarana",
//      ModNames.GUARANA_SEEDS,
//      "Budding Guarana Crop",
//      "Guarana Seeds",
//      MapColor.COLOR_RED,
//      { TALL_GUARANA.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
//      listOf(),
//      { p -> p },
//    )
//
//  // CASSAVA
//  val TALL_CASSAVA: BlockEntry<DoubleCropBlock> =
//    CropBuilderPresets.createDoubleCropBlock(
//      "cassava",
//      MapColor.TERRACOTTA_BROWN,
//      false,
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      { BUDDING_CASSAVA.get().asItem() },
//      null,
//      0.75f,
//      3
//    )
//  val BUDDING_CASSAVA: BlockEntry<BuddingDoubleCropBlock> =
//    CropBuilderPresets.createBuddingDoubleCropBlock(
//      "cassava",
//      ModNames.CASSAVA_ROOT,
//      "Budding Cassava Crop",
//      "Cassava Root",
//      MapColor.TERRACOTTA_BROWN,
//      { TALL_CASSAVA.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      listOf(),
//      { p -> ModItems.foodItem(p, AddonFoodValues.CASSAVA) },
//    )
//
//
//  // LEMON
//  val LEMON_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(
//    ModNames.LEMON,
//    MapColor.COLOR_LIGHT_GREEN,
//    LemonTreeGrower(),
//    listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER)
//  ) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }
//  val POTTED_LEMON_SAPLING =
//    SaplingBuilderPresets.createPottedBlock(ModNames.LEMON, MapColor.COLOR_LIGHT_GREEN, { LEMON_SAPLING.get() })
//  val LEMON_LEAVES = LeavesBuilderPresets.createLeavesBlock<FlammableLeavesBlock>(
//    ModNames.LEMON,
//    MapColor.COLOR_LIGHT_GREEN,
//    { LEMON_SAPLING.get() })
//  val BUDDING_LEMON_LEAVES = LeavesBuilderPresets.createCropLeavesBlock(
//    ModNames.LEMON,
//    MapColor.COLOR_LIGHT_GREEN,
//    { ModItems.LEMON.get() },
//    { LEMON_SAPLING.get() })
//
//  // ACAI PALM
//  val ACAI_BRANCH: BlockEntry<DoubleAcaiBlock> =
//    CropBuilderPresets.createDoubleAcaiBlock(
//      "acai",
//      MapColor.COLOR_PURPLE,
//      true,
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      { BUDDING_ACAI_BRANCH.get().asItem() },
//      null,
//      0.5f,
//      3
//    )
//  val BUDDING_ACAI_BRANCH: BlockEntry<BuddingAcaiBlock> =
//    CropBuilderPresets.createBuddingAcaiBlock(
//      "acai",
//      ModNames.ACAI_BERRIES,
//      "Budding Acai Branch",
//      "Acai Berries",
//      MapColor.COLOR_PURPLE,
//      { ACAI_BRANCH.get() },
//      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
//      listOf(AddonTags.ITEM.ACAI),
//      { p -> ModItems.foodItem(p, AddonFoodValues.ACAI) },
//    )
//  val ACAI_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(
//    ModNames.ACAI_PALM,
//    MapColor.COLOR_PURPLE,
//    AcaiPalmTreeGrower(),
//    listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER)
//  ) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }
//  val POTTED_ACAI_PALM_SAPLING =
//    SaplingBuilderPresets.createPottedBlock(ModNames.ACAI_PALM, MapColor.COLOR_PURPLE, { ACAI_PALM_SAPLING.get() })
//  val ACAI_PALM_LEAVES = LeavesBuilderPresets.createLeavesBlock(
//    ModNames.ACAI_PALM,
//    MapColor.COLOR_PURPLE,
//    { ACAI_PALM_SAPLING.get() },
//    { p -> PalmLeavesBlock(p) })
//
//  // WILD CROPS
//  val WILD_GARLIC = GrassBuilderPresets.createGrassBlock(
//    ModNames.WILD_GARLIC,
//    MapColor.TERRACOTTA_WHITE,
//    { ModItems.GARLIC_BULB.get() },
//    false,
//    false,
//    false,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val WILD_BEANS = GrassBuilderPresets.createGrassBlock(
//    ModNames.WILD_BEANS,
//    MapColor.TERRACOTTA_LIGHT_GRAY,
//    { ModItems.BEAN_POD.get() },
//    false,
//    false,
//    false,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val WILD_COLLARD_GREENS = GrassBuilderPresets.createGrassBlock(
//    ModNames.WILD_COLLARD_GREENS,
//    MapColor.TERRACOTTA_GREEN,
//    { ModItems.COLLARD_GREENS.get() },
//    false,
//    false,
//    false,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val WILD_CASSAVA = GrassBuilderPresets.createGrassBlock(
//    ModNames.WILD_CASSAVA,
//    MapColor.TERRACOTTA_BROWN,
//    { BUDDING_CASSAVA.get() },
//    false,
//    false,
//    false,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val WILD_COFFEE_BERRIES = GrassBuilderPresets.createGrassBlock(
//    ModNames.WILD_COFFEE_BUSH,
//    MapColor.COLOR_BROWN,
//    { ModItems.COFFEE_BERRIES.get() },
//    false,
//    false,
//    false,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val WILD_CORN = GrassBuilderPresets.createDoubleTallGrassBlock(
//    ModNames.WILD_CORN,
//    MapColor.COLOR_YELLOW,
//    { ModItems.CORN.get() },
//    { BUDDING_CORN.get().asItem() },
//    0.6f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) },
//    ""
//  )
//  val WILD_GUARANA = GrassBuilderPresets.createDoubleTallGrassBlock(
//    ModNames.WILD_GUARANA,
//    MapColor.COLOR_RED,
//    { ModItems.GUARANA_FRUIT.get() },
//    { BUDDING_GUARANA.get().asItem() },
//    0.6f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) },
//    ""
//  )
//
//  val YERBA_MATE_BUSH: BlockEntry<GenericGrassBlock> = GrassBuilderPresets.createGrassBlock(
//    ModNames.YERBA_MATE_BUSH,
//    MapColor.TERRACOTTA_GREEN,
//    { ModItems.YERBA_MATE_LEAVES.get() },
//    false,
//    false,
//    true,
//    0.85f,
//    2,
//    { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
//  val POTTED_YERBA_MATE = SaplingBuilderPresets.createPottedBlock(
//    ModNames.YERBA_MATE_BUSH,
//    MapColor.TERRACOTTA_GREEN,
//    { YERBA_MATE_BUSH.get() },
//    ""
//  )
//
//  // COCONUT
//  val COCONUT_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(
//    ModNames.COCONUT_PALM,
//    MapColor.COLOR_BROWN,
//    CoconutPalmTreeGrower(),
//    listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER)
//  ) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) || blockState.`is`(BlockTags.SAND) }
//  val POTTED_COCONUT_PALM_SAPLING = SaplingBuilderPresets.createPottedBlock(
//    ModNames.COCONUT_PALM,
//    MapColor.COLOR_BROWN,
//    { COCONUT_PALM_SAPLING.get() })
//  val COCONUT_PALM_LEAVES = LeavesBuilderPresets.createLeavesBlock(
//    ModNames.COCONUT_PALM,
//    MapColor.COLOR_BROWN,
//    { COCONUT_PALM_SAPLING.get() },
//    { p -> PalmLeavesBlock(p) })
//  val BUDDING_COCONUT_PALM_LEAVES = LeavesBuilderPresets.createBuddingLeavesBlock(
//    ModNames.COCONUT_PALM,
//    MapColor.COLOR_BROWN,
//    { COCONUT_PALM_SAPLING.get() }) { p -> BuddingLeavesBlock(p) { GREEN_COCONUT.get() } }
//  val GREEN_COCONUT = CoconutBuilderPresets.createCoconutBlock(
//    ModNames.GREEN_COCONUT,
//    MapColor.COLOR_GREEN,
//    CoconutBlock.CoconutState.GREEN
//  ) { COCONUT.get() }
//  val COCONUT: BlockEntry<CoconutBlock> = CoconutBuilderPresets.createCoconutBlock(
//    ModNames.COCONUT,
//    MapColor.COLOR_BROWN,
//    CoconutBlock.CoconutState.BROWN
//  ) { FALLING_COCONUT.get() }
//  val FALLING_COCONUT: BlockEntry<FallingCoconutBlock> =
//    CoconutBuilderPresets.createFallingCoconutBlock(ModNames.COCONUT, MapColor.COLOR_BROWN) { COCONUT.get() }

  fun register() {
    // init
  }
}