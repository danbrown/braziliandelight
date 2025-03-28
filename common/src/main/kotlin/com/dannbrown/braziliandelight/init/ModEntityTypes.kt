package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.entity.CoconutProjectileEntity
import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.content.item.arrow.BaseArrowRenderer
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.world.entity.MobCategory
import java.util.function.Function

object ModEntityTypes {
  val REPUGNANT_ARROW = REGISTRATE.entityType<RepugnantArrow>(ModNames.REPUGNANT_ARROW)
    .factory { e, l -> RepugnantArrow(e, l) }
    .category(MobCategory.MISC)
    .properties { p ->
      p
        .sized(0.5f, 0.5f)
        .clientTrackingRange(4)
        .updateInterval(20)
    }
    .renderer { Function { ctx -> BaseArrowRenderer(ctx, ModContent.MOD_ID, ModNames.REPUGNANT_ARROW) } }
    .register()

  val COCONUT_PROJECTILE = REGISTRATE.entityType<CoconutProjectileEntity>(ModNames.COCONUT)
    .factory { e, l -> CoconutProjectileEntity(e, l) }
    .category(MobCategory.MISC)
    .properties { p ->
      p.sized(0.5f, 0.5f)
    }
    .renderer { Function { ctx -> ThrownItemRenderer(ctx) } }
    .register()

  fun register() {
    REGISTRATE.buildEntityTypes()
  }
}