package com.dannbrown.discovermars.init

import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.discovermars.AddonContent
import net.minecraftforge.eventbus.api.IEventBus

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

}