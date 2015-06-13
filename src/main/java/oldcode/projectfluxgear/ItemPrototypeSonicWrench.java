package oldcode.projectfluxgear;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cofh.api.block.IDismantleable;
import Reika.RotaryCraft.API.Screwdriverable;
import Reika.RotaryCraft.API.ShaftMachine;
import ic2.api.tile.IWrenchable;
import main.flowstoneenergy.core.interfaces.IFlowWrenchable;
import oldcode.projectfluxgear.api.IFluxGearAdvOmniwrench;
import oldcode.projectfluxgear.util.helper.cofh.BlockHelper;
import oldcode.projectfluxgear.util.helper.cofh.ServerHelper;
import oldcode.projectfluxgear.util.item.ItemFluxGear;
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
 * Carpenter's Blocks Chisel (Once I add modes)
 * Mekanism Configurator (Once I add modes)
 * Enhanced Portals Painter (Once I add modes)
 * Thaumcraft Wand (For Rotations)
 * Advanced EnderIO Support (Sometime in the future?)
 * Chisel Chisel (I don't care that it doesn't make sense!)
 * Applied Energistics Network Tool (Once I add modes)
 * Project Flux Gear Paintbrush (Because we can, for the good of all of us, except the ones who are Gregs)
 *
 * May Work as:
 * Full RotaryCraft Screwdriver (If Reika made stuff streamlined enough to fully integrate, or I have nothing else to do... maybe someday...)
 * BiblioCraft Screwgun (No API :c)
 * BluePower Screwdriver (NO SCREWDRIVER API, MAYBE EVENTUALLY)
 */

public class ItemPrototypeSonicWrench extends ItemFluxGear implements IFluxGearAdvOmniwrench {

