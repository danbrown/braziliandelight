package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.content.item.CustomDrinkItem
import com.dannbrown.braziliandelight.content.item.CustomFoodItem
import com.dannbrown.braziliandelight.lib.AddonNames
import com.dannbrown.deltaboxlib.content.entity.projectile.BaseArrowItem
import com.dannbrown.deltaboxlib.registry.generators.ItemGen
import net.minecraft.tags.ItemTags
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import vectorwing.farmersdelight.common.item.MilkBottleItem

object AddonItems {
  fun register(modBus: IEventBus) {}

  val ITEMS = ItemGen(AddonContent.REGISTRATE)
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

  val BRAZIL_FLAG = ITEMS.simpleItem(AddonNames.BRAZIL_FLAG, { p -> Item(p) })

  val BEAN_POD = ITEMS.simpleItem(
    AddonNames.BEAN_POD,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BEAN)) },
    AddonTags.ITEM.BEAN_PODS
  )

  val GARLIC_BULB = ITEMS.simpleItem(
    AddonNames.GARLIC_BULB,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GARLIC), true) },
    AddonTags.ITEM.GARLIC
  )
  val REPUGNANT_ARROW = ITEMS.simpleItem(
    AddonNames.REPUGNANT_ARROW,
    { p -> BaseArrowItem(p) { l, e, _ -> RepugnantArrow(l, e) } },
    ItemTags.ARROWS
  )

  val GUARANA_FRUIT = ITEMS.simpleItem(
    AddonNames.GUARANA_FRUIT,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GUARANA), true) },
    AddonTags.ITEM.GUARANA
  )
  val GUARANA_POWDER = ITEMS.simpleItem(
    AddonNames.GUARANA_POWDER,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.GUARANA_POWDER), true) })

  val COCONUT_SLICE = ITEMS.simpleItem(
    AddonNames.COCONUT_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COCONUT_SLICE)) },
    AddonTags.ITEM.COCONUT
  )

  val CORN = ITEMS.simpleItem(AddonNames.CORN, { p -> Item(foodItem(p, AddonFoodValues.CORN)) }, AddonTags.ITEM.CORN)
  val COOKED_CORN =
    ITEMS.simpleItem(AddonNames.COOKED_CORN, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COOKED_CORN)) })
  val CORN_FLOUR =
    ITEMS.simpleItem(AddonNames.CORN_FLOUR, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CORN_FLOUR)) })
  val BUTTERED_CORN =
    ITEMS.simpleItem(AddonNames.BUTTERED_CORN, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BUTTERED_CORN)) })

  val CASSAVA_FLOUR =
    ITEMS.simpleItem(AddonNames.CASSAVA_FLOUR, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CASSAVA_FLOUR)) })

  val COLLARD_GREENS = ITEMS.simpleItem(
    AddonNames.COLLARD_GREENS,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COLLARD_GREENS)) },
    AddonTags.ITEM.COLLARD_GREENS
  )

  val COFFEE_BERRIES =
    ITEMS.simpleItem(AddonNames.COFFEE_BERRIES, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COFFEE_BERRIES)) })
  val COFFEE_BEANS = ITEMS.simpleItem(
    AddonNames.COFFEE_BEANS,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COFFEE_BEANS), true) },
    AddonTags.ITEM.COFFEE_BEANS,
    AddonTags.ITEM.COFFEE
  )

  val YERBA_MATE_LEAVES = ITEMS.simpleItem(
    AddonNames.YERBA_MATE_LEAVES,
    { p -> ItemNameBlockItem(AddonBlocks.YERBA_MATE_BUSH.get(), foodItem(p, AddonFoodValues.YERBA_MATE_LEAVES)) })
  val DRIED_YERBA_MATE = ITEMS.simpleItem(
    AddonNames.DRIED_YERBA_MATE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.DRIED_YERBA_MATE)) })
  val CHIMARRAO =
    ITEMS.simpleItem(AddonNames.CHIMARRAO, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHIMARRAO)) })

  val LEMON = ITEMS.simpleItem(
    AddonNames.LEMON,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.LEMON)) },
    AddonTags.ITEM.LEMON
  )
  val LEMON_SLICE = ITEMS.simpleItem(
    AddonNames.LEMON_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.LEMON_SLICE)) },
    AddonTags.ITEM.LEMON
  )
  val LEMONADE = ITEMS.simpleItem(AddonNames.LEMONADE, { p -> CustomDrinkItem(foodItem(p, AddonFoodValues.LEMONADE)) })
  val COLLARD_LEMONADE = ITEMS.simpleItem(
    AddonNames.COLLARD_LEMONADE,
    { p -> CustomDrinkItem(foodItem(p, AddonFoodValues.COLLARD_LEMONADE)) })

  val SALT = ITEMS.simpleItem(AddonNames.SALT, { p -> Item(p) }, AddonTags.ITEM.SALT)
  val SALT_BUCKET = ITEMS.simpleItem(AddonNames.SALT_BUCKET, { p -> Item(p.craftRemainder(Items.BUCKET).stacksTo(16)) })
  val BUTTER = ITEMS.simpleItem(
    AddonNames.BUTTER,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BUTTER)) },
    AddonTags.ITEM.BUTTER
  )
  val HEAVY_CREAM_BUCKET = ITEMS.simpleItem(
    AddonNames.HEAVY_CREAM_BUCKET,
    { p -> CustomFoodItem(bucketItem(p, AddonFoodValues.HEAVY_CREAM_BUCKET)) })
  val CONDENSED_MILK =
    ITEMS.simpleItem(AddonNames.CONDENSED_MILK, { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.CONDENSED_MILK)) })

  val MINAS_CHEESE_SLICE = ITEMS.simpleItem(
    AddonNames.MINAS_CHEESE_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.MINAS_CHEESE_SLICE)) },
    AddonTags.ITEM.CHEESE
  )
  val MINAS_CHEESE_ON_A_STICK = ITEMS.handheldItem(
    AddonNames.MINAS_CHEESE_ON_A_STICK,
    { p -> CustomFoodItem(stickItem(p, AddonFoodValues.MINAS_CHEESE_ON_A_STICK)) })
  val GRILLED_CHEESE_ON_A_STICK = ITEMS.handheldItem(
    AddonNames.GRILLED_CHEESE_ON_A_STICK,
    { p -> CustomFoodItem(stickItem(p, AddonFoodValues.GRILLED_CHEESE_ON_A_STICK)) })

  val CARROT_CAKE_SLICE = ITEMS.simpleItem(
    AddonNames.CARROT_CAKE_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CARROT_CAKE_SLICE)) })
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = ITEMS.simpleItem(
    AddonNames.CARROT_CAKE_WITH_CHOCOLATE_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CARROT_CAKE_WITH_CHOCOLATE_SLICE)) })
  val CHICKEN_POT_PIE_SLICE = ITEMS.simpleItem(
    AddonNames.CHICKEN_POT_PIE_SLICE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHICKEN_POT_PIE_SLICE)) })
  val PUDDING_SLICE =
    ITEMS.simpleItem(AddonNames.PUDDING_SLICE, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.PUDDING_SLICE)) })

