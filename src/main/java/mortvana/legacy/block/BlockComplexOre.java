package mortvana.legacy.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.util.MTCreativeTab;

public class BlockComplexOre extends Block {

	public IIcon[] icons;
	public static String[] textureNames = new String[] {"bauxite_ore", "monazite_ore", "chalcocite_ore", "millerite_ore", "bornite_ore", "limonite_ore", "magnetite_ore", "hematite_ore", "pyrolusite_ore", "molybdenite_ore", "cooprite_ore", "ilmenite_ore", "tetrahedrite_ore", "tennatite_ore", "pentalandite_ore", "nierdermayrite_ore"};
	
	public BlockComplexOre() {
		
		super(Material.rock);
		setHardness(3.0F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("ComplexOre");
		setCreativeTab(MTCreativeTab.tab);
		setResistance(5.0F);
		GameRegistry.registerBlock(this, "ComplexOre");
	}

	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta) {
        return icons[meta];
    }
	
    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[textureNames.length/*16*/];
		
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("MortTech:" + textureNames[i]);
			//icons[i] = par1IconRegister.registerIcon(getUnlocalizedName().substring(5) + i);
		}
	}

    @Override
    public int damageDropped (int meta) {
        return meta;
    }
    
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < icons.length; i++)
		par3List.add(new ItemStack(par1, 1, i));
	}
}