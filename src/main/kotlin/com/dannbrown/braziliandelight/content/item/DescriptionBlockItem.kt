package com.dannbrown.braziliandelight.content.item

import com.dannbrown.braziliandelight.AddonContent
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class DescriptionBlockItem<T : Block>(b: T, p: Properties) : BlockItem(b, p) {
  @OnlyIn(Dist.CLIENT)
  override fun appendHoverText(stack: ItemStack, level: Level?, tooltip: MutableList<Component>, isAdvanced: TooltipFlag) {
    val textEmpty: MutableComponent = Component.translatable("tooltip." + AddonContent.MOD_ID  + ".$this")
    tooltip.add(textEmpty.withStyle(ChatFormatting.BLUE))
  }
}
