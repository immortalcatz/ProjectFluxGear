package mortvana.legacy.refactored.crystalclimate.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.errored.crystalclimate.common.CrystalClimate;
import mortvana.legacy.errored.crystalclimate.block.tileentity.RedstoneAggregator;
import mortvana.melteddashboard.util.helpers.ChatHelper;

public class Aggregator extends BlockContainer {
	public String[] textureNames = { "redstone" };
	public IIcon[] icons;

	public Aggregator() {
		super(Material.iron);
		setCreativeTab(CrystalClimate.tab);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.icons = new IIcon[textureNames.length];

		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = iconRegister.registerIcon("crystal:aggregator_" + textureNames[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[0];
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new RedstoneAggregator();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
		if (!player.worldObj.isRemote) {
			RedstoneAggregator logic = (RedstoneAggregator) world.getTileEntity(x, y, z);
			float value = logic.getCrystalValue() / 10f;

			ChatHelper.addChatMessage(player, StatCollector.translateToLocal("tooltip.crystalvalue") + ": " + value);
		}
		return true;
	}
}
