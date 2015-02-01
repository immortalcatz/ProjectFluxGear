package mortvana.projectfluxgear.unmodulized.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;

import mortvana.projectfluxgear.unmodulized.block.itemblock.ItemBlockTemporalPylon;
import mortvana.projectfluxgear.unmodulized.block.tileentity.TileTemporalPylon;
import mortvana.projectfluxgear.unmodulized.common.FluxGearContent;

public class BlockTemporalPylon extends Block implements ITileEntityProvider {

	public BlockTemporalPylon() {
		super(Material.rock);
		setBlockName("temporalPylon").setLightLevel(0.75F);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileTemporalPylon) {
				((TileTemporalPylon) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileTemporalPylon) {
				((TileTemporalPylon) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
			}
		}
		super.onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile == null || !(tile instanceof TileTemporalPylon)) {
				return false;
			}
			TileTemporalPylon pylon = (TileTemporalPylon) tile;
			pylon.changeMode(player.isSneaking());
			if (player.isSneaking()) {
				player.addChatComponentMessage(new ChatComponentText("Changed speed: " + pylon.getSpeedDescription()));
			} else {
				player.addChatComponentMessage(new ChatComponentText("Changed mode: " + pylon.getModeDescription()));
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		for (int i = 0; i < NAMES.length; i++) {
			this.blockIcon = ir.registerIcon("projectfluxgear:" + StringHelper.camelCase(NAMES[i]));
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileTemporalPylon();
	}

	public void preInit() {
		GameRegistry.registerBlock(this, ItemBlockTemporalPylon.class, "TemporalPylon");

		FluxGearContent.temporalPylon = new ItemStack(this, 1, 0);
	}

	public static final String[] NAMES = {"temporalPylon"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
}
