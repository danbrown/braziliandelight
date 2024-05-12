package com.dannbrown.braziliandelight.content.placerTypes

import com.dannbrown.braziliandelight.init.AddonPlacerTypes
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType

class PalmFoliagePlacer(pRadius: IntProvider, pOffset: IntProvider) : FoliagePlacer(pRadius, pOffset) {
  override fun type(): FoliagePlacerType<*> {
    return AddonPlacerTypes.PALM_FOLIAGE_PLACER.get()
  }

  override fun createFoliage(pLevel: LevelSimulatedReader, foliageSetter: FoliageSetter, pRandom: RandomSource, pConfig: TreeConfiguration, i: Int, pAttachment: FoliageAttachment, j: Int, k: Int, l: Int) {
    val startingPos = pAttachment.pos()

    tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, startingPos)

    createQuadrant(Direction.NORTH, startingPos, pLevel, foliageSetter, pRandom, pConfig)
    createQuadrant(Direction.EAST, startingPos, pLevel, foliageSetter, pRandom, pConfig)
    createQuadrant(Direction.SOUTH, startingPos, pLevel, foliageSetter, pRandom, pConfig)
    createQuadrant(Direction.WEST, startingPos, pLevel, foliageSetter, pRandom, pConfig)
  }

  override fun foliageHeight(pRandom: RandomSource, pHeight: Int, pConfig: TreeConfiguration): Int {
    return 0
  }

  override fun shouldSkipLocation(pRandom: RandomSource, pLocalX: Int, pLocalY: Int, pLocalZ: Int, pRange: Int, pLarge: Boolean): Boolean {
    return false
  }

  companion object {
    val CODEC: Codec<PalmFoliagePlacer> = RecordCodecBuilder.create { placer ->
      foliagePlacerParts(placer).apply(placer) { pRadius: IntProvider, pOffset: IntProvider -> PalmFoliagePlacer(pRadius, pOffset) }
    }

    private fun createQuadrant(direction: Direction, startingPos: BlockPos, pLevel: LevelSimulatedReader, foliageSetter: FoliageSetter, pRandom: RandomSource, pConfig: TreeConfiguration) {
      val pos = startingPos.mutable()

      pos.move(direction)
      tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)

      for (i in 0..1) {
        pos.move(direction)
        tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)
        pos.move(Direction.DOWN)
        tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)
      }

      pos.set(startingPos)
      pos.move(direction).move(direction.counterClockWise)
      tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)
      pos.move(Direction.DOWN).move(direction.counterClockWise)
      tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)
      pos.move(direction)
      tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos.relative(direction.clockWise))
      for (i in 0..2) {
        tryPlaceLeaf(pLevel, foliageSetter, pRandom, pConfig, pos)
        pos.move(Direction.DOWN)
      }
    }
  }
}