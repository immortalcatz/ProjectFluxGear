package oldcode.projectfluxgear;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.util.RotationHelper;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import cofh.core.fluid.BlockFluidCoFHBase;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.util.block.FluxGearBlock;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

@Deprecated
//TODO: I had class outside a funeral
public class BlockFluxGear extends Block {

	/**
	 * The simple way to initialize a block.
	 * Uses common for a type defaults.
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param type Use a default type for ease of use.
	 */
	public BlockFluxGear(Material material, CreativeTabs tab, EnumBlockType type) {
		super(material);
		setCreativeTab(tab);

		if (type == EnumBlockType.STORAGE) {
			canSpawn = false;
			beaconBase = true;
			isColorized = false;
			setHardness(5.0F);
			setResistance(10.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.ORE) {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			setHardness(3.0F);
			setResistance(5.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.SOIL_ORE) {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			setHardness(3.0F);
			setResistance(5.0F);
			setStepSound(soundTypeGravel);
		} else {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			ProjectFluxGear.logger.warn(wrong);
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
	public BlockFluxGear(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized) {
		super(material);
		setCreativeTab(tab);

		canSpawn = spawn;
		beaconBase = base;
		isColorized = colorized;
	}

	public String[] names;
	public IIcon[] textures;

	public BlockFluxGear setRenderType(int renderType) {

	}

	public BlockFluxGear(Material material, String texDir) {
		super(material);
		textureLocation = texDir;
	}

	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {

		return beaconBase;
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return canSpawn;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return isColorized ? 1 : 0;
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return blockLight[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return textures[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < names.length; i++) {
			textures[i] = ir.registerIcon(textureLocation + StringHelper.camelCase(names[i]));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {

		return hardness[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

		return resistance[world.getBlockMetadata(x, y, z)];
	}

	/**
	 * The only way, currently, to initialize a block with metadata things and automatic texture registration.
	 * Uses common for a type defaults.
	 *
	 * @param material The material of the block.
	 * @param tab      The creative tab the block is under.
	 * @param type     Use a default type for ease of use.
	 */
	public BlockFluxGear(Material material, CreativeTabs tab, EnumBlockType type, String[] names, IIcon[] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type);

		this.names = names;
		this.textures = textures;
		this.hardness = hardness;
		this.resistance = resistance;
		blockLight = light;
		this.textureLocation = textureLocation;
	}

	public boolean isColorized;
	public boolean canSpawn;
	public boolean beaconBase;
	public static String wrong = "Mortvana registered a block wrong in Project Flux Gear... Derp";
	public int[] blockLight;
	public int[] rarity;
	public String textureLocation;
	public float[] hardness;
	public float[] resistance;

	public static enum EnumBlockType {
		STORAGE,
		ORE,
		SOIL_ORE
	}

	/** *=-=-=-=* WEIRD SCIENCE LEGACY *=-=-=-=* */

	protected String englishName;
	public String harvestType = "pickaxe";
	public int harvestLevel = 1;
	protected ItemStack itemDropped;
	protected int droppedRandomBonus;

	public BlockFluxGear(String name, Material material) {
		/*
		 * Default material set to rock.
		 */
		super(material);
		englishName = name;
		this.setBlockName("block." + name.replace(" ", "")); //A default value. Absolutely acceptable to not keep it.

	}

	public boolean isEnabled() {
		return true;
	}

	public Material getMaterial() {
		return this.blockMaterial;
	}

	public void setMaterial(Material m) {
		//Deep dark voodoo. If you get a security exception, here it is. I'm sorry, Gyro says he did it all for the greater good.
		Field field;
		try {
			//Get the field of the block class.
			field = Block.class.getField("blockMaterial");
			field.setAccessible(true);

			//Modify the field to not be final.
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(this, m);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		//Make sure that the entries to canBlockGrass are still valid.
		canBlockGrass = !m.getCanBlockGrass();
	}

	@Override
	public int getHarvestLevel(int subBlockMeta) {
		//By default, no metadata-based sub-blocks.
		return harvestLevel;
	}

	@Override
	public int quantityDropped(Random random) {
		return this.quantityDroppedWithBonus(0, random);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World p_getDrops_1_, int p_getDrops_2_, int p_getDrops_3_, int p_getDrops_4_, int p_getDrops_5_, int p_getDrops_6_) {
		if(this.itemDropped != null) {
			ArrayList<ItemStack> result = new ArrayList<ItemStack>(1);
			result.add(itemDropped);
			return result;
		} else {
			return super.getDrops(p_getDrops_1_, p_getDrops_2_, p_getDrops_3_, p_getDrops_4_, p_getDrops_5_, p_getDrops_6_);
		}
	}

	@Override
	public int quantityDroppedWithBonus(int bonus, Random random) {
		if(this.itemDropped != null) {
			return itemDropped.stackSize + random.nextInt(bonus + droppedRandomBonus);
		} else {
			return super.quantityDroppedWithBonus(bonus+ droppedRandomBonus, random);
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return this.quantityDroppedWithBonus(fortune, random);
	}

	public int damageDropped2(int par1) {
		if(this.itemDropped != null) {
			return itemDropped.getItemDamage();
		} else {
			return super.damageDropped(par1);
		}
	}

	public ItemStack getItemDropped() {
		return itemDropped;
	}

	public void setItemDropped(ItemStack itemDropped) {
		this.itemDropped = itemDropped;
	}

	public int getDroppedRandomBonus() {
		return droppedRandomBonus;
	}

	public void setDroppedRandomBonus(int droppedRandomBonus) {
		this.droppedRandomBonus = droppedRandomBonus;
	}

	/* *=-=-=-=* Item Blocks *=-=-=-=* */

	public class ItemBlockFluxGear extends ItemBlock {

		public ItemBlockFluxGear (Block block) {
			super(block);
			setHasSubtypes(true);
			setMaxDamage(0);
		}

		public ItemBlockFluxGear (Block block, boolean subTypes, int maxDamage) {
			super(block);
			setHasSubtypes(subTypes);
			setMaxDamage(maxDamage);
		}

		public String getItemStackDisplayName(ItemStack item) {
			return StringHelper.localize(this.getUnlocalizedName(item));
		}

		@Override
		public int getMetadata(int i) {

			return i;
		}

		public boolean hasCustomEntity(ItemStack itemstack) {
			return SecurityHelper.isSecure(itemstack);
		}

		public boolean isItemTool(ItemStack itemstack) {
			return false;
		}

		public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
			if(SecurityHelper.isSecure(itemstack)) {
				entity.invulnerable = true;
				entity.isImmuneToFire = true;
				((EntityItem)entity).lifespan = 2147483647;
			}
			return null;
		}

		public String unlocalizedName;
		public String[] blockNames;
		public int[] blockRarities;

		public ItemBlockFluxGear(Block block, String unlocName, String[] blockNames, int[] blockRarities) {
			this(block);
			unlocalizedName = unlocName;
			this.blockNames = blockNames;
			this.blockRarities = blockRarities;
		}

		@Override
		public String getUnlocalizedName(ItemStack item) {
			return unlocalizedName + blockNames[ItemHelper.getItemDamage(item)] + ".name";
		}

		@Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.values()[blockRarities[ItemHelper.getItemDamage(stack)]];
		}

	/**/

		public FluxGearItemBlock(Block block, String sentBlockName, Class sentBlockClass) {

			super(block);
			blockName = sentBlockName;
			blockClass = sentBlockClass;
			setHasSubtypes(true);
			setMaxDamage(0);
		}

		@Override
		public String getItemStackDisplayName(ItemStack item) {

			return StringHelper.localize(getUnlocalizedName(item));
		}

		@Override
		public String getUnlocalizedName(ItemStack item) {

			return null;//"tile." + blockName + "." + BlockOre.NAMES[ItemHelper.getItemDamage(item)] + ".name";
		}


		@Override
		public EnumRarity getRarity(ItemStack stack) {

			return null;//EnumRarity.values()[BlockOre.RARITY[ItemHelper.getItemDamage(stack)]];
		}

		public static String blockName;
		public static Class blockClass;
	}

	public class ItemBlockPaintedStone extends ItemBlock {
		public ItemBlockPaintedStone(Block block) {
			super(block);
			setMaxDamage(0).setHasSubtypes(true);
		}

		public int getMetadata(int meta) {
			return meta;
		}

		public String getUnlocalizedName(ItemStack par1ItemStack) {
			return super.getUnlocalizedName() + "." + oldcode.projectfluxgear.ProjectFluxGear.colorNames[par1ItemStack.getItemDamage()];
		}
	}

	/* *=-=-=-=* Fields *=-=-=-=* */

	// Materials
	public static final Material materialFluidGhastTear = new MaterialLiquid(MapColor.snowColor);
	public static final Material materialFluidSmog = new MaterialLiquid(MapColor.brownColor);
	public static Material materialSoilOre = new Material(MapColor.sandColor).setRequiresTool();

	// Names
	public static final String[] rockNames = new String[] { "mica", "andesite", "rhyolite", "gabrro", "diorite", "dolomite", "chert", "phyllite", "kimberlite", "dacite", "serpentine", "larvikite", "schalstein", "greenschist", "hornblendeBiotiteGranite", "pitchstone" };
	public static final String[] NAMES_ORES_MAIN = { "chalcocite", "cassiterite", "galena", "acanthite", "garnierite", "sphalerite", "bismuthinite", "pyrolusite", "bauxite", "cooperite", "braggite", "molybdenite", "cobaltite", "wolframite", "ilmenite", "chromite" };
	public static final String[] NAMES_ORES_AUX = { "cinnabar", "pitchblende", "monazite", "niedermayrite", "greenockite", "gaotaiite", "osarsite", "znamenskyite", "gallobeudanite", "tetrahedrite", "tennantite", "santafeite", "magnetite", "dioptase", "pyrope", "myuvil" };
	public static final String[] NAMES_STORE_AUX = { "antimony", "arsenic", "neodymium", "tesseractium", "cadmium", "tellurium", "osmium", "iridium", "indium", "antimonialBronze", "arsenicalBronze", "vanadium", "unobtainium", "dioptase", "pyrope", "myuvil" };
	public static final String[] NAMES_ALLOY_MAIN = { "bronze", "brass", "invar", "bismuthBronze", "cupronickel", "aluminiumBrass", "electrum", "dullRedsolder", "redsolder", "highCarbonSteel", "steel", "hsla", "stainlessSteel", "tungstenSteel", "electriplatinum", "mithril" };
	public static final String[] NAMES_ALLOY_AUX = { "technomancy", "resonantTechnomancy", "tungstenBlazing", "platinumGelid", "silverLuminous", "electrumFlux", "molybdenumResonant", "chromiumCarbide", "bismuthBronzeColdfire", "pyrum", "gelinum", "lumium", "signalum", "enderium", "carbonite", "therminate" };
	public static final String[] NAMES_STORE_ALCH = { "nullmetal", "iocarbide", "cryocarbide", "pyrocarbide", "tenebride", "illuminide", "zythoferride", "crystalFlux", "lapiquartz", "rust", "whitePointStar", "voidInfernoStar", "sulfur", "saltpeter", "mithrilFlux", "mithrilTinker" };
	//TODO: Move Amber
	public static final String[] NAMES_STORE_ADV = { "thorium", "uranium235", "uranium238", "magnetite", "neodymiumM"/**/, "ironM", "manganeseM"/**/, "cobaltM", "nickelM", "invarM", "highCarbonSteelM", "steelM", "hslaM", "amber", "nichrome", "polycarbide" };

	public static final String[] NAMES_EARTH = { "clayIridium", "clayIridiumPoor", "aluminosilicateSludge" };

	// Hardness
	public static final float[] HARDNESS_ORES = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
	public static final float[] HARDNESS_STORE_AUX = { 3, 3, 5, 8, 5, 5, 11, 13, 5, 6, 6, 7, 42, 7, 7, 4 };
	public static final float[] HARDNESS_ALLOY_MAIN = { 5, 5, 6, 5, 5, 5, 5, 5, 5, 7, 8, 8, 8, 13, 6, 5 };
	public static final float[] HARDNESS_ALLOY_AUX = { 7, 11, 13, 8, 8, 8, 13, 11, 16, 11, 6, 5, 5, 40, 7, 13 };
	public static final float[] HARDNESS_STORE_ALCH = { 1, 5, 5, 5, 7, 7, 11, 5, 5, 0.6F, 8, 8, 5, 5, 8, 11 };
	public static final float[] HARDNESS_STORE_ADV  = { 5, 5, 5, 5, 5, 5, 5, 7, 7, 6, 7, 8, 8, 4, 2, 42 };

	public static final float[] HARDNESS_EARTH = { 5, 5, 0.3F };

	// Texture Locations
	public static final String TEXTURE_LOCATION_DEFAULT = "mortvana.projectfluxgear:";
	public static final String TEXTURE_LOCATION_ORE = "mortvana.projectfluxgear:ore/ore";
	public static final String TEXTURE_LOCATION_BLOCK = "mortvana.projectfluxgear:storage/block";
	public static final String TEXTURE_LOCATION_POOR_ORE = "mortvana.projectfluxgear:ore/orePoor";
	public static final String TEXTURE_LOCATION_GRAVEL_ORE = "mortvana.projectfluxgear:ore/oreGravel";

	// Resistance
	public static final float[] RESISTANCE_ORES = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	public static final float[] RESISTANCE_STORE_AUX = { 5, 5, 8, 42, 6, 6, 13, 16, 6, 8, 8, 9, 500, 13, 13, 6 };
	public static final float[] RESISTANCE_ALLOY_MAIN = { 7, 6, 8, 8, 6, 6, 6, 6, 6, 12, 13, 13, 13, 42, 8, 10 };
	public static final float[] RESISTANCE_ALLOY_AUX = { 16, 128, 135, 85, 85, 85, 192, 192, 256, 35, 11, 9, 9, 120, 64, 128 };
	public static final float[] RESISTANCE_STORE_ALCH = { 1, 6, 7, 7, 10, 10, 42, 8, 8, 1, 42, 507, 5, 5, 32, 64 };
	public static final float[] RESISTANCE_STORE_ADV  = { 5, 5, 5, 8, 8, 10, 6, 8, 6, 8, 12, 13, 13, 6, 11, 1000 };

	public static final float[] RESISTANCE_EARTH = { 5, 5, 5 };

	// Light
	public static final int[] LIGHT_ORES_MAIN = { 0, 0, 1, 4, 0, 0, 2, 0, 0, 4, 1, 2, 0, 0, 0, 0 };
	public static final int[] LIGHT_ORES_AUX = { 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 8, 4, 4 };
	public static final int[] LIGHT_STORE_AUX = { 0, 0, 0, 7, 0, 0, 1, 4, 0, 0, 0, 1, 15, 8, 4, 4 };
	public static final int[] LIGHT_ALLOY_MAIN =  { 0, 0, 0, 2, 0, 2, 4, 2, 2, 0, 0, 0, 0, 0, 4, 2 };
	public static final int[] LIGHT_ALLOY_AUX = { 4, 8, 12, 4, 15, 7, 4, 2, 15, 12, 4, 15, 7, 4, 2, 15 };
	public static final int[] LIGHT_STORE_ALCH = { 0, 0, 4, 4, 0, 15, 7, 4, 2, 0, 15, 15, 0, 0, 7, 15 };
	public static final int[] LIGHT_STORE_ADV  = { 2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static final int[] LIGHT_EARTH = { 0, 0, 0 };

	// Rarity
	public static final int[] RARITY_ORES_MAIN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 0 };
	public static final int[] RARITY_ORES_AUX = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1 };
	public static final int[] RARITY_STORE_AUX = { 0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 1, 3, 2, 2, 1 };
	public static final int[] RARITY_ALLOY_MAIN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 };
	public static final int[] RARITY_ALLOY_AUX = { 1, 2, 2, 2, 2, 2, 3, 2, 3, 1, 1, 1, 1, 2, 1, 2 };
	public static final int[] RARITY_STORE_ALCH = { 0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 2, 3, 0, 0, 1, 2 };
	public static final int[] RARITY_STORE_ADV  = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 3 };

	public static final int[] RARITY_EARTH = { 2, 1, 0 };

	// Other
	public static final IIcon[] TEXTURES_FULL = new IIcon[16];
	public static final IIcon[] TEXTURES_EARTH = new IIcon[NAMES_EARTH.length];

	public static String unlocDefault = "tile.projectfluxgear.block.";
	public static String unlocOre = "tile.projectfluxgear.ore.";
	public static String unlocStore = "tile.projectfluxgear.storage.";
	public static String unlocGravel = "tile.projectfluxgear.oreGravel.";
	public static String unlocPoore = "tile.projectfluxgear.orePoor.";

	//TODO: More Redstone Signal Stuff

	/* *=-=-=-=* Blocks *=-=-=-=* */

	public class BlockQuartzNormal extends Block {

		public IIcon topIcon;
		public IIcon botIcon;
		public IIcon sideIcon;

		public BlockQuartzNormal() {
			super(Material.rock);
			setBlockName("blockInfusedQuartzNormal");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setStepSound(Block.soundTypeStone);
			setHardness(0.8F);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister register) {
			topIcon = register.registerIcon("trevelations:infusedquartztop");
			botIcon = register.registerIcon("trevelations:infusedquartzbot");
			sideIcon = register.registerIcon("trevelations:infusedquartzside");
		}

		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta) {
			if (side == 0) {
				return botIcon;
			} else if (side == 1) {
				return topIcon;
			} else {
				return sideIcon;
			}
		}


	}

	public class BlockQuartzChiseled extends Block {

		public IIcon topIcon;
		public IIcon botIcon;
		public IIcon sideIcon;

		public BlockQuartzChiseled() {
			super(Material.rock);
			setBlockName("blockInfusedQuartzChiseled");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setStepSound(Block.soundTypeStone);
			setHardness(0.8F);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister register) {
			topIcon = register.registerIcon("trevelations:infusedquartzchiseledtop");
			botIcon = register.registerIcon("trevelations:infusedquartzchiseledtop");
			sideIcon = register.registerIcon("trevelations:infusedquartzchiseledside");
		}

		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta) {
			if (side == 0) {
				return botIcon;
			} else if (side == 1) {
				return topIcon;
			} else {
				return sideIcon;
			}
		}
	}

	public class BlockQuartzPillar extends BlockRotatedPillar {

		public IIcon topIcon;
		public IIcon sideIcon;

		public BlockQuartzPillar() {
			super(Material.rock);
			setBlockName("blockInfusedQuartzPillar");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setStepSound(Block.soundTypeStone);
			setHardness(0.8F);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister register) {
			topIcon = register.registerIcon("trevelations:infusedquartzpillartop");
			sideIcon = register.registerIcon("trevelations:infusedquartzpillarside");
		}

		@SideOnly(Side.CLIENT)
		protected IIcon getSideIcon(int par) {
			return sideIcon;
		}

		@SideOnly(Side.CLIENT)
		protected IIcon getTopIcon(int par) {
			return topIcon;
		}
	}

	public class BlockQuartzSlab extends BlockSlab {

		public BlockQuartzSlab() {
			super(false, Material.rock);
			setBlockName("blockInfusedQuartzSlab");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setStepSound(Block.soundTypeStone);
			setHardness(0.8F);
			setLightOpacity(0);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int par1, int par2) {
			return oldcode.projectfluxgear.ThaumicContent.blockInfusedQuartzNormal.getBlockTextureFromSide(par1);
		}

		@Override
		public Item getItemDropped(int par1, Random par2, int par3) {
			return Item.getItemFromBlock(oldcode.projectfluxgear.ThaumicContent.blockInfusedQuartzSlab);
		}

		@Override
		public ItemStack createStackedBlock(int par1) {
			return new ItemStack(oldcode.projectfluxgear.ThaumicContent.blockInfusedQuartzSlab);
		}

		@Override
		public String func_150002_b(int var1) {
			return "tile.blockInfusedQuartzSlab";
		}
	}

	public class BlockQuartzStair extends BlockStairs {
		public BlockQuartzStair() {
			super(oldcode.projectfluxgear.ThaumicContent.blockInfusedQuartzNormal, 0);
			setBlockName("blockInfusedQuartzStair");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setLightOpacity(0);
		}
	}

	public class BlockPlant extends Block implements IPlantable {
		public BlockPlant() {
			super(Material.plants);
			setStepSound(Block.soundTypeGrass).setCreativeTab(oldcode.projectfluxgear.ProjectFluxGear.generalTab).setTickRandomly(true);
		}

		@Override
		public Item getItemDropped(int metadata, Random random, int par2) {
			switch (metadata) {
				case 0:
					return FluxGearContent.itemMaterial;
				default:
					return FluxGearContent.itemMaterial;
			}
		}

		@Override
		public int damageDropped(int metadata) {
			switch (metadata) {
				case 0:
					return 15000;
				default:
					return 0;
			}
		}

		@Override
		public boolean canBlockStay(World world, int x, int y, int z) {
			int meta = getPlantMetadata(world, x, y, z);
			switch (meta) {
				case 0:
					return true;
				default:
					return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this);
			}
		}

		@Override
		public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
			int meta = getPlantMetadata(world, x, y, z);
			switch (meta) {
			}
			return EnumPlantType.Plains;
		}

		@Override
		public Block getPlant(IBlockAccess world, int x, int y, int z) {
			return this;
		}

		@Override
		public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
			return world.getBlockMetadata(x, y, z);
		}

