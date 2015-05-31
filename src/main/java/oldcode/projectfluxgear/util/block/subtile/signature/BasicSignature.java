package oldcode.projectfluxgear.util.block.subtile.signature;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import oldcode.projectfluxgear.core.common.ProjectFluxGear;

public class BasicSignature extends SubtileSignature {

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
