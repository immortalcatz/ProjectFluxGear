package oldcode.legacy.block;

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

import oldcode.legacy.util.MTCreativeTab;

public class BlockGemOre extends Block {

	public IIcon[] icons;
	public static String[] textureNames = new String[] {"dioptase_ore", "ruby_ore", "sapphire_ore", "green_sapphire_ore", "pink_sapphire_ore", "purple_sapphire_ore", "topaz_ore", "tanzanite_ore", "pyrope_ore", "malachite_ore", "olivine_ore", "super_sekrit_ore"};
	
	public BlockGemOre() {
		
		super(Material.rock);
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("GemOre");
		setCreativeTab(MTCreativeTab.tab);
		setResistance(5.0F);
		GameRegistry.registerBlock(this, "GemOre");
		
	}
	
	/*public int idDropped(int metadata, Random random, int fortune) {
	  return Item.ingotIron.itemID;
	  }
	*/
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta) {
        return icons[meta];
    }

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[textureNames.length];
		
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("MortTech:" + textureNames[i]);
		}
	}

    @Override
    public int damageDropped (int meta) {
        return meta;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < textureNames.length /*12*/; i++)
		par3List.add(new ItemStack(item, 1, i));
	}
}