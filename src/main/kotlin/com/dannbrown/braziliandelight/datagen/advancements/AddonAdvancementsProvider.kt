package com.dannbrown.braziliandelight.datagen.advancements

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.DisplayInfo
import net.minecraft.advancements.FrameType
import net.minecraft.advancements.RequirementsStrategy
import net.minecraft.advancements.critereon.BlockPredicate
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger
import net.minecraft.advancements.critereon.LocationPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.event.ContainerScreenEvent.Render.Background
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.ForgeAdvancementProvider
import java.util.function.Consumer

class AddonAdvancementsProvider : ForgeAdvancementProvider.AdvancementGenerator {

  companion object {
    val ROOT_ADVANCEMENT_KEY = "root"

    val TROPICAL_SEEDS_KEY = "tropical_seeds"
    val GARLIC_CROP_KEY = "garlic_crop"
    val BEANS_CROP_KEY = "beans_crop"
    val CORN_CROP_KEY = "corn_crop"
    val CASSAVA_CROP_KEY = "cassava_crop"
    val COFFEE_CROP_KEY = "coffee_crop"
    val GUARANA_CROP_KEY = "guarana_crop"
    val COLLARD_GREENS_CROP_KEY = "collard_greens_crop"
    val WHITE_KERNELS_SPECIAL_KEY = "white_kernels_crop"
    val YERBA_MATE_CROP_KEY = "yerba_mate_crop"

    val GARLIC_REPUGNANT_KEY = "garlic_repugnant"
    val FEIJOADA_CRAFT_KEY = "feijoada_craft"
    val GREEN_SOUP_CRAFT_KEY = "green_soup_craft"
    val COUSCOUS_CRAFT_KEY = "couscous_craft"
    val GUARANA_DRINK_CRAFT_KEY = "guarana_drink_craft"

    val TROPICAL_SAPLINGS_KEY = "tropical_saplings"
    val LEMON_ITEM_KEY = "lemon_item"
    val ACAI_BERRY_ITEM_KEY = "acai_berry_item"
    val COCONUT_ITEM_KEY = "coconut_item"

    val FISH_MOQUECA_CRAFT_KEY = "fish_moqueca_craft"
    val COCONUT_CREAM_CRAFT_KEY = "coconut_cream_craft"
    val ACAI_CREAM_CRAFT_KEY = "acai_cream_craft"

    val MILK_POT_ADVANCEMENT_KEY = "milk_pot"
    val HEAVY_CREAM_POT_ADVANCEMENT_KEY = "heavy_cream_pot"
    val CHEESE_MAKING_KEY = "cheese_making"
    val SALT_BUCKET_CRAFT_KEY = "salt_bucket_craft"
    val CONDENSED_MILK_CRAFT_KEY = "condensed_milk_craft"
    val BRIGADEIRO_CRAFT_KEY = "brigadeiro_craft"
    val PUDDING_CRAFT_KEY = "pudding_craft"
    val COCONUT_DRINK_CRAFT_KEY = "coconut_drink_craft"
    val FRIED_FISH_WITH_ACAI_CRAFT_KEY = "fried_fish_with_acai_craft"
    val COXINHA_CRAFT_KEY = "coxinha_craft"
    val CHEESE_BREAD_CRAFT_KEY = "cheese_bread_craft"
    val CASSAVA_FRITTERS_CRAFT_KEY = "cassava_fritters_craft"
    val CHIMARRAO_CRAFT_KEY = "chimarrao_craft"


    val BACKGROUND = ResourceLocation(AddonContent.MOD_ID, "textures/block/lemon_leaves.png")
  }

  private val SAPLINGS = arrayOf(
    AddonBlocks.LEMON_SAPLING,
    AddonBlocks.ACAI_PALM_SAPLING,
    AddonBlocks.COCONUT_PALM_SAPLING
  )

  private val SEEDS = arrayOf(
    AddonBlocks.COLLARD_GREENS_CROP,
    AddonBlocks.GARLIC_CROP,
    AddonBlocks.BUDDING_BEANS_CROP,
    AddonBlocks.CARIOCA_BEANS_CROP,
    AddonBlocks.BUDDING_CORN,
    AddonBlocks.BUDDING_CASSAVA,
    AddonBlocks.BUDDING_COFFEE,
    AddonBlocks.WHITE_KERNELS_CROP
  )

