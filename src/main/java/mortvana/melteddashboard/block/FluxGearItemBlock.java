package mortvana.melteddashboard.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FluxGearItemBlock extends ItemBlock {

	public String unlocalizedName;
	public String[] names;
	public int[] rarities;

	public FluxGearItemBlock(Block block, String[] names, int[] rarities, String unlocalizedName) {
		super(block);
		setHasSubtypes(true);
		setMaxDurability(0);
		this.unlocalizedName = unlocalizedName;
		this.names = names;
		this.rarities = rarities;
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return unlocalizedName + names[itemstack.getMetadata()] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.values()[rarities[itemstack.getMetadata()]];
	}

	@Override
	public boolean isItemTool(ItemStack itemstack) {
		return false;
	}

	//TODO: Todo?
	/*
	public boolean hasCustomEntity(ItemStack itemstack) {
		return SecurityHelper.isSecure(itemstack);
	}

	public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
		if (SecurityHelper.isSecure(itemstack)) {
			entity.invulnerable = true;
			entity.isImmuneToFire = true;
			((EntityItem)entity).lifespan = 2147483647;
		}
		return null;
	}

	public String getItemStackDisplayName(ItemStack item) {
		return StringHelper_.localize(this.getUnlocalizedName(item));
	}
	*/
}
