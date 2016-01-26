package mortvana.melteddashboard.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ColorLibrary;
import mortvana.melteddashboard.util.enums.EnumBlockType;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

/**
 *  A relatively easy to use, yet advanced version of FluxGearBlock for use in many situations.
 *  Contains automagic setting of meta-sensitive hardness, resistance, light emissions, redstone signals,
 *  beacon base-ness, mob spawnability, creative tabs, names, and more!
 *
 *  @author Mortvana
 */
public class FluxGearBlock extends Block {

	public String textureLocation = "fluxgear:";
	public int metaBlocks = -1;
	public IIcon[] icons;
	public String[] textures;
	public String[] names;
	public float[] blockHardness;
	public float[] blastResistance;
	public int[] blockLight;
	public int[] signal;
	public boolean[] mobSpawns;
	public boolean[] beaconBase;
	public int[] colors;
	public int[] droppedMeta;
	public String name;
	public boolean colorized = false;
	public boolean specialRender = false;
	public int renderID;

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public FluxGearBlock(Material material) {
		super(material);
		setStepSound(getDefaultSound(material));
		preInitialize();
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab) {
		this(material);
		setCreativeTab(tab);
	}

	public FluxGearBlock(Material material, String name) {
		this(material);
		setUnlocalizedName(name);
	}

