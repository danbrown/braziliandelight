package com.dannbrown.braziliandelight.init

import com.dannbrown.deltaboxlib.registrate.providers.trades.VillagerLevel
import com.dannbrown.deltaboxlib.registrate.providers.trades.VillagerTradeItem
import com.dannbrown.deltaboxlib.registrate.providers.trades.WandererTradeRarity
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.Items
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE

object ModTrades {
  init {
    REGISTRATE
      // buy lemon for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ ModItems.LEMON.get() }, 5)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.2f
      )
      // sell lemon sapling for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 4)),
        listOf(VillagerTradeItem({ ModBlocks.LEMON_SAPLING.getItem() }, 2)),
        5,
        6,
        0.5f
      )
      // sell coconut sapling for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.JOURNEYMAN,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.COCONUT_PALM_SAPLING.getItem() }, 2)),
        5,
        6,
        0.05f
      )
      // sell acai sapling for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.JOURNEYMAN,
        listOf(VillagerTradeItem({ Items.EMERALD }, 6)),
        listOf(VillagerTradeItem({ ModBlocks.ACAI_PALM_SAPLING.getItem() }, 2)),
        5,
        6,
        0.05f
      )
      // buy corn for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.APPRENTICE,
        listOf(VillagerTradeItem({ ModItems.CORN.get() }, 12)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        6,
        0.02f
      )
      // sell corn kernels for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.APPRENTICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_CORN.getItem() }, 2)),
        2,
        12,
        0.075f
      )
      // buy guarana fruit for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ ModItems.GUARANA_FRUIT.get() }, 12)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        10,
        8,
        0.02f
      )
      // sell guarana seeds for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_GUARANA.getItem() }, 2)),
        2,
        12,
        0.075f
      )
      // buy garlic for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ ModItems.GARLIC_BULB.get() }, 7)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.02f
      )
      // sell garlic bulb for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModItems.GARLIC_BULB.get() }, 2)),
        2,
        12,
        0.075f
      )
      // buy cassava root for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.APPRENTICE,
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_CASSAVA.getItem() }, 8)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        5,
        8,
        0.02f
      )
      // sell cassava flour for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.APPRENTICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModItems.CASSAVA_FLOUR.get() }, 2)),
        7,
        12,
        0.075f
      )
      // buy coffee beans for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.JOURNEYMAN,
        listOf(VillagerTradeItem({ ModItems.COFFEE_BEANS.get() }, 10)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        10,
        8,
        0.02f
      )
      // sell coffee berries for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.JOURNEYMAN,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModItems.COFFEE_BERRIES.get() }, 2)),
        2,
        12,
        0.075f
      )
      // buy collard greens for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ ModItems.COLLARD_GREENS.get() }, 8)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        5,
        8,
        0.02f
      )
      // sell collard seeds for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.COLLARD_GREENS_CROP.getItem() }, 2)),
        7,
        12,
        0.075f
      )
      // buy beans for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ ModItems.BEAN_POD.get() }, 8)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.02f
      )
      // sell beans for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.EXPERT,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModItems.BEAN_POD.get() }, 2)),
        2,
        12,
        0.075f
      )
      // buy acai berries for emerald
//  .villagerTrade(
//    VillagerProfession.FARMER,
//    VillagerLevel.EXPERT,
//    listOf(VillagerTradeItem({ModBlocks.BUDDING_ACAI_BRANCH.get()}, 8)),
//    listOf(VillagerTradeItem({Items.EMERALD}, 1)),
//    10,
//    8,
//    0.02f
//  )
//  // sell acai seeds for emerald
//  .villagerTrade(
//    VillagerProfession.FARMER,
//    VillagerLevel.EXPERT,
//    listOf(VillagerTradeItem({Items.EMERALD}, 3)),
//    listOf(VillagerTradeItem({ModBlocks.BUDDING_ACAI_BRANCH.get()}, 2)),
//    2,
//    12,
//    0.075f
//  )
      // buy yerba mate for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ ModItems.DRIED_YERBA_MATE.get() }, 4)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.02f
      )
      // sell yerba mate for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        listOf(VillagerTradeItem({ ModItems.YERBA_MATE_LEAVES.get() }, 1)),
        2,
        12,
        0.075f
      )
      // fisherman buy shrimp for emerald
      .villagerTrade(
        VillagerProfession.FISHERMAN,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ ModItems.SHRIMP.get() }, 5)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.02f
      )
      // sell shrimp for emerald
      .villagerTrade(
        VillagerProfession.FISHERMAN,
        VillagerLevel.NOVICE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModItems.SHRIMP.get() }, 2)),
        2,
        12,
        0.075f
      )
      // buy coconut slices for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.APPRENTICE,
        listOf(VillagerTradeItem({ ModItems.COCONUT_SLICE.get() }, 5)),
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        10,
        8,
        0.02f
      )
      // sell green coconut for emerald
      .villagerTrade(
        VillagerProfession.FARMER,
        VillagerLevel.MASTER,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.GREEN_COCONUT.getItem() }, 2)),
        5,
        12,
        0.075f
      )

      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_COFFEE.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // rare guarana seeds
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_GUARANA.getItem() }, 1)),
        2,
        12,
        0.15f
      )
      // generic garlic
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.GARLIC_CROP.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // generic collard seeds
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.COLLARD_GREENS_CROP.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // generic corn kernels
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_CORN.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // generic cassava root
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.BUDDING_CASSAVA.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // generic beans
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModItems.BEAN_POD.get() }, 2)),
        3,
        2,
        0.2f
      )
      // generic black beans
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.BLACK_BEANS_CROP.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // generic yerba mate
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModItems.YERBA_MATE_LEAVES.get() }, 2)),
        3,
        2,
        0.2f
      )
      // generic brown beans
      .wandererTrade(
        WandererTradeRarity.GENERIC,
        listOf(VillagerTradeItem({ Items.EMERALD }, 1)),
        listOf(VillagerTradeItem({ ModBlocks.CARIOCA_BEANS_CROP.getItem() }, 2)),
        3,
        2,
        0.2f
      )
      // rare shrimp
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 2)),
        listOf(VillagerTradeItem({ ModItems.SHRIMP.get() }, 1)),
        3,
        2,
        0.2f
      )
      // rare acai berries
//    .wandererTrade(
//      WandererTradeRarity.RARE,
//      listOf(VillagerTradeItem({Items.EMERALD}, 1)),
//        listOf(VillagerTradeItem({ModBlocks.BUDDING_ACAI_BRANCH.getItem()}, 2)),
//      3,
//      2,
//      0.2f
//    )
      // rare lemon sapling
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 4)),
        listOf(VillagerTradeItem({ ModBlocks.LEMON_SAPLING.getItem() }, 1)),
        3,
        2,
        0.2f
      )
      // rare coconut palm sapling
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.COCONUT_PALM_SAPLING.getItem() }, 1)),
        3,
        2,
        0.2f
      )
      // rare acai palm sapling
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 3)),
        listOf(VillagerTradeItem({ ModBlocks.ACAI_PALM_SAPLING.getItem() }, 1)),
        3,
        2,
        0.2f
      )
      // rare green coconut
      .wandererTrade(
        WandererTradeRarity.RARE,
        listOf(VillagerTradeItem({ Items.EMERALD }, 6)),
        listOf(VillagerTradeItem({ ModBlocks.GREEN_COCONUT.getItem() }, 1)),
        3,
        2,
        0.2f
      )
  }

  fun register() {
    // init
  }
}