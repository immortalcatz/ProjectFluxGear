package mortvana.legacy.crystaltweaks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AggregatorItem extends ItemBlock {

	public AggregatorItem(int par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("tooltip.aggregator.redstone"));
	}
}
