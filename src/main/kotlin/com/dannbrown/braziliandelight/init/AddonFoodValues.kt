package com.dannbrown.braziliandelight.init

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties



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
    .effect( {MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .effect( {MobEffectInstance(MobEffects.DIG_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val CASSAVA = FoodProperties.Builder()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect( {MobEffectInstance(MobEffects.HUNGER, INSTANT_DURATION, 0) }, 1.0f)
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
    .effect( {MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val GARLIC = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect( {MobEffectInstance(MobEffects.HUNGER, INSTANT_DURATION, 0) }, 1.0f)
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
    .effect( {MobEffectInstance(MobEffects.MOVEMENT_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .effect( {MobEffectInstance(MobEffects.DIG_SPEED, BRIEF_DURATION, 1) }, 1.0f)
    .build();

  // Foods
  val MINAS_CHEESE_ON_A_STICK = FoodProperties.Builder()
    .fast()
    .nutrition(4)
    .saturationMod(0.6f)
    .build();
  val GRILLED_CHEESE_ON_A_STICK = FoodProperties.Builder()
    .fast()
    .nutrition(5)
    .saturationMod(0.7f)
    .build();
  val SWEET_LOVE_APPLE = FoodProperties.Builder()
    .fast()
    .nutrition(4)
    .saturationMod(0.7f)
    .build();
  val ROASTED_GARLIC = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val RAW_COXINHA = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val COXINHA = FoodProperties.Builder()
    .fast()
    .nutrition(4)
    .saturationMod(0.7f)
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
    .nutrition(4)
    .saturationMod(0.6f)
    .build();
  val ACAI_CREAM = FoodProperties.Builder()
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
    .nutrition(3)
    .saturationMod(0.5f)
    .build();
  val MINAS_CHEESE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();
  val CHICKEN_POT_PIE_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(3)
    .saturationMod(0.4f)
    .build();
  val PUDDING_SLICE = FoodProperties.Builder()
    .fast()
    .nutrition(2)
    .saturationMod(0.2f)
    .build();

  // other
  val GUARANA_POWDER = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect( {MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .effect( {MobEffectInstance(MobEffects.DIG_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val CASSAVA_FLOUR = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();
  val BUTTER = FoodProperties.Builder()
    .alwaysEat()
    .fast()
    .nutrition(1)
    .saturationMod(0.1f)
    .build();


}