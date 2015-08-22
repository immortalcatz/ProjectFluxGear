package mortvana.legacy.crystaltweaks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.crystaltweaks.CrystalClimate;
import mortvana.legacy.crystaltweaks.block.logic.EssenceExtractorLogic;

public class EssenceCrystal extends Item {

	public EssenceCrystal() {
		super();
		this.setCreativeTab(CrystalClimate.tab);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.eat;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World par2World, EntityPlayer player) {
		player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		return itemstack;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (stack.hasTagCompound()) {
			EntityXPOrb entity = new EntityXPOrb(world, player.posX, player.posY + 1, player.posZ, stack.getTagCompound().getInteger("Essence"));
			spawnEntity(player.posX, player.posY + 1, player.posZ, entity, world, player);
			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;
		}
		return stack;
	}

	public static void spawnEntity(double x, double y, double z, Entity entity, World world, EntityPlayer player) {
		if (!world.isRemote) {
			world.spawnEntityInWorld(entity);
		}
	}

	//Need to load in icons from somewhere
	public IIcon leafIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("crystal:crystal_essence");
		leafIcon = iconRegister.registerIcon("crystal:leaf");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.hasTagCompound()) {
			int amount = stack.getTagCompound().getInteger("Essence");
			list.add("Stored Levels: " + EssenceExtractorLogic.getEssencelevels(amount));
		} else {
			list.add("Stored Levels: 0");
		}
	}

	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		int[] amount = new int[] {17, 85, 170, 255, 385, 590, 825};
		for (int i = 0; i < amount.length; i++) {
			ItemStack crystal = new ItemStack(id, 1, 0);
			NBTTagCompound tags = new NBTTagCompound();
			tags.setInteger("Essence", amount[i]);
			crystal.setTagCompound(tags);
			list.add(crystal);
		}
	}
}
