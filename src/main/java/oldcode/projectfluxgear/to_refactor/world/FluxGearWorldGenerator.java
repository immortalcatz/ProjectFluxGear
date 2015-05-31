package oldcode.projectfluxgear.to_refactor.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

import mortvana.projectfluxgear.to_refactor.FluxGearContent_;

public class FluxGearWorldGenerator implements IWorldGenerator{

	public FluxGearWorldGenerator() {
		chalcocite = new WorldGenMinable(FluxGearContent_.blockOreMain, 0, 10, Blocks.stone);
		cassiterite = new WorldGenMinable(FluxGearContent_.blockOreMain, 1, 9, Blocks.stone);
		//TODO: Make Galena (55%) and Acanthite (45%) entwined (Total 17)
		galena = new WorldGenMinable(FluxGearContent_.blockOreMain, 2, 8, Blocks.stone);
		acanthite = new WorldGenMinable(FluxGearContent_.blockOreMain, 3, 7, Blocks.stone);
		garnierite = new WorldGenMinable(FluxGearContent_.blockOreMain, 4, 7, Blocks.stone);
		sphalerite = new WorldGenMinable(FluxGearContent_.blockOreMain, 5, 9, Blocks.stone);
		//TODO: Entwined w/ Galena (30%) and Acanthite (5%) (Total 12)
		bismuthinite = new WorldGenMinable(FluxGearContent_.blockOreMain, 6, 9, Blocks.stone);
		pyrolusite = new WorldGenMinable(FluxGearContent_.blockOreMain, 7, 9, Blocks.stone);
		bauxite = new WorldGenMinable(FluxGearContent_.blockOreMain, 8, 24, Blocks.stone);
		//TODO: Halve when w/ Braggite (1)
		cooperite = new WorldGenMinable(FluxGearContent_.blockOreMain, 9, 2, Blocks.stone);
		//TODO: Entwined w/ Garnierite (20%) and Cooperite (10%) (Total 18)
		braggite = new WorldGenMinable(FluxGearContent_.blockOreMain, 10, 8, Blocks.stone);
		molybdenite = new WorldGenMinable(FluxGearContent_.blockOreMain, 11, 6, Blocks.stone);
		//TODO: Entwined w/ Pyrolusite (10%) Iron (10%) Garnierite (10%) and Chromite (5%) (24 Total)
		cobaltite = new WorldGenMinable(FluxGearContent_.blockOreMain, 12, 10, Blocks.stone);
		//TODO: Spawn with Tin (Tin Wolf it was named...)
		wolframite = new WorldGenMinable(FluxGearContent_.blockOreMain, 13, 2, Blocks.stone);
		ilmenite = new WorldGenMinable(FluxGearContent_.blockOreMain, 14, 2, Blocks.stone);
		chromite = new WorldGenMinable(FluxGearContent_.blockOreMain, 15, 2, Blocks.stone);

		cinnabar = new WorldGenMinable(FluxGearContent_.blockOreAux, 0, 4, Blocks.stone);
		pitchblende = new WorldGenMinable(FluxGearContent_.blockOreAux, 1, 32, Blocks.stone);
		monazite = new WorldGenMinable(FluxGearContent_.blockOreAux, 2, 6, Blocks.stone);
		niedermayrite = new WorldGenMinable(FluxGearContent_.blockOreAux, 3, 6, Blocks.stone);
		greenockite = new WorldGenMinable(FluxGearContent_.blockOreAux, 4, 6, Blocks.stone);
		gaotaiite = new WorldGenMinable(FluxGearContent_.blockOreAux, 5, 4, Blocks.stone);
		osarsite = new WorldGenMinable(FluxGearContent_.blockOreAux, 6, 8, Blocks.stone);
		gallobeudanite = new WorldGenMinable(FluxGearContent_.blockOreAux, 7, 8, Blocks.stone);
		znamenskyite = new WorldGenMinable(FluxGearContent_.blockOreAux, 8, 8, Blocks.stone);
		tertahedrite = new WorldGenMinable(FluxGearContent_.blockOreAux, 9, 6, Blocks.stone);
		tennantite = new WorldGenMinable(FluxGearContent_.blockOreAux, 10, 6, Blocks.stone);
		santafeite = new WorldGenMinable(FluxGearContent_.blockOreAux, 11, 9, Blocks.stone);
		magnetite = new WorldGenMinable(FluxGearContent_.blockOreAux, 12, 8, Blocks.stone);
		dioptase = new WorldGenMinable(FluxGearContent_.blockOreAux, 13, 4, Blocks.stone);
		pyrope = new WorldGenMinable(FluxGearContent_.blockOreAux, 14, 4, Blocks.netherrack);
		myuvil = new WorldGenMinable(FluxGearContent_.blockOreAux, 15, 2, Blocks.stone);
		//iridiumSands = new WorldGenDeposits(FluxGearContent.blockEarthen, 0, 1, Blocks.stone);

	}



	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER)) {
			generateNether(random, chunkX * 16, chunkZ * 16, world);
		} else if (!(BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.END) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER))) {
			generateSurface(random, chunkX * 16, chunkZ * 16, world);
		}

	}

	public void generateSurface(Random random, int xChunk, int zChunk, World world) {

	}

	public void generateNether(Random random, int xChunk, int zChunk, World world) {

	}

	WorldGenMinable chalcocite;
	WorldGenMinable cassiterite;
	WorldGenMinable galena;
	WorldGenMinable acanthite;
	WorldGenMinable garnierite;
	WorldGenMinable sphalerite;
	WorldGenMinable bismuthinite;
	WorldGenMinable pyrolusite;
	WorldGenMinable bauxite;
	WorldGenMinable cooperite;
	WorldGenMinable braggite;
	WorldGenMinable molybdenite;
	WorldGenMinable cobaltite;
	WorldGenMinable wolframite;
	WorldGenMinable ilmenite;
	WorldGenMinable chromite;
	WorldGenMinable cinnabar;
	WorldGenMinable pitchblende;
	WorldGenMinable monazite;
	WorldGenMinable niedermayrite;
	WorldGenMinable greenockite;
	WorldGenMinable gaotaiite;
	WorldGenMinable osarsite;
	WorldGenMinable gallobeudanite;
	WorldGenMinable znamenskyite;
	WorldGenMinable tertahedrite;
	WorldGenMinable tennantite;
	WorldGenMinable santafeite;
	WorldGenMinable magnetite;
	WorldGenMinable dioptase;
	WorldGenMinable pyrope;
	WorldGenMinable myuvil;
	//WorldGenDeposits iridiumSands;
}
