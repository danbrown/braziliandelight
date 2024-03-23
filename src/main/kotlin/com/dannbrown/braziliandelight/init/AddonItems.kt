package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
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

  val BEAN_POD = ITEMS.simpleItem("bean_pod", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )
  val BLACK_BEANS = ITEMS.simpleItem("black_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )
  val CARIOCA_BEANS = ITEMS.simpleItem("carioca_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )

  val GARLIC_BULB = ITEMS.simpleItem("garlic_bulb", { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) } )
  val GARLIC_CLOVE = ITEMS.simpleItem("garlic_clove", { p -> CustomFoodItem(foodItem(AddonFoodValues.GARLIC), true) } )

  val ACAI_BERRIES = ITEMS.simpleItem("acai_berries", { p -> CustomFoodItem(foodItem(AddonFoodValues.ACAI)) } )
  val ACAI_CREAM = ITEMS.simpleItem("acai_cream", { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.ACAI_CREAM)) } )
  val BRIGADEIRO_CREAM = ITEMS.simpleItem("brigadeiro_cream", { p -> CustomFoodItem(bowlFoodItem(AddonFoodValues.BRIGADEIRO_CREAM)) } )

  val GUARANA_FRUIT = ITEMS.simpleItem("guarana_fruit", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA), true) } )
  val GUARANA_POWDER = ITEMS.simpleItem("guarana_powder", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA_POWDER), true) } )
  val GUARANA_SODA = ITEMS.simpleItem("guarana_soda", { p -> CustomFoodItem(drinkItem(AddonFoodValues.GUARANA_SODA), true) } )

  val CASSAVA_ROOT = ITEMS.simpleItem("cassava_root", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA), true) } )
  val CASSAVA_FLOUR = ITEMS.simpleItem("cassava_flour", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FLOUR)) } )

  val COLLARD_GREENS = ITEMS.simpleItem("collard_greens", { p -> CustomFoodItem(foodItem(AddonFoodValues.COLLARD_GREENS)) } )

  val COFFEE_BERRIES = ITEMS.simpleItem("coffee_berries", { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BERRIES)) } )
  val COFFEE_BEANS = ITEMS.simpleItem("coffee_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.COFFEE_BEANS), true) } )

  val RAW_COXINHA = ITEMS.simpleItem("raw_coxinha", { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_COXINHA)) } )
  val COXINHA = ITEMS.simpleItem("coxinha", { p -> CustomFoodItem(foodItem(AddonFoodValues.COXINHA)) } )

  val RAW_CASSAVA_FRITTERS = ITEMS.simpleItem("raw_cassava_fritters", { p -> CustomFoodItem(foodItem(AddonFoodValues.RAW_CASSAVA_FRITTERS)) } )
  val CASSAVA_FRITTERS = ITEMS.simpleItem("cassava_fritters", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FRITTERS)) } )

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

  val GARAPA = ITEMS.simpleItem("garapa", { p -> CustomFoodItem(drinkItem(AddonFoodValues.GARAPA)) } )
  val SWEET_LOVE_APPLE = ITEMS.handheldItem("sweet_love_apple", { p -> CustomFoodItem(foodItem(AddonFoodValues.SWEET_LOVE_APPLE)) } )

  val ROASTED_GARLIC = ITEMS.simpleItem("roasted_garlic", { p -> CustomFoodItem(foodItem(AddonFoodValues.ROASTED_GARLIC)) } )

  val PASTEL_DOUGH = ITEMS.simpleItem("pastel_dough", { p -> Item(p) } )

  val SHRIMP = ITEMS.simpleItem("shrimp", { p -> CustomFoodItem(foodItem(AddonFoodValues.SHRIMP)) } )
  val COOKED_SHRIMP = ITEMS.simpleItem("cooked_shrimp", { p -> CustomFoodItem(foodItem(AddonFoodValues.COOKED_SHRIMP)) } )

}