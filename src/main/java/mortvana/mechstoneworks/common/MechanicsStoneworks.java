package mortvana.mechstoneworks.common;

import java.io.File;
import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import mortvana.fluxgearcore.common.FluxGearCore;
import mortvana.fluxgearcore.gui.FluxGearTab;

import mortvana.mechstoneworks.client.ClientProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.Detailing;
import tconstruct.library.tools.ToolCore;*/

@Mod(modid = MechanicsStoneworks.modID, name = "Mechanic's Stoneworks", version = MechanicsStoneworks.version, dependencies = MechanicsStoneworks.dependencies)
public class MechanicsStoneworks {

	public static final String modID = "MechanicsStoneworks";
	public static final String version = "v2.0.0.0";
	public static final String dependencies = "required-after:FluxGearCore; after:Artifice; after:ProjRed|Exploration; after:StevesFactoryManager; after:appliedenergistics2; after:ExtraUtilities; after:OpenBlocks; after:magicalcrops; after:JABBA; after:BigReactors; after:Mekanism; after:ThermalExpansion";

	@Instance(modID)
	public static MechanicsStoneworks instance;

	@SidedProxy(serverSide = "mortvana.mechstoneworks.common.CommonProxy", clientSide = "mortvana.mechstoneworks.client.ClientProxy")
	public static CommonProxy proxy;

	public static final String[] dyeTypes = new String[] { "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
	public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red", "black" };


	public static MechStoneworksContent content;

	public static final Logger logger = LogManager.getLogger(modID);
	//public static CreativeTabs generalTab = new FluxGearTab("MechStoneworks-General", "mechanicsStoneworks.generalTab", new ItemStack(Items.potato));
	//public static CreativeTabs paintedStoneTab = new FluxGearTab("MechStoneworks-PaintedStone", "mechanicsStoneworks.paintedStoneTab", new ItemStack(MechStoneworksContent.coloredStone/*, 1, 13*/));
	//public static CreativeTabs stonesTab = new FluxGearTab("MechStoneworks-Stone", "mechanicsStoneworks.stoneTab", new ItemStack(Blocks.obsidian/*MechStoneworksContent.coloredStone, 1, 10*/));
	//public static CreativeTabs stairSlabTab = new FluxGearTab("", "", null);

	public MechanicsStoneworks() {
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MechStoneworksConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/MechanicsStoneworks.cfg"));
		content = new MechStoneworksContent();
		content.loadStuff();

		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ClientProxy.registerRenderPassing();

		//TODO: mDerpohouse!!!!!!!!!!!!!!
		/*if(FluxGearCore.isTinkersLoaded) {
			ToolCore chisel = (ToolCore) GameRegistry.findItem("TConstruct", "chisel");
			Detailing chiseling = TConstructRegistry.getChiselDetailing();

			for(int i = 0; i < 16; ++i) {
				chiseling.addDetailing(content.coloredStone, i, content.coloredStoneBrick, i, chisel);
				chiseling.addDetailing(content.coloredStoneBrick, i, content.coloredStoneRoad, i, chisel);
				chiseling.addDetailing(content.coloredStoneRoad, i, content.coloredStoneFancyBrick, i, chisel);
				chiseling.addDetailing(content.coloredStoneFancyBrick, i, content.coloredStoneSquareBrick, i, chisel);
			}
		}*/
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MechStoneworksTweaks.removal();
		MechStoneworksTweaks.addition();
	}

	public int colorStoneBlocks(World world, int x, int y, int z, int inputMeta, int range, int maxBlocks) {
		boolean changed = false;
		int amount = 0;

		for(int xPos = -range; xPos <= range && amount <= maxBlocks; ++xPos) {
			for(int yPos = -range; yPos <= range && amount <= maxBlocks; ++yPos) {
				for(int zPos = -range; zPos <= range && amount <= maxBlocks; ++zPos) {
					Block block = world.getBlock(x + xPos, y + yPos, z + zPos);
					if(block == Blocks.stone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStone, inputMeta, 3);
					} else if(block == Blocks.cobblestone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCobble, inputMeta, 3);
					} else if(block == Blocks.mossy_cobblestone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossCobble, inputMeta, 3);
					} else {
						int meta;
						if(block == Blocks.stonebrick) {
							++amount;
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 0) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneBrick, inputMeta, 3);
							} else if(meta == 1) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossStoneBrick, inputMeta, 3);
							} else if(meta == 2) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCrackedStoneBrick, inputMeta, 3);
							} else if(meta == 3) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneSquareBrick, inputMeta, 3);
							}
						} else if(block == Blocks.wool) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, Blocks.wool, inputMeta, 3);
						} else if(block == Blocks.brick_block) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, content.clayBrickSmall, inputMeta, 3);
						} else if(FluxGearCore.isTinkersLoaded && block == GameRegistry.findBlock("TConstruct", "decoration.multibrickfancy")) {
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 14) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneFancyBrick, inputMeta, 3);
							} else if(meta == 15) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneRoad, inputMeta, 3);
							}
						}
					}
				}
			}
		}

		return amount;
	}
}
