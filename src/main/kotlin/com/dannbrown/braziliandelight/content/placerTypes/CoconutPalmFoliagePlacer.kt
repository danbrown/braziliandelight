package com.dannbrown.braziliandelight.content.placerTypes

import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonPlacerTypes
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.TreeFeature
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids

class CoconutPalmFoliagePlacer(pRadius: IntProvider, pOffset: IntProvider) : FoliagePlacer(pRadius, pOffset) {
  override fun type(): FoliagePlacerType<*> {
    return AddonPlacerTypes.COCONUT_PALM_FOLIAGE_PLACER.get()
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
    val CODEC: Codec<CoconutPalmFoliagePlacer> = RecordCodecBuilder.create { placer ->
      foliagePlacerParts(placer).apply(placer) { pRadius: IntProvider, pOffset: IntProvider -> CoconutPalmFoliagePlacer(pRadius, pOffset) }
    }
    val fruitsProvider = WeightedStateProvider(SimpleWeightedRandomList.builder<BlockState>()
      .add(AddonBlocks.COCONUT.get().defaultBlockState().setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING), 3)
      .add(AddonBlocks.GREEN_COCONUT.get().defaultBlockState().setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.CEILING), 2)
      .build()
    )
    val buddingLeavesProvider = BlockStateProvider.simple(AddonBlocks.BUDDING_COCONUT_PALM_LEAVES.get().defaultBlockState())
    fun tryPlaceBudding(pLevel: LevelSimulatedReader, pFoliageSetter: FoliageSetter, pRandom: RandomSource, pTreeConfiguration: TreeConfiguration, pPos: BlockPos): Boolean {
      if (!TreeFeature.validTreePos(pLevel, pPos)) {
        return false
      }
      else {
        var blockState = buddingLeavesProvider.getState(pRandom, pPos)
        if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
          blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, pLevel.isFluidAtPosition(pPos) { fluidState: FluidState -> fluidState.isSourceOfType(Fluids.WATER) }) as BlockState
        }

        pFoliageSetter[pPos] = blockState
        return true
      }
    }

    private fun createQuadrant(direction: Direction, startingPos: BlockPos, pLevel: LevelSimulatedReader, foliageSetter: FoliageSetter, pRandom: RandomSource, pConfig: TreeConfiguration) {
      val pos = startingPos.mutable()

      pos.move(direction)
      tryPlaceBudding(pLevel, foliageSetter, pRandom, pConfig, pos)

      if (pRandom.nextInt(2) == 0) {
        if (pLevel.isStateAtPosition(pos.below()) { t: BlockState -> t.isAir }) {
          foliageSetter[pos.below()] = fruitsProvider.getState(pRandom, pos.below())
        }
      }
      if (pRandom.nextInt(2) == 0) {
        if (pLevel.isStateAtPosition(pos.below().relative(direction.counterClockWise)) { t: BlockState -> t.isAir }) {
          foliageSetter[pos.below()
            .relative(direction.counterClockWise)] = fruitsProvider.getState(pRandom, pos.below().relative(direction.counterClockWise))
        }
      }

      for (i in 0..1) {
        pos.move(direction)
        tryPlaceBudding(pLevel, foliageSetter, pRandom, pConfig, pos)
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