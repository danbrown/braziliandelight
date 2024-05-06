package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.placerTypes.CrookedTrunkPlacer
import com.dannbrown.braziliandelight.content.placerTypes.PalmFoliagePlacer
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

object AddonPlacerTypes {
  val FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, AddonContent.MOD_ID)
  val TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, AddonContent.MOD_ID)

  val PALM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("palm_foliage_placer") { FoliagePlacerType(PalmFoliagePlacer.CODEC) }
  val CROOKED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("crooked_trunk_placer") { TrunkPlacerType(CrookedTrunkPlacer.CODEC) }

  fun register(bus:IEventBus) {
    FOLIAGE_PLACER_TYPES.register(bus)
    TRUNK_PLACER_TYPES.register(bus)
  }
}