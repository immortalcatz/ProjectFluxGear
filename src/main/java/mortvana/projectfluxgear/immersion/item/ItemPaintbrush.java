package mortvana.projectfluxgear.immersion.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ColorLibrary;
import mortvana.melteddashboard.util.helpers.MiscHelper;
import mortvana.projectfluxgear.immersion.common.FluxGearImmersion;
import mortvana.projectfluxgear.immersion.util.helpers.PaintingHelper;

public class ItemPaintbrush extends Item {
	// Totally not for extending classes :P...
	public final int maxPaint = 1728;
	IIcon bristles;

	public ItemPaintbrush() {
		super();
		setMaxStackSize(1);
		setCreativeTab(FluxGearImmersion.paintedStoneTab);
		setMaxDamage(maxPaint);
		setUnlocalizedName("fluxgear.paintbrush");
	}

	public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemstack) {
		MovingObjectPosition mop = MiscHelper.raytraceFromPlayer(entity.worldObj, entity, false);
		if (mop != null) {
			PaintingHelper.paintBlocks(entity.worldObj, mop.blockX, mop.blockY, mop.blockZ, itemstack, entity, 0);
		}
		return false;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, float hitX, float hitY, float hitZ) {
		return PaintingHelper.paintBlocks(world, x, y, z, itemstack, player, 1);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("fluxgear:tools/paintbrush");
		bristles = iconRegister.registerIcon("fluxgear:tools/paintbrushbristles");
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int metadata, int pass) {
		return pass == 1 ? bristles : itemIcon;
	}

	@SideOnly(Side.CLIENT)
	public int getColorForItemStack(ItemStack itemstack, int pass) {
		return (pass == 1 && itemstack.hasTagCompound()) ? PaintingHelper.paintTypes.get(itemstack.getTagCompound().getInteger("PaintType")).color : ColorLibrary.CLEAR;
	}

	public String getItemDisplayName(ItemStack itemstack) {
		return StatCollector.translateToLocal(getUnlocalizedNameInefficiently(itemstack)) + "." + PaintingHelper.paintTypes.get(itemstack.getTagCompound().getInteger("PaintType")).name + ".name";
	}

	public Item register() {
		GameRegistry.registerItem(this, "fluxgear.paintbrush");
		GameRegistry.addRecipe(new ShapedOreRecipe(this, "w", "s", 'w', new ItemStack(Blocks.wool, 1, MeltedDashboardCore.WILDCARD), 's', "stickWood"));
		return this;
	}

	/*public String getItemDisplayName(ItemStack par1ItemStack) {
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


		public static final int[] dyeColorsDec = new int[]{ 16777215, 14383931, 13972141, 6921443, 12036905, 4832573, 13400208, 6118749, 10660008, 5287101, 10045406, 3559620, 7620651, 4612892, 14101287, 2105376 };
		ItemStack dyeType;
	}*/
}
