package mortvana.legacy.clean.core.util.block;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.legacy.clean.core.util.helpers.StringHelper;

@Deprecated
//TODO: I had class outside a funeral
public class BlockFluxGear extends Block {


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

	/*public BlockFluxGear setRenderType(int renderType) {

	}*/

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
		return null;//
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




	public boolean isColorized;
	public boolean canSpawn;
	public boolean beaconBase;
	public static String wrong = ;
	public int[] blockLight;
	public int[] rarity;
	public String textureLocation;
	public float[] hardness;
	public float[] resistance;



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
}
