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
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.ForgeAdvancementProvider
import java.util.function.Consumer

class AddonAdvancementsProvider : ForgeAdvancementProvider.AdvancementGenerator {

  companion object {
    val ROOT_ADVANCEMENT_KEY = "root"
    val TROPICAL_SEEDS_KEY = "tropical_seeds"
    val MILK_POT_ADVANCEMENT_KEY = "milk_pot"
    val HEAVY_CREAM_POT_ADVANCEMENT_KEY = "heavy_cream_pot"
    val CHEESE_MAKING_KEY = "cheese_making"
  }

  fun advancementTitle(key: String): Component {
    return Component.translatable("advancements.${AddonContent.MOD_ID}.${key}.title")
  }

  fun advancementDescription(key: String): Component {
    return Component.translatable("advancements.${AddonContent.MOD_ID}.${key}.description")
  }

  override fun generate(
    registries: HolderLookup.Provider,
    saver: Consumer<Advancement>,
    existingFileHelper: ExistingFileHelper
  ) {
    val ROOT_ADVANCEMENT =
      Advancement.Builder.advancement()
        .display(
          DisplayInfo(
            ItemStack(AddonItems.BRAZIL_FLAG.get()),
            advancementTitle(ROOT_ADVANCEMENT_KEY),
            advancementDescription(ROOT_ADVANCEMENT_KEY),
            ResourceLocation("minecraft", "textures/block/jungle_leaves.png"),
            FrameType.TASK,
            true,
            true,
            false
          )
        )
        .addCriterion(
          "started_brazilian",
          InventoryChangeTrigger.TriggerInstance.hasItems(AddonItems.BRAZIL_FLAG.get())
        )
        .save(saver, ResourceLocation(AddonContent.MOD_ID, "started_brazilian"), existingFileHelper)

    val TROPICAL_SEEDS = Advancement.Builder.advancement()
      .parent(ROOT_ADVANCEMENT)
      .display(
        DisplayInfo(
          ItemStack(AddonBlocks.BUDDING_CORN.asItem()),
          advancementTitle(TROPICAL_SEEDS_KEY),
          advancementDescription(TROPICAL_SEEDS_KEY),
          null,
          FrameType.TASK,
          true,
          true,
          false
        )
      )
      .addCriterion(
        "started_brazilian",
        InventoryChangeTrigger.TriggerInstance.hasItems(
          ItemPredicate.Builder.item()
            .of(
              AddonBlocks.COLLARD_GREENS_CROP.asItem(),
              AddonBlocks.GARLIC_CROP.asItem(),
              AddonBlocks.BUDDING_BEANS_CROP.asItem(),
              AddonBlocks.CARIOCA_BEANS_CROP.asItem(),
              AddonBlocks.BUDDING_CORN.asItem(),
              AddonBlocks.BUDDING_CASSAVA.asItem(),
              AddonBlocks.BUDDING_COFFEE.asItem(),
              AddonBlocks.WHITE_KERNELS_CROP.asItem(),
              AddonBlocks.BUDDING_CASSAVA.asItem(),
              AddonBlocks.LEMON_SAPLING.asItem(),
              AddonBlocks.ACAI_PALM_SAPLING.asItem(),
              AddonBlocks.COCONUT_PALM_SAPLING.asItem(),
            )
            .build()
        )
      )
      .save(saver, ResourceLocation(AddonContent.MOD_ID, "has_tropical_seeds"), existingFileHelper)

    val MILK_POT_ADVANCEMENT: Advancement = Advancement.Builder.advancement()
      .parent(ROOT_ADVANCEMENT)
      .display(
        DisplayInfo(
          ItemStack(Items.MILK_BUCKET),
          advancementTitle(MILK_POT_ADVANCEMENT_KEY),
          advancementDescription(MILK_POT_ADVANCEMENT_KEY),
          null,
          FrameType.TASK,
          true,
          true,
          false
        )
      )
      .requirements(RequirementsStrategy.AND)
      .addCriterion("milk", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
        LocationPredicate.Builder.location()
          .setBlock(BlockPredicate.Builder.block().of(AddonBlocks.MILK_POT.get()).build()),
        ItemPredicate.Builder.item().of(Items.MILK_BUCKET)
        )
      )
      .save(saver, ResourceLocation(AddonContent.MOD_ID, "make_milk_pot"), existingFileHelper)

    val HEAVY_CREAM_POT_ADVANCEMENT: Advancement = Advancement.Builder.advancement()
      .parent(MILK_POT_ADVANCEMENT)
      .display(
        DisplayInfo(
          ItemStack(AddonItems.HEAVY_CREAM_BUCKET.get()),
          advancementTitle(HEAVY_CREAM_POT_ADVANCEMENT_KEY),
          advancementDescription(HEAVY_CREAM_POT_ADVANCEMENT_KEY),
          null,
          FrameType.TASK,
          true,
          true,
          false
        )
      )
      .addCriterion(
        "has_heavy_cream",
        InventoryChangeTrigger.TriggerInstance.hasItems(AddonItems.HEAVY_CREAM_BUCKET.get())
      )
      .save(saver, ResourceLocation(AddonContent.MOD_ID, "has_heavy_cream"), existingFileHelper)

    val CHEESE_MAKING_ADVANCEMENT: Advancement = Advancement.Builder.advancement()
      .parent(HEAVY_CREAM_POT_ADVANCEMENT)
      .display(
        DisplayInfo(
          ItemStack(AddonBlocks.MINAS_CHEESE.asItem()),
          advancementTitle(CHEESE_MAKING_KEY),
          advancementDescription(CHEESE_MAKING_KEY),
          null,
          FrameType.TASK,
          true,
          true,
          false
        )
      )
      .requirements(RequirementsStrategy.AND)
      .addCriterion("lemon", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
        LocationPredicate.Builder.location()
          .setBlock(BlockPredicate.Builder.block().of(AddonBlocks.HEAVY_CREAM_POT.get()).build()),
        ItemPredicate.Builder.item().of(AddonItems.LEMON.get())
        )
      )
      .addCriterion("salt", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
        LocationPredicate.Builder.location()
          .setBlock(BlockPredicate.Builder.block().of(AddonBlocks.HEAVY_CREAM_POT.get()).build()),
        ItemPredicate.Builder.item().of(AddonItems.SALT.get())
        )
      )
      .save(saver, ResourceLocation(AddonContent.MOD_ID, "make_cheese"), existingFileHelper)
  }
}