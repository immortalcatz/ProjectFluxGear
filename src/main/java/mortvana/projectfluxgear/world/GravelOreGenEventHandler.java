package mortvana.projectfluxgear.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

import mortvana.fluxgearcore.common.FluxGearCore;
import mortvana.fluxgearcore.common.FluxGearCoreConfig;
import mortvana.fluxgearcore.util.world.SurfaceOreGen;

import mortvana.projectfluxgear.common.FluxGearContent;
import mortvana.projectfluxgear.common.config.FluxGearConfig;
import mortvana.projectfluxgear.common.config.FluxGearConfigWorld;

public class GravelOreGenEventHandler {

	public final SurfaceOreGen chalcociteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 0, FluxGearConfigWorld.gravelChalcociteSize, true);
	public final SurfaceOreGen cassiteriteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 1, FluxGearConfigWorld.gravelCassiteriteSize, true);
	public final SurfaceOreGen galenaGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 2, FluxGearConfigWorld.gravelGalenaSize, true);
	public final SurfaceOreGen acanthiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 3, FluxGearConfigWorld.gravelAcanthiteSize, true);
	public final SurfaceOreGen garnieriteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 4, FluxGearConfigWorld.gravelGarnieriteSize, true);
	public final SurfaceOreGen sphaleriteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 5, FluxGearConfigWorld.gravelSphaleriteSize, true);
	public final SurfaceOreGen bismuthiniteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 6, FluxGearConfigWorld.gravelBismuthiniteSize, true);
	public final SurfaceOreGen pyrolusiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 7, FluxGearConfigWorld.gravelPyrolusiteSize, true);
	public final SurfaceOreGen bauxiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 8, FluxGearConfigWorld.gravelBauxiteSize, true);
	public final SurfaceOreGen cooperiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 9, FluxGearConfigWorld.gravelCooperiteSize, true);
	public final SurfaceOreGen braggiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 10, FluxGearConfigWorld.gravelBraggiteSize, true);
	public final SurfaceOreGen molybdeniteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 11, FluxGearConfigWorld.gravelMolybdeniteSize, true);
	public final SurfaceOreGen cobaltiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 12, FluxGearConfigWorld.gravelCobaltiteSize, true);
	public final SurfaceOreGen wolframiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 13, FluxGearConfigWorld.gravelWolframiteSize, true);
	public final SurfaceOreGen ilmeniteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 14, FluxGearConfigWorld.gravelIlmeniteSize, true);
	public final SurfaceOreGen chromiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreMain, 15, FluxGearConfigWorld.gravelChromiteSize, true);

	public final SurfaceOreGen cinnabarGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 0, FluxGearConfigWorld.gravelCinnabarSize, true);
	public final SurfaceOreGen pitchblendeGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 1, FluxGearConfigWorld.gravelPitchblendeSize, true);
	public final SurfaceOreGen monaziteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 2, FluxGearConfigWorld.gravelMonaziteSize, true);
	public final SurfaceOreGen niedermayriteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 3, FluxGearConfigWorld.gravelNiedermayriteSize, true);
	public final SurfaceOreGen greenockiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 4, FluxGearConfigWorld.gravelGreenockiteSize, true);
	public final SurfaceOreGen gaotaiiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 5, FluxGearConfigWorld.gravelGaotaiiteSize, true);
	public final SurfaceOreGen osarsiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 6, FluxGearConfigWorld.gravelOsarsiteSize, true);
	public final SurfaceOreGen znamenskyiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 7, FluxGearConfigWorld.gravelZnamenskyiteSize, true);
	public final SurfaceOreGen gallobeudaniteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 8, FluxGearConfigWorld.gravelGallobeudaniteSize, true);
	public final SurfaceOreGen tetrahedriteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 9, FluxGearConfigWorld.gravelTetrahedriteSize, true);
	public final SurfaceOreGen tennantiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 10, FluxGearConfigWorld.gravelTennantiteSize, true);
	public final SurfaceOreGen santafeiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 11, FluxGearConfigWorld.gravelSantafeiteSize, true);
	public final SurfaceOreGen magnetiteGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 12, FluxGearConfigWorld.gravelMagnetiteSize, true);
	public final SurfaceOreGen dioptaseGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 13, FluxGearConfigWorld.gravelDioptaseSize, true);
	public final SurfaceOreGen pyropeGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 14, FluxGearConfigWorld.gravelPyropeSize, true);
	public final SurfaceOreGen myuvilGravel = new SurfaceOreGen(FluxGearContent.blockGravelOreAux, 15, FluxGearConfigWorld.gravelMyuvilSize, true);
	
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
		if ((FluxGearConfigWorld.generateGravelChalcocite == 0 || (FluxGearConfigWorld.generateGravelChalcocite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelChalcociteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
		    yPos = 64 + FluxGearCoreConfig.seaLevel;
		    zPos = zChunk + random.nextInt(16);
		    chalcociteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelCassiterite == 0 || (FluxGearConfigWorld.generateGravelCassiterite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelCassiteriteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			cassiteriteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelGalena == 0 || (FluxGearConfigWorld.generateGravelGalena == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelGalenaRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			galenaGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelAcanthite == 0 || (FluxGearConfigWorld.generateGravelAcanthite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelAcanthiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			acanthiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelGarnierite == 0 || (FluxGearConfigWorld.generateGravelGarnierite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelGarnieriteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			garnieriteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelSphalerite == 0 || (FluxGearConfigWorld.generateGravelSphalerite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelSphaleriteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			sphaleriteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelBismuthinite == 0 || (FluxGearConfigWorld.generateGravelBismuthinite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelBismuthiniteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			bismuthiniteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelPyrolusite == 0 || (FluxGearConfigWorld.generateGravelPyrolusite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelPyrolusiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			pyrolusiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelBauxite == 0 || (FluxGearConfigWorld.generateGravelBauxite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelBauxiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			bauxiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelCooperite == 0 || (FluxGearConfigWorld.generateGravelCooperite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelCooperiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			cooperiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelBraggite == 0 || (FluxGearConfigWorld.generateGravelBraggite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelBraggiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			braggiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelMolybdenite == 0 || (FluxGearConfigWorld.generateGravelMolybdenite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelMolybdeniteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			molybdeniteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelCobaltite == 0 || (FluxGearConfigWorld.generateGravelCobaltite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelCobaltiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			cobaltiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelWolframite == 0 || (FluxGearConfigWorld.generateGravelWolframite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelWolframiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			wolframiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelIlmenite == 0 || (FluxGearConfigWorld.generateGravelIlmenite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelIlmeniteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			ilmeniteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelChromite == 0 || (FluxGearConfigWorld.generateGravelChromite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelChromiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			chromiteGravel.generate(world, random, xPos, yPos, zPos);
		}

		if ((FluxGearConfigWorld.generateGravelCinnabar == 0 || (FluxGearConfigWorld.generateGravelCinnabar == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelCinnabarRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			cinnabarGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelPitchblende == 0 || (FluxGearConfigWorld.generateGravelPitchblende == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelPitchblendeRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			pitchblendeGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelMonazite == 0 || (FluxGearConfigWorld.generateGravelMonazite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelMonaziteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			monaziteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelNiedermayrite == 0 || (FluxGearConfigWorld.generateGravelNiedermayrite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelNiedermayriteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			niedermayriteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelGreenockite == 0 || (FluxGearConfigWorld.generateGravelGreenockite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelGreenockiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			greenockiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelGaotaiite == 0 || (FluxGearConfigWorld.generateGravelGaotaiite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelGaotaiiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			gaotaiiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelOsarsite == 0 || (FluxGearConfigWorld.generateGravelOsarsite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelOsarsiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			osarsiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelZnamenskyite == 0 || (FluxGearConfigWorld.generateGravelZnamenskyite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelZnamenskyiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			znamenskyiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelGallobeudanite == 0 || (FluxGearConfigWorld.generateGravelGallobeudanite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelGallobeudaniteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			gallobeudaniteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelTetrahedrite == 0 || (FluxGearConfigWorld.generateGravelTetrahedrite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelTetrahedriteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			tetrahedriteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelTennantite == 0 || (FluxGearConfigWorld.generateGravelTennantite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelTennantiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			tennantiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelSantafeite == 0 || (FluxGearConfigWorld.generateGravelSantafeite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelSantafeiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			santafeiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelMagnetite == 0 || (FluxGearConfigWorld.generateGravelMagnetite == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelMagnetiteRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			magnetiteGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelDioptase == 0 || (FluxGearConfigWorld.generateGravelDioptase == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelDioptaseRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			dioptaseGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelPyrope == 0 || (FluxGearConfigWorld.generateGravelPyrope == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelPyropeRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			pyropeGravel.generate(world, random, xPos, yPos, zPos);
		}
		if ((FluxGearConfigWorld.generateGravelMyuvil == 0 || (FluxGearConfigWorld.generateGravelMyuvil == 2 && FluxGearCore.isTinkersLoaded)) && random.nextInt(FluxGearConfigWorld.gravelMyuvilRarity) == 0) {
			xPos = xChunk + random.nextInt(16);
			yPos = 64 + FluxGearCoreConfig.seaLevel;
			zPos = zChunk + random.nextInt(16);
			myuvilGravel.generate(world, random, xPos, yPos, zPos);
		}
	}
}
