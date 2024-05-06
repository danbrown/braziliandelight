package com.dannbrown.braziliandelight

import com.dannbrown.braziliandelight.compat.vanilla.AddonCompostables
import com.dannbrown.braziliandelight.compat.vanilla.AddonDispenserBehaviors
import com.dannbrown.braziliandelight.compat.vanilla.AddonFlowerPots
import com.dannbrown.braziliandelight.compat.vanilla.AddonVillagerTrades
import com.dannbrown.braziliandelight.compat.vanilla.AddonWandererTrades
import com.dannbrown.braziliandelight.datagen.AddonDatagen
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.databoxlib.registry.DataboxRegistrate
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.event.village.WandererTradesEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import vectorwing.farmersdelight.common.registry.ModItems

@Mod(AddonContent.MOD_ID)
class AddonContent {
  companion object {
    const val MOD_ID = "braziliandelight"
    const val NAME = "Brazilian Delight"
    val LOGGER = LogManager.getLogger()
    val REGISTRATE = DataboxRegistrate(MOD_ID)
    // mod compatibility
    fun register(modBus: IEventBus, forgeEventBus: IEventBus) {
      LOGGER.info("$MOD_ID has started!")
      AddonItems.register(modBus)
      AddonBlocks.register(modBus)
      AddonCreativeTabs.register(modBus)
      // register create content
      REGISTRATE.registerEventListeners(modBus)

      modBus.addListener(::commonSetup)
      modBus.addListener(EventPriority.LOWEST) { event: GatherDataEvent -> AddonDatagen.gatherData(event) }
      forgeEventBus.addListener { event: WandererTradesEvent -> AddonWandererTrades(event).register() }
      forgeEventBus.addListener { event: VillagerTradesEvent -> AddonVillagerTrades(event).register() }
    }

    // RUN SETUP
    private fun commonSetup(event: FMLCommonSetupEvent) {
      event.enqueueWork {
        // FLOWER POTS
        AddonFlowerPots.register()
        // COMPOSTABLES
        AddonCompostables.register()
        // DISPENSER BEHAVIORS
        AddonDispenserBehaviors.registerAll()
      }
    }
  }

  init {
    val eventBus = FMLJavaModLoadingContext.get().modEventBus
    val forgeEventBus = MinecraftForge.EVENT_BUS
    register(eventBus, forgeEventBus)
  }
}


