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
import mortvana.legacy.common.ProjectFluxGear;

public class BlockBasicOre extends Block {

	public IIcon[] icons;
	public static String[] textureNames = new String[] {"chalcopyrite_ore", "cassiterite_ore", "argentite_ore", "galena_ore", "sphalerite_ore", "bismuthinite_ore", "garnierite_ore", "chromite_ore", "cobaltite_ore", "wolframite_ore"};
	
	public BlockBasicOre() {
		
		super(/*assignedBlockID, */Material.rock);
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("BasicOre");
		setCreativeTab(MTCreativeTab.tab);
		setResistance(5.0F);
		GameRegistry.registerBlock(this, "BasicOre");
	}

	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        return icons[meta];
    }
	
    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[textureNames.length/*9*/];
		
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = iconRegister.registerIcon("MortTech:" + textureNames[i]);
			//icons[i] = par1IconRegister.registerIcon(getUnlocalizedName().substring(5) + i);
		}

		this.blockIcon = iconRegister.registerIcon(ProjectFluxGear.modid + ":" + this.getUnlocalizedName().substring(5));
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