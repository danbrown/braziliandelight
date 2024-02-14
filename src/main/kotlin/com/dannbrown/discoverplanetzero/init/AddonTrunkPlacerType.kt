package com.dannbrown.discoverplanetzero.init

import com.dannbrown.discover.ProjectContent
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.trunkPlacer.ForkingStalkTrunkPlacer
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

object AddonTrunkPlacerType {
  val TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, AddonContent.MOD_ID)

  fun register(modBus: IEventBus) {
    TRUNK_PLACER.register(modBus)
  }

  // content
  val FORKING_STALK_TRUNK_PLACER = TRUNK_PLACER.register("forking_stalk_trunk_placer") {
    TrunkPlacerType(ForkingStalkTrunkPlacer.CODEC)
  }
}