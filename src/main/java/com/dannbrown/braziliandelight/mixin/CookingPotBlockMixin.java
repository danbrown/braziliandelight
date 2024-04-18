package com.dannbrown.braziliandelight.mixin;

import com.dannbrown.braziliandelight.content.block.MilkPotBlock;
import com.dannbrown.braziliandelight.init.AddonBlocks;
import com.dannbrown.braziliandelight.init.AddonItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.CookingPotBlock;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(CookingPotBlock.class)
public abstract class CookingPotBlockMixin {
  @Inject(method = { "use" }, at = { @At("HEAD") }, require = 1, cancellable = true)
  public void brazilianDelight$use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result, CallbackInfoReturnable<InteractionResult> cir) {
    // if player hand is a milk bucket, replace its hand with a bucket and place a milk pot block in place
    if (player.getItemInHand(hand).getItem().equals(Items.MILK_BUCKET)) {
      if(level.isClientSide()) level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

      if(!level.isClientSide()) {
        // place a milk pot block in place of the cooking pot
        level.setBlockAndUpdate(pos, AddonBlocks.INSTANCE.getMILK_POT().getDefaultState().setValue(MilkPotBlock.Companion.getFACING(), state.getValue(CookingPotBlock.FACING).getOpposite()));

        // drop all contents of the cooking pot
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if(tileEntity instanceof CookingPotBlockEntity cookingPotEntity){
          Containers.dropContents(level, pos, cookingPotEntity.getDroppableInventory());
          level.removeBlockEntity(pos);
        }

        // if player is not in creative mode, replace the milk bucket with a bucket
        if(!player.isCreative()) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
      }

      cir.setReturnValue(InteractionResult.SUCCESS);
    }

    // if player hand is a heavy cream bucket, replace its hand with a bucket and place a heavy cream pot block in place
    if (player.getItemInHand(hand).getItem().equals(AddonItems.INSTANCE.getHEAVY_CREAM_BUCKET().get())) {
      if(level.isClientSide()) level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

      if(!level.isClientSide()) {
        // place a heavy cream pot block in place of the cooking pot
        level.setBlockAndUpdate(pos, AddonBlocks.INSTANCE.getHEAVY_CREAM_POT().getDefaultState().setValue(MilkPotBlock.Companion.getFACING(), state.getValue(CookingPotBlock.FACING).getOpposite()));

        // drop all contents of the cooking pot
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if(tileEntity instanceof CookingPotBlockEntity cookingPotEntity){
          Containers.dropContents(level, pos, cookingPotEntity.getDroppableInventory());
        }

        // if player is not in creative mode, replace the heavy cream bucket with a bucket
        if(!player.isCreative()) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
      }

      cir.setReturnValue(InteractionResult.SUCCESS);
    }
  }
}