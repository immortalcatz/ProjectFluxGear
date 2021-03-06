package mortvana.legacy.clean.core.common;

import java.util.Random;

import mortvana.legacy.clean.core.common.FluxGearConfig;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.melteddashboard.intermod.tinkers.TinkersHelper;
import tconstruct.library.event.ToolCraftEvent;

public class FluxGearEventHandler implements IWorldGenerator {

	public static final int MANASTEEL_ID = 145;
	public static final int TERRASTEEL_ID = 146;

	@SubscribeEvent
	public void onToolCraft(ToolCraftEvent.NormalTool event) {
		NBTTagCompound toolNBT = event.toolTag.getCompoundTag("InfiTool");

		// Modifiers
		int modifiers = toolNBT.getInteger("Modifiers");
		modifiers += TinkersHelper.getPartCount(toolNBT, FluxGearConfig.tinkersID_Enderium);
		modifiers += TinkersHelper.getPartCount(toolNBT, FluxGearConfig.tinkersID_Lumium);
		modifiers += TinkersHelper.getPartCount(toolNBT, FluxGearConfig.tinkersID_Signalum) * 2;

		if (LoadedHelper.isBotaniaLoaded && LoadedHelper.isExtraTiCLoaded && FluxGearConfig.modifyTinkersBotania) {
			modifiers += TinkersHelper.getPartCount(toolNBT, MANASTEEL_ID);
			if (TinkersHelper.getPartCount(toolNBT, TERRASTEEL_ID) > 0) {
				modifiers += 3;
			}
		}

		toolNBT.setInteger("Modifiers", modifiers);
	}

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId) {
            case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }
    }
      
	private void generateSurface(World world, Random random, int x, int z) {
           
	}

	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 *
	 * @param block - The Block to spawn
	 * @param world - The World to spawn in
	 * @param random - A Random object for retrieving random positions within the world to spawn the Block
	 * @param blockXPos - An int for passing the X-Coordinate for the Generation method
	 * @param blockZPos - An int for passing the Z-Coordinate for the Generation method
	 * @param maxX - An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param maxZ - An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param maxVeinSize - An int for setting the maximum size of a vein
	 * @param chancesToSpawn - An int for the Number of chances available for the Block to spawn per-chunk
	 * @param minY - An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param maxY - An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
	       int maxPossY = minY + (maxY - 1);
	       assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
	       assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
	       assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
	       assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
	       assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

	       int diffBtwnMinMaxY = maxY - minY;
	       for(int x = 0; x < chancesToSpawn; x++) {
	             int posX = blockXPos + random.nextInt(maxX);
	             int posY = minY + random.nextInt(diffBtwnMinMaxY);
	             int posZ = blockZPos + random.nextInt(maxZ);
	             (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
	       }
	}
}