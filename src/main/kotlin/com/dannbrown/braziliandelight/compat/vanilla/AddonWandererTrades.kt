package com.dannbrown.braziliandelight.compat.vanilla

import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxWandererTrades
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.event.village.WandererTradesEvent

class AddonWandererTrades(event: WandererTradesEvent): DeltaboxWandererTrades(event, mutableListOf(
  // rare coffee seeds
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.BUDDING_COFFEE.get(), 2),
    3,
    2,
    0.2f
  ),
  // rare guarana seeds
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 2),
    ItemStack(AddonBlocks.BUDDING_GUARANA.get(), 1),
    2,
    12,
    0.15f
  ),
  // generic garlic
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.GARLIC_CROP.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic collard seeds
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.COLLARD_GREENS_CROP.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic corn kernels
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.BUDDING_CORN.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic cassava root
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.BUDDING_CASSAVA.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic beans
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonItems.BEAN_POD.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic black beans
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.BUDDING_BEANS_CROP.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic yerba mate
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonItems.YERBA_MATE_LEAVES.get(), 2),
    3,
    2,
    0.2f
  ),
  // generic brown beans
  addTrade(
    TradeRarity.GENERIC,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.CARIOCA_BEANS_CROP.get(), 2),
    3,
    2,
    0.2f
  ),
  // rare shrimp
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 2),
    ItemStack(AddonItems.SHRIMP.get(), 1),
    3,
    2,
    0.2f
  ),
  // rare acai berries
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 1),
    ItemStack(AddonBlocks.BUDDING_ACAI_BRANCH.get(), 2),
    3,
    2,
    0.2f
  ),
  // rare lemon sapling
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonBlocks.LEMON_SAPLING.asItem(), 1),
    3,
    2,
    0.2f
  ),
  // rare green coconut
  addTrade(
    TradeRarity.RARE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonBlocks.GREEN_COCONUT.get(), 1),
    3,
    2,
    0.2f
  ),
))
