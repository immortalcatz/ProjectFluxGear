package mortvana.fluxgearcore.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.fluxgearcore.util.helper.SecurityHelper;
import mortvana.fluxgearcore.util.helper.StringHelper;

public class ItemBlockFluxGear extends ItemBlock {

	public ItemBlockFluxGear (Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	public ItemBlockFluxGear (Block block, boolean subTypes, int maxDamage) {
		super(block);
		setHasSubtypes(subTypes);
		setMaxDamage(maxDamage);
	}

	public String getItemStackDisplayName(ItemStack item) {
		return StringHelper.localize(this.getUnlocalizedName(item));
	}

	@Override
	public int getMetadata(int i) {

		return i;
	}

	public boolean hasCustomEntity(ItemStack itemstack) {
		return SecurityHelper.isSecure(itemstack);
	}

	public boolean isItemTool(ItemStack itemstack) {
		return false;
	}

	public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
		if(SecurityHelper.isSecure(itemstack)) {
			entity.invulnerable = true;
			entity.isImmuneToFire = true;
			((EntityItem)entity).lifespan = 2147483647;
		}
		return null;
	}
}