//  val PASTEL_DOUGH = ITEMS.simpleItem(AddonNames.PASTEL_DOUGH, { p -> Item(p) } )

  val SHRIMP = ITEMS.simpleItem(AddonNames.SHRIMP, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.SHRIMP)) })
  val COOKED_SHRIMP =
    ITEMS.simpleItem(AddonNames.COOKED_SHRIMP, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COOKED_SHRIMP)) })

  val TUCUPI = ITEMS.simpleItem(AddonNames.TUCUPI, { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.TUCUPI)) })

  //  val RAW_PASTEL = ITEMS.simpleItem(AddonNames.RAW_PASTEL, { p -> Item(p) } )
//  val PASTEL = ITEMS.simpleItem(AddonNames.PASTEL, { p -> Item(p) } )
  val FRIED_CASSAVA_WITH_BUTTER = ITEMS.simpleItem(
    AddonNames.FRIED_CASSAVA_WITH_BUTTER,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.FRIED_CASSAVA_WITH_BUTTER)) })
  val RAW_COXINHA =
    ITEMS.simpleItem(AddonNames.RAW_COXINHA, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.RAW_COXINHA)) })
  val COXINHA = ITEMS.simpleItem(AddonNames.COXINHA, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COXINHA)) })
  val RAW_CASSAVA_FRITTERS = ITEMS.simpleItem(
    AddonNames.RAW_CASSAVA_FRITTERS,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.RAW_CASSAVA_FRITTERS)) })
  val CASSAVA_FRITTERS = ITEMS.simpleItem(
    AddonNames.CASSAVA_FRITTERS,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CASSAVA_FRITTERS)) })
  val ROASTED_GARLIC =
    ITEMS.simpleItem(AddonNames.ROASTED_GARLIC, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.ROASTED_GARLIC)) })
  val COCONUT_DRINK =
    ITEMS.simpleItem(AddonNames.COCONUT_DRINK, { p -> CustomDrinkItem(foodItem(p, AddonFoodValues.COCONUT_DRINK)) })
  val COCONUT_MILK = ITEMS.simpleItem(
    AddonNames.COCONUT_MILK,
    { p -> MilkBottleItem(drinkItem(p, AddonFoodValues.COCONUT_MILK)) },
    AddonTags.ITEM.MILK
  )
  val GUARANA_SODA = ITEMS.simpleItem(
    AddonNames.GUARANA_SODA,
    { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.GUARANA_SODA), true) })
  val ACAI_CREAM =
    ITEMS.simpleItem(AddonNames.ACAI_CREAM, { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.ACAI_CREAM)) })
  val BRIGADEIRO_CREAM = ITEMS.simpleItem(
    AddonNames.BRIGADEIRO_CREAM,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.BRIGADEIRO_CREAM)) })
  val SWEET_LOVE_APPLE = ITEMS.handheldItem(
    AddonNames.SWEET_LOVE_APPLE,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.SWEET_LOVE_APPLE)) })
  val GARAPA = ITEMS.simpleItem(AddonNames.GARAPA, { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.GARAPA)) })
  val GUARANA_JUICE =
    ITEMS.simpleItem(AddonNames.GUARANA_JUICE, { p -> CustomDrinkItem(foodItem(p, AddonFoodValues.GUARANA_JUICE)) })
  val ACAI_TEA_WITH_GUARANA = ITEMS.simpleItem(
    AddonNames.ACAI_TEA_WITH_GUARANA,
    { p -> CustomDrinkItem(drinkItem(p, AddonFoodValues.ACAI_TEA_WITH_GUARANA)) })
  val BROA = ITEMS.simpleItem(AddonNames.BROA, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BROA)) })
  val COUSCOUS = ITEMS.simpleItem(AddonNames.COUSCOUS, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.COUSCOUS)) })
  val ANGU = ITEMS.simpleItem(AddonNames.ANGU, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.ANGU)) })
  val POPCORN = ITEMS.simpleItem(AddonNames.POPCORN, { p -> CustomFoodItem(bucketItem(p, AddonFoodValues.POPCORN)) })
  val CHEESE_BREAD_DOUGH = ITEMS.simpleItem(
    AddonNames.CHEESE_BREAD_DOUGH,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHEESE_BREAD_DOUGH)) })
  val CHEESE_BREAD =
    ITEMS.simpleItem(AddonNames.CHEESE_BREAD, { p -> CustomFoodItem(foodItem(p, AddonFoodValues.CHEESE_BREAD)) })
  val BRAZILIAN_DINNER = ITEMS.simpleItem(
    AddonNames.BRAZILIAN_DINNER,
    { p -> CustomFoodItem(foodItem(p, AddonFoodValues.BRAZILIAN_DINNER)) })
  val TROPEIRO_BEANS =
    ITEMS.simpleItem(
      AddonNames.TROPEIRO_BEANS,
      { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.TROPEIRO_BEANS)) })
  val COOKED_BEANS =
    ITEMS.simpleItem(AddonNames.COOKED_BEANS, { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COOKED_BEANS)) })
  val COLLARD_GREENS_FAROFA = ITEMS.simpleItem(
    AddonNames.COLLARD_GREENS_FAROFA,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COLLARD_GREENS_FAROFA)) })
  val COLLARD_GREENS_SALAD = ITEMS.simpleItem(
    AddonNames.COLLARD_GREENS_SALAD,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.COLLARD_GREENS_SALAD)) })
  val PLATE_OF_STROGANOFF = ITEMS.simpleItem(
    AddonNames.PLATE_OF_STROGANOFF,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_STROGANOFF)) })
  val PLATE_OF_GREEN_SOUP = ITEMS.simpleItem(
    AddonNames.PLATE_OF_GREEN_SOUP,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_GREEN_SOUP)) })
  val PLATE_OF_FISH_MOQUECA = ITEMS.simpleItem(
    AddonNames.PLATE_OF_FISH_MOQUECA,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_FISH_MOQUECA)) })
  val PLATE_OF_FEIJOADA = ITEMS.simpleItem(
    AddonNames.PLATE_OF_FEIJOADA,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_FEIJOADA)) })
  val FRIED_FISH_WITH_ACAI = ITEMS.simpleItem(AddonNames.FRIED_FISH_WITH_ACAI, { p -> Item(p) })
  val PLATE_OF_FRIED_FISH_WITH_ACAI = ITEMS.simpleItem(
    AddonNames.PLATE_OF_FRIED_FISH_WITH_ACAI,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.PLATE_OF_FRIED_FISH_WITH_ACAI)) })
  val SALPICAO = ITEMS.simpleItem(
    AddonNames.SALPICAO,
    { p -> CustomFoodItem(bowlFoodItem(p, AddonFoodValues.SALPICAO)) })
}