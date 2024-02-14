package com.dannbrown.discoverplanetzero.datagen.recipe

import com.dannbrown.discover.content.core.ProjectHeat
import com.dannbrown.discover.datagen.recipes.DiscoverRecipeGen
import com.dannbrown.discover.init.DiscoverFluids
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.dannbrown.discoverplanetzero.init.AddonItems
import com.simibubi.create.AllItems
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks

class AddonRecipeGen(generator: DataGenerator) : DiscoverRecipeGen(generator, AddonContent.MOD_ID) {
  override val recipeName: String = AddonContent.NAME


    val JOSHUA_WOOD_RECIPES = woodRecipesCompat(AddonBlocks.JOSHUA_FAMILY)

  val SILT_TO_GLASS = cooking({ DataIngredient.items(AddonBlocks.SILT.get()) }, { Blocks.GLASS }) { b ->
    b
      .prefix("silt_to_glass_")
      .inFurnace(100, 0.1f)
  }

  val ROSEATE_GRAINS_TO_GLASS =
    cooking({ DataIngredient.items(AddonBlocks.ROSEATE_GRAINS.get()) }, { Blocks.GLASS }) { b ->
      b
        .prefix("roseate_grains_to_glass_")
        .inFurnace(100, 0.1f)
    }


  val SAND_TO_SILT = create("sand_to_silt") { b ->
    b
      .milling { b ->
        b
          .require(Items.SAND)
          .duration(60)
          .output(AddonBlocks.SILT.get(), 1)
      }
      .crushing { b ->
        b
          .require(Items.SAND)
          .duration(60)
          .output(AddonBlocks.SILT.get(), 1)
      }
  }

  val HIMALAYAN_SALT_TO_MAGNESIUM = create("himalayan_salt_to_magnesium") { b ->
    b
      .milling { b ->
        b
          .require(AddonItems.HIMALAYAN_SALT.get())
          .duration(60)
          .output(AddonItems.MAGNESIUM_DUST.get(), 1)
          .output(AddonItems.SALT.get(), 1)
      }
      .crushing { b ->
        b
          .require(AddonItems.HIMALAYAN_SALT.get())
          .duration(60)
          .output(AddonItems.MAGNESIUM_DUST.get(), 1)
          .output(AddonItems.SALT.get(), 1)
      }
  }


  val SANDSTONE_PEBBLES_TO_SAND = create("sandstone_pebbles_to_sand") { b ->
    b
      .milling { b ->
        b
          .require(AddonBlocks.SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(Items.SAND, 1)
      }
      .crushing { b ->
        b
          .require(AddonBlocks.SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(Items.SAND, 1)
      }
  }

  val RED_SANDSTONE_PEBBLES_TO_SAND = create("red_sandstone_pebbles_to_sand") { b ->
    b
      .milling { b ->
        b
          .require(AddonBlocks.RED_SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(Items.RED_SAND, 1)
      }
      .crushing { b ->
        b
          .require(AddonBlocks.RED_SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(Items.RED_SAND, 1)
      }
  }

  val ROSEATE_SANDSTONE_PEBBLES_TO_SAND = create("roseate_sandstone_pebbles_to_sand") { b ->
    b
      .milling { b ->
        b
          .require(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(AddonBlocks.ROSEATE_GRAINS.get(), 1)
      }
      .crushing { b ->
        b
          .require(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get())
          .duration(60)
          .output(AddonBlocks.ROSEATE_GRAINS.get(), 1)
      }
  }

  val SANDSTONE_PEBBLES = create("sandstone_pebbles") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.SAND)
          .require(Blocks.SAND)
          .require(Blocks.GRAVEL)
          .require(Blocks.GRAVEL)
          .output(AddonBlocks.SANDSTONE_PEBBLES.get(), 4)
      }
  }
  val RED_SANDSTONE_PEBBLES = create("red_sandstone_pebbles") { b ->
    b
      .mixing { b ->
        b
          .require(Blocks.RED_SAND)
          .require(Blocks.RED_SAND)
          .require(Blocks.GRAVEL)
          .require(Blocks.GRAVEL)
          .output(AddonBlocks.RED_SANDSTONE_PEBBLES.get(), 4)
      }
  }
  val ROSEATE_SANDSTONE_PEBBLES = create("roseate_sandstone_pebbles") { b ->
    b
      .mixing { b ->
        b
          .require(AddonBlocks.ROSEATE_GRAINS.get())
          .require(AddonBlocks.ROSEATE_GRAINS.get())
          .require(Blocks.GRAVEL)
          .require(Blocks.GRAVEL)
          .output(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get(), 4)
      }
  }


  val DYES_FROM_OCOTILLO = create("dyes_from_ocotillo") { b ->
    b
      .milling { b ->
        b
          .require(AddonBlocks.OCOTILLO.get())
          .duration(60)
          .output(Items.ORANGE_DYE, 1)
      }
      .crushing { b ->
        b
          .require(AddonBlocks.OCOTILLO.get())
          .duration(60)
          .output(Items.ORANGE_DYE, 1)
          .output(0.50f, Items.RED_DYE, 1)
          .output(0.125f, Items.GREEN_DYE, 1)
      }
  }

  val STRING_FROM_COTTON_PULPS = crafting({ Items.STRING }) { b ->
    b
      .shaped(2, "", "_from_cotton_pulps") { c ->
        c
          .pattern("PPP")
          .define('P', AddonItems.COTTON_PULP.get())
      }
  }
  val ORANGE_DYE_FROM_OCOTILLO = crafting({ Items.ORANGE_DYE }) { b ->
    b
      .shapeless(1, "", "_from_ocotillo", listOf(DataIngredient.items(AddonBlocks.OCOTILLO.get())))
  }

  val SILICA_GRAINS_FROM_SILT = create("silica_grains_from_silt") { b ->
    b
      .splashing { b ->
        b
          .require(AddonBlocks.SILT.get())
          .output(0.05f, _DiscoverItems.SILICA_GRAINS.get(), 1)
          .output(0.125f, AllItems.COPPER_NUGGET.get(), 1)
          .output(0.5f, Items.CLAY_BALL, 1)
      }
  }
  val HIMALAYAN_SALT_FROM_ROSEATE_GRAINS = create("himalayan_salt_from_roseate_grains") { b ->
    b
      .splashing { b ->
        b
          .require(AddonBlocks.ROSEATE_GRAINS.get())
          .output(0.5f, AddonItems.HIMALAYAN_SALT.get(), 1)
          .output(0.5f, Items.CLAY_BALL, 1)
      }
  }


  val LATEX_FROM_GUAYULE_SHRUBS = create("latex_from_guayule_shrubs") { b ->
    b
      .compacting { b ->
        b
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .require(AddonBlocks.GUAYULE_SHRUB.get())
          .requiresHeat(ProjectHeat.PASSIVEHEATED)
          .output(DiscoverFluids.LATEX.get(), 100)
      }
  }
  val PAPER_FROM_COTTON_PULPS = create("paper_from_cotton_pulps") { b ->
    b
      .compacting { b ->
        b
          .require(AddonItems.COTTON_PULP.get())
          .require(AddonItems.COTTON_PULP.get())
          .require(AddonItems.COTTON_PULP.get())
          .require(AddonItems.COTTON_PULP.get())
          .output(Items.PAPER, 1)
      }
  }
  val SANDSTONE = create("sandstone_from_pebbles") { b ->
    b
      .compacting { b ->
        b
          .require(AddonBlocks.SANDSTONE_PEBBLES.get())
          .require(AddonBlocks.SANDSTONE_PEBBLES.get())
          .output(Blocks.SANDSTONE, 1)
      }
  }
  val RED_SANSTONE = create("red_sandstone_from_pebbles") { b ->
    b
      .compacting { b ->
        b
          .require(AddonBlocks.RED_SANDSTONE_PEBBLES.get())
          .require(AddonBlocks.RED_SANDSTONE_PEBBLES.get())
          .output(Blocks.RED_SANDSTONE, 1)
      }
  }
//  val ROSEATE_SANDSTONE = create("roseate_sandstone_from_pebbles") { b ->
//    b
//      .compacting { b ->
//        b
//          .require(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get())
//          .require(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get())
//          .output(AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get(), 1)
//      }
//  }
}