package com.dannbrown.braziliandelight.content.entity

import com.dannbrown.braziliandelight.init.AddonEffects
import com.dannbrown.braziliandelight.init.AddonEntityTypes
import com.dannbrown.braziliandelight.init.AddonItems
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.Mth
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.Vec3
import net.minecraftforge.network.PlayMessages.SpawnEntity
import java.util.function.Predicate

class RepugnantArrow : AbstractArrow {
  constructor(entityType: EntityType<out RepugnantArrow>, level: Level) : super(entityType, level)
  constructor(level: Level, livingEntity: LivingEntity) : super(AddonEntityTypes.REPUGNANT_ARROW.get(), livingEntity, level)
  constructor(level: Level, px: Double, py: Double, pz: Double) : super(AddonEntityTypes.REPUGNANT_ARROW.get(), px, py, pz, level)
  constructor(spawnEntity: SpawnEntity, world: Level) : this(AddonEntityTypes.REPUGNANT_ARROW.get(), world)

  override fun getPickupItem(): ItemStack {
    return ItemStack(AddonItems.REPUGNANT_ARROW.get())
  }

  override fun doPostHurtEffects(livingEntity: LivingEntity) {
    super.doPostHurtEffects(livingEntity)
    val nauseaEffect = MobEffectInstance(MobEffects.CONFUSION, 400, 0)
    val repugnantEffect = MobEffectInstance(AddonEffects.REPUGNANT.get(), 400, 1)
    livingEntity.addEffect(nauseaEffect, this.effectSource)
    livingEntity.addEffect(repugnantEffect, this.effectSource)
  }

  override fun tick() {
    super.tick()
    if (level().isClientSide) {
      val k = MobEffects.POISON.color
      val d5 = ((k shr 16 and 255).toFloat() / 255.0f).toDouble()
      val d6 = ((k shr 8 and 255).toFloat() / 255.0f).toDouble()
      val d7 = ((k and 255).toFloat() / 255.0f).toDouble()
      level().addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, this.x, this.y, this.z, d5, d6, d7)
    }
    val range = AABB(this.x - 12.0, this.y - 3.0, this.z - 12.0, this.x + 12.0, this.y + 3.0, this.z + 12.0)
    val entityPredicate: Predicate<Entity> = EntitySelector.NO_SPECTATORS.and { entity ->
      if (entity is LivingEntity) {
        return@and true
      }
      false
    }
    val entities = level().getEntitiesOfClass(PathfinderMob::class.java, range, entityPredicate)
    for (entity in entities) {
      if (entity != null) {
        val xt = entity.xOld
        val yt = entity.yOld
        val zt = entity.zOld
        val x1 = Mth.floor(this.xOld)
        val y1 = Mth.floor(this.yOld)
        val z1 = Mth.floor(this.zOld)
        val x2 = xt - x1.toDouble()
        val y2 = yt - y1.toDouble()
        val z2 = zt - z1.toDouble()
        if (Mth.abs(x2.toInt()) < 12 && Mth.abs(z2.toInt()) < 6 && Mth.abs(y2.toInt()) < 12) {
          val vec3d = DefaultRandomPos.getPosAway(entity, 18, 7, Vec3(entity.xOld, entity.yOld, entity.zOld))
          val wb = level().worldBorder
          if ((vec3d != null && wb.isWithinBounds(BlockPos(entity.xOld.toInt(), entity.yOld.toInt(), entity.zOld.toInt()))) && this.distanceToSqr(vec3d.x, vec3d.y, vec3d.z) >= this.distanceToSqr(entity)) {
            entity.navigation.moveTo(vec3d.x, vec3d.y, vec3d.z, 2.0)
          }
        }
      }
    }
  }

  override fun onHitEntity(hitResult: EntityHitResult) {
    super.onHitEntity(hitResult)
  }

  override fun onHitBlock(hitResult: BlockHitResult) {
    super.onHitBlock(hitResult)
  }

  override fun readAdditionalSaveData(tag: CompoundTag) {
    super.readAdditionalSaveData(tag)
  }

  override fun addAdditionalSaveData(tag: CompoundTag) {
    super.addAdditionalSaveData(tag)
  }
}
