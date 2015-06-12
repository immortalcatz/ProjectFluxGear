package oldcode.projectfluxgear;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import oldcode.projectfluxgear.core.ProjectFluxGear;

public class ItemPaintbrush extends Item {
	// Totally not for extending classes :P...
	public static final int maxPaint = 1728;
	IIcon bristles;
	public static final int[] dyeColorsDec = new int[]{ 16777215, 14383931, 13972141, 6921443, 12036905, 4832573, 13400208, 6118749, 10660008, 5287101, 10045406, 3559620, 7620651, 4612892, 14101287, 2105376 };
	ItemStack dyeType;

	public ItemPaintbrush() {
		super();
		dyeType = new ItemStack(Items.dye);
		setCreativeTab(CreativeTabs.tabTools).setMaxDamage(maxPaint);
		maxStackSize = 1;

	}

	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		MovingObjectPosition mop = this.raytraceFromPlayer(entity.worldObj, entity, false);
		if(mop != null) {
			int xPos = mop.blockX;
			int yPos = mop.blockY;
			int zPos = mop.blockZ;
			ForgeDirection sideHit = ForgeDirection.getOrientation(mop.sideHit);
			colorBlocks(stack, entity, entity.worldObj, xPos, yPos, zPos, 0);
		}

		return false;
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return colorBlocks(stack, player, world, x, y, z, 1);
	}

	private boolean colorBlocks(ItemStack stack, EntityLivingBase player, World world, int x, int y, int z, int paintRadius) {
		int damage = stack.getItemDamage();
		if(stack.hasTagCompound()) {
			int type = stack.getTagCompound().getInteger("PaintType");
				if(type != 0) {
				int max = maxPaint - damage;
				int amount = ProjectFluxGear.instance.colorStoneBlocks(world, x, y, z, type - 1, paintRadius, max);
				if(amount > 0) {
					if(!player.worldObj.isRemote) {
						Block total = Blocks.stone;
						player.worldObj.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), total.stepSound.func_150496_b(), (total.stepSound.getVolume() + 1.0F) / 2.0F, total.stepSound.getPitch() * 0.8F);
					}

					int total1 = amount + damage;
					if(total1 >= maxPaint) {
						resetItem(stack);
					} else {
						stack.damageItem(amount, player);
					}
					return true;
				}
			}
		}
		return false;
	}

	public MovingObjectPosition raytraceFromPlayer(World world, EntityLivingBase entity, boolean par3) {
		float f = 1.0F;
		float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f;
		float f2 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f;
		double prevX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)f;
		double prevY = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)f + 1.62D - (double)entity.yOffset;
		double prevZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(prevX, prevY, prevZ);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, par3);
	}

	void resetItem(ItemStack stack) {
		stack.setItemDamage(0);
		stack.getTagCompound().setInteger("PaintType", 0);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iiconRegister) {
		itemIcon = iiconRegister.registerIcon("mortvana.mechstoneworks:paintbrush");
		bristles = iiconRegister.registerIcon("mortvana.mechstoneworks:paintbristles");
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 1 ? bristles : itemIcon;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		if(pass == 1 && stack.hasTagCompound()) {
			int type = stack.getTagCompound().getInteger("PaintType");
			if(type != 0) {
				return dyeColorsDec[type - 1];
			}
		}
		return 16777215;
	}

	public String getItemDisplayName(ItemStack par1ItemStack) {
		String base = ("" + StatCollector.translateToLocal(getUnlocalizedNameInefficiently(par1ItemStack) + ".name")).trim();
		String color = this.getDyeName(par1ItemStack);
		if(!color.equals("")) {
			color = " (" + StatCollector.translateToLocal(color) + ")";
		}
		return base + color;
	}

	public String getDyeName(ItemStack stack) {
		String color = "";
		if(stack.hasTagCompound()) {
			int type = stack.getTagCompound().getInteger("PaintType");
			if(type != 0) {
				dyeType.setItemDamage(16 - type);
				return dyeType.getUnlocalizedName() + ".name";
			}
		}
		return color;
	}
}