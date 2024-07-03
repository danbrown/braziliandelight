package com.dannbrown.braziliandelight.compat.vanilla

import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxVillagerTrades
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.event.village.VillagerTradesEvent

class AddonVillagerTrades(event: VillagerTradesEvent): DeltaboxVillagerTrades(event, mutableListOf(
  // buy lemon for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.NOVICE,
    ItemStack(AddonItems.LEMON.get(), 5),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell lemon sapling for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.JOURNEYMAN,
    ItemStack(Items.EMERALD, 4),
    ItemStack(AddonBlocks.LEMON_SAPLING.asItem(), 1),
    5,
    6,
    0.05f
  ),
  // buy corn for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.APPRENTICE,
    ItemStack(AddonItems.CORN.get(), 12),
    ItemStack(Items.EMERALD, 1),
    10,
    6,
    0.02f
  ),
  // sell corn kernels for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.APPRENTICE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.KERNELS.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy guarana fruit for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(AddonItems.GUARANA_FRUIT.get(), 12),
    ItemStack(Items.EMERALD, 2),
    10,
    8,
    0.02f
  ),
  // sell guarana seeds for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.GUARANA_SEEDS.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy garlic for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.NOVICE,
    ItemStack(AddonItems.GARLIC_BULB.get(), 7),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell garlic bulb for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.NOVICE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.GARLIC_BULB.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy cassava root for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.APPRENTICE,
    ItemStack(AddonItems.CASSAVA_ROOT.get(), 8),
    ItemStack(Items.EMERALD, 2),
    5,
    8,
    0.02f
  ),
  // sell cassava flour for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.APPRENTICE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.CASSAVA_FLOUR.get(), 2),
    7,
    12,
    0.075f
  ),
  // buy coffee beans for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.JOURNEYMAN,
    ItemStack(AddonItems.COFFEE_BEANS.get(), 10),
    ItemStack(Items.EMERALD, 2),
    10,
    8,
    0.02f
  ),
  // sell coffee berries for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.JOURNEYMAN,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.COFFEE_BERRIES.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy collard greens for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(AddonItems.COLLARD_GREENS.get(), 8),
    ItemStack(Items.EMERALD, 2),
    5,
    8,
    0.02f
  ),
  // sell collard seeds for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonBlocks.COLLARD_GREENS_CROP.get(), 2),
    7,
    12,
    0.075f
  ),
  // buy beans for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(AddonItems.BEAN_POD.get(), 8),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell beans for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.BEAN_POD.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy acai berries for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(AddonItems.ACAI_BERRIES.get(), 8),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell acai seeds for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.EXPERT,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.ACAI_BERRIES.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy yerba mate for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.NOVICE,
    ItemStack(AddonItems.DRIED_YERBA_MATE.get(), 4),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell yerba mate for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.NOVICE,
    ItemStack(Items.EMERALD, 2),
    ItemStack(AddonItems.YERBA_MATE_LEAVES.get(), 1),
    2,
    12,
    0.075f
  ),
  // fisherman buy shrimp for emerald
  addTrade(
    VillagerProfession.FISHERMAN,
    VillagerLevel.NOVICE,
    ItemStack(AddonItems.SHRIMP.get(), 5),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell shrimp for emerald
  addTrade(
    VillagerProfession.FISHERMAN,
    VillagerLevel.NOVICE,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonItems.SHRIMP.get(), 2),
    2,
    12,
    0.075f
  ),
  // buy coconut slices for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.APPRENTICE,
    ItemStack(AddonItems.COCONUT_SLICE.get(), 5),
    ItemStack(Items.EMERALD, 1),
    10,
    8,
    0.02f
  ),
  // sell green coconut for emerald
  addTrade(
    VillagerProfession.FARMER,
    VillagerLevel.MASTER,
    ItemStack(Items.EMERALD, 3),
    ItemStack(AddonBlocks.GREEN_COCONUT.get(), 2),
    5,
    12,
    0.075f
  ),
))
