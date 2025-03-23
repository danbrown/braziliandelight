package com.dannbrown.braziliandelight.content.placerTypes

import com.dannbrown.braziliandelight.content.blocks.AcaiCropBlock
import com.dannbrown.braziliandelight.init.ModBlocks
import com.dannbrown.braziliandelight.init.ModPlacerTypes
import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction.Plane
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType

class AcaiTreeDecorator(private val probability: Float) : TreeDecorator() {
  override fun type(): TreeDecoratorType<*> {
    return ModPlacerTypes.ACAI_DECORATOR.get()
  }

  override fun place(pContext: Context) {
    val randomSource = pContext.random()
    val logPosList: List<BlockPos> = pContext.logs()
    val firstY = logPosList[0].y
    logPosList.stream()
      .filter { pos: BlockPos -> pos.y - firstY >= 4 }
      .forEach { pos: BlockPos ->
        val horizontalDirections = Plane.HORIZONTAL.iterator()
        while (horizontalDirections.hasNext()) {
          val direction = horizontalDirections.next()
          if (!(randomSource.nextFloat() >= this.probability)) {
            val direction1 = direction.opposite
            val offsetPos = pos.relative(direction)
            if (pContext.isAir(offsetPos) && pContext.isAir(offsetPos.below())) {
              val blockState = ModBlocks.BUDDING_ACAI_BRANCH.get().defaultBlockState()
                .setValue(CropBlock.AGE, randomSource.nextInt(CropBlock.MAX_AGE))
                .setValue(AcaiCropBlock.FACING, direction1)
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
