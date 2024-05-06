package com.dannbrown.braziliandelight.content.placerTypes

import com.dannbrown.braziliandelight.init.AddonPlacerTypes
import com.google.common.collect.ImmutableList
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import java.util.function.BiConsumer

class CrookedTrunkPlacer(pBaseHeight: Int, pHeightRandA: Int, pHeightRandB: Int) : TrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB) {
  override fun type(): TrunkPlacerType<*> {
    return AddonPlacerTypes.CROOKED_TRUNK_PLACER.get()
  }

  override fun placeTrunk(pLevel: LevelSimulatedReader, pBlockSetter: BiConsumer<BlockPos, BlockState>, pRandom: RandomSource, pFreeTreeHeight: Int, pPos: BlockPos, pConfig: TreeConfiguration): List<FoliageAttachment> {
    val direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom)
    val mutableBlockPos = pPos.mutable()
    placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos.relative(direction.opposite), pConfig) { state: BlockState -> state.setValue(RotatedPillarBlock.AXIS, direction.axis) }
    placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos.relative(if (pRandom.nextInt(2) == 0) direction.clockWise else direction.counterClockWise), pConfig)
    for (i in 0 until pFreeTreeHeight) {
      if (pRandom.nextFloat() < 0.4f && i > 2) {
        mutableBlockPos.move(direction)
      }
      placeLog(pLevel, pBlockSetter, pRandom, mutableBlockPos, pConfig)
      mutableBlockPos.move(Direction.UP)
    }

    return ImmutableList.of(FoliageAttachment(mutableBlockPos, 0, false))
  }

  companion object {
    val CODEC: Codec<CrookedTrunkPlacer?> = RecordCodecBuilder.create { placer ->
      trunkPlacerParts(placer).apply(placer) { pBaseHeight: Int, pHeightRandA: Int, pHeightRandB: Int -> CrookedTrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB) }
    }
  }
}