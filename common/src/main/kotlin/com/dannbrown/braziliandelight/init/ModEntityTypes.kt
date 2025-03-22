package com.dannbrown.braziliandelight.init

import com.dannbrown.braziliandelight.content.entity.RepugnantArrow
import com.dannbrown.braziliandelight.init.ModContent.REGISTRATE
import com.dannbrown.deltaboxlib.content.item.arrow.BaseArrowRenderer
import net.minecraft.world.entity.MobCategory

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
    .renderer { ctx -> BaseArrowRenderer(ctx, ModContent.MOD_ID, ModNames.REPUGNANT_ARROW) }
    .register()

  fun register() {
    REGISTRATE.buildEntityTypes()
  }
}