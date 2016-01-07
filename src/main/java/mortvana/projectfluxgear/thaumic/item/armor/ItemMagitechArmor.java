package mortvana.projectfluxgear.thaumic.item.armor;

import java.util.List;

import mortvana.legacy.clean.core.util.item.ItemArmorFluxGear;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.enums.EnumArmorType;
import mortvana.melteddashboard.util.helpers.NBTHelper;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.projectfluxgear.api.item.armor.IMagitechArmor;
import mortvana.projectfluxgear.library.FluxGearLibrary;

public abstract class ItemMagitechArmor extends ItemArmorFluxGear implements IMagitechArmor {

    //1czykznt
    public ItemMagitechArmor(ItemArmor.ArmorMaterial material, int renderIndex, EnumArmorType type, String name, String sheet, String icon) {
	    super(material, renderIndex, type, name, sheet, icon);
	    setCreativeTab(FluxGearLibrary.equipmentTab);
    }

	/* ItemArmor Overrides */
	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack item) {
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "fluxgear:armor/" + sheetName + (armorType == EnumArmorType.PANTS ? "_2" : "_1") + (type == null ? "_overlay" : "") + ".png";
	}


	/* Item Overrides */
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getBlock(x, y, z) == Blocks.cauldron && world.getBlockMetadata(x, y, z) > 0) {
			removeColor(stack);
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
			world.updateNeighborsAboutBlockChange(x, y, z, Blocks.cauldron);
			//setColor;
			return true;
		} else {
			return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (StringHelper.displayShiftForDetails && !StringHelper.isShiftKeyDown()) {
			list.add(StringHelper.holdShiftForDetails());
		} else if (StringHelper.isShiftKeyDown()) {
			list.add(StringHelper.visDiscount(getVisDiscount(stack, player, null)));
		}
		super.addInformation(stack, player, list, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		super.registerIcons(register);
		overlayIcon = register.registerIcon(icon + "_overlay");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}


    /* IArmorApiarist */
    @Override
    public boolean protectPlayer(EntityPlayer entityPlayer, ItemStack stack, String cause, boolean protect) {
        return false;
    }

    /* ILPGauge */
    @Override
    public boolean canSeeLPBar(ItemStack stack) {
        return stack.getItem() instanceof ItemMagitechArmor && NBTHelper.hasKey(stack, FluxGearLibrary.SANGUINE);
    }

    /* IRepairableExtended */
    @Override
    public boolean doRepair(ItemStack stack, EntityPlayer player, int level) {
        return false;
    }

    /* IRunicArmor */
    @Override
    public int getRunicCharge(ItemStack stack) {
        return 0;
    }

    /* ISpecialArmor */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
	    boolean doMetaDamageCalc = true;
	    if (source == DamageSource.fall) {
		    doMetaDamageCalc = false;
	    }
	    //if ()

	    if (doMetaDamageCalc) {
		    stack.damageItem(damage, entity);
	    }
    }

	/* IManaProficiencyArmor */
	@Override
	public boolean shouldGiveProficiency(ItemStack stack, int slot, EntityPlayer player) {
		return false;
	}

	/* IManaDiscountArmor */
	@Override
	public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
		return 0;
	}

	/* IPhantomInkable  */
	@Override
	public boolean hasPhantomInk(ItemStack stack) {
		return false;
	}

	@Override
	public void setPhantomInk(ItemStack stack, boolean ink) {

	}

	/* IManaUsingItem */
	@Override
	public boolean usesMana(ItemStack stack) {
		return false;
	}
}
