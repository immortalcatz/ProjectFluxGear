package mortvana.legacy.clean.core.util.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import mortvana.melteddashboard.util.helpers.StringHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.legacy.clean.core.util.helpers.ItemHelper;
import mortvana.legacy.clean.core.util.helpers.SecurityHelper;
import mortvana.melteddashboard.item.entry.ColorEntry;
import mortvana.melteddashboard.item.entry.ItemEntry;

//TODO: Use stuff we learned here on ItemBlocks and Blocks
public class ItemFluxGearFood extends ItemFood {
    protected int[] hunger;
    protected float[] saturation;
    protected String[] unlocalizedNames;
    protected String[] iconNames;
    protected IIcon[] icons;

    public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();
    public ArrayList<Integer> itemList = new ArrayList<Integer>(); // This is actually more memory efficient than a LinkedHashMap
    public TMap<Integer, ColorEntry> colorizerMap = new THashMap<Integer, ColorEntry>();
    public ArrayList<Boolean> colorizerList = new ArrayList<Boolean>();

    public boolean hasTextures = true;
    public String modName = "fluxgear";

    public ItemFluxGearFood() {
        super(0, 0, false);
        setHasSubtypes(true);
    }

    public ItemFluxGearFood(String modName) {
        super(0, 0, false);
        this.modName = modName;
        setHasSubtypes(true);
    }

    public ItemStack addItem(int number, String name, int rarity, boolean register) {

        if (itemMap.containsKey(number)) {
            return null;
        }
        itemMap.put(number, new ItemEntry(name, rarity));
        itemList.add(number);
        colorizerList.add(Boolean.FALSE);

        ItemStack item = new ItemStack(this, 1, number);
        if (register) {
            GameRegistry.registerCustomItemStack(name, item);
        }
        return item;
    }

    public ItemStack addColorizedItem(int metadata, String name, int rarity, int color, String texture, boolean register) {

        if (itemMap.containsKey(metadata)) {
            return null;
        }
        itemMap.put(metadata, new ItemEntry(name, rarity));
        itemList.add(metadata);
        colorizerMap.put(metadata, new ColorEntry("", texture, color));
        colorizerList.add(Boolean.TRUE);

        ItemStack item = new ItemStack(this, 1, metadata);
        if (register) {
            GameRegistry.registerCustomItemStack(name, item);
        }
        return item;
    }

    public ItemStack addItem(int number, String name, int rarity) {

        return addItem(number, name, rarity, true);
    }

    public ItemStack addItem(int number, String name) {

        return addItem(number, name, 0);
    }

    public ItemStack addOreDictItem(int number, String name, int rarity) {

        ItemStack stack = addItem(number, name, rarity);
        OreDictionary.registerOre(name, stack);

        return stack;
    }

    public ItemStack addOreDictItem(int number, String name) {

        ItemStack stack = addItem(number, name);
        OreDictionary.registerOre(name, stack);

        return stack;
    }

    public ItemStack addOreDictItem(int number, String name, int rarity, int color, String texture) {

        ItemStack stack = addColorizedItem(number, name, rarity, color, texture, true);
        OreDictionary.registerOre(name, stack);

        return stack;
    }

    public ItemStack addOreDictItem(int number, String name, int color, String texture) {

        ItemStack stack = addColorizedItem(number, name, 0, color, texture, true);
        OreDictionary.registerOre(name, stack);

        return stack;
    }


    public ItemStack addOreDictItem(int number, String name, String oredict, int rarity) {

        ItemStack stack = addItem(number, name, rarity);
        OreDictionary.registerOre(oredict, stack);

        return stack;
    }

    public ItemStack addOreDictItem(int number, String name, String oredict) {

        ItemStack stack = addItem(number, name);
        OreDictionary.registerOre(oredict, stack);

        return stack;
    }

    public ItemStack addOreDictItem(int number, String name, String oredict, int rarity, int color, String texture) {

        ItemStack stack = addColorizedItem(number, name, rarity, color, texture, true);
        OreDictionary.registerOre(oredict, stack);

        return stack;

    }

    public ItemStack addOreDictItem(int number, String name, String oredict, int color, String texture) {

        ItemStack stack = addColorizedItem(number, name, 0, color, texture, true);
        OreDictionary.registerOre(oredict, stack);

        return stack;

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {

        for (int i = 0; i < itemList.size(); i++) {
            list.add(new ItemStack(item, 1, itemList.get(i)));
        }
    }

    public String getRawName(ItemStack stack) {

        int i = ItemHelper.getMetadata(stack);
        if (!itemMap.containsKey(i)) {
            return "invalid";
        }
        return itemMap.get(i).name;
    }

    @Override
    public Item setUnlocalizedName(String name) {

        GameRegistry.registerItem(this, name);
        name = modName + "." + name;
        return super.setUnlocalizedName(name);
    }

    public Item setUnlocalizedName(String textureName, String registrationName) {

        GameRegistry.registerItem(this, registrationName);
        textureName = modName + "." + textureName;
        return super.setUnlocalizedName(textureName);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int i = ItemHelper.getMetadata(stack);
        if (!itemMap.containsKey(i)) {
            return "item.invalid";
        }
        return getUnlocalizedName() + "." + itemMap.get(i).name;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return itemMap.containsKey(stack.getMetadata()) ? EnumRarity.values()[itemMap.get(stack.getMetadata()).rarity] : EnumRarity.common;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.isSecure(stack);
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack stack) {

        if (SecurityHelper.isSecure(stack)) {
            location.invulnerable = true;
            location.isImmuneToFire = true;
            ((EntityItem) location).lifespan = Integer.MAX_VALUE;
        }
        return null;
    }

    public ItemFluxGearFood setHasTextures(boolean hasTextures) {

        this.hasTextures = hasTextures;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public IIcon getIconFromDamage(int i) {

        if (!itemMap.containsKey(i)) {
            return null;
        }
        return itemMap.get(i).icon;
    }

    @Override
    public void registerIcons(IIconRegister ir) {

        if (!hasTextures) {
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            ItemEntry item = itemMap.get(itemList.get(i));
	        if (colorizerList.get(i)) {
                item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name/*Code Here*/));
            } else {
            item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name));
            }
        }
    }

    public ItemFluxGearFood(int[] hunger, float[] saturation, String[] textureNames, String[] iconNames) {
        super(0, 0, false);
        this.hunger = hunger;
        this.saturation = saturation;
        this.unlocalizedNames = textureNames;
        this.iconNames = iconNames;
    }

    @Override
    public int getHealAmount (ItemStack stack) {
        return hunger[stack.getMetadata()];
    }

    @Override
    public float getSaturationModifier (ItemStack stack) {
        return saturation[stack.getMetadata()];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage_(int meta) {
        if (meta >= icons.length)
            return icons[0];
        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons_(IIconRegister iconRegister) {
        this.icons = new IIcon[iconNames.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("tinker:" + iconNames[i]);
        }
    }

    public String getUnlocalizedName_(ItemStack stack) {
        int arr = MathHelper.clamp_int(stack.getMetadata(), 0, unlocalizedNames.length);
        return getUnlocalizedName() + "." + unlocalizedNames[arr];
    }

    public void getSubItems_(Item b, CreativeTabs tab, List list) {
        for (int i = 0; i < unlocalizedNames.length; i++)
            list.add(new ItemStack(b, 1, i));
    }
}