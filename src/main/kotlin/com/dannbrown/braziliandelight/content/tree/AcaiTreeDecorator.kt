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
    if (!(randomSource.nextFloat() >= this.probability)) {
      val blockPosList: List<BlockPos> = pContext.logs()
      val height = blockPosList[0].y
      blockPosList.stream()
        .filter { pos: BlockPos -> pos.y - height <= 2 }
        .forEach { pos: BlockPos ->
          val var3 = Plane.HORIZONTAL.iterator()
          while (var3.hasNext()) {
            val direction = var3.next()
            if (randomSource.nextFloat() <= 0.25f) {
              val direction1 = direction.opposite
              val offsetPos = pos.offset(direction1.stepX, 0, direction1.stepZ)
              if (pContext.isAir(offsetPos)) {
                pContext.setBlock(offsetPos, (AddonBlocks.BUDDING_ACAI.get().defaultBlockState().setValue(BuddingDoubleCropBlock.AGE, randomSource.nextInt(BuddingDoubleCropBlock.MAX_AGE))).setValue(BuddingAcaiBlock.FACING, direction1))
              }
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
