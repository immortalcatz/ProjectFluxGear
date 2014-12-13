package mortvana.projectfluxgear.legacy.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import mortvana.fluxgearcore.legacy.block.tile.TileEntityFuelBurner;
import mortvana.fluxgearcore.legacy.block.BlockContainerBase;

public class BlockFuelBurner extends BlockContainerBase {

    public BlockFuelBurner(Configuration config, String name, Material material) {
		super(config, name, material);
	}

	@Override
    public TileEntity createNewTileEntity (World world)
    {
        return new TileEntityFuelBurner();
    }

}