  override fun generate(
    registries: HolderLookup.Provider,
    saver: Consumer<Advancement>,
    existingFileHelper: ExistingFileHelper
  ) {
    val ROOT_ADVANCEMENT = hasItemsCriterion(
      basicAdvancement(AddonItems.BRAZIL_FLAG.get(), ROOT_ADVANCEMENT_KEY, BACKGROUND),
      saver,
      existingFileHelper,
      ROOT_ADVANCEMENT_KEY,
      RequirementsStrategy.OR,
      *SAPLINGS,
      *SEEDS
    )

    // SEEDS TREE
    val TROPICAL_SEEDS = hasItemsCriterion(
      basicAdvancement(AddonBlocks.BUDDING_GUARANA.asItem(), TROPICAL_SEEDS_KEY).parent(ROOT_ADVANCEMENT),
      saver,
      existingFileHelper,
      TROPICAL_SEEDS_KEY,
      RequirementsStrategy.OR,
      *SEEDS
    )

    val GARLIC_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.GARLIC_BULB.get(), GARLIC_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      GARLIC_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.GARLIC_BULB.get()
    )

    val GARLIC_REPUGNANT = hasItemsCriterion(
      basicAdvancement(AddonItems.REPUGNANT_ARROW.get(), GARLIC_REPUGNANT_KEY).parent(GARLIC_CROP),
      saver,
      existingFileHelper,
      GARLIC_REPUGNANT_KEY,
      RequirementsStrategy.OR,
      AddonItems.REPUGNANT_ARROW.get()
    )

