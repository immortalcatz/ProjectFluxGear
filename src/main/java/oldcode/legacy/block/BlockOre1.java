package oldcode.legacy.block;

import java.util.List;

import morttech.morttech.common.MTCreativeTab;
import oldcode.legacy.util.MTCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOre1 extends Block {
	
	public static int assignedBlockID;
	public Icon[] icons;
	public static String[] textureNames = new String[] {"chalcopyrite_ore", "cassiterite_ore"};
	
	public BlockOre1() {
		
		super(assignedBlockID, Material.rock);
		setHardness(3.0F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("BasicOre");
		setCreativeTab(MTCreativeTab.tab);
		setResistance(5.0F);
		GameRegistry.registerBlock(this, "BasicOre");
	}

	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon (int side, int meta)
    {
        return icons[meta];
    }
	
	/*public int idDropped(int metadata, Random random, int fortune) {
	  return Item.ingotIron.itemID;
	  }
	*/
	
    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		icons = new Icon[textureNames.length];
		
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = iconRegister.registerIcon("MortTech:" + textureNames[i] + "_ore");
		}
	}

    @Override
    public int damageDropped (int meta)
    {
        return meta;
    }
    
    @Override
    
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}