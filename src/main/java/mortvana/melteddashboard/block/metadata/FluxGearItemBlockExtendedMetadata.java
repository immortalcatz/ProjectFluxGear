package mortvana.melteddashboard.block.metadata;

import java.util.Map;

import gnu.trove.map.hash.THashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.melteddashboard.util.helpers.SecurityHelper;
import mortvana.melteddashboard.util.helpers.StringHelper;

public class FluxGearItemBlockExtendedMetadata extends ItemBlockMetadata {

	public Map<Integer, String> names;
	public Map<Integer, Integer> rarities;

	public FluxGearItemBlockExtendedMetadata(Block block, Map<Integer, String> names, Map<Integer, Integer> rarities) {
		super(block);
		setHasSubtypes(true);
		setMaxDurability(0);
		this.names = names;
		this.rarities = rarities;
	}

	/**
	 *  Dummy version of the constructor so FML stop complaining about illogical shit...
	 *  A THashMap is a Map, and the arguments are CORRECT, okay FML.
	 *  ONLY SUPPLY MAPS THAT ARE ACTUALLY CORRECT.
	 */
	@SuppressWarnings("unchecked")
	public FluxGearItemBlockExtendedMetadata(Block block, THashMap names, THashMap rarities) {
		this(block, (Map<Integer, String>) names, (Map<Integer, Integer>) rarities);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return super.getUnlocalizedName() + "." + names.get(itemstack.getMetadata());
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.values()[rarities.get(itemstack.getMetadata())];
	}

	@Override
	public boolean isItemTool(ItemStack itemstack) {
		return false;
	}

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
		return StringHelper.localize(this.getUnlocalizedName(item));
	}
}
