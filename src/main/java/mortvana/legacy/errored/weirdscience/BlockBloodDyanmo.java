package mortvana.legacy.errored.weirdscience;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import mortvana.legacy.errored.core.ProjectFluxGear;
import mortvana.legacy.clean.weirdscience.block.tile.TileEntityBloodDynamo;
import mortvana.legacy.clean.weirdscience.util.block.tile.BlockMetaTank;
import tconstruct.smeltery.TinkerSmeltery;

public class BlockBloodDyanmo extends BlockMetaTank {

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBloodDynamo();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3) {
		TileEntityBloodDynamo tileEntity = null;
		if (world.getTileEntity(x, y, z) instanceof TileEntityBloodDynamo) {
			tileEntity = (TileEntityBloodDynamo) world.getTileEntity(x, y, z);
		}
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		ItemStack playerItem = player.inventory.getCurrentItem();

		//Check to see if the player is holding something that can be filled with a fluid.
		if (playerItem != null) {
			//Is the player's item THE ONE TRUE ITEM we're lookin' for?
			//First try the Zettabyte version.
			if (playerItem.getItem() instanceof ItemBucket) {
				//Do deep black Worst Practices hoodoo because Notch didn't design anything to be extended or generalized
				//Lucky for us the performance impact of reflection is negligable in this case since there won't be
				//instances of right clicking on an engine with a bucket very often.
				Block dumpedBlock = ((ItemBucket) playerItem.getItem()).isFull;
				/*try {
					Field f = ItemBucket.class.getDeclaredField("isFull"); //NoSuchFieldException if there is no isFull
					f.setAccessible(true);
					dumpedBlock = (Block) f.get(bucket);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				//Does this bucket contain a fluid block?
				if (dumpedBlock instanceof IFluidBlock) {
					Fluid fluidTry = ((IFluidBlock)dumpedBlock).getFluid();
					//Is it blood?
					if (fluidTry.getName().contentEquals("blood")) {
						if(fillTank(fluidTry, tileEntity) && !player.capabilities.isCreativeMode) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.EMPTY_BUCKET);
						}
						return true;
					}
				}
				//Now that we've tried one wonky kind of reflection, try another for TCon compat.
				if (playerItem.getItem().getUnlocalizedName().contentEquals("item.tconstruct.bucket")) {
					//Deeper voodoo begins here.
					Block[] fluidBlockArray = TinkerSmeltery.fluidBlocks;
					/*try {
						Class tcontent = Class.forName("tconstruct.common.TContent");
						Field f = tcontent.getDeclaredField("fluidBlocks");
						fluidBlockArray = (Block[]) f.get(null);
					} catch (Exception e) {
						//do nothing, all this means is that Tinker's Construct is not loaded.
					}*/
					if (fluidBlockArray[playerItem.getMetadata()].getUnlocalizedName().contentEquals("tile.liquid.blood")) {
						if (fillTank(FluidRegistry.getFluid("blood"), tileEntity) && !player.capabilities.isCreativeMode) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.EMPTY_BUCKET);
						}
						return true;
					}
				}
			}
		}
		//TODO: GUIs
		//The player is not holding a blood bucket.
		player.openGui(ProjectFluxGear.instance, guiID, world, x, y, z);
		return true;
		return false;
	}

	public boolean fillTank(Fluid blood, TileEntityBloodDynamo tile) {
		//Get the amount of fluid we're trying to put in the TE.
		FluidStack fluid = new FluidStack(blood, 1000);
		//First simulate the filling to make sure we can fit a whole bucket of blood in the engine.
		if (tile.fill(ForgeDirection.UP, fluid, false) == 1000) {
			//Then, do it for real.
			tile.fill(ForgeDirection.UP, fluid, true);
			//If they are not god they do not get infinite blood bucket.
			return true;
		}
		return false;
	}

	public BlockBloodDyanmo(String name, Material material) {
		super(name, material);
	}

	public BlockBloodDyanmo(String name) {
		super(name);
	}
}