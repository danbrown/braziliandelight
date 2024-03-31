package com.dannbrown.braziliandelight.datagen.content.transformers

import com.dannbrown.braziliandelight.content.block.DoubleCropBlock
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables
import com.tterrag.registrate.util.nullness.NonNullBiConsumer
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.function.Supplier

object CustomBlockLootPresets {
  fun <B : Block> dropDoubleCropLoot(cropItem: Supplier<Item>, seedItem: Supplier<Item>?, chance: Float = 1f, multiplier: Int = 1): NonNullBiConsumer<RegistrateBlockLootTables, B> {
    return NonNullBiConsumer { lt, b ->

      val dropGrownCondition = LootItemRandomChanceCondition.randomChance(chance)
        .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoubleCropBlock.AGE, DoubleCropBlock.MAX_AGE)))
        .and(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))

      val itemBuilder = LootItem.lootTableItem(cropItem.get())
        .`when`(dropGrownCondition)

      if (seedItem !== null) {
        itemBuilder.otherwise(LootItem.lootTableItem(seedItem.get()))
      }

      val lootBuilder = LootTable.lootTable().withPool(
        LootPool.lootPool().add(
          itemBuilder
        ).setRolls(ConstantValue.exactly(multiplier.toFloat()))
      )

      if (seedItem !== null) {
        lootBuilder.withPool(
          LootPool.lootPool()
            .`when`(dropGrownCondition)
            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286f, 3))
            .add(LootItem.lootTableItem(seedItem.get()))
        )
      }

      lt.add(b, lt.applyExplosionDecay(b,lootBuilder))
//
//      val builder: LootPoolEntryContainer.Builder<*> = LootItem.lootTableItem(b)
//        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f)))
//        .`when`(BlockLootHelpers.HAS_SHEARS)
//        .otherwise(lt.applyExplosionCondition(b, LootItem.lootTableItem(seedItem.get()))
//          .apply(SetItemCountFunction.setCount(ConstantValue.exactly(count)))
//          .`when`(LootItemRandomChanceCondition.randomChance(chance))
//          .otherwise(LootItem.lootTableItem(cropItem.get())))
//
//      val pool = LootTable.lootTable()
//        .withPool(LootPool.lootPool()
//          .add(builder)
//          .`when`(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
//            .setProperties(StatePropertiesPredicate.Builder.properties()
//              .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
//          .`when`(LocationCheck.checkLocation(LocationPredicate.Builder.location()
//            .setBlock(BlockPredicate.Builder.block()
//              .of(b)
//              .setProperties(StatePropertiesPredicate.Builder.properties()
//                .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
//                .build())
//              .build()), BlockPos(0, 1, 0))))
//
//        .withPool(LootPool.lootPool()
//          .add(builder)
//          .`when`(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
//            .setProperties(StatePropertiesPredicate.Builder.properties()
//              .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
//          .`when`(LocationCheck.checkLocation(LocationPredicate.Builder.location()
//            .setBlock(BlockPredicate.Builder.block()
//              .of(b)
//              .setProperties(StatePropertiesPredicate.Builder.properties()
//                .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
//                .build())
//              .build()), BlockPos(0, -1, 0))))
//
//      lt.add(b, pool)
    }
  }
}