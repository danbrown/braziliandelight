package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.item.CustomDrinkItem
import com.dannbrown.braziliandelight.content.item.CustomFoodItem
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.databoxlib.registry.generators.ItemGen
import com.tterrag.registrate.util.entry.ItemEntry
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus

object AddonItems {
  fun register(modBus: IEventBus) {}

  val ITEMS = ItemGen(AddonContent.REGISTRATE)
  fun foodItem(food: FoodProperties): Item.Properties {
    return Item.Properties().food(food)
  }

  fun bowlFoodItem(food: FoodProperties): Item.Properties {
    return Item.Properties()
      .food(food)
      .craftRemainder(Items.BOWL)
      .stacksTo(16)
  }

  fun drinkItem(food: FoodProperties): Item.Properties {
    return Item.Properties()
      .food(food)
      .craftRemainder(Items.GLASS_BOTTLE)
      .stacksTo(16)
  }

  fun stickItem(food: FoodProperties): Item.Properties {
    return Item.Properties()
      .food(food)
      .craftRemainder(Items.STICK)
      .stacksTo(16)
  }

  val BRAZIL_FLAG = ITEMS.simpleItem(AddonNames.BRAZIL_FLAG, { p -> Item(p) } )

  val BEAN_POD = ITEMS.simpleItem(AddonNames.BEAN_POD, { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEAN_PODS )
  val BLACK_BEANS: ItemEntry<Item> = ITEMS.simpleItem(AddonNames.BLACK_BEANS, { p -> ItemNameBlockItem(AddonBlocks.BUDDING_BEANS_CROP.get(), foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEANS )
  val CARIOCA_BEANS: ItemEntry<Item> = ITEMS.simpleItem(AddonNames.CARIOCA_BEANS, { p -> ItemNameBlockItem(AddonBlocks.BUDDING_BEANS_CROP.get(), foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEANS )

  val GARLIC_BULB = ITEMS.simpleItem(AddonNames.GARLIC_BULB, { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) }, AddonTags.ITEM.GARLIC )
  val GARLIC_CLOVE = ITEMS.simpleItem(AddonNames.GARLIC_CLOVE, { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) } )

  val ACAI_BERRIES = ITEMS.simpleItem(AddonNames.ACAI_BERRIES, { p -> CustomFoodItem(foodItem(AddonFoodValues.ACAI)) }, AddonTags.ITEM.ACAI )
  val GUARANA_FRUIT = ITEMS.simpleItem(AddonNames.GUARANA_FRUIT, { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA), true) }, AddonTags.ITEM.GUARANA )
  val GUARANA_POWDER = ITEMS.simpleItem(AddonNames.GUARANA_POWDER, { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA_POWDER), true) } )

  val GREEN_COCONUT = ITEMS.simpleItem(AddonNames.GREEN_COCONUT, { p -> Item(p) } )
  val COCONUT = ITEMS.simpleItem(AddonNames.COCONUT, { p -> Item(p) }, AddonTags.ITEM.COCONUT )
  val COCONUT_SLICE = ITEMS.simpleItem(AddonNames.COCONUT_SLICE, { p -> Item(p) } )

  val CORN = ITEMS.simpleItem(AddonNames.CORN, { p -> Item(p) }, AddonTags.ITEM.CORN )
  val COOKED_CORN = ITEMS.simpleItem(AddonNames.COOKED_CORN, { p -> Item(p) } )
  val CORN_GRAINS = ITEMS.simpleItem(AddonNames.CORN_GRAINS, { p -> Item(p) } )
  val CORN_FLOUR = ITEMS.simpleItem(AddonNames.CORN_FLOUR, { p -> Item(p) } )

  val CASSAVA_ROOT = ITEMS.simpleItem(AddonNames.CASSAVA_ROOT, { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA), true) }, AddonTags.ITEM.CASSAVA )
  val CASSAVA_FLOUR = ITEMS.simpleItem(AddonNames.CASSAVA_FLOUR, { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FLOUR)) } )

  val COLLARD_GREENS = ITEMS.simpleItem(AddonNames.COLLARD_GREENS, { p -> CustomFoodItem(foodItem(AddonFoodValues.COLLARD_GREENS)) }, AddonTags.ITEM.COLLARD_GREENS )

  val COFFEE_BERRIES = ITEMS.simpleItem(AddonNames.COFFEE_BERRIES, { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BERRIES)) } )
  val COFFEE_BEANS = ITEMS.simpleItem(AddonNames.COFFEE_BEANS, { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BEANS), true) }, AddonTags.ITEM.COFFEE_BEANS, AddonTags.ITEM.COFFEE )

  val LEMON = ITEMS.simpleItem(AddonNames.LEMON, { p -> Item(p) }, AddonTags.ITEM.LEMON )

  val SALT = ITEMS.simpleItem(AddonNames.SALT, { p -> Item(p) }, AddonTags.ITEM.SALT)
  val BUTTER = ITEMS.simpleItem(AddonNames.BUTTER, { p -> CustomFoodItem(foodItem(AddonFoodValues.BUTTER)) }, AddonTags.ITEM.BUTTER)
  val HEAVY_CREAM_BUCKET = ITEMS.simpleItem(AddonNames.HEAVY_CREAM_BUCKET, { p -> CustomFoodItem(p.stacksTo(1)) } )
  val CONDENSED_MILK = ITEMS.simpleItem(AddonNames.CONDENSED_MILK, { p -> CustomFoodItem(p.craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)) } )

  val MINAS_CHEESE_SLICE = ITEMS.simpleItem(AddonNames.MINAS_CHEESE_SLICE, { p -> CustomFoodItem(foodItem(AddonFoodValues.MINAS_CHEESE_SLICE)) }, AddonTags.ITEM.CHEESE)
  val MINAS_CHEESE_ON_A_STICK = ITEMS.handheldItem(AddonNames.MINAS_CHEESE_ON_A_STICK, { p -> CustomFoodItem(stickItem(AddonFoodValues.MINAS_CHEESE_ON_A_STICK)) } )
  val GRILLED_CHEESE_ON_A_STICK = ITEMS.handheldItem(AddonNames.GRILLED_CHEESE_ON_A_STICK, { p -> CustomFoodItem(stickItem(AddonFoodValues.GRILLED_CHEESE_ON_A_STICK)) } )

  val CARROT_CAKE_SLICE = ITEMS.simpleItem(AddonNames.CARROT_CAKE_SLICE, { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_SLICE)) } )
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = ITEMS.simpleItem(AddonNames.CARROT_CAKE_WITH_CHOCOLATE_SLICE, { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_WITH_CHOCOLATE_SLICE)) } )
  val CHICKEN_POT_PIE_SLICE = ITEMS.simpleItem(AddonNames.CHICKEN_POT_PIE_SLICE, { p -> CustomFoodItem(foodItem(AddonFoodValues.CHICKEN_POT_PIE_SLICE)) } )
  val PUDDING_SLICE = ITEMS.simpleItem(AddonNames.PUDDING_SLICE, { p -> CustomFoodItem(foodItem(AddonFoodValues.PUDDING_SLICE)) } )

  val PASTEL_DOUGH = ITEMS.simpleItem(AddonNames.PASTEL_DOUGH, { p -> Item(p) } )

  val SHRIMP = ITEMS.simpleItem(AddonNames.SHRIMP, { p -> CustomFoodItem(foodItem(AddonFoodValues.SHRIMP)) } )
  val COOKED_SHRIMP = ITEMS.simpleItem(AddonNames.COOKED_SHRIMP, { p -> CustomFoodItem(foodItem(AddonFoodValues.COOKED_SHRIMP)) } )

  val TUCUPI = ITEMS.simpleItem(AddonNames.TUCUPI, { p -> Item(p) } )
  val RAW_PASTEL = ITEMS.simpleItem(AddonNames.RAW_PASTEL, { p -> Item(p) } )
  val PASTEL = ITEMS.simpleItem(AddonNames.PASTEL, { p -> Item(p) } )
  val FRIED_CASSAVA_WITH_BUTTER = ITEMS.simpleItem(AddonNames.FRIED_CASSAVA_WITH_BUTTER, { p -> Item(p) } )
  val RAW_COXINHA = ITEMS.simpleItem(AddonNames.RAW_COXINHA, { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_COXINHA)) } )
  val COXINHA = ITEMS.simpleItem(AddonNames.COXINHA, { p -> CustomFoodItem(foodItem(AddonFoodValues.COXINHA)) } )
  val RAW_CASSAVA_FRITTERS = ITEMS.simpleItem(AddonNames.RAW_CASSAVA_FRITTERS, { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_CASSAVA_FRITTERS)) } )
  val CASSAVA_FRITTERS = ITEMS.simpleItem(AddonNames.CASSAVA_FRITTERS, { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FRITTERS)) } )
  val ROASTED_GARLIC = ITEMS.simpleItem(AddonNames.ROASTED_GARLIC, { p -> CustomFoodItem(foodItem(AddonFoodValues.ROASTED_GARLIC)) } )
  val COCONUT_DRINK = ITEMS.simpleItem(AddonNames.COCONUT_DRINK, { p -> Item(p) } )
  val COCONUT_MILK = ITEMS.simpleItem(AddonNames.COCONUT_MILK, { p -> Item(p) } )
  val GUARANA_SODA = ITEMS.simpleItem(AddonNames.GUARANA_SODA, { p -> CustomDrinkItem(drinkItem(AddonFoodValues.GUARANA_SODA), true) } )
  val ACAI_CREAM = ITEMS.simpleItem(AddonNames.ACAI_CREAM, { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.ACAI_CREAM)) } )
  val BRIGADEIRO_CREAM = ITEMS.simpleItem(AddonNames.BRIGADEIRO_CREAM, { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.BRIGADEIRO_CREAM)) } )
  val SWEET_LOVE_APPLE = ITEMS.handheldItem(AddonNames.SWEET_LOVE_APPLE, { p -> CustomFoodItem(foodItem(AddonFoodValues.SWEET_LOVE_APPLE)) } )
  val GARAPA = ITEMS.simpleItem(AddonNames.GARAPA, { p -> CustomDrinkItem(drinkItem(AddonFoodValues.GARAPA)) } )
  val GUARANA_JUICE = ITEMS.simpleItem(AddonNames.GUARANA_JUICE, { p -> Item(p) } )
  val BROA = ITEMS.simpleItem(AddonNames.BROA, { p -> Item(p) } )
  val CUZCUZ = ITEMS.simpleItem(AddonNames.CUZCUZ, { p -> Item(p) } )
  val ANGU = ITEMS.simpleItem(AddonNames.ANGU, { p -> Item(p) } )
  val POPCORN = ITEMS.simpleItem(AddonNames.POPCORN, { p -> Item(p) } )
  val CHEESE_BREAD_DOUGH = ITEMS.simpleItem(AddonNames.CHEESE_BREAD_DOUGH, { p -> Item(p) } )
  val CHEESE_BREAD = ITEMS.simpleItem(AddonNames.CHEESE_BREAD, { p -> Item(p) } )
  val BRAZILIAN_BREAKFAST = ITEMS.simpleItem(AddonNames.BRAZILIAN_BREAKFAST, { p -> Item(p) } )
  val BRAZILIAN_DINNER = ITEMS.simpleItem(AddonNames.BRAZILIAN_DINNER, { p -> Item(p) } )
  val BRAZILIAN_RICE = ITEMS.simpleItem(AddonNames.BRAZILIAN_RICE, { p -> Item(p) } )
  val BREAD_WITH_BUTTER = ITEMS.simpleItem(AddonNames.BREAD_WITH_BUTTER, { p -> Item(p) } )
  val GARLIC_BREAD = ITEMS.simpleItem(AddonNames.GARLIC_BREAD, { p -> Item(p) } )
  val CHICKEN_SAUCE = ITEMS.simpleItem(AddonNames.CHICKEN_SAUCE, { p -> Item(p) } )
  val TROPEIRO_BEANS = ITEMS.simpleItem(AddonNames.TROPEIRO_BEANS, { p -> Item(p) } )
  val COOKED_BEANS = ITEMS.simpleItem(AddonNames.COOKED_BEANS, { p -> Item(p) } )
  val COLLARD_GREENS_FAROFA = ITEMS.simpleItem(AddonNames.COLLARD_GREENS_FAROFA, { p -> Item(p) } )
  val COLLARD_GREENS_SALAD = ITEMS.simpleItem(AddonNames.COLLARD_GREENS_SALAD, { p -> Item(p) } )
  val PLATE_OF_STROGANOFF = ITEMS.simpleItem(AddonNames.PLATE_OF_STROGANOFF, { p -> Item(p) } )
  val PLATE_OF_GREEN_SOUP = ITEMS.simpleItem(AddonNames.PLATE_OF_GREEN_SOUP, { p -> Item(p) } )
  val PLATE_OF_FISH_MOQUECA = ITEMS.simpleItem(AddonNames.PLATE_OF_FISH_MOQUECA, { p -> Item(p) } )
  val PLATE_OF_FEIJOADA = ITEMS.simpleItem(AddonNames.PLATE_OF_FEIJOADA, { p -> Item(p) } )
  val FRIED_FISH_WITH_ACAI = ITEMS.simpleItem(AddonNames.FRIED_FISH_WITH_ACAI, { p -> Item(p) } )
  val PLATE_OF_FRIED_FISH_WITH_ACAI = ITEMS.simpleItem(AddonNames.PLATE_OF_FRIED_FISH_WITH_ACAI, { p -> Item(p) } )
  val PLATE_OF_SALPICAO = ITEMS.simpleItem(AddonNames.PLATE_OF_SALPICAO, { p -> Item(p) } )
  val PLATE_OF_CUZCUZ_PAULISTA = ITEMS.simpleItem(AddonNames.PLATE_OF_CUZCUZ_PAULISTA, { p -> Item(p) } )
}