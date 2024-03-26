package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.item.CustomDrinkItem
import com.dannbrown.braziliandelight.content.item.CustomFoodItem
import com.dannbrown.databoxlib.registry.generators.ItemGen
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.FarmersDelight
import vectorwing.farmersdelight.common.item.ConsumableItem

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

  val BRAZIL_FLAG = ITEMS.simpleItem("brazil_flag", { p -> Item(p) } )

  val HEAVY_POT = ITEMS.simpleItem("heavy_pot", { p -> Item(p) } )

  val BEAN_POD = ITEMS.simpleItem("bean_pod", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEAN_PODS )
  val BLACK_BEANS = ITEMS.simpleItem("black_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEANS )
  val CARIOCA_BEANS = ITEMS.simpleItem("carioca_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) }, AddonTags.ITEM.BEANS )

  val GARLIC_BULB = ITEMS.simpleItem("garlic_bulb", { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) }, AddonTags.ITEM.GARLIC )
  val GARLIC_CLOVE = ITEMS.simpleItem("garlic_clove", { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) } )

  val ACAI_BERRIES = ITEMS.simpleItem("acai_berries", { p -> CustomFoodItem(foodItem(AddonFoodValues.ACAI)) }, AddonTags.ITEM.ACAI )
  val GUARANA_FRUIT = ITEMS.simpleItem("guarana_fruit", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA), true) }, AddonTags.ITEM.GUARANA )
  val GUARANA_POWDER = ITEMS.simpleItem("guarana_powder", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA_POWDER), true) } )

  val GREEN_COCONUT = ITEMS.simpleItem("green_coconut", { p -> Item(p) } )
  val COCONUT = ITEMS.simpleItem("coconut", { p -> Item(p) }, AddonTags.ITEM.COCONUT )
  val COCONUT_SLICE = ITEMS.simpleItem("coconut_slice", { p -> Item(p) } )

  val CORN = ITEMS.simpleItem("corn", { p -> Item(p) }, AddonTags.ITEM.CORN )
  val COOKED_CORN = ITEMS.simpleItem("cooked_corn", { p -> Item(p) } )
  val CORN_GRAINS = ITEMS.simpleItem("corn_grains", { p -> Item(p) } )
  val CORN_FLOUR = ITEMS.simpleItem("corn_flour", { p -> Item(p) } )

  val CASSAVA_ROOT = ITEMS.simpleItem("cassava_root", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA), true) }, AddonTags.ITEM.CASSAVA )
  val CASSAVA_FLOUR = ITEMS.simpleItem("cassava_flour", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FLOUR)) } )

  val COLLARD_GREENS = ITEMS.simpleItem("collard_greens", { p -> CustomFoodItem(foodItem(AddonFoodValues.COLLARD_GREENS)) }, AddonTags.ITEM.COLLARD_GREENS )

  val COFFEE_BERRIES = ITEMS.simpleItem("coffee_berries", { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BERRIES)) } )
  val COFFEE_BEANS = ITEMS.simpleItem("coffee_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BEANS), true) }, AddonTags.ITEM.COFFEE_BEANS, AddonTags.ITEM.COFFEE )

  val SALT = ITEMS.simpleItem("salt", { p -> Item(p) }, AddonTags.ITEM.SALT)
  val BUTTER = ITEMS.simpleItem("butter", { p -> CustomFoodItem(foodItem(AddonFoodValues.BUTTER)) }, AddonTags.ITEM.BUTTER)
  val HEAVY_CREAM = ITEMS.simpleItem("heavy_cream", { p -> CustomFoodItem(p) } )
  val CONDENSED_MILK = ITEMS.simpleItem("condensed_milk", { p -> CustomFoodItem(p.craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)) } )

  val MINAS_CHEESE_SLICE = ITEMS.simpleItem("minas_cheese_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.MINAS_CHEESE_SLICE)) }, AddonTags.ITEM.CHEESE)
  val MINAS_CHEESE_ON_A_STICK = ITEMS.handheldItem("minas_cheese_on_a_stick", { p -> CustomFoodItem(stickItem(AddonFoodValues.MINAS_CHEESE_ON_A_STICK)) } )
  val GRILLED_CHEESE_ON_A_STICK = ITEMS.handheldItem("grilled_cheese_on_a_stick", { p -> CustomFoodItem(stickItem(AddonFoodValues.GRILLED_CHEESE_ON_A_STICK)) } )

  val CARROT_CAKE_SLICE = ITEMS.simpleItem("carrot_cake_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_SLICE)) } )
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = ITEMS.simpleItem("carrot_cake_with_chocolate_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_WITH_CHOCOLATE_SLICE)) } )
  val CHICKEN_POT_PIE_SLICE = ITEMS.simpleItem("chicken_pot_pie_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.CHICKEN_POT_PIE_SLICE)) } )
  val PUDDING_SLICE = ITEMS.simpleItem("pudding_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.PUDDING_SLICE)) } )

  val PASTEL_DOUGH = ITEMS.simpleItem("pastel_dough", { p -> Item(p) } )

  val SHRIMP = ITEMS.simpleItem("shrimp", { p -> CustomFoodItem(foodItem(AddonFoodValues.SHRIMP)) } )
  val COOKED_SHRIMP = ITEMS.simpleItem("cooked_shrimp", { p -> CustomFoodItem(foodItem(AddonFoodValues.COOKED_SHRIMP)) } )

  val TUCUPI = ITEMS.simpleItem("tucupi", { p -> Item(p) } )
  val RAW_PASTEL = ITEMS.simpleItem("raw_pastel", { p -> Item(p) } )
  val PASTEL = ITEMS.simpleItem("pastel", { p -> Item(p) } )
  val FRIED_CASSAVA_WITH_BUTTER = ITEMS.simpleItem("fried_cassava_with_butter", { p -> Item(p) } )
  val RAW_COXINHA = ITEMS.simpleItem("raw_coxinha", { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_COXINHA)) } )
  val COXINHA = ITEMS.simpleItem("coxinha", { p -> CustomFoodItem(foodItem(AddonFoodValues.COXINHA)) } )
  val RAW_CASSAVA_FRITTERS = ITEMS.simpleItem("raw_cassava_fritters", { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_CASSAVA_FRITTERS)) } )
  val CASSAVA_FRITTERS = ITEMS.simpleItem("cassava_fritters", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FRITTERS)) } )
  val ROASTED_GARLIC = ITEMS.simpleItem("roasted_garlic", { p -> CustomFoodItem(foodItem(AddonFoodValues.ROASTED_GARLIC)) } )
  val COCONUT_DRINK = ITEMS.simpleItem("coconut_drink", { p -> Item(p) } )
  val COCONUT_MILK = ITEMS.simpleItem("coconut_milk", { p -> Item(p) } )
  val GUARANA_SODA = ITEMS.simpleItem("guarana_soda", { p -> CustomDrinkItem(drinkItem(AddonFoodValues.GUARANA_SODA), true) } )
  val ACAI_CREAM = ITEMS.simpleItem("acai_cream", { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.ACAI_CREAM)) } )
  val BRIGADEIRO_CREAM = ITEMS.simpleItem("brigadeiro_cream", { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.BRIGADEIRO_CREAM)) } )
  val SWEET_LOVE_APPLE = ITEMS.handheldItem("sweet_love_apple", { p -> CustomFoodItem(foodItem(AddonFoodValues.SWEET_LOVE_APPLE)) } )
  val GARAPA = ITEMS.simpleItem("garapa", { p -> CustomDrinkItem(drinkItem(AddonFoodValues.GARAPA)) } )
  val GUARANA_JUICE = ITEMS.simpleItem("guarana_juice", { p -> Item(p) } )
  val BROA = ITEMS.simpleItem("broa", { p -> Item(p) } )
  val CUZCUZ = ITEMS.simpleItem("cuzcuz", { p -> Item(p) } )
  val ANGU = ITEMS.simpleItem("angu", { p -> Item(p) } )
  val POPCORN = ITEMS.simpleItem("popcorn", { p -> Item(p) } )
  val CHEESE_BREAD_DOUGH = ITEMS.simpleItem("cheese_bread_dough", { p -> Item(p) } )
  val CHEESE_BREAD = ITEMS.simpleItem("cheese_bread", { p -> Item(p) } )
  val BRAZILIAN_BREAKFAST = ITEMS.simpleItem("brazilian_breakfast", { p -> Item(p) } )
  val BRAZILIAN_DINNER = ITEMS.simpleItem("brazilian_dinner", { p -> Item(p) } )
  val BRAZILIAN_RICE = ITEMS.simpleItem("brazilian_rice", { p -> Item(p) } )
  val BREAD_WITH_BUTTER = ITEMS.simpleItem("bread_with_butter", { p -> Item(p) } )
  val GARLIC_BREAD = ITEMS.simpleItem("garlic_bread", { p -> Item(p) } )
  val CHICKEN_SAUCE = ITEMS.simpleItem("chicken_sauce", { p -> Item(p) } )
  val TROPEIRO_BEANS = ITEMS.simpleItem("tropeiro_beans", { p -> Item(p) } )
  val COOKED_BEANS = ITEMS.simpleItem("cooked_beans", { p -> Item(p) } )
  val COLLARD_GREENS_FAROFA = ITEMS.simpleItem("collard_greens_farofa", { p -> Item(p) } )
  val COLLARD_GREENS_SALAD = ITEMS.simpleItem("collard_greens_salad", { p -> Item(p) } )
  val STROGANOFF_POT = ITEMS.simpleItem("stroganoff_pot", { p -> Item(p) } )
  val PLATE_OF_STROGANOFF = ITEMS.simpleItem("plate_of_stroganoff", { p -> Item(p) } )
  val GREEN_SOUP_POT = ITEMS.simpleItem("green_soup_pot", { p -> Item(p) } )
  val PLATE_OF_GREEN_SOUP = ITEMS.simpleItem("plate_of_green_soup", { p -> Item(p) } )
  val FISH_MOQUECA_POT = ITEMS.simpleItem("fish_moqueca_pot", { p -> Item(p) } )
  val PLATE_OF_FISH_MOQUECA = ITEMS.simpleItem("plate_of_fish_moqueca", { p -> Item(p) } )
  val FEIJOADA_POT = ITEMS.simpleItem("feijoada_pot", { p -> Item(p) } )
  val PLATE_OF_FEIJOADA = ITEMS.simpleItem("plate_of_feijoada", { p -> Item(p) } )
  val FRIED_FISH_WITH_ACAI = ITEMS.simpleItem("fried_fish_with_acai", { p -> Item(p) } )
  val PLATE_OF_FRIED_FISH_WITH_ACAI = ITEMS.simpleItem("plate_of_fried_fish_with_acai", { p -> Item(p) } )
  val PLATE_OF_SALPICAO = ITEMS.simpleItem("plate_of_salpicao", { p -> Item(p) } )
  val PLATE_OF_CUZCUZ_PAULISTA = ITEMS.simpleItem("plate_of_cuzcuz_paulista", { p -> Item(p) } )
}