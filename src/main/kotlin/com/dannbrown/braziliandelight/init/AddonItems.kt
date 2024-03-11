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

  fun drinkItem(): Item.Properties {
    return Item.Properties()
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

  val ACAI_BERRIES = ITEMS.simpleItem("acai_berries", { p -> CustomFoodItem(foodItem(AddonFoodValues.ACAI), true) } )
  val GUARANA_FRUIT = ITEMS.simpleItem("guarana_fruit", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA), true) } )
  val GUARANA_POWDER = ITEMS.simpleItem("guarana_powder", { p -> CustomFoodItem(foodItem(AddonFoodValues.GUARANA_POWDER), true) } )

  val CASSAVA_ROOT = ITEMS.simpleItem("cassava_root", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA), true) } )
  val CASSAVA_FLOUR = ITEMS.simpleItem("cassava_flour", { p -> CustomFoodItem(foodItem(AddonFoodValues.CASSAVA_FLOUR)) } )

  val BEAN_POD = ITEMS.simpleItem("bean_pod", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )
  val BLACK_BEANS = ITEMS.simpleItem("black_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )
  val CARIOCA_BEANS = ITEMS.simpleItem("carioca_beans", { p -> CustomFoodItem(foodItem(AddonFoodValues.BEAN)) } )

  val MINAS_CHEESE_SLICE = ITEMS.simpleItem("minas_cheese_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.MINAS_CHEESE_SLICE)) } )
  val MINAS_CHEESE_ON_A_STICK = ITEMS.simpleItem("minas_cheese_on_a_stick", { p -> CustomFoodItem(stickItem(AddonFoodValues.MINAS_CHEESE_ON_A_STICK)) } )
  val GRILLED_CHEESE_ON_A_STICK = ITEMS.simpleItem("grilled_cheese_on_a_stick", { p -> CustomFoodItem(stickItem(AddonFoodValues.GRILLED_CHEESE_ON_A_STICK)) } )
  val HEAVY_CREAM = ITEMS.simpleItem("heavy_cream", { p -> CustomFoodItem(p) } )

  val CARROT_CAKE_SLICE = ITEMS.simpleItem("carrot_cake_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_SLICE)) } )
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = ITEMS.simpleItem("carrot_cake_with_chocolate_slice", { p -> CustomFoodItem(foodItem(AddonFoodValues.CARROT_CAKE_WITH_CHOCOLATE_SLICE)) } )



}