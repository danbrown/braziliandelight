package com.dannbrown.braziliandelight.datagen.content.block

import com.dannbrown.braziliandelight.content.block.CropLeavesBlock
import com.dannbrown.braziliandelight.datagen.content.transformers.CustomBlockLootPresets
import com.dannbrown.braziliandelight.init.AddonBlocks.BLOCKS
import com.dannbrown.deltaboxlib.content.block.FlammableLeavesBlock
import com.dannbrown.deltaboxlib.content.block.GenericSaplingBlock
import com.dannbrown.deltaboxlib.lib.LibTags
import com.dannbrown.deltaboxlib.registry.transformers.BlockLootPresets
import com.dannbrown.deltaboxlib.registry.transformers.BlockstatePresets
import com.tterrag.registrate.util.entry.BlockEntry
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraftforge.client.model.generators.ConfiguredModel
import java.util.function.Supplier

object LeavesBuilderPresets {
  fun <T : LeavesBlock> createLeavesBlock(
    _name: String,
    color: MapColor,
    sapling: Supplier<GenericSaplingBlock>,
    blockFactory: (BlockBehaviour.Properties) -> T = { p -> FlammableLeavesBlock(p, 60, 30) as T }
  ): BlockEntry<T> {
    return BLOCKS
      .create<T>(_name + "_leaves")
      .blockFactory(blockFactory)
      .color(color)
      .copyFrom { Blocks.OAK_LEAVES }
      .properties { p ->
        p.randomTicks()
          .noOcclusion()
          .isSuffocating { s, b, p -> false }
          .isViewBlocking { s, b, p -> false }
          .isRedstoneConductor { s, b, p -> false }
          .ignitedByLava()
      }
      .blockTags(
        listOf(BlockTags.LEAVES, LibTags.forgeBlockTag("leaves"), BlockTags.MINEABLE_WITH_HOE)
      )
      .itemTags(listOf(ItemTags.LEAVES, LibTags.forgeItemTag("leaves")))
      .blockstate(BlockstatePresets.leavesBlock(_name + "_leaves"))
      .loot(BlockLootPresets.leavesLoot { sapling.get() })
      .register()
  }

  fun <T : LeavesBlock> createBuddingLeavesBlock(
    _name: String,
    color: MapColor,
    sapling: Supplier<GenericSaplingBlock>,
    blockFactory: (BlockBehaviour.Properties) -> T = { p -> FlammableLeavesBlock(p, 60, 30) as T }
  ): BlockEntry<T> {
    return BLOCKS
      .create<T>("budding_" + _name + "_leaves")
      .blockFactory(blockFactory)
      .color(color)
      .copyFrom { Blocks.OAK_LEAVES }
      .properties { p ->
        p.randomTicks()
          .noOcclusion()
          .isSuffocating { s, b, p -> false }
          .isViewBlocking { s, b, p -> false }
          .isRedstoneConductor { s, b, p -> false }
          .ignitedByLava()
      }
      .loot(BlockLootPresets.leavesLoot { sapling.get() })
      .blockTags(
        listOf(BlockTags.LEAVES, LibTags.forgeBlockTag("leaves"), BlockTags.MINEABLE_WITH_HOE)
      )
      .blockstate(BlockstatePresets.leavesBlock(_name + "_leaves"))
      .noItem()
      .register()
  }

  fun createCropLeavesBlock(
    _name: String,
    color: MapColor,
    dropItem: Supplier<Item>,
    saplingBlock: Supplier<GenericSaplingBlock>
  ): BlockEntry<CropLeavesBlock> {
    return BLOCKS
      .create<CropLeavesBlock>("budding_" + _name + "_leaves")
      .blockFactory { p -> CropLeavesBlock(p) { dropItem.get() } }
      .copyFrom { Blocks.OAK_LEAVES }
      .properties { p ->
        p.mapColor(color)
          .strength(0.2f)
          .randomTicks()
          .noOcclusion()
          .sound(SoundType.AZALEA_LEAVES)
          .isSuffocating { s, b, p -> false }
          .isViewBlocking { s, b, p -> false }
          .isRedstoneConductor { s, b, p -> false }
          .ignitedByLava()
      }
      .blockTags(
        listOf(BlockTags.LEAVES, LibTags.forgeBlockTag("leaves"), BlockTags.MINEABLE_WITH_HOE)
      )
      .loot(
        CustomBlockLootPresets.dropLeafCropLoot(
          { dropItem.get() },
          { saplingBlock.get().asItem() }
        )
      )
      .transform { t ->
        t.blockstate { c, p ->
          p.getVariantBuilder(c.get()).forAllStates { state ->
            val age: Int = state.getValue(CropLeavesBlock.AGE)
            val suffix = if (age > 0) "_stage$age" else ""
            ConfiguredModel.builder()
              .modelFile(
                p.models()
                  .withExistingParent(c.name + suffix, p.mcLoc("block/leaves"))
                  .texture("all", p.modLoc("block/${_name}_leaves${suffix}"))
                  .renderType("cutout_mipped")
              )
              .build()
          }
        }
      }
      .noItem()
      .register()
  }

}