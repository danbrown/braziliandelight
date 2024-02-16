package com.dannbrown.discovermars.init

import com.dannbrown.databoxlib.registry.generators.CreativeTabGen
import com.dannbrown.discover.init.DiscoverCreativeTabs
import com.dannbrown.discovermars.AddonContent
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject

object AddonCreativeTabs {
  val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AddonContent.MOD_ID)

  fun register(modBus: IEventBus) {
    TABS.register(modBus)
  }

  val CREATIVE_TAB_KEY = "creative_tab"
  val CREATIVE_TAB: RegistryObject<CreativeModeTab> =
    CreativeTabGen(TABS, AddonContent.MOD_ID).createTab(
      CREATIVE_TAB_KEY,
      { ItemStack(Items.DIRT) },
      DiscoverCreativeTabs.DISCOVER_MAIN_TAB.key,
      { parameters, output ->
        CreativeTabGen.displayAllRegistrate(AddonContent.REGISTRATE, parameters, output)
      }
    )
}