package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import com.dannbrown.braziliandelight.lib.AddonNames
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AddonPaintings {
  val PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, AddonContent.MOD_ID)

  val BRAZILIAN_MIKU = PAINTINGS.register(AddonNames.BRAZILIAN_MIKU) { PaintingVariant(16, 32) }

  fun register(bus: IEventBus){
    PAINTINGS.register(bus)
  }
}