	public ItemPrototypeSonicWrench() {
		super("mortvana/fluxgearzee");
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
		Block block = world.getBlock(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		/* IDismantleable Compat */
		if (ServerHelper.isServerWorld(world) && player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(player, world, x, y, z)) {
			((IDismantleable)block).dismantleBlock(player, world, x, y, z, false);
			return true;
		}

		/* Crude RotaryCraft Screwdriver Support */
		if (tile instanceof ShaftMachine) {
			ShaftMachine machine = (ShaftMachine) tile;
			machine.setIORenderAlpha(512);
			world.markBlockForUpdate(x, y, z);
		}
		if (tile instanceof Screwdriverable) {
			Screwdriverable screwdriverable = (Screwdriverable) tile;
			boolean flag;
			if (player.isSneaking()) {
				flag = screwdriverable.onShiftRightClick(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[hitSide]);
			} else {
				flag = screwdriverable.onRightClick(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[hitSide]);
			}
			return flag;
		}

		/* Rotation Handling */
		if (BlockHelper.canRotate(block)) {

			if (player.isSneaking()) {
				world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlockAlt(world, block, x, y, z), 3);
				/*if (ServerHelper.isClientWorld(world)) {
					String soundName = block.stepSound.getBreakSound();
					FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.6F));
				}*/
			} else {
				world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlock(world, block, x, y, z), 3);
				/*if (ServerHelper.isClientWorld(world)) {
					String soundName = block.stepSound.getBreakSound();
					FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.8F));
				}*/
			}
			return ServerHelper.isServerWorld(world);
		} else if (!player.isSneaking() && block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(hitSide))) {
			player.swingItem();
			return ServerHelper.isServerWorld(world);
		}

		/* Flowstone Energy Compat */
		if (block instanceof IFlowWrenchable) {
			if (player.isSneaking()) {
				world.setBlockToAir(x, y, z);
				if (ServerHelper.isServerWorld(world)) {
					world.spawnEntityInWorld(new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(block, 1, meta)));
				}
			}
		}

		/* Industrial Craft 2 */
		if (tile instanceof IWrenchable) {
			IWrenchable wrenchable = (IWrenchable) tile;
			if (player.isSneaking()) {
				hitSide = BlockHelper.SIDE_OPPOSITE[hitSide];
			}
			if (wrenchable.wrenchCanSetFacing(player, hitSide)) {
				if (ServerHelper.isServerWorld(world)) {
					wrenchable.setFacing((short) hitSide);
				}
			} else if (wrenchable.wrenchCanRemove(player)) {
				ItemStack dropBlock = wrenchable.getWrenchDrop(player);

				if (dropBlock != null) {
					world.setBlockToAir(x, y, z);
					if (ServerHelper.isServerWorld(world)) {
						List<ItemStack> drops = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
						if (drops.isEmpty()) {
							drops.add(dropBlock);
						} else {
							drops.set(0, dropBlock);
						}
						for (ItemStack drop : drops) {
							float f = 1.0F;
							double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
							EntityItem entity = new EntityItem(world, x + x2, y + y2, z + z2, drop);
							entity.delayBeforeCanPickup = 10;
							world.spawnEntityInWorld(entity);
						}
					}
				}
			}
			return ServerHelper.isServerWorld(world);
		}

		if (block instanceof IPneumaticWrenchable && ServerHelper.isServerWorld(world)) {
			if (((IPneumaticWrenchable) block).rotateBlock(world, player, x, y, z, ForgeDirection.getOrientation(hitSide))) {
				//NetworkHandler.sendToAllAround(new PacketPlaySound(Sounds.PNEUMATIC_WRENCH, x, y, z, 1.0F, 1.0F, false), world);
				return true;
			}
		}
		return false;
	}

	/* PneumaticCraft Drone Compat */
	@Override
	public boolean itemInteractionForEntity(ItemStack iStack, EntityPlayer player, EntityLivingBase entity){
		if(!player.worldObj.isRemote) {
			if(entity.isEntityAlive() && entity instanceof IPneumaticWrenchable) {
				if(((IPneumaticWrenchable)entity).rotateBlock(entity.worldObj, player, 0, 0, 0, ForgeDirection.UNKNOWN)) {
					//NetworkHandler.sendToAllAround(new PacketPlaySound(Sounds.PNEUMATIC_WRENCH, entity.posX, entity.posY, entity.posZ, 1.0F, 1.0F, false), entity.worldObj);
					return true;
				}
			}
		}
		return false;
	}

	/* ICarpentersHammer (Carpenter's Blocks) */
	@Override
	public void onHammerUse(World world, EntityPlayer player) {}

	@Override
	public boolean canUseHammer(World world, EntityPlayer player) {
		return true;
	}

	/* IScrewdriver (Project:Red) */
	@Override
	public void damageScrewdriver(World world, EntityPlayer player) {}

	/* IToolCrowbar (RailCraft) */
	@Override
	public boolean canWhack(EntityPlayer player, ItemStack wrench, int x, int y, int z) {
		return true;
	}

	@Override
	public void onWhack(EntityPlayer player, ItemStack wrench, int x, int y, int z) {
		player.swingItem();
	}

	@Override
	public boolean canLink(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		return player.isSneaking();
	}

	@Override
	public void onLink(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		player.swingItem();
	}

	@Override
	public boolean canBoost(EntityPlayer player, ItemStack wrench, EntityMinecart cart) {
		return !player.isSneaking();
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
		return true;
	}

	@Override
	public void toolUsed(ItemStack wrench, EntityLivingBase entityLiving, int x, int y, int z) {}

	/* IAEWrench (Applied Energistics 2) */
	@Override
	public boolean canWrench(ItemStack wrench, EntityPlayer player, int x, int y, int z) {
		return true;
	}

	/* IWrench (Hairy Spice) */
	@Override
	public boolean isWrench(ItemStack wrench) {
		return true;
	}

	/* IToolHammer (Extra Trees) */
	@Override
	public boolean isActive(ItemStack wrench) {
		return true;
	}

	@Override
	public void onHammerUsed(ItemStack wrench, EntityPlayer player) {}

	/* IMekWrench (Mekanism) */
	@Override
	public boolean canUseWrench(EntityPlayer player, int x, int y, int z) {
		return true;
	}

	/* ITool (Ender IO) */
	@Override
	public boolean canUse(ItemStack stack, EntityPlayer player, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean shouldHideFacades(ItemStack stack, EntityPlayer player) {
		return true;
	}

	@Override
	public void used(ItemStack stack, EntityPlayer player, int x, int y, int z) {}

	/* IFluxGearWrench (Project Flux Gear) */
	@Override
	public boolean isPFGWrench() {
		return true;
	}

	@Override
	public void onLeftWhack() {}

	@Override
	public void onRightWhack() {}

	@Override
	public void onSneakLeftWhack() {}

	@Override
	public void onSneakRightWhack() {}

	@Override
	public void hideFacades() {}
}