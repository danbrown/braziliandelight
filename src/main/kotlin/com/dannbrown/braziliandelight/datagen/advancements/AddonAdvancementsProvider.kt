package com.dannbrown.braziliandelight.datagen.advancements

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.DisplayInfo
import net.minecraft.advancements.FrameType
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.ForgeAdvancementProvider
import java.util.function.Consumer

class AddonAdvancementsProvider : ForgeAdvancementProvider.AdvancementGenerator {
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
            Component.literal("Brazilian Delight"),
            Component.literal("Desc"),
            ResourceLocation(AddonContent.MOD_ID, "textures/block/lemon_block.png"),
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
          ItemStack(AddonItems.BRAZIL_FLAG.get()),
          Component.literal("Tropical Seeds"),
          Component.literal("Desc"),
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
  }
}