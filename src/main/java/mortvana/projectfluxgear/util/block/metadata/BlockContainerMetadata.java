package mortvana.projectfluxgear.util.block.metadata;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockContainerMetadata extends BlockContainer {

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public BlockContainerMetadata(Material material) {
		super(material);
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public BlockContainerMetadata(Material material, CreativeTabs tab) {
		super(material);
		setCreativeTab(tab);
	}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	public BlockContainerMetadata(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material);
		setCreativeTab(tab);
		setHardness(hardness);
		setResistance(resistance);
	}
	// TODO: Expand this as FluxGearBlock adds more constructors

	//public boolean removedByPlayer(World world, int x, int y, int z, EntityPlayer player) {
	//	ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
	//	TileEntityMetadata tile = (TileEntityMetadata) getTileEntity(world, x, y, z);
	//}
}
