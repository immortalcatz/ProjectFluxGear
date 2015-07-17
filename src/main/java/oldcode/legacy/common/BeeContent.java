package oldcode.legacy.common;

import cpw.mods.fml.common.registry.GameRegistry;
import oldcode.legacy.item.ItemHoneyComb;
import net.minecraft.item.Item;

public class BeeContent {

    public BeeContent(){
        registerItems();
        registerBlocks();
        registerForestry();
        addCraftingRecipes();
    }

    void registerItems() {
        honeyComb = new ItemHoneyComb().setUnlocalizedName("mortvanaBeesHoneyComb");
        GameRegistry.registerItem(honeyComb, "mortvanaBeesHoneyComb");
    }
	public static Item honeyComb;


    void registerBlocks() {

    }

    void registerForestry() {

    }

    private void addCraftingRecipes() {

    }

    public void oreRegistry() {

    }


}
