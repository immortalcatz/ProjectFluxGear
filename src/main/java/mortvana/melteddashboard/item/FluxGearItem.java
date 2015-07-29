package mortvana.melteddashboard.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.oredict.OreDictionary;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.melteddashboard.item.entry.ColorEntry;
import mortvana.melteddashboard.item.entry.ItemEntry;

/* Slightly Modified and Augmented Version of ItemBase from CoFH Core */
public class FluxGearItem extends Item {
	public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();
	// This is actually more memory efficient than a LinkedHashMap, according to Lemming.
	public List<Integer> itemList = new ArrayList<Integer>();
	public TMap<Integer, ColorEntry> colorMap = new THashMap<Integer, ColorEntry>();

	public boolean hasTextures;
	public String modName = "fluxgear";

	public FluxGearItem() {
		setHasSubtypes(true);
	}

	public FluxGearItem(String modName) {
		this.modName = modName;
		setHasSubtypes(true);
	}

	// addItem(...) {}
	public ItemStack addItem(int metadata, String name, int rarity, boolean register) {
		if (itemMap.containsKey(metadata)) {
			return null;
		} else {
			itemMap.put(metadata, new ItemEntry(name, rarity));
			itemList.add(metadata);

			ItemStack stack = new ItemStack(this, 1, metadata);
			if (register) {
				GameRegistry.registerCustomItemStack(name, stack);
			}
			return stack;
		}
	}

	public ItemStack addItem(int metadata, String name, int rarity) {
		if (itemMap.containsKey(metadata)) {
			return null;
		} else {
			itemMap.put(metadata, new ItemEntry(name, rarity));
			itemList.add(metadata);
			ItemStack stack = new ItemStack(this, 1, metadata);
			GameRegistry.registerCustomItemStack(name, stack);
			return stack;
		}
	}

	public ItemStack addItem(int metadata, String name) {
		return addItem(metadata, name, 0);
	}

	// addColorizedItem(...) {}
	public ItemStack addColorizedItem(int metadata, String name, int rarity, boolean register, String template, String texture, int color) {
		ItemStack stack = addItem(metadata, name, rarity, register);
		if (stack != null) {
			colorMap.put(metadata, new ColorEntry(template, texture, color));
		}
		return stack;
	}

	public ItemStack addColorizedItem(int metadata, String name, int rarity, int color, String texture, String template) {
		ItemStack stack = addItem(metadata, name, rarity);
		if (stack != null) {
			colorMap.put(metadata, new ColorEntry(template, texture, color));
		}
		return stack;
	}

	public ItemStack addColorizedItem(int metadata, String name, int color, String texture, String template) {
		return addColorizedItem(metadata, name, 0, color, texture, template);
	}

	// addOreDictItem(...) {}
	public ItemStack addOreDictItem(int metadata, String name, int rarity, boolean register, String... oreDict) {
		ItemStack stack = addItem(metadata, name, rarity, register);
		if (oreDict.length > 0) {
			for (String oreDictEntry : oreDict) {
				OreDictionary.registerOre(oreDictEntry, stack);
			}
		} else {
			OreDictionary.registerOre(name, stack);
		}
		return stack;
	}

	public ItemStack addOreDictItem(int metadata, String name, int rarity, String... oreDict) {
		ItemStack stack = addItem(metadata, name, rarity);
		if (oreDict.length > 0) {
			for (String oreDictEntry : oreDict) {
				OreDictionary.registerOre(oreDictEntry, stack);
			}
		} else {
			OreDictionary.registerOre(name, stack);
		}
		return stack;
	}


	public ItemStack addOreDictItem(int metadata, String name, String... oreDict) {
		return addOreDictItem(metadata, name, 0, oreDict);
	}

	// addColorizedOreDictItem(...) {}
	public ItemStack addColorizedOreDictItem(int metadata, String name, String template, String texture, int color, String... oreDict) {
		ItemStack stack = addColorizedItem(metadata, name, 0, color, texture, template);
		for (String oreDictEntry : oreDict) {
			OreDictionary.registerOre(oreDictEntry, stack);
		}
		return stack;
	}

	public ItemStack addColorizedOreDictItem(int metadata, String name, int rarity, String template, String texture, int color, String... oreDict) {
		ItemStack stack = addColorizedItem(metadata, name, rarity, color, texture, template);
		for (String oreDictEntry : oreDict) {
			OreDictionary.registerOre(oreDictEntry, stack);
		}
		return stack;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int meta : itemList) {
			list.add(new ItemStack(item, 1, meta));
		}
	}

	public String getInternalName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();
		return itemMap.containsKey(meta) ? itemMap.get(meta).name : "invalid";
	}

	@Override
	public Item setUnlocalizedName(String name) {
		GameRegistry.registerItem(this, name);
		return super.setUnlocalizedName(modName + "." + name);
	}

	public Item setUnlocalizedName(String textureName, String registrationName) {
		GameRegistry.registerItem(this, registrationName);
		return super.setUnlocalizedName(modName + "." + textureName);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();
		return itemMap.containsKey(meta) ? itemMap.get(meta).name : "item.invalid";
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();
		return itemMap.containsKey(meta) ? EnumRarity.values()[itemMap.get(meta).rarity] : EnumRarity.common;
	}

	//TODO
	@Override
	public boolean hasCustomEntity(ItemStack itemstack) {
		return false; //SecurityHelper.isSecure(itemstack);
	}

	@Override
	public Entity createEntity(World world, Entity itemEntity, ItemStack itemstack) {
		//if (SecurityHelper.isSecure(itemstack)) {
			itemEntity.invulnerable = true;
			itemEntity.isImmuneToFire = true;
			((EntityItem) itemEntity).lifespan = Integer.MAX_VALUE;
		//}
		return null;
	}

	public FluxGearItem setHasTextures(boolean hasTextures) {
		this.hasTextures = hasTextures;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public IIcon getIconFromDamage(int metadata) {
		return itemMap.containsKey(metadata) ? itemMap.get(metadata).icon : null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		if (hasTextures) {
			for (int i = 0; i < itemList.size(); i++) {
				ItemEntry entry = itemMap.get(itemList.get(i));
				if (colorMap.containsKey(i)) {

				} else {

				}
			}
		}
	}

	/*@Override
	public void registerIcons(IIconRegister ir) {

		if (!hasTextures) {
			return;
		}
		for (int i = 0; i < itemList.size(); i++) {
			ItemEntry item = itemMap.get(itemList.get(i));
			if (colorizerList.get(i)) {
				item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name/*Code Here*//*));
			/*} else {
				item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name));
			}
		}
	}*/

}
