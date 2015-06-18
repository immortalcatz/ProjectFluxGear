package mortvana.projectfluxgear.util.block.metadata;

import java.util.ArrayList;
import java.util.HashMap;

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

import cofh.lib.util.helpers.ServerHelper;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.util.helpers.ParticleHelper;

public abstract class BlockExtendedMetadata extends BlockContainerMetadata {

	public HashMap<Integer, Integer> harvestLevels = new HashMap<Integer, Integer>();
	public HashMap<Integer, String> harvestTools = new HashMap<Integer, String>();
	final int WILDCARD = ProjectFluxGear.WILDCARD;


	public BlockExtendedMetadata(Material material) {
		super(material);
	}

	public BlockExtendedMetadata(Material material, CreativeTabs tab) {
		super(material, tab);
	}

	public BlockExtendedMetadata(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material, tab, hardness, resistance);
	}

	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityMetadata.class;
	}

	public int getPlacedMetadata(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side, float xHit, float yHit, float zHit) {
		return stack.getItemDamage();
	}

	public int getDroppedMetadata(World world, int x, int y, int z, int meta, int fortune) {
		return meta;
	}

	public boolean shouldDropItems(World world, int x, int y, int z, int meta, EntityPlayer player, ItemStack stack) {
		return ForgeHooks.canHarvestBlock(this, player, meta);
	}

	public int getDamageValue(World world, int x, int y, int z) {
		return getDroppedMetadata(world, x, y, z, getMetadata(world, x, y, z), 0);
	}

	public int getHarvestLevel(int meta) {
		if (harvestLevels.containsKey(meta)) {
			return harvestLevels.get(meta);
		} else if (harvestLevels.containsKey(WILDCARD)) {
			return harvestLevels.get(WILDCARD);
		} else {
			return 0;
		}
	}

	public String getHarvestTool(int meta) {
		if (harvestTools.containsKey(meta)) {
			return harvestTools.get(meta);
		} else if (harvestTools.containsKey(WILDCARD)) {
			return harvestTools.get(WILDCARD);
		} else {
			return "";
		}
	}

	public boolean isToolEffective(String type, int meta) {
		if (harvestTools.containsKey(meta)) {
			return harvestTools.get(meta).equals(type);
		} else if (harvestTools.containsKey(WILDCARD)) {
			return harvestTools.get(WILDCARD).equals(type);
		} else {
			return false;
		}
	}

	public void setHarvestLevel(String tool, int level) {
		harvestLevels.put(WILDCARD, level);
		harvestTools.put(WILDCARD, tool);
	}

	public void setHarvestLevel(String tool, int level, int meta) {
		harvestLevels.put(meta, level);
		harvestTools.put(meta, tool);
	}

	public int getMetadata(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityMetadata) {
			return ((TileEntityMetadata) tile).metadata;
		} else {
			return world.getBlockMetadata(x, y, z);
		}
	}

	public void setMetadata(World world, int x, int y, int z, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityMetadata) {
			((TileEntityMetadata) tile).setTileMetadata(meta);
		}
	}

	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		int metadata = getMetadata(world, x, y, z);
		float hardness = getBlockHardness(world, x, y, z);
		if (hardness < 0.0F) {
			return 0.0F;
		}
		if (!ForgeHooks.canHarvestBlock(this, player, metadata)) {
			return player.getBreakSpeed(this, true, metadata, x, y, z) / hardness / 100F;
		} else {
			return player.getBreakSpeed(this, false, metadata, x, y, z) / hardness / 30F;
		}
	}

	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile != null && !tile.hasDropped) {
			drops = world.getBlock(x, y, z).getDrops(world, x, y, z, tile.getTileMetadata(), EnchantmentHelper.getFortuneModifier(player));
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if (hasBeenBroken && ServerHelper.isServerWorld(world)) {
			if (drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode) && shouldDropItems(world, x, y, z, tile.getTileMetadata(), player, player.getCurrentEquippedItem())) {
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

	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile != null && !tile.hasDropped) {
			super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);
		}

	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>(); // Have some Retcon, courtesy of Captain Jack Harkness.
		int count = quantityDropped(metadata, fortune, world.rand);
		Item item = getItemDropped(metadata, world.rand, fortune);
		if (item != null) {
			stacks.add(new ItemStack(item, count, getDroppedMetadata(world, x, y, z, metadata, fortune)));
		}
		return stacks;
	}

	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer renderer) {
		return ParticleHelper.addBlockDestroyEffects(world, x, y, z, meta, renderer, this, getMetadata(world, x, y, z), 2);
	}

	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer renderer) {
		return ParticleHelper.addBlockHitEffects(world, target, renderer, getMetadata(world, target.blockX, target.blockY, target.blockZ), 2);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return getIcon(side, getMetadata(world, x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
	}
}
