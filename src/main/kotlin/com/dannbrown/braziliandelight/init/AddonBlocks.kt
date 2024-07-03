package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.BuddingAcaiBlock
import com.dannbrown.braziliandelight.content.block.BuddingDoubleCropBlock
import com.dannbrown.braziliandelight.content.block.BuddingLeavesBlock
import com.dannbrown.braziliandelight.content.block.BuddingVineCropBlock
import com.dannbrown.braziliandelight.content.block.CoconutBlock
import com.dannbrown.braziliandelight.content.block.CustomCakeBlock
import com.dannbrown.braziliandelight.content.block.DoubleAcaiBlock
import com.dannbrown.braziliandelight.content.block.DoubleCropBlock
import com.dannbrown.braziliandelight.content.block.FallingCoconutBlock
import com.dannbrown.braziliandelight.content.block.HeavyCreamPotBlock
import com.dannbrown.braziliandelight.content.block.LoveAppleTrayBlock
import com.dannbrown.braziliandelight.content.block.MilkPotBlock
import com.dannbrown.braziliandelight.content.block.MinasCheesePot
import com.dannbrown.braziliandelight.content.block.NormalCropBlock
import com.dannbrown.braziliandelight.content.block.PalmLeavesBlock
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.content.block.VineCropBlock
import com.dannbrown.braziliandelight.content.tree.AcaiPalmTreeGrower
import com.dannbrown.braziliandelight.content.tree.CoconutPalmTreeGrower
import com.dannbrown.braziliandelight.content.tree.LemonTreeGrower
import com.dannbrown.braziliandelight.datagen.content.block.CoconutBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.CrateBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.CropBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.FeastBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.GrassBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.LeavesBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.block.SaplingBuilderPresets
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockstatePresets
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.deltaboxlib.content.block.FlammableLeavesBlock
import com.dannbrown.deltaboxlib.content.block.GenericDoublePlantBlock
import com.dannbrown.deltaboxlib.content.block.GenericSaplingBlock
import com.dannbrown.deltaboxlib.content.block.GenericTallGrassBlock
import com.dannbrown.deltaboxlib.registry.generators.BlockGenerator
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.registry.ModBlocks

object AddonBlocks {
  fun register(modBus: IEventBus?) {}

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  // VIRTUAL
  val MILK_POT =
      BLOCKS
          .create<MilkPotBlock>("milk_pot")
          .properties { p ->
            p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
          }
          .blockFactory { p -> MilkPotBlock(p) }
          .blockstate(CustomBlockstatePresets.potBlock("milk"))
          .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
          .noItem()
          .register()

  val HEAVY_CREAM_POT =
      BLOCKS
          .create<HeavyCreamPotBlock>("heavy_cream_pot")
          .properties { p: BlockBehaviour.Properties ->
            p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
          }
          .blockFactory { p -> HeavyCreamPotBlock(p) }
          .blockstate(CustomBlockstatePresets.potBlock("heavy_cream"))
          .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
          .noItem()
          .register()

  val MINAS_CHEESE_POT =
      BLOCKS
          .create<MinasCheesePot>("minas_cheese_pot")
          .properties { p ->
            p.mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)
          }
          .blockFactory { p -> MinasCheesePot(p) }
          .blockstate(CustomBlockstatePresets.potBlock("minas_cheese"))
          .loot(BlockLootPresets.dropOtherLoot { ModBlocks.COOKING_POT.get() })
          .noItem()
          .register()

  // CRATES
  val BEAN_POD_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.BEAN_POD,
          MapColor.COLOR_LIGHT_GREEN,
          { AddonItems.BEAN_POD.get() },
          { DataIngredient.tag(AddonTags.ITEM.BEAN_PODS) }
      )
  val GARLIC_BULB_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.GARLIC_BULB,
          MapColor.TERRACOTTA_WHITE,
          { AddonItems.GARLIC_BULB.get() },
          { DataIngredient.tag(AddonTags.ITEM.GARLIC) }
      )
  val ACAI_BERRIES_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.ACAI_BERRIES,
          MapColor.COLOR_PURPLE,
          { BUDDING_ACAI_BRANCH.get() },
          { DataIngredient.tag(AddonTags.ITEM.ACAI) }
      )
  val GUARANA_FRUIT_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.GUARANA_FRUIT,
          MapColor.COLOR_RED,
          { AddonItems.GUARANA_FRUIT.get() },
          { DataIngredient.tag(AddonTags.ITEM.GUARANA) }
      )
  val GREEN_COCONUT_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.GREEN_COCONUT,
          MapColor.COLOR_GREEN,
          { GREEN_COCONUT.get() },
          { DataIngredient.items(GREEN_COCONUT.get()) }
      )
  val COCONUT_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.COCONUT,
          MapColor.COLOR_BROWN,
          { COCONUT.get() },
          { DataIngredient.tag(AddonTags.ITEM.COCONUT) }
      )
  val CORN_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.CORN,
          MapColor.COLOR_YELLOW,
          { AddonItems.CORN.get() },
          { DataIngredient.tag(AddonTags.ITEM.CORN) }
      )
  val CASSAVA_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.CASSAVA,
          MapColor.COLOR_BROWN,
          { BUDDING_CASSAVA.get() },
          { DataIngredient.tag(AddonTags.ITEM.CASSAVA) }
      )
  val COLLARD_GREENS_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.COLLARD_GREENS,
          MapColor.COLOR_GREEN,
          { AddonItems.COLLARD_GREENS.get() },
          { DataIngredient.tag(AddonTags.ITEM.COLLARD_GREENS) }
      )
  val COFFEE_BERRIES_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.COFFEE_BERRIES,
          MapColor.COLOR_BROWN,
          { AddonItems.COFFEE_BERRIES.get() },
          { DataIngredient.items(AddonItems.COFFEE_BERRIES.get()) }
      )
  val LEMON_CRATE =
      CrateBuilderPresets.createCrateBlock(
          AddonNames.LEMON,
          MapColor.COLOR_YELLOW,
          { AddonItems.LEMON.get() },
          { DataIngredient.items(AddonItems.LEMON.get()) }
      )
  // BAGS
  val BLACK_BEANS_BAG =
      CrateBuilderPresets.crateBagBlock(
          AddonNames.BLACK_BEANS,
          MapColor.COLOR_BLACK,
          { BUDDING_BEANS_CROP.get() },
          { DataIngredient.items(BUDDING_BEANS_CROP.get()) }
      )
  val CARIOCA_BEANS_BAG =
      CrateBuilderPresets.crateBagBlock(
          AddonNames.CARIOCA_BEANS,
          MapColor.TERRACOTTA_ORANGE,
          { CARIOCA_BEANS_CROP.get() },
          { DataIngredient.items(CARIOCA_BEANS_CROP.get()) }
      )
  val COFFEE_BEANS_BAG =
      CrateBuilderPresets.crateBagBlock(
          AddonNames.COFFEE_BEANS,
          MapColor.COLOR_BROWN,
          { AddonItems.COFFEE_BEANS.get() },
          { DataIngredient.tag(AddonTags.ITEM.COFFEE_BEANS) }
      )

  // PLACEABLE FOODS
  val CARROT_CAKE_CANDLE_COLORS = FeastBuilderPresets.createCandleCakes(AddonNames.CARROT_CAKE) { CARROT_CAKE.get() }
  val CARROT_CAKE: BlockEntry<CustomCakeBlock> =
    FeastBuilderPresets.createCakeBlock(
          AddonNames.CARROT_CAKE,
          MapColor.COLOR_ORANGE,
          { AddonItems.CARROT_CAKE_SLICE.get() },
          CARROT_CAKE_CANDLE_COLORS
      )

  val CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS =
    FeastBuilderPresets.createCandleCakes(AddonNames.CARROT_CAKE_WITH_CHOCOLATE) { CARROT_CAKE_WITH_CHOCOLATE.get() }
  val CARROT_CAKE_WITH_CHOCOLATE: BlockEntry<CustomCakeBlock> =
    FeastBuilderPresets.createCakeBlock(
          AddonNames.CARROT_CAKE_WITH_CHOCOLATE,
          MapColor.COLOR_ORANGE,
          { AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() },
          CARROT_CAKE_WITH_CHOCOLATE_CANDLE_COLORS
      )

  val MINAS_CHEESE: BlockEntry<PieBlock> =
    FeastBuilderPresets.createCheeseBlock(AddonNames.MINAS_CHEESE, MapColor.TERRACOTTA_WHITE) {
        AddonItems.MINAS_CHEESE_SLICE.get()
      }
  val CHICKEN_POT_PIE: BlockEntry<PieBlock> =
    FeastBuilderPresets.createPieBlock(AddonNames.CHICKEN_POT_PIE, MapColor.SAND) {
        AddonItems.CHICKEN_POT_PIE_SLICE.get()
      }
  val PUDDING: BlockEntry<PlaceableFoodBlock> =
    FeastBuilderPresets.createPuddingBlock(AddonNames.PUDDING, MapColor.COLOR_BROWN) {
        AddonItems.PUDDING_SLICE.get()
      }
  val SALPICAO: BlockEntry<PlaceableFoodBlock> =
    FeastBuilderPresets.createPlateFoodBlock(AddonNames.SALPICAO, MapColor.COLOR_ORANGE) {
        AddonItems.PLATE_OF_SALPICAO.get()
      }
  val CUZCUZ_PAULISTA: BlockEntry<PlaceableFoodBlock> =
    FeastBuilderPresets.createPlateFoodBlock(AddonNames.CUZCUZ_PAULISTA, MapColor.TERRACOTTA_ORANGE) {
        AddonItems.PLATE_OF_CUZCUZ_PAULISTA.get()
      }
  val FEIJOADA_POT: BlockEntry<PlaceableFoodBlock> =
      FeastBuilderPresets.createPotBlock(AddonNames.FEIJOADA_POT, MapColor.COLOR_BLACK) {
        AddonItems.PLATE_OF_FEIJOADA.get()
      }
  val GREEN_SOUP_POT: BlockEntry<PlaceableFoodBlock> =
      FeastBuilderPresets.createPotBlock(AddonNames.GREEN_SOUP_POT, MapColor.COLOR_GREEN) {
        AddonItems.PLATE_OF_GREEN_SOUP.get()
      }
  val FISH_MOQUECA_POT: BlockEntry<PlaceableFoodBlock> =
      FeastBuilderPresets.createPotBlock(AddonNames.FISH_MOQUECA_POT, MapColor.COLOR_ORANGE) {
        AddonItems.PLATE_OF_FISH_MOQUECA.get()
      }
  val STROGANOFF_POT: BlockEntry<PlaceableFoodBlock> =
      FeastBuilderPresets.createPotBlock(AddonNames.STROGANOFF_POT, MapColor.COLOR_RED) {
        AddonItems.PLATE_OF_STROGANOFF.get()
      }

  val SWEET_LOVE_APPLE_TRAY: BlockEntry<LoveAppleTrayBlock> =
    FeastBuilderPresets.createLoveAppleTrayBlock(AddonNames.SWEET_LOVE_APPLE_TRAY, MapColor.COLOR_RED) {
        AddonItems.SWEET_LOVE_APPLE.get()
      }

  // GRASS
  val TALL_SPARSE_DRY_GRASS: BlockEntry<GenericDoublePlantBlock> =
    GrassBuilderPresets.createDoubleTallGrassBlock(
      "sparse_dry_grass",
      MapColor.TERRACOTTA_YELLOW,
      { Items.BEETROOT_SEEDS }
    )
  val SPARSE_DRY_GRASS: BlockEntry<GenericTallGrassBlock> =
    GrassBuilderPresets.createTallGrassBlock(
      "sparse_dry_grass",
      MapColor.TERRACOTTA_YELLOW,
      { TALL_SPARSE_DRY_GRASS.get() },
      { Items.BEETROOT_SEEDS }
    )

  // BEANS
  val BEANS_CROP: BlockEntry<VineCropBlock> =
      CropBuilderPresets.createVineCropBlock(
        AddonNames.BEAN,
        MapColor.TERRACOTTA_LIGHT_GRAY,
        { AddonItems.BEAN_POD.get() },
        { AddonItems.BEAN_POD.get() },
        { BUDDING_BEANS_CROP.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER)
      )
  val BUDDING_BEANS_CROP: BlockEntry<BuddingVineCropBlock> =
      CropBuilderPresets.createBuddingVineCropBlock(
        AddonNames.BEAN,
        AddonNames.BLACK_BEANS,
        "Budding Beans Crop",
        "Black Beans",
        MapColor.TERRACOTTA_LIGHT_GREEN,
        { BEANS_CROP.get() },
        { AddonItems.BEAN_POD.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
        listOf(AddonTags.ITEM.BEANS),
        { p -> AddonItems.foodItem(p, AddonFoodValues.BEAN)}
      )
  val CARIOCA_BEANS_CROP: BlockEntry<BuddingVineCropBlock> =
    CropBuilderPresets.createBuddingVineCropBlock(
      AddonNames.BEAN,
      AddonNames.CARIOCA_BEANS,
      "Budding Beans Crop",
      "Carioca Beans",
      MapColor.TERRACOTTA_LIGHT_GREEN,
      { BEANS_CROP.get() },
      { AddonItems.BEAN_POD.get() },
      listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
      listOf(AddonTags.ITEM.BEANS),
      { p -> AddonItems.foodItem(p, AddonFoodValues.BEAN)}
    )

  // COLLARD GREENS
  val COLLARD_GREENS_CROP: BlockEntry<NormalCropBlock> =
      CropBuilderPresets.createNormalCropBlock(
        AddonNames.COLLARD_GREENS,
        AddonNames.COLLARD_GREENS_SEEDS,
        "Collard Greens Crops",
        "Collard Greens Seeds",
        MapColor.TERRACOTTA_GREEN,
        { AddonItems.COLLARD_GREENS.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN)
      )

  // GARLIC
  val GARLIC_CROP: BlockEntry<NormalCropBlock> =
      CropBuilderPresets.createNormalCropBlock(
        AddonNames.GARLIC,
        AddonNames.GARLIC_CLOVE,
        "Garlic Crops",
        "Garlic Clove",
        MapColor.TERRACOTTA_WHITE,
        { AddonItems.GARLIC_BULB.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_AUTUMN, AddonTags.BLOCK.SERENE_SEASONS_WINTER),
        false,
      )

  // COFFEE
  val TALL_COFFEE: BlockEntry<DoubleCropBlock> =
      CropBuilderPresets.createDoubleCropBlock(
        "coffee",
        MapColor.TERRACOTTA_RED,
        true,
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        { AddonItems.COFFEE_BERRIES.get() },
        null,
        0.5f,
        3
      )
  val BUDDING_COFFEE: BlockEntry<BuddingDoubleCropBlock> =
      CropBuilderPresets.createBuddingDoubleCropBlock(
       "coffee",
    AddonNames.COFFEE_SEEDS,
    "Budding Coffee Crop",
    "Coffee Seeds",
        MapColor.TERRACOTTA_RED,
        { TALL_COFFEE.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        listOf(),
        { p -> p },
      )

  // CORN
  val TALL_CORN: BlockEntry<DoubleCropBlock> =
      CropBuilderPresets.createDoubleCropBlock(
        "corn",
        MapColor.COLOR_YELLOW,
        true,
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        { AddonItems.CORN.get() },
        null,
        0.5f,
        3
      )
  val BUDDING_CORN: BlockEntry<BuddingDoubleCropBlock> =
      CropBuilderPresets.createBuddingDoubleCropBlock(
        "corn",
        AddonNames.KERNELS,
        "Budding Corn Crop",
        "Kernels",
        MapColor.COLOR_YELLOW,
        { TALL_CORN.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        listOf(),
        { p -> p },
      )
  val WHITE_KERNELS_CROP: BlockEntry<BuddingDoubleCropBlock> =
    CropBuilderPresets.createBuddingDoubleCropBlock(
      "corn",
      AddonNames.WHITE_KERNELS,
      "Budding Corn Crop",
      "White Kernels",
      MapColor.SNOW,
      { TALL_CORN.get() },
      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
      listOf(),
      { p -> p },
    )

  // GUARANA
  val TALL_GUARANA: BlockEntry<DoubleCropBlock> =
      CropBuilderPresets.createDoubleCropBlock(
        "guarana",
        MapColor.COLOR_RED,
        true,
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        { AddonItems.GUARANA_FRUIT.get() },
        null,
        0.25f,
        4
      )
  val BUDDING_GUARANA: BlockEntry<BuddingDoubleCropBlock> =
      CropBuilderPresets.createBuddingDoubleCropBlock(
        "guarana",
        AddonNames.GUARANA_SEEDS,
        "Budding Guarana Crop",
        "Guarana Seeds",
        MapColor.COLOR_RED,
        { TALL_GUARANA.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER, AddonTags.BLOCK.SERENE_SEASONS_AUTUMN),
        listOf(),
        { p -> p },
      )

  // CASSAVA
  val TALL_CASSAVA: BlockEntry<DoubleCropBlock> =
      CropBuilderPresets.createDoubleCropBlock(
        "cassava",
        MapColor.TERRACOTTA_BROWN,
        false,
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
        { BUDDING_CASSAVA.get().asItem() },
        null,
        0.75f,
        3
      )
  val BUDDING_CASSAVA: BlockEntry<BuddingDoubleCropBlock> =
      CropBuilderPresets.createBuddingDoubleCropBlock(
        "cassava",
        AddonNames.CASSAVA_ROOT,
        "Budding Cassava Crop",
        "Cassava Root",
        MapColor.TERRACOTTA_BROWN,
        { TALL_CASSAVA.get() },
        listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
        listOf(),
        { p -> AddonItems.foodItem(p, AddonFoodValues.CASSAVA) },
      )


  // LEMON
  val LEMON_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(AddonNames.LEMON, MapColor.COLOR_LIGHT_GREEN, LemonTreeGrower(), listOf(AddonTags.BLOCK.SERENE_SEASONS_SPRING, AddonTags.BLOCK.SERENE_SEASONS_SUMMER)) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }
  val POTTED_LEMON_SAPLING = SaplingBuilderPresets.createPottedSaplingBlock(AddonNames.LEMON, MapColor.COLOR_LIGHT_GREEN) { LEMON_SAPLING.get() }
  val LEMON_LEAVES = LeavesBuilderPresets.createLeavesBlock<FlammableLeavesBlock>(AddonNames.LEMON, MapColor.COLOR_LIGHT_GREEN, { LEMON_SAPLING.get() })
  val BUDDING_LEMON_LEAVES = LeavesBuilderPresets.createCropLeavesBlock(AddonNames.LEMON, MapColor.COLOR_LIGHT_GREEN, { AddonItems.LEMON.get() }, { LEMON_SAPLING.get() })

  // ACAI PALM
  val ACAI_BRANCH: BlockEntry<DoubleAcaiBlock> =
    CropBuilderPresets.createDoubleAcaiBlock(
      "acai",
      MapColor.COLOR_PURPLE,
      true,
      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
      { BUDDING_ACAI_BRANCH.get().asItem() },
      null,
      0.5f,
      3
    )
  val BUDDING_ACAI_BRANCH: BlockEntry<BuddingAcaiBlock> =
    CropBuilderPresets.createBuddingAcaiBlock(
      "acai",
      AddonNames.ACAI_BERRIES,
      "Budding Acai Branch",
      "Acai Berries",
      MapColor.COLOR_PURPLE,
      { ACAI_BRANCH.get() },
      listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER),
      listOf(AddonTags.ITEM.ACAI),
      { p -> AddonItems.foodItem(p, AddonFoodValues.ACAI) },
    )
  val ACAI_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(AddonNames.ACAI_PALM, MapColor.COLOR_PURPLE, AcaiPalmTreeGrower(), listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER)) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }
  val POTTED_ACAI_PALM_SAPLING = SaplingBuilderPresets.createPottedSaplingBlock(AddonNames.ACAI_PALM, MapColor.COLOR_PURPLE) { ACAI_PALM_SAPLING.get() }
  val ACAI_PALM_LEAVES = LeavesBuilderPresets.createLeavesBlock(AddonNames.ACAI_PALM, MapColor.COLOR_PURPLE, { ACAI_PALM_SAPLING.get() }, { p -> PalmLeavesBlock(p) })

  // WILD CROPS
  val WILD_GARLIC = GrassBuilderPresets.createGrassBlock(AddonNames.WILD_GARLIC, MapColor.TERRACOTTA_WHITE, { AddonItems.GARLIC_BULB.get() }, 0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
  val WILD_BEANS = GrassBuilderPresets.createGrassBlock(AddonNames.WILD_BEANS, MapColor.TERRACOTTA_LIGHT_GRAY, { AddonItems.BEAN_POD.get() }, 0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
  val WILD_COLLARD_GREENS = GrassBuilderPresets.createGrassBlock(AddonNames.WILD_COLLARD_GREENS, MapColor.TERRACOTTA_GREEN, { AddonItems.COLLARD_GREENS.get() },0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
  val WILD_CASSAVA = GrassBuilderPresets.createGrassBlock(AddonNames.WILD_CASSAVA, MapColor.TERRACOTTA_BROWN, { BUDDING_CASSAVA.get() }, 0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
  val WILD_COFFEE_BERRIES = GrassBuilderPresets. createGrassBlock(AddonNames.WILD_COFFEE_BUSH, MapColor.COLOR_BROWN, { AddonItems.COFFEE_BERRIES.get() }, 0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
  val WILD_CORN = GrassBuilderPresets.createDoubleTallGrassBlock(AddonNames.WILD_CORN, MapColor.COLOR_YELLOW, { AddonItems.CORN.get() }, { BUDDING_CORN.get().asItem() }, 0.6f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }, "")
  val WILD_GUARANA = GrassBuilderPresets.createDoubleTallGrassBlock(AddonNames.WILD_GUARANA, MapColor.COLOR_RED, { AddonItems.GUARANA_FRUIT.get() }, { BUDDING_GUARANA.get().asItem() }, 0.6f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }, "")
  val YERBA_MATE_BUSH = GrassBuilderPresets.createGrassBlock(AddonNames.YERBA_MATE_BUSH, MapColor.TERRACOTTA_GREEN, { AddonItems.YERBA_MATE_LEAVES.get() }, 0.85f, 2, { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })

  // COCONUT
  val COCONUT_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = SaplingBuilderPresets.createSaplingBlock(AddonNames.COCONUT_PALM, MapColor.COLOR_BROWN, CoconutPalmTreeGrower(), listOf(AddonTags.BLOCK.SERENE_SEASONS_SUMMER)) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) || blockState.`is`(BlockTags.SAND) }
  val POTTED_COCONUT_PALM_SAPLING = SaplingBuilderPresets.createPottedSaplingBlock(AddonNames.COCONUT_PALM, MapColor.COLOR_BROWN) { COCONUT_PALM_SAPLING.get() }
  val COCONUT_PALM_LEAVES = LeavesBuilderPresets.createLeavesBlock(AddonNames.COCONUT_PALM, MapColor.COLOR_BROWN, { COCONUT_PALM_SAPLING.get() }, { p -> PalmLeavesBlock(p) })
  val BUDDING_COCONUT_PALM_LEAVES = LeavesBuilderPresets.createBuddingLeavesBlock(AddonNames.COCONUT_PALM, MapColor.COLOR_BROWN, { COCONUT_PALM_SAPLING.get() }) { p -> BuddingLeavesBlock(p) { GREEN_COCONUT.get() } }
  val GREEN_COCONUT = CoconutBuilderPresets.createCoconutBlock(AddonNames.GREEN_COCONUT, MapColor.COLOR_GREEN, CoconutBlock.CoconutState.GREEN) { COCONUT.get() }
  val COCONUT: BlockEntry<CoconutBlock> = CoconutBuilderPresets.createCoconutBlock(AddonNames.COCONUT, MapColor.COLOR_BROWN, CoconutBlock.CoconutState.BROWN) { FALLING_COCONUT.get() }
  val FALLING_COCONUT: BlockEntry<FallingCoconutBlock> = CoconutBuilderPresets.createFallingCoconutBlock(AddonNames.COCONUT, MapColor.COLOR_BROWN) { COCONUT.get() }
}
