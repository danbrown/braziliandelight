package com.dannbrown.discoverplanetzero.datagen.worldgen.trunkPlacer

import com.dannbrown.discover.init.DiscoverTrunkPlacerType
import com.dannbrown.discoverplanetzero.init.AddonTrunkPlacerType
import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType
import java.util.*
import java.util.function.BiConsumer


class ForkingStalkTrunkPlacer(pBaseHeight: Int, pHeightRandA: Int, pHeightRandB: Int) :
  TrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB) {
  override fun type(): TrunkPlacerType<*> {
    return AddonTrunkPlacerType.FORKING_STALK_TRUNK_PLACER.get()
  }

  override fun placeTrunk(
    levelReader: LevelSimulatedReader,
    blockStateBiConsumer: BiConsumer<BlockPos, BlockState>,
    randomSource: RandomSource,
    pFreeTreeHeight: Int,
    blockPos: BlockPos,
    treeConfiguration: TreeConfiguration
  ): List<FoliageAttachment> {
    // Set a dirt block at the base of the tree
    // setDirtAt(levelReader, blockStateBiConsumer, randomSource, blockPos.below(), treeConfiguration)

    // Initialize variables
    val list: MutableList<FoliageAttachment> = Lists.newArrayList()
    val direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource)
    val i = pFreeTreeHeight - randomSource.nextInt(4) - 1
    var j = 3 - randomSource.nextInt(3)
    val mutableBlockPos = MutableBlockPos()
    var k = blockPos.x
    var l = blockPos.z
    var optionalint = OptionalInt.empty()

    // Generate the main trunk of the tree
    for (i1 in 0 until pFreeTreeHeight) {
      val j1 = blockPos.y + i1
      if (i1 >= i && j > 0) {
        k += direction.stepX
        l += direction.stepZ
        --j
      }
      if (this.placeLog(
          levelReader,
          blockStateBiConsumer,
          randomSource,
          mutableBlockPos.set(k, j1, l),
          treeConfiguration
        )
      ) {
        optionalint = OptionalInt.of(j1 + 1)
      }
    }

    // Add foliage attachment for the main trunk if successful
    if (optionalint.isPresent) {
      list.add(FoliageAttachment(BlockPos(k, optionalint.getAsInt(), l), 1, false))
    }

    // Reset variables for additional branching
    k = blockPos.x
    l = blockPos.z
    val direction1 = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource)

    // Create branches from the main trunk
    if (direction1 != direction) {
      val j2 = i - randomSource.nextInt(2) - 1
      var k1 = 1 + randomSource.nextInt(3)
      optionalint = OptionalInt.empty()
      var l1 = j2

      while (l1 < pFreeTreeHeight && k1 > 0) {
        if (l1 >= 1) {
          val i2 = blockPos.y + l1
          k += direction1.stepX
          l += direction1.stepZ


          // Place logs for the branch
          val logPlaced = this.placeLog(
            levelReader,
            blockStateBiConsumer,
            randomSource,
            mutableBlockPos.set(k, i2, l),
            treeConfiguration
          )

          if (logPlaced) {
            // Place logs below the branch
            this.placeLog(
              levelReader,
              blockStateBiConsumer,
              randomSource,
              mutableBlockPos.set(k, i2 - 1, l),
              treeConfiguration
            )

            // add the foliage attachment for the branch if successful
            optionalint = OptionalInt.of(i2 + 1)
          }
        }
        ++l1
        --k1
      }

      // Add foliage attachment for the branch if successful
      if (optionalint.isPresent) {
        list.add(FoliageAttachment(BlockPos(k, optionalint.getAsInt(), l), 0, false))
      }
    }

    // Return the list of foliage attachments
    return list
  }

  companion object {
    val CODEC: Codec<ForkingStalkTrunkPlacer> =
      RecordCodecBuilder.create { placerInstance: RecordCodecBuilder.Instance<ForkingStalkTrunkPlacer> ->
        trunkPlacerParts(placerInstance).apply(
          placerInstance
        ) { baseHeight: Int, heightRandA: Int, heightRandB: Int ->
          ForkingStalkTrunkPlacer(
            baseHeight,
            heightRandA,
            heightRandB
          )
        }
      }
  }
}