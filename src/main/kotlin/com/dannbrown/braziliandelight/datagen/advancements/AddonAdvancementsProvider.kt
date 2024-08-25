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

    val TROPICAL_SAPLINGS_KEY = "tropical_saplings"
    val LEMON_ITEM_KEY = "lemon_item"
    val ACAI_BERRY_ITEM_KEY = "acai_berry_item"
    val COCONUT_ITEM_KEY = "coconut_item"

    val MILK_POT_ADVANCEMENT_KEY = "milk_pot"
    val HEAVY_CREAM_POT_ADVANCEMENT_KEY = "heavy_cream_pot"
    val CHEESE_MAKING_KEY = "cheese_making"

    val BACKGROUND = ResourceLocation("minecraft", "textures/block/jungle_leaves.png")
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

    val BEANS_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.BEAN_POD.get(), BEANS_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      BEANS_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.BEAN_POD.get()
    )

    val CORN_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.CORN.get(), CORN_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      CORN_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.CORN.get()
    )

    val CASSAVA_CROP = hasItemsCriterion(
      basicAdvancement(AddonBlocks.BUDDING_CASSAVA.asItem(), CASSAVA_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      CASSAVA_CROP_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.BUDDING_CASSAVA.asItem()
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

    val COLLARD_GREENS_CROP = hasItemsCriterion(
      basicAdvancement(AddonItems.COLLARD_GREENS.asItem(), COLLARD_GREENS_CROP_KEY).parent(TROPICAL_SEEDS),
      saver,
      existingFileHelper,
      COLLARD_GREENS_CROP_KEY,
      RequirementsStrategy.OR,
      AddonItems.COLLARD_GREENS.asItem()
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

    val ACAI_BERRY_ITEM = hasItemsCriterion(
      basicAdvancement(AddonBlocks.BUDDING_ACAI_BRANCH.asItem(), ACAI_BERRY_ITEM_KEY).parent(TROPICAL_SAPLINGS),
      saver,
      existingFileHelper,
      ACAI_BERRY_ITEM_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.BUDDING_ACAI_BRANCH.asItem()
    )

    val COCONUT_ITEM = hasItemsCriterion(
      basicAdvancement(AddonBlocks.COCONUT.asItem(), COCONUT_ITEM_KEY).parent(TROPICAL_SAPLINGS),
      saver,
      existingFileHelper,
      COCONUT_ITEM_KEY,
      RequirementsStrategy.OR,
      AddonBlocks.COCONUT.asItem()
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

    val CHEESE_MAKING_ADVANCEMENT = usedOnBlockCriterion(
      basicAdvancement(AddonBlocks.MINAS_CHEESE.asItem(), CHEESE_MAKING_KEY).parent(HEAVY_CREAM_POT_ADVANCEMENT),
      saver,
      existingFileHelper,
      CHEESE_MAKING_KEY,
      RequirementsStrategy.AND,
      mapOf(AddonItems.LEMON to AddonBlocks.HEAVY_CREAM_POT.get(), AddonItems.SALT to AddonBlocks.HEAVY_CREAM_POT.get())
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