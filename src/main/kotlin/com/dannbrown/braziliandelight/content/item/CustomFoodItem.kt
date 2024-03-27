package com.dannbrown.braziliandelight.content.item

import com.dannbrown.braziliandelight.AddonContent
import net.minecraft.ChatFormatting
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.stats.Stats
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffectUtil
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import vectorwing.farmersdelight.common.Configuration
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

/**
 * Items that can be consumed by an entity. Based on the original ConsumableItem class from Farmers Delight with a few changes.
 * When consumed, they may affect the consumer somehow, and will give back containers if applicable, regardless of their stack size.
 */
open class CustomFoodItem(props: Properties, private var hasFoodEffectTooltip: Boolean = false, private var hasCustomTooltip: Boolean = false): Item(props) {

  override fun finishUsingItem(stack: ItemStack, level: Level, consumer: LivingEntity): ItemStack {
    if (!level.isClientSide) {
      this.affectConsumer(stack, level, consumer)
    }
    val containerStack = stack.craftingRemainingItem

    if (stack.isEdible) {
      super.finishUsingItem(stack, level, consumer)
    }
    else {
      val player: Player? = if (consumer is Player) consumer else null
      if (player is ServerPlayer) {
        CriteriaTriggers.CONSUME_ITEM.trigger(player, stack)
      }
      if (player != null) {
        player.awardStat(Stats.ITEM_USED.get(this))
        if (!player.abilities.instabuild) {
          stack.shrink(1)
        }
      }
    }

    if (stack.isEmpty) {
      return containerStack
    }
    else {
      if (consumer is Player && !(consumer).abilities.instabuild) {
        if (!consumer.inventory
            .add(containerStack)) {
          consumer.drop(containerStack, false)
        }
      }
      return stack
    }
  }

  /**
   * Override this to apply changes to the consumer (e.g. curing effects).
   */
  fun affectConsumer(stack: ItemStack?, level: Level?, consumer: LivingEntity?) {
  }

  @OnlyIn(Dist.CLIENT)
  override fun appendHoverText(stack: ItemStack, level: Level?, tooltip: MutableList<Component>, isAdvanced: TooltipFlag) {
    if (Configuration.FOOD_EFFECT_TOOLTIP.get()) {
      if (this.hasCustomTooltip) {
        val textEmpty: MutableComponent = Component.translatable(AddonContent.MOD_ID + ".tooltip.$this")
        tooltip.add(textEmpty.withStyle(ChatFormatting.BLUE))
      }
      if (this.hasFoodEffectTooltip) {
        addFoodEffectTooltip(stack, tooltip, 1.0f)
      }
    }
  }
  @OnlyIn(Dist.CLIENT)
  fun addFoodEffectTooltip(itemIn: ItemStack, lores: MutableList<Component>, durationFactor: Float) {
    val foodStats = itemIn.item.foodProperties ?: return
    val effectList: List<Pair<MobEffectInstance, Float>> = foodStats.effects
    val attributeList: MutableList<Pair<Attribute, AttributeModifier>> = Lists.newArrayList()
    if (effectList.isEmpty()) {
      lores.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY))
    }
    else {
      for (effectPair in effectList) {
        val instance: MobEffectInstance = effectPair.first
        var iformattabletextcomponent = Component.translatable(instance.descriptionId)
        val effect = instance.effect
        val attributeMap: Map<Attribute, AttributeModifier> = effect.attributeModifiers
        if (attributeMap.isNotEmpty()) {
          for ((key, rawModifier) in attributeMap) {
            val modifier = AttributeModifier(rawModifier.name, effect.getAttributeModifierValue(instance.amplifier, rawModifier), rawModifier.operation)
            attributeList.add(Pair(key, modifier))
          }
        }

        if (instance.amplifier > 0) {
          iformattabletextcomponent = Component.translatable("potion.withAmplifier", iformattabletextcomponent, Component.translatable("potion.potency." + instance.amplifier))
        }

        if (instance.duration > 20) {
          iformattabletextcomponent = Component.translatable("potion.withDuration", iformattabletextcomponent, MobEffectUtil.formatDuration(instance, durationFactor))
        }

        lores.add(iformattabletextcomponent.withStyle(effect.category.tooltipFormatting))
      }
    }

//    if (attributeList.isNotEmpty()) {
//      lores.add(CommonComponents.EMPTY)
//      lores.add(Component.translatable("potion.whenDrank")
//        .withStyle(ChatFormatting.DARK_PURPLE))
//
//      for (pair in attributeList) {
//        val modifier: AttributeModifier = pair.getSecond()
//        val amount = modifier.amount
//        var formattedAmount: Double
//        formattedAmount = if (modifier.operation != AttributeModifier.Operation.MULTIPLY_BASE && modifier.operation != AttributeModifier.Operation.MULTIPLY_TOTAL) {
//          modifier.amount
//        }
//        else {
//          modifier.amount * 100.0
//        }
//
//        if (amount > 0.0) {
//          lores.add(Component.translatable("attribute.modifier.plus." + modifier.operation.toValue(),
//            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount),
//            Component.translatable(pair.getFirst()
//              .getDescriptionId()))
//            .withStyle(ChatFormatting.BLUE))
//        }
//        else if (amount < 0.0) {
//          formattedAmount = formattedAmount * -1.0
//          lores.add(Component.translatable("attribute.modifier.take." + modifier.operation.toValue(),
//            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(formattedAmount),
//            Component.translatable(pair.getFirst()
//              .getDescriptionId()))
//            .withStyle(ChatFormatting.RED))
//        }
//      }
//    }
  }
}