		@Override
		public void registerBlockIcons(IIconRegister register) {
			blockIcon = register.registerIcon("fluxgear:exubitura");
		}
	}

	public class BlockSolarPanel extends Block implements ITileEntityProvider {

		public String modId;

		public BlockSolarPanel(String modID, String name, int maxPowerGen, int powerStorage) {
			super(Material.iron);
			this.isBlockContainer = true;
		}

		@Override
		public boolean onBlockEventReceived(World pWorld, int x, int y, int z, int eventNumber, int eventArgument) {
			super.onBlockEventReceived(pWorld, x, y, z, eventNumber, eventArgument);
			TileEntity tileentity = pWorld.getTileEntity(x, y, z);
			if (tileentity != null) {
				return tileentity.receiveClientEvent(eventNumber, eventArgument);
			}
			return false;
		}

		public String getModId() {
			return modId;
		}

		@Override
		public String getUnlocalizedName() {
			return String.format("tile.%s:%s", getModId(), unwrapUnlocalizedName(super.getUnlocalizedName()));
		}

		@Override
		public String getTextureName() {
			return unwrapUnlocalizedName(getUnlocalizedName());
		}

		public String unwrapUnlocalizedName(String unlocalizedName) {
			return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
		}

		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			return null;
		}
	}

	public static class BlockAlloyAux extends BlockFluxGear {

		public BlockAlloyAux() {
			super(Material.iron, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_AUX, TEXTURES_FULL, HARDNESS_ALLOY_AUX, RESISTANCE_ALLOY_AUX, LIGHT_ALLOY_AUX, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.alloyAux");
		}

		@Override
		public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

			if (world.getBlockMetadata(x, y, z) == 5) {
				return 7;
			} else if (world.getBlockMetadata(x, y, z) == 12) {
				return 15;
			} else {
				return 0;
			}
		}

	}

	//BlockStorageRandom

	//TODO: AdvFallingBlockFluxGear
	public static class BlockGravelOreMain extends FallingBlockFluxGear {

		public BlockGravelOreMain() {
			super(materialSoilOre, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, EnumBlockType.SOIL_ORE);
			setBlockName("mortvana.projectfluxgear.gravelOreMain");
		}

		@Override
		public void getSubBlocks(Item item, CreativeTabs tab, List list) {
			for (int i = 0; i < NAMES_ORES_MAIN.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z) {

			return LIGHT_ORES_MAIN[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public IIcon getIcon(int side, int metadata) {

			return TEXTURES[metadata];
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister ir) {

			for (int i = 0; i < NAMES_ORES_MAIN.length; i++) {
				TEXTURES[i] = ir.registerIcon(TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(NAMES_ORES_MAIN[i]));
			}
		}

		public /*static*/ final IIcon[] TEXTURES = new IIcon[NAMES_ORES_MAIN.length];
	}

	public static class BlockGravelOreAux extends FallingBlockFluxGear {

		public BlockGravelOreAux() {
			super(materialSoilOre, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, EnumBlockType.SOIL_ORE);
			setBlockName("mortvana.projectfluxgear.gravelOreAux");
		}

		@Override
		public void getSubBlocks(Item item, CreativeTabs tab, List list) {

			for (int i = 0; i < NAMES_ORES_AUX.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z) {

			return LIGHT_ORES_AUX[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public IIcon getIcon(int side, int metadata) {

			return TEXTURES[metadata];
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister ir) {

			for (int i = 0; i < NAMES_ORES_AUX.length; i++) {
				TEXTURES[i] = ir.registerIcon(TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(NAMES_ORES_AUX[i]));
			}
		}

		public /*static*/ final IIcon[] TEXTURES = new IIcon[NAMES_ORES_AUX.length];
	}

	//BlockPFGGlass

	/* *=-=-=-=* Weird Science Dynamos *=-=-=-=* */

	@Deprecated
	//TODO: I like to burn fascists
	public abstract class BlockContainerBase extends BlockContainer {

		protected String englishName;

		protected TileEntity protoTileEntity;

		public String harvestType = "pickaxe";
		public int harvestLevel = 1;

		public BlockContainerBase(String name, Material material) {
		/*
		 * Default material set to rock.
		 */
			super(material);
			englishName = name;
			this.setBlockName("block" + "." + name.replace(" ", "")); //A default value. Absolutely acceptable to not keep it.

		}

		public BlockContainerBase(String name) {
			this(name, Material.rock);
		}
		public BlockContainerBase(Material material) {
			super(material);
		}

		public boolean isEnabled ()
		{
			return true;
		}

		public Material getMaterial ()
		{
			return this.blockMaterial;
		}

		public void setMaterial (Material m) {
			//Deep dark voodoo. If you get a security exception, here it is. I'm sorry, Gyro say he did it all for the greater good.
			Field field;
			try {
				//Get the field of the block class.
				field = Block.class.getField("blockMaterial");
				field.setAccessible(true);

				//Modify the field to not be final.
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

				field.set(this, m);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			//Make sure that the entries to canBlockGrass are still valid.
			canBlockGrass[blockID] = !m.getCanBlockGrass();
		}

		@Override
		public int getHarvestLevel (int subBlockMeta) {
			//By default, no metadata-based sub-blocks.
			return harvestLevel;
		}

		@Override
		public void onNeighborChange (IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
			super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null) {
				TileEntityBase b = (TileEntityBase) te;
				TileEntity te2 = world.getTileEntity(tileX, tileY, tileZ);
				//Check directionality
				for (int i = 0; i < 6; i++) {
					if ((((tileX - x) == ForgeDirection.VALID_DIRECTIONS[i].offsetX) && ((tileY - y) == ForgeDirection.VALID_DIRECTIONS[i].offsetY)) && ((tileZ - z) == ForgeDirection.VALID_DIRECTIONS[i].offsetZ))
						b.updateAdjacency(te2, i);
				}
			}
		}

		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			return null;
		}


		public TileEntity createNewTileEntity(World world) {
			return createNewTileEntity(world, 0);
		}

		@Override
		public void onBlockPreDestroy (World world, int x, int y, int z, int oldmeta) {
			super.onBlockPreDestroy(world, x, y, z, oldmeta);

			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null) {
				if(te instanceof TileEntityBase) {
					TileEntityBase b = (TileEntityBase)te;
					b.onKill();
				}
			}
		}
	}

	public class BlockFuelBurner extends BlockContainerBase {

		public BlockFuelBurner(String name, Material material) {
			super(name, material);
		}

		@Override
		public TileEntity createNewTileEntity (World world)
		{
			return new TileEntityFuelBurner();
		}

	}

	@Deprecated
	//TODO: Make some implosions
	//A block that renders some of its sides with a tank texture that varies.
	//Used for the Hemoionic Dynamo and the Blood Donation Block.
	public abstract class BlockMetaTank extends BlockContainerBase {

		protected ArrayList<String> tankTexNames = new ArrayList<String>(8);
		protected ArrayList<String> topTexNames = new ArrayList<String>(2);

		@SideOnly(Side.CLIENT)
		protected ArrayList<IIcon> tankIcons;

		@SideOnly(Side.CLIENT)
		protected ArrayList<IIcon> topIcons;

		public void addSidesTextureName(String str) {
			tankTexNames.add(str);
		}
		public void addTopTextureName(String str) {
			topTexNames.add(str);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister IIconRegister) {
			super.registerBlockIcons(IIconRegister);

			//Register tank IIcons.
			tankIcons = new ArrayList<IIcon>(tankTexNames.size());
			for(int i = 0; (i < tankTexNames.size()) && (i < 16); ++i) {
				tankIcons.add(IIconRegister.registerIcon(tankTexNames.get(i)));
			}
			//Register top IIcons.
			topIcons = new ArrayList<IIcon>(topTexNames.size());
			for(int i = 0; (i < topTexNames.size()) && (i < 16); ++i) {
				topIcons.add(IIconRegister.registerIcon(topTexNames.get(i)));
			}
			if(topIcons.size() < tankIcons.size()) { //Fill in the blanks
				IIcon last = topIcons.get(topIcons.size() - 1);
				while(topIcons.size() < tankIcons.size()) {
					topIcons.add(last);
				}
			} else if(topIcons.size() > tankIcons.size()) { //Fill in the blanks
				IIcon last = tankIcons.get(tankIcons.size() - 1);
				while(topIcons.size() > tankIcons.size()) {
					tankIcons.add(last);
				}
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public IIcon getIcon(int side, int metadata) {
			if(side == 1) { //Top
				return topIcons.get(metadata);
			} else if(side == 0) { //Bottom
				return this.blockIcon;
			} else { //Sides (tank)
				return tankIcons.get(metadata);
			}
		}

		public void setMetaByFillPercent(World world, int x, int y, int z, int fillPercent) {
			world.setBlockMetadataWithNotify(x, y, z, (fillPercent * (tankTexNames.size()-1))/100, 1|2);
		}
		public BlockMetaTank(String name, Material material) {
			super(name, material);
		}
		public BlockMetaTank(String name) {
			super(name);
		}
		public BlockMetaTank(Material material) {
			super(material);
		}
	}

	public class BlockNitrateEngine extends BlockContainerBase {

		int teCapacity = 0;
		int tePerTick = 0;
		int tePerDirt = 0;

		private final Random itemDropRand = new Random(); // Randomize item drop direction.

		public /*static*/ Fluid waste = null;

		@SideOnly(Side.CLIENT)
		public IIcon frontIcon;
		@SideOnly(Side.CLIENT)
		public IIcon frontIconPowered;
		@SideOnly(Side.CLIENT)
		public IIcon topIcon;
		@SideOnly(Side.CLIENT)
		public IIcon sidesIcon;

		/**
		 * Args: side, metadata
		 */
		@SideOnly(Side.CLIENT)
		@Override
		public IIcon getIcon(int side, int meta) {
			if (side == 1) {
				return topIcon;
			} else if (side == 0) {
				return sidesIcon;
			} else if (side == (meta & ~8)) {
				// Is it powered?
				if ((meta & 8) > 0) {
					return frontIconPowered;
				} else {
					return frontIcon;
				}
			} else {
				return sidesIcon;
			}
		}

		protected /*static*/ void initRotate(BlockNitrateEngine block) {
			BlockHelper.rotateType[getIdFromBlock(block)] = BlockHelper.RotationType.CHEST;
		}

		@Override
		public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
			// Dumb hacks ahoy. Should really find a better (but still non-verbose) way to do this.
			return RotationHelper.getValidVanillaBlockRotations(Blocks.furnace);
		}

		@Override
		public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
			// Dumb hacks ahoy. Should really find a better (but still non-verbose way to do this.
			return RotationHelper.rotateVanillaBlock(Blocks.furnace, worldObj,
					x, y, z, axis);
		}

		public boolean hasComparatorInputOverride() {
			return true;
		}


		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister iconRegister) {
			frontIcon = iconRegister.registerIcon("gui:genericmachine5");
			sidesIcon = iconRegister.registerIcon("gui:genericmachine");
			topIcon = iconRegister.registerIcon("gui:genericmachine3");
			frontIconPowered = iconRegister.registerIcon("gui:genericmachine5_active");
		}

		@Override
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack thisItemStack) {
			int quadrant = (int) ((placer.rotationYaw * 4.0F / 360.0F) + 0.5F);

			// Modulo out any 360 degree dealies.
			quadrant %= 4;

		/*
		 * public static final ForgeDirection[] VALID_DIRECTIONS = {DOWN, UP,
		 * NORTH, SOUTH, WEST, EAST}; 0 1 2 3 4 5
		 */
			if (quadrant == 0) { // Facing south
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			} else if (quadrant == 1) { // Facing west
				world.setBlockMetadataWithNotify(x, y, z, 5, 2);
			} else if (quadrant == 2) { // Facing north
				world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			} else if (quadrant == 3) { // Facing east
				world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			}
		}

		public /*static*/ void setWaste(Fluid w) {
			waste = w;
		}

		@Override
		public TileEntity createNewTileEntity(World world) {
			TileEntityNitrateEngine TE = new TileEntityNitrateEngine();
			TE.setWaste(waste);
			return TE;
		}

		/**
		 * If hasComparatorInputOverride returns true, the return value from this is
		 * used instead of the redstone signal strength when this block inputs to a
		 * comparator.
		 */
		public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
			return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
		}

		@Override
		public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
			TileEntity te = world.getTileEntity(x, y, z);
			if ((te != null) && (te instanceof TileEntityNitrateEngine)) {
				TileEntityNitrateEngine tileentity = (TileEntityNitrateEngine) te;
				for (int slotiter = 0; slotiter < tileentity.getSizeInventory(); ++slotiter) {
					ItemStack itemstack = tileentity.getStackInSlot(slotiter);
					if (itemstack != null) {
						float xr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
						float yr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
						float zr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
						EntityItem entityItem = new EntityItem(world, (double) ((float) x + xr), (double) ((float) y + yr), (double) ((float) z + zr), itemstack);
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
			super.onBlockDestroyedByPlayer(world, x, y, z, par5);
		}

		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}
			player.openGui(oldcode.projectfluxgear.ProjectFluxGear.instance, 0, world, x, y, z);
			return true;
		}

		public void recievePowerOn(World world, int x, int y, int z) {
			// Bitmask bit 8 to on
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 2);
		}

		public void recievePowerOff(World world, int x, int y, int z) {
		/*
		 * Bitmask bit 8 to off by &ing it with the bitwise complement of 8
		 * (which is to say ~8).
		 */
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) & ~8, 2);
		}

		public BlockNitrateEngine(String name, Material material) {
			super(name, material);
		}
	}

	//A copy-and-paste from BlockNitrateEngine.
	//Soon I will abstract this functionality out
	//to a BlockContainerRotatable or something like that.
	public class BlockGunpowderEngine extends BlockContainerBase {

		public BlockGunpowderEngine(String name, Material material) {
			super(name, material);
		}

		int teCapacity = 0;
		int tePerTick = 0;
		int tePerDirt = 0;

		private final Random itemDropRand = new Random(); // Randomize item drop direction.

		@SideOnly(Side.CLIENT)
		public IIcon frontIcon;

		@SideOnly(Side.CLIENT)
		public IIcon frontIconPowered;

		@SideOnly(Side.CLIENT)
		public IIcon topIcon;

		@SideOnly(Side.CLIENT)
		public IIcon sidesIcon;

		/**
		 * Args: side, metadata
		 */
		@SideOnly(Side.CLIENT)
		@Override
		public IIcon getIcon(int side, int meta) {
			if (side == 1) {
				return topIcon;
			} else if (side == 0) {
				return sidesIcon;
			} else if (side == (meta & ~8)) {
				// Is it powered?
				if ((meta & 8) > 0) {
					return frontIconPowered;
				} else {
					return frontIcon;
				}
			} else {
				return sidesIcon;
			}
		}

		protected /*static*/ void initRotate(BlockGunpowderEngine block) {
			BlockHelper.rotateType[getIdFromBlock(block)] = BlockHelper.RotationType.CHEST;
		}

		@Override
		public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
			// Dumb hacks ahoy. Should really find a better (but still non-verbose) way to do this.
			return RotationHelper.getValidVanillaBlockRotations(Blocks.furnace);
		}

		@Override
		public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
			// Dumb hacks ahoy. Should really find a better (but still non-verbose) way to do this.
			return RotationHelper.rotateVanillaBlock(Blocks.furnace, worldObj, x, y, z, axis);
		}

		public boolean hasComparatorInputOverride() {
			return true;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
			return this.getIcon(side, world.getBlockMetadata(x, y, z));
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister iconRegister) {
			//TODO: Visually differentiate this from the Nitrate Engine.
			frontIcon = iconRegister.registerIcon("gui:genericmachine5");
			sidesIcon = iconRegister.registerIcon("gui:genericmachine");
			topIcon = iconRegister.registerIcon("gui:genericmachine3");
			frontIconPowered = iconRegister.registerIcon("gui:genericmachine5_active");
		}

		@Override
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack thisItemStack) {
			int quadrant = (int) ((placer.rotationYaw * 4.0F / 360.0F) + 0.5F);

			// Modulo out any 360 degree dealies.
			quadrant %= 4;

		/*
		 * public static final ForgeDirection[] VALID_DIRECTIONS = {DOWN, UP,
		 * NORTH, SOUTH, WEST, EAST}; 0 1 2 3 4 5
		 */

			if (quadrant == 0) { // Facing south
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			} else if (quadrant == 1) { // Facing west
				world.setBlockMetadataWithNotify(x, y, z, 5, 2);
			} else if (quadrant == 2) { // Facing north
				world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			} else if (quadrant == 3) { // Facing east
				world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			}
		}

		/**
		 * If hasComparatorInputOverride returns true, the return value from this is
		 * used instead of the redstone signal strength when this block inputs to a
		 * comparator.
		 */
		public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
			return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
		}

		//Toss away all item stacks on block break.

		/**/@Override
		public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null) {
				if (te instanceof TileEntityNitrateEngine) {
					TileEntityNitrateEngine tile = (TileEntityNitrateEngine) te;
					for (int slotiter = 0; slotiter < tile.getSizeInventory(); ++slotiter) {
						ItemStack itemstack = tile.getStackInSlot(slotiter);
						if (itemstack != null) {
							float xr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
							float yr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
							float zr = this.itemDropRand.nextFloat() * 0.8F + 0.1F;
							EntityItem entityItem = new EntityItem(world,
									(double) ((float) x + xr),
									(double) ((float) y + yr),
									(double) ((float) z + zr), itemstack);
							world.spawnEntityInWorld(entityItem);
						}
					}
				}
			}
			super.onBlockDestroyedByPlayer(world, x, y, z, par5);
		}/**/

		public boolean onBlockActivated(World world, int x, int y, int z,
		                                EntityPlayer player, int metadata, float par1, float par2,
		                                float par3) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}
			//TODO: A GUI for this block.
			player.openGui(oldcode.projectfluxgear.ProjectFluxGear.instance, 0, world, x, y, z);
			return true;
		}

		/**/ //@Override
		public void recievePowerOn(World world, int x, int y, int z) {
			// Bitmask bit 8 to on
			world.setBlockMetadataWithNotify(x, y, z,
					world.getBlockMetadata(x, y, z) | 8, 2);

		}

		//@Override
		public void recievePowerOff(World world, int x, int y, int z) {
		/*
		 * Bitmask bit 8 to off by &ing it with the bitwise complement of 8
		 * (which is to say ~8).
		 */
		/**/world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) & ~8, 2);
		}/**/
	}

	public class BlockBloodEngine extends BlockMetaTank {

		protected static final String fuelName= "blood";

		@Override
		public TileEntity createNewTileEntity(World world) {
			return new TileEntityBloodDynamo();
		}

		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3) {
			TileEntityBloodDynamo tileEntity = null;
			if(world.getTileEntity(x, y, z) instanceof TileEntityBloodDynamo) {
				tileEntity = (TileEntityBloodDynamo)world.getTileEntity(x, y, z);
			}
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}
			ItemStack playerItem = player.inventory.getCurrentItem();

			//Check to see if the player is holding something that can be filled with a fluid.
			if (playerItem != null) {
				//Is the player's item THE ONE TRUE ITEM we're lookin' for?
				//First try the Zettabyte version.
				if (playerItem.getItem() instanceof ItemBucket) {
					ItemBucket bucket = (ItemBucket)playerItem.getItem();
					//Do deep black Worst Practices hoodoo because Notch didn't design anything to be extended or generalized
					//Lucky for us the performance impact of reflection is negligable in this case since there won't be
					//instances of right clicking on an engine with a bucket very often.
					Block dumpedBlock = null;
					try {
						Field f = ItemBucket.class.getDeclaredField("isFull"); //NoSuchFieldException if there is no isFull
						f.setAccessible(true);
						dumpedBlock = (Block) f.get(bucket);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//Does this bucket contain a fluid block?
					if(dumpedBlock instanceof IFluidBlock) {
						Fluid fluidTry = ((IFluidBlock)dumpedBlock).getFluid();
						//Is it blood?
						if(fluidTry.getName().contentEquals(fuelName)) {
							if(FillTank(fluidTry, tileEntity) && !player.capabilities.isCreativeMode) {
								player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.EMPTY_BUCKET);
							}
							return true;
						}
					}
					//Now that we've tried one wonky kind of reflection, try another for TCon compat.
					if(playerItem.getItem().getUnlocalizedName().contentEquals("item.tconstruct.bucket")) {
						//Deeper voodoo begins here.
						Block[] fluidBlockArry = null;
						try {
							Class tcontent = Class.forName("tconstruct.common.TContent");
							Field f = tcontent.getDeclaredField("fluidBlocks");
							fluidBlockArry = (Block[]) f.get(null);
						} catch (Exception e) {
							//do nothing, all this means is that Tinker's Construct is not loaded.
						}
						if(fluidBlockArry[playerItem.getItemDamage()].getUnlocalizedName().contentEquals("tile.liquid.blood")) {
							Fluid fluidTry = FluidRegistry.getFluid("blood");
							if(FillTank(fluidTry, tileEntity) && !player.capabilities.isCreativeMode) {
								player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.EMPTY_BUCKET);
							}
							return true;
						}
					}
				}
			}
			//The player is not holding a blood bucket.
			player.openGui(oldcode.projectfluxgear.ProjectFluxGear.instance, guiID, world, x, y, z);
			return true;
			return false;
		}

		public boolean FillTank(Fluid f, TileEntityBloodDynamo tileEntity) {
			//Get the amount of fluid we're trying to put in the TE.
			FluidStack toTry = new FluidStack(f, 1000);
			//First simulate the filling to make sure we can fit a whole bucket of blood in the engine.
			if(tileEntity.fill(ForgeDirection.UP, toTry, false) == 1000) {
				//Then, do it for real.
				tileEntity.fill(ForgeDirection.UP, toTry, true);
				//If they are not god they do not get infinite blood bucket.
				return true;
			}
			return false;
		}

		public BlockBloodEngine(String name, Material material) {
			super(name, material);
		}

		public BlockBloodEngine(String name) {
			super(name);
		}

	}

	//TODO: Particle effect when an appropriate block is on top of this engine.
	public class BlockOccultEngine extends BlockBloodEngine {

		public /*static*/ ArrayList<String> idols = new ArrayList<String>(2);

		private void initIdols(){
			idols.add(Blocks.skull.getUnlocalizedName());
			idols.add(Blocks.dragon_egg.getUnlocalizedName());
		}

		public void recievePowerOn(World world, int x, int y, int z) {
		}

		public void recievePowerOff(World world, int x, int y, int z) {
		}

		@Override
		public TileEntity createNewTileEntity(World world) {
			TileEntityOccultEngine te = new TileEntityOccultEngine();
			return te;
		}

		@Override
		public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
			super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
			updateIdol(world, x, y, z);
		}

		private void updateIdol(IBlockAccess world, int x, int y, int z) {
			Block b = world.getBlock(x, y+1, z);
			if(b != null) {
				for(String s : idols) {
					if(s.contentEquals(b.getUnlocalizedName())) {
						if(b.getUnlocalizedName().contentEquals(Blocks.skull.getUnlocalizedName())) {
							//Special case for the wither skull
							TileEntity teUp = world.getTileEntity(x, y+1, z);
							if((teUp != null) && (teUp instanceof TileEntitySkull)) {
								TileEntitySkull teS = (TileEntitySkull) teUp;
								if(teS.getBlockMetadata() == 1) {
									TileEntity te = world.getTileEntity(x, y, z);
									if((te != null) && (te instanceof TileEntityOccultEngine)) {
										((TileEntityOccultEngine)te).updateCurrentIdol(b.getUnlocalizedName());
										return;
									}
								}
							}
						} else {
							//Every other block.
							TileEntity te = world.getTileEntity(x, y, z);
							if((te != null) && (te instanceof TileEntityOccultEngine)) {
								((TileEntityOccultEngine)te).updateCurrentIdol(b.getUnlocalizedName());
							}
						}
					}
				} if((b == null) || (b == Blocks.air)) {
					TileEntity te = world.getTileEntity(x, y, z);
					if((te != null) && (te instanceof TileEntityOccultEngine)) {
						((TileEntityOccultEngine)te).updateCurrentIdol(null);
					}

				}
			} else {
				TileEntity te = world.getTileEntity(x, y, z);
				if((te != null) && (te instanceof TileEntityOccultEngine)) {
					((TileEntityOccultEngine)te).updateCurrentIdol(null);
				}
			}
		}

		public BlockOccultEngine(String name, Material material) {
			super(name, material);
		}
	}

	/* *=-=-=-=* Fluid Blocks *=-=-=-=* */

	@Deprecated
	public class BlockFluidClassicWS extends BlockFluidClassic {

		//Which blocks are displaced by this one?
		protected HashMap<Block, Boolean> displacementBlocks = new HashMap<Block, Boolean>();

		public BlockFluidClassicWS(String name, Material material, Fluid fluid) {
		/*
		 * Real version of the constructor. Ultimately all other versions of the constructor turn into this.
		 * In this line, config looks up the block ID with a setting based upon the name argument.
		 */
			super(fluid, material);
			englishName = name;
		}

		public BlockFluidClassicWS(Material material, Fluid fluid) {
		/*
		 * A variant of BlockBase(Configuration config, String name) with Material specified.
		 * Probably the constructor of choice for an easy development cycle (i.e. lines of
		 * code to init a block will be lowest here).
		 */
			super(fluid, material);
		}

		public BlockFluidClassicWS(Fluid fluid) {
			//Dumb version of the constructor. Use this if you want to make life harder for yourself.
			super(fluid, Material.water);
		}

		protected String englishName;
		boolean entitiesInteract = true;
		public boolean isEntitiesInteract() {
			return entitiesInteract;
		}

		public void setEntitiesInteract(boolean entitiesInteract) {
			this.entitiesInteract = entitiesInteract;
		}

		@Override
		public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
			if(entitiesInteract & (getFluid() instanceof IBioactive)) {
				IBioactive bioFluid = (IBioactive)getFluid();
				if(entity instanceof EntityLivingBase) {
					bioFluid.contactAffectCreature((EntityLivingBase)entity);
				}
			}
		}
		public void addDisplacementEntry(Block b, boolean isDisplaced) {
			this.displacementBlocks.put(b, isDisplaced);
		}

		public boolean isEnabled() {
			return true;
		}

		public void setMaterial(Material m) {
			//Deep dark voodoo. If you get a security exception, here it is. I'm sorry, Gyro says he did it all for the greater good.
			Field field;
			try {
				//Get the field of the block class.
				field = Block.class.getField("blockMaterial");
				field.setAccessible(true);

				//Modify the field to not be final.
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

				field.set(this, m);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

		@Override
		public int getHarvestLevel(int subBlockMeta) {
			//By default, no metadata-based sub-blocks.
			return 0;
		}

	}

	@Deprecated
	//TODO: Something, something, make our own fluid handling...
	//TODO: Code for corroding blocks.
	public class BlockFluidReactive extends BlockFluidClassicWS implements IReactionReceiver {

		protected ArrayList<IReactionSpec> Reactions = new ArrayList<IReactionSpec>(12);
		protected ArrayList<ItemStack> solutesItems = new ArrayList<ItemStack>(4);
		protected ArrayList<Fluid> solutesFluids = new ArrayList<Fluid>(4);
		protected ArrayList<Block> solutesBlocks = new ArrayList<Block>(4);

		@Override
		public boolean registerReaction (IReactionSpec react) {
			if (react.getSolvent().getName().contentEquals(this.getFluid().getName())) {
				Reactions.add(react);
				// Build sub-lists for sanity here:
				// Items
				if (react.getSolute() instanceof ItemStack) {
					solutesItems.add((ItemStack) react.getSolute());
				} else if (react.getSolute() instanceof Item) {
					solutesItems.add(new ItemStack((Item) react.getSolute()));
				} else {
					solutesItems.add(null);
				}
				// Blocks
				if (react.getSolute() instanceof Block) {
					solutesBlocks.add((Block) react.getSolute());
				} else {
					solutesBlocks.add(null);
				}
				// Fluids
				if (react.getSolute() instanceof Fluid) {
					solutesFluids.add((Fluid) react.getSolute());
				} else {
					solutesFluids.add(null);
				}
				// End list building.
				return true;
			} else {
				return false;
			}
		}

		// Herein is our reaction code.
		@Override
		public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity) {
			super.onEntityCollidedWithBlock(world, x, y, z, entity);
			if ((!world.isRemote) && (this.isSourceBlock(world, x, y, z)) && (entity instanceof EntityItem)) {
				// Do item reactions.
				EntityItem entityItem = (EntityItem) entity;
				ItemStack itemStack = entityItem.getEntityItem();
				if (solutesItems.size() > 0) {
					for (int i = 0; i < solutesItems.size(); ++i) {
						// And now, the meat of the reaction code.
						// Is this reaction possible for us?
						// Do we have the minimum amount of solute needed?
						if ((solutesItems.get(i) != null) && (itemStack.getItem().getUnlocalizedName(itemStack).contentEquals(solutesItems.get(i).getItem().getUnlocalizedName(solutesItems.get(i)))) && (1000 >= Reactions.get(i).getSolventMin()) && (itemStack.stackSize >= Reactions.get(i).getSoluteMin())) {
							// Mess with the item stack.
							if (Reactions.get(i).isSoluteAffected()) {
								if (Reactions.get(i).getSoluteTarget() != null) {
									// Make sure we can do something sane here.
									if (Reactions.get(i).getSoluteTarget() instanceof ItemStack) {
										ItemStack newItemStack = ((ItemStack) Reactions.get(i).getSoluteTarget()).copy();
										newItemStack.stackSize *= itemStack.stackSize;

										// Spawn our new item.
										EntityItem newEntityItem = new EntityItem(world, entityItem.posX, entityItem.posY, entityItem.posZ, newItemStack);
										newEntityItem.motionX = entityItem.motionX;
										newEntityItem.motionY = entityItem.motionY;
										newEntityItem.motionZ = entityItem.motionZ;
										newEntityItem.delayBeforeCanPickup = entityItem.delayBeforeCanPickup;
										world.spawnEntityInWorld(newEntityItem);

										entityItem.setDead();
									}
								} else {
									// Destroys solute.
									entityItem.setDead();
								}
							}
							// Mess with this block.
							if (Reactions.get(i).isSolventAffected()) {
								if (Reactions.get(i).getSolventTarget() != null) {
									// Try to do the item thing.
									if (Reactions.get(i).getSolventTarget() instanceof ItemStack) {
										ItemStack newItemStack = ((ItemStack) Reactions.get(i).getSolventTarget()).copy();

										// Spawn our new item.
										EntityItem newEntityItem = new EntityItem(world, entityItem.posX, entityItem.posY, entityItem.posZ, newItemStack);
										entityItem.motionX = 0;
										entityItem.motionY = 0;
										entityItem.motionZ = 0;
										world.spawnEntityInWorld(newEntityItem);
									} else { // Try to do the block thing.
										if (Reactions.get(i).getSolventTarget() instanceof Block) {
											Block newBlock = (Block) Reactions.get(i).getSolventTarget();
											world.setBlock(x, y, z, newBlock);
										} else if (Reactions.get(i).getSolventTarget() instanceof Fluid) {
											// If it's a fluid we still try to do the block thing.
											Block newBlock = ((Fluid) Reactions.get(i).getSolventTarget()).getBlock();
											world.setBlock(x, y, z, newBlock);
										}
									}
								} else {
									// Set this block to air if solvent is affected and there is a null target.
									world.setBlock(x, y, z, Blocks.air);
								}
							}
						}
					}
				}
			}
		}

		public BlockFluidReactive(Fluid fluid) {
			super(fluid);
			// TODO Auto-generated constructor stub
		}

		public BlockFluidReactive(Material material, Fluid fluid) {
			super(material, fluid);
			// TODO Auto-generated constructor stub
		}

		public BlockFluidReactive(String name, Material material, Fluid fluid) {
			super(name, material, fluid);
			// TODO Auto-generated constructor stub
		}


	}

	//TODO: Make CoFH Style
	public class BlockFluidAcid extends Fluid implements IBioactive {

		public int damage = 4;
		public BlockFluidAcid(String fluidName) {
			super(fluidName);
		}

		@Override
		public boolean contactAffectCreature(EntityLivingBase affected) {
			affected.attackEntityFrom(DamageSource.magic, (float)damage);
			return true;
		}

		@Override
		public boolean drinkAffectCreature(EntityLivingBase affected) {
			//Don't drink acid.
			affected.attackEntityFrom(DamageSource.magic, (float)damage*2);
			return true;
		}

		@Override
		public boolean bloodstreamAffectCreature(EntityLivingBase affected) {
			//Holy shit what are you doing?
			affected.attackEntityFrom(DamageSource.magic, (float)damage*4);
			return true;
		}

		@Override
		public boolean breatheAffectCreature(EntityLivingBase affected) {
			//Doesn't seem possible, but eh.
			//If there are going to be Space Station 13 style shenanigans wherein we put acid in somebody's
			//oxygen tank, we should start laying the foundations for that now.
			return bloodstreamAffectCreature(affected);
		}

		@Override
		public boolean canContactAffectCreature() {
			return true;
		}

		@Override
		public boolean canDrinkAffectCreature() {
			return true;
		}

		@Override
		public boolean canBloodstreamAffectCreature() {
			return true;
		}

		@Override
		public boolean canBreatheAffectCreature() {
			return true;
		}
	}

	public class BlockFluidSmog extends BlockFluidCoFHBase implements IBioactive {

		public static final int LEVELS = 6;


		private /*static*/ boolean enableSourceFloat = true;
		private /*static*/ int maxSmogHeight = 256;

		public BlockFluidSmog(String fluidName) {
			super("thermaltinkerer", FluxGearContent.fluidSmog, materialFluidSmog, "smog");
			setQuantaPerBlock(LEVELS);
			setTickRate(20);

			setHardness(1F);
			setLightOpacity(0);
			setParticleColor(1.0F, 0.9F, 0.05F);
		}

		@Override
		public boolean preInit() {

			GameRegistry.registerBlock(this, "FluidSmog");
			return true;
		}

		/**/@Override
		public void updateTick(World world, int x, int y, int z, Random rand) {

			if (world.getBlockMetadata(x, y, z) == 0) {
				if (rand.nextInt(3) == 0) {
					if (shouldSourceBlockDisapate(world, x, y, z)) {
						world.setBlock(x, y, z, null);
						return;
					}
					if (shouldSourceBlockFloat(world, x, y, z)) {
						world.setBlock(x, y + densityDir, z, this, 0, 3);
						world.setBlockToAir(x, y, z);
						return;
					}
				}
			} else if (y + densityDir > maxSmogHeight) {

				int quantaRemaining = quantaPerBlock - world.getBlockMetadata(x, y, z);
				int expQuanta = -101;
				int y2 = y - densityDir;

				if (world.getBlock(x, y2, z) == this || world.getBlock(x - 1, y2, z) == this || world.getBlock(x + 1, y2, z) == this
						|| world.getBlock(x, y2, z - 1) == this || world.getBlock(x, y2, z + 1) == this) {
					expQuanta = quantaPerBlock - 1;

				} else {
					int maxQuanta = -100;
					maxQuanta = getLargerQuanta(world, x - 1, y, z, maxQuanta);
					maxQuanta = getLargerQuanta(world, x + 1, y, z, maxQuanta);
					maxQuanta = getLargerQuanta(world, x, y, z - 1, maxQuanta);
					maxQuanta = getLargerQuanta(world, x, y, z + 1, maxQuanta);

					expQuanta = maxQuanta - 1;
				}
				// decay calculation
				if (expQuanta != quantaRemaining) {
					quantaRemaining = expQuanta;
					if (expQuanta <= 0) {
						world.setBlockToAir(x, y, z);
					} else {
						world.setBlockMetadataWithNotify(x, y, z, quantaPerBlock - expQuanta, 3);
						world.scheduleBlockUpdate(x, y, z, this, tickRate);
						world.notifyBlocksOfNeighborChange(x, y, z, this);
					}
				}
				return;
			}
			super.updateTick(world, x, y, z, rand);
		}/**/

		protected boolean shouldSourceBlockFloat(World world, int x, int y, int z) {

			return enableSourceFloat && (world.getBlock(x, y + densityDir, z) == this && world.getBlockMetadata(x, y + densityDir, z) != 0);
		}


		@Override
		public boolean contactAffectCreature(EntityLivingBase affected) {
			return false;
		}

		@Override
		public boolean drinkAffectCreature(EntityLivingBase affected) {
			return breatheAffectCreature(affected);
		}

		@Override
		public boolean bloodstreamAffectCreature(EntityLivingBase affected) {
			return breatheAffectCreature(affected);
		}

		@Override
		public boolean breatheAffectCreature(EntityLivingBase affected) {
			if(affected != null) {
				affected.addPotionEffect(new PotionEffect(Potion.poison.id, 250, 1));
				affected.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 250, 1));
				return true;
			}
			return false;
		}

		@Override
		public boolean canContactAffectCreature() {
			return false;
		}

		@Override
		public boolean canDrinkAffectCreature() {
			return true;
		}

		@Override
		public boolean canBloodstreamAffectCreature() {
			return true;
		}

		@Override
		public boolean canBreatheAffectCreature() {
			return true;
		}

	}

	public class BlockFluidGhastTear extends BlockFluidCoFHBase {

		public static final int LEVELS = 6;

		private /*static*/ boolean enableSourceFloat = false;
		private /*static*/ boolean effect = true;

		public BlockFluidGhastTear() {
			super("thermaltinkerer", FluxGearContent.fluidGhastTear, materialFluidGhastTear, "ghastTear");
			setQuantaPerBlock(LEVELS);
			setTickRate(30);

			setHardness(100F);
			setLightOpacity(1);
			setParticleColor(0.9F, 0.9F, 0.85F);
		}

		@Override
		public boolean preInit() {
			GameRegistry.registerBlock(this, "FluidGhastTears");
			return true;
		}
	}

	/* *=-=-=-=* Other Blocks *=-=-=-=* */

	public class BlockPaintedStone extends Block {
		public final String textureName;
		public final String localName;
		public IIcon[] icons;
		Block dropBlock;

		public BlockPaintedStone(Material material, float hardness, String texture, String name) {
			super(material);
			setHardness(hardness).setCreativeTab(CreativeTabs.tabDecorations);
			textureName = texture;
			localName = name;
			dropBlock = this;
		}

		public BlockPaintedStone(Material material, float hardness, String texture, String name, Block dropBlock) {
			this(material, hardness, texture, name);
			this.dropBlock = dropBlock;
		}

		public BlockPaintedStone(String texture, String name) {
			this(Material.rock, 1.5F, texture, name);
		}

		public String getUnlocalizedName() {
			return "tile." + localName;
		}

		public int damageDropped(int meta) {
			return meta;
		}

		public Block blockDropped(int par1, Random par2Random, int par3) {
			return dropBlock;
		}

		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister) {
			icons = new IIcon[16];

			for(int i = 0; i < this.icons.length; ++i) {
				this.icons[i] = iconRegister.registerIcon("mortvana.mechstoneworks:" + textureName + "_" + oldcode.projectfluxgear.ProjectFluxGear.colorNames[i]);
			}

		}

		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta) {
			return meta < icons.length ? icons[meta] : icons[0];
		}

		public void getSubBlocks(Item block, CreativeTabs tab, List list) {
			for(int iter = 0; iter < this.icons.length; ++iter) {
				list.add(new ItemStack(block, 1, iter));
			}

		}
	}

	public class BlockDecorStone extends Block {
		public IIcon[] icons;
		public float[] hardness;
		public float[] resistance;
		public int[] blockLight;
		Block dropBlock;

		public String overlayType;
		@SideOnly(Side.CLIENT)
		public IIcon overlayTexture;

		public BlockDecorStone(Material material, CreativeTabs tab, float[] hardness, float[] resistance, int[] light, String overlayType, SoundType sound) {
			super(material);
			setCreativeTab(tab);
			setStepSound(sound);
			this.hardness = hardness;
			this.resistance = resistance;
			blockLight = light;
			dropBlock = this;
			this.overlayType = overlayType;

		}

		public BlockDecorStone(Material material, CreativeTabs tab, float[] hardness, float[] resistance, int[] light, String overlayType, SoundType sound, Block dropBlock) {
			this(material, tab, hardness, resistance, light, overlayType, sound);
			this.dropBlock = dropBlock;
		}

		@Override
		public void getSubBlocks(Item item, CreativeTabs tab, List list) {

			for (int i = 0; i < rockNames.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z) {

			return blockLight[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public float getBlockHardness(World world, int x, int y, int z) {

			return hardness[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

			return resistance[world.getBlockMetadata(x, y, z)];
		}

		public Block blockDropped(int par1, Random random, int par3) {
			return dropBlock;
		}


		/* Convoluted Overlay Renderage Code */
		@Override
		public boolean renderAsNormalBlock() {
			// We don't render as a normal block, we are special and use a custom renderer
			return false;
		}

		@Override
		public boolean isOpaqueCube() {
			// Would return false if it was transparent, but it is just for overlays this time
			return true;
		}

		@Override
		public int getRenderType() {
			// We are a 1*1*1 cube, we just have TWO render passes
			return ClientProxy.dualPassCubeID;
		}

		@Override
		public boolean canRenderInPass(int pass) {
			// Set the static variable in our Client Proxy
			ClientProxy.renderPass = pass;
			// The block utilizes both passes, so it is always true
			return true;
		}

		@Override
		public int getRenderBlockPass() {
			// 0 for Solids; 1 for Alpha
			// This renders a darkening overlay, so it is alpha
			return 1;
		}

		public String getUnlocalizedName() {
			return "tile." + localName;
		}

		public int damageDropped(int meta) {
			return meta;
		}

		public IIcon getOverlayTexture() {
			return overlayTexture;
		}

		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister) {
			this.icons = new IIcon[rockNames.length];
			for(int i = 0; i < this.icons.length; ++i) {
				this.icons[i] = iconRegister.registerIcon("mechanicsstoneworks:rocks/" + rockNames[i]);
			}
			overlayTexture = iconRegister.registerIcon("mechanicsstoneworks:overlays/" + overlayType);
		}

		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta) {
			return meta < icons.length ? icons[meta] : icons[0];
		}

	}

	public class BlockFakeAir extends FluxGearBlock {
		public BlockFakeAir() {
			super(Config.airyMaterial, mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setTickRandomly(true).setLightLevel(15).setStepSound(new SoundType("cloth", 0.0F, 1.0F)).setBlockName("blockThaumicAir").setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
		}

		// Can't touch this
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
			return null;
		}

		// I'm different
		public boolean renderAsNormalBlock() {
			return false;
		}

		public boolean isOpaqueCube() {
			return false;
		}

		public Item getItemDropped(int par1, Random par2Random, int par3) {
			return null;
		}

		public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
			return false;
		}

		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister register) {
			blockIcon = register.registerIcon("thaumcraft:blank");
		}

		@SideOnly(Side.CLIENT)
		public void getIcon() {}
	}

	public class BlockWitor extends BlockContainer {

		public BlockWitor() {

			super(Config.airyMaterial);
			setBlockName("blockWitor");
			setStepSound(Block.soundTypeCloth);
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
			setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);

		}



		@Override
		public int getRenderType() {
			return -1;
		}

		@Override
		public int getLightValue() {
			return 15;
		}

		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			return new TileWitor();
		}


	}

	public class TileWitor extends TileEntity {

		public boolean canUpdate() {return true;}

		public void updateEntity() {
			super.updateEntity();
			if (this.worldObj.isRemote) {
				if (this.worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
					Thaumcraft.proxy.wispFX3(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, this.yCoord + 0.5F, this.zCoord + 0.3F + this.worldObj.rand.nextFloat() * 0.4F, 0.5F, 0, true, -0.025F);
				}

				if (this.worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
					Thaumcraft.proxy.wispFX3(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, this.xCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, this.yCoord + 0.5F, this.zCoord + 0.4F + this.worldObj.rand.nextFloat() * 0.2F, 0.25F, 2, true, -0.02F);
				}
			}
		}
	}

	public class BlockTimeyWimey extends BlockTorch implements ITileEntityProvider {
		public BlockTimeyWimey() {
			setBlockName("TimeyWimeyTorch").setLightLevel(1.0F).setCreativeTab(CreativeTabs.tabDecorations).setBlockTextureName("FluxGearZee:timeyWimeyTorch");
		}

		@Override
		public void onBlockAdded(World world, int x, int y, int z) {
			if (ServerHelper.isServerWorld(world)) {
				TileEntity tile = world.getTileEntity(x, y, z);
				if (tile != null && tile instanceof TileTimeyWimey) {
					((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
				}
			}
			super.onBlockAdded(world, x, y, z);
		}

		@Override
		public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
			if (ServerHelper.isServerWorld(world)) {
				TileEntity tile = world.getTileEntity(x, y, z);
				if (tile != null && tile instanceof TileTimeyWimey) {
					((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
				}
			}
			super.onNeighborBlockChange(world, x, y, z, block);
		}

		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
			if (ServerHelper.isServerWorld(world)) {
				TileEntity tile = world.getTileEntity(x, y, z);

				if (tile != null && tile instanceof TileTimeyWimey) {
					TileTimeyWimey torch = (TileTimeyWimey) tile;

					torch.changeMode(player.isSneaking());

					if (player.isSneaking()) {
						player.addChatComponentMessage(new ChatComponentText("Changed speed: " + torch.getSpeedDescription()));
					} else {
						player.addChatComponentMessage(new ChatComponentText("Changed mode: " + torch.getModeDescription()));
					}
				}
				return false;
			}
			return false;
		}

		@Override
		public TileEntity createNewTileEntity(World world, int i) {
			return new TileTimeyWimey();
		}

	}

	public static class CongealedBloodBlock extends Block implements ISoundProvider {
		private static String unlocalizedName = "congealedBloodBlock";

		private static final String stepSounds[];
		private static final String placeSounds[];

		public static final ArrayList<String> sounds;

		static {
			stepSounds = new String[2];
			for(int i = 0; i < 2; i++) {
				stepSounds[i] = "mortvana.projectfluxgear:step/congealedBloodBlock" + (i + 1) + ".wav";
				System.out.println("PROJECT FLUX GEAR - WEIRD SCIENCE LEGACY DEBUG OUTPUT: " + stepSounds[i]);
			}
			placeSounds = new String[1];
			for(int i = 0; i < 1; i++) {
				placeSounds[i] = "mortvana.projectfluxgear:place/congealedBloodBlock" + (i + 1) + ".wav";
			}

			sounds = new ArrayList<String>();
			sounds.addAll(Arrays.asList(stepSounds));
			sounds.addAll(Arrays.asList(placeSounds));
		}

		public CongealedBloodBlock(Material blockMaterial) {
			super(Material.ground);

			setCreativeTab(oldcode.projectfluxgear.ProjectFluxGear.tabMaterials);
			setBlockName("congealedBloodBlock");
			setHardness(1); //TODO: Proper value for this.
			setResistance(1);
			setBlockTextureName("gui:congealedBloodBlock");
			//setStepSound((StepSound) new WeirdStepSound(placeSoundName, placeSoundName, stepSoundName, 1.0f, 1.0f));
		}

		@Override
		public int getHarvestLevel(int subBlockMeta) {
			return 0;
		}

		public String getHarvestType(int subBlockMeta) {
			return "shovel";
		}

		@Override
		public ArrayList<String> getSounds() {
			return sounds;
		}

	}

	/* *=-=-=-=* Automagically Constructed Blocks *=-=-=-=* */

	public static Block blockOreMain = new BlockFluxGear(Material.rock, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, BlockFluxGear.EnumBlockType.ORE, NAMES_ORES_MAIN, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_MAIN, TEXTURE_LOCATION_ORE).setBlockName("mortvana.projectfluxgear.oreMain");
	public static Block blockOreAux = new BlockFluxGear(Material.rock, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, BlockFluxGear.EnumBlockType.ORE, NAMES_ORES_AUX, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_AUX, TEXTURE_LOCATION_ORE).setBlockName("mortvana.projectfluxgear.oreAux");
	public static Block blockStorageAux = new BlockFluxGear(Material.iron, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_AUX, TEXTURES_FULL, HARDNESS_STORE_AUX, RESISTANCE_STORE_AUX, LIGHT_STORE_AUX, TEXTURE_LOCATION_BLOCK).setBlockName("mortvana.projectfluxgear.storageAux");
	public static Block blockAlloyMain = new BlockFluxGear(Material.iron, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_MAIN, TEXTURES_FULL, HARDNESS_ALLOY_MAIN, RESISTANCE_ALLOY_MAIN, LIGHT_ALLOY_MAIN, TEXTURE_LOCATION_BLOCK).setBlockName("mortvana.projectfluxgear.alloyMain");
	public static Block blockStorageAdv = new BlockFluxGear(Material.iron, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ADV, TEXTURES_FULL, HARDNESS_STORE_ADV, RESISTANCE_STORE_ADV, LIGHT_STORE_ADV, TEXTURE_LOCATION_BLOCK).setBlockName("mortvana.projectfluxgear.storageAdv");
	public static Block blockStorageAlch = new  BlockFluxGear(Material.iron, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ALCH, TEXTURES_FULL, HARDNESS_STORE_ALCH, RESISTANCE_STORE_ALCH, LIGHT_STORE_ALCH, TEXTURE_LOCATION_BLOCK).setBlockName("mortvana.projectfluxgear.storageAux");
	public static Block blockEarthen = new BlockFluxGear(materialSoilOre, oldcode.projectfluxgear.ProjectFluxGear.tabMaterials, EnumBlockType.SOIL_ORE, NAMES_EARTH, TEXTURES_EARTH, HARDNESS_EARTH, RESISTANCE_EARTH, LIGHT_EARTH, TEXTURE_LOCATION_DEFAULT).setBlockName("mortvana.projectfluxgear.earthen");
	public static Block blockPoorOreMain = new BlockFluxGear(Material.rock, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, EnumBlockType.ORE, NAMES_ORES_MAIN, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_MAIN, TEXTURE_LOCATION_POOR_ORE).setBlockName("mortvana.projectfluxgear.poorOreMain");
	public static Block blockPoorOreAux = new BlockFluxGear(Material.rock, oldcode.projectfluxgear.ProjectFluxGear.tabWorld, EnumBlockType.ORE, NAMES_ORES_AUX, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_AUX, TEXTURE_LOCATION_POOR_ORE).setBlockName("mortvana.projectfluxgear.poorOreAux");

	public static ItemBlock itemblockOreMain = new ItemBlockFluxGear(blockOreMain, unlocOre, NAMES_ORES_MAIN, RARITY_ORES_MAIN);
	public static ItemBlock itemblockOreAux = new ItemBlockFluxGear(blockOreAux, unlocOre, NAMES_ORES_AUX, RARITY_ORES_AUX);
	public static ItemBlock ItemBlockStorageAux = new ItemBlockFluxGear(blockStorageAux, unlocStore, NAMES_STORE_AUX, RARITY_STORE_AUX);
	public static ItemBlock ItemBlockAlloyMain = new ItemBlockFluxGear(blockAlloyMain, unlocStore, NAMES_ALLOY_MAIN, RARITY_ALLOY_MAIN);
	public static ItemBlock ItemBlockAlloyAux = new ItemBlockFluxGear(new BlockAlloyAux(), unlocStore, NAMES_ALLOY_AUX, RARITY_ALLOY_AUX);
	public static ItemBlock itemblockStorageAlch = new ItemBlockFluxGear(blockStorageAlch, unlocStore, NAMES_STORE_ALCH, RARITY_STORE_ALCH);
	public static ItemBlock itemblockStorageAdv = new ItemBlockFluxGear(blockStorageAdv, unlocStore, NAMES_STORE_ADV, RARITY_STORE_ADV);
	public static ItemBlock itemblockEarthen = new ItemBlockFluxGear(blockEarthen, unlocDefault, NAMES_EARTH, RARITY_EARTH);
	public static ItemBlock itemblockGravelOreMain = new ItemBlockFluxGear(new BlockGravelOreMain(), unlocGravel, NAMES_ORES_MAIN, RARITY_ORES_MAIN);
	public static ItemBlock itemblockGravelOreAux = new ItemBlockFluxGear(new BlockGravelOreAux(), unlocGravel, NAMES_ORES_AUX, RARITY_ORES_AUX);
	public static ItemBlock itemblockPoorOreMain = new ItemBlockFluxGear(blockPoorOreMain, unlocPoore, NAMES_ORES_MAIN, RARITY_ORES_MAIN);
	public static ItemBlock itemblockPoorOreAux = new ItemBlockFluxGear(blockPoorOreAux, unlocPoore, NAMES_ORES_AUX, RARITY_ORES_AUX);

	/* *=-=-=-=* Ore Entries *=-=-=-=* */

	public static BasicOreEntry chalcocite = new BasicOreEntry(FluxGearContent.oreChalcocite, 1, "oreChalcocite", "oreCopper");
	public static BasicOreEntry cassiterite = new BasicOreEntry(FluxGearContent.oreCassiterite, 1, "oreCassiterite", "oreTin");
	public static BasicOreEntry galena = new BasicOreEntry(FluxGearContent.oreGalena, 2, "oreGalena", "oreLead");
	public static BasicOreEntry acanthite = new BasicOreEntry(FluxGearContent.oreAcanthite, 2, "oreAcanthite", "oreSilver");
	public static BasicOreEntry garnierite = new BasicOreEntry(FluxGearContent.oreGarnierite, 2, "oreGarnierite", "oreNickel");
	public static BasicOreEntry sphalerite = new BasicOreEntry(FluxGearContent.oreSphalerite, 1, "oreSphalerite", "oreZinc");
	public static BasicOreEntry bismuthinite = new BasicOreEntry(FluxGearContent.oreBismuthinite, 1, "oreBismuthinite", "oreBismuth");
	public static BasicOreEntry pyrolustite = new BasicOreEntry(FluxGearContent.orePyrolusite, 1, "orePyrolustite", "oreManganese");
	public static BasicOreEntry bauxite = new BasicOreEntry(FluxGearContent.oreBauxite, 1, "oreBauxite");
	public static BasicOreEntry cooperite = new BasicOreEntry(FluxGearContent.oreCooperite, 2, "oreCooperite", "orePlatinum");
	public static BasicOreEntry braggite = new BasicOreEntry(FluxGearContent.oreBraggite, 2, "oreBraggite");
	public static BasicOreEntry molybdenite = new BasicOreEntry(FluxGearContent.oreMolybdenite, 2, "oreMolybdenite", "oreMolybdenum");
	public static BasicOreEntry cobaltite = new BasicOreEntry(FluxGearContent.oreCobaltite, 2, "oreCobaltite", "oreNaturalCobalt");
	public static BasicOreEntry wolframite = new BasicOreEntry(FluxGearContent.oreWolframite, 3, "oreWolframite", "oreTungsten");
	public static BasicOreEntry ilmenite = new BasicOreEntry(FluxGearContent.oreIlmenite, 2, "oreIlmenite", "oreTitanium");
	public static BasicOreEntry chromite = new BasicOreEntry(FluxGearContent.oreChromite, 2, "oreChromite", "oreChromium");

	public static BasicOreEntry cinnabar = new BasicOreEntry(FluxGearContent.oreCinnabar, 2, "oreCinnabar", "oreMercury");
	public static BasicOreEntry pitchblende = new BasicOreEntry(FluxGearContent.orePitchblende, 2, "orePitchblende", "oreUranium");
	public static BasicOreEntry monazite = new BasicOreEntry(FluxGearContent.oreMonazite, 2, "oreMonazite");
	public static BasicOreEntry nierdermayrite = new BasicOreEntry(FluxGearContent.oreNiedermayrite, 2, "oreNierdermayrite");
	public static BasicOreEntry greenockite = new BasicOreEntry(FluxGearContent.oreGreenockite, 2, "oreGreenockite");
	public static BasicOreEntry gaotaiite = new BasicOreEntry(FluxGearContent.oreGaotaiite, 3, "oreGaotaiite");
	public static BasicOreEntry osarsite = new BasicOreEntry(FluxGearContent.oreOsarsite, 2, "oreOsarsite", "oreOsmium");
	public static BasicOreEntry znamenskyite = new BasicOreEntry(FluxGearContent.oreZnamenskyite, 2, "oreZnamenskyite");
	public static BasicOreEntry gallobeudanite = new BasicOreEntry(FluxGearContent.oreGallobeudanite, 2, "oreGallobeudanite");
	public static BasicOreEntry tetrahedrite = new BasicOreEntry(FluxGearContent.oreTertahedrite, 1, "oreTetrahedrite", "oreCopper");
	public static BasicOreEntry tennantite = new BasicOreEntry(FluxGearContent.oreTennantite, 1, "oreTennantite", "oreCopper");
	public static BasicOreEntry santafeite = new BasicOreEntry(FluxGearContent.oreSantafeite, 2, "oreSantafeite", "oreVanadium");
	public static BasicOreEntry magnetite = new BasicOreEntry(FluxGearContent.oreMagnetite, 2, "oreMagnetite");
	public static BasicOreEntry dioptase = new BasicOreEntry(FluxGearContent.oreDioptase, 3, "oreDioptase");
	public static BasicOreEntry pyrope = new BasicOreEntry(FluxGearContent.orePyrope, 3, "orePyrope");
	public static BasicOreEntry myuvil = new BasicOreEntry(FluxGearContent.oreMyuvil, 3, "oreMyuvil");

	public static BasicOreEntry poorChalcocite = new BasicOreEntry(FluxGearContent.orePoorChalcocite, 1, "orePoorChalcocite", "orePoorCopper");
	public static BasicOreEntry poorCassiterite = new BasicOreEntry(FluxGearContent.orePoorCassiterite, 1, "orePoorCassiterite", "orePoorTin");
	public static BasicOreEntry poorGalena = new BasicOreEntry(FluxGearContent.orePoorGalena, 2, "orePoorGalena", "orePoorLead");
	public static BasicOreEntry poorAcanthite = new BasicOreEntry(FluxGearContent.orePoorAcanthite, 2, "orePoorAcanthite", "orePoorSilver");
	public static BasicOreEntry poorGarnierite = new BasicOreEntry(FluxGearContent.orePoorGarnierite, 2, "orePoorGarnierite", "orePoorNickel");
	public static BasicOreEntry poorSphalerite = new BasicOreEntry(FluxGearContent.orePoorSphalerite, 1, "orePoorSphalerite", "orePoorZinc");
	public static BasicOreEntry poorBismuthinite = new BasicOreEntry(FluxGearContent.orePoorBismuthinite, 1, "orePoorBismuthinite", "orePoorBismuth");
	public static BasicOreEntry poorPyrolustite = new BasicOreEntry(FluxGearContent.orePoorPyrolusite, 1, "orePoorPyrolustite", "orePoorManganese");
	public static BasicOreEntry poorBauxite = new BasicOreEntry(FluxGearContent.orePoorBauxite, 1, "orePoorBauxite");
	public static BasicOreEntry poorCooperite = new BasicOreEntry(FluxGearContent.orePoorCooperite, 2, "orePoorCooperite", "orePoorPlatinum");
	public static BasicOreEntry poorBraggite = new BasicOreEntry(FluxGearContent.orePoorBraggite, 2, "orePoorBraggite");
	public static BasicOreEntry poorMolybdenite = new BasicOreEntry(FluxGearContent.orePoorMolybdenite, 2, "orePoorMolybdenite", "orePoorMolybdenum");
	public static BasicOreEntry poorCobaltite = new BasicOreEntry(FluxGearContent.orePoorCobaltite, 2, "orePoorCobaltite", "orePoorNaturalCobalt");
	public static BasicOreEntry poorWolframite = new BasicOreEntry(FluxGearContent.orePoorWolframite, 3, "orePoorWolframite", "orePoorTungsten");
	public static BasicOreEntry poorIlmenite = new BasicOreEntry(FluxGearContent.orePoorIlmenite, 2, "orePoorIlmenite", "orePoorTitanium");
	public static BasicOreEntry poorChromite = new BasicOreEntry(FluxGearContent.orePoorChromite, 2, "orePoorChromite", "orePoorChromium");

	public static BasicOreEntry poorCinnabar = new BasicOreEntry(FluxGearContent.orePoorCinnabar, 2, "orePoorCinnabar", "orePoorMercury");
	public static BasicOreEntry poorPitchblende = new BasicOreEntry(FluxGearContent.orePoorPitchblende, 2, "orePoorPitchblende", "orePoorUranium");
	public static BasicOreEntry poorMonazite = new BasicOreEntry(FluxGearContent.orePoorMonazite, 2, "orePoorMonazite");
	public static BasicOreEntry poorNierdermayrite = new BasicOreEntry(FluxGearContent.orePoorNiedermayrite, 2, "orePoorNierdermayrite");
	public static BasicOreEntry poorGreenockite = new BasicOreEntry(FluxGearContent.orePoorGreenockite, 2, "orePoorGreenockite");
	public static BasicOreEntry poorGaotaiite = new BasicOreEntry(FluxGearContent.orePoorGaotaiite, 3, "orePoorGaotaiite");
	public static BasicOreEntry poorOsarsite = new BasicOreEntry(FluxGearContent.orePoorOsarsite, 2, "orePoorOsarsite", "orePoorOsmium");
	public static BasicOreEntry poorZnamenskyite = new BasicOreEntry(FluxGearContent.orePoorZnamenskyite, 2, "orePoorZnamenskyite");
	public static BasicOreEntry poorGallobeudanite = new BasicOreEntry(FluxGearContent.orePoorGallobeudanite, 2, "orePoorGallobeudanite");
	public static BasicOreEntry poorTetrahedrite = new BasicOreEntry(FluxGearContent.orePoorTertahedrite, 1, "orePoorTetrahedrite", "orePoorCopper");
	public static BasicOreEntry poorTennantite = new BasicOreEntry(FluxGearContent.orePoorTennantite, 1, "orePoorTennantite", "orePoorCopper");
	public static BasicOreEntry poorSantafeite = new BasicOreEntry(FluxGearContent.orePoorSantafeite, 2, "orePoorSantafeite", "orePoorVanadium");
	public static BasicOreEntry poorMagnetite = new BasicOreEntry(FluxGearContent.orePoorMagnetite, 2, "orePoorMagnetite");
	public static BasicOreEntry poorDioptase = new BasicOreEntry(FluxGearContent.orePoorDioptase, 3, "orePoorDioptase");
	public static BasicOreEntry poorPyrope = new BasicOreEntry(FluxGearContent.orePoorPyrope, 3, "orePoorPyrope");
	public static BasicOreEntry poorMyuvil = new BasicOreEntry(FluxGearContent.orePoorMyuvil, 3, "orePoorMyuvil");

	public static BasicOreEntry gravelChalcocite = new BasicOreEntry(FluxGearContent.oreGravelChalcocite, 1, "oreChalcocite", "oreCopper");
	public static BasicOreEntry gravelCassiterite = new BasicOreEntry(FluxGearContent.oreGravelCassiterite, 1, "oreCassiterite", "oreTin");
	public static BasicOreEntry gravelGalena = new BasicOreEntry(FluxGearContent.oreGravelGalena, 2, "oreGalena", "oreLead");
	public static BasicOreEntry gravelAcanthite = new BasicOreEntry(FluxGearContent.oreGravelAcanthite, 2, "oreAcanthite", "oreSilver");
	public static BasicOreEntry gravelGarnierite = new BasicOreEntry(FluxGearContent.oreGravelGarnierite, 2, "oreGarnierite", "oreNickel");
	public static BasicOreEntry gravelSphalerite = new BasicOreEntry(FluxGearContent.oreGravelSphalerite, 1, "oreSphalerite", "oreZinc");
	public static BasicOreEntry gravelBismuthinite = new BasicOreEntry(FluxGearContent.oreGravelBismuthinite, 1, "oreBismuthinite", "oreBismuth");
	public static BasicOreEntry gravelPyrolustite = new BasicOreEntry(FluxGearContent.oreGravelPyrolusite, 1, "orePyrolustite", "oreManganese");
	public static BasicOreEntry gravelBauxite = new BasicOreEntry(FluxGearContent.oreGravelBauxite, 1, "oreBauxite");
	public static BasicOreEntry gravelCooperite = new BasicOreEntry(FluxGearContent.oreGravelCooperite, 2, "oreCooperite", "orePlatinum");
	public static BasicOreEntry gravelBraggite = new BasicOreEntry(FluxGearContent.oreGravelBraggite, 2, "oreBraggite");
	public static BasicOreEntry gravelMolybdenite = new BasicOreEntry(FluxGearContent.oreGravelMolybdenite, 2, "oreMolybdenite", "oreMolybdenum");
	public static BasicOreEntry gravelCobaltite = new BasicOreEntry(FluxGearContent.oreGravelCobaltite, 2, "oreCobaltite", "oreNaturalCobalt");
	public static BasicOreEntry gravelWolframite = new BasicOreEntry(FluxGearContent.oreGravelWolframite, 3, "oreWolframite", "oreTungsten");
	public static BasicOreEntry gravelIlmenite = new BasicOreEntry(FluxGearContent.oreGravelIlmenite, 2, "oreIlmenite", "oreTitanium");
	public static BasicOreEntry gravelChromite = new BasicOreEntry(FluxGearContent.oreGravelChromite, 2, "oreChromite", "oreChromium");

	public static BasicOreEntry gravelCinnabar = new BasicOreEntry(FluxGearContent.oreGravelCinnabar, 2, "oreCinnabar", "oreMercury");
	public static BasicOreEntry gravelPitchblende = new BasicOreEntry(FluxGearContent.oreGravelPitchblende, 2, "orePitchblende", "oreUranium");
	public static BasicOreEntry gravelMonazite = new BasicOreEntry(FluxGearContent.oreGravelMonazite, 2, "oreMonazite");
	public static BasicOreEntry gravelNierdermayrite = new BasicOreEntry(FluxGearContent.oreGravelNiedermayrite, 2, "oreNierdermayrite");
	public static BasicOreEntry gravelGreenockite = new BasicOreEntry(FluxGearContent.oreGravelGreenockite, 2, "oreGreenockite");
	public static BasicOreEntry gravelGaotaiite = new BasicOreEntry(FluxGearContent.oreGravelGaotaiite, 3, "oreGaotaiite");
	public static BasicOreEntry gravelOsarsite = new BasicOreEntry(FluxGearContent.oreGravelOsarsite, 2, "oreOsarsite", "oreOsmium");
	public static BasicOreEntry gravelZnamenskyite = new BasicOreEntry(FluxGearContent.oreGravelZnamenskyite, 2, "oreZnamenskyite");
	public static BasicOreEntry gravelGallobeudanite = new BasicOreEntry(FluxGearContent.oreGravelGallobeudanite, 2, "oreGallobeudanite");
	public static BasicOreEntry gravelTetrahedrite = new BasicOreEntry(FluxGearContent.oreGravelTertahedrite, 1, "oreTetrahedrite", "oreCopper");
	public static BasicOreEntry gravelTennantite = new BasicOreEntry(FluxGearContent.oreGravelTennantite, 1, "oreTennantite", "oreCopper");
	public static BasicOreEntry gravelSantafeite = new BasicOreEntry(FluxGearContent.oreGravelSantafeite, 2, "oreSantafeite", "oreVanadium");
	public static BasicOreEntry gravelMagnetite = new BasicOreEntry(FluxGearContent.oreGravelMagnetite, 2, "oreMagnetite");
	public static BasicOreEntry gravelDioptase = new BasicOreEntry(FluxGearContent.oreGravelDioptase, 3, "oreDioptase");
	public static BasicOreEntry gravelPyrope = new BasicOreEntry(FluxGearContent.oreGravelPyrope, 3, "orePyrope");
	public static BasicOreEntry gravelMyuvil = new BasicOreEntry(FluxGearContent.oreGravelMyuvil, 3, "oreMyuvil");

	public static BasicOreEntry iridiumSands = new BasicOreEntry(FluxGearContent.blockIridiumSands, 3, "oreIridiumSands");
	public static BasicOreEntry poorIridiumSands = new BasicOreEntry(FluxGearContent.blockPoorIridiumSands, 3, "orePoorIridiumSands");
	public static BasicOreEntry aluminosilicateSludge = new BasicOreEntry(FluxGearContent.blockAluminosilicateSludge , 0, "blockAluminosilicateSludge");

	public static BasicOreEntry[] metaOresMain = new BasicOreEntry[] { chalcocite, cassiterite, galena, acanthite, garnierite, sphalerite, bismuthinite, pyrolustite, bauxite, cooperite, braggite, molybdenite, cobaltite, wolframite, ilmenite, chromite };
	public static BasicOreEntry[] metaOresAux = new BasicOreEntry[] { cinnabar, pitchblende, monazite, nierdermayrite, greenockite, gaotaiite, osarsite, znamenskyite, gallobeudanite, tetrahedrite, tennantite, santafeite, magnetite, dioptase, pyrope, myuvil };
	public static BasicOreEntry[] metaOresEarthen = new BasicOreEntry[] { iridiumSands, poorIridiumSands, aluminosilicateSludge };
	public static BasicOreEntry[] metaPoorOresMain = new BasicOreEntry[] { poorChalcocite, poorCassiterite, poorGalena, poorAcanthite, poorGarnierite, poorSphalerite, poorBismuthinite, poorPyrolustite, poorBauxite, poorCooperite, poorBraggite, poorMolybdenite, poorCobaltite, poorWolframite, poorIlmenite, poorChromite };
	public static BasicOreEntry[] metaPoorOresAux = new BasicOreEntry[] { poorCinnabar, poorPitchblende, poorMonazite, poorNierdermayrite, poorGreenockite, poorGaotaiite, poorOsarsite, poorZnamenskyite, poorGallobeudanite, poorTetrahedrite, poorTennantite, poorSantafeite, poorMagnetite, poorDioptase, poorPyrope, poorMyuvil };
	public static BasicOreEntry[] metaGravelOresMain = new BasicOreEntry[] { gravelChalcocite, gravelCassiterite, gravelGalena, gravelAcanthite, gravelGarnierite, gravelSphalerite, gravelBismuthinite, gravelPyrolustite, gravelBauxite, gravelCooperite, gravelBraggite, gravelMolybdenite, gravelCobaltite, gravelWolframite, gravelIlmenite, gravelChromite };
	public static BasicOreEntry[] metaGravelOresAux = new BasicOreEntry[] { gravelCinnabar, gravelPitchblende, gravelMonazite, gravelNierdermayrite, gravelGreenockite, gravelGaotaiite, gravelOsarsite, gravelZnamenskyite, gravelGallobeudanite, gravelTetrahedrite, gravelTennantite, gravelSantafeite, gravelMagnetite, gravelDioptase, gravelPyrope, gravelMyuvil };

	//I know this is in the wrong spot, but one must do the things in the name of science!
	public static OreBlockEntry oreEntryMain = new OreBlockEntry(FluxGearContent.blockOreMain, ManualBlockEntries.ItemBlockOreMain.class, "OreMain", "pickaxe");
	public static OreBlockEntry oreEntryAux = new OreBlockEntry(FluxGearContent.blockOreAux, ManualBlockEntries.ItemBlockOreAux.class, "OreAux", "pickaxe");
	public static OreBlockEntry oreEntryEarthen = new OreBlockEntry(FluxGearContent.blockEarthen, ManualBlockEntries.ItemBlockEarthen.class, "Earthen", "shovel");
	public static OreBlockEntry oreEntryPoorMain = new OreBlockEntry(FluxGearContent.blockPoorOreMain, ManualBlockEntries.ItemBlockPoorOreMain.class, "PoorOreMain", "pickaxe");
	public static OreBlockEntry oreEntryPoorAux = new OreBlockEntry(FluxGearContent.blockPoorOreAux, ManualBlockEntries.ItemBlockPoorOreAux.class, "PoorOreAux", "pickaxe");
	public static OreBlockEntry oreEntryGravelMain = new OreBlockEntry(FluxGearContent.blockGravelOreMain, ManualBlockEntries.ItemBlockGravelOreMain.class, "GravelOreMain", "shovel");
	public static OreBlockEntry oreEntryGravelAux = new OreBlockEntry(FluxGearContent.blockGravelOreAux, ManualBlockEntries.ItemBlockGravelOreAux.class, "GravelOreAux", "shovel");

	public static OrePair mainOres = new OrePair(oreEntryMain, metaOresMain);
	public static OrePair auxOres = new OrePair(oreEntryAux, metaOresAux);
	public static OrePair earthenOres = new OrePair(oreEntryEarthen, metaOresEarthen);
	public static OrePair mainPoorOres = new OrePair(oreEntryPoorMain, metaPoorOresMain);
	public static OrePair auxPoorOres = new OrePair(oreEntryPoorAux, metaPoorOresAux);
	public static OrePair mainGravelOres = new OrePair(oreEntryGravelMain, metaGravelOresMain);
	public static OrePair auxGravelOres = new OrePair(oreEntryGravelAux, metaGravelOresAux);

	public static OrePair[] ores = new OrePair[] { mainOres, auxOres, earthenOres, mainPoorOres, auxPoorOres, mainGravelOres, auxGravelOres };

	public static class BasicOreEntry {

		public ItemStack itemstack;
		public int harvestLevel;
		public String oreDict;
		public String oreDict2;

		public BasicOreEntry(ItemStack itemstack, int harvestLevel, String oreDict, String oreDict2) {
			this.itemstack = itemstack;
			this.harvestLevel = harvestLevel;
			this.oreDict = oreDict;
			this.oreDict2 = oreDict2;
		}

		public BasicOreEntry(ItemStack itemstack, int harvestLevel, String oreDict) {
			this(itemstack, harvestLevel, oreDict, null);
		}
	}

	public static class OreBlockEntry {
		public Block block;
		public Class<? extends ItemBlock> itemblock;
		public String name;
		public String toolType;

		public OreBlockEntry(Block block, Class<? extends ItemBlock> itemblock, String name, String toolType) {
			this.block = block;
			this.itemblock = itemblock;
			this.name = name;
			this.toolType = toolType;
		}
	}

	public static class OrePair {
		public BasicOreEntry[] ores;
		public OreBlockEntry block;

		public OrePair (OreBlockEntry block, BasicOreEntry[] ores) {
			this.block = block;
			this.ores = ores;
		}
	}
}
