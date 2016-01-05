package mortvana.legacy.dependent.seconddegree.crystalclimate.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.clean.crystalclimate.block.tile.TerraformerLogic;
import mortvana.legacy.clean.crystalclimate.block.tile.TerraleecherLogic;
import mortvana.legacy.dependent.firstdegree.crystalclimate.common.CrystalClimate;
import mortvana.legacy.dependent.seconddegree.crystalclimate.block.tile.*;

public class BlockTerraformer extends BlockContainer {
	public BlockTerraformer() {
		super(Material.iron);
		setCreativeTab(CrystalClimate.tab);
		setStepSound(Block.soundTypeMetal);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch (metadata) {
			case 3:
				return new TerraleecherLogic();
			case 4:
				return new TerragrowerLogic();
			default:
				return new TerraformerLogic();
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < textureNames.length - 1; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

    /* Rendering */

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	static String[] textureNames = {"crystal_machine_top", "terrafreezer", "terrafumer", "terrawaver", "terraleecher", "terragrower", "terranether", "terralighter", "terracrystal"};

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icons = new IIcon[textureNames.length];

		for (int i = 0; i < icons.length; i++) {
			this.icons[i] = iconRegister.registerIcon("crystal:" + textureNames[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if ((side == 0 || side == 1) && (meta != 0 && meta != 5)) {
			return icons[0];
		}
		return icons[meta + 1];
	}
}
