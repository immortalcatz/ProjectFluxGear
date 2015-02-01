package mortvana.projectfluxgear.weirdScienceLegacy;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.fluxgearcore.legacy.block.BlockContainerBase;

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
