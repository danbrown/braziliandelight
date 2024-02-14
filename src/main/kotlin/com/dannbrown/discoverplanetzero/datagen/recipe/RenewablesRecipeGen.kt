package com.dannbrown.discoverplanetzero.datagen.recipe

import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.discover.datagen.recipes.DiscoverRecipeGen
import com.dannbrown.discoverplanetzero.AddonContent
import com.simibubi.create.AllBlocks
import com.simibubi.create.AllFluids
import com.simibubi.create.AllItems
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes
import com.simibubi.create.content.processing.recipe.HeatCondition
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluids

class RenewablesRecipeGen(generator: DataGenerator) : DiscoverRecipeGen(generator, AddonContent.MOD_ID) {
  override val recipeName = "Renewables"

  // RENEWABLE BLOCKS PRODUCTION
  val ASURINE = create("asurine_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.TUFF)
          .require(Blocks.ANDESITE)
          .require(Fluids.LAVA, 100)
          .output(AllPaletteStoneTypes.ASURINE.baseBlock.get(), 1)
      }
  }
  val CRIMSITE = create("crimsite_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.TUFF)
          .require(Blocks.NETHERRACK)
          .require(Fluids.LAVA, 100)
          .output(AllPaletteStoneTypes.CRIMSITE.baseBlock.get(), 1)
      }
  }
  val OCHRUM = create("ochrum_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.TUFF)
          .require(Blocks.GRANITE)
          .require(Fluids.LAVA, 100)
          .output(AllPaletteStoneTypes.OCHRUM.baseBlock.get(), 1)
      }
  }
  val VERIDIUM = create("veridium_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.TUFF)
          .require(AllPaletteStoneTypes.LIMESTONE.baseBlock.get())
          .require(Fluids.LAVA, 100)
          .output(AllPaletteStoneTypes.VERIDIUM.baseBlock.get(), 1)
      }
  }
  val LIMESTONE = create("limestone_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.CALCITE)
          .require(Blocks.GRAVEL)
          .output(AllPaletteStoneTypes.LIMESTONE.baseBlock.get(), 1)
      }
  }
  val BASALT = create("basalt_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.COBBLED_DEEPSLATE)
          .require(Fluids.LAVA, 100)
          .requiresHeat(HeatCondition.HEATED)
          .output(Blocks.BASALT, 1)
      }
  }
  val DEEPSLATE = create("deepslate_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.COBBLESTONE)
          .require(Blocks.COBBLESTONE)
          .require(Blocks.COBBLESTONE)
          .require(Blocks.COBBLESTONE)
          .require(Fluids.LAVA, 100)
          .requiresHeat(HeatCondition.HEATED)
          .output(Blocks.DEEPSLATE, 1)
      }
  }
  val CALCITE = create("calcite_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.CLAY)
          .require(Blocks.COBBLESTONE)
          .require(Items.BONE_MEAL)
          .output(Blocks.CALCITE, 1)
      }
  }
  val TUFF = create("tuff_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.ANDESITE)
          .require(Items.FLINT)
          .require(Fluids.LAVA, 100)
          .output(Blocks.TUFF, 1)
      }
  }

  // VANILLA BLOCKS PRODUCTION
  val DIRT = create("dirt_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.GRAVEL)
          .require(LibTags.vanillaItemTag("leaves"))
          .output(Blocks.DIRT, 1)
      }
  }
  val ROOTED_DIRT = create("rooted_dirt_renewable") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.DIRT)
          .require(Blocks.HANGING_ROOTS)
          .require(Blocks.HANGING_ROOTS)
          .output(Blocks.ROOTED_DIRT, 1)
      }
  }
  val HANGING_ROOTS = crafting({ Blocks.HANGING_ROOTS }) { b ->
    b
      .shapeless(2, "", "_from_rooted_dirt", listOf(DataIngredient.items(Blocks.MANGROVE_ROOTS)))
  }
  val MOSS_BLOCK = create("moss_block_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Blocks.ROOTED_DIRT)
          .require(LibTags.vanillaItemTag("leaves"))
          .require(Fluids.WATER, 100)
          .output(Blocks.MOSS_BLOCK, 1)
      }
  }
  val BROWN_MUSHROOM_BLOCK = create("brown_mushroom_block_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Items.BROWN_MUSHROOM)
          .require(Items.BROWN_MUSHROOM)
          .require(Items.BROWN_MUSHROOM)
          .require(Items.BROWN_MUSHROOM)
          .output(Blocks.BROWN_MUSHROOM_BLOCK, 1)
      }
  }
  val RED_MUSHROOM_BLOCK = create("red_mushroom_block_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Items.RED_MUSHROOM)
          .require(Items.RED_MUSHROOM)
          .require(Items.RED_MUSHROOM)
          .require(Items.RED_MUSHROOM)
          .output(Blocks.RED_MUSHROOM_BLOCK, 1)
      }
  }
  val MUSHROOM_STEM_BLOCK = create("mushroom_stem_block_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Items.RED_MUSHROOM)
          .require(Items.RED_MUSHROOM)
          .require(Items.BROWN_MUSHROOM)
          .require(Items.BROWN_MUSHROOM)
          .output(Blocks.MUSHROOM_STEM, 1)
      }
  }
  val WATER = create("water_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .require(LibTags.vanillaItemTag("leaves"))
          .output(Fluids.WATER, 100)
      }
  }
  val MAGMA_BLOCK = create("magma_block_renewable") { b ->
    b
      .filling { b ->
        b
          .require(Blocks.GRAVEL)
          .require(Fluids.LAVA, 100)
          .output(Blocks.MAGMA_BLOCK, 1)
      }
  }
  val NETHERRACK = create("netherrack_renewable") { b ->
    b
      .haunting { b ->
        b
          .require(Blocks.CLAY)
          .output(Blocks.NETHERRACK, 1)
      }
  }
  val COARSE_DIRT = create("coarse_dirt_renewable") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.DIRT)
          .require(Blocks.GRAVEL)
          .output(Blocks.COARSE_DIRT, 2)
      }
  }
  val DIRT_FROM_COARSE = create("dirt_from_coarse_dirt_renewable") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.COARSE_DIRT)
          .require(Items.CLAY_BALL)
          .output(Blocks.DIRT, 1)
      }
  }
  val POWDER_SNOW = create("powder_snow_renewable") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.POWDER_SNOW)
          .require(Items.BUCKET)
          .output(Items.POWDER_SNOW_BUCKET, 1)
      }
  }
  val SNOW_LAYER = create("snow_layer_renewable") { b ->
    b
      .compacting { b ->
        b
          .require(Items.SNOWBALL)
          .require(Items.SNOWBALL)
          .output(Blocks.SNOW, 1)
      }
  }
  // RENEWABLE ITEMS PRODUCTION
  val COBWEB = crafting({ Items.COBWEB }) { b ->
    b
      .shaped(1, "", "_from_string") { c ->
        c
          .pattern("S S")
          .pattern(" S ")
          .pattern("S S")
          .define('S', Items.STRING)
      }
  }
  val ENCHANTED_GOLDEN_APPLE = create("enchanted_golden_apple") { b ->
    b
      .compacting { b ->
        b
          .require(Items.GOLDEN_APPLE)
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(LibTags.forgeItemTag("storage_blocks/gold"))
          .require(AllBlocks.EXPERIENCE_BLOCK.get())
          .output(Items.ENCHANTED_GOLDEN_APPLE, 1)
      }
  }
  val SUGAR_FROM_HONEY = create("sugar_from_honey") { b ->
    b
      .mixing { b ->
        b
          .require(AllFluids.HONEY.get(), 100)
          .requiresHeat(HeatCondition.valueOf("PASSIVEHEATED"))
          .output(Items.SUGAR, 3)
      }
  }
  val LEATHER_FROM_ROTTEN_FLESH = create("leather_from_rotten_flesh") { b ->
    b
      .compacting { b ->
        b
          .require(Items.ROTTEN_FLESH)
          .require(Items.ROTTEN_FLESH)
          .require(Items.ROTTEN_FLESH)
          .require(Items.ROTTEN_FLESH)
          .requiresHeat(HeatCondition.valueOf("PASSIVEHEATED"))
          .output(Items.LEATHER, 1)
      }
  }
  val ZINC_FROM_MUD = create("zinc_from_mud") { b ->
    b
      .splashing { b ->
        b
          .require(Blocks.MUD)
          .output(0.125f, AllItems.ZINC_NUGGET.get(), 3)
          .output(0.5f, Blocks.MANGROVE_ROOTS, 1)
      }
  }
  val ZINC_FROM_MUD_ROOTS = create("zinc_from_muddy_roots") { b ->
    b
      .splashing { b ->
        b
          .require(Blocks.MUDDY_MANGROVE_ROOTS)
          .output(0.125f, AllItems.ZINC_NUGGET.get(), 3)
          .output(0.5f, Blocks.MANGROVE_ROOTS, 2)
      }
  }
  val PAPER_FROM_BIRCH_LOG = create("paper_from_birch_log") { b ->
    b
      .cutting { b ->
        b
          .require(Blocks.BIRCH_LOG)
          .output(Blocks.STRIPPED_BIRCH_LOG, 1)
          .output(0.25f, Items.PAPER, 2)
      }
  }
  val PAPER_FROM_BIRCH_WOOD = create("paper_from_birch_wood") { b ->
    b
      .cutting { b ->
        b
          .require(Blocks.BIRCH_WOOD)
          .output(Blocks.STRIPPED_BIRCH_WOOD, 1)
          .output(0.45f, Items.PAPER, 2)
      }
  }
}