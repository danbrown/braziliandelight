import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
  id("com.github.johnrengelman.shadow")
}

architectury {
  platformSetupLoomIde()
  fabric()
}

loom {
  accessWidenerPath.set(project(":common").loom.accessWidenerPath)

  val common = project(":common")

  runs {
    create("datagen") {
      client()

      name("Minecraft Data")
      vmArg("-Dfabric-api.datagen")
      vmArg("-Dfabric-api.datagen.output-dir=${common.file("src/generated/resources")}")
      vmArg("-Dfabric-api.datagen.modid=${project.property("mod_id")}")
      vmArg("-Dporting_lib.datagen.existing_resources=${common.file("src/main/resources")}")

      environmentVariable("DATAGEN", "TRUE")
    }
  }
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating
val developmentFabric: Configuration by configurations.getting

configurations {
  compileOnly.configure { extendsFrom(common) }
  runtimeOnly.configure { extendsFrom(common) }
  developmentFabric.extendsFrom(common)
}

repositories {
  mavenCentral()
  maven {
    setUrl("https://maven.quiltmc.org/repository/release/")
  }
  maven {
    name = "Greenhouse Maven"
    setUrl("https://repo.greenhouse.house/releases/")
  }
//  maven { url "https://mvn.devos.one/releases/" } // Porting Lib
  maven {
    name = "Greenhouse Maven (Snapshots)" // Temporary Porting Lib Fork for 1.21
    setUrl("https://repo.greenhouse.house/snapshots/")
  }
}

dependencies {
  modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}")
  modApi("net.fabricmc.fabric-api:fabric-api:${rootProject.property("fabric_api_version")}")
  // Remove the next line if you don't want to depend on the API
  modApi("dev.architectury:architectury-fabric:${rootProject.property("architectury_version")}")

  common(project(":common", "namedElements")) {
    isTransitive = false
  }
  shadowCommon(project(":common", "transformProductionFabric")) {
    isTransitive = false
  }

  // Fabric Kotlin
  modImplementation("net.fabricmc:fabric-language-kotlin:${rootProject.property("fabric_kotlin_version")}")

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
      "${rootProject.projectDir.parent}/libs/deltaboxlib-${rootProject.property("minecraft_version")}-fabric-${
        rootProject.property(
          "deltaboxlib_version"
        )
      }.jar"
    )
  )
  // compile against the JEI API but do not include it at runtime
  modCompileOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-common-api:${rootProject.property("jei_version")}")
  modCompileOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-fabric-api:${rootProject.property("jei_version")}")
  // at runtime, use the full JEI jar for Fabric
  modRuntimeOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-fabric:${rootProject.property("jei_version")}")

  // Add Farmers Delight as Dependency
  modImplementation("curse.maven:farmers-delight-refabricated-993166:${rootProject.property("farmersdelight_version_fabric")}")

  // Porting Lib
  for (module in rootProject.property("porting_lib_modules").toString().split(",")) {
    modApi("io.github.fabricators_of_create.Porting-Lib:$module:${rootProject.property("porting_lib_version")}")
    include("io.github.fabricators_of_create.Porting-Lib:$module:${rootProject.property("porting_lib_version")}")
  }
}

archivesName.set("${rootProject.property("mod_id")}-${rootProject.property("minecraft_version")}-${project.name}")

tasks.processResources {
  inputs.property("group", rootProject.property("mod_group"))
  inputs.property("version", project.version)

  filesMatching("fabric.mod.json") {
    expand(
      mapOf(
        "minecraft_version" to project.property("minecraft_version"),
        "minecraft_version_range" to project.property("minecraft_version_range_fabric"),
        "forge_version" to project.property("forge_version"),
        "forge_version_range" to project.property("forge_version_range"),
        "kff_version_range" to project.property("kff_version_range"),
        "architectury_version" to project.property("architectury_version"),
        "architectury_version_range" to project.property("architectury_version_range_fabric"),
        "fabric_kotlin_version_range" to project.property("fabric_kotlin_version_range"),
        "fabric_loader_version_range" to project.property("fabric_loader_version_range"),
        "java_version_range" to project.property("java_version_range"),
        "deltaboxlib_version" to project.property("deltaboxlib_version"),
        "deltaboxlib_version_range" to project.property("deltaboxlib_version_range_fabric"),

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