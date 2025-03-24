package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.advancements.RequirementsStrategy
import net.minecraft.world.item.Items

object ModAdvancements {
  val BACKGROUND = DeltaboxUtil.resourceLocation(ModContent.MOD_ID, "textures/block/lemon_leaves.png")

  private val SAPLINGS = arrayOf(
    ModBlocks.LEMON_SAPLING,
    ModBlocks.ACAI_PALM_SAPLING,
    ModBlocks.COCONUT_PALM_SAPLING
  )

  private val SEEDS = arrayOf(
    ModBlocks.COLLARD_GREENS_CROP,
    ModBlocks.GARLIC_CROP,
    ModBlocks.BLACK_BEANS_CROP,
    ModBlocks.CARIOCA_BEANS_CROP,
    ModBlocks.BUDDING_CORN,
    ModBlocks.BUDDING_CASSAVA,
    ModBlocks.BUDDING_COFFEE,
    ModBlocks.BUDDING_WHITE_CORN
  )

  val ROOT_ADVANCEMENT =
    REGISTRATE.advancement(
      "root",
      "Brazilian Delight",
      "Welcome to Brazilian Delight!"
    ) { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.BRAZIL_FLAG.get(), k, BACKGROUND),
        k,
        RequirementsStrategy.OR,
        *SAPLINGS.map { it.getItem() }.toTypedArray(),
        *SEEDS.map { it.getItem() }.toTypedArray(),
      )
    }
  val TROPICAL_SEEDS =
    REGISTRATE.advancement(
      "tropical_seeds",
      "Tropical Seeds",
      "Obtain any seed from Brazilian Delight"
    ) { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModBlocks.BUDDING_GUARANA.getItem(), k).parent(ROOT_ADVANCEMENT),
        k,
        RequirementsStrategy.OR,
        *SEEDS.map { it.getItem() }.toTypedArray()
      )
    }
  val TROPICAL_SAPLINGS = REGISTRATE.advancement(
    "tropical_saplings",
    "Tropical Saplings",
    "Obtain any sapling from Brazilian Delight"
  )
  { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.COCONUT_PALM_SAPLING.getItem(), k).parent(ROOT_ADVANCEMENT),
      k,
      RequirementsStrategy.OR,
      *SAPLINGS.map { it.getItem() }.toTypedArray()
    )

  }
  val GARLIC_CROP = REGISTRATE.advancement(
    "garlic_crop",
    "Bite Back!",
    "Plant and harvest Garlic"
  )
  { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.GARLIC_BULB.get(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModItems.GARLIC_BULB.get()
    )
  }
  val BEANS_CROP =
    REGISTRATE.advancement(
      "beans_crop",
      "Bean There, Done That!",
      "Plant and harvest Beans"
    ) { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.BEAN_POD.get(), k).parent(TROPICAL_SEEDS),
        k,
        RequirementsStrategy.OR,
        ModItems.BEAN_POD.get()
      )
    }
  val CORN_CROP = REGISTRATE.advancement(
    "corn_crop",
    "Poppin' Corn!",
    "Plant and harvest Corn"
  ) { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.CORN.get(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModItems.CORN.get()
    )
  }
  val CASSAVA_CROP = REGISTRATE.advancement(
    "cassava_crop",
    "Root Revival!",
    "Plant and harvest Cassava"
  ) { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.BUDDING_CASSAVA.getItem(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModBlocks.BUDDING_CASSAVA.getItem()
    )
  }
  val COFFEE_CROP = REGISTRATE.advancement(
    "coffee_crop",
    "Brew Awakening!",
    "Plant and harvest Coffee"
  ) { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.COFFEE_BERRIES.get(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModItems.COFFEE_BERRIES.get()
    )
  }
  val GUARANA_CROP = REGISTRATE.advancement("guarana_crop", "Energy Boost!", "Plant and harvest Guarana") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.GUARANA_FRUIT.get(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModItems.GUARANA_FRUIT.get()
    )
  }
  val COLLARD_GREENS_CROP =
    REGISTRATE.advancement("collard_greens_crop", "Green and Clean!", "Plant and harvest Collard Greens") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.COLLARD_GREENS.get(), k).parent(TROPICAL_SEEDS),
        k,
        RequirementsStrategy.OR,
        ModItems.COLLARD_GREENS.get()
      )

    }
  val WHITE_KERNELS_SPECIAL =
    REGISTRATE.advancement("white_kernels_crop", "White Gold", "Plant and harvest White Kernels") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModBlocks.BUDDING_WHITE_CORN.getItem(), k).parent(TROPICAL_SEEDS),
        k,
        RequirementsStrategy.OR,
        ModBlocks.BUDDING_WHITE_CORN.getItem()
      )
    }
  val GARLIC_REPUGNANT = REGISTRATE.advancement(
    "garlic_repugnant",
    "Bad Breath!",
    "Use a Garlic Bulb to craft a Repugnant Arrow"
  )
  { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.REPUGNANT_ARROW.get(), k).parent(GARLIC_CROP),
      k,
      RequirementsStrategy.OR,
      ModItems.REPUGNANT_ARROW.get()
    )

  }
  val FEIJOADA_CRAFT = REGISTRATE.advancement("feijoada_craft", "Feijoada Feast", "Craft Feijoada")
  { k, u, b ->

    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.FEIJOADA_POT.getItem(), k).parent(BEANS_CROP),
      k,
      RequirementsStrategy.OR,
      ModBlocks.FEIJOADA_POT.getItem()
    )
  }
  val GREEN_SOUP_CRAFT =
    REGISTRATE.advancement("green_soup_craft", "Verdant Delights", "Craft Green Soup") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModBlocks.GREEN_SOUP_POT.getItem(), k).parent(BEANS_CROP),
        k,
        RequirementsStrategy.OR,
        ModBlocks.GREEN_SOUP_POT.getItem()
      )

    }
  val COUSCOUS_CRAFT = REGISTRATE.advancement("couscous_craft", "Couscous is Ready!", "Craft Couscous") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.COUSCOUS.get(), k).parent(CORN_CROP),
      k,
      RequirementsStrategy.OR,
      ModItems.COUSCOUS.get()
    )
  }
  val GUARANA_DRINK_CRAFT =
    REGISTRATE.advancement("guarana_drink_craft", "Como Refresca!", "Craft Guarana Soda") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.GUARANA_SODA.get(), k).parent(GUARANA_CROP),
        k,
        RequirementsStrategy.OR,
        ModItems.GUARANA_SODA.get()
      )
    }
  val LEMON_ITEM = REGISTRATE.advancement("lemon_item", "If Life Gives You Lemons...", "Obtain a Lemon") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.LEMON.get(), k).parent(TROPICAL_SAPLINGS),
      k,
      RequirementsStrategy.OR,
      ModItems.LEMON.get()
    )
  }
  val ACAI_BERRY_ITEM = REGISTRATE.advancement("acai_berry_item", "Acai Delight", "Obtain an Acai Berry") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.BUDDING_ACAI_BRANCH.getItem(), k).parent(TROPICAL_SAPLINGS),
      k,
      RequirementsStrategy.OR,
      ModBlocks.BUDDING_ACAI_BRANCH.getItem()
    )
  }
  val COCONUT_ITEM = REGISTRATE.advancement("coconut_item", "Solid as a Rock!", "Obtain a Coconut") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.COCONUT.getItem(), k).parent(TROPICAL_SAPLINGS),
      k,
      RequirementsStrategy.OR,
      ModBlocks.COCONUT.getItem()
    )
  }
  val FISH_MOQUECA_CRAFT =
    REGISTRATE.advancement("fish_moqueca_craft", "Moqueca Masterpiece", "Craft Fish Moqueca") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModBlocks.FISH_MOQUECA_POT.getItem(), k).parent(LEMON_ITEM),
        k,
        RequirementsStrategy.OR,
        ModBlocks.FISH_MOQUECA_POT.getItem()
      )
    }
  val COCONUT_CREAM_CRAFT =
    REGISTRATE.advancement("coconut_cream_craft", "Creamy Coconut", "Craft Coconut Cream") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.COCONUT_CREAM.get(), k).parent(COCONUT_ITEM),
        k,
        RequirementsStrategy.OR,
        ModItems.COCONUT_CREAM.get()
      )
    }
  val ACAI_CREAM_CRAFT =
    REGISTRATE.advancement("acai_cream_craft", "Tropical Harmony", "Craft Acai Cream") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.ACAI_CREAM.get(), k).parent(ACAI_BERRY_ITEM),
        k,
        RequirementsStrategy.OR,
        ModItems.ACAI_CREAM.get()
      )
    }
  val SALT_BUCKET_CRAFT = REGISTRATE.advancement(
    "salt_bucket_craft",
    "Salty Reserve",
    "Obtain a Salt Bucket by evaporating water"
  )
  { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.SALT_BUCKET.get(), k).parent(ROOT_ADVANCEMENT),
      k,
      RequirementsStrategy.AND,
      ModItems.SALT_BUCKET.get()
    )
  }
  val CONDENSED_MILK_CRAFT =
    REGISTRATE.advancement("condensed_milk_craft", "Sweet Essence", "Craft Condensed Milk")
    { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.CONDENSED_MILK.get(), k).parent(HEAVY_CREAM_POT_ADVANCEMENT),
        k,
        RequirementsStrategy.AND,
        ModItems.CONDENSED_MILK.get()
      )
    }

  val BRIGADEIRO_CRAFT =
    REGISTRATE.advancement("brigadeiro_craft", "Chocolate Treasure", "Craft Brigadeiro") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.BRIGADEIRO_CREAM.get(), k).parent(CONDENSED_MILK_CRAFT),
        k,
        RequirementsStrategy.AND,
        ModItems.BRIGADEIRO_CREAM.get()
      )
    }
  val PUDDING_CRAFT = REGISTRATE.advancement("pudding_craft", "Festive Dessert", "Craft Pudding") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModBlocks.PUDDING.getItem(), k).parent(CONDENSED_MILK_CRAFT),
      k,
      RequirementsStrategy.AND,
      ModBlocks.PUDDING.getItem()
    )
  }
  val MILK_POT_ADVANCEMENT = REGISTRATE.advancement(
    "milk_pot",
    "The Milky Way",
    "Place milk in a cooking pot, and now try to mix it!",
    { k, u, b ->
      u.usedOnBlockCriterion(
        u.basicAdvancement(Items.MILK_BUCKET, k).parent(ROOT_ADVANCEMENT),
        k,
        RequirementsStrategy.AND,
        mapOf(Items.MILK_BUCKET to ModBlocks.MILK_POT.get())
      )
    }
  )
  val HEAVY_CREAM_POT_ADVANCEMENT = REGISTRATE.advancement(
    "heavy_cream_pot",
    "And it condenses",
    "Obtain a Heavy Cream Bucket from mixing milk",
    { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.HEAVY_CREAM_BUCKET.get(), k).parent(
          MILK_POT_ADVANCEMENT
        ),
        k,
        RequirementsStrategy.AND,
        ModItems.HEAVY_CREAM_BUCKET.get()
      )
    }
  )
  val CHEESE_MAKING = REGISTRATE.advancement(
    "cheese_making",
    "Artisan Cheese",
    "Mix Salt and Lemon with Heavy Cream in a cooking pot to make cheese!"
  )
  { k, u, b ->
    u.usedOnBlockCriterion(
      u.basicAdvancement(ModBlocks.MINAS_CHEESE.getItem(), k).parent(HEAVY_CREAM_POT_ADVANCEMENT),
      k,
      RequirementsStrategy.OR,
      mapOf(
        ModItems.LEMON.get() to ModBlocks.HEAVY_CREAM_POT.get(),
        ModItems.SALT.get() to ModBlocks.HEAVY_CREAM_POT.get()
      )
    )
  }
  val CHEESE_BREAD_CRAFT = REGISTRATE.advancement("cheese_bread_craft", "Uai so!", "Craft Cheese Bread") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.CHEESE_BREAD.get(), k).parent(CHEESE_MAKING),
      k,
      RequirementsStrategy.AND,
      ModItems.CHEESE_BREAD.get()
    )
  }

  val CASSAVA_FRITTERS_CRAFT =
    REGISTRATE.advancement("cassava_fritters_craft", "Savory Roots", "Craft Cassava Fritters") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.CASSAVA_FRITTERS.get(), k).parent(CASSAVA_CROP),
        k,
        RequirementsStrategy.OR,
        ModItems.CASSAVA_FRITTERS.get()
      )
    }
  val FRIED_FISH_WITH_ACAI_CRAFT =
    REGISTRATE.advancement("fried_fish_with_acai_craft", "Amazonian Feast", "Craft Fried Fish with Acai") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.FRIED_FISH_WITH_ACAI.get(), k).parent(
          ACAI_BERRY_ITEM
        ),
        k,
        RequirementsStrategy.OR,
        ModItems.FRIED_FISH_WITH_ACAI.get()
      )
    }
  val COXINHA_CRAFT =
    REGISTRATE.advancement("coxinha_craft", "Aquele salgado é de quê?", "Craft Coxinha") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.COXINHA.get(), k).parent(CASSAVA_CROP),
        k,
        RequirementsStrategy.OR,
        ModItems.COXINHA.get()
      )
    }
  val COCONUT_DRINK_CRAFT =
    REGISTRATE.advancement("coconut_drink_craft", "Samba Sip", "Craft Coconut Drink") { k, u, b ->
      u.hasItemsCriterion(
        u.basicAdvancement(ModItems.COCONUT_DRINK.get(), k).parent(COCONUT_ITEM),
        k,
        RequirementsStrategy.OR,
        ModItems.COCONUT_DRINK.get()
      )
    }
  val YERBA_MATE_CROP = REGISTRATE.advancement("yerba_mate_crop", "Mate Harvest", "Obtain Yerba Mate") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.YERBA_MATE_LEAVES.get(), k).parent(TROPICAL_SEEDS),
      k,
      RequirementsStrategy.OR,
      ModItems.YERBA_MATE_LEAVES.get()
    )
  }
  val CHIMARRAO_CRAFT = REGISTRATE.advancement("chimarrao_craft", "Gaucho Brew", "Craft Chimarrao") { k, u, b ->
    u.hasItemsCriterion(
      u.basicAdvancement(ModItems.CHIMARRAO.get(), k).parent(YERBA_MATE_CROP),
      k,
      RequirementsStrategy.OR,
      ModItems.CHIMARRAO.get()
    )
  }


  fun register() {
    // init
  }
}