package com.dannbrown.braziliandelight.datagen.recipe

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.databoxlib.registry.datagen.DataboxRecipeProvider
import com.tterrag.registrate.util.DataIngredient
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Items

class AddonRecipeGen(generator: DataGenerator) : DataboxRecipeProvider(generator.packOutput, AddonContent.MOD_ID) {


  val SLICE_FROM_MINAS_CHEESE = crafting({ AddonItems.MINAS_CHEESE_SLICE.get() }) { b ->
    b.shapeless(4, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.MINAS_CHEESE.get())))
  }

  val MINAS_CHEESE_TO_SLICE = crafting({ AddonBlocks.MINAS_CHEESE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
      DataIngredient.items(AddonItems.MINAS_CHEESE_SLICE.get()),
    ))
  }

  val SLICE_FROM_CARROT_CAKE = crafting({ AddonItems.CARROT_CAKE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE.get())))
  }

  val CARROT_CAKE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_SLICE.get()),
    ))
  }

  val SLICE_FROM_CARROT_CAKE_WITH_CHOCOLATE = crafting({ AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get() }) { b ->
    b.shapeless(7, "", "_from_block", listOf(DataIngredient.items(AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get())))
  }

  val CARROT_CAKE_WITH_CHOCOLATE_TO_SLICE = crafting({ AddonBlocks.CARROT_CAKE_WITH_CHOCOLATE.get() }) { b ->
    b.shapeless(1, "", "_from_slice", listOf(
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
      DataIngredient.items(AddonItems.CARROT_CAKE_WITH_CHOCOLATE_SLICE.get()),
    ))
  }

  val MINAS_CHEESE_ON_A_STICK = crafting({ AddonItems.MINAS_CHEESE_ON_A_STICK.get() }) { b ->
    b
      .shaped(1) { c ->
        c
          .pattern("  C")
          .pattern(" C ")
          .pattern("S  ")
          .define('C', AddonItems.MINAS_CHEESE_SLICE.get())
          .define('S', Items.STICK)
      }
  }

  val MINAS_CHEESE_ON_A_STICK_INVERTED = crafting({ AddonItems.MINAS_CHEESE_ON_A_STICK.get() }) { b ->
    b
      .suffix("_inverted")
      .shaped(1) { c ->
        c
          .pattern("C  ")
          .pattern(" C ")
          .pattern("  S")
          .define('C', AddonItems.MINAS_CHEESE_SLICE.get())
          .define('S', Items.STICK)
      }
  }

  val GRILLED_CHEESE_ON_A_STICK = cooking(
    { DataIngredient.items(AddonItems.MINAS_CHEESE_ON_A_STICK.get()) },
    { AddonItems.GRILLED_CHEESE_ON_A_STICK.get() }
  ) { b -> b
    .comboFoodCooking(300, 4f)
  }



}