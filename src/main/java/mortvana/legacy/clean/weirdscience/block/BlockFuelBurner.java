package mortvana.legacy.clean.weirdscience.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.melteddashboard.block.FluxGearBlockTileEntity;

import mortvana.legacy.clean.weirdscience.util.block.tile.TileEntityFuelBurner;

public class BlockFuelBurner extends FluxGearBlockTileEntity {

	public BlockFuelBurner(Material material, String name) {
		super(material, name);
	}

	@Override
	public TileEntity createNewTileEntity (World world, int metadata)
	{
		return new TileEntityFuelBurner();
	}

}
