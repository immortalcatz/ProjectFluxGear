package oldcode.legacy.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

import oldcode.legacy.common.FluxGearContent;
import oldcode.legacy.util.item.ItemFluxGear;
import oldcode.legacy.common.ProjectFluxGear;

public class ItemInteractivePFG extends ItemFluxGear {

	public static Block congealedBlock = FluxGearContent.blockCongealedBlood;

	public ItemInteractivePFG() {
		super("projectfluxgear");
		setCreativeTab(ProjectFluxGear.tabMaterials);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack heldStack, World currentWorld, EntityPlayer clickingPlayer) {
		// Get the object the user clicked on (raytrace) - the last 'flag' parameter controls whether or not to trace through transparent objects, with 'true' meaning it won't
		MovingObjectPosition clickedObject = getMovingObjectPositionFromPlayer(currentWorld, clickingPlayer, true);
		int x = clickedObject.blockX, y = clickedObject.blockY, z = clickedObject.blockZ;

		// Is the item Coagulant? Is the clicked object not null? Did the user click on a block? Can the player edit the block? Is the block not air? Is the block a fluid? If all are true, continue. Otherwise, do nothing.
		if ((heldStack.getItemDamage() == 1) && /*(clickedObject != null) && --Always true*/ (clickedObject.typeOfHit == MovingObjectType.BLOCK) && (clickingPlayer.canPlayerEdit(x, y, z, clickedObject.sideHit, heldStack)) && ((currentWorld.getBlock(x, y, z) != Blocks.air) && (currentWorld.getBlock(x, y, z) instanceof IFluidBlock))) {
			// If it's fluid blood, make it congealed and remove a coagulant
			IFluidBlock fluidBlock = (IFluidBlock) currentWorld.getBlock(x, y, z);
			if (fluidBlock.getFluid().getName().contentEquals("blood")) {
				//TODO: Make actually this do something
				currentWorld.setBlock(x, y, z, congealedBlock);
				if (!clickingPlayer.capabilities.isCreativeMode) {
					--heldStack.stackSize;
				}
			}
		}
		return heldStack;
	}
}
