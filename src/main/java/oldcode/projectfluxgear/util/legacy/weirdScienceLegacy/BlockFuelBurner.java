package oldcode.projectfluxgear.util.legacy.weirdScienceLegacy;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import oldcode.projectfluxgear.util.legacy.block.BlockContainerBase;

public class BlockFuelBurner extends BlockContainerBase {

    public BlockFuelBurner(String name, Material material) {
		super(name, material);
	}

	@Override
    public TileEntity createNewTileEntity (World world)
    {
        return new TileEntityFuelBurner();
    }

}