	public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks) {
		this(material);
		this.metaBlocks = metaBlocks;
		setCreativeTab(tab);
		//internalName = ;
	}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting non-meta-sensitive
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, float hardness, float resistance) {
		this(material, tab);
		setAllHardness(hardness);
		setAllResistance(resistance);
	}

	/**
	 * The simple way to initialize a block.
	 * Uses common for a type defaults.
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param type Use a default type for ease of use.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type) {
		this(material, tab);

		if (type == EnumBlockType.STORAGE) {
			setAllMobSpawns(false);
			setAllBeaconBase(true);
			setAllHardness(5.0F);
			setAllResistance(10.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.ORE) {
			setAllHardness(3.0F);
			setAllResistance(5.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.SOIL_ORE) {
			setAllHardness(3.0F);
			setAllResistance(5.0F);
			setStepSound(soundTypeGravel);
		} else {
			MeltedDashboardCore.logger.warn("Someone registered a block wrong in using Melted Dashboard Core... Using defaults...");
		}
	}

	/**
	 * The slightly more complex way to initialize a block.
	 * Allows for more customization
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param spawn Can mobs spawn on top of it.
	 * @param base Is it usable as a beacon base.
	 * @param colorized Set to true if you are using a simple colorizer.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized) {
		this(material, tab);
		setAllMobSpawns(spawn);
		setAllBeaconBase(base);
		setColorized(colorized);
	}

	/**
	 * The only way, currently, to initialize a block with metadata things and automatic texture registration.
	 * Uses common for a type defaults.
	 *
	 * @param material The material of the block.
	 * @param tab      The creative tab the block is under.
	 * @param type     Use a default type for ease of use.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names, String[] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type);
		this.names = names;
		this.textures = textures;
		setHardness(hardness);
		setResistance(resistance);
		setLight(light);
		setTextureLocation(textureLocation);
	}

	/**
	 * The only way, currently, to initialize a block with metadata things and automatic texture registration.
	 * Uses common for a type defaults.
	 *
	 * @param material The material of the block.
	 * @param tab      The creative tab the block is under.
	 * @param type     Use a default type for ease of use.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type, names, names, hardness, resistance, light, textureLocation);
	}

    /**
     * The only way, currently, to initialize a block with metadata things and automatic texture registration.
     * Uses common for a type defaults.
     *
     * @param material The material of the block.
     * @param tab      The creative tab the block is under.
     * @param type     Use a default type for ease of use.
     */
    @Deprecated
    public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names, IIcon[] dummy, float[] hardness, float[] resistance, int[] light, String textureLocation) {
        this(material, tab, type, names, names, hardness, resistance, light, textureLocation);
    }

	//public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks, float blockHardness, float blockResistance,
	//                     List<Boolean> canSpawn, List<Boolean> beaconBase, List<String> names, List<Float> hardness, List<Float> resistance,
	//                     List<Integer> light, List<Integer> signal, String textureLocation) {
	//	super(material);

	public void preInitialize() {
		if (metaBlocks == -1) {
			metaBlocks = names != null ? names.length: 16;
		}
		icons = new IIcon[metaBlocks];
		textures = new String[metaBlocks];
		names = new String[metaBlocks];
		blockHardness = new float[metaBlocks];
		blastResistance = new float[metaBlocks];
		blockLight = new int[metaBlocks];
		signal = new int[metaBlocks];
		mobSpawns = new boolean[metaBlocks];
		beaconBase = new boolean[metaBlocks];
		colors = new int[metaBlocks];
		setAllLight(0);
		setAllSignal(0);
		setAllMobSpawns(true);
		setAllBeaconBase(false);
		setAllColor(ColorLibrary.CLEAR);
	}

	public void setMaterial(Material material) {
		blockMaterial = material;
		translucent = !material.blocksLight();
	}

	public FluxGearBlock setBlockName(String name) {
		setUnlocalizedName(name);
		setTextureName(name);
		return this;
	}

	public SoundType getDefaultSound(Material material) {
		if (material == Material.rock || material == Material.piston || material == Material.dragonEgg) {
			return soundTypeStone;
		} else if (material == Material.iron) {
			return soundTypeMetal;
		} else if (material == Material.grass || material == Material.tnt || material == Material.leaves || material == Material.vine || material == Material.plants || material == Material.sponge) {
			return soundTypeGrass;
		} else if (material == Material.glass || material == Material.ice || material == Material.packedIce || material == Material.portal || material == Material.redstoneLight) {
			return soundTypeGlass;
		} else if (material == Material.snow || material == Material.craftedSnow) {
			return soundTypeSnow;
		} else if (material == Material.ground || material == Material.clay) {
			return soundTypeGravel;
		} else if (material == Material.wood || material == Material.fire || material == Material.gourd) {
			return soundTypeWood;
		} else if (material == Material.cloth || material == Material.cactus || material == Material.cake) {
			return soundTypeCloth;
		} else if (material == Material.anvil) {
			return soundTypeAnvil;
		} else { //Material.water, Material.lava, Material.air, Material.sand, Material.web, Material.circuits, Material.coral
			return null;
		}
	}


	public FluxGearBlock setTextureLocation(String path) {
		textureLocation = path;
		return this;
	}

	public FluxGearBlock setColorized(boolean colorized) {
		this.colorized = colorized;
		return this;
	}

	public FluxGearBlock setHardness(float hardness, int metadata) {
		blockHardness[metadata] = hardness;
		return this;
	}

	public FluxGearBlock setHardness(float... hardness) {
		System.arraycopy(hardness, 0, blockHardness, 0, metaBlocks);
		return this;
	}

	public FluxGearBlock setAllHardness(float hardness) {
		for (int i = 0; i < metaBlocks; i++) {
			blockHardness[i] = hardness;
		}
		return (FluxGearBlock) setHardness(hardness);
	}

	public FluxGearBlock setResistance(float resistance, int metadata) {
		blastResistance[metadata] = resistance;
		return this;
	}

	public FluxGearBlock setResistance(float... resistance) {
		System.arraycopy(resistance, 0, blastResistance, 0, metaBlocks);
		return this;
	}

	public FluxGearBlock setAllResistance(float resistance) {
		for (int i = 0; i < metaBlocks; i++) {
			blastResistance[i] = resistance;
		}
		return (FluxGearBlock) setResistance(resistance);
	}

	public FluxGearBlock setLight(int light, int metadata) {
		blockLight[metadata] = MathHelper.clampInt(light, 0, 15);
		return this;
	}

	public FluxGearBlock setLight(int... light) {
		for (int i = 0; i < metaBlocks; i++) {
			blockLight[i] = MathHelper.clampInt(light[i], 0, 15);
		}
		return this;
	}

	public FluxGearBlock setAllLight(int light) {
		light = MathHelper.clampInt(light, 0, 15);
		for (int i = 0; i < metaBlocks; i++) {
			blockLight[i] = light;
		}
		return (FluxGearBlock) setLightLevel(light);
	}

	public FluxGearBlock setSignal(int power, int metadata) {
		signal[metadata] = MathHelper.clampInt(power, 0, 31);
		return this;
	}

	public FluxGearBlock setSignal(int... power) {
		for (int i = 0; i < metaBlocks; i++) {
			signal[i] = MathHelper.clampInt(power[i], 0, 31);
		}
		return this;
	}

	public FluxGearBlock setAllSignal(int power) {
		power = MathHelper.clampInt(power, 0, 15);
		for (int i = 0; i < metaBlocks; i++) {
			signal[i] = power;
		}
		return this;
	}

	public FluxGearBlock setMobSpawns(boolean spawns, int metadata) {
		mobSpawns[metadata] = spawns;
		return this;
	}

	public FluxGearBlock setMobSpawns(boolean... spawns) {
		System.arraycopy(spawns, 0, mobSpawns, 0, metaBlocks);
		return this;
	}

	public FluxGearBlock setAllMobSpawns(boolean spawns) {
		for (int i = 0; i < metaBlocks; i++) {
			mobSpawns[i] = spawns;
		}
		return this;
	}

	public FluxGearBlock setBeaconBase(boolean base, int metadata) {
		beaconBase[metadata] = base;
		return this;
	}

	public FluxGearBlock setBeaconBase(boolean... base) {
		System.arraycopy(base, 0, beaconBase, 0, metaBlocks);
		return this;
	}

	public FluxGearBlock setAllBeaconBase(boolean base) {
		for (int i = 0; i < metaBlocks; i++) {
			beaconBase[i] = base;
		}
		return this;
	}

	public FluxGearBlock setColor(int color, int metadata) {
		colors[metadata] = MathHelper.clampInt(color, 0x000000, 0xFFFFFF);
		return this;
	}

	public FluxGearBlock setColor(int... color) {
		for (int i = 0; i < metaBlocks; i++) {
			colors[i] = MathHelper.clampInt(color[i], 0x000000, 0xFFFFFF);
		}
		return this;
	}

	public FluxGearBlock setAllColor(int color) {
		color = MathHelper.clampInt(color, 0x000000, 0xFFFFFF);
		for (int i = 0; i < metaBlocks; i++) {
			colors[i] = color;
		}
		return this;
	}

	/*@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			LexiconUnlockHandler.unlockEntry(getName());
		}
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}*/

	/* Standard Meta-Sensitive Getters */
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? blockHardness[meta] : blockHardness[0];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? blastResistance[meta] : blastResistance[0];
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? blockLight[meta] : 0;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks && signal[meta] > 16 ? signal[meta] : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks && signal[meta] < 16 ? signal[meta] % 16 : 0;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? mobSpawns[meta] : mobSpawns[0];
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? beaconBase[meta] : beaconBase[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > metaBlocks ? colors[meta] : 0xFFFFFF;
	}

	/* Block Breaking */
	@Override
	public int damageDropped(int metadata) {
		return metadata > droppedMeta.length ? droppedMeta[metadata] : droppedMeta[0];
	}

	/* Textures */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return specialRender ? null : metadata > icons.length ? icons[metadata] : blockIcon;
	}

	@Override
	public boolean canRenderInPass(int pass) {
		return pass == 0 || colorized;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		if (!specialRender) {
			for (int i = 0; i < names.length; i++) {
				icons[i] = iconRegister.registerIcon(textureLocation + StringHelper.camelCase(names[i]));
			}
		}
	}

	/*public String getUnlocalizedName() {
		return ""; //TODO
	}*/

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < metaBlocks; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getRenderType() {
		return specialRender ? renderID : super.getRenderType();
	}

	@Override
	public boolean isOpaqueCube() {
		return !specialRender && super.isOpaqueCube();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return !specialRender && super.renderAsNormalBlock();
	}
}