package mortvana.legacy.crystaltweaks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

 public abstract class BlockModContainer <T extends TileEntity> extends BlockContainer {

	protected BlockModContainer(int par1, Material par2Material) {
		super(par1, par2Material);
		if (registerInCreative()) {
			func_71849_a(ModCreativeTab.INSTANCE);
		}
	}

	@SideOnly(Side.CLIENT)
	public void func_94332_a(IIconRegister par1IconRegister) {
		this.field_94336_cN = IconHelper.forBlock(par1IconRegister, this);
	}

	boolean registerInCreative() {
		return true;
	}

	public abstract T func_72274_a(World paramWorld);
}