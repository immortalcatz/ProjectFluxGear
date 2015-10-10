package mortvana.melteddashboard.block.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.melteddashboard.util.enums.EnumTextCasing;

public class FluxGearBlockExtendedMetadata extends BlockExtendedMetadata {

	public List<Integer> metaBlocks = new ArrayList<Integer>();

	public TMap<Integer, String> textureNames = new THashMap<Integer, String>();
	public TMap<Integer, String> blockNames = new THashMap<Integer, String>();
	public TMap<Integer, Float> blockHardness = new THashMap<Integer, Float>();
	public TMap<Integer, Float> blastResistance = new THashMap<Integer, Float>();
	public TMap<Integer, Integer> itemRarity = new THashMap<Integer, Integer>();
	public TMap<Integer, Integer> blockLight = new THashMap<Integer, Integer>();
	public TMap<Integer, Integer> redstoneSignal = new THashMap<Integer, Integer>();
	public TMap<Integer, Boolean> beaconBase = new THashMap<Integer, Boolean>();
	public TMap<Integer, Boolean> mobSpawns = new THashMap<Integer, Boolean>();
	public TMap<Integer, Integer> colorData = new THashMap<Integer, Integer>();
	public TMap<Integer, IIcon> blockIcons = new THashMap<Integer, IIcon>();
	public TMap<Integer, ItemStack> stackMap = new THashMap<Integer, ItemStack>();

	public TileEntityMetadata providerTile;
	public String textureBase;
	public EnumTextCasing textCasing = EnumTextCasing.CAMEL;

	public FluxGearBlockExtendedMetadata(Material material, CreativeTabs tab, TileEntityMetadata tile, String textureBase, String blockName) {
		super(material, tab);
		providerTile = tile;
		this.textureBase = textureBase;
		setBlockName(blockName);
	}

	public void setDefaultData(float blockHardness, float blastResistance) {
		setDefaultData(blockHardness, blastResistance, 0, 0, false, true, 0, "pickaxe");
	}

	public void setDefaultData(float blockHardness, float blastResistance, boolean beaconBase, boolean mobSpawns) {
		setDefaultData(blockHardness, blastResistance, 0, 0, beaconBase, mobSpawns, 0, "pickaxe");
	}

	public void setDefaultData(float blockHardness, float blastResistance, int blockLight, int redstoneSignal, boolean beaconBase, boolean mobSpawns) {
		setDefaultData(blockHardness, blastResistance, blockLight, redstoneSignal, beaconBase, mobSpawns, 0, "pickaxe");
	}

	public void setDefaultData(float blockHardness, float blastResistance, int blockLight, int redstoneSignal, boolean beaconBase, boolean mobSpawns, int miningLevel, String toolType) {
		setBlockHardness(WILD, blockHardness);
		setBlastResistance(WILD, blastResistance);
		setItemRarity(WILD, 0);
		setBlockLight(WILD, blockLight);
		setRedstoneSignal(WILD, redstoneSignal);
		setBeaconBase(WILD, beaconBase);
		setMobSpawns(WILD, mobSpawns);
		setMiningLevel(WILD, miningLevel);
		setMiningTool(WILD, toolType);
	}

	public void setData(int metadata, String textureName, String blockName) {
		addMetaBlock(metadata);
		setTextureName(metadata, textureName);
		setBlockName(metadata, blockName);
	}

	public void setData(int metadata, String textureName, String blockName, float blockHardness, float blastResistance) {
		setData(metadata, textureName, blockName);
		setBlockHardness(metadata, blockHardness);
		setBlastResistance(metadata, blastResistance);
	}

	public void setData(int metadata, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance) {
		setData(metadata, textureName, blockName, blockHardness, blastResistance);
		setMiningLevel(metadata, miningLevel);
	}

	public void setData(int metadata, String textureName, String blockName, float blockHardness, float blastResistance, int rarity) {
		setData(metadata, textureName, blockName, blockHardness, blastResistance);
		setItemRarity(metadata, rarity);
	}

	public void setData(int metadata, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance, int rarity) {
		setData(metadata, textureName, blockName, miningLevel, blockHardness, blastResistance);
		setItemRarity(metadata, rarity);
	}

	public void setData(int metadata, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance, int rarity, int blockLight, int redstoneSignal) {
		setData(metadata, textureName, blockName, miningLevel, blockHardness, blastResistance, rarity);
		setBlockLight(metadata, blockLight);
		setRedstoneSignal(metadata, redstoneSignal);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName) {
		setData(metadata, textureName, blockName);
		setColorData(metadata, hexColor);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName, float blockHardness, float blastResistance) {
		setData(metadata, textureName, blockName, blockHardness, blastResistance);
		setColorData(metadata, hexColor);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance) {
		setData(metadata, textureName, blockName, miningLevel, blockHardness, blastResistance);
		setColorData(metadata, hexColor);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName, float blockHardness, float blastResistance, int rarity) {
		setData(metadata, textureName, blockName, blockHardness, blastResistance, rarity);
		setColorData(metadata, hexColor);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance, int rarity) {
		setData(metadata, textureName, blockName, miningLevel, blockHardness, blastResistance, rarity);
		setColorData(metadata, hexColor);
	}

	public void setData(int metadata, int hexColor, String textureName, String blockName, int miningLevel, float blockHardness, float blastResistance, int rarity, int blockLight, int redstoneSignal) {
		setData(metadata, textureName, blockName, miningLevel, blockHardness, blastResistance, rarity, blockLight, redstoneSignal);
		setColorData(metadata, hexColor);
	}

	public void setTextureName(int metadata, String textureName) {
		textureNames.put(metadata, textureName);
	}

	public void setBlockName(int metadata, String blockName) {
		blockNames.put(metadata, blockName);
	}

	public void setBlockHardness(int metadata, float blockHardness) {
		this.blockHardness.put(metadata, blockHardness);
	}

	public void setBlastResistance(int metadata, float blastResistance) {
		this.blastResistance.put(metadata, blastResistance);
	}

	public void setItemRarity(int metadata, int rarity) {
		this.itemRarity.put(metadata, rarity);
	}

	public void setBlockLight(int metadata, int blockLight) {
		this.blockLight.put(metadata, blockLight);
	}

	public void setRedstoneSignal(int metadata, int redstoneSignal) {
		this.redstoneSignal.put(metadata, redstoneSignal);
	}

	public void setBeaconBase(int metadata, boolean beaconBase) {
		this.beaconBase.put(metadata, beaconBase);
	}

	public void setMobSpawns(int metadata, boolean mobSpawns) {
		this.mobSpawns.put(metadata, mobSpawns);
	}

	public void setColorData(int metadata, int hexColor) {
		colorData.put(metadata, hexColor);
	}

	public void setMiningLevel(int metadata, int miningLevel) {
		harvestLevels.put(metadata, miningLevel);
	}

	public void setMiningTool(int metadata, String tool) {
		harvestTools.put(metadata, tool);
	}

	public void setTextureCasing(EnumTextCasing casing) {
		textCasing = casing;
	}

	public void addMetaBlock(int metadata) {
		if (!metaBlocks.contains(metadata)){
			metaBlocks.add(metadata);
			stackMap.put(metadata, ItemBlockMetadata.withTag(new ItemStack(this, 1, metadata), metadata));
		}
	}

	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return providerTile;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = getMetadata(world, x, y, z);
		if (blockHardness.containsKey(meta)) {
			return blockHardness.get(meta);
		} else if (blockHardness.containsKey(WILD)) {
			return blockHardness.get(WILD);
		} else {
			return 3.0F;
		}
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = getMetadata(world, x, y, z);
		if (blastResistance.containsKey(meta)) {
			return blastResistance.get(meta);
		} else if (blastResistance.containsKey(WILD)) {
			return blastResistance.get(WILD);
		} else {
			return 5.0F;
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = getMetadata(world, x, y, z);
		if (blockLight.containsKey(meta)) {
			return blockLight.get(meta);
		} else if (blockLight.containsKey(WILD)) {
			return blockLight.get(WILD);
		} else {
			return 0;
		}
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = getMetadata(world, x, y, z);
		if (redstoneSignal.containsKey(meta)) {
			return redstoneSignal.get(meta);
		} else if (redstoneSignal.containsKey(WILD)) {
			return redstoneSignal.get(WILD);
		} else {
			return 0;
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		int meta = getMetadata(world, x, y, z);
		if (beaconBase.containsKey(meta)) {
			return beaconBase.get(meta);
		} else if (beaconBase.containsKey(WILD)) {
			return beaconBase.get(WILD);
		} else {
			return false;
		}
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		int meta = getMetadata(world, x, y, z);
		if (mobSpawns.containsKey(meta)) {
			return mobSpawns.get(meta);
		} else if (mobSpawns.containsKey(WILD)) {
			return mobSpawns.get(WILD);
		} else {
			return true;
		}
	}

	//TODO: This
	@Override
	@SuppressWarnings("unchecked") //Stupid Mojang, why you so derpcode?
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i : metaBlocks) {
			list.add(stackMap.get(i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return getIcon(side, getMetadata(world, x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return (blockIcons.get(metadata));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {

		switch (textCasing) {
			case CAMEL:
				for (Entry<Integer, String> textures : textureNames.entrySet()) {
					blockIcons.put(textures.getKey(), register.registerIcon(textureBase + camelCase(textures.getValue())));
				}

			/*case TITLE:
				for (Entry<Integer, String> textures : textureNames.entrySet()) {
					Integer metadata = textures.getKey();
					String texture = textures.getValue();
					IIcon icon = register.registerIcon(textureBase + StringHelper.titleCase(texture));
					blockIcons.put(metadata, icon);
				}

			case CAPITAL:
				for (Entry<Integer, String> textures : textureNames.entrySet()) {
					Integer metadata = textures.getKey();
					String texture = textures.getValue();
					IIcon icon = register.registerIcon(textureBase + oldcode.projectfluxgear.helper.StringHelper.camelCase(texture));
					blockIcons.put(metadata, icon);
				}

			case LOWER:
				for (Entry<Integer, String> textures : textureNames.entrySet()) {
					Integer metadata = textures.getKey();
					String texture = textures.getValue();
					IIcon icon = register.registerIcon(textureBase + oldcode.projectfluxgear.helper.StringHelper.camelCase(texture));
					blockIcons.put(metadata, icon);
				}*/
		}
	}

	//Temporary
	public static String camelCase(String var0) {
		return var0.substring(0, 1).toLowerCase() + var0.substring(1);
	}
}