    val BEANS_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.BEAN_POD.get(), BEANS_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      BEANS_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.BEAN_POD.get()
    )

    val FEIJOADA_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonBlocks.FEIJOADA_POT.asItem(), FEIJOADA_CRAFT_KEY).parent(BEANS_CROP),
      saver,
      existingFileHelper,
      FEIJOADA_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.FEIJOADA_POT.asItem()
    )

    val GREEN_SOUP_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonBlocks.GREEN_SOUP_POT.asItem(), GREEN_SOUP_CRAFT_KEY).parent(BEANS_CROP),
      saver,
      existingFileHelper,
      GREEN_SOUP_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.GREEN_SOUP_POT.asItem()
    )

    val CORN_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.CORN.get(), CORN_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      CORN_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.CORN.get()
    )

    val COUSCOUS_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.COUSCOUS.asItem(), COUSCOUS_CRAFT_KEY).parent(CORN_CROP),
      saver,
      existingFileHelper,
      COUSCOUS_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.COUSCOUS.asItem()
    )

    val CASSAVA_CROP = hasItemsCriterion(
      basicAdvancement(AddonBlocks.BUDDING_CASSAVA.asItem(), CASSAVA_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      CASSAVA_CROP_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.BUDDING_CASSAVA.asItem()
    )

    val CASSAVA_FRITTERS_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.CASSAVA_FRITTERS.asItem(), CASSAVA_FRITTERS_CRAFT_KEY).parent(CASSAVA_CROP),
      saver,
      existingFileHelper,
      CASSAVA_FRITTERS_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.CASSAVA_FRITTERS.asItem()
    )

    val COXINHA_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.COXINHA.asItem(), COXINHA_CRAFT_KEY).parent(CASSAVA_CROP),
      saver,
      existingFileHelper,
      COXINHA_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.COXINHA.asItem()
    )

    val COFFEE_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.COFFEE_BERRIES.asItem(), COFFEE_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      COFFEE_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.COFFEE_BERRIES.asItem()
    )

    val GUARANA_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.GUARANA_FRUIT.asItem(), GUARANA_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      GUARANA_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.GUARANA_FRUIT.asItem()
    )

    val GUARANA_DRINK_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.GUARANA_SODA.asItem(), GUARANA_DRINK_CRAFT_KEY).parent(GUARANA_CROP),
      saver,
      existingFileHelper,
      GUARANA_DRINK_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.GUARANA_SODA.asItem()
    )

    val COLLARD_GREENS_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.COLLARD_GREENS.asItem(), COLLARD_GREENS_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      COLLARD_GREENS_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.COLLARD_GREENS.asItem()
    )

    val YERBA_MATE_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.YERBA_MATE_LEAVES.asItem(), YERBA_MATE_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      YERBA_MATE_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.YERBA_MATE_LEAVES.asItem()
    )

    val WHITE_KERNELS_SPECIAL = hasItemsCriterion(
      basicAdvancement(AddonBlocks.WHITE_KERNELS_CROP.asItem(), WHITE_KERNELS_SPECIAL_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      WHITE_KERNELS_SPECIAL_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.WHITE_KERNELS_CROP.asItem()
    )

    // SAPLINGS TREE
    val TROPICAL_SAPLINGS = hasItemsCriterion(
      basicAdvancement(AddonBlocks.COCONUT_PALM_SAPLING.asItem(), TROPICAL_SAPLINGS_KEY).parent(ROOT_ADVANCEMENT),
      saver,
      existingFileHelper,
      TROPICAL_SAPLINGS_KEY,
      RequirementsStrategy.OR,
      *SAPLINGS
    )

    val LEMON_ITEM = hasItemsCriterion(
      basicAdvancement(AddonItems.LEMON.get(), LEMON_ITEM_KEY).parent(TROPICAL_SAPLINGS),
      saver,
      existingFileHelper,
      LEMON_ITEM_KEY,
      RequirementsStrategy.OR,
      AddonItems.LEMON.get()
    )

    val FISH_MOQUECA_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonBlocks.FISH_MOQUECA_POT.asItem(), FISH_MOQUECA_CRAFT_KEY).parent(LEMON_ITEM),
      saver,
      existingFileHelper,
      FISH_MOQUECA_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.FISH_MOQUECA_POT.asItem()
    )

    val ACAI_BERRY_ITEM = hasItemsCriterion(
      basicAdvancement(AddonBlocks.BUDDING_ACAI_BRANCH.asItem(), ACAI_BERRY_ITEM_KEY).parent(TROPICAL_SAPLINGS),
      saver,
      existingFileHelper,
      ACAI_BERRY_ITEM_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.BUDDING_ACAI_BRANCH.asItem()
    )

    val ACAI_CREAM_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.ACAI_CREAM.get(), ACAI_CREAM_CRAFT_KEY).parent(ACAI_BERRY_ITEM),
      saver,
      existingFileHelper,
      ACAI_CREAM_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.ACAI_CREAM.get()
    )

    val FRIED_FISH_WITH_ACAI_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.FRIED_FISH_WITH_ACAI.asItem(), FRIED_FISH_WITH_ACAI_CRAFT_KEY).parent(ACAI_BERRY_ITEM),
      saver,
      existingFileHelper,
      FRIED_FISH_WITH_ACAI_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.FRIED_FISH_WITH_ACAI.asItem()
    )

    val COCONUT_ITEM = hasItemsCriterion(
      basicAdvancement(AddonBlocks.COCONUT.asItem(), COCONUT_ITEM_KEY).parent(TROPICAL_SAPLINGS),
      saver,
      existingFileHelper,
      COCONUT_ITEM_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.COCONUT.asItem()
    )

    val COOCNUT_DRINK_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.COCONUT_DRINK.get(), COCONUT_DRINK_CRAFT_KEY).parent(COCONUT_ITEM),
      saver,
      existingFileHelper,
      COCONUT_DRINK_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.COCONUT_DRINK.get()
    )

    val COCONUT_CREAM_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.COCONUT_CREAM.get(), COCONUT_CREAM_CRAFT_KEY).parent(COCONUT_ITEM),
      saver,
      existingFileHelper,
      COCONUT_CREAM_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.COCONUT_CREAM.get()
    )

    // CHEESE MAKING TREE
    val MILK_POT_ADVANCEMENT = usedOnBlockCriterion(
      basicAdvancement(Items.MILK_BUCKET, MILK_POT_ADVANCEMENT_KEY).parent(ROOT_ADVANCEMENT),
      saver,
      existingFileHelper,
      MILK_POT_ADVANCEMENT_KEY,
      RequirementsStrategy.AND,
      mapOf(Items.MILK_BUCKET to AddonBlocks.MILK_POT.get())
    )


    val HEAVY_CREAM_POT_ADVANCEMENT = hasItemsCriterion(
      basicAdvancement(AddonItems.HEAVY_CREAM_BUCKET.get(), HEAVY_CREAM_POT_ADVANCEMENT_KEY).parent(MILK_POT_ADVANCEMENT),
      saver,
      existingFileHelper,
      HEAVY_CREAM_POT_ADVANCEMENT_KEY,
      RequirementsStrategy.AND,
      AddonItems.HEAVY_CREAM_BUCKET.get()
    )

    val CONDENSED_MILK_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.CONDENSED_MILK.get(), CONDENSED_MILK_CRAFT_KEY).parent(HEAVY_CREAM_POT_ADVANCEMENT),
      saver,
      existingFileHelper,
      CONDENSED_MILK_CRAFT_KEY,
      RequirementsStrategy.AND,
      AddonItems.CONDENSED_MILK.get()
    )

    val CHEESE_MAKING_ADVANCEMENT = usedOnBlockCriterion(
      basicAdvancement(AddonBlocks.MINAS_CHEESE.asItem(), CHEESE_MAKING_KEY).parent(HEAVY_CREAM_POT_ADVANCEMENT),
      saver,
      existingFileHelper,
      CHEESE_MAKING_KEY,
      RequirementsStrategy.AND,
      mapOf(AddonItems.LEMON to AddonBlocks.HEAVY_CREAM_POT.get(), AddonItems.SALT to AddonBlocks.HEAVY_CREAM_POT.get())
    )

    val CHEESE_BREAD_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.CHEESE_BREAD.get(), CHEESE_BREAD_CRAFT_KEY).parent(CHEESE_MAKING_ADVANCEMENT),
      saver,
      existingFileHelper,
      CHEESE_BREAD_CRAFT_KEY,
      RequirementsStrategy.AND,
      AddonItems.CHEESE_BREAD.get()
    )

    val SALT_BUCKET_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.SALT_BUCKET.get(), SALT_BUCKET_CRAFT_KEY).parent(ROOT_ADVANCEMENT),
      saver,
      existingFileHelper,
      SALT_BUCKET_CRAFT_KEY,
      RequirementsStrategy.AND,
      AddonItems.SALT_BUCKET.get()
    )

    val BRIGADEIRO_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.BRIGADEIRO_CREAM.get(), BRIGADEIRO_CRAFT_KEY).parent(CONDENSED_MILK_CRAFT),
      saver,
      existingFileHelper,
      BRIGADEIRO_CRAFT_KEY,
      RequirementsStrategy.AND,
      AddonItems.BRIGADEIRO_CREAM.get()
    )

    val PUDDING_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonBlocks.PUDDING.asItem(), PUDDING_CRAFT_KEY).parent(CONDENSED_MILK_CRAFT),
      saver,
      existingFileHelper,
      PUDDING_CRAFT_KEY,
      RequirementsStrategy.AND,
      AddonBlocks.PUDDING.asItem()
    )

    val CHIMARRAO_CRAFT = hasItemsCriterion(
      basicAdvancement(AddonItems.CHIMARRAO.asItem(), CHIMARRAO_CRAFT_KEY).parent(YERBA_MATE_CROP),
      saver,
      existingFileHelper,
      CHIMARRAO_CRAFT_KEY,
      RequirementsStrategy.OR,
      AddonItems.CHIMARRAO.asItem()
    )
  }





  private fun advancementTitle(key: String): Component {
    return Component.translatable("advancements.${AddonContent.MOD_ID}.${key}.title")
  }

  private fun advancementDescription(key: String): Component {
    return Component.translatable("advancements.${AddonContent.MOD_ID}.${key}.description")
  }

  private fun basicAdvancement(iconItem: Item, key: String, background: ResourceLocation? = null): Advancement.Builder {
    return Advancement.Builder.advancement()
      .display(
        DisplayInfo(
          ItemStack(iconItem),
          advancementTitle(key),
          advancementDescription(key),
          background,
          FrameType.TASK,
          true,
          true,
          false
        )
      )
  }

  private fun hasItemsCriterion(builder: Advancement.Builder, saver: Consumer<Advancement>, existingFileHelper: ExistingFileHelper, savePath: String, requirementsStrategy: RequirementsStrategy, vararg items: ItemLike): Advancement {
    for (item in items) {
      builder.addCriterion(
        "has_${item.asItem().descriptionId}",
        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(item).build())
      )
    }
    return builder
      .requirements(requirementsStrategy)
      .save(saver, ResourceLocation(AddonContent.MOD_ID, savePath), existingFileHelper)
  }

  private fun usedOnBlockCriterion(builder: Advancement.Builder, saver: Consumer<Advancement>, existingFileHelper: ExistingFileHelper, savePath: String, requirementsStrategy: RequirementsStrategy, itemOnBlock: Map<ItemLike, Block>): Advancement {
    for ((item, block) in itemOnBlock) {
      builder.addCriterion("used_${item.asItem().descriptionId}_on_${block.descriptionId}",
        ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
          LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).build()),
          ItemPredicate.Builder.item().of(item)
        )
      )
    }
    return builder
      .requirements(requirementsStrategy)
      .save(saver, ResourceLocation(AddonContent.MOD_ID, savePath), existingFileHelper)
  }
}