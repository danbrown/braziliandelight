package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import net.minecraft.tags.BiomeTags

object ModTags {
  object BLOCK {
    val SERENE_SEASONS_SPRING = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "spring_crops")
    val SERENE_SEASONS_SUMMER = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "summer_crops")
    val SERENE_SEASONS_AUTUMN = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "autumn_crops")
    val SERENE_SEASONS_WINTER = DeltaboxUtil.TAGS.modBlockTag("sereneseasons", "winter_crops")
  }

  object ITEM {
    val CHEESE_COAGULANT = DeltaboxUtil.TAGS.modItemTag(ModContent.MOD_ID, "is_cheese_coagulant")

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
  }

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

  fun register() {
    // init
  }
}