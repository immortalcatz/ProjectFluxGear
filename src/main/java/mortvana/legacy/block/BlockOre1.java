package mortvana.legacy.block;

import java.util.List;

import mortvana.legacy.util.MTCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOre1 extends Block {
	
	public static int assignedBlockID;
	public IIcon[] icons;
	public static String[] textureNames = new String[] {"chalcopyrite_ore", "cassiterite_ore"};
	
	public BlockOre1() {
		
		super(Material.rock);
		setHardness(3.0F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("BasicOre");
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
	public void registerIcons(IIconRegister iconRegister){
		icons = new IIcon[textureNames.length];
		
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("MortTech:" + textureNames[i] + "_ore");
		}
	}

    @Override
    public int damageDropped (int meta){
        return meta;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}