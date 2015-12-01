package mortvana.legacy.errored.thaumicrevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusIllumination extends ItemFocusBasic {

	public IIcon depth, orn;

	public ItemFocusIllumination() {
		super();
		setUnlocalizedName("itemFocusIllumination");
		setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon("trevelations:purityfocus");
		depth = iconRegister.registerIcon("trevelations:puritydepth");
		orn = iconRegister.registerIcon("trevelations:purityorn");
	}

	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
		return depth;
	}

	@Override
	public IIcon getOrnament(ItemStack itemstack) {
		return orn;
	}

	@Override
	public int getFocusColor(ItemStack itemstack) {
		return 0x6698FF;
	}


	public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
		if (mop != null && !world.isRemote && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			if (((ItemWandCasting) itemstack.getItem()).consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {

				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;

				if (mop.sideHit == 0) {
					y--;
				}
				if (mop.sideHit == 1) {
					y++;
				}
				if (mop.sideHit == 2) {
					z--;
				}
				if (mop.sideHit == 3) {
					z++;
				}
				if (mop.sideHit == 4) {
					x--;
				}
				if (mop.sideHit == 5) {
					x++;
				}
				world.setBlock(x, y, z, ThaumicContent.blockWitor, 0, 2);
			}
		}
		player.swingItem();
		return itemstack;
	}

	@Override
	public String getSortingHelper(ItemStack stack) {
		return "ILLUMINATION";
	}

	@Override
	public AspectList getVisCost(ItemStack stack) {
		return new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50);
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack stack, int i) {
		return new FocusUpgradeType[0];
	}
}
