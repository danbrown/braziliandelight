package com.dannbrown.braziliandelight.content.entity

import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.braziliandelight.init.AddonEntityTypes
import com.dannbrown.braziliandelight.init.AddonItems
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3

class CoconutProjectileEntity : ThrowableItemProjectile {
  constructor(pEntityType: EntityType<out ThrowableItemProjectile>, pLevel: Level) : super(pEntityType, pLevel)
  constructor(pLevel: Level) : super(AddonEntityTypes.COCONUT_PROJECTILE.get(), pLevel)
  constructor(pLevel: Level, livingEntity: LivingEntity) : super(
    AddonEntityTypes.COCONUT_PROJECTILE.get(),
    livingEntity,
    pLevel
  )
  constructor(level: Level, x: Double, y: Double, z: Double) : super(
    AddonEntityTypes.COCONUT_PROJECTILE.get(), x, y, z, level
  )

  override fun getDefaultItem(): Item {
    return AddonBlocks.COCONUT.asItem()
  }

  private fun getSliceItem(): Item {
    return AddonItems.COCONUT_SLICE.get()
  }

  private fun getParticleParameters(): ParticleOptions {
    val itemStack = ItemStack(this.getSliceItem())
    return (if (itemStack.isEmpty) ParticleTypes.ITEM_SNOWBALL else ItemParticleOption(ParticleTypes.ITEM, itemStack))
  }

  private fun addParticles(){
    val particleEffect = this.getParticleParameters()

    for (i in 0..7) {
      level().addParticle(particleEffect, this.x, this.y, this.z, level().random.nextGaussian() * 0.15, 0.2, level().random.nextGaussian() * 0.15)
    }
  }

  override fun handleEntityEvent(status: Byte) {
    if (status.toInt() == 3) {
      addParticles()
    }
  }

  override fun onHitBlock(pResult: BlockHitResult) {
    super.onHitBlock(pResult)
    // ensure server side
    if (!this.level().isClientSide()) {
      if(!this.isRemoved) {
        val random = this.level().random.nextInt(1) // will drop 1 or 2 coconut slices
        this.spawnAtLocation(ItemStack(getSliceItem(), random + 1), 0.0f)
      }
      performEvents(pResult.location)
      discard()
    }
  }

  private val baseDamage = 2.0f
  override fun onHitEntity(entityHitResult: EntityHitResult) {
    super.onHitEntity(entityHitResult)
    val entity: Entity = entityHitResult.entity
    entity.hurt(entity.damageSources().thrown(this, this.owner), baseDamage)
    performEvents(entity.position())
    discard()
  }

  private fun performEvents(location: Vec3) {
    // ensure server side
    if (!level().isClientSide) {
     this.level().broadcastEntityEvent(this, 3.toByte())
     this.level().playSound(null, location.x, location.y, location.z,
       SoundEvents.BAMBOO_BREAK, SoundSource.AMBIENT, 0.5f, 0.4f / (this.level().getRandom().nextFloat() * 0.4f + 0.8f)
     )
     addParticles()
    }
  }
}