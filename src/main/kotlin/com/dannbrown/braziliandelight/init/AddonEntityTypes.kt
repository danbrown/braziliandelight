package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.lib.AddonNames
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.Level
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.network.PlayMessages.SpawnEntity
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AddonEntityTypes {
  val ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AddonContent.MOD_ID)

  fun register(bus: IEventBus?) {
    ENTITY_TYPES.register(bus)
  }

  val COCONUT_PROJECTILE = ENTITY_TYPES.register(AddonNames.COCONUT) { EntityType.Builder.of({ e, l -> CoconutProjectileEntity(e, l) } , MobCategory.MISC).sized(0.5f, 0.5f).build(AddonNames.COCONUT) }
  val REPUGNANT_ARROW = ENTITY_TYPES.register(AddonNames.REPUGNANT_ARROW) { EntityType.Builder.of({ e, l -> RepugnantArrow(e, l) } , MobCategory.MISC)
    .sized(0.5f, 0.5f)
    .clientTrackingRange(4)
    .updateInterval(20)
    .setCustomClientFactory { s: SpawnEntity, w: Level -> RepugnantArrow(s, w) }
    .build(AddonNames.REPUGNANT_ARROW) }

}