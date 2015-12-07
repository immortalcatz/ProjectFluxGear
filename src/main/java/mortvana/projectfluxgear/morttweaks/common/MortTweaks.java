package mortvana.projectfluxgear.morttweaks.common;

import net.minecraft.init.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import mortvana.melteddashboard.util.ConfigBase;
import mortvana.melteddashboard.util.IConfigInitialized;
import mortvana.melteddashboard.util.helpers.LoadedHelper;

import mortvana.projectfluxgear.morttweaks.world.ClayGenerator;

import static mortvana.projectfluxgear.core.config.MortTweaksConfig.*;

public class MortTweaks implements IConfigInitialized {

	@Override
	public void preInit(ConfigBase config) {}

	@Override
	public void init(ConfigBase config) {}

	@Override
	public void postInit(ConfigBase config) {
		MinecraftForge.EVENT_BUS.register(new TweakEventHandler());
		if (claySpawn) {
			GameRegistry.registerWorldGenerator(new ClayGenerator(), 10);
		}

		if (alterStackSizes) {
			alterStackSizes();
		}
	}

	public void alterStackSizes() {
		Items.wooden_door.setMaxStackSize(16);
		Items.iron_door.setMaxStackSize(16);
		Items.boat.setMaxStackSize(16);
		//Items.itemsList[Blocks.cake.blockID].setMaxStackSize(16);
		if (!LoadedHelper.isRailcraftLoaded) {
			Items.minecart.setMaxStackSize(3);
			Items.chest_minecart.setMaxStackSize(3);
			Items.furnace_minecart.setMaxStackSize(3);
			Items.hopper_minecart.setMaxStackSize(3);
			Items.tnt_minecart.setMaxStackSize(3);
			Items.command_block_minecart.setMaxStackSize(3);
		}

	}
}
