package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import com.dannbrown.braziliandelight.lib.AddonNames
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AddonEntityTypes {
  val ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AddonContent.MOD_ID)

  fun register(bus: IEventBus?) {
    ENTITY_TYPES.register(bus)
  }

  val COCONUT_PROJECTILE = ENTITY_TYPES.register(AddonNames.COCONUT) { EntityType.Builder.of({ e, l -> CoconutProjectileEntity(e, l) } , MobCategory.MISC).sized(0.5f, 0.5f).build(AddonNames.COCONUT) }

}