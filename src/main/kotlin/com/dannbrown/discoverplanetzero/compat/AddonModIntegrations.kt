package com.dannbrown.discoverplanetzero.compat

import com.dannbrown.databoxlib.registry.datagen.DataboxModIntegrations
import com.dannbrown.discoverplanetzero.compat.alexsmobs.AlexsMobsPlugin
import com.dannbrown.discoverplanetzero.compat.enemyexpansion.EnemyExpansionPlugin

object AddonModIntegrations: DataboxModIntegrations(listOf(
  AlexsMobsPlugin,
  EnemyExpansionPlugin,
))