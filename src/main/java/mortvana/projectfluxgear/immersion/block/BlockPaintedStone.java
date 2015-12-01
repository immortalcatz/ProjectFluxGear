package mortvana.projectfluxgear.immersion.block;

import java.util.List;
import java.util.Random;

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

import mortvana.melteddashboard.registry.wrapped.RegistationWrapper;
import mortvana.melteddashboard.util.helpers.ColorHelper;
import mortvana.projectfluxgear.immersion.block.itemblock.ItemBlockPaintedStone;
import mortvana.projectfluxgear.immersion.common.FluxGearImmersion;

public class BlockPaintedStone extends Block {
	public final String textureName;
	public final String localName;
	public IIcon[] icons = new IIcon[16];
	public Item dropItem;

	public BlockPaintedStone(Material material, float hardness, String texture, String name, Block dropBlock, String blockName) {
		super(material);
		setHardness(hardness);
		setCreativeTab(FluxGearImmersion.paintedStoneTab);
		textureName = texture;
		localName = name;
		dropItem = Item.getItemFromBlock(dropBlock);
		setUnlocalizedName(blockName);
		GameRegistry.registerBlock(this, ItemBlockPaintedStone.class, blockName);
		RegistationWrapper.registerFMP(this, 16);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + localName;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	public Item itemDropped(Item item, Random random, int par3) {
		return dropItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[16];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon("fluxgear:painted/" + textureName + "_" + ColorHelper.LOWER_PAINT_NAMES[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(block, 1, i));
		}

	}

	public BlockPaintedStone addOreDict(String oreDict) {
		for (int i = 0; i < icons.length; i++) {
			RegistationWrapper.registerOreDict(new ItemStack(this, 1, i), oreDict);
		}
		return this;
	}

	public BlockPaintedStone addSmelting(Block output) {
		for (int i = 0; i < icons.length; i++) {
			RegistationWrapper.addSmelting(new ItemStack(this, 1, i), new ItemStack(output, 1, i), 0.2F);
		}
		return this;
	}

	public BlockPaintedStone addCubingRecipe(Block input) {
		for (int i = 0; i < icons.length; i++) {
			RegistationWrapper.registerSquareRecipe(new ItemStack(this, 1, i), new ItemStack(input, 4, i));
		}
		return this;
	}
}