import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
  id("com.github.johnrengelman.shadow")
}

architectury {
  platformSetupLoomIde()
  forge()
}

loom {
  accessWidenerPath.set(project(":common").loom.accessWidenerPath)

  forge.apply {
    convertAccessWideners.set(true)
    extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)

    mixinConfig("braziliandelight-common.mixins.json")
    mixinConfig("braziliandelight.mixins.json")
  }
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating
val developmentForge: Configuration by configurations.getting

configurations {
  compileOnly.configure { extendsFrom(common) }
  runtimeOnly.configure { extendsFrom(common) }
  developmentForge.extendsFrom(common)
}

repositories {
  mavenCentral()
  // KFF
  maven {
    name = "Kotlin for Forge"
    setUrl("https://thedarkcolour.github.io/KotlinForForge/")
  }
}

dependencies {
  forge("net.minecraftforge:forge:${rootProject.property("forge_version")}")
  // Remove the next line if you don't want to depend on the API
  modApi("dev.architectury:architectury-forge:${rootProject.property("architectury_version")}")

  common(project(":common", "namedElements")) { isTransitive = false }
  shadowCommon(project(":common", "transformProductionForge")) { isTransitive = false }

  // Kotlin For Forge
  implementation("thedarkcolour:kotlinforforge:${rootProject.property("kff_version")}")

  // DeltaboxLib
  modImplementation(
    files(
      "${rootProject.projectDir.parent}/libs/deltaboxlib-${rootProject.property("minecraft_version")}-common-${
        rootProject.property(
          "deltaboxlib_version"
        )
      }.jar"
    )
  )
  modImplementation(
    files(
      "${rootProject.projectDir.parent}/libs/deltaboxlib-${rootProject.property("minecraft_version")}-forge-${
        rootProject.property(
          "deltaboxlib_version"
        )
      }.jar"
    )
  )
  // compile against the JEI API but do not include it at runtime
  modCompileOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-common-api:${rootProject.property("jei_version")}")
  modCompileOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-forge-api:${rootProject.property("jei_version")}")
  // at runtime, use the full JEI jar for Forge
  modRuntimeOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-forge:${rootProject.property("jei_version")}")

  // Add Farmers Delight as Dependency
  modImplementation("curse.maven:farmers-delight-398521:${rootProject.property("farmersdelight_version_forge")}")
}

archivesName.set("${rootProject.property("mod_id")}-${rootProject.property("minecraft_version")}-${project.name}")

tasks.processResources {
  inputs.property("group", rootProject.property("mod_group"))
  inputs.property("version", project.version)

  filesMatching("META-INF/mods.toml") {
    expand(
      mapOf(
        "minecraft_version" to project.property("minecraft_version"),
        "minecraft_version_range" to project.property("minecraft_version_range_forge"),
        "forge_version" to project.property("forge_version"),
        "forge_version_range" to project.property("forge_version_range"),
        "kff_version_range" to project.property("kff_version_range"),
        "architectury_version" to project.property("architectury_version"),
        "architectury_version_range" to project.property("architectury_version_range_forge"),
        "fabric_kotlin_version_range" to project.property("fabric_kotlin_version_range"),
        "fabric_loader_version_range" to project.property("fabric_loader_version_range"),
        "java_version_range" to project.property("java_version_range"),
        "deltaboxlib_version" to project.property("deltaboxlib_version"),
        "deltaboxlib_version_range" to project.property("deltaboxlib_version_range_forge"),
        "farmersdelight_version_range" to project.property("farmersdelight_version_range_forge"),

        "mod_id" to project.property("mod_id"),
        "mod_name" to project.property("mod_name"),
        "mod_license" to project.property("mod_license"),
        "mod_version" to project.property("mod_version"),
        "mod_authors" to project.property("mod_authors"),
        "mod_description" to project.property("mod_description"),
        "mod_source" to project.property("mod_source"),
        "pack_format_number" to project.property("pack_format_number"),
      )
    )
  }
}

tasks.shadowJar {
  exclude("fabric.mod.json")
  exclude("architectury.common.json")
  configurations = listOf(shadowCommon)
  archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
  injectAccessWidener.set(true)
  inputFile.set(tasks.shadowJar.get().archiveFile)
  dependsOn(tasks.shadowJar)
  archiveClassifier.set(null as String?)
}

tasks.jar {
  archiveClassifier.set("dev")
}

tasks.sourcesJar {
  val commonSources = project(":common").tasks.getByName<Jar>("sourcesJar")
  dependsOn(commonSources)
  from(commonSources.archiveFile.map { zipTree(it) })
}

components.getByName("java") {
  this as AdhocComponentWithVariants
  this.withVariantsFromConfiguration(project.configurations["shadowRuntimeElements"]) {
    skip()
  }
}