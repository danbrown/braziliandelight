package com.dannbrown.braziliandelight.init

import kotlinx.coroutines.CoroutineName
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import vectorwing.farmersdelight.common.registry.ModEffects

object AddonFoodValues {
  const val INSTANT_DURATION: Int = 100 // 5 seconds
  const val TINY_DURATION: Int = 300 // 15 seconds
  const val BRIEF_DURATION: Int = 600 // 30 seconds
  const val SHORT_DURATION: Int = 1200 // 1 minute
  const val MEDIUM_DURATION: Int = 3600 // 3 minutes
  const val LONG_DURATION: Int = 6000 // 5 minutes
  const val VERY_LONG_DURATION: Int = 9600 // 8 minutes

  // Crops
  val ACAI = FoodProperties.Builder()
    .nutrition(2)
    .saturationMod(0.4f)
    .build();
  val GUARANA = FoodProperties.Builder()
    .nutrition(2)
    .saturationMod(0.4f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, INSTANT_DURATION, 0) }, 1.0f)
    .effect({ MobEffectInstance(MobEffects.DIG_SPEED, INSTANT_DURATION, 0) }, 1.0f)
    .build();
  val CASSAVA = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.HUNGER, INSTANT_DURATION, 0) }, 1.0f)
    .alwaysEat()
    .build();
  val BEAN = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val COLLARD_GREENS = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val COFFEE_BERRIES = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val COFFEE_BEANS = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val GARLIC = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.HUNGER, INSTANT_DURATION, 0) }, 1.0f)
    .alwaysEat()
    .build();

  // Drinks
  val GARAPA = FoodProperties.Builder()
    .fast()
    .alwaysEat()
    .nutrition(2)
    .saturationMod(0.4f)
    .build();
  val GUARANA_SODA = FoodProperties.Builder()
    .fast()
    .alwaysEat()
    .nutrition(2)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .effect({ MobEffectInstance(MobEffects.DIG_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .build();

  // Foods
  val MINAS_CHEESE_ON_A_STICK = FoodProperties.Builder()
    .fast()
    .nutrition(5)
    .saturationMod(0.2f)
    .build();
  val GRILLED_CHEESE_ON_A_STICK = FoodProperties.Builder()
    .fast()
    .nutrition(6)
    .saturationMod(0.8f)
    .build();
  val SWEET_LOVE_APPLE = FoodProperties.Builder()
    .fast()
    .nutrition(5)
    .saturationMod(0.9f)
    .build();
  val ROASTED_GARLIC = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .effect({ MobEffectInstance(AddonEffects.REPUGNANT.get(), BRIEF_DURATION, 1) }, 1.0f)
    .saturationMod(0.2f)
    .build();
  val RAW_COXINHA = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val COXINHA = FoodProperties.Builder()
    .fast()
    .nutrition(6)
    .saturationMod(0.8f)
    .build();
  val SHRIMP = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val COOKED_SHRIMP = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val RAW_CASSAVA_FRITTERS = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val CASSAVA_FRITTERS = FoodProperties.Builder()
    .fast()
    .nutrition(6)
    .saturationMod(0.8f)
    .build();
  val ACAI_CREAM = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.4f)
    .build();
  val BRIGADEIRO_CREAM = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.4f)
    .build();

  // Plated

  // Soups

  // Slices
  val CARROT_CAKE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.4f)
    .build();
  val CARROT_CAKE_WITH_CHOCOLATE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(5)
    .saturationMod(0.8f)
    .build();
  val MINAS_CHEESE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val CHICKEN_POT_PIE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.8f)
    .build();
  val PUDDING_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.6f)
    .build();

  // other
  val GUARANA_POWDER = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .effect({ MobEffectInstance(MobEffects.DIG_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val CASSAVA_FLOUR = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val BUTTER = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val COCONUT_MILK = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .build();
  val COCONUT_SLICE = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.2f)
    .build();
  val CORN_FLOUR = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val BUTTERED_CORN = FoodProperties.Builder()
    .nutrition(4)
    .saturationMod(0.4f)
    .build();
  val YERBA_MATE_LEAVES = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val DRIED_YERBA_MATE = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val CHIMARRAO = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(1f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, SHORT_DURATION, 0) }, 1.0f)
    .build();
  val LEMON = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect({ MobEffectInstance(MobEffects.CONFUSION, TINY_DURATION, 1) }, 1.0f)
    .build();
  val LEMON_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .effect({ MobEffectInstance(MobEffects.CONFUSION, TINY_DURATION, 1) }, 1.0f)
    .build();
  val LEMONADE = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(4)
    .saturationMod(0.8f)
    .build();
  val COLLARD_LEMONADE = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(5)
    .saturationMod(0.8f)
    .build();
  val HEAVY_CREAM_BUCKET = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val CONDENSED_MILK = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.3f)
    .build();
  val TUCUPI = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val FRIED_CASSAVA_WITH_BUTTER = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val COCONUT_DRINK = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(2)
    .saturationMod(1f)
    .build();
  val BROA = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val COUSCOUS = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.2f)
    .build();
  val ANGU = FoodProperties.Builder()
    .nutrition(2)
    .saturationMod(0.5f)
    .build();
  val CHEESE_BREAD_DOUGH = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();
  val CHEESE_BREAD = FoodProperties.Builder()
    .nutrition(6)
    .saturationMod(0.8f)
    .build();
  val BRAZILIAN_DINNER = FoodProperties.Builder()
    .nutrition(10)
    .saturationMod(1f)
    .effect({ MobEffectInstance(ModEffects.NOURISHMENT.get(), LONG_DURATION, 1) }, 1.0f)
    .build();
  val BREAD_WITH_BUTTER = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(0.8f)
    .build();
  val GARLIC_BREAD = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(0.8f)
    .effect({ MobEffectInstance(AddonEffects.REPUGNANT.get(), BRIEF_DURATION, 1) }, 1.0f)
    .build();
  val CHICKEN_SAUCE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.8f)
    .build();
  val TROPEIRO_BEANS = FoodProperties.Builder()
    .nutrition(8)
    .saturationMod(0.7f)
    .build();
  val COOKED_BEANS = FoodProperties.Builder()
    .nutrition(4)
    .saturationMod(0.4f)
    .build();
  val COLLARD_GREENS_FAROFA = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(0.8f)
    .build();
  val COLLARD_GREENS_SALAD = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.2f)
    .build();
  val PLATE_OF_STROGANOFF = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(0.8f)
    .effect({ MobEffectInstance(ModEffects.NOURISHMENT.get(), MEDIUM_DURATION, 1) }, 1.0f)
    .build();
  val PLATE_OF_GREEN_SOUP = FoodProperties.Builder()
    .nutrition(4)
    .saturationMod(0.6f)
    .effect({ MobEffectInstance(ModEffects.COMFORT.get(), MEDIUM_DURATION, 1) }, 1.0f)
    .build();
  val PLATE_OF_FISH_MOQUECA = FoodProperties.Builder()
    .nutrition(5)
    .saturationMod(0.8f)
    .effect({ MobEffectInstance(ModEffects.COMFORT.get(), SHORT_DURATION, 1) }, 1.0f)
    .build();
  val PLATE_OF_FEIJOADA = FoodProperties.Builder()
    .nutrition(4)
    .saturationMod(1f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, TINY_DURATION, 1) }, 1.0f)
    .effect({ MobEffectInstance(ModEffects.COMFORT.get(), MEDIUM_DURATION, 1) }, 1.0f)
    .build();
  val PLATE_OF_FRIED_FISH_WITH_ACAI = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val SALPICAO = FoodProperties.Builder()
    .nutrition(3)
    .saturationMod(0.6f)
    .build();
  val GUARANA_JUICE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.6f)
    .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .effect({ MobEffectInstance(MobEffects.DIG_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .build();
  val ACAI_TEA_WITH_GUARANA = FoodProperties.Builder()
    .fast()
    .nutrition(4)
    .saturationMod(0.6f)
    .build();
  val COOKED_CORN = FoodProperties.Builder()
    .nutrition(2)
    .saturationMod(0.3f)
    .build();
  val CORN = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
}