package com.dannbrown.braziliandelight.content.tree

import com.dannbrown.braziliandelight.content.block.BuddingAcaiBlock
import com.dannbrown.braziliandelight.content.block.BuddingDoubleCropBlock
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonTreeDecorators
import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.Plane
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType

class AcaiTreeDecorator(private val probability: Float) : TreeDecorator() {
  override fun type(): TreeDecoratorType<*> {
    return AddonTreeDecorators.ACAI_DECORATOR.get()
  }

  override fun place(pContext: Context) {
    val randomSource = pContext.random()
      val logPosList: List<BlockPos> = pContext.logs()
      val firstY = logPosList[0].y
      logPosList.stream()
        // filter the logs that are at least 2 blocks above the first log
        .filter { pos: BlockPos -> pos.y - firstY >= 4 }
        .forEach { pos: BlockPos ->
          val horizontalDirections = Plane.HORIZONTAL.iterator()
          while (horizontalDirections.hasNext()) {
            val direction = horizontalDirections.next()
            if (!(randomSource.nextFloat() >= this.probability)) {
              val direction1 = direction.opposite
              val offsetPos = pos.relative(direction)
              if (pContext.isAir(offsetPos) && pContext.isAir(offsetPos.below())) {
                val blockState = (AddonBlocks.BUDDING_ACAI_BRANCH.get().defaultBlockState()
                  .setValue(BuddingDoubleCropBlock.AGE, randomSource.nextInt(BuddingDoubleCropBlock.MAX_AGE))
                  .setValue(BuddingAcaiBlock.FACING, direction1))
                pContext.setBlock(offsetPos, blockState)
              }
            }
          }
        }
  }

  companion object {
    val CODEC: Codec<AcaiTreeDecorator> = Codec.floatRange(0.0f, 1.0f)
      .fieldOf("probability")
      .xmap({ prob: Float -> AcaiTreeDecorator(prob) }, { decorator: AcaiTreeDecorator -> decorator.probability })
      .codec()
  }
}
