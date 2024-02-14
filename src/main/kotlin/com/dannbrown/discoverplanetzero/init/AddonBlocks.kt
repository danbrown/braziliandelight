package com.dannbrown.discoverplanetzero.init

import com.dannbrown.databoxlib.registry.generators.BlockFamily
import com.dannbrown.databoxlib.registry.generators.BlockFamilyGen
import com.dannbrown.databoxlib.registry.generators.BlockGenerator
import com.dannbrown.discover.content.utils.toolTiers.SetTier
import com.dannbrown.discover.content.utils.toolTiers.SetTool
import com.dannbrown.discover.datagen.transformers.BlockLootPresets
import com.dannbrown.discover.datagen.transformers.BlockstatePresets
import com.dannbrown.discover.datagen.transformers.ItemModelPresets
import com.dannbrown.discover.init.DiscoverTags
import com.dannbrown.discover.init._DiscoverItems
import com.dannbrown.discover.lib.LibData
import com.dannbrown.databoxlib.lib.LibTags
import com.dannbrown.discoverplanetzero.AddonContent
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.AmethystClusterBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SandBlock
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.eventbus.api.IEventBus

object AddonBlocks {
  fun register(modBus: IEventBus?) {
  }

  val BLOCKS = BlockGenerator(AddonContent.REGISTRATE)

}