package com.dannbrown.braziliandelight.compat.vanilla

import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import com.dannbrown.braziliandelight.init.AddonBlocks
import com.dannbrown.deltaboxlib.registry.datagen.DeltaboxDispenserBehaviors

object AddonDispenserBehaviors: DeltaboxDispenserBehaviors() {
    val COCONUT_DISPENSER = registerThrowableBehavior({ AddonBlocks.COCONUT.get() }, CoconutProjectileEntity::class)
}