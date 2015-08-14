package mortvana.melteddashboard.block.metadata;

import java.util.HashMap;
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

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.oredict.OreDictionary;

import mortvana.melteddashboard.util.enums.EnumTextCasing;

public class FluxGearBlockExtendedMetadata extends BlockExtendedMetadata {

	public HashMap<Integer, String> textureNames = new HashMap<Integer, String>();
	public HashMap<Integer, String> blockNames = new HashMap<Integer, String>();
	public HashMap<Integer, Float> blockHardness = new HashMap<Integer, Float>();
	public HashMap<Integer, Float> blastResistance = new HashMap<Integer, Float>();
	public HashMap<Integer, Integer> itemRarity = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> blockLight = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> redstoneSignal = new HashMap<Integer, Integer>();
	public HashMap<Integer, Boolean> beaconBase = new HashMap<Integer, Boolean>();
	public HashMap<Integer, Boolean> mobSpawns = new HashMap<Integer, Boolean>();
	public HashMap<Integer, Integer> colorData = new HashMap<Integer, Integer>();
	public HashMap<Integer, IIcon> blockIcons = new HashMap<Integer, IIcon>();

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
		this.blockHardness.put(WILD, blockHardness);
		this.blastResistance.put(WILD, blastResistance);
		itemRarity.put(WILD, 0);
		this.blockLight.put(WILD, 0);
		this.redstoneSignal.put(WILD, 0);
		this.beaconBase.put(WILD, false);
		this.mobSpawns.put(WILD, true);
	}

	public void setDefaultData(float blockHardness, float blastResistance, boolean beaconBase, boolean mobSpawns) {
		this.blockHardness.put(WILD, blockHardness);
		this.blastResistance.put(WILD, blastResistance);
		itemRarity.put(WILD, 0);
		this.blockLight.put(WILD, 0);
		this.redstoneSignal.put(WILD, 0);
		this.beaconBase.put(WILD, beaconBase);
		this.mobSpawns.put(WILD, mobSpawns);
	}

	public void setDefaultData(float blockHardness, float blastResistance, int blockLight, int redstoneSignal, boolean beaconBase, boolean mobSpawns) {
		this.blockHardness.put(WILD, blockHardness);
		this.blastResistance.put(WILD, blastResistance);
		itemRarity.put(WILD, 0);
		this.blockLight.put(WILD, blockLight);
		this.redstoneSignal.put(WILD, redstoneSignal);
		this.beaconBase.put(WILD, beaconBase);
		this.mobSpawns.put(WILD, mobSpawns);
	}

	public void setData(int metadata, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
	}

	public void setData(int metadata, float blockHardness, float blastResistance, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		harvestLevels.put(metadata, miningLevel);
	}

	public void setData(int metadata, float blockHardness, float blastResistance, int rarity, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, int rarity, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
		harvestLevels.put(metadata, miningLevel);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, int rarity, int blockLight, int redstoneSignal, String textureName, String blockName) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
		this.blockLight.put(metadata, blockLight);
		this.redstoneSignal.put(metadata, redstoneSignal);
		harvestLevels.put(metadata, miningLevel);
	}

	public void setData(int metadata, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		colorData.put(metadata, hexColor);
	}

	public void setData(int metadata, float blockHardness, float blastResistance, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		colorData.put(metadata, hexColor);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		colorData.put(metadata, hexColor);
		harvestLevels.put(metadata, miningLevel);
	}

	public void setData(int metadata, float blockHardness, float blastResistance, int rarity, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
		colorData.put(metadata, hexColor);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, int rarity, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
		colorData.put(metadata, hexColor);
		harvestLevels.put(metadata, miningLevel);
	}

	public void setData(int metadata, int miningLevel, float blockHardness, float blastResistance, int rarity, int blockLight, int redstoneSignal, String textureName, String blockName, int hexColor) {
		textureNames.put(metadata, textureName);
		blockNames.put(metadata, blockName);
		this.blockHardness.put(metadata, blockHardness);
		this.blastResistance.put(metadata, blastResistance);
		this.itemRarity.put(metadata, rarity);
		this.blockLight.put(metadata, blockLight);
		this.redstoneSignal.put(metadata, redstoneSignal);
		colorData.put(metadata, hexColor);
		harvestLevels.put(metadata, miningLevel);
	}

	public static void registerWithHandlers(String oreDictName, ItemStack var1) {
		OreDictionary.registerOre(oreDictName, var1);
		GameRegistry.registerCustomItemStack(oreDictName, var1);
		FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", var1);
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
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		/*for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}*/
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
					Integer metadata = textures.getKey();
					String texture = textures.getValue();
					IIcon icon = register.registerIcon(textureBase + camelCase(texture));
					blockIcons.put(metadata, icon);
				}

			/*case TITLE:
				for (Entry<Integer, String> textures : textureNames.entrySet()) {
					Integer metadata = textures.getKey();
					String texture = textures.getValue();
					IIcon icon = register.registerIcon(textureBase + StringHelper.titleCase(texture));
					blockIcons.put(metadata, icon);
				}*/

			/*case CAPITAL:
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
