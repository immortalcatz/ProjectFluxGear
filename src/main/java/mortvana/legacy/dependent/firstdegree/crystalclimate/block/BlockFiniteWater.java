package mortvana.legacy.dependent.firstdegree.crystalclimate.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

import mortvana.legacy.errored.crystalclimate.common.CrystalClimate;

public class BlockFiniteWater extends BlockFluidFinite {
	public BlockFiniteWater(Fluid fluid, Material material) {
		super(fluid, material);
		this.setCreativeTab(CrystalClimate.tab);
	}

	public void registerIcons(IIconRegister par1IconRegister) {
	}

	public IIcon getIcon(int side, int meta) {
		return Blocks.water.getIcon(side, meta);
	}
}