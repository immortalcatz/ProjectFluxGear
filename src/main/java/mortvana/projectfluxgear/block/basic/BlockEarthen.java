package mortvana.projectfluxgear.block.basic;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.block.BlockFluxGear;
import mortvana.fluxgearcore.block.EnumBlockType;
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockEarthen extends BlockFluxGear {
	public BlockEarthen() {
		super(Material.clay, ProjectFluxGear.tabResources, EnumBlockType.SOIL_ORE);
		setBlockName("projectfluxgear.earthen");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return TEXTURES[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < NAMES.length; i++) {
			TEXTURES[i] = ir.registerIcon("projectfluxgear:ore/" + StringHelper.titleCase(NAMES[i]));
		}
	}

	public static final String[] NAMES = {"claysandsIridium", "claysandsIridiumPoor", "aluminosilicateSludge"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] RARITY = { 1, 0, 0 };

}
