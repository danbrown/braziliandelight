package com.dannbrown.braziliandelight

import com.dannbrown.braziliandelight.compat.vanilla.AddonCompostables
import com.dannbrown.braziliandelight.compat.vanilla.AddonDispenserBehaviors
import com.dannbrown.braziliandelight.compat.vanilla.AddonFlowerPots
import com.dannbrown.braziliandelight.compat.vanilla.AddonVillagerTrades
import com.dannbrown.braziliandelight.compat.vanilla.AddonWandererTrades
import com.dannbrown.braziliandelight.datagen.AddonDatagen
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonCreativeTabs
import com.dannbrown.braziliandelight.init.AddonEffects
import com.dannbrown.braziliandelight.init.AddonEntityTypes
import com.dannbrown.braziliandelight.init.AddonItems
import com.dannbrown.braziliandelight.init.AddonPlacerTypes
import com.dannbrown.braziliandelight.init.AddonTreeDecorators
import com.dannbrown.deltaboxlib.registry.DeltaboxRegistrate
import net.minecraft.client.renderer.BiomeColors
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.FoliageColor
import net.minecraftforge.client.event.RegisterColorHandlersEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.event.village.WandererTradesEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.DIST

@Mod(AddonContent.MOD_ID)
class AddonContent {
  companion object {
    const val MOD_ID = "braziliandelight"
    const val NAME = "Brazilian Delight"
    val LOGGER = LogManager.getLogger()
    val REGISTRATE = DeltaboxRegistrate(MOD_ID)
    // mod compatibility
    fun register(modBus: IEventBus, forgeEventBus: IEventBus) {
      LOGGER.info("$MOD_ID has started!")
      AddonItems.register(modBus)
      AddonBlocks.register(modBus)
      AddonCreativeTabs.register(modBus)
      AddonEffects.register(modBus)
      AddonPlacerTypes.register(modBus)
      AddonEntityTypes.register(modBus)
      AddonTreeDecorators.register(modBus)
      // register create content
      REGISTRATE.registerEventListeners(modBus)

      modBus.addListener(::commonSetup)
      modBus.addListener(EventPriority.LOWEST) { event: GatherDataEvent -> AddonDatagen.gatherData(event) }
      forgeEventBus.addListener { event: WandererTradesEvent -> AddonWandererTrades(event).register() }
      forgeEventBus.addListener { event: VillagerTradesEvent -> AddonVillagerTrades(event).register() }
    }

    fun registerClient(modBus: IEventBus, forgeEventBus: IEventBus) {
      modBus.addListener(::clientSetup)
      modBus.addListener { event:  RegisterColorHandlersEvent.Block ->
        event.blockColors.register(
          { state, level, pos, tint ->
            if(level != null && pos != null) BiomeColors.getAverageFoliageColor(level, pos) else FoliageColor.getDefaultColor()
          }, AddonBlocks.COCONUT_PALM_LEAVES.get(), AddonBlocks.BUDDING_COCONUT_PALM_LEAVES.get(), AddonBlocks.ACAI_PALM_LEAVES.get())
      }
      modBus.addListener { event: RegisterColorHandlersEvent.Item ->
        event.itemColors.register({ stack, tintIndex ->
          val state = (stack.item as BlockItem).block.defaultBlockState()
          return@register event.blockColors.getColor(state, null, null, tintIndex)
        }, AddonBlocks.COCONUT_PALM_LEAVES.get(), AddonBlocks.ACAI_PALM_LEAVES.get())
      }

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
        // BREWING RECIPES
        AddonEffects.addRecipes()
      }
    }

    // Run Client Setup
    private fun clientSetup(event: FMLClientSetupEvent) {
      EntityRenderers.register(AddonEntityTypes.COCONUT_PROJECTILE.get()) { ctx -> ThrownItemRenderer(ctx) }
    }
  }

  init {
    val modBus = FMLJavaModLoadingContext.get().modEventBus
    val forgeEventBus = MinecraftForge.EVENT_BUS
    register(modBus, forgeEventBus)
    // client
    if (DIST.isClient) {
      // register main mod client content
      registerClient(modBus, forgeEventBus)
    }
  }
}


