package com.dannbrown.braziliandelight

import com.dannbrown.deltaboxlib.registrate.util.DeltaboxUtil
import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

object FarmersCompat {
  const val MOD_ID = "farmersdelight"

  object TAGS {
    val KNIVES = DeltaboxUtil.TAGS.modItemTag(MOD_ID, "tools/knives")
  }

  fun spawnItemEntity(
    level: Level,
    stack: ItemStack,
    x: Double,
    y: Double,
    z: Double,
    xMotion: Double,
    yMotion: Double,
    zMotion: Double
  ) {
    val entity = ItemEntity(level, x, y, z, stack);
    entity.setDeltaMovement(xMotion, yMotion, zMotion);
    level.addFreshEntity(entity);
  }

  @JvmStatic
  @ExpectPlatform
  fun getCookingPot(): Block {
    throw AssertionError("Method not implemented on modloader")
  }

  @JvmStatic
  @ExpectPlatform
  fun getNourishment(): MobEffect {
    throw AssertionError("Method not implemented on modloader")
  }

  @JvmStatic
  @ExpectPlatform
  fun getComfort(): MobEffect {
    throw AssertionError("Method not implemented on modloader")
  }
}