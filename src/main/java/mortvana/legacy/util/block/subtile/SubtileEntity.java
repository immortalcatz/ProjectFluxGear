package mortvana.legacy.util.block.subtile;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.util.helpers.PacketHelper;
import mortvana.legacy.util.helpers.SubtileHelper;
import mortvana.legacy.errored.core.common.ProjectFluxGear;

/**
 * A Sub-TileEntity, this was used for Vazkii's flower system. I use it for my machines.
 * Make sure to map subclasses of this using SubtileHelper.mapSubtile(String, Class).
 * Any subclass of this must have a no parameter constructor.
 */
public class SubtileEntity {

	/** Bucketman's sidekick! */
	protected TileEntity supertile;

	public int ticksExisted = 0;

	/** The NBT Tags to store which Subtile they are */
	public static final String NBT_TAG_TYPE = "type";
	public static final String NBT_TAG_TICKS_EXISTED = "ticksExisted";

	public void setSupertile(TileEntity tile) {
		supertile = tile;
	}

	public boolean canUpdate() {
		return true;
	}

	public void onUpdate() {
		ticksExisted++;
	}

	public final void writeToInternalPacketNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBT_TAG_TICKS_EXISTED, ticksExisted);
		writeToPacketNBT(nbt);
	}

	public final void readFromInternalPacketNBT(NBTTagCompound nbt) {
		if (nbt.hasKey(NBT_TAG_TICKS_EXISTED)) {
			ticksExisted = nbt.getInteger(NBT_TAG_TICKS_EXISTED);
		}
		readFromPacketNBT(nbt);
	}

	/**
	 * Writes some extra data to a network packet. This data is read
	 * by readFromPacketNBT on the client that receives the packet.
	 * Note: This method is also used to write to the world NBT.
	 */
	public void writeToPacketNBT(NBTTagCompound nbt) { }

	/**
	 * Reads data from a network packet. This data is written by
	 * writeToPacketNBT in the server. Note: This method is also used
	 * to read from the world NBT.
	 */
	public void readFromPacketNBT(NBTTagCompound nbt) { }

	public void sync() {
		PacketHelper.dispatchTEToNearbyPlayers(supertile);
	}

	public String getUnlocalizedName() {
		return SubtileHelper.getSubtileStringMapping(getClass());
	}

	/** Gets the block icon for this SubtileEntity*/
	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		return ProjectFluxGear.handler.getSubTileIconForName(getUnlocalizedName());
	}

	/**
	 * Called when this sub tile is placed in the world (by an entity).
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
	}

	/**
	 * Called when a player right clicks this sub tile.
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

	/**
	 * Called when this sub tile is added to the world.
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
	}

	/**
	 * Called when this sub tile is harvested
	 */
	public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
	}

	/**
	 * Allows additional processing of sub tile drops
	 */
	public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
		return list;
	}

	/**
	 * Called on the client when the block being pointed at is the one with this sub tile.
	 * Used to render a HUD portraying some data from this sub tile.
	 */
	@SideOnly(Side.CLIENT)
	public void renderHUD(Minecraft minecraft, ScaledResolution resolution) {
	}

}
