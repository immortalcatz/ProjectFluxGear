package mortvana.legacy.errored.thaumicrevelations.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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

import mortvana.melteddashboard.util.helpers.ChatHelper;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.legacy.errored.thaumicrevelations.block.tileentity.TileWardChest;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BlockWardChest extends BlockContainer {

	Random random;

	protected BlockWardChest() {
		super(Material.wood);
		setBlockUnbreakable();
		setHardness(6000.0F);
		disableStats();
		setStepSound(soundTypeWood);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		ThaumcraftApi.portableHoleBlackList.add(this);
		random = new Random();

		if (registerInCreative()) {
			setCreativeTab(ModCreativeTab.INSTANCE);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		byte meta = 0;
		int l1 = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
		if (l1 == 0) {
			meta = 2;
		}
		if (l1 == 1) {
			meta = 5;
		}
		if (l1 == 2) {
			meta = 3;
		}
		if (l1 == 3) {
			meta = 4;
		}
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		TileWardChest chest = (TileWardChest) world.getTileEntity(x, y, z);
		if (itemstack.hasDisplayName()) {
			chest.customName = itemstack.getDisplayName();
		}
		if (entity instanceof EntityPlayer) {
			chest.owner = ((EntityPlayer) entity).username;
		} else {
			chest.owner = "";
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		TileWardChest chest = (TileWardChest) world.getTileEntity(x, y, z);
		if (chest != null) {
			for (int i = 0; i < chest.getSizeInventory(); i++) {
				ItemStack itemstack = chest.getStackInSlot(i);
				if (itemstack != null) {
					float f = random.nextFloat() * 0.8F + 0.1F;
					float f1 = random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;
					for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						int k1 = random.nextInt(21) + 10;
						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}
						itemstack.stackSize -= k1;
						entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getMetadata()));
						float f3 = 0.05F;
						entityitem.motionX = ((float) random.nextGaussian() * f3);
						entityitem.motionY = ((float) random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = ((float) random.nextGaussian() * f3);
						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}
			world.func_96440_m(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		}
		TileWardChest chest = (TileWardChest) world.getTileEntity(x, y, z);
		if (chest != null) {
			String owner = chest.owner;
			if (owner.equals(player.username)) {
				if ((player.getCurrentEquippedItem() != null) && (player.getCurrentEquippedItem().getItem() instanceof ItemWandCasting)) {
					if (!world.isRemote) {
						int meta = world.getBlockMetadata(x, y, z);
						dropBlockAsItem(world, x, y, z, meta, par6);
						world.playAuxSFX(2001, x, y, z, blockId + (meta << 12));
						world.setBlock(x, y, z, null, 0, 0);
					} else {
						player.swingItem();
					}
				} else {
					player.displayGUIChest(chest);
					world.playSoundEffect(x, y, z, "thaumcraft.key", 1.0F, 1.0F);
				}
			} else {
				player.addChatMessage(ChatHelper.addChatMessage(player, StringHelper.localize("info.fluxgear.thaumic.lockedchest")/*"The Chest refuses to budge."*/));
				world.playSoundEffect(x, y, z, "thaumcraft.doorfail", 1.0F, 0.2F);
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return ConfigBlocks.blockWoodenDevice.getIcon(par1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idWardChest;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean registerInCreative() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileWardChest();
	}
}