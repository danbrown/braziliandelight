import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
  java
  kotlin("jvm") version "1.8.22"
  id("architectury-plugin") version "3.4-SNAPSHOT"
  id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
  id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

architectury {
  minecraft = rootProject.property("minecraft_version").toString()
}

subprojects {
  apply(plugin = "dev.architectury.loom")

  val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")


  dependencies {
    "minecraft"("com.mojang:minecraft:${project.property("minecraft_version")}")
    // The following line declares the mojmap mappings, you may use other mappings as well
    "mappings"(
      loom.officialMojangMappings()
    )
    // The following line declares the yarn mappings you may select this one as well.
    // "mappings"("net.fabricmc:yarn:1.18.2+build.3:v2")
  }
}

allprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")
  apply(plugin = "architectury-plugin")
  apply(plugin = "maven-publish")

  base.archivesName.set(rootProject.property("mod_id").toString())
  //base.archivesBaseName = rootProject.property("mod_id").toString()
  version = rootProject.property("mod_version").toString()
  group = rootProject.property("mod_group").toString()

  repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    mavenCentral()
    maven {
      setUrl("https://maven.tterrag.com/")
    }
    maven {
      setUrl("https://maven.minecraftforge.net")
    }
    maven { setUrl("https://repo.spongepowered.org/maven") }
    // location of the maven that hosts JEI files since January 2023
    maven {
      setUrl("https://maven.blamejared.com/")
    }
    // location of a maven mirror for JEI files, as a fallback
    maven {
      setUrl("https://modmaven.dev")
    }
    // add curseforge maven for other mods
    maven {

      setUrl("https://cursemaven.com")
      content {
        includeGroup("curse.maven")
      }
    }
    // add modrinth maven for other mods
    maven {
      setUrl("https://api.modrinth.com/maven")
      content {
        includeGroup("maven.modrinth")
      }
    }
    flatDir {
      dirs("../libs")
    }
  }

  dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
  }

  tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
  }
  kotlin.target.compilations.all {
    kotlinOptions.jvmTarget = "17"
  }

  java {
    withSourcesJar()
  }
}

tasks.register<Copy>("collectJars") {
  val deps = listOf("common", "fabric", "forge").map { project(":$it").tasks.getByName("remapJar") }
  dependsOn(deps)

  val modId = rootProject.property("mod_id")
  val modVersion = rootProject.property("mod_version")
  val minecraftVersion = rootProject.property("minecraft_version")
  val jarPattern = Regex("$modId-${minecraftVersion}-(common|fabric|forge)-$modVersion\\.jar")

  from(deps.map { "${project(it.project.path).buildDir}/libs" }) {
    include { it.file.name.matches(jarPattern) }
  }
  into(rootProject.projectDir.parentFile.resolve("libs"))
}

tasks.named("assemble").configure {
  dependsOn("collectJars")
}
