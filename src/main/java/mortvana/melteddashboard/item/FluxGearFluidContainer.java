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

import mortvana.melteddashboard.util.CoordSet;


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
		this();
		this.modName = modName;
	}

	public FluxGearFluidContainer(Item container) {
		super();
		setMaxStackSize(1);
		this.container = container;
		setContainerItem(container);
	}

	public FluxGearFluidContainer() {
		this(Items.bucket);
	}

	public FluxGearFluidContainer setFluidInteraction(boolean canPlace, boolean canPickup) {
		this.canPlace = canPlace;
		this.canPickup = canPickup;
		return this;
	}

	public void registerFluidContainer(int metadata, String name, int rarity, Fluid fluid) {
		ItemStack container = addItem(metadata, name, rarity);
		//BucketHandler.registerBucket(fluid, 0, container);
		FluidContainerRegistry.registerFluidContainer(fluid, container, new ItemStack(this.container));
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, false);
		if (position != null && position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			CoordSet coords = new CoordSet(position.blockX, position.blockY, position.blockZ).offsetCoordsBySide(position.sideHit);
			int x = coords.getX();
			int y = coords.getY();
			int z = coords.getZ();

			//if (!(!player.canPlayerEdit(x, y, z, position.sideHit, itemstack) || !world.isAirBlock(x, y, z) && world.getBlock(x, y, z).getMaterial().isSolid()) && (BucketHandler.emptyBucket(world, x, y, z, itemstack) && !player.capabilities.isCreativeMode) ) {
			//	return new ItemStack(container);
			//}
		}
		return itemstack;
	}
}
