package com.dannbrown.braziliandelight.init

import com.dannbrown.deltaboxlib.registry.generators.CreativeTabGen
import com.dannbrown.braziliandelight.AddonContent
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import vectorwing.farmersdelight.common.registry.ModCreativeTabs

object AddonCreativeTabs {
  val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AddonContent.MOD_ID)

  fun register(modBus: IEventBus) {
    TABS.register(modBus)
  }

  val CREATIVE_TAB_KEY = "creative_tab"
  val CREATIVE_TAB: RegistryObject<CreativeModeTab> =
    CreativeTabGen(TABS, AddonContent.MOD_ID).createTab(
      CREATIVE_TAB_KEY,
      { ItemStack(AddonItems.BRAZIL_FLAG.get()) },
      ModCreativeTabs.TAB_FARMERS_DELIGHT.key,
      { parameters, output ->
        CreativeTabGen.displayAllRegistrate(AddonContent.REGISTRATE, parameters, output)
      }
    )
}