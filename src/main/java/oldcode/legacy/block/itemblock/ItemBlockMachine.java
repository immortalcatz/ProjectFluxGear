package oldcode.legacy.block.itemblock;

import java.util.List;
import mantle.blocks.abstracts.MultiItemBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;

//TODO: Make this used
public class ItemBlockMachine extends MultiItemBlock {
    public static final String blockTypes[] = { "woodmill" };

    public ItemBlockMachine(Block b) {
        super(b, "MachineBlock", blockTypes);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0:
                list.add("A simple machine for cutting wood.");
                list.add("Gives bonus sticks and sawdust.");
                break;
        }
    }
	
}
