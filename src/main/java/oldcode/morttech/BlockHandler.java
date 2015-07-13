package oldcode.morttech;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class BlockHandler {
	public static Block woodMill;
	public static Block crank;

    public static void registerBlocks(GameRegistry registry) {
    	registry.registerBlock(woodMill, "woodMill");
    	registry.registerBlock(crank, "crank");
    }

    public static void setNames(LanguageRegistry registry) {
    	registry.addName(woodMill, "Wood Mill");
    	registry.addName(crank, "Crank");
    }
}
