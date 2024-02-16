package com.dannbrown.discovermars.init

import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.databoxlib.registry.generators.ItemGen
import com.dannbrown.discover.content.item.FuelItem
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discover.lib.LibData
import com.dannbrown.discovermars.AddonContent
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraftforge.eventbus.api.IEventBus

object AddonItems {
  fun register(modBus: IEventBus) {}

  val ITEMS = ItemGen(AddonContent.REGISTRATE)

}