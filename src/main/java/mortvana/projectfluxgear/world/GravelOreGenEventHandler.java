package mortvana.projectfluxgear.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

import mortvana.fluxgearcore.util.world.SurfaceOreGen;

import mortvana.projectfluxgear.common.FluxGearContent;
import mortvana.projectfluxgear.common.config.FluxGearConfig;
import mortvana.projectfluxgear.common.config.FluxGearConfigWorld;

public class GravelOreGenEventHandler {

	//public final SurfaceOreGen chalcociteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 0, FluxGearConfigWorld.chalcociteGravelAmount, true);

	@SubscribeEvent
	public void onDecorationEvent(Decorate event) {
		if (event.type != Decorate.EventType.SAND)
			return;

		BiomeGenBase biome = event.world.getWorldChunkManager().getBiomeGenAt(event.chunkX, event.chunkZ);
		int iterations = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN) ? 2 : 1;
		for (int i = 0; i < iterations; i++) {
			generateGravelOres(event.rand, event.chunkX, event.chunkZ, event.world);
		}
	}

	public void generateGravelOres(Random random, int xChunk, int zChunk, World world) {
		if (random == null)
			return;

		int xPos, yPos, zPos;
		//if (FluxGearConfig.generateChalcociteGravel && random.nextInt(FluxGearConfig.chalcociteGravelRarity) == 0) {
		//	xPos = xChunk + random.nextInt(16);
		//  yPos = 64 + FluxGearCoreConfig.seaLevel;
		//  zPos = zChunk + random.nextInt(16);
		//  chalcociteGravel.generate(world, random, xPos, yPos, zPos);
		//}
	}

}
