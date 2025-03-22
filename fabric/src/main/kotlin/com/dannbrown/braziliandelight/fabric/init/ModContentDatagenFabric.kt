package com.dannbrown.braziliandelight.fabric.init

import com.dannbrown.deltaboxlib.fabric.registrate.RegistrateDatagenFabric
import com.dannbrown.braziliandelight.init.ModContent
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.RegistrySetBuilder

class ModContentDatagenFabric : DataGeneratorEntrypoint {
  override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
    val pack = fabricDataGenerator.createPack()
    RegistrateDatagenFabric.buildDatagenResources(pack, ModContent.REGISTRATE)
  }

  override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
    RegistrateDatagenFabric.buildRegistry(registryBuilder, ModContent.REGISTRATE)
  }
}