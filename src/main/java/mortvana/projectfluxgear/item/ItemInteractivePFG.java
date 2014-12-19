package mortvana.projectfluxgear.item;

import mortvana.fluxgearcore.item.ItemFluxGear;
import mortvana.projectfluxgear.common.ProjectFluxGear;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.IFluidBlock;
import mortvana.fluxgearcore.legacy.item.ItemBase;

public class ItemInteractivePFG extends ItemFluxGear {

	public static Block congealedBlock = null;

	public ItemInteractivePFG() {
		super("projectfluxgear");
		setCreativeTab(ProjectFluxGear.tabResources);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack heldStack, World currentWorld, EntityPlayer clickingPlayer) {
		// Get the object the user clicked on (raytrace) - the last 'flag' parameter controls whether or not to trace through transparent objects, with 'true' meaning it won't
		MovingObjectPosition clickedObject = this.getMovingObjectPositionFromPlayer(currentWorld, clickingPlayer, true);

		// If no object was within the click-range, just return normally
		if (heldStack.getItemDamage() == 0)
			if (clickedObject == null) {
				return heldStack;
			} else {
				// If the user clicked on a tile of some sort
				if (clickedObject.typeOfHit == MovingObjectType.BLOCK) {
					int x = clickedObject.blockX, y = clickedObject.blockY, z = clickedObject.blockZ;

					// Make sure the player has permission to edit the clicked-on block, otherwise just cancel this
					if (!clickingPlayer.canPlayerEdit(x, y, z, clickedObject.sideHit, heldStack)) {
						return heldStack;
					}
					// If it's fluid blood, make it congealed and remove a coagulant
					if ((currentWorld.getBlock(x, y, z) != Blocks.air) && (currentWorld.getBlock(x, y, z) instanceof IFluidBlock)) {
						IFluidBlock fluidBlock = (IFluidBlock) currentWorld.getBlock(x, y, z);
						if (fluidBlock.getFluid().getName().contentEquals("blood")) {
							//TODO: Make this do something
							currentWorld.setBlock(x, y, z, congealedBlock);
							if (!clickingPlayer.capabilities.isCreativeMode) {
								--heldStack.stackSize;
							}
						}
					}
				return heldStack;
			} else {
				return heldStack;
			}
		}
	return heldStack;
	}
}
