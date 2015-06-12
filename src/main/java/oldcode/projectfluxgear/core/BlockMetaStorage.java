package oldcode.projectfluxgear.core;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

import mortvana.projectfluxgear.util.block.BlockContainerFluxGear;
import mortvana.projectfluxgear.util.block.EnumBlockType;

public class BlockMetaStorage extends BlockContainerFluxGear {
	public BlockMetaStorage(CreativeTabs tab, EnumBlockType type, TileEntity tile) {
		super(Material.iron, tab, type, tile);
	}
}
