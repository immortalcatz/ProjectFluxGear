package oldcode.projectfluxgear.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.unrefactored.trevelations.common.TRevelations;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

public class BlockWitor extends BlockContainer {

	public BlockWitor() {

		super(Config.airyMaterial);
		setBlockName("blockWitor");
		setStepSound(Block.soundTypeCloth);
		setCreativeTab(TRevelations.tabTRevelations);
		setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);

	}



	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public int getLightValue() {
		return 15;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileWitor();
	}

	public static class TileWitor extends TileEntity {

		public boolean canUpdate() {return true;}

		public void updateEntity() {

			super.updateEntity();

			if (this.worldObj.isRemote) {

				if (this.worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
					Thaumcraft.proxy.wispFX3(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, this.yCoord + 0.5F, this.zCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, 0.5F, 0, true, -0.025F);
				}

				if (this.worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
					Thaumcraft.proxy.wispFX3(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, this.yCoord + 0.5F, this.zCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, 0.25F, 2, true, -0.02F);
				}

			}

		}

	}

}
