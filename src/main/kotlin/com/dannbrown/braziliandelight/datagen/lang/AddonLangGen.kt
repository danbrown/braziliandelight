package com.dannbrown.braziliandelight.datagen.lang

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.block.PlaceableFoodBlock
import com.dannbrown.braziliandelight.datagen.advancements.AddonAdvancementsProvider
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.lib.AddonNames
import vectorwing.farmersdelight.FarmersDelight

object AddonLangGen {
  fun addStaticLangs(doRun: Boolean) {
    if (!doRun) return // avoid running in the server-side
    //
    // Creative tabs
    AddonContent.REGISTRATE.addCreativeTabLang(AddonCreativeTabs.CREATIVE_TAB_KEY, AddonContent.NAME)

    // Items
    AddonContent.REGISTRATE.addItemTooltipLang(AddonNames.COFFEE_BEANS, "Work in progress")
    AddonContent.REGISTRATE.addItemTooltipLang(AddonNames.COFFEE_BERRIES, "Work in progress")
    AddonContent.REGISTRATE.addItemTooltipLang(AddonNames.WHITE_KERNELS, "Work in progress")
    AddonContent.REGISTRATE.addItemTooltipLang(AddonNames.REPUGNANT_ARROW, "Nobody wants to be near this")
    AddonContent.REGISTRATE.addRawLang(PlaceableFoodBlock.WRONG_ITEM_KEY, "You need a %s to eat this.")
    AddonContent.REGISTRATE.addRawLang(FarmersDelight.MODID + ".tooltip." + AddonNames.COCONUT_MILK, "Clears 1 Effect")

    // Potions
    AddonContent.REGISTRATE.addEffectLang(AddonNames.REPUGNANT, "Repugnant")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.REPUGNANT, "Repugnant")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONG_REPUGNANT, "Repugnant II")
    AddonContent.REGISTRATE.addPotionLang(AddonNames.STRONGER_REPUGNANT, "Repugnant III")

    // Painting Variants
    AddonContent.REGISTRATE.addPaintingVariantLang(AddonNames.BRAZILIAN_MIKU, "Brazilian Miku", "Arbeet")

    // Advancements
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.ROOT_ADVANCEMENT_KEY, "Brazilian Delight", "Welcome to Brazilian Delight!")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.TROPICAL_SEEDS_KEY, "Tropical Seeds", "Obtain any seed from Brazilian Delight")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.TROPICAL_SAPLINGS_KEY, "Tropical Saplings", "Obtain any sapling from Brazilian Delight")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.GARLIC_CROP_KEY, "Bite Back!", "Plant and harvest Garlic")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.BEANS_CROP_KEY, "Bean There, Done That!", "Plant and harvest Beans")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CORN_CROP_KEY, "Poppin' Corn!", "Plant and harvest Corn")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CASSAVA_CROP_KEY, "Root Revival!", "Plant and harvest Cassava")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COFFEE_CROP_KEY, "Brew Awakening!", "Plant and harvest Coffee")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.GUARANA_CROP_KEY, "Energy Boost!", "Plant and harvest Guarana")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COLLARD_GREENS_CROP_KEY, "Green and Clean!", "Plant and harvest Collard Greens")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.WHITE_KERNELS_SPECIAL_KEY, "White Gold", "Plant and harvest White Kernels")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.GARLIC_REPUGNANT_KEY, "Bad Breath!", "Use a Garlic Bulb to craft a Repugnant Arrow")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.FEIJOADA_CRAFT_KEY, "Feijoada Feast", "Craft Feijoada")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.GREEN_SOUP_CRAFT_KEY, "Verdant Delights", "Craft Green Soup")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COUSCOUS_CRAFT_KEY, "Couscous is Ready!", "Craft Couscous")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.GUARANA_DRINK_CRAFT_KEY, "Como Refresca!", "Craft Guarana Soda")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.LEMON_ITEM_KEY, "If Life Gives You Lemons...", "Obtain a Lemon")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.ACAI_BERRY_ITEM_KEY, "Acai Delight", "Obtain an Acai Berry")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COCONUT_ITEM_KEY, "Solid as a Rock!", "Obtain a Coconut")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.FISH_MOQUECA_CRAFT_KEY, "Moqueca Masterpiece", "Craft Fish Moqueca")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COCONUT_CREAM_CRAFT_KEY, "Creamy Coconut", "Craft Coconut Cream")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.ACAI_CREAM_CRAFT_KEY, "Tropical Harmony", "Craft Acai Cream")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.SALT_BUCKET_CRAFT_KEY, "Salty Reserve", "Obtain a Salt Bucket by evaporating water")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CONDENSED_MILK_CRAFT_KEY, "Sweet Essence", "Craft Condensed Milk")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.BRIGADEIRO_CRAFT_KEY, "Chocolate Treasure", "Craft Brigadeiro")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.PUDDING_CRAFT_KEY, "Festive Dessert", "Craft Pudding")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.MILK_POT_ADVANCEMENT_KEY, "The Milky Way", "Place milk in a cooking pot, and now try to mix it!")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.HEAVY_CREAM_POT_ADVANCEMENT_KEY, "And it condenses", "Obtain a Heavy Cream Bucket from mixing milk")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CHEESE_MAKING_KEY, "Artisan Cheese", "Mix Salt and Lemon with Heavy Cream in a cooking pot to make cheese!")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CHEESE_BREAD_CRAFT_KEY, "Uai so!", "Craft Cheese Bread")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CASSAVA_FRITTERS_CRAFT_KEY, "Savory Roots", "Craft Cassava Fritters")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.FRIED_FISH_WITH_ACAI_CRAFT_KEY, "Amazonian Feast", "Craft Fried Fish with Acai")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COXINHA_CRAFT_KEY, "Aquele salgado é de quê?", "Craft Coxinha")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.COCONUT_DRINK_CRAFT_KEY, "Samba Sip", "Craft Coconut Drink")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.YERBA_MATE_CROP_KEY, "Mate Harvest", "Obtain Yerba Mate")
    AddonContent.REGISTRATE.addAdvancementLang(AddonAdvancementsProvider.CHIMARRAO_CRAFT_KEY, "Gaucho Brew", "Craft Chimarrao")
  }

}