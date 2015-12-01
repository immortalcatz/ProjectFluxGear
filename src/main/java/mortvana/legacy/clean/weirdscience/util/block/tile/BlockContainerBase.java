package mortvana.legacy.clean.weirdscience.util.block.tile;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.legacy.clean.weirdscience.util.block.tile.TileEntityBase;

@Deprecated
//TODO: I like to burn fascists
public abstract class BlockContainerBase extends BlockContainer {

	protected String englishName;

	protected TileEntity protoTileEntity;

	public String harvestType = "pickaxe";
	public int harvestLevel = 1;

	public BlockContainerBase(String name, Material material) {
		/*
		 * Default material set to rock.
		 */
		super(material);
		englishName = name;
		setUnlocalizedName("block" + "." + name.replace(" ", "")); //A default value. Absolutely acceptable to not keep it.

	}

	public BlockContainerBase(String name) {
		this(name, Material.rock);
	}
	public BlockContainerBase(Material material) {
		super(material);
	}

	public boolean isEnabled ()
	{
		return true;
	}

	public Material getMaterial ()
	{
		return this.blockMaterial;
	}

	public void setMaterial (Material m) {
		// Formerly deep dark voodoo. You can't get a security exception here anymore.
		// Gyro says he did reflection for the greater good. I say, Access Transformer Master-Race!
		blockMaterial = m;

		//Make sure that the entries to canBlockGrass are still valid.
		translucent = !m.isTranslucent;
	}

	@Override
	public int getHarvestLevel (int subBlockMeta) {
		//By default, no metadata-based sub-blocks.
		return harvestLevel;
	}

	@Override
	public void onNeighborChange (IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			TileEntityBase b = (TileEntityBase) te;
			TileEntity te2 = world.getTileEntity(tileX, tileY, tileZ);
			//Check directionality
			for (int i = 0; i < 6; i++) {
				if ((((tileX - x) == ForgeDirection.VALID_DIRECTIONS[i].offsetX) && ((tileY - y) == ForgeDirection.VALID_DIRECTIONS[i].offsetY)) && ((tileZ - z) == ForgeDirection.VALID_DIRECTIONS[i].offsetZ)) {
					b.updateAdjacency(te2, i);
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}


	public TileEntity createNewTileEntity(World world) {
		return createNewTileEntity(world, 0);
	}

	@Override
	public void onBlockPreDestroy (World world, int x, int y, int z, int oldmeta) {
		super.onBlockPreDestroy(world, x, y, z, oldmeta);

		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			if(te instanceof TileEntityBase) {
				TileEntityBase b = (TileEntityBase)te;
				b.onKill();
			}
		}
	}
}
