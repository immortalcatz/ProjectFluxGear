package mortvana.legacy.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.FluxGearContent;

public class BlockMortTechOre extends Block {

	public IIcon[] icons;
	public String[] textures;

	public BlockMortTechOre(String name, String... textures) {
		super(Material.rock);
		this.textures = textures;
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName(name);
		setCreativeTab(FluxGearContent.tabWorld);
		setResistance(5.0F);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int side, int meta) {
		return icons[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[textures.length];

		for (int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("MortTech:" + textures[i]);
			//icons[i] = par1IconRegister.registerIcon(getUnlocalizedName().substring(5) + i);
		}

		blockIcon = iconRegister.registerIcon("MortTech:" + getUnlocalizedName().substring(5));
	}

	@Override
	public int damageDropped (int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
}
