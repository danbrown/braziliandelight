package com.dannbrown.discoverplanetzero.init

import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.databoxlib.registry.generators.ItemGen
import com.dannbrown.discover.content.item.FuelItem
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discover.lib.LibData
import com.dannbrown.discoverplanetzero.AddonContent
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraftforge.eventbus.api.IEventBus

object AddonItems {
  fun register(modBus: IEventBus) {}

  val ITEMS = ItemGen(AddonContent.REGISTRATE)

  val PHOSPHORUS_POWDER = ITEMS.dustItemControlled(LibData.ITEMS.PHOSPHORUS_POWDER, { LibTags.forgeItemTag("dusts/phosphorus") }, { p -> Item(p) })
  val MAGNESIUM_DUST = ITEMS.dustItemControlled(LibData.ITEMS.MAGNESIUM_DUST, { LibTags.forgeItemTag("dusts/magnesium") }, { p -> Item(p) })
  val HIMALAYAN_SALT = ITEMS.dustItemControlled(LibData.ITEMS.HIMALAYAN_SALT, { LibTags.forgeItemTag("ingots/himalayan_salt") }, { p -> Item(p) })
  val SALT = ITEMS.dustItemControlled(LibData.ITEMS.SALT, { LibTags.forgeItemTag("dusts/salt") }, { p -> Item(p) })

  // PLANTS
  val COTTON_SEEDS = ITEMS.simpleItem(LibData.ITEMS.COTTON_SEEDS, { p -> ItemNameBlockItem(AddonBlocks.BUDDING_COTTON_CROP.get(), p) })
  val COTTON_PULP = ITEMS.simpleItem(LibData.ITEMS.COTTON_PULP, { p -> FuelItem(p, 400) })
}