package com.dannbrown.braziliandelight.content.block

import com.dannbrown.braziliandelight.init.AddonBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.FallingBlockEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.Supplier

class FallingCoconutBlock(props: Properties, private val item: Supplier<ItemLike>): FallingBlock(props) {
  companion object{
    val SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    const val FALL_DAMAGE_PER_DISTANCE = 2.0f
    const val FALL_DAMAGE_MAX = 20
    val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
  }

  init {
    registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH))
  }

  override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
    pBuilder.add(FACING)
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(item.get())
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE
  }

  override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
    return defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)
  }

  override fun falling(pFallingEntity: FallingBlockEntity) {
    pFallingEntity.setHurtsEntities(FALL_DAMAGE_PER_DISTANCE, FALL_DAMAGE_MAX)
  }

  override fun getFallDamageSource(pEntity: Entity): DamageSource {
    return pEntity.damageSources().fallingBlock(pEntity)
  }

  override fun isPathfindable(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pType: PathComputationType): Boolean {
    return false
  }

  override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    // if bellow is face sturdy
    val blockPos = pPos.below()
    val blockState = pLevel.getBlockState(blockPos)
    return blockState.isFaceSturdy(pLevel, blockPos, Direction.UP)
  }

  override fun onLand(pLevel: Level, pPos: BlockPos, pState: BlockState, pReplaceableState: BlockState, pFallingBlock: FallingBlockEntity) {
    pLevel.setBlockAndUpdate(pPos, AddonBlocks.COCONUT.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, pState.getValue(FACING)).setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR))
  }

  override fun onBrokenAfterFall(pLevel: Level, pPos: BlockPos, pFallingBlock: FallingBlockEntity) {
    if (!pFallingBlock.isSilent) {
      Block.popResource(pLevel, pPos, ItemStack(item.get()))
    }
  }
}