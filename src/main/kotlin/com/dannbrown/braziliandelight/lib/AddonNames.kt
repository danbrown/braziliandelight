package com.dannbrown.braziliandelight.lib

object AddonNames {

  fun getNameFromId(id: String): String {
    return id.split("_").joinToString(" ") { it.capitalize() }
  }
}