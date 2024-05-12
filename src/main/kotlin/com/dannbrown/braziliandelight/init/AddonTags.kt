package com.dannbrown.braziliandelight.init

import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.braziliandelight.AddonContent

object AddonTags {
  object BLOCK {
  }
  object ITEM {
    val CHEESE_COAGULANT = LibTags.modItemTag(AddonContent.MOD_ID, "is_cheese_coagulant")

    val CHEESE = LibTags.forgeItemTag("cheese")
    val SALT = LibTags.forgeItemTag("salt")
    val BUTTER = LibTags.forgeItemTag("butter")

    val BEAN_PODS = LibTags.forgeItemTag("bean_pods")
    val BEANS = LibTags.forgeItemTag("beans")
    val GARLIC = LibTags.forgeItemTag("garlic")
    val GUARANA = LibTags.forgeItemTag("guarana")
    val ACAI = LibTags.forgeItemTag("acai")
    val COCONUT = LibTags.forgeItemTag("coconut")
    val CORN = LibTags.forgeItemTag("corn")
    val COLLARD_GREENS = LibTags.forgeItemTag("collard_greens")
    val CASSAVA = LibTags.forgeItemTag("cassava")
    val COFFEE_BEANS = LibTags.forgeItemTag("coffee_beans")
    val COFFEE = LibTags.forgeItemTag("coffee")
    val LEMON = LibTags.forgeItemTag("lemon")
  }
  object BIOME {
    val HAS_LEMON_TREE = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_lemon_tree")
    val HAS_COCONUT_PALM_TREE = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_coconut_palm_tree")
    val HAS_ACAI_PALM_TREE = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_acai_palm_tree")
    val HAS_WILD_GARLIC = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_garlic")
    val HAS_WILD_COLLARD_GREENS = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_collard_greens")
    val HAS_WILD_COFFEE_BERRIES = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_coffee_berries")
    val HAS_WILD_CASSAVA = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_cassava")
    val HAS_WILD_CORN = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_corn")
    val HAS_WILD_GUARANA = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_guarana")
    val HAS_WILD_BEANS = LibTags.modBiomeTag(AddonContent.MOD_ID, "has_wild_beans")
  }
}