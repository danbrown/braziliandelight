package com.dannbrown.braziliandelight.content.effect

import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import java.util.function.Predicate

class RepulsiveEffect : MobEffect(MobEffectCategory.HARMFUL, 0X577D4D) {
  override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
    if (!entity.commandSenderWorld.isClientSide) {
      val level = entity.commandSenderWorld
      val radiusHorizontal = (3.0 * (amplifier + 1)).coerceAtMost(12.0)
      val radiusVertical = 3.0
      val range = AABB(entity.x - radiusHorizontal, entity.y - radiusVertical, entity.z - radiusHorizontal, entity.x + radiusHorizontal, entity.y + radiusVertical, entity.z + radiusHorizontal)
      val entityPredicate: Predicate<Entity> = EntitySelector.NO_SPECTATORS.and { entity ->
        if (entity is LivingEntity) { return@and true }
        false
      }
      val entities = level.getEntitiesOfClass(PathfinderMob::class.java, range, entityPredicate)

      for (mob in entities) {
        if (mob != null) {
          val xt = mob.xOld
          val yt = mob.yOld
          val zt = mob.zOld
          val x1 = Mth.floor(entity.xOld)
          val y1 = Mth.floor(entity.yOld)
          val z1 = Mth.floor(entity.zOld)
          val x2 = xt - x1.toDouble()
          val y2 = yt - y1.toDouble()
          val z2 = zt - z1.toDouble()
          if (Mth.abs(x2.toInt()) < radiusHorizontal && Mth.abs(z2.toInt()) < radiusVertical * 2 && Mth.abs(y2.toInt()) < radiusHorizontal) {
            val vec3d = DefaultRandomPos.getPosAway(mob, 18, 7, Vec3(mob.xOld, mob.yOld, mob.zOld))
            val wb = level.worldBorder
            if ((vec3d != null && wb.isWithinBounds(BlockPos(mob.xOld.toInt(), mob.yOld.toInt(), mob.zOld.toInt()))) && this.distanceToSqr(entity, vec3d.x, vec3d.y, vec3d.z) >= distanceToSqr(entity, mob.position())) {
              mob.navigation.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0)
            }
          }
        }
      }
    }
  }

  /**
   * Gets the squared distance to the position.
   */
  private fun distanceToSqr(owner: LivingEntity, pX: Double, pY: Double, pZ: Double): Double {
    val d0: Double = owner.x - pX
    val d1: Double = owner.y - pY
    val d2: Double = owner.z - pZ
    return d0 * d0 + d1 * d1 + d2 * d2
  }

  private fun distanceToSqr(owner: LivingEntity, pVec: Vec3): Double {
    val d0: Double = owner.x - pVec.x
    val d1: Double = owner.y - pVec.y
    val d2: Double = owner.z - pVec.z
    return d0 * d0 + d1 * d1 + d2 * d2
  }

  override fun isDurationEffectTick(duration: Int, amplifier: Int): Boolean {
    return duration > 0
  }
}