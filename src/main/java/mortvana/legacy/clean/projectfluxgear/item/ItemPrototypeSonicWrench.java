package mortvana.legacy.clean.projectfluxgear.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mortvana.melteddashboard.api.item.tool.wrench.EnumWrenchMode;
import mortvana.melteddashboard.api.item.tool.wrench.IFluxGearAdvOmniwrench;
import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.legacy.clean.wrenching.NBTWrenchingHelper;
import pneumaticCraft.api.block.IPneumaticWrenchable;

/*
 * Currently Works as:
 * A Decent Weapon
 * Flux Gear Standard IFluxGearAdvOmniwrench
 *     Flux Gear Standard IFluxGearOmniwrench:
 *         Flux Gear Standard IFluxGearWrench:
 *             CoFH Hammer (MFR or TE)
 *             BuildCraft Wrench
 *             Applied Energistics Wrench
 *             Mekanism Wrench
 *             Ender IO Compatable Facade Viewing Wrench
 *             Flux Gear Standard Wrench
 *         Project:Red Screwdriver
 *         Hairy Spice Wrench
 *         Professor Flaxbeard's Wondrous Steam Power Mod Wrench
 *         MineFactory Reloaded Sledge Hammer (for overlays)
 *         Railcraft Crowbar
 *     Extra Trees Hammer
 *     Carpenter's Blocks Hammer (SHOULD, BUT IT *WAS* BROKEN)
 * IndustrialCraft Wrench
 * Flowstone Energy Wrench
 * PneumaticCraft Pneumatic Wrench
 * Crude RotaryCraft Screwdriver
 *
 * Planned to Work as:
 * [API] Carpenter's Blocks Chisel (Once I add modes)
 * [HARDCODE] Mekanism Configurator (Once I add modes)
 * Enhanced Portals Painter (Once I add modes)
 * Thaumcraft Wand (For Rotations)
 * Advanced EnderIO Support (Sometime in the future?)
 * [API] Chisel Chisel (I don't care that it doesn't make sense!)
 * [HARDCODE] Applied Energistics Network Tool (Once I add modes)
 * Project Flux Gear Paintbrush (Because we can, for the good of all of us, except the ones who are Gregs)
 *
 * May Work as:
 * [HARDCODE] Full RotaryCraft Screwdriver (If Reika made stuff streamlined enough to fully integrate, or I have nothing else to do... maybe someday...)
 * [HARDCODE] BiblioCraft Screwgun (No API :c)
 * [HARDCODE] BluePower Screwdriver (NO SCREWDRIVER API, MAYBE EVENTUALLY)
 *
 * Might Work as, because I am silly:
 * Botania Wand of the Forest? (This wrench works as a chisel, so this is nothing new :P)
 * Blood Magic Alchemy Tools (Science, yo... Well, alchemy... Actually it's alchemical wizardry)
 * Thaumometer (Just why?)
 * TE Multimeter (Actually makes sense...)
 * IE Voltmeter thingy (Come on...)
 * RoC Angular Transducer (Keep it up...)
 * A way of healing (You've... What, what?)
 * A geiger counter (Umm...)
 * A walkman! (Shit, you're just going mad, Mortvana)
 * That stupid Essentia Pipe thing
 * A Golem bell (With Soaryn-grade dinging)
 */

public class ItemPrototypeSonicWrench extends FluxGearItem implements IFluxGearAdvOmniwrench {

