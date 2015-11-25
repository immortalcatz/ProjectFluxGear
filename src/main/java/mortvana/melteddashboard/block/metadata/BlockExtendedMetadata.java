package mortvana.melteddashboard.block.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.ParticleHelper;
import mortvana.melteddashboard.util.helpers.ServerHelper;

public abstract class BlockExtendedMetadata extends BlockContainerMetadata {

	public HashMap<Integer, Integer> harvestLevels = new HashMap<Integer, Integer>();
	public HashMap<Integer, String> harvestTools = new HashMap<Integer, String>();
	public static final int WILD = MeltedDashboardCore.WILDCARD;


	public BlockExtendedMetadata(Material material) {
		super(material);
	}

	public BlockExtendedMetadata(Material material, CreativeTabs tab) {
		super(material, tab);
	}

	public BlockExtendedMetadata(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material, tab, hardness, resistance);
	}

	public int getPlacedMetadata(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side, float xHit, float yHit, float zHit) {
		return stack.getMetadata();
	}

	public boolean shouldDropItems(World world, int x, int y, int z, int meta, EntityPlayer player, ItemStack stack) {
		return ForgeHooks.canHarvestBlock(this, player, meta);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return getMetadata(world, x, y, z);
	}

	@Override
	public int getHarvestLevel(int meta) {
		if (harvestLevels.containsKey(meta)) {
			return harvestLevels.get(meta);
		} else if (harvestLevels.containsKey(WILD)) {
			return harvestLevels.get(WILD);
		} else {
			return 0;
		}
	}

	@Override
	public String getHarvestTool(int meta) {
		if (harvestTools.containsKey(meta)) {
			return harvestTools.get(meta);
		} else if (harvestTools.containsKey(WILD)) {
			return harvestTools.get(WILD);
		} else {
			return "";
		}
	}

	@Override
	public boolean isToolEffective(String type, int meta) {
		return harvestTools.containsKey(meta) ? harvestTools.get(meta).equals(type) : (harvestTools.containsKey(WILD) && harvestTools.get(WILD).equals(type));
	}

	@Override
	public void setHarvestLevel(String tool, int level) {
		harvestLevels.put(WILD, level);
		harvestTools.put(WILD, tool);
	}

	@Override
	public void setHarvestLevel(String tool, int level, int meta) {
		harvestLevels.put(meta, level);
		harvestTools.put(meta, tool);
	}

	public int getMetadata(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile instanceof TileEntityMetadata ? ((TileEntityMetadata) tile).getTileMetadata() : world.getBlockMetadata(x, y, z);
	}

	public void setMetadata(World world, int x, int y, int z, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityMetadata) {
			((TileEntityMetadata) tile).setTileMetadata(meta);
		}
	}

	public void setMetadata(TileEntity tile, int meta) {
		if (tile instanceof TileEntityMetadata) {
			((TileEntityMetadata) tile).setTileMetadata(meta);
		}
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		int metadata = getMetadata(world, x, y, z);
		float hardness = getBlockHardness(world, x, y, z);

		if (hardness < 0.0F ) {
			return 0.0F;
		} else if (ForgeHooks.canHarvestBlock(this, player, metadata)) {
			return player.getBreakSpeed(this, false, metadata, x, y, z) / hardness / 30F;
		} else {
			return player.getBreakSpeed(this, true, metadata, x, y, z) / hardness / 100F;
		}
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile != null && !tile.hasDropped) {
			drops = world.getBlock(x, y, z).getDrops(world, x, y, z, tile.getTileMetadata(), EnchantmentHelper.getFortuneModifier(player));
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if (hasBeenBroken && ServerHelper.isServerWorld(world)) {
			if (drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode) && ForgeHooks.canHarvestBlock(this, player, tile.getTileMetadata())) {
				for (ItemStack drop : drops) {
					if (world.rand.nextFloat() <= ForgeEventFactory.fireBlockHarvesting(drops, world, this, x, y, z, tile.getTileMetadata(), EnchantmentHelper.getFortuneModifier(player), 1.0F, false, player)) {
						dropBlockAsItem(world, x, y, z, drop);
					}
				}
			}
			tile.hasDropped = true;
		}
		return hasBeenBroken;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile != null && !tile.hasDropped) {
			super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>(); // Have some Retcon, courtesy of Captain Jack Harkness.
		int count = quantityDropped(metadata, fortune, world.rand);
		Item item = getItemDropped(metadata, world.rand, fortune);
		if (item != null) {
			stacks.add(new ItemStack(item, count, metadata));
		}
		//TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);
		//if (tile != null && !tile.hasDroppedBlock()) {
		//	stacks.add(TileEntityMetadata.getItemStack((Block) block, tile.getTileMetadata()));
		//}
		return stacks;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		List<ItemStack> list = getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		return list.isEmpty() ? null : list.get(0);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer renderer) {
		return ParticleHelper.addBlockDestroyEffects(world, x, y, z, meta, renderer, this, getMetadata(world, x, y, z), 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer renderer) {
		return ParticleHelper.addBlockHitEffects(world, target, renderer, getMetadata(world, target.blockX, target.blockY, target.blockZ), 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return getIcon(side, getMetadata(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
	}

	//Tile Entity
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMetadata();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityMetadata.class;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
}
