package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.effect.RepugnantEffect
import com.dannbrown.braziliandelight.lib.AddonNames
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AddonEffects {
  val EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AddonContent.MOD_ID)
  val POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, AddonContent.MOD_ID)

  fun register(modBus: IEventBus){
    EFFECTS.register(modBus)
    POTIONS.register(modBus)
  }

  val REPUGNANT = EFFECTS.register(AddonNames.REPUGNANT) { RepugnantEffect() }
//  val REPULSIVE_POTION = POTIONS.register(AddonNames.REPULSIVE) { Potion(MobEffectInstance(REPULSIVE.get(), 3600)) }
//  val STRONG_REPULSIVE_POTION = POTIONS.register(AddonNames.STRONG_REPULSIVE) { Potion(MobEffectInstance(REPULSIVE.get(), 1800, 1)) }
//  val STRONGER_REPULSIVE_POTION = POTIONS.register(AddonNames.STRONGER_REPULSIVE) { Potion(MobEffectInstance(REPULSIVE.get(), 900, 2)) }
//
//  private fun createPotion(potion: Potion): ItemStack {
//    return PotionUtils.setPotion(ItemStack(Items.POTION), potion)
//  }
//
//  fun addRecipes(){
//    BrewingRecipeRegistry.addRecipe(BrewingRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(AddonItems.GARLIC_BULB.get()), createPotion(REPULSIVE_POTION.get())))
//    BrewingRecipeRegistry.addRecipe(BrewingRecipe(Ingredient.of(createPotion(REPULSIVE_POTION.get())), Ingredient.of(Items.REDSTONE), createPotion(STRONG_REPULSIVE_POTION.get())))
//    BrewingRecipeRegistry.addRecipe(BrewingRecipe(Ingredient.of(createPotion(STRONG_REPULSIVE_POTION.get())), Ingredient.of(Items.REDSTONE), createPotion(STRONGER_REPULSIVE_POTION.get())))
//  }
}