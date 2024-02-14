package com.dannbrown.discoverplanetzero.init

import com.dannbrown.databoxlib.content.block.BuddingBushBlock
import com.dannbrown.databoxlib.content.block.FlammableSandBlock
import com.dannbrown.databoxlib.registry.generators.BlockFamily
import com.dannbrown.databoxlib.registry.generators.BlockFamilyGen
import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.discover.content.utils.toolTiers.SetTier
import com.dannbrown.discover.content.utils.toolTiers.SetTool
import com.dannbrown.discover.lib.LibData
import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.discoverplanetzero.content.block.CottonCropBlock
import com.dannbrown.databoxlib.content.block.GenericDoubleFlowerBlock
import com.dannbrown.databoxlib.content.block.GenericDoublePlantBlock
import com.dannbrown.databoxlib.content.block.GenericFlowerBlock
import com.dannbrown.databoxlib.content.block.GenericGrassBlock
import com.dannbrown.databoxlib.content.block.GenericTallGrassBlock
import com.dannbrown.databoxlib.content.block.GenericTallGrassTopBlock
import com.dannbrown.databoxlib.registry.transformers.BlockLootPresets
import com.dannbrown.databoxlib.registry.transformers.BlockstatePresets
import com.dannbrown.databoxlib.registry.transformers.ItemModelPresets
import com.dannbrown.discoverplanetzero.datagen.worldgen.tree.JoshuaTreeGrower
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.content.block.BuddingCottonBlock
import com.dannbrown.discoverplanetzero.content.block.GuayuleShrubBlock
import com.dannbrown.discoverplanetzero.content.block.GuayuleShrubTopBlock
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.AmethystClusterBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CarpetBlock
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.SandBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.eventbus.api.IEventBus

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

  val SILT = BLOCKS.create<SandBlock>(LibData.BLOCKS.SILT).sandBlock(14866349)
    .color(MapColor.SAND)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .blockTags(listOf(BlockTags.SAND))
    .itemTags(listOf(ItemTags.SAND))
    .register()
  val SANDSTONE_PEBBLES = BLOCKS.create<SandBlock>(LibData.BLOCKS.SANDSTONE_PEBBLES).sandBlock(14406560)
    .copyFrom({ Blocks.GRAVEL })
    .color(MapColor.SAND)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .register()
  val RED_SANDSTONE_PEBBLES = BLOCKS.create<SandBlock>(LibData.BLOCKS.RED_SANDSTONE_PEBBLES).sandBlock(11098145)
    .copyFrom({ Blocks.GRAVEL })
    .color(MapColor.COLOR_ORANGE)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .register()

  val PHOSPHORUS_BLOCK = BLOCKS.create<FlammableSandBlock>(LibData.NAMES.PHOSPHORUS).flammableSandBlock(16250871)
    .storageBlock({ AddonItems.PHOSPHORUS_POWDER.get() }, {
      DataIngredient.tag(LibTags.forgeItemTag("dusts/phosphorus"))
    })
    .color(MapColor.TERRACOTTA_MAGENTA)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .register()
  val MAGNESIUM_BLOCK = BLOCKS.create<SandBlock>(LibData.NAMES.MAGNESIUM).sandBlock(16250871)
    .storageBlock({ AddonItems.MAGNESIUM_DUST.get() }, {
      DataIngredient.tag(LibTags.forgeItemTag("dusts/magnesium"))
    })
    .color(MapColor.COLOR_LIGHT_GRAY)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .register()

  val ROSEATE_SANDSTONE_PEBBLES = BLOCKS.create<SandBlock>(LibData.BLOCKS.ROSEATE_SANDSTONE_PEBBLES)
    .sandBlock(15839662)
    .copyFrom { Blocks.GRAVEL }
    .color(MapColor.COLOR_PINK)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .register()
  val ROSEATE_GRAINS = BLOCKS.create<SandBlock>(LibData.BLOCKS.ROSEATE_GRAINS)
    .sandBlock(15839662)
    .copyFrom { Blocks.SAND }
    .color(MapColor.COLOR_PINK)
    .toolAndTier(SetTool.SHOVEL.getTag(), SetTier.WOOD.getTag(), false)
    .blockTags(listOf(BlockTags.SAND))
    .itemTags(listOf(ItemTags.SAND))
    .register()
  val ROSEATE_FAMILY = BLOCKS.createFamily(LibData.NAMES.ROSEATE)
    .color(MapColor.COLOR_PINK)
    .copyFrom { Blocks.SANDSTONE }
    .toolAndTier(SetTool.PICKAXE.getTag(), SetTier.WOOD.getTag())
    .sandstoneFamily(ROSEATE_GRAINS)
  val HIMALAYAN_SALT_CLUSTER = BLOCKS.create<AmethystClusterBlock>("${LibData.NAMES.HIMALAYAN_SALT}_cluster").copyFrom({ Blocks.AMETHYST_CLUSTER })
    .color(MapColor.COLOR_MAGENTA)
    .toolAndTier(SetTool.PICKAXE.getTag(), SetTier.WOOD.getTag())
    .blockFactory { p -> AmethystClusterBlock(7, 3, p) }
    .properties { p ->
      p.lightLevel { 5 }
        .sound(SoundType.GLASS)
        .noOcclusion()
        .noCollission()
    }
    .transform { t ->
      t
        .loot(BlockLootPresets.dropSelfSilkLoot { AddonItems.HIMALAYAN_SALT.get() })
        .item()
        .model(ItemModelPresets.simpleLayerItem("${LibData.NAMES.HIMALAYAN_SALT}_cluster"))
        .build()
        .blockstate(BlockstatePresets.clusterCrossBlock())
    }
    .register()
  val RAW_HIMALAYAN_SALT = BLOCKS.create<Block>(LibData.NAMES.RAW_HIMALAYAN_SALT).copyFrom({ Blocks.QUARTZ_BLOCK })
    .color(MapColor.COLOR_MAGENTA)
    .toolAndTier(SetTool.PICKAXE.getTag(), SetTier.WOOD.getTag())
    .smallStorageBlock({ AddonItems.HIMALAYAN_SALT.get() }, {
      DataIngredient.tag(LibTags.forgeItemTag("ingots/himalayan_salt"))
    })
    .register()
  val HIMALAYAN_SALT_FAMILY = BLOCKS.createFamily(LibData.NAMES.HIMALAYAN_SALT).color(MapColor.COLOR_MAGENTA)
    .copyFrom { Blocks.QUARTZ_BLOCK }
    .toolAndTier(SetTool.PICKAXE.getTag(), SetTier.WOOD.getTag())
    .longBlockFamily(RAW_HIMALAYAN_SALT)

  // @ Minerals
  val PHOSPHORITE_FAMILY = BlockFamilyGen(LibData.NAMES.PHOSPHORITE, BLOCKS).color(MapColor.NETHER)
    .copyFrom { Blocks.GRANITE }
    .toolAndTier(SetTool.PICKAXE.getTag(), SetTier.WOOD.getTag())
    .denyList(BlockFamily.Type.BRICKS)
    .mineralFamily()

  val DRY_PATCHES = BLOCKS.create<CarpetBlock>("dry_patches").blockFactory { p ->
    CarpetBlock(p)
  }
    .copyFrom({ Blocks.MOSS_CARPET })
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_YELLOW)
        .sound(SoundType.MANGROVE_ROOTS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .transform { t ->
      t.blockstate(BlockstatePresets.carpetBlock("dry_patches"))
    }
    .register()
  val OCOTILLO: BlockEntry<GenericDoubleFlowerBlock> = BLOCKS.create<GenericDoubleFlowerBlock>("ocotillo").blockFactory { p ->
    GenericDoubleFlowerBlock(p) { b, _, _ ->
      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT)
    }
  }
    .copyFrom({ Blocks.TALL_GRASS })
    .color(MapColor.TERRACOTTA_ORANGE)
    .properties { p ->
      p.strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .transform { t ->
      t.blockstate(BlockstatePresets.tallPlantTopBlock("ocotillo"))
        .item()
        .model(ItemModelPresets.simpleLayerItem("ocotillo_top"))
        .build()
    }
    .loot(BlockLootPresets.dropDoubleFlowerLootLower())
    .register()
  val AGAVE_TOP: BlockEntry<GenericTallGrassTopBlock> = BLOCKS.create<GenericTallGrassTopBlock>("double_" + "agave").blockFactory { p ->
    GenericTallGrassTopBlock({ AGAVE.get() }, p) { b, _, _ ->
      b.`is`(BlockTags.SAND)
    }
  }
    .copyFrom({ Blocks.POPPY })
    .color(MapColor.TERRACOTTA_LIGHT_GREEN)
    .properties { p ->
      p.strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropOtherLoot { AGAVE.get() })
    .transform { t ->
      t.blockstate { c, p ->
        p.getVariantBuilder(c.get())
          .partialState()
          .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
          .setModels(*ConfiguredModel.builder()
            .modelFile(p.models()
              .withExistingParent(c.name + "_top", p.modLoc("block/cross_large"))
              .texture("cross", p.modLoc("block/" + "agave" + "_top"))
              .texture("particle", p.modLoc("block/" + "agave" + "_top"))
              .renderType("cutout_mipped"))
            .build())
          .partialState()
          .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
          .setModels(*ConfiguredModel.builder()
            .modelFile(p.models()
              .withExistingParent(c.name + "_bottom", p.mcLoc("block/template_wall_post"))
              .texture("wall", p.modLoc("block/" + "agave" + "_bottom"))
              .texture("particle", p.modLoc("block/" + "agave" + "_bottom"))
              .renderType("cutout_mipped"))
            .build())
      }
    }
    .noItem()
    .register()
  val AGAVE: BlockEntry<GenericTallGrassBlock> = BLOCKS.create<GenericTallGrassBlock>("agave").blockFactory { p ->
    GenericTallGrassBlock({ AGAVE_TOP.get() }, p) { b, _, _ ->
      b.`is`(BlockTags.SAND)
    }
  }
    .copyFrom({ Blocks.POPPY })
    .color(MapColor.TERRACOTTA_LIGHT_GREEN)
    .properties { p ->
      p.strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropItselfLoot())
    .transform { t ->
      t.blockstate(BlockstatePresets.tallPlantBottomBlock("agave"))
        .item()
        .model(ItemModelPresets.tallPlantBottomItem("agave", true))
        .build()
    }
    .register()
  val BUDDING_COTTON_CROP = AddonContent.REGISTRATE.block<BuddingCottonBlock>(LibData.PLANTS.BUDDING_COTTON) { props ->
    BuddingCottonBlock(props)
  }
    .initialProperties { Blocks.WHEAT }
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
        .sound(SoundType.CROP)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .blockstate { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          ConfiguredModel.builder()
            .modelFile(p.models()
              .withExistingParent(c.name + "_stage" + state.getValue(BuddingBushBlock.AGE), p.mcLoc("block/cross"))
              .texture("cross", p.modLoc("block/cotton/" + LibData.PLANTS.BUDDING_COTTON + "_stage" + state.getValue(BuddingBushBlock.AGE)))
              .texture("particle", p.modLoc("block/cotton/" + LibData.PLANTS.BUDDING_COTTON + "_stage" + state.getValue(BuddingBushBlock.AGE)))
              .renderType("cutout"))
            .build()
        }
    }
    .register()
  val COTTON_CROP = AddonContent.REGISTRATE.block<CottonCropBlock>(LibData.PLANTS.COTTON) { props ->
    CottonCropBlock(props)
  }
    .initialProperties { Blocks.WHEAT }
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
        .sound(SoundType.CROP)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .blockstate { c, p ->
      p.getVariantBuilder(c.get())
        .forAllStates { state ->
          ConfiguredModel.builder()
            .modelFile(p.models()
              .withExistingParent(c.name + "_stage" + state.getValue(CottonCropBlock.CROP_AGE), p.mcLoc("block/cross"))
              .texture("cross", p.modLoc("block/cotton/" + LibData.PLANTS.COTTON + "_stage" + state.getValue(CottonCropBlock.CROP_AGE)))
              .texture("particle", p.modLoc("block/cotton/" + LibData.PLANTS.COTTON + "_stage" + state.getValue(CottonCropBlock.CROP_AGE)))
              .renderType("cutout"))
            .build()
        }
    }
    .register()
  val SIMPLE_FLOWER = BLOCKS.create<GenericFlowerBlock>("simple_flower").blockFactory { p ->
    GenericFlowerBlock({ MobEffects.SATURATION }, 5, p)
  }
    .copyFrom({ Blocks.POPPY })
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
        .sound(SoundType.GRASS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .transform { t ->
      t
        .blockstate(BlockstatePresets.simpleCrossBlock("simple_flower"))
        .item()
        .model(ItemModelPresets.simpleLayerItem("simple_flower"))
        .build()
    }
    .register()
  val POTTED_SIMPLE_FLOWER = BLOCKS.create<FlowerPotBlock>("potted_simple_flower").blockFactory { p ->
    FlowerPotBlock({ Blocks.FLOWER_POT as FlowerPotBlock }, { SIMPLE_FLOWER.get() }, p)
  }
    .copyFrom({ Blocks.POTTED_POPPY })
    .noItem()
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
        .noOcclusion()
    }
    .loot(BlockLootPresets.pottedPlantLoot { SIMPLE_FLOWER.get() })
    .transform { t ->
      t.blockstate(BlockstatePresets.pottedPlantBlock("simple_flower"))
    }
    .register()
  val TALL_SPARSE_DRY_GRASS: BlockEntry<GenericDoublePlantBlock> = BLOCKS.create<GenericDoublePlantBlock>("tall_" + "sparse_dry_grass").blockFactory { p ->
    GenericDoublePlantBlock(p) { b, _, _ ->
      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT)
    }
  }
    .copyFrom({ Blocks.TALL_GRASS })
    .color(MapColor.TERRACOTTA_YELLOW)
    .properties { p ->
      p.strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropDoubleCropLoot({ Items.BEETROOT_SEEDS }, { Items.BEETROOT_SEEDS }))
    .transform { t ->
      t.blockstate(BlockstatePresets.tallPlantTopBlock("sparse_dry_grass"))
        .item()
        .model(ItemModelPresets.simpleLayerItem("sparse_dry_grass_top"))
        .build()
    }
    .register()
  val SPARSE_DRY_GRASS: BlockEntry<GenericTallGrassBlock> = BLOCKS.create<GenericTallGrassBlock>("sparse_dry_grass").blockFactory { p ->
    GenericTallGrassBlock({ TALL_SPARSE_DRY_GRASS.get() }, p) { b, _, _ ->
      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT)
    }
  }
    .copyFrom({ Blocks.TALL_GRASS })
    .color(MapColor.TERRACOTTA_YELLOW)
    .properties { p ->
      p.strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .transform { t ->
      t.blockstate(BlockstatePresets.tallPlantBottomBlock("sparse_dry_grass"))
        .item()
        .model(ItemModelPresets.tallPlantBottomItem("sparse_dry_grass", false))
        .build()
    }
    .loot(BlockLootPresets.dropCropLoot({ Items.BEETROOT_SEEDS }, { Items.BEETROOT_SEEDS }))
    .register()
  val DEAD_GRASS = BLOCKS.create<GenericGrassBlock>("dead_grass").blockFactory { p ->
    GenericGrassBlock(p, { b, _, _ ->
      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT) || b.`is`(BlockTags.TERRACOTTA)
    })
  }
    .copyFrom({ Blocks.GRASS })
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_YELLOW)
        .sound(SoundType.GRASS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropSelfSilkShearsLoot())
    .transform { t ->
      t.blockstate(BlockstatePresets.simpleCrossBlock("dead_grass"))
        .item()
        .model(ItemModelPresets.simpleLayerItem("dead_grass"))
        .build()
    }
    .register()
  val DRY_SHRUB = BLOCKS.create<GenericGrassBlock>("dry_shrub").blockFactory { p ->
    GenericGrassBlock(p, { b, _, _ ->
      b.`is`(BlockTags.SAND) || b.`is`(BlockTags.DIRT) || b.`is`(BlockTags.TERRACOTTA)
    }, true)
  }
    .copyFrom({ Blocks.GRASS })
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_YELLOW)
        .sound(SoundType.MANGROVE_ROOTS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropSelfSilkShearsOtherLoot({ Items.STICK }))
    .transform { t ->
      t.blockstate(BlockstatePresets.simpleCrossBlock("dry_shrub"))
        .item()
        .model(ItemModelPresets.simpleLayerItem("dry_shrub"))
        .build()
    }
    .register()

  val GUAYULE_SHRUB_TALL: BlockEntry<GuayuleShrubTopBlock> = AddonContent.REGISTRATE.block<GuayuleShrubTopBlock>("double_" + LibData.PLANTS.GUAYULE_SHRUB) { props ->
    GuayuleShrubTopBlock(props)
  }
    .initialProperties { Blocks.POPPY }
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
        .sound(SoundType.WET_GRASS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .loot(BlockLootPresets.dropDoubleFlowerLootUpper({ GUAYULE_SHRUB.get() }, 2))
    .blockstate { c, p ->
      p.getVariantBuilder(c.get())
        .partialState()
        .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
        .setModels(*ConfiguredModel.builder()
          .modelFile(p.models()
            .withExistingParent(c.name + "_top", p.mcLoc("block/cross"))
            .texture("cross", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB + "_top"))
            .texture("particle", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB + "_top"))
            .renderType("cutout_mipped"))
          .build())
        .partialState()
        .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
        .setModels(*ConfiguredModel.builder()
          .modelFile(p.models()
            .withExistingParent(c.name + "_bottom", p.mcLoc("block/cross"))
            .texture("cross", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB + "_bottom"))
            .texture("particle", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB + "_bottom"))
            .renderType("cutout_mipped"))
          .build())
    }
    .register()
  val GUAYULE_SHRUB: BlockEntry<GuayuleShrubBlock> = AddonContent.REGISTRATE.block<GuayuleShrubBlock>(LibData.PLANTS.GUAYULE_SHRUB) { props ->
    GuayuleShrubBlock(GUAYULE_SHRUB_TALL.get(), props)
  }
    .initialProperties { Blocks.POPPY }
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
        .sound(SoundType.WET_GRASS)
        .strength(0.0f)
        .randomTicks()
        .noCollission()
        .noOcclusion()
    }
    .blockstate { c, p ->
      p.getVariantBuilder(c.get())
        .partialState()
        .setModels(*ConfiguredModel.builder()
          .modelFile(p.models()
            .withExistingParent(c.name, p.mcLoc("block/cross"))
            .texture("cross", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB))
            .texture("particle", p.modLoc("block/" + LibData.PLANTS.GUAYULE_SHRUB))
            .renderType("cutout_mipped"))
          .build())
    }
    .loot(BlockLootPresets.dropItselfLoot())
    .item()
    .model { c, p ->
      p.withExistingParent(c.name, p.mcLoc("item/generated"))
        .texture("layer0", p.modLoc("item/" + LibData.PLANTS.GUAYULE_SHRUB))
    }
    .build()
    .register()

  val POTTED_GUAYULE_SHRUB = BLOCKS.create<FlowerPotBlock>("potted_guayule_shrub").blockFactory { p ->
    FlowerPotBlock({ Blocks.FLOWER_POT as FlowerPotBlock }, { GUAYULE_SHRUB.get() }, p)
  }
    .copyFrom({ Blocks.POTTED_POPPY })
    .noItem()
    .properties { p ->
      p.mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
        .noOcclusion()
    }
    .loot(BlockLootPresets.pottedPlantLoot { GUAYULE_SHRUB.get() })
    .transform { t ->
      t.blockstate(BlockstatePresets.pottedPlantBlock("guayule_shrub"))
    }
    .register()


  //  // Joshua stalk tree
  val JOSHUA_FAMILY = BLOCKS.createFamily(LibData.NAMES.JOSHUA).color(MapColor.COLOR_BROWN, MapColor.TERRACOTTA_LIGHT_BLUE)
    .toolAndTier(SetTool.AXE.getTag(), SetTier.WOOD.getTag())
    .stalkWoodFamily(AddonWoodTypes.JOSHUA, JoshuaTreeGrower()) { b, _, _ ->
      b.`is`(BlockTags.SAND)
    }
}