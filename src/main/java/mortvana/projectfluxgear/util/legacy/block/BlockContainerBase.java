package mortvana.projectfluxgear.util.legacy.block;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.projectfluxgear.util.legacy.block.tile.TileEntityBase;

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
		this.setBlockName("block" + "." + name.replace(" ", "")); //A default value. Absolutely acceptable to not keep it.
	
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
        //Deep dark voodoo. If you get a security exception, here it is. I'm sorry, Gyro say he did it all for the greater good.
        Field field;
        try {
            //Get the field of the block class.
            field = Block.class.getField("blockMaterial");
            field.setAccessible(true);

            //Modify the field to not be final.
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(this, m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //Make sure that the entries to canBlockGrass are still valid.
        //canBlockGrass[blockID] = !m.getCanBlockGrass();
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
                //if ((((tileX - x) == ForgeDirection.VALID_DIRECTIONS[i].offsetX) && ((tileY - y) == ForgeDirection.VALID_DIRECTIONS[i].offsetY))
                        //&& ((tileZ - z) == ForgeDirection.VALID_DIRECTIONS[i].offsetZ))
                    //b.updateAdjacency(te2, i);
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
