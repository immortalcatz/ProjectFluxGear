package mortvana.legacy.clean.core.util.block;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.clean.core.util.helpers.StringHelper;

@Deprecated
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

	//public BlockFluxGear setRenderType(int renderType) {}

	public BlockFluxGear(Material material, String texDir) {
		super(material);
		textureLocation = texDir;
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
	public IIcon getIcon(int side, int metadata) {
		return textures[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		for (int i = 0; i < names.length; i++) {
			textures[i] = ir.registerIcon(textureLocation + StringHelper.camelCase(names[i]));
		}
	}




	public boolean isColorized;
	public boolean canSpawn;
	public boolean beaconBase;
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
		// Default material set to rock.
		super(material);
		englishName = name;
		setUnlocalizedName("block." + name.replace(" ", "")); //A default value. Absolutely acceptable to not keep it.

	}

	public boolean isEnabled() {
		return true;
	}

	public void setMaterial(Material m) {
		// Formerly deep dark voodoo. You can't get a security exception here anymore.
		// Gyro says he did reflection for the greater good. I say, Access Transformer Master-Race!
		blockMaterial = m;
		//Make sure that the entries to translucent are still valid.
		translucent = m.isTranslucent;
	}

	@Override
	public int getHarvestLevel(int subBlockMeta) {
		//By default, no metadata-based sub-blocks.
		return harvestLevel;
	}

	@Override
	public int quantityDropped(Random random) {
		return quantityDroppedWithBonus(0, random);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World p_getDrops_1_, int p_getDrops_2_, int p_getDrops_3_, int p_getDrops_4_, int p_getDrops_5_, int p_getDrops_6_) {
		if (itemDropped != null) {
			ArrayList<ItemStack> result = new ArrayList<ItemStack>(1);
			result.add(itemDropped);
			return result;
		} else {
			return super.getDrops(p_getDrops_1_, p_getDrops_2_, p_getDrops_3_, p_getDrops_4_, p_getDrops_5_, p_getDrops_6_);
		}
	}

	@Override
	public int quantityDroppedWithBonus(int bonus, Random random) {
		if (itemDropped != null) {
			return itemDropped.stackSize + random.nextInt(bonus + droppedRandomBonus);
		} else {
			return super.quantityDroppedWithBonus(bonus+ droppedRandomBonus, random);
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return quantityDroppedWithBonus(fortune, random);
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
