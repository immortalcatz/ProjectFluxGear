package mortvana.melteddashboard.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;

import mortvana.melteddashboard.util.vecmath.CoordSet;
import mortvana.melteddashboard.util.handlers.BucketHandler;

public class FluxGearFluidContainer extends FluxGearItem {

	public Item container;
	public boolean canPlace = true;
	public boolean canPickup = true;

	public FluxGearFluidContainer(String modName, CreativeTabs tab, Item container) {
		this(container);
		this.modName = modName;
		setCreativeTab(tab);
	}

	public FluxGearFluidContainer(String modName, CreativeTabs tab) {
		this(modName);
		setCreativeTab(tab);
	}

	public FluxGearFluidContainer(String modName) {
		this(Items.bucket);
		this.modName = modName;
	}

	public FluxGearFluidContainer(Item container) {
		super();
		setMaxStackSize(1);
		this.container = container;
		setContainerItem(container);
	}

	public FluxGearFluidContainer setFluidInteraction(boolean canPlace, boolean canPickup) {
		this.canPlace = canPlace;
		this.canPickup = canPickup;
		return this;
	}

	public void registerFluidContainer(int metadata, String name, int rarity, Fluid fluid) {
		ItemStack container = addItem(metadata, name, rarity);
		BucketHandler.registerBucket(container, new ItemStack(this.container), fluid, fluid.getBlock(), 0);
		FluidContainerRegistry.registerFluidContainer(fluid, container, new ItemStack(this.container));
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, false);
		if (position != null && position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			CoordSet coords = new CoordSet(position.blockX, position.blockY, position.blockZ).offsetCoordsBySide(position.sideHit);
			return BucketHandler.emptyContainer(world, coords.getX(), coords.getY(), coords.getZ(), position.sideHit, itemstack, player, canPlace);
		}
		return itemstack;
	}
}


