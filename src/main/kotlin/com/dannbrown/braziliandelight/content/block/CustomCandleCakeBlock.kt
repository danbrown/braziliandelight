package com.dannbrown.braziliandelight.content.block

import com.google.common.collect.ImmutableList
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.AbstractCandleBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CakeBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import vectorwing.farmersdelight.common.tag.ModTags
import vectorwing.farmersdelight.common.utility.ItemUtils
import java.util.function.Supplier

class CustomCandleCakeBlock(props: Properties, private val candleBlock: Supplier<CandleBlock>, private val cakeBlock: Supplier<CustomCakeBlock>)
  : AbstractCandleBlock(props.lightLevel { if (it.getValue(LIT)) 3 else 0 }) {
    companion object{
      private const val AABB_OFFSET: Float = 1.0f
      private val CAKE_SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
      private val CANDLE_SHAPE: VoxelShape = box(7.0, 8.0, 7.0, 9.0, 14.0, 9.0)
      private val SHAPE: VoxelShape = Shapes.or(CAKE_SHAPE, CANDLE_SHAPE)
      private val PARTICLE_OFFSETS: Iterable<Vec3> = ImmutableList.of(Vec3(0.5, 1.0, 0.5))
    }

  init {
    this.registerDefaultState((stateDefinition.any() as BlockState).setValue(LIT, false))
  }

  override fun getCloneItemStack(state: BlockState?, target: HitResult?, level: BlockGetter?, pos: BlockPos?, player: Player?): ItemStack {
    return ItemStack(cakeBlock.get().asItem())
  }
  override fun getParticleOffsets(pState: BlockState): Iterable<Vec3> {
    return PARTICLE_OFFSETS
  }

  override fun getShape(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pContext: CollisionContext): VoxelShape {
    return SHAPE
  }

  override fun use(pState: BlockState, pLevel: Level, pPos: BlockPos, pPlayer: Player, pHand: InteractionHand, pHit: BlockHitResult): InteractionResult {
    val heldStack = pPlayer.getItemInHand(pHand)

    // Knives interact with the cake
    if (heldStack.`is`(ModTags.KNIVES)) {
      dropCandle(pLevel, pPlayer, pPos)
      return cakeBlock.get().cutSlice(pLevel, pPos, pState, pPlayer)
    }

    // Flint and steel or fire charge interact with the candle
    if(heldStack.`is`(Items.FLINT_AND_STEEL) || heldStack.`is`(Items.FIRE_CHARGE)){
      litCakeCandle(pLevel, pPos, pState, pPlayer, pHand)
      return InteractionResult.sidedSuccess(pLevel.isClientSide)
    }

    // Empty hand interacts with the candle
    if (candleHit(pHit) && pPlayer.getItemInHand(pHand).isEmpty && pState.getValue(LIT) as Boolean) {
      extinguish(pPlayer, pState, pLevel, pPos)
      return InteractionResult.sidedSuccess(pLevel.isClientSide)
    }

    // Eat
    val eatResult = CustomCakeBlock.eat(pLevel, pPos, cakeBlock.get().defaultBlockState(), pPlayer)
    if (eatResult.consumesAction()) {
      dropResources(pState, pLevel, pPos)
    }
    return eatResult
  }

  private fun candleHit(pHit: BlockHitResult): Boolean {
    return pHit.location.y - pHit.blockPos.y.toDouble() > 0.5
  }

  override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
    pBuilder.add(*arrayOf<Property<*>>(LIT))
  }

  override fun getCloneItemStack(pLevel: BlockGetter, pPos: BlockPos, pState: BlockState): ItemStack {
    return ItemStack(Blocks.CAKE)
  }

  override fun updateShape(pState: BlockState, pDirection: Direction, pNeighborState: BlockState, pLevel: LevelAccessor, pPos: BlockPos, pNeighborPos: BlockPos): BlockState {
    return if (pDirection == Direction.DOWN && !pState.canSurvive(pLevel, pPos)) Blocks.AIR.defaultBlockState() else super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos)
  }

  override fun canSurvive(pState: BlockState, pLevel: LevelReader, pPos: BlockPos): Boolean {
    return pLevel.getBlockState(pPos.below()).isSolid
  }

  override fun getAnalogOutputSignal(pState: BlockState, pLevel: Level, pPos: BlockPos): Int {
    return CakeBlock.FULL_CAKE_SIGNAL
  }

  override fun hasAnalogOutputSignal(pState: BlockState): Boolean {
    return true
  }

  override fun isPathfindable(pState: BlockState, pLevel: BlockGetter, pPos: BlockPos, pType: PathComputationType): Boolean {
    return false
  }

  fun dropCandle(pLevel: Level, pPlayer: Player, pPos: BlockPos) {
    val direction: Direction = pPlayer.direction.opposite
    ItemUtils.spawnItemEntity(pLevel, ItemStack(candleBlock.get().asItem()),
      pPos.x + 0.5, pPos.y + 0.3, pPos.z + 0.5,
      direction.stepX * 0.15, 0.05, direction.stepZ * 0.15
    )
  }

  fun litCakeCandle(pLevel: Level, pPos: BlockPos, pState: BlockState, pPlayer: Player, pHand: InteractionHand) {
    val heldStack = pPlayer.getItemInHand(pHand)
    if(heldStack.`is`(Items.FLINT_AND_STEEL) || heldStack.`is`(Items.FIRE_CHARGE)){
      if(heldStack.`is`(Items.FLINT_AND_STEEL)){
        pLevel.playSound(null as Player?, pPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0f, 1.0f)
      }else if(heldStack.`is`(Items.FIRE_CHARGE)){
        pLevel.playSound(null as Player?, pPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0f, 1.0f)
        if(!pPlayer.isCreative) heldStack.shrink(1)
      }
      pLevel.setBlock(pPos, pState.setValue(LIT, true), 11);
      pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos)
      pPlayer.awardStat(Stats.ITEM_USED[heldStack.item])
    }

    // ---
  }
}