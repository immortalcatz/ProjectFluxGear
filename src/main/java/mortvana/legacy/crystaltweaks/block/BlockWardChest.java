package mortvana.legacy.crystaltweaks.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.crystaltweaks.block.logic.TileWardChest;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BlockWardChest extends BlockModContainer {

	Random random;

	protected BlockWardChest(int par1) {
		super(par1, Material.field_76245_d);
		func_71875_q();
		func_71848_c(6000.0F);
		func_71896_v();
		func_71884_a(field_71967_e);
		func_71905_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		ThaumcraftApi.portableHoleBlackList.add(this);
		this.random = new Random();
	}

	public void func_71860_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		byte b0 = 0;
		int l1 = MathHelper.func_76128_c(par5EntityLiving.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
		if (l1 == 0) {
			b0 = 2;
		}
		if (l1 == 1) {
			b0 = 5;
		}
		if (l1 == 2) {
			b0 = 3;
		}
		if (l1 == 3) {
			b0 = 4;
		}
		par1World.func_72921_c(par2, par3, par4, b0, 2);
		TileWardChest chest = (TileWardChest) par1World.func_72796_p(par2, par3, par4);
		if (par6ItemStack.func_82837_s()) {
			chest.customName = par6ItemStack.func_82833_r();
		}
		if ((par5EntityLiving instanceof EntityPlayer)) {
			chest.owner = ((EntityPlayer) par5EntityLiving).field_71092_bJ;
		} else {
			chest.owner = "";
		}
	}

	public void func_71852_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
		TileWardChest chest = (TileWardChest) par1World.func_72796_p(par2, par3, par4);
		if (chest != null) {
			for (int j1 = 0; j1 < chest.func_70302_i_(); j1++) {
				ItemStack itemstack = chest.func_70301_a(j1);
				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;
					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; par1World.func_72838_d(entityitem)) {
						int k1 = this.random.nextInt(21) + 10;
						if (k1 > itemstack.field_77994_a) {
							k1 = itemstack.field_77994_a;
						}
						itemstack.field_77994_a -= k1;
						entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.field_77993_c, k1, itemstack.func_77960_j()));
						float f3 = 0.05F;
						entityitem.field_70159_w = ((float) this.random.nextGaussian() * f3);
						entityitem.field_70181_x = ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.field_70179_y = ((float) this.random.nextGaussian() * f3);
						if (itemstack.func_77942_o()) {
							entityitem.func_92059_d().func_77982_d((NBTTagCompound) itemstack.func_77978_p().func_74737_b());
						}
					}
				}
			}
			par1World.func_96440_m(par2, par3, par4, par5);
		}
		super.func_71852_a(par1World, par2, par3, par4, par5, par6);
	}

	public boolean func_71903_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par1World.field_72995_K) {
			return true;
		}
		TileWardChest chest = (TileWardChest) par1World.func_72796_p(par2, par3, par4);
		if (chest != null) {
			String owner = chest.owner;
			if (owner.equals(par5EntityPlayer.field_71092_bJ)) {
				if ((par5EntityPlayer.func_71045_bC() != null) && ((par5EntityPlayer.func_71045_bC().func_77973_b() instanceof ItemWandCasting))) {
					if (!par1World.field_72995_K) {
						int meta = par1World.func_72805_g(par2, par3, par4);
						func_71897_c(par1World, par2, par3, par4, meta, par6);
						par1World.func_72926_e(2001, par2, par3, par4, this.field_71990_ca + (meta << 12));
						par1World.func_72832_d(par2, par3, par4, 0, 0, 0);
					} else {
						par5EntityPlayer.func_71038_i();
					}
				} else {
					par5EntityPlayer.func_71007_a(chest);
					par1World.func_72908_a(par2, par3, par4, "thaumcraft.key", 1.0F, 1.0F);
				}
			} else {
				par5EntityPlayer.func_71035_c("The Chest refuses to budge.");
				par1World.func_72908_a(par2, par3, par4, "thaumcraft.doorfail", 1.0F, 0.2F);
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_71858_a(int par1, int par2) {
		return ConfigBlocks.blockWoodenDevice.func_71858_a(par1, 0);
	}

	public int func_71857_b() {
		return LibRenderIDs.idWardChest;
	}

	public boolean func_71926_d() {
		return false;
	}

	public boolean func_71886_c() {
		return false;
	}

	public TileEntity func_72274_a(World world) {
		return new TileWardChest();
	}
}