package oldcode.legacy.common;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

import oldcode.legacy.network.*;
import mortvana.projectfluxgear.util.block.BlockFluxGear;

public class FluxGearAddons {

	public static final String RESOURCESPREFIX = "projectfluxgear:";

	public static final String networkChannelName = "FluxGearAddons";
	public static SimpleNetworkWrapper network;
	public void initialize() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("FluxGearAddons");
		network.registerMessage(ParticleGenPacketHandler.class, ParticleGenPacket.class, 1, Side.SERVER);
		network.registerMessage(ObjectPacketHandler.class, ObjectPacket.class, 6, Side.CLIENT);

		//thing = new Thing();
	}

	public static BlockFluxGear invisibleMultiblock;
	public static BlockFluxGear particleGenerator;
	public static BlockFluxGear energyPylon;
	public static BlockFluxGear energyStorageCore;

	/*public static void register(BlockFluxGear block) {
		String name = block.getUnwrappedUnlocalizedName(block.func_149739_a());
		GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
	}*/

/*
* ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyStorageCore.class, new RenderTileEnergyStorageCore());
* ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyPylon.class, new RenderTileEnergyPylon());
* MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.particleGenerator), new RenderParticleGen());
*
*
* GameRegistry.registerTileEntity(TileEnergyStorageCore.class, References.RESOURCESPREFIX + "TileEnergyStorageCore");
* GameRegistry.registerTileEntity(TileInvisibleMultiblock.class, References.RESOURCESPREFIX + "TileInvisibleMultiblock");
* GameRegistry.registerTileEntity(TileEnergyPylon.class, References.RESOURCESPREFIX + "TileEnergyPylon");
* GameRegistry.registerTileEntity(TileParticleGenerator.class, References.RESOURCESPREFIX + "TileParticleGenerator");
*
* add((Block)ModBlocks.particleGenerator, new Object[]{"RBR", "BCB", "RBR", Character.valueOf('R'), Blocks.redstone_block, Character.valueOf('B'), Items.blaze_rod, Character.valueOf('C'), ModItems.draconicCore});
* add((Block)ModBlocks.energyStorageCore, new Object[]{"CCC", "SMS", "CCC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), ModItems.wyvernEnergyCore, Character.valueOf('M'), ModItems.wyvernCore});
* addOre((ItemStack)(new ItemStack(ModBlocks.energyPylon, 2)), new Object[]{"IEI", "MCM", "IDI", Character.valueOf('I'), ModItems.draconiumIngot, Character.valueOf('E'), Items.ender_eye, Character.valueOf('C'), ModItems.draconicCore, Character.valueOf('D'), "gemDiamond", Character.valueOf('M'), "gemEmerald"});
* addOre((Item)ModItems.draconicCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), "ingotGold", Character.valueOf('S'), ModItems.draconiumIngot, Character.valueOf('M'), "gemDiamond"});
* add((Item)ModItems.wyvernCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), ModItems.draconicCore, Character.valueOf('M'), Items.nether_star});
* add((ItemStack)ModItems.wyvernEnergyCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), Blocks.redstone_block, Character.valueOf('M'), ModItems.draconicCore});
*/

}