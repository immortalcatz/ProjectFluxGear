package mortvana.fluxgearaddons.block;

import java.util.Random;

import mortvana.projectfluxgear.util.block.BlockFluxGear;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import oldcode.legacy.block.tileentity.TileEnergyPylon;
import oldcode.legacy.block.tileentity.TileEnergyStorageCore;
import oldcode.legacy.block.tileentity.TileInvisibleMultiblock;
import oldcode.legacy.common.FluxGearAddons;
import oldcode.legacy.util.Utils;
import mortvana.projectfluxgear.unmodulized.common.FluxGearContent;
import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class InvisibleMultiblock extends BlockFluxGear {

	public InvisibleMultiblock() {
		super(Material.iron);
		this.func_149711_c(10.0F);
		this.func_149752_b(2000.0F);
		this.func_149663_c("invisibleMultiblock");
		ModBlocks.register(this);
	}

	@SideOnly(Side.CLIENT)
	public void func_149651_a(IIconRegister iconRegister) {
		this.field_149761_L = iconRegister.registerIcon(FluxGearAddons.RESOURCESPREFIX + "draconium_block_0");
	}

	@SideOnly(Side.CLIENT)
	public int func_149645_b() {
		return -1;
	}

	public boolean func_149662_c() {
		return false;
	}

	public boolean func_149686_d() {
		return false;
	}

	public boolean hasTileEntity(int metadata) {
		return metadata == 0 || metadata == 1;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return metadata != 0 && metadata != 1 ? null : new TileInvisibleMultiblock();
	}

	public Item func_149650_a(int meta, Random p_149650_2_, int var2) {
		return meta == 0 ? Item.getItemFromBlock(FluxGearContent.draconiumBlock) : (meta == 1 ? Item.getItemFromBlock(Blocks.redstone_block) : null);
	}

	public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta != 0 && meta != 1) {
			if(meta != 2) {
				return false;
			} else {
				TileEnergyPylon pylon1 = world.getTileEntity(x, y + 1, z) != null && world.getTileEntity(x, y + 1, z) instanceof TileEnergyPylon?(TileEnergyPylon)world.getTileEntity(x, y + 1, z):(world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof TileEnergyPylon?(TileEnergyPylon)world.getTileEntity(x, y - 1, z):null);
				if(pylon1 == null) {
					return false;
				} else {
					pylon1.reciveEnergy = !pylon1.reciveEnergy;
					world.markBlockForUpdate(pylon1.field_145851_c, pylon1.field_145848_d, pylon1.field_145849_e);
					pylon1.onActivated();
					return true;
				}
			}
		} else {
			TileInvisibleMultiblock pylon = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)world.getTileEntity(x, y, z):null;
			if(pylon == null) {
				ProjectFluxGear.logger.error("Missing Tile Entity (TileInvisibleMultiblock)");
				return false;
			} else {
				TileEnergyStorageCore master = pylon.getMaster();
				if(master == null) {
					this.func_149695_a(world, x, y, z, this);
					return false;
				} else {
					if(!world.isRemote) {
						world.markBlockForUpdate(master.field_145851_c, master.field_145848_d, master.field_145849_e);
						player.addChatComponentMessage(new ChatComponentText("Tier:" + master.getTier()));
						String BN = String.valueOf(master.getEnergyStored());
						if(BN.substring(BN.length() - 2).contentEquals(".0")) {
							BN = BN.substring(0, BN.length() - 2);
						}

						player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("info.de.charge.txt") + ": " + Utils.formatNumber(master.getEnergyStored()) + " / " + Utils.formatNumber(master.getMaxEnergyStored()) + " [" + BN + " RF]"));
					}

					return true;
				}
			}
		}
	}

	public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta != 0 && meta != 1) {
			if(meta == 2 && world.getBlock(x, y + 1, z) != FluxGearAddons.energyPylon && world.getBlock(x, y - 1, z) != FluxGearAddons.energyPylon) {
				world.setBlock(x, y, z, Blocks.glass);
			}
		} else {
			TileInvisibleMultiblock thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)world.getTileEntity(x, y, z):null;
			if(thisTile == null) {
				LogHelper.error("Missing Tile Entity (TileInvisibleMultiblock)");
				this.revert(world, x, y, z);
				return;
			}

			TileEnergyStorageCore master = thisTile.getMaster();
			if(master == null) {
				LogHelper.error("Master = null reverting!");
				this.revert(world, x, y, z);
				return;
			}

			if(master.isOnline()) {
				master.isStructureStillValid(thisTile.getMaster().getTier() == 1);
			}

			if(!master.isOnline()) {
				this.revert(world, x, y, z);
			}
		}

	}

	private void revert(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0) {
			world.setBlock(x, y, z, ModBlocks.draconiumBlock);
		} else if(meta == 1) {
			world.setBlock(x, y, z, Blocks.redstone_block);
		}

	}

	public void func_149749_a(World world, int x, int y, int z, Block p_149749_5_, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		TileInvisibleMultiblock thisTile = tile != null && tile instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)tile:null;
		if(thisTile != null && thisTile.getMaster() != null && thisTile.getMaster().isOnline()) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
			thisTile.getMaster().isStructureStillValid(thisTile.getMaster().getTier() == 1);
		}

		super.func_149749_a(world, x, y, z, p_149749_5_, meta);
	}

	public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta != 0 && meta != 1) {
			return meta == 2?AxisAlignedBB.getBoundingBox((double)x + 0.49D, (double)y + 0.49D, (double)z + 0.49D, (double)x + 0.51D, (double)y + 0.51D, (double)z + 0.51D):super.func_149633_g(world, x, y, z);
		} else {
			TileInvisibleMultiblock thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)world.getTileEntity(x, y, z):null;
			return thisTile != null && thisTile.getMaster() != null? AxisAlignedBB.getBoundingBox((double) thisTile.getMaster().field_145851_c, (double) thisTile.getMaster().field_145848_d, (double) thisTile.getMaster().field_145849_e, (double) thisTile.getMaster().field_145851_c + 0.5D, (double) thisTile.getMaster().field_145848_d + 0.5D, (double) thisTile.getMaster().field_145849_e + 0.5D):super.func_149633_g(world, x, y, z);
		}
	}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 0?new ItemStack(ModBlocks.draconiumBlock):(world.getBlockMetadata(x, y, z) == 1?new ItemStack(Blocks.redstone_block):new ItemStack(Blocks.glass));
	}
}
