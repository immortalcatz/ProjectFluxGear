package oldcode.projectfluxgear;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidBlock extends BlockFluidClassic {
	public IIcon stillIcon;
	public IIcon flowingIcon;
	public final Fluid definedFluid;

	public FluidBlock(Fluid fluid, Material material) {
		super(fluid, material);
		definedFluid = fluid;
	}

	public void registerBlockIcons (IIconRegister iconRegister) {
		stillIcon = iconRegister.registerIcon("fluxgear:grayscale_fluid");
		flowingIcon = iconRegister.registerIcon("fluxgear:grayscale_fluid_flowing");

		this.getFluid().setIcons(stillIcon, flowingIcon);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int side, int meta) {
		if (side == 0 || side == 1) {
			return stillIcon;
		} else {
			return flowingIcon;
		}
	}

	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return definedFluid.getColor();
	}

	public int getRenderColor(int randomInt) {
		return definedFluid.getColor();
	}
}
