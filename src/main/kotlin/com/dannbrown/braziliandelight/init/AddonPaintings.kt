package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.AddonContent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AddonPaintings {
  val PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, AddonContent.MOD_ID)


  fun register(bus: IEventBus){
    PAINTINGS.register(bus)
  }
}