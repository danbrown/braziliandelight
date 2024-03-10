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
    .nutrition(1)
    .saturationMod(0.2f)
    .effect( {MobEffectInstance(MobEffects.HUNGER, INSTANT_DURATION, 0) }, 1.0f)
    .build();
  val BEAN = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();

  // Drinks

  // Foods

  // Plated

  // Soups

  // other
  val GUARANA_POWDER = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .effect( {MobEffectInstance(MobEffects.MOVEMENT_SPEED, TINY_DURATION, 0) }, 1.0f)
    .effect( {MobEffectInstance(MobEffects.DIG_SPEED, TINY_DURATION, 0) }, 1.0f)
    .build();
  val CASSAVA_FLOUR = FoodProperties.Builder()
    .nutrition(1)
    .saturationMod(0.2f)
    .build();

}