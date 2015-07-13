package mortvana.projectfluxgear.util.module.classifiers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.player.PlayerEvent;

public interface IBlockBreakingModule extends IFluxGearModule {

	/**
	 *  Return true if using the tool allows the block to drop as an item.
	 *
	 *  @param itemstack - ItemStack being used as the tool.
	 *  @param block - Block being checked for ability to break.
	 *  @param meta - Metadata of the block being broken.
	 *  @param player - Player doing the stuff.
	 *  @return True if the player can harvest the block. False if they can't.
	 */
	public boolean canHarvestBlock(ItemStack itemstack, Block block, int meta, EntityPlayer player);

	public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityPlayer player);

	public void handleBreakSpeed(PlayerEvent.BreakSpeed event);

	public List<Material> getEffectedMaterials();

	public String getToolType();
}
