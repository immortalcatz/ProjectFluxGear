package mortvana.projectfluxgear.weirdScienceLegacy;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO: Particle effect when an appropriate block is on top of this engine.
public class BlockOccultEngine extends BlockBloodEngine {
	
	public static ArrayList<String> idols = new ArrayList<String>(2);

	private void initIdols(){
		idols.add(Blocks.skull.getUnlocalizedName());
		idols.add(Blocks.dragon_egg.getUnlocalizedName());
	}

	public void recievePowerOn(World world, int x, int y, int z) {
	}

	public void recievePowerOff(World world, int x, int y, int z) {
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntityOccultEngine te = new TileEntityOccultEngine();
		return te;
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
		updateIdol(world, x, y, z);
	}
	
	private void updateIdol(IBlockAccess world, int x, int y, int z) {
		Block b = world.getBlock(x, y+1, z);
		if(b != null) {
			for(String s : idols) {
				if(s.contentEquals(b.getUnlocalizedName())) {
					if(b.getUnlocalizedName().contentEquals(Blocks.skull.getUnlocalizedName())) {
						//Special case for the wither skull
						TileEntity teUp = world.getTileEntity(x, y+1, z);
						if((teUp != null) && (teUp instanceof TileEntitySkull)) {
							TileEntitySkull teS = (TileEntitySkull) teUp;
							if(teS.getBlockMetadata() == 1) {
								TileEntity te = world.getTileEntity(x, y, z);
								if((te != null) && (te instanceof TileEntityOccultEngine)) {
									((TileEntityOccultEngine)te).updateCurrentIdol(b.getUnlocalizedName());
									return;
								}
							}
						}
					} else {
						//Every other block.
						TileEntity te = world.getTileEntity(x, y, z);
						if((te != null) && (te instanceof TileEntityOccultEngine)) {
							((TileEntityOccultEngine)te).updateCurrentIdol(b.getUnlocalizedName());
						}
					}
				}
			} if((b == null) || (b == Blocks.air)) {
				TileEntity te = world.getTileEntity(x, y, z);
				if((te != null) && (te instanceof TileEntityOccultEngine)) {
					((TileEntityOccultEngine)te).updateCurrentIdol(null);
				}
				
			}
		} else {
			TileEntity te = world.getTileEntity(x, y, z);
			if((te != null) && (te instanceof TileEntityOccultEngine)) {
				((TileEntityOccultEngine)te).updateCurrentIdol(null);
			}
		}
	}

	public BlockOccultEngine(String name, Material material) {
		super(name, material);
	}
}
