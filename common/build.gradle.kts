import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

architectury {
  common(rootProject.property("enabled_platforms").toString().split(","))
}

loom {
  accessWidenerPath.set(file("src/main/resources/${rootProject.property("mod_id")}.accesswidener"))
}

sourceSets.main.get().resources.srcDir("src/generated/resources")
archivesName.set("${rootProject.property("mod_id")}-${rootProject.property("minecraft_version")}-${project.name}")

dependencies {
  // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
  // Do NOT use other classes from fabric loader
  modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}")
  modApi("net.fabricmc.fabric-api:fabric-api:${rootProject.property("fabric_api_version")}")
  // Remove the next line if you don"t want to depend on the API
  modApi("dev.architectury:architectury:${rootProject.property("architectury_version")}")

  // DeltaboxLib
  modApi(
    files(
      "${rootProject.projectDir.parent}/libs/deltaboxlib-${rootProject.property("minecraft_version")}-common-${
        rootProject.property(
          "deltaboxlib_version"
        )
      }.jar"
    )
  )
  // compile against the JEI API but do not include it at runtime
  modCompileOnly("mezz.jei:jei-${rootProject.property("minecraft_version")}-common-api:${rootProject.property("jei_version")}")

  // Add Farmers Delight as Dependency
//  modApi("curse.maven:farmersdelight-482834:${rootProject.property("farmersdelight_version_fabric")}")
  modImplementation("curse.maven:farmers-delight-refabricated-993166:${rootProject.property("farmersdelight_version_fabric")}")
}