	public ItemPrototypeSonicWrench() {
		super("fluxgear");
		setCreativeTab(CreativeTabs.tabTools);
		setMaxStackSize(1);
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
		return true;
	}

	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
		return true;
	}

	public Multimap getAttributeModifiers() {
		HashMultimap localHashMultimap = HashMultimap.create();
		localHashMultimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", 7.0D, 0));
		return localHashMultimap;
	}

	/* IWrenchable (IndustrialCraft), IDismantleable (CoFH Mods), IFlowWrench (Flowstone Energy), Screwdriverable (RotaryCraft) and Rotation Handling*/
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
		return NBTWrenchingHelper.delegateWrenching(stack, player, world, x, y, z, hitSide);
	}

	/* PneumaticCraft Drone Compat */
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
		if(itemstack.hasTagCompound() && itemstack.stackTagCompound.hasKey("PneumaticWrench") && !player.worldObj.isRemote) {
			if(entity.isEntityAlive() && entity instanceof IPneumaticWrenchable) {
				if(((IPneumaticWrenchable)entity).rotateBlock(entity.worldObj, player, 0, 0, 0, ForgeDirection.UNKNOWN)) {
					//TODO: Fix
					//NetworkHandler.sendToAllAround(new PacketPlaySound(Sounds.PNEUMATIC_WRENCH, entity.posX, entity.posY, entity.posZ, 1.0F, 1.0F, false), entity.worldObj);
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasNBT(ItemStack itemstack, String nbtKey) {
		return itemstack.getItem() instanceof ItemPrototypeSonicWrench && itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey(nbtKey) && itemstack.getTagCompound().getBoolean(nbtKey);
	}

	public boolean isMode(ItemStack itemstack, EnumWrenchMode... modes) {
		for (EnumWrenchMode mode : modes) {
			if (itemstack.getItem() instanceof ItemPrototypeSonicWrench) {
				ItemPrototypeSonicWrench wrench = (ItemPrototypeSonicWrench) itemstack.getItem();
				if (wrench.getWrenchMode(itemstack).equals(mode)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean overrideIndustrialChance(ItemStack wrench) {
		return hasNBT(wrench, "IC2Override");
	}

	public EnumWrenchMode getWrenchMode(ItemStack wrench) {
		if (wrench.getItem() instanceof ItemPrototypeSonicWrench && wrench.hasTagCompound() && wrench.stackTagCompound.hasKey("Mode")) {
			return EnumWrenchMode.values() [wrench.stackTagCompound.getByte("Mode")];
		} else {
			MeltedDashboardCore.logger.error("Your wrench lacks the proper NBT key for its mode. This shouldn't happen.");
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setByte("Mode", (byte) 0);
			nbt = addModules(nbt); //Temporary, until I add modules
			wrench.setTagCompound(nbt);
			return EnumWrenchMode.STANDARD;
		}
	}

	public NBTTagCompound addModules(NBTTagCompound nbt) {
		for (String module : modules) {
			nbt.setBoolean(module, true);
		}
		return nbt;
	}

	/* ICarpentersHammer (Carpenter's Blocks) */
	@Override
	public void onHammerUse(World world, EntityPlayer player) {}

	@Override
	public boolean canUseHammer(World world, EntityPlayer player) {
		return hasNBT(player.getCurrentEquippedItem(), "CarpentersHammer") && isMode(player.getCurrentEquippedItem(), EnumWrenchMode.STANDARD);
	}

	/* IScrewdriver (Project:Red) */
	@Override
	public void damageScrewdriver(World world, EntityPlayer player) {}

	/* IToolCrowbar (RailCraft) */
	@Override
	public boolean canWhack(EntityPlayer player, ItemStack wrench, int x, int y, int z) {
		return hasNBT(wrench, "RailcraftCrowbar") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public void onWhack(EntityPlayer player, ItemStack wrench, int x, int y, int z) {
		player.swingItem();
	}

	@Override
	public boolean canLink(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		return player.isSneaking() && hasNBT(wrench, "RailcraftCrowbar") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public void onLink(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		player.swingItem();
	}

	@Override
	public boolean canBoost(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		return !player.isSneaking() && hasNBT(wrench, "RailcraftCrowbar") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public void onBoost(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		player.swingItem();
	}

	/* IToolWrench (BuildCraft) and IPipeWrench (Flaxbeard's Steam Power) */
	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z) {
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z) {}

	/* IToolHammer (CoFH Mods) */
	@Override
	public boolean isUsable(ItemStack wrench, EntityLivingBase entityLiving, int x, int y, int z) {
		return isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public void toolUsed(ItemStack wrench, EntityLivingBase entityLiving, int x, int y, int z) {}

	/* IAEWrench (Applied Energistics 2) */
	@Override
	public boolean canWrench(ItemStack wrench, EntityPlayer player, int x, int y, int z) {
		return hasNBT(wrench, "AEWrench") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	/* IWrench (Hairy Spice) */
	@Override
	public boolean isWrench(ItemStack wrench) {
		return hasNBT(wrench, "HairySpiceWrench") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	/* IToolHammer (Extra Trees) */
	@Override
	public boolean isActive(ItemStack wrench) {
		return hasNBT(wrench, "ExtraTreesHammer") && isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public void onHammerUsed(ItemStack wrench, EntityPlayer player) {}

	/* IMekWrench (Mekanism) */
	@Override
	public boolean canUseWrench(EntityPlayer player, int x, int y, int z) {
		return hasNBT(player.getCurrentEquippedItem(), "MekanismWrench") && isMode(player.getCurrentEquippedItem(), EnumWrenchMode.STANDARD);
	}

	/* ITool (Ender IO) */
	@Override
	public boolean canUse(ItemStack stack, EntityPlayer player, int x, int y, int z) {
		return hasNBT(stack, "EnderIOWrench") && isMode(stack, EnumWrenchMode.STANDARD);
	}

	@Override
	public boolean shouldHideFacades(ItemStack stack, EntityPlayer player) {
		return hasNBT(stack, "EnderIOWrench") && hasNBT(stack, "HideFacades");
	}

	@Override
	public void used(ItemStack stack, EntityPlayer player, int x, int y, int z) {}

	/* IFluxGearWrench (Project Flux Gear) */
	@Override
	public boolean isPFGWrench(ItemStack wrench) {
		return isMode(wrench, EnumWrenchMode.STANDARD);
	}

	@Override
	public boolean hideFacades(ItemStack wrench) {
		return hasNBT(wrench, "HideFacades");
	}

	@Override
	public void onLeftClick() {}

	@Override
	public void onRightClick() {}

	public static String[] modules = new String[] {
			"CarpentersHammer",
			"RotaryScrewdriver",
			"FlowstoneEnergyWrench",
			"IndustrialCraftWrench",
			"RailcraftCrowbar",
			"AEWrench",
			"HairySpiceWrench",
			"ExtraTreesHammer",
			"MekanismWrench",
			"EnderIOWrench",
			"HideFacades",
			"PneumaticWrench",
			"IC2Override",
			"Chisel",
			"CarpentersChisel",
			"Configurator",
			"ThaumicWand",
			"EssentiaResonator",
			"Paintbrush",
			"EnhancedPortalsPainter",
			"AENetworkTool",
			"AdvEnderIOWrench",
			"Screwgun",
			"BluePowerScrewdriver",
			"BotaniaWand",
			"GolemBell",
			"Thaumometer",
			"TEMultimeter",
			"IEMultimeter",
			"Fluxmeter",
			"Pedometer",
			"AngularTransducer",
			"Healing",
			"GeigerCounter",
			"Walkman"
	};

}
