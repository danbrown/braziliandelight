package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.databoxlib.registry.generators.ItemGen
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.FarmersDelight

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

  val BRAZIL_FLAG = ITEMS.simpleItem("brazil_flag", { p -> Item(p) })

  val ACAI_BERRIES = ITEMS.simpleItem("acai_berries", { p -> Item(foodItem(AddonFoodValues.ACAI)) })
  val GUARANA_FRUIT = ITEMS.simpleItem("guarana_fruit", { p -> Item(foodItem(AddonFoodValues.GUARANA)) })
  val GUARANA_POWDER = ITEMS.simpleItem("guarana_powder", { p -> Item(foodItem(AddonFoodValues.GUARANA_POWDER)) })

  val CASSAVA_ROOT = ITEMS.simpleItem("cassava_root", { p -> Item(foodItem(AddonFoodValues.CASSAVA)) })
  val CASSAVA_FLOUR = ITEMS.simpleItem("cassava_flour", { p -> Item(foodItem(AddonFoodValues.CASSAVA_FLOUR)) })

  val BEAN_POD = ITEMS.simpleItem("bean_pod", { p -> Item(foodItem(AddonFoodValues.BEAN)) })
  val BLACK_BEANS = ITEMS.simpleItem("black_beans", { p -> Item(foodItem(AddonFoodValues.BEAN)) })
  val CARIOCA_BEANS = ITEMS.simpleItem("carioca_beans", { p -> Item(foodItem(AddonFoodValues.BEAN)) })

  val MINAS_CHEESE = ITEMS.simpleItem("minas_cheese", { p -> Item(p) })
  val MINAS_CHEESE_SLICE = ITEMS.simpleItem("minas_cheese_slice", { p -> Item(p) })
  val GRILLED_CHEESE_ON_A_STICK = ITEMS.simpleItem("grilled_cheese_on_a_stick", { p -> Item(p) })
  val HEAVY_CREAM = ITEMS.simpleItem("heavy_cream", { p -> Item(p) })

  val CARROT_CAKE = ITEMS.simpleItem("carrot_cake", { p -> Item(p) })
  val CARROT_CAKE_SLICE = ITEMS.simpleItem("carrot_cake_slice", { p -> Item(p) })
  val CARROT_CAKE_WITH_CHOCOLATE = ITEMS.simpleItem("carrot_cake_with_chocolate", { p -> Item(p) })
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = ITEMS.simpleItem("carrot_cake_with_chocolate_slice", { p -> Item(p) })



}