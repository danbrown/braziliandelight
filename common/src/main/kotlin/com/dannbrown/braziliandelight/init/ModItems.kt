package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.FarmersCompat
import com.dannbrown.braziliandelight.content.items.CustomDrinkItem
import com.dannbrown.braziliandelight.content.items.CustomFoodItem
import com.dannbrown.braziliandelight.content.items.MilkBottleItem
import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.content.item.arrow.BaseArrowItem
import net.minecraft.tags.ItemTags
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.Items

object ModItems {
  fun foodItem(p: Item.Properties, food: FoodProperties): Item.Properties {
    return p.food(food)
  }

  fun bowlFoodItem(p: Item.Properties, food: FoodProperties): Item.Properties {
    return p
      .food(food)
      .craftRemainder(Items.BOWL)
      .stacksTo(16)
  }

  fun drinkItem(p: Item.Properties, food: FoodProperties): Item.Properties {
    return p
      .food(food)
      .craftRemainder(Items.GLASS_BOTTLE)
      .stacksTo(16)
  }

  fun stickItem(p: Item.Properties, food: FoodProperties): Item.Properties {
    return p
      .food(food)
      .craftRemainder(Items.STICK)
      .stacksTo(16)
  }

  fun bucketItem(p: Item.Properties, food: FoodProperties): Item.Properties {
    return p
      .food(food)
      .craftRemainder(Items.BUCKET)
      .stacksTo(16)
  }

  val BRAZIL_FLAG = REGISTRATE.item<Item>(ModNames.BRAZIL_FLAG).register()

  val BEAN_POD = REGISTRATE.item<CustomFoodItem>(
    ModNames.BEAN_POD
  )
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BEAN)) }
    .itemTags(*ModTags.ITEM.BEAN_PODS.toTypedArray())
    .register()

  val GARLIC_BULB = REGISTRATE.item<CustomFoodItem>(
    ModNames.GARLIC_BULB
  )
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GARLIC), true) }
    .itemTags(*ModTags.ITEM.GARLIC.toTypedArray())
    .register()

  val REPUGNANT_ARROW = REGISTRATE.item<BaseArrowItem>(
    ModNames.REPUGNANT_ARROW
  )
    .factory { p -> BaseArrowItem(p) { l, e, _ -> RepugnantArrow(l, e) } }
    .itemTags(ItemTags.ARROWS)
    .register()

  val GUARANA_FRUIT = REGISTRATE.item<CustomFoodItem>(ModNames.GUARANA_FRUIT)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GUARANA)) }
    .itemTags(*ModTags.ITEM.GUARANA.toTypedArray())
    .register()

  val GUARANA_POWDER = REGISTRATE.item<CustomFoodItem>(ModNames.GUARANA_POWDER)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GUARANA_POWDER)) }
    .register()

  val COCONUT_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.COCONUT_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COCONUT_SLICE)) }
    .itemTags(*ModTags.ITEM.COCONUT.toTypedArray())
    .register()

  val CORN = REGISTRATE.item<CustomFoodItem>(ModNames.CORN)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CORN)) }
    .itemTags(*ModTags.ITEM.CORN.toTypedArray())
    .register()

  val COOKED_CORN = REGISTRATE.item<CustomFoodItem>(ModNames.COOKED_CORN)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COOKED_CORN)) }
    .register()

  val CORN_FLOUR = REGISTRATE.item<CustomFoodItem>(ModNames.CORN_FLOUR)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CORN_FLOUR)) }
    .register()

  val COLLARD_GREENS = REGISTRATE.item<CustomFoodItem>(ModNames.COLLARD_GREENS)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COLLARD_GREENS)) }
    .itemTags(*ModTags.ITEM.COLLARD_GREENS.toTypedArray())
    .register()

  val CASSAVA_FLOUR = REGISTRATE.item<CustomFoodItem>(ModNames.CASSAVA_FLOUR)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CASSAVA_FLOUR)) }
    .register()

  val COFFEE_BERRIES = REGISTRATE.item<CustomFoodItem>(ModNames.COFFEE_BERRIES)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COFFEE_BERRIES)) }
    .register()

  val COFFEE_BEANS = REGISTRATE.item<CustomFoodItem>(ModNames.COFFEE_BEANS)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COFFEE_BEANS)) }
    .itemTags(*ModTags.ITEM.COFFEE_BEANS.toTypedArray(), *ModTags.ITEM.COFFEE.toTypedArray())
    .register()

  val YERBA_MATE_LEAVES = REGISTRATE.item<CustomFoodItem>(ModNames.YERBA_MATE_LEAVES)
    .factory { p -> ItemNameBlockItem(ModBlocks.YERBA_MATE_BUSH.get(), foodItem(p, AddonFoodValues.YERBA_MATE_LEAVES)) }
    .register()

  val DRIED_YERBA_MATE = REGISTRATE.item<CustomFoodItem>(ModNames.DRIED_YERBA_MATE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.DRIED_YERBA_MATE)) }
    .register()


  val CHIMARRAO = REGISTRATE.item<CustomFoodItem>(ModNames.CHIMARRAO)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHIMARRAO)) }
    .register()

  val LEMON = REGISTRATE.item<CustomFoodItem>(ModNames.LEMON)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.LEMON)) }
    .itemTags(*ModTags.ITEM.LEMON.toTypedArray(), ModTags.ITEM.CHEESE_COAGULANT)
    .register()

  val LEMON_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.LEMON_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.LEMON_SLICE)) }
    .itemTags(*ModTags.ITEM.LEMON.toTypedArray(), ModTags.ITEM.CHEESE_COAGULANT)
    .register()

  val LEMONADE = REGISTRATE.item<CustomDrinkItem>(ModNames.LEMONADE)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.LEMONADE)) }
    .register()

  val COLLARD_LEMONADE = REGISTRATE.item<CustomDrinkItem>(ModNames.COLLARD_LEMONADE)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.COLLARD_LEMONADE)) }
    .register()

  val SALT = REGISTRATE.item<Item>(ModNames.SALT).itemTags(*ModTags.ITEM.SALT.toTypedArray()).register()
  val SALT_BUCKET =
    REGISTRATE.item<Item>(ModNames.SALT_BUCKET)
      .properties { p -> p.craftRemainder(Items.BUCKET).stacksTo(16) }
      .register()
  val HEAVY_CREAM_BUCKET = REGISTRATE.item<CustomDrinkItem>(ModNames.HEAVY_CREAM_BUCKET)
    .factory { p -> CustomDrinkItem(bucketItem(p, AddonFoodValues.HEAVY_CREAM_BUCKET)) }
    .register()

  val CONDENSED_MILK = REGISTRATE.item<CustomDrinkItem>(ModNames.CONDENSED_MILK)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.CONDENSED_MILK)) }
    .register()

  val BUTTER = REGISTRATE.item<CustomFoodItem>(ModNames.BUTTER)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BUTTER)) }
    .itemTags(*ModTags.ITEM.BUTTER.toTypedArray())
    .register()
  val MINAS_CHEESE_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.MINAS_CHEESE_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.MINAS_CHEESE_SLICE)) }
    .itemTags(*ModTags.ITEM.CHEESE.toTypedArray())
    .register()

  val MINAS_CHEESE_ON_A_STICK = REGISTRATE.item<CustomFoodItem>(ModNames.MINAS_CHEESE_ON_A_STICK)
    .factory { p -> CustomFoodItem(stickItem(p, AddonFoodValues.MINAS_CHEESE_ON_A_STICK)) }
    .register()

  val GRILLED_CHEESE_ON_A_STICK = REGISTRATE.item<CustomFoodItem>(ModNames.GRILLED_CHEESE_ON_A_STICK)
    .factory { p -> CustomFoodItem(stickItem(p, AddonFoodValues.GRILLED_CHEESE_ON_A_STICK)) }
    .register()

  val CARROT_CAKE_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.CARROT_CAKE_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CARROT_CAKE_SLICE)) }
    .register()

  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.CARROT_CAKE_WITH_CHOCOLATE_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CARROT_CAKE_WITH_CHOCOLATE_SLICE)) }
    .register()

  val CHICKEN_POT_PIE_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.CHICKEN_POT_PIE_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHICKEN_POT_PIE_SLICE)) }
    .register()
  val PUDDING_SLICE = REGISTRATE.item<CustomFoodItem>(ModNames.PUDDING_SLICE)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.PUDDING_SLICE)) }
    .register()
  val SHRIMP = REGISTRATE.item<CustomFoodItem>(ModNames.SHRIMP)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.SHRIMP)) }
    .register()
  val COOKED_SHRIMP = REGISTRATE.item<CustomFoodItem>(ModNames.COOKED_SHRIMP)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COOKED_SHRIMP)) }
    .register()

  val TUCUPI = REGISTRATE.item<CustomDrinkItem>(ModNames.TUCUPI)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.TUCUPI)) }
    .register()

  val FRIED_CASSAVA_WITH_BUTTER = REGISTRATE.item<CustomFoodItem>(ModNames.FRIED_CASSAVA_WITH_BUTTER)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.FRIED_CASSAVA_WITH_BUTTER)) }
    .register()

  val BUTTERED_CORN = REGISTRATE.item<CustomFoodItem>(ModNames.BUTTERED_CORN)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BUTTERED_CORN)) }
    .register()

  val RAW_COXINHA = REGISTRATE.item<CustomFoodItem>(ModNames.RAW_COXINHA)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.RAW_COXINHA)) }
    .register()

  val COXINHA = REGISTRATE.item<CustomFoodItem>(ModNames.COXINHA)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COXINHA)) }
    .register()

  val RAW_CASSAVA_FRITTERS = REGISTRATE.item<CustomFoodItem>(ModNames.RAW_CASSAVA_FRITTERS)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.RAW_CASSAVA_FRITTERS)) }
    .register()

  val CASSAVA_FRITTERS = REGISTRATE.item<CustomFoodItem>(ModNames.CASSAVA_FRITTERS)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CASSAVA_FRITTERS)) }
    .register()

  val ROASTED_GARLIC = REGISTRATE.item<CustomFoodItem>(ModNames.ROASTED_GARLIC)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.ROASTED_GARLIC)) }
    .register()

  val COCONUT_DRINK = REGISTRATE.item<CustomDrinkItem>(ModNames.COCONUT_DRINK)
    .factory { p -> CustomDrinkItem(foodItem(p, AddonFoodValues.COCONUT_DRINK)) }
    .register()

  val COCONUT_MILK = REGISTRATE.item<MilkBottleItem>(ModNames.COCONUT_MILK)
    .factory { p -> MilkBottleItem(drinkItem(p, AddonFoodValues.COCONUT_MILK)) }
    .itemTags(*ModTags.ITEM.MILK.toTypedArray())
    .register()

  val COCONUT_CREAM = REGISTRATE.item<CustomFoodItem>(ModNames.COCONUT_CREAM)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COCONUT_CREAM)) }
    .register()

  val GUARANA_SODA = REGISTRATE.item<CustomDrinkItem>(ModNames.GUARANA_SODA)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.GUARANA_SODA)) }
    .register()

  val ACAI_CREAM = REGISTRATE.item<CustomFoodItem>(ModNames.ACAI_CREAM)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.ACAI_CREAM)) }
    .register()

  val BRIGADEIRO_CREAM = REGISTRATE.item<CustomFoodItem>(ModNames.BRIGADEIRO_CREAM)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.BRIGADEIRO_CREAM)) }
    .register()

  val SWEET_LOVE_APPLE = REGISTRATE.item<CustomFoodItem>(ModNames.SWEET_LOVE_APPLE)
    .factory { p -> CustomFoodItem(stickItem(p, AddonFoodValues.SWEET_LOVE_APPLE)) }
    .register()

  val GARAPA = REGISTRATE.item<CustomDrinkItem>(ModNames.GARAPA)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.GARAPA)) }
    .register()

  val GUARANA_JUICE = REGISTRATE.item<CustomDrinkItem>(ModNames.GUARANA_JUICE)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.GUARANA_JUICE)) }
    .register()

  val ACAI_TEA_WITH_GUARANA = REGISTRATE.item<CustomDrinkItem>(ModNames.ACAI_TEA_WITH_GUARANA)
    .factory { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.ACAI_TEA_WITH_GUARANA)) }
    .register()

  val BROA = REGISTRATE.item<CustomFoodItem>(ModNames.BROA)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BROA)) }
    .register()

  val COUSCOUS = REGISTRATE.item<CustomFoodItem>(ModNames.COUSCOUS)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COUSCOUS)) }
    .register()

  val ANGU = REGISTRATE.item<CustomFoodItem>(ModNames.ANGU)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.ANGU)) }
    .register()

  val CHEESE_BREAD_DOUGH = REGISTRATE.item<CustomFoodItem>(ModNames.CHEESE_BREAD_DOUGH)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHEESE_BREAD_DOUGH)) }
    .register()

  val CHEESE_BREAD = REGISTRATE.item<CustomFoodItem>(ModNames.CHEESE_BREAD)
    .factory { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHEESE_BREAD)) }
    .register()

  val BRAZILIAN_DINNER = REGISTRATE.item<CustomFoodItem>(ModNames.BRAZILIAN_DINNER)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.BRAZILIAN_DINNER)) }
    .register()

  val TROPEIRO_BEANS = REGISTRATE.item<CustomFoodItem>(ModNames.TROPEIRO_BEANS)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.TROPEIRO_BEANS)) }
    .register()

  val COOKED_CARIOCA_BEANS = REGISTRATE.item<CustomFoodItem>(ModNames.COOKED_CARIOCA_BEANS)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COOKED_BEANS)) }
    .register()

  val COOKED_BLACK_BEANS = REGISTRATE.item<CustomFoodItem>(ModNames.COOKED_BLACK_BEANS)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COOKED_BEANS)) }
    .register()

  val COLLARD_GREENS_FAROFA = REGISTRATE.item<CustomFoodItem>(ModNames.COLLARD_GREENS_FAROFA)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COLLARD_GREENS_FAROFA)) }
    .register()

  val COLLARD_GREENS_SALAD = REGISTRATE.item<CustomFoodItem>(ModNames.COLLARD_GREENS_SALAD)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COLLARD_GREENS_SALAD)) }
    .register()

  val PLATE_OF_STROGANOFF = REGISTRATE.item<CustomFoodItem>(ModNames.PLATE_OF_STROGANOFF)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_STROGANOFF)) }
    .register()

  val PLATE_OF_GREEN_SOUP = REGISTRATE.item<CustomFoodItem>(ModNames.PLATE_OF_GREEN_SOUP)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_GREEN_SOUP)) }
    .register()

  val PLATE_OF_FISH_MOQUECA = REGISTRATE.item<CustomFoodItem>(ModNames.PLATE_OF_FISH_MOQUECA)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_FISH_MOQUECA)) }
    .register()

  val PLATE_OF_FEIJOADA = REGISTRATE.item<CustomFoodItem>(ModNames.PLATE_OF_FEIJOADA)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_FEIJOADA)) }
    .register()

  val FRIED_FISH_WITH_ACAI = REGISTRATE.item<CustomFoodItem>(ModNames.FRIED_FISH_WITH_ACAI)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.FRIED_FISH_WITH_ACAI)) }
    .register()

  val SALPICAO = REGISTRATE.item<CustomFoodItem>(ModNames.SALPICAO)
    .factory { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.SALPICAO)) }
    .register()

  fun register() {
    REGISTRATE.buildItems()
  }
}