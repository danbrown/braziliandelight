package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.registrate.util.DispenserBehaviorUtil
import net.minecraft.core.Position
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

object ModDispenserBehaviors {
  val BEHAVIORS = REGISTRATE
    .dispenserBehavior({ ModItems.REPUGNANT_ARROW.get() }, object : AbstractProjectileDispenseBehavior() {
      override fun getProjectile(level: Level, position: Position, itemStack: ItemStack): Projectile {
        val arrow = RepugnantArrow(level, position.x(), position.y(), position.z())
        arrow.pickup = AbstractArrow.Pickup.ALLOWED
        return arrow
      }
    })
    .dispenserBehavior(
      { ModBlocks.COCONUT.get() },
      DispenserBehaviorUtil.registerThrowableBehavior({ ModBlocks.COCONUT.get() }, CoconutProjectileEntity::class)
    )

  fun register() {
    // init
  }
}