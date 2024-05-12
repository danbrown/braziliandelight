package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.tree.AcaiTreeDecorator
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

object AddonTreeDecorators {
  val TREE_DECORATOR_TYPES = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, AddonContent.MOD_ID)

  val ACAI_DECORATOR = TREE_DECORATOR_TYPES.register("acaipalm_decorator") { TreeDecoratorType(AcaiTreeDecorator.CODEC) }

  fun register(bus: IEventBus) {
    TREE_DECORATOR_TYPES.register(bus)
  }
}