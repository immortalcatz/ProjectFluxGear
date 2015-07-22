package mortvana.legacy.util.block.subtile;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import mortvana.legacy.common.ProjectFluxGear;

/**
 * A singleton instance for a SubtileEntity, this is called for a few methods.
 */
public abstract class SubtileSignature {
	public static final String SPECIAL_MACHINE_PREFIX = "machine.";
	public static final String SPECIAL_DYNAMO_PREFIX = "dynamo.";
	public static final String SPECIAL_COMPONENT_PREFIX = "component.";

	/**
	 * Equivalent to Block.registerBlockIcons.
	 */
	public abstract void registerIcons(IIconRegister register);

	/**
	 * Gets the icon to display for the flower item.
	 */
	public abstract IIcon getIconForStack(ItemStack stack);

	/**
	 * Gets the display name for the flower item.
	 */
	public abstract String getUnlocalizedNameForStack(ItemStack stack);

	/**
	 * Gets the lore text for the flower item, displayed in the item's tooltip.
	 * If you do not want a reference return a key that does not have localization such
	 * as "fluxgear.misc.noloc".
	 */
	public abstract String getUnlocalizedLoreTextForStack(ItemStack stack);

	/**
	 * Adds additional text to the tooltip. This text is added after getUnlocalizedLoreTextForStack.
	 */
	public void addTooltip(ItemStack stack, EntityPlayer player, List<String> tooltip) {}

	public static class BasicSignature extends SubtileSignature {

		public final String name;

		public BasicSignature(String name) {
			this.name = name;
		}

		@Override
		public void registerIcons(IIconRegister register) {
			ProjectFluxGear.handler.registerBasicSignatureIcons(name, register);
		}

		@Override
		public IIcon getIconForStack(ItemStack stack) {
			return ProjectFluxGear.handler.getSubTileIconForName(name);
		}

		@Override
		public String getUnlocalizedNameForStack(ItemStack stack) {
			return unlocalizedName("");
		}

		@Override
		public String getUnlocalizedLoreTextForStack(ItemStack stack) {
			return unlocalizedName(".lore");
		}

		public String unlocalizedName(String ending) {
			return "tile.fluxgear:mechanism" + name + ending;
		}
	}

}
