package com.dannbrown.discoverplanetzero.init

import com.dannbrown.discover.ProjectContent
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.lib.AddonNames
import net.minecraft.client.renderer.Sheets
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType

object AddonWoodTypes {
  val JOSHUA = WoodType.register(WoodType(AddonContent.MOD_ID + ":" + AddonNames.JOSHUA, BlockSetType.OAK))

  fun register() {
    Sheets.addWoodType(JOSHUA)
  }
}