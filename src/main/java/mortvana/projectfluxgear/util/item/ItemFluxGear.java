package mortvana.projectfluxgear.util.item;

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
import net.minecraftforge.oredict.OreDictionary;

import mortvana.projectfluxgear.util.helper.ItemHelper;
import mortvana.projectfluxgear.util.helper.SecurityHelper;
import mortvana.projectfluxgear.util.helper.StringHelper;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

/* Slightly Modified and Augmented Version of ItemBase from CoFH Core */
public class ItemFluxGear extends Item {

    public TMap<Integer, ItemEntry> itemMap = new THashMap<Integer, ItemEntry>();
    public ArrayList<Integer> itemList = new ArrayList<Integer>(); // This is actually more memory efficient than a LinkedHashMap
    //public TMap<Integer, ColorEntry> colorizerMap = new THashMap<Integer, ColorEntry>();
    //public ArrayList<Boolean> colorizerList = new ArrayList<Boolean>();

    public boolean hasTextures = true;
    public String modName = "fluxgear";

    public ItemFluxGear() {
        setHasSubtypes(true);
    }

    public ItemFluxGear(String modName) {
        this.modName = modName;
        setHasSubtypes(true);
    }

    public ItemStack addItem(int number, String name, int rarity, boolean register) {

        if (itemMap.containsKey(Integer.valueOf(number))) {
            return null;
        }
        itemMap.put(Integer.valueOf(number), new ItemEntry(name, rarity));
        itemList.add(Integer.valueOf(number));
        //colorizerList.add(Boolean.FALSE);

        ItemStack item = new ItemStack(this, 1, number);
        if (register) {
            GameRegistry.registerCustomItemStack(name, item);
        }
        return item;
    }

    public ItemStack addColorizedItem(int metadata, String name, int rarity, int color, String texture, boolean register) {

        if (itemMap.containsKey(Integer.valueOf(metadata))) {
            return null;
        }
        itemMap.put(Integer.valueOf(metadata), new ItemEntry(name, rarity));
        itemList.add(Integer.valueOf(metadata));
        //colorizerMap.put(Integer.valueOf(metadata), new ColorEntry(texture, color));
        //colorizerList.add(Boolean.TRUE);

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

        int i = ItemHelper.getItemDamage(stack);
        if (!itemMap.containsKey(Integer.valueOf(i))) {
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

        int i = ItemHelper.getItemDamage(stack);
        if (!itemMap.containsKey(Integer.valueOf(i))) {
            return "item.invalid";
        }
        return new StringBuilder().append(getUnlocalizedName()).append('.').append(itemMap.get(i).name).toString();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        int i = stack.getItemDamage();
        if (!itemMap.containsKey(Integer.valueOf(i))) {
            return EnumRarity.common;
        }
        return EnumRarity.values()[itemMap.get(stack.getItemDamage()).rarity];
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

    public ItemFluxGear setHasTextures(boolean hasTextures) {

        this.hasTextures = hasTextures;
        return this;
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }*/

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
            //if (colorizerList.get(i)) {
                //item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name/*Code Here*/));
            //} else {*/
                item.icon = ir.registerIcon(modName + ":" + getUnlocalizedName().replace("item." + modName + ".", "") + "/" + StringHelper.camelCase(item.name));
            //}
        }
    }
}
