package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.content.block.BuddingAcaiBlock
import com.dannbrown.braziliandelight.content.block.BuddingDoubleCropBlock
import com.dannbrown.braziliandelight.content.block.BuddingVineCropBlock
import com.dannbrown.braziliandelight.content.block.DoubleAcaiBlock
import com.dannbrown.braziliandelight.content.block.DoubleCropBlock
import com.dannbrown.braziliandelight.content.block.NormalCropBlock
import com.dannbrown.braziliandelight.content.block.VineCropBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockLootPresets
import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.ItemModelPresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraftforge.client.model.generators.ConfiguredModel
import vectorwing.farmersdelight.common.block.BuddingBushBlock
import vectorwing.farmersdelight.common.block.BuddingTomatoBlock
import vectorwing.farmersdelight.common.block.TomatoVineBlock
import java.util.function.Supplier

object CropBuilderPresets {

  // Create Budding Vine Crops
  fun createBuddingVineCropBlock(
    _name: String,
    seedName: String,
    cropLang: String,
    seedLang: String,
    color: MapColor,
    cropBlock: Supplier<VineCropBlock>,
    displayItem: Supplier<Item>,
    blockTags: List<TagKey<Block>> = listOf(),
    itemTags: List<TagKey<Item>> = listOf(),
    propertyBuilder: (Item.Properties) -> Item.Properties = { it },
  ): BlockEntry<BuddingVineCropBlock> {
    return BLOCKS
      .create<BuddingVineCropBlock>(seedName)
      .blockFactory { p -> BuddingVineCropBlock(p, cropBlock, displayItem) }
      .copyFrom { Blocks.WHEAT }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.CROP)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .blockTags(blockTags)
      .blockstate { c, p ->
        p.getVariantBuilder(c.get()).forAllStates { state ->
          val isOverStaged = state.getValue(BuddingBushBlock.AGE) == 4
          val suffix =
            if (isOverStaged) "_stage3" else "_stage" + state.getValue(BuddingTomatoBlock.AGE)
          ConfiguredModel.builder()
            .modelFile(
              p.models()
                .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/budding_" + _name + suffix))
                .texture("particle", p.modLoc("block/${_name}/budding_" + _name + suffix))
                .renderType("cutout")
            )
            .build()
        }
      }
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.lang(cropLang)
          .item { b, p -> ItemNameBlockItem(b, propertyBuilder(p)) }
          .model(ItemModelPresets.simpleItem(seedName))
          .lang(seedLang)
          .tag(*itemTags.toTypedArray())
          .build()
      }
      .register()
  }

  fun createVineCropBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>,
    cropBlock: Supplier<BuddingVineCropBlock>,
    blockTags: List<TagKey<Block>> = listOf()
  ): BlockEntry<VineCropBlock> {
    return BLOCKS
      .create<VineCropBlock>("${_name}_vine")
      .blockFactory { p -> VineCropBlock(p, dropItem, seedItem, cropBlock) }
      .copyFrom { Blocks.WHEAT }
      .properties { p ->
        p.mapColor(color)
          .sound(SoundType.CROP)
          .strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
      }
      .blockTags(blockTags)
      .blockstate { c, p ->
        p.getVariantBuilder(c.get()).forAllStates { state ->
          val isRopelogged = state.getValue(TomatoVineBlock.ROPELOGGED)
          val vineSuffix = "_vine_stage" + state.getValue(TomatoVineBlock.VINE_AGE)
          val suffix = "_stage" + state.getValue(TomatoVineBlock.VINE_AGE)

          val cropModel =
            p.models()
              .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
              .texture("cross", p.modLoc("block/${_name}/" + _name + suffix))
              .texture("particle", p.modLoc("block/${_name}/" + _name + suffix))
              .renderType("cutout")

          val ropeCropModel =
            p.models()
              .withExistingParent(
                c.name + vineSuffix + "_ropelogged",
                p.modLoc("block/crop_with_rope")
              )
              .texture("crop", p.modLoc("block/${_name}/" + _name + vineSuffix))
              .texture("rope_side", p.modLoc("block/${_name}/" + _name + "_coiled_rope"))
              .texture("rope_top", p.modLoc("block/rope_top"))
              .texture("particle", p.modLoc("block/${_name}/" + _name + vineSuffix))
              .renderType("cutout")

          ConfiguredModel.builder()
            .modelFile(if (isRopelogged) ropeCropModel else cropModel)
            .build()
        }
      }
      .loot(BlockLootPresets.dropCropLoot(dropItem, null, 0.5f, 3, 3))
      .noItem()
      .register()
  }

  fun createNormalCropBlock(
    _name: String,
    seedName: String,
    cropLang: String,
    seedLang: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    blockTags: List<TagKey<Block>> = listOf(),
    includeSeedOnDrop: Boolean = true,
    chance: Float = 1f,
    multiplier: Int = 1,
  ): BlockEntry<NormalCropBlock> {
    return BLOCKS
      .create<NormalCropBlock>(seedName)
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .blockFactory { p -> NormalCropBlock(p, dropItem) }
      .blockTags(blockTags)
      .blockstate { c, p ->
        p.getVariantBuilder(c.get()).forAllStates { state ->
          val suffix = "_stage" + state.getValue(CropBlock.AGE)
          ConfiguredModel.builder()
            .modelFile(
              p.models()
                .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                .texture("cross", p.modLoc("block/${_name}/" + _name + suffix))
                .texture("particle", p.modLoc("block/${_name}/" + _name + suffix))
                .renderType("cutout")
            )
            .build()
        }
      }
      .loot { lt, b ->
        val cropItem: Supplier<Item> = dropItem
        val seedItem: Supplier<Item>? = if (includeSeedOnDrop) Supplier { b.asItem() } else null
        val age = 7

        val dropGrownCondition = LootItemRandomChanceCondition.randomChance(chance)
          .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
            .setProperties(StatePropertiesPredicate.Builder.properties()
              .hasProperty(CropBlock.AGE, age)))
        val itemBuilder = LootItem.lootTableItem(cropItem.get())
          .`when`(dropGrownCondition)

        if (seedItem !== null) {
          itemBuilder.otherwise(LootItem.lootTableItem(seedItem.get()))
        }
        val lootBuilder = LootTable.lootTable()
          .withPool(
            LootPool.lootPool()
              .add(
                itemBuilder
              )
              .setRolls(ConstantValue.exactly(multiplier.toFloat()))
          )

        if (seedItem !== null) {
          lootBuilder.withPool(
            LootPool.lootPool()
              .`when`(dropGrownCondition)
              .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, 3))
              .add(LootItem.lootTableItem(seedItem.get()))
          )
        }

        lt.add(b, lt.applyExplosionDecay(b, lootBuilder))
      }
      .transform { t ->
        t
          .lang(cropLang)
          .item { b, p -> ItemNameBlockItem(b, p) }
          .model(ItemModelPresets.simpleItem(seedName))
          .lang(seedLang)
          .build()
      }
      .register()
  }

  // Create Double Crops
  fun createBuddingDoubleCropBlock(
    _name: String,
    seedName: String,
    cropLang: String,
    seedLang: String,
    color: MapColor,
    doubleBlock: Supplier<DoubleCropBlock>,
    blockTags: List<TagKey<Block>> = listOf(),
    itemTags: List<TagKey<Item>> = listOf(),
    propertyBuilder: (Item.Properties) -> Item.Properties = { it },
  ): BlockEntry<BuddingDoubleCropBlock> {
    return BLOCKS
      .create<BuddingDoubleCropBlock>(seedName)
      .blockFactory { p -> BuddingDoubleCropBlock(p, doubleBlock) }
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .blockTags(blockTags)
      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get()).forAllStates { state ->
            val age: Int = state.getValue(BuddingDoubleCropBlock.AGE)
            val maxAge = BuddingDoubleCropBlock.MAX_AGE
            val suffix =
              if (maxAge == age) "_budding_stage${maxAge - 1}" else "_budding_stage$age"
            ConfiguredModel.builder()
              .modelFile(
                p.models()
                  .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                  .texture("cross", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .renderType("cutout_mipped")
              )
              .build()
          }
        }
      }
      .loot(BlockLootPresets.noLoot())
      .transform { t ->
        t.lang(cropLang)
          .item { b, p -> ItemNameBlockItem(b, propertyBuilder(p)) }
          .tag(*itemTags.toTypedArray())
          .lang(seedLang)
          .model(ItemModelPresets.simpleItem(seedName))
          .build()
      }
      .register()
  }

  fun createDoubleCropBlock(
    _name: String,
    color: MapColor,
    isBush: Boolean = false,
    blockTags: List<TagKey<Block>> = listOf(),
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 1f,
    multiplier: Int = 1
  ): BlockEntry<DoubleCropBlock> {
    return BLOCKS
      .create<DoubleCropBlock>("tall_$_name")
      .blockFactory { p -> DoubleCropBlock(p, isBush, dropItem, seedItem, chance, multiplier) }
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .properties { p -> p.strength(0.0f).randomTicks().noCollission().noOcclusion() }
      .blockTags(blockTags)
      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get()).forAllStates { state ->
            val age: Int = state.getValue(DoubleCropBlock.AGE)
            val isUpper = state.getValue(DoubleCropBlock.HALF) == DoubleBlockHalf.UPPER
            val suffix = if (isUpper) "_top_stage$age" else "_bottom_stage$age"
            ConfiguredModel.builder()
              .modelFile(
                p.models()
                  .withExistingParent(c.name + suffix, p.mcLoc("block/cross"))
                  .texture("cross", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .renderType("cutout_mipped")
              )
              .build()
          }
        }
      }
      .loot(CustomBlockLootPresets.dropDoubleCropLoot(dropItem, null, chance, multiplier))
      .noItem()
      .register()
  }

  // acai blocks
  fun createBuddingAcaiBlock(
    _name: String,
    seedName: String,
    cropLang: String,
    seedLang: String,
    color: MapColor,
    doubleBlock: Supplier<DoubleAcaiBlock>,
    blockTags: List<TagKey<Block>> = listOf(),
    itemTags: List<TagKey<Item>> = listOf(),
    propertyBuilder: (Item.Properties) -> Item.Properties = { it },
  ): BlockEntry<BuddingAcaiBlock> {
    return BLOCKS
      .create<BuddingAcaiBlock>(seedName)
      .blockFactory { p -> BuddingAcaiBlock(p, doubleBlock) }
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .properties { p ->
        p.strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
          .offsetType(BlockBehaviour.OffsetType.NONE)
      }
      .blockTags(blockTags)
      .transform { t ->
        t
          .lang(cropLang)
          .item { b, p -> ItemNameBlockItem(b, propertyBuilder(p)) }
          .model(ItemModelPresets.simpleItem(seedName))
          .tag(*itemTags.toTypedArray())
          .lang(seedLang)
          .build()
          .blockstate { c, p ->
            p.getVariantBuilder(c.get()).forAllStates { state ->
              val age: Int = state.getValue(BuddingDoubleCropBlock.AGE)
              val maxAge = BuddingDoubleCropBlock.MAX_AGE
              val suffix =
                if (maxAge == age) "_budding_stage${maxAge - 1}" else "_budding_stage$age"
              ConfiguredModel.builder()
                .modelFile(
                  p.models()
                    .withExistingParent(c.name + suffix, p.modLoc("block/branch_bush"))
                    .texture("texture", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                    .renderType("cutout_mipped")
                )
                .rotationY((state.getValue(BuddingAcaiBlock.FACING).toYRot().toInt() + 180) % 360)
                .build()
            }
          }
      }
      .loot(BlockLootPresets.noLoot())
      .noItem()
      .register()
  }

  fun createDoubleAcaiBlock(
    _name: String,
    color: MapColor,
    isBush: Boolean = false,
    blockTags: List<TagKey<Block>> = listOf(),
    dropItem: Supplier<Item>,
    seedItem: Supplier<Item>? = null,
    chance: Float = 1f,
    multiplier: Int = 1
  ): BlockEntry<DoubleAcaiBlock> {
    return BLOCKS
      .create<DoubleAcaiBlock>("${_name}_branch")
      .blockFactory { p -> DoubleAcaiBlock(p, isBush, dropItem, seedItem, chance, multiplier) }
      .copyFrom { Blocks.WHEAT }
      .color(color)
      .properties { p ->
        p.strength(0.0f)
          .randomTicks()
          .noCollission()
          .noOcclusion()
          .offsetType(BlockBehaviour.OffsetType.NONE)
      }
      .blockTags(blockTags)
      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get()).forAllStates { state ->
            val age: Int = state.getValue(DoubleCropBlock.AGE)
            val isUpper = state.getValue(DoubleCropBlock.HALF) == DoubleBlockHalf.UPPER
            val suffix = if (isUpper) "_top_stage$age" else "_bottom_stage$age"
            ConfiguredModel.builder()
              .modelFile(
                p.models()
                  .withExistingParent(c.name + suffix, p.modLoc("block/branch_bush"))
                  .texture("texture", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .texture("particle", p.modLoc("block/${_name}/${_name}${suffix}"))
                  .renderType("cutout_mipped")
              )
              .rotationY((state.getValue(DoubleAcaiBlock.FACING).toYRot().toInt() + 180) % 360)
              .build()
          }
        }
      }
      .loot(CustomBlockLootPresets.dropDoubleCropLoot(dropItem, null, chance, multiplier))
      .noItem()
      .register()
  }

}