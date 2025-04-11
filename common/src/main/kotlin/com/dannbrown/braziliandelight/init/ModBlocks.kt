package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.content.blocks.*
import com.dannbrown.braziliandelight.content.presets.BlockstatePresets
import com.dannbrown.braziliandelight.content.presets.CoconutBuilderPresets
import com.dannbrown.braziliandelight.content.presets.CrateBuilderPresets
import com.dannbrown.braziliandelight.content.presets.FeastBuilderPresets
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.content.block.*
import com.dannbrown.deltaboxlib.registrate.registry.BlockEntry
import com.dannbrown.deltaboxlib.registrate.util.DataIngredient
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction

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
  val BEAN_POD_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.BEAN_POD,
    MapColor.COLOR_LIGHT_GREEN,
    { ModItems.BEAN_POD.get() },
    { DataIngredient(ModItems.BEAN_POD.get()) })
  val GARLIC_BULB_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.GARLIC_BULB,
    MapColor.TERRACOTTA_WHITE,
    { ModItems.GARLIC_BULB.get() },
    { DataIngredient(ModItems.GARLIC_BULB.get()) })

  val ACAI_BERRIES_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.ACAI_BERRIES,
    MapColor.COLOR_PURPLE,
    { BUDDING_ACAI_BRANCH.get() },
    { DataIngredient(BUDDING_ACAI_BRANCH.getItem()) })
  val GUARANA_FRUIT_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.GUARANA_FRUIT,
    MapColor.COLOR_RED,
    { ModItems.GUARANA_FRUIT.get() },
    { DataIngredient(ModItems.GUARANA_FRUIT.get()) })
  val GREEN_COCONUT_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.GREEN_COCONUT,
    MapColor.COLOR_GREEN,
    { GREEN_COCONUT.get() },
    { DataIngredient(GREEN_COCONUT.get()) })
  val COCONUT_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.COCONUT,
    MapColor.COLOR_BROWN,
    { COCONUT.get() },
    { DataIngredient(COCONUT.get()) })
  val CORN_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.CORN,
    MapColor.COLOR_YELLOW,
    { ModItems.CORN.get() },
    { DataIngredient(ModItems.CORN.get()) })
  val CASSAVA_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.CASSAVA,
    MapColor.COLOR_BROWN,
    { BUDDING_CASSAVA.get() },
    { DataIngredient(BUDDING_CASSAVA.get()) })
  val COLLARD_GREENS_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.COLLARD_GREENS,
    MapColor.COLOR_GREEN,
    { ModItems.COLLARD_GREENS.get() },
    { DataIngredient(ModItems.COLLARD_GREENS.get()) })
  val COFFEE_BERRIES_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.COFFEE_BERRIES,
    MapColor.COLOR_BROWN,
    { ModItems.COFFEE_BERRIES.get() },
    { DataIngredient(ModItems.COFFEE_BERRIES.get()) })
  val LEMON_CRATE = CrateBuilderPresets.createCrateBlock(
    ModNames.LEMON,
    MapColor.COLOR_YELLOW,
    { ModItems.LEMON.get() },
    { DataIngredient(ModItems.LEMON.get()) })

  //
  // BAGS
  val BLACK_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
    ModNames.BLACK_BEANS,
    MapColor.COLOR_BLACK,
    { BLACK_BEANS_CROP.get() },
    { DataIngredient(BLACK_BEANS_CROP.get()) })
  val CARIOCA_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
    ModNames.CARIOCA_BEANS,
    MapColor.TERRACOTTA_ORANGE,
    { CARIOCA_BEANS_CROP.get() },
    { DataIngredient(CARIOCA_BEANS_CROP.get()) })
  val COFFEE_BEANS_BAG = CrateBuilderPresets.crateBagBlock(
    ModNames.COFFEE_BEANS,
    MapColor.COLOR_BROWN,
    { ModItems.COFFEE_BEANS.get() },
    { DataIngredient(ModItems.COFFEE_BEANS.get()) })

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

  // BEANS
  val CARIOCA_BEANS_CROP = REGISTRATE.blockPreset<GenericCropBlock>(ModNames.CARIOCA_BEANS)
    .crop(ModNames.BEAN, "Carioca Beans Crop", "Carioca Beans", { ModItems.BEAN_POD.get() }, true, false)
    .color(MapColor.TERRACOTTA_LIGHT_GREEN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .itemTags(*ModTags.ITEM.BEANS.toTypedArray())
    .register()
  val BLACK_BEANS_CROP = REGISTRATE.blockPreset<GenericCropBlock>(ModNames.BLACK_BEANS)
    .crop(ModNames.BEAN, "Black Beans Crop", "Black Beans", { ModItems.BEAN_POD.get() }, true, false)
    .color(MapColor.TERRACOTTA_LIGHT_GREEN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .itemTags(*ModTags.ITEM.BEANS.toTypedArray())
    .register()

  // COLLARD GREENS
  val COLLARD_GREENS_CROP = REGISTRATE.blockPreset<GenericCropBlock>(ModNames.COLLARD_GREENS_SEEDS)
    .crop(
      ModNames.COLLARD_GREENS,
      "Collard Greens Crop",
      "Collard Greens Seeds",
      { ModItems.COLLARD_GREENS.get() },
      false,
      true
    )
    .color(MapColor.TERRACOTTA_GREEN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
    .register()

  // GARLIC
  val GARLIC_CROP = REGISTRATE.blockPreset<GenericCropBlock>(ModNames.GARLIC_CLOVE)
    .crop(ModNames.GARLIC, "Garlic Crop", "Garlic Clove", { ModItems.GARLIC_BULB.get() }, false, false)
    .color(MapColor.TERRACOTTA_WHITE)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_AUTUMN, ModTags.BLOCK.SERENE_SEASONS_WINTER)
    .itemTags(*ModTags.ITEM.GARLIC.toTypedArray())
    .register()

  // COFFEE
  val BUDDING_COFFEE: BlockEntry<GenericCropBlock> =
    REGISTRATE.blockPreset<GenericCropBlock>(ModNames.COFFEE_SEEDS)
      .buddingCrop(ModNames.COFFEE, "Coffee Seeds", "Coffee Crop", { COFFEE_CROP.get() }, false)
      .color(MapColor.COLOR_LIGHT_GREEN)
      .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
      .itemTags(*ModTags.ITEM.COFFEE.toTypedArray())
      .register()
  val COFFEE_CROP: BlockEntry<GenericCropBlock> = REGISTRATE.blockPreset<GenericCropBlock>("${ModNames.COFFEE}_crop")
    .doubleCrop(
      ModNames.COFFEE,
      "Coffee Crop",
      { BUDDING_COFFEE.get().asItem() },
      { ModItems.COFFEE_BERRIES.get() },
      true,
      false,
      0.5f,
      3
    )
    .color(MapColor.COLOR_RED)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
    .register()

  // CORN
  val BUDDING_CORN: BlockEntry<GenericCropBlock> = REGISTRATE.blockPreset<GenericCropBlock>(ModNames.KERNELS)
    .buddingCrop(ModNames.CORN, "Kernels", "Corn Crop", { CORN_CROP.get() }, false)
    .color(MapColor.COLOR_LIGHT_GREEN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
    .itemTags(*ModTags.ITEM.KERNELS.toTypedArray())
    .register()
  val BUDDING_WHITE_CORN: BlockEntry<GenericCropBlock> =
    REGISTRATE.blockPreset<GenericCropBlock>(ModNames.WHITE_KERNELS)
      .buddingCrop(ModNames.CORN, "White Kernels", "Corn Crop", { CORN_CROP.get() }, false)
      .color(MapColor.COLOR_LIGHT_GREEN)
      .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
      .itemTags(*ModTags.ITEM.KERNELS.toTypedArray())
      .register()
  val CORN_CROP: BlockEntry<GenericCropBlock> = REGISTRATE.blockPreset<GenericCropBlock>("${ModNames.CORN}_crop")
    .doubleCrop(
      ModNames.CORN,
      "Corn Crop",
      { BUDDING_CORN.get().asItem() },
      { ModItems.CORN.get() },
      true,
      false,
      0.5f,
      3
    )
    .color(MapColor.COLOR_YELLOW)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
    .register()

  // GUARANA
  val BUDDING_GUARANA: BlockEntry<GenericCropBlock> =
    REGISTRATE.blockPreset<GenericCropBlock>(ModNames.GUARANA_SEEDS)
      .buddingCrop(ModNames.GUARANA, "Guarana Seeds", "Guarana Crop", { GUARANA_CROP.get() }, false)
      .color(MapColor.COLOR_LIGHT_GREEN)
      .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
      .itemTags(*ModTags.ITEM.GUARANA.toTypedArray())
      .register()
  val GUARANA_CROP: BlockEntry<GenericCropBlock> = REGISTRATE.blockPreset<GenericCropBlock>("${ModNames.GUARANA}_crop")
    .doubleCrop(
      ModNames.GUARANA,
      "Guarana Crop",
      { BUDDING_GUARANA.get().asItem() },
      { ModItems.GUARANA_FRUIT.get() },
      true,
      false,
      0.3f,
      4
    )
    .color(MapColor.COLOR_RED)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER, ModTags.BLOCK.SERENE_SEASONS_AUTUMN)
    .register()


  // CASSAVA
  val BUDDING_CASSAVA: BlockEntry<GenericCropBlock> = REGISTRATE
    .block<GenericCropBlock>(ModNames.CASSAVA_ROOT)
    .factory { c, p ->
      GenericCropBlock(
        p,
        true,
        { CASSAVA_CROP.get() },
        false,
        false,
        false,
        null,
        1f,
        3
      )
    }
    .copyFrom { Blocks.WHEAT }
    .properties { c, p ->
      p
        .noCollission()
        .randomTicks()
        .instabreak()
        .sound(SoundType.CROP)
        .pushReaction(PushReaction.DESTROY)
    }
    .cutoutRender()
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .blockstate { g, b -> g.buddingCropBlock(b.get(), ModNames.CASSAVA) }
    .lang("Cassava Crop")
    .color(MapColor.TERRACOTTA_BROWN)
    .item { b, p -> ItemNameBlockItem(p, ModItems.foodItem(b, AddonFoodValues.CASSAVA)) }
    .itemTags(*ModTags.ITEM.CASSAVA.toTypedArray())
    .model { g, i -> g.flatItem(i.get()) }
    .lang("Cassava Root")
    .build()
    .compostable(0.3f)
    .loot { g, b -> g.noLoot(b.get()) }
    .register() as BlockEntry<GenericCropBlock>
  val CASSAVA_CROP: BlockEntry<GenericCropBlock> = REGISTRATE.blockPreset<GenericCropBlock>("${ModNames.CASSAVA}_crop")
    .doubleCrop(
      ModNames.CASSAVA,
      "Cassava Crop",
      { BUDDING_CASSAVA.get().asItem() },
      { BUDDING_CASSAVA.get().asItem() },
      false,
      true,
      0.75f,
      3
    )
    .color(MapColor.TERRACOTTA_BROWN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .register()

  // LEMON
  val LEMON_SAPLING: BlockEntry<GenericSaplingBlock> = REGISTRATE
    .blockPreset<GenericSaplingBlock>(ModNames.LEMON)
    .saplingBlock(ModTreeGrowers.LEMON_GROWER) { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) }
    .color(MapColor.COLOR_LIGHT_GREEN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SPRING, ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .register()
  val POTTED_LEMON_SAPLING: BlockEntry<FlowerPotBlock> = REGISTRATE
    .blockPreset<FlowerPotBlock>(ModNames.LEMON)
    .pottedBlock({ LEMON_SAPLING.get() }, "_sapling")
    .color(MapColor.COLOR_LIGHT_GREEN)
    .register()
  val LEMON_LEAVES = REGISTRATE.blockPreset<FlammableLeavesBlock>(ModNames.LEMON)
    .leaves({ LEMON_SAPLING.get() })
    .color(MapColor.COLOR_LIGHT_GREEN)
    .register()
  val BUDDING_LEMON_LEAVES = REGISTRATE.blockPreset<CropLeavesBlock>(ModNames.BUDDING_LEMON)
    .cropLeaves({ LEMON_SAPLING.get() }, { ModItems.LEMON.get() })
    .color(MapColor.COLOR_LIGHT_GREEN)
    .register()

  // ACAI PALM
  val BUDDING_ACAI_BRANCH: BlockEntry<AcaiCropBlock> = REGISTRATE.block<AcaiCropBlock>("acai_berries")
    .factory { c, p ->
      AcaiCropBlock(
        p,
        true,
        { ACAI_BRANCH.get() },
        false,
        false,
        false,
        null,
        0f,
        1
      )
    }
    .copyFrom { Blocks.WHEAT }
    .properties { c, p ->
      p
        .noCollission()
        .randomTicks()
        .instabreak()
        .sound(SoundType.ROOTS)
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
    }
    .cutoutRender()
    .blockstate(BlockstatePresets.buddingAcaiBlock("acai"))
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .lang("Budding Acai Crop")
    .item { b, p -> ItemNameBlockItem(p, ModItems.foodItem(b, AddonFoodValues.ACAI)) }
    .model { g, i -> g.flatItem(i.get()) }
    .itemTags(*ModTags.ITEM.ACAI.toTypedArray())
    .lang("Acai Berries")
    .build()
    .compostable(0.3f)
    .loot { g, b -> g.noLoot(b.get()) }
    .register() as BlockEntry<AcaiCropBlock>

  val ACAI_BRANCH = REGISTRATE.block<AcaiCropBlock>("acai_branch")
    .factory { c, p ->
      AcaiCropBlock(
        p,
        false,
        null,
        true,
        true,
        true,
        { BUDDING_ACAI_BRANCH.get() },
        0.5f,
        3
      )
    }
    .copyFrom { Blocks.WHEAT }
    .properties { c, p ->
      p
        .noCollission()
        .randomTicks()
        .instabreak()
        .sound(SoundType.ROOTS)
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
    }
    .cutoutRender()
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .blockstate(BlockstatePresets.doubleAcaiBlock("acai"))
    .lang("Acai Branch")
    .noItem()
    .loot { g, b ->
      g.dropDoubleCropLoot(
        b.get(),
        { BUDDING_ACAI_BRANCH.get() },
        { BUDDING_ACAI_BRANCH.get() },
        true,
        0.5f,
        3
      )
    }
    .register()

  //  val ACAI_BRANCH: BlockEntry<DoubleAcaiBlock> =
//    CropBuilderPresets.createDoubleAcaiBlock(
//      "acai",
//      MapColor.COLOR_PURPLE,
//      true,
//      listOf(ModTags.BLOCK.SERENE_SEASONS_SUMMER),
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
//      listOf(ModTags.BLOCK.SERENE_SEASONS_SUMMER),
//      listOf(ModTags.ITEM.ACAI),
//      { p -> ModItems.foodItem(p, AddonFoodValues.ACAI) },
//    )
  val ACAI_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = REGISTRATE
    .blockPreset<GenericSaplingBlock>(ModNames.ACAI_PALM)
    .saplingBlock(ModTreeGrowers.ACAI_PALM_GROWER) { blockState, _, _ ->
      blockState.`is`(BlockTags.DIRT) || blockState.`is`(
        BlockTags.SAND
      )
    }
    .color(MapColor.COLOR_PURPLE)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .register()
  val POTTED_ACAI_PALM_SAPLING: BlockEntry<FlowerPotBlock> = REGISTRATE
    .blockPreset<FlowerPotBlock>(ModNames.ACAI_PALM)
    .pottedBlock({ ACAI_PALM_SAPLING.get() }, "_sapling")
    .color(MapColor.COLOR_PURPLE)
    .register()
  val ACAI_PALM_LEAVES = REGISTRATE.blockPreset<PalmLeavesBlock>(ModNames.ACAI_PALM)
    .palmLeaves({ ACAI_PALM_SAPLING.get() })
    .color(MapColor.COLOR_LIGHT_GREEN)
    .biomeColors()
    .register()

  // WILD CROPS
  val WILD_GARLIC: BlockEntry<GenericGrassBlock> = REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.WILD_GARLIC)
    .flowerBlock(
      false,
      false,
      false,
      0.85f,
      2,
      { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
    .color(MapColor.TERRACOTTA_WHITE)
    .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { ModItems.GARLIC_BULB.get() }, 0.85f, 2) }
    .register()
  val WILD_COLLARD_GREENS: BlockEntry<GenericGrassBlock> =
    REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.WILD_COLLARD_GREENS)
      .flowerBlock(
        false,
        false,
        false,
        0.85f,
        2,
        { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
      .color(MapColor.TERRACOTTA_GREEN)
      .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { ModItems.COLLARD_GREENS.get() }, 0.85f, 2) }
      .register()
  val WILD_BEANS: BlockEntry<GenericGrassBlock> = REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.WILD_BEANS)
    .flowerBlock(
      false,
      false,
      false,
      0.85f,
      2,
      { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
    .color(MapColor.TERRACOTTA_LIGHT_GREEN)
    .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { ModItems.BEAN_POD.get() }, 0.85f, 2) }
    .register()
  val WILD_CASSAVA: BlockEntry<GenericGrassBlock> = REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.WILD_CASSAVA)
    .flowerBlock(
      false,
      false,
      false,
      0.85f,
      2,
      { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
    .color(MapColor.TERRACOTTA_BROWN)
    .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { BUDDING_CASSAVA.get() }, 0.85f, 2) }
    .register()
  val WILD_COFFEE_BUSH: BlockEntry<GenericGrassBlock> =
    REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.WILD_COFFEE_BUSH)
      .flowerBlock(
        false,
        false,
        false,
        0.85f,
        2,
        { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
      .color(MapColor.TERRACOTTA_ORANGE)
      .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { ModItems.COFFEE_BERRIES.get() }, 0.85f, 2) }
      .register()
  val WILD_CORN: BlockEntry<GenericDoublePlantBlock> =
    REGISTRATE.blockPreset<GenericDoublePlantBlock>(ModNames.WILD_CORN)
      .doubleFlowerBlock(
        0.85f,
        2,
        { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
      .color(MapColor.COLOR_YELLOW)
      .loot { g, b -> g.dropDoubleCropLoot(b.get(), { ModItems.CORN.get() }, { ModItems.CORN.get() }, false, 0.85f, 2) }
      .register()
  val WILD_GUARANA: BlockEntry<GenericDoublePlantBlock> =
    REGISTRATE.blockPreset<GenericDoublePlantBlock>(ModNames.WILD_GUARANA)
      .doubleFlowerBlock(
        0.85f,
        2,
        { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
      .color(MapColor.COLOR_RED)
      .loot { g, b ->
        g.dropDoubleCropLoot(
          b.get(),
          { ModItems.GUARANA_FRUIT.get() },
          { ModItems.GUARANA_FRUIT.get() },
          false,
          0.85f,
          2
        )
      }
      .register()
  val YERBA_MATE_BUSH: BlockEntry<GenericGrassBlock> =
    REGISTRATE.blockPreset<GenericGrassBlock>(ModNames.YERBA_MATE_BUSH)
      .flowerBlock(
        false,
        false,
        true,
        0.85f,
        2,
        { blockState, _, _ -> blockState.`is`(BlockTags.DIRT) })
      .color(MapColor.TERRACOTTA_GREEN)
      .loot { g, b -> g.dropItselfSilkShearsOtherLoot(b.get(), { ModItems.YERBA_MATE_LEAVES.get() }, 0.85f, 2) }
      .register()
  val POTTED_YERBA_MATE: BlockEntry<FlowerPotBlock> = REGISTRATE
    .blockPreset<FlowerPotBlock>(ModNames.YERBA_MATE_BUSH)
    .pottedBlock({ YERBA_MATE_BUSH.get() }, "")
    .color(MapColor.TERRACOTTA_GREEN)
    .register()

  // COCONUT
  val COCONUT_PALM_SAPLING: BlockEntry<GenericSaplingBlock> = REGISTRATE
    .blockPreset<GenericSaplingBlock>(ModNames.COCONUT_PALM)
    .saplingBlock(ModTreeGrowers.COCONUT_PALM_GROWER) { blockState, _, _ ->
      blockState.`is`(BlockTags.DIRT) || blockState.`is`(
        BlockTags.SAND
      )
    }
    .color(MapColor.COLOR_BROWN)
    .blockTags(ModTags.BLOCK.SERENE_SEASONS_SUMMER)
    .register()
  val POTTED_COCONUT_PALM_SAPLING: BlockEntry<FlowerPotBlock> = REGISTRATE
    .blockPreset<FlowerPotBlock>(ModNames.COCONUT_PALM)
    .pottedBlock({ COCONUT_PALM_SAPLING.get() }, "_sapling")
    .color(MapColor.COLOR_BROWN)
    .register()
  val COCONUT_PALM_LEAVES = REGISTRATE.blockPreset<PalmLeavesBlock>(ModNames.COCONUT_PALM)
    .palmLeaves({ COCONUT_PALM_SAPLING.get() })
    .color(MapColor.COLOR_LIGHT_GREEN)
    .biomeColors()
    .register()
  val BUDDING_COCONUT_PALM_LEAVES = REGISTRATE.blockPreset<BuddingLeavesBlock>("budding_" + ModNames.COCONUT_PALM)
    .buddingLeaves({ COCONUT_PALM_SAPLING.get() }, { GREEN_COCONUT.get() })
    .color(MapColor.COLOR_LIGHT_GREEN)
    .biomeColors()
    .register()

  val GREEN_COCONUT = CoconutBuilderPresets.createCoconutBlock(
    ModNames.GREEN_COCONUT,
    MapColor.COLOR_GREEN,
    CoconutBlock.CoconutState.GREEN
  ) { COCONUT.get() }
  val COCONUT: BlockEntry<CoconutBlock> = CoconutBuilderPresets.createCoconutBlock(
    ModNames.COCONUT,
    MapColor.COLOR_BROWN,
    CoconutBlock.CoconutState.BROWN
  ) { FALLING_COCONUT.get() }
  val FALLING_COCONUT: BlockEntry<FallingCoconutBlock> =
    CoconutBuilderPresets.createFallingCoconutBlock(ModNames.COCONUT, MapColor.COLOR_BROWN) { COCONUT.get() }

  fun register() {
    REGISTRATE.buildBlocks()
  }
}