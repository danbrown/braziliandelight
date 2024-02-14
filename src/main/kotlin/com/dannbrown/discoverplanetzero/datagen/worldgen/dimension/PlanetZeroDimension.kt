package com.dannbrown.discoverplanetzero.datagen.worldgen.dimension

import net.minecraft.resources.ResourceLocation
import com.dannbrown.discover.ProjectContent
import com.dannbrown.discover.content.core.AbstractDiscoverDimension
import com.dannbrown.discover.content.core.GravityManager
import com.dannbrown.discover.datagen.planets.PlanetCodec
import com.dannbrown.discover.datagen.worldgen.DiscoverDensityFunctions
import com.dannbrown.discover.init._DiscoverBlocks
import com.dannbrown.discoverplanetzero.AddonContent
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.LushRedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.MossySlatesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RedSandArchesBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.RoseateDesertBiome
import com.dannbrown.discoverplanetzero.datagen.worldgen.biome.ScrapWastelandsBiome
import com.dannbrown.discoverplanetzero.init.AddonBlocks
import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeSource
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.biome.MultiNoiseBiomeSource
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.dimension.DimensionType
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunctions
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings
import net.minecraft.world.level.levelgen.NoiseRouter
import net.minecraft.world.level.levelgen.NoiseSettings
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.CaveSurface
import org.joml.Vector3f
import java.util.*

object PlanetZeroDimension : AbstractDiscoverDimension() {
  override val dimensionId: String = "planet_zero"
  override val LEVEL = ResourceKey.create(Registries.DIMENSION, ResourceLocation(AddonContent.MOD_ID, dimensionId))
  override val LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation(AddonContent.MOD_ID, dimensionId))
  override val DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation(AddonContent.MOD_ID, dimensionId))
  override val NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation(AddonContent.MOD_ID, dimensionId))

  // SKY DATA
  override val GRAVITY = GravityManager.DEFAULT_GRAVITY
  override val TEMPERATURE: Short = 5
  override val PRESSURE: Short = 5
  override val ATMOSPHERE = PlanetCodec.AtmosphereType.OXYGEN
  override val SKYBOX = PlanetCodec.SkyBoxProperties()
    .setHasClouds(true)
    .setCloudHeight(250f)
    .setStarsRenderer(PlanetCodec.StarsRenderer(13000, 6000, false, false))
    .setSkyObjects(listOf(
      PlanetCodec.SkyObject(
        ResourceLocation(ProjectContent.MOD_ID, "textures/sky/sun.png"),
        true,
        PlanetCodec.RenderTypeProperty.DYNAMIC,
        Vector3f(0.0f, -90.0f, 0.0f),
        30.0f
      ),
      PlanetCodec.SkyObject(
        ResourceLocation(ProjectContent.MOD_ID, "textures/sky/moon.png"),
        false,
        PlanetCodec.RenderTypeProperty.DYNAMIC,
        Vector3f(-180.0f, -90.0f, 0.0f),
        9.0f
      ),
      PlanetCodec.SkyObject(
        ResourceLocation(ProjectContent.MOD_ID, "textures/sky/light.png"),
        true,
        PlanetCodec.RenderTypeProperty.DYNAMIC,
        Vector3f(-180.0f, -90.0f, 0.0f),
        24.0f,
        PlanetCodec.ColorInstance(255, 255, 230, 255)
      ),
    ))

  // BOOTSTRAP
  override fun bootstrapType(context: BootstapContext<DimensionType>) {
    context.register(
      DIMENSION_TYPE, DimensionType(
        OptionalLong.empty(), // fixed time
        true,  // skylight
        false,  // ceiling
        false,  // ultrawarm
        true,  // natural
        1.0,  // coordinate scale
        true,  // bed works
        false,  // respawn anchor works
        0,  // Minimum Y Level
        320,  // Height + Min Y = Max Y
        320,  // Logical Height
        BlockTags.INFINIBURN_OVERWORLD,  // infiniburn
        ResourceLocation(AddonContent.MOD_ID, dimensionId),  // DimensionRenderInfo
        0.0f,  // ambient light
        DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
      )
    )
  }

  override fun bootstrapStem(context: BootstapContext<LevelStem>) {
    val biomeRegistry = context.lookup(Registries.BIOME)
    val dimTypes = context.lookup(Registries.DIMENSION_TYPE)
    val noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS)
    context.register(
      LEVEL_STEM, LevelStem(
        dimTypes.getOrThrow(DIMENSION_TYPE),
        NoiseBasedChunkGenerator(
          buildBiomeSource(biomeRegistry),
          noiseGenSettings.getOrThrow(NOISE_SETTINGS)
        )
      )
    )
  }

  override fun bootstrapNoise(context: BootstapContext<NoiseGeneratorSettings>) {
    val functions = context.lookup(Registries.DENSITY_FUNCTION)
    val noises = context.lookup(Registries.NOISE)
    val densityShiftX = DiscoverDensityFunctions.getFunction(functions, DiscoverDensityFunctions.SHIFT_X)
    val densityShiftZ = DiscoverDensityFunctions.getFunction(functions, DiscoverDensityFunctions.SHIFT_Z)
    val endBase3dNoise = DiscoverDensityFunctions.getFunction(functions, DiscoverDensityFunctions.BASE_3D_NOISE_END)

    context.register(
      NOISE_SETTINGS, NoiseGeneratorSettings(
        // Noise
        NoiseSettings.create(0, 320, 2, 1),
        // Default Block
        Blocks.STONE.defaultBlockState(),
        // Default Fluid
        Blocks.AIR.defaultBlockState(),
        // Noise Router
        NoiseRouter(
          // barrier
          DensityFunctions.zero(),
          // fluid level floodedness
          DensityFunctions.zero(),
          // fluid level spread
          DensityFunctions.zero(),
          // lava
          DensityFunctions.zero(),
          // temperature
          DensityFunctions.shiftedNoise2d(
            densityShiftX, densityShiftZ, 0.25,
            noises.getOrThrow(Noises.TEMPERATURE)
          ),
          // vegetation
          DensityFunctions.shiftedNoise2d(
            densityShiftX, densityShiftZ, 0.25,
            noises.getOrThrow(Noises.VEGETATION)
          ),
          // continents
          DensityFunctions.zero(),
          // erosion
          DensityFunctions.zero(),
          // depth
          DensityFunctions.zero(),
          // ridges
          DensityFunctions.zero(),
          // initial density
          DensityFunctions.zero(),
          // final density
          DensityFunctions.mul(
            DensityFunctions.constant(0.64),
            DensityFunctions.interpolated(
              DensityFunctions.blendDensity(
                DensityFunctions.add(
                  DensityFunctions.constant(-0.234375),
                  DensityFunctions.mul(
                    DensityFunctions.yClampedGradient(4, 32, 0.0, 1.0),
                    DensityFunctions.add(
                      DensityFunctions.constant(0.234375),
                      DensityFunctions.add(
                        DensityFunctions.constant(-23.4375),
                        DensityFunctions.mul(
                          DensityFunctions.yClampedGradient(184, 440, 1.0, 0.0),
                          DensityFunctions.add(
                            DensityFunctions.constant(23.4375),
                            endBase3dNoise
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
            .squeeze(),
          // vein toggle
          DensityFunctions.zero(),
          // vein ridged
          DensityFunctions.zero(),
          // vein gap
          DensityFunctions.zero()
        ),
        // surface rules builder
        SurfaceRules.sequence(
          SurfaceRules.sequence(
//            // stone floor
//            SurfaceRules.ifTrue(
//              SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
//              SurfaceRules.sequence()
//            ),
            // surface biomes floor
            SurfaceRules.ifTrue(
              SurfaceRules.waterStartCheck(-6, -3),
              SurfaceRules.sequence(
                // first biome
                SurfaceRules.ifTrue(
                  SurfaceRules.isBiome(RedSandArchesBiome.BIOME_KEY, LushRedSandArchesBiome.BIOME_KEY),
                  SurfaceRules.sequence(
                    // add surface gravel spots
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.hole(),
                        SurfaceRules.state(AddonBlocks.RED_SANDSTONE_PEBBLES.get()
                          .defaultBlockState())
                      )
                    ),
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                      SurfaceRules.state(Blocks.RED_SAND.defaultBlockState())
                    ),
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.state(Blocks.RED_SANDSTONE.defaultBlockState())
                    ),
                  )
                ),
                // second biome
                SurfaceRules.ifTrue(
                  SurfaceRules.isBiome(ScrapWastelandsBiome.BIOME_KEY),
                  SurfaceRules.sequence(
                    // add surface gravel spots
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.hole(),
                        SurfaceRules.state(AddonBlocks.SANDSTONE_PEBBLES.get()
                          .defaultBlockState())
                      )
                    ),
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                      SurfaceRules.state(AddonBlocks.SILT.get()
                        .defaultBlockState())
                    ),
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.state(Blocks.SANDSTONE.defaultBlockState())
                    ),
                  )
                ),
                // third biome
                SurfaceRules.ifTrue(
                  SurfaceRules.isBiome(RoseateDesertBiome.BIOME_KEY),
                  SurfaceRules.sequence(
                    // add surface gravel spots
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.hole(),
                        SurfaceRules.state(AddonBlocks.ROSEATE_SANDSTONE_PEBBLES.get()
                          .defaultBlockState())
                      )
                    ),
                    // add sand patches to the surface
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(noises.getOrThrow(Noises.POWDER_SNOW)
                          .key(), 0.3, 0.6),
                        SurfaceRules.ifTrue(
                          SurfaceRules.waterBlockCheck(0, 0),
                          SurfaceRules.state(Blocks.SAND.defaultBlockState())
                        )
                      )
                    ),
                    // roseate grains surface
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.state(AddonBlocks.ROSEATE_GRAINS.get()
                        .defaultBlockState())
                    ),
//                    // roseate sandstone
//                    SurfaceRules.ifTrue(
//                      SurfaceRules.stoneDepthCheck(0, true, 30, CaveSurface.FLOOR),
//                      SurfaceRules.state(AddonBlocks.ROSEATE_FAMILY.SANDSTONE!!.get()
//                        .defaultBlockState())
//                    ),
                  )
                ),
                // forth biome
                SurfaceRules.ifTrue(
                  SurfaceRules.isBiome(MossySlatesBiome.BIOME_KEY),
                  SurfaceRules.sequence(
                    // add surface mud spots
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.hole(),
                        SurfaceRules.state(Blocks.MUD.defaultBlockState())
                      )
                    ),
                    // add mud patches to the surface
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(noises.getOrThrow(Noises.POWDER_SNOW)
                          .key(), 0.3, 0.6),
                        SurfaceRules.ifTrue(
                          SurfaceRules.waterBlockCheck(0, 0),
                          SurfaceRules.state(Blocks.MUD.defaultBlockState())
                        )
                      )
                    ),
                    // add mud patches to the surface
//                    SurfaceRules.ifTrue(
//                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
//                      SurfaceRules.ifTrue(
//                        SurfaceRules.noiseCondition(noises.getOrThrow(Noises.POWDER_SNOW).key(), 0.2, 0.5),
//                        SurfaceRules.ifTrue(
//                          SurfaceRules.waterBlockCheck(0, 0),
//                          SurfaceRules.state(Blocks.BROWN_TERRACOTTA.defaultBlockState())
//                        )
//                      )
//                    ),
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(noises.getOrThrow(Noises.SPAGHETTI_3D_1)
                          .key(), 0.2, 0.5),
                        SurfaceRules.ifTrue(
                          SurfaceRules.waterBlockCheck(0, 0),
                          SurfaceRules.state(Blocks.BROWN_TERRACOTTA.defaultBlockState())
                        )
                      )
                    ),
                    // brown slate surface
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                      SurfaceRules.state(_DiscoverBlocks.COBBLED_BROWN_SLATE.get()
                        .defaultBlockState())
                    ),
                    // roseate sandstone
                    SurfaceRules.ifTrue(
                      SurfaceRules.stoneDepthCheck(0, true, 30, CaveSurface.FLOOR),
                      SurfaceRules.state(Blocks.GRAY_TERRACOTTA.defaultBlockState())
                    ),
                  )
                )
                // ----- biomes end
              )
            ),
          ),
          // deepslate vertical gradient
          SurfaceRules.ifTrue(
            SurfaceRules.verticalGradient(
              "minecraft:deepslate",
              VerticalAnchor.absolute(0),
              VerticalAnchor.absolute(8)
            ),
            SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState())
          )
        ),
        // spawn targets
        listOf<Climate.ParameterPoint>(),
        // sea level
        0,
        // disable mob generation
        false,
        // aquifers enabled
        false,
        // ore veins enabled
        false,
        // use legacy random source
        false
      )
    )
  }

  // build the biome source argument for the dimension type builder
  override fun buildBiomeSource(biomes: HolderGetter<Biome>): BiomeSource {
    return MultiNoiseBiomeSource.createFromList(
      Climate.ParameterList(
        ImmutableList.of(
          Pair.of(
            Climate.parameters(
              0f, // temperature
              1f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              RedSandArchesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0.9f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              LushRedSandArchesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0.8f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              ScrapWastelandsBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0.6f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              RoseateDesertBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0.4f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              MossySlatesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0.2f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              LushRedSandArchesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              0f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              ScrapWastelandsBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              -0.2f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              RoseateDesertBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              -0.4f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              MossySlatesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              -0.6f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              RedSandArchesBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              -0.8f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              ScrapWastelandsBiome.BIOME_KEY
            )
          ),
          Pair.of(
            Climate.parameters(
              0f, // temperature
              -1f, // humidity
              0f, // continentalness
              0f, // erosion
              0f,// depth
              0f, // weirdness
              0f  // offset
            ),
            biomes.getOrThrow(
              RoseateDesertBiome.BIOME_KEY
            )
          ),
        )
      )
    )
  }
}