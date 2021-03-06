package mortvana.legacy.clean.weirdscience.util.handlers;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import org.apache.commons.lang3.tuple.ImmutablePair;

@Deprecated
public class BucketEventHandler {
	protected Map<ImmutablePair<Block, Integer>, ItemStack> fluidToBucket;
	
	public BucketEventHandler() {
		fluidToBucket = new HashMap<ImmutablePair<Block, Integer>, ItemStack>();
	}
	
	public boolean addRecipe(Block sourceBlock, int meta, ItemStack bucket) {
		if((sourceBlock == null) || (fluidToBucket.containsKey(new ImmutablePair<Block, Integer>(sourceBlock, meta)))) {
			return false;
		} else {
			fluidToBucket.put(new ImmutablePair<Block, Integer>(sourceBlock, meta), bucket);
			return true;
		}
	}

	@SubscribeEvent
	public void bucketFill(FillBucketEvent event) {
		if(!event.world.isRemote){ //Prevent stupid shit in single player.
			MovingObjectPosition target = event.target;
			//Have we clicked a tile with an empty bucket?
			if (event.current.getItem() == Items.bucket && event.target.typeOfHit == MovingObjectType.BLOCK) {
				//Is there an entry for this block's ID?
				ImmutablePair<Block, Integer> iPair = new ImmutablePair<Block, Integer>(event.world.getBlock(target.blockX, target.blockY, target.blockZ), event.world.getBlockMetadata(target.blockX, target.blockY, target.blockZ));
				if(fluidToBucket.get(iPair) != null) {
					//Set our event's item to our fluid.
					event.result = fluidToBucket.get(iPair).copy();
					//Allow this to happen.
					event.setResult(Result.ALLOW);
					//Set the block to 0 so we don't just have infinite liquid.
					event.world.setBlock(target.blockX, target.blockY, target.blockZ, Blocks.air);
				} //Nothing we recognize.
			} //Not an acceptable event.
		}
	}
}
