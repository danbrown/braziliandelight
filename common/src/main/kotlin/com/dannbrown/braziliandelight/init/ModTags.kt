package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.world.item.Items

object ModTags {
  object BLOCK {
    val SERENE_SEASONS_SPRING = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "spring_crops")
    val SERENE_SEASONS_SUMMER = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "summer_crops")
    val SERENE_SEASONS_AUTUMN = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "autumn_crops")
    val SERENE_SEASONS_WINTER = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "winter_crops")
    fun register() {
      // init
    }
  }

  object ITEM {
    val CHEESE_COAGULANT = DeltaboxUtil.TAGS.modItemTag(ModContent.MOD_ID, "is_cheese_coagulant")
    val COXINHA_FILLINGS = DeltaboxUtil.TAGS.modItemTag(ModContent.MOD_ID, "coxinha_fillings")
    val STROGANOFF_INGREDIENTS = DeltaboxUtil.TAGS.modItemTag(ModContent.MOD_ID, "stroganoff_ingredients")

    val CHEESE = DeltaboxUtil.TAGS.modloaderItemTag("cheese")
    val SALT = DeltaboxUtil.TAGS.modloaderItemTag("salt")
    val BUTTER = DeltaboxUtil.TAGS.modloaderItemTag("butter")

    val BEAN_PODS = DeltaboxUtil.TAGS.modloaderItemTag("bean_pods")
    val BEANS = DeltaboxUtil.TAGS.modloaderItemTag("beans")
    val GARLIC = DeltaboxUtil.TAGS.modloaderItemTag("garlic")
    val GUARANA = DeltaboxUtil.TAGS.modloaderItemTag("guarana")
    val ACAI = DeltaboxUtil.TAGS.modloaderItemTag("acai")
    val COCONUT = DeltaboxUtil.TAGS.modloaderItemTag("coconut")
    val CORN = DeltaboxUtil.TAGS.modloaderItemTag("corn")
    val KERNELS = DeltaboxUtil.TAGS.modloaderItemTag("kernels")
    val COLLARD_GREENS = DeltaboxUtil.TAGS.modloaderItemTag("collard_greens")
    val CASSAVA = DeltaboxUtil.TAGS.modloaderItemTag("cassava")
    val COFFEE_BEANS = DeltaboxUtil.TAGS.modloaderItemTag("coffee_beans")
    val COFFEE = DeltaboxUtil.TAGS.modloaderItemTag("coffee")
    val LEMON = DeltaboxUtil.TAGS.modloaderItemTag("lemon")
    val MILK = DeltaboxUtil.TAGS.modloaderItemTag("milk")
    fun register() {
      // init
    }
  }

  object BIOME {
    val HAS_LEMON_TREE = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_lemon_tree")
    val HAS_COCONUT_PALM_TREE = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_coconut_palm_tree")
    val HAS_ACAI_PALM_TREE = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_acai_palm_tree")
    val HAS_WILD_GARLIC = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_garlic")
    val HAS_WILD_COLLARD_GREENS = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_collard_greens")
    val HAS_WILD_COFFEE_BERRIES = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_coffee_berries")
    val HAS_WILD_CASSAVA = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_cassava")
    val HAS_WILD_CORN = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_corn")
    val HAS_WILD_GUARANA = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_guarana")
    val HAS_WILD_BEANS = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_wild_beans")
    val HAS_YERBA_MATE = DeltaboxUtil.TAGS.modBiomeTag(ModContent.MOD_ID, "has_yerba_mate")

    fun register() {
      // init
    }
  }

  object INGREDIENT {
    val CHEESE_INGREDIENT = REGISTRATE.comboItemTag("cheese")
    val SALT_INGREDIENT = REGISTRATE.comboItemTag("salt")
    val BUTTER_INGREDIENT = REGISTRATE.comboItemTag("butter")
    val RAW_CHICKEN = REGISTRATE.comboItemTag("raw_chicken")
    val RAW_BEEF = REGISTRATE.comboItemTag("raw_beef")
    val RAW_PORK = REGISTRATE.comboItemTag("raw_pork")
    val RAW_MUTTON = REGISTRATE.comboItemTag("raw_mutton")
    val WHEAT_DOUGH = REGISTRATE.comboItemTag("dough/wheat")
    val EGGS = REGISTRATE.comboItemTag("eggs")
    val MILK = REGISTRATE.comboItemTag("milk")
    val VEGETABLES_ONION = REGISTRATE.comboItemTag("vegetables/onion")
    val VEGETABLES_TOMATO = REGISTRATE.comboItemTag("vegetables/tomato")
    val VEGETABLES_CARROT = REGISTRATE.comboItemTag("vegetables/carrot")
    val TOMATO_SAUCE = REGISTRATE.comboItemTag("tomato_sauce")
    val COOKED_CHICKEN = REGISTRATE.comboItemTag("cooked_chicken")
    val COOKED_PORK = REGISTRATE.comboItemTag("cooked_pork")
    val PIE_CRUST = REGISTRATE.comboItemTag("pie_crust")
    val FRIED_EGG = REGISTRATE.comboItemTag("fried_egg")
    val COOKED_RICE = REGISTRATE.comboItemTag("fried_egg")
    val BACON = REGISTRATE.comboItemTag("bacon")
    val RAW_FISHES_COD = REGISTRATE.comboItemTag("raw_fishes/cod")


    fun register() {
      // init
    }
  }

  val KNIVES = REGISTRATE.itemTags(FarmersCompat.TAGS.KNIVES)
    .add(DeltaboxUtil.TAGS.modItemTag(FarmersCompat.MOD_ID, "tools/knives"))
    .add(*DeltaboxUtil.TAGS.modloaderItemTag("tools/knives").toTypedArray())
    .register()

  val HAS_LEMON_TREE = REGISTRATE.biomeTags(BIOME.HAS_LEMON_TREE)
    .add(BiomeTags.IS_FOREST)
    .register()

  // COCONUT PALM TREE
  val HAS_COCONUT_PALM_TREE = REGISTRATE.biomeTags(BIOME.HAS_COCONUT_PALM_TREE)
    .add(BiomeTags.IS_BEACH)
    .add(BiomeTags.IS_JUNGLE)
    .add(BiomeTags.IS_BADLANDS)
    .register()

  // ACAI PALM TREE
  val HAS_ACAI_PALM_TREE = REGISTRATE.biomeTags(BIOME.HAS_ACAI_PALM_TREE)
    .add(BiomeTags.IS_JUNGLE)
    .add(BiomeTags.HAS_SWAMP_HUT)
    .register()

  // WILD CROPS
  val HAS_WILD_GARLIC = REGISTRATE.biomeTags(BIOME.HAS_WILD_GARLIC)
    .add(BiomeTags.IS_HILL)
    .add(BiomeTags.IS_FOREST)
    .add(BiomeTags.IS_SAVANNA)
    .register()

  val HAS_WILD_COLLARD_GREENS = REGISTRATE.biomeTags(BIOME.HAS_WILD_COLLARD_GREENS)
    .add(BiomeTags.IS_TAIGA)
    .register()

  val HAS_WILD_COFFEE_BERRIES = REGISTRATE.biomeTags(BIOME.HAS_WILD_COFFEE_BERRIES)
    .add(BiomeTags.IS_JUNGLE)
    .add(BiomeTags.IS_TAIGA)
    .register()

  val HAS_WILD_CASSAVA = REGISTRATE.biomeTags(BIOME.HAS_WILD_CASSAVA)
    .add(BiomeTags.IS_JUNGLE)
    .add(BiomeTags.IS_SAVANNA)
    .register()

  val HAS_WILD_CORN = REGISTRATE.biomeTags(BIOME.HAS_WILD_CORN)
    .add(BiomeTags.IS_HILL)
    .add(BiomeTags.IS_FOREST)
    .register()

  val HAS_WILD_GUARANA = REGISTRATE.biomeTags(BIOME.HAS_WILD_GUARANA)
    .add(BiomeTags.IS_JUNGLE)
    .register()

  val HAS_WILD_BEANS = REGISTRATE.biomeTags(BIOME.HAS_WILD_BEANS)
    .add(BiomeTags.IS_FOREST)
    .add(BiomeTags.IS_MOUNTAIN)
    .register()

  val HAS_YERBA_MATE = REGISTRATE.biomeTags(BIOME.HAS_YERBA_MATE)
    .add(BiomeTags.IS_FOREST)
    .add(BiomeTags.IS_JUNGLE)
    .add(BiomeTags.IS_MOUNTAIN)
    .register()

  val COXINHA_FILLINGS = REGISTRATE.itemTags(ITEM.COXINHA_FILLINGS)
    .add(INGREDIENT.RAW_BEEF)
    .add(INGREDIENT.RAW_CHICKEN)
    .add(INGREDIENT.RAW_MUTTON)
    .add(INGREDIENT.RAW_PORK)
    .add({ Items.BROWN_MUSHROOM })
    .add({ Items.RABBIT })
    .register()

  val STROGANOFF_INGREDIENTS = REGISTRATE.itemTags(ITEM.STROGANOFF_INGREDIENTS)
    .add(INGREDIENT.RAW_BEEF)
    .add(INGREDIENT.RAW_CHICKEN)
    .add(INGREDIENT.RAW_MUTTON)
    .add({ Items.RABBIT })
    .register()

  val WHEAT_DOUGH = REGISTRATE.itemTags(INGREDIENT.WHEAT_DOUGH)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "wheat_dough")))
    .add(*DeltaboxUtil.TAGS.modloaderItemTag("dough").toTypedArray())
    .register()

  val TOMATO_SAUCE = REGISTRATE.itemTags(INGREDIENT.TOMATO_SAUCE)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "tomato_sauce")))
    .register()

  val VEGETABLES_CARROT = REGISTRATE.itemTags(INGREDIENT.VEGETABLES_CARROT)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("minecraft", "carrot")))
    .register()

  val VEGETABLES_ONION = REGISTRATE.itemTags(INGREDIENT.VEGETABLES_ONION)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "onion")))
    .register()

  val VEGETABLES_TOMATO = REGISTRATE.itemTags(INGREDIENT.VEGETABLES_TOMATO)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "tomato")))
    .register()

  val COOKED_CHICKEN = REGISTRATE.itemTags(INGREDIENT.COOKED_CHICKEN)
    .add({ Items.COOKED_CHICKEN })
    .register()
  val COOKED_PORK = REGISTRATE.itemTags(INGREDIENT.COOKED_PORK)
    .add({ Items.COOKED_PORKCHOP })
    .register()

  val PIE_CRUST = REGISTRATE.itemTags(INGREDIENT.PIE_CRUST)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "pie_crust")))
    .register()
  val FRIED_EGG = REGISTRATE.itemTags(INGREDIENT.FRIED_EGG)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "fried_egg")))
    .register()
  val COOKED_RICE = REGISTRATE.itemTags(INGREDIENT.COOKED_RICE)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "cooked_rice")))
    .register()
  val BACON = REGISTRATE.itemTags(INGREDIENT.BACON)
    .add(ResourceKey.create(Registries.ITEM, DeltaboxUtil.resourceLocation("farmersdelight", "bacon")))
    .register()
  val RAW_FISHES_COD = REGISTRATE.itemTags(INGREDIENT.RAW_FISHES_COD)
    .add({ Items.COD })
    .register()

  fun register() {
    BLOCK.register()
    ITEM.register()
    BIOME.register()
    INGREDIENT.register()
    // init
  }
}