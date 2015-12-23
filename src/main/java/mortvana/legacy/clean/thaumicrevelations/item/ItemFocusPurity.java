package mortvana.legacy.clean.thaumicrevelations.item;

import mortvana.projectfluxgear.library.ContentLibrary;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;

import mortvana.legacy.clean.thaumicrevelations.entity.EntityPurity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusPurity extends ItemFocusBasic {

	public IIcon depth, orn;

	public ItemFocusPurity() {
		super();
		setUnlocalizedName("itemFocusPurity");
		setCreativeTab(ContentLibrary.thaumicRevelationsTab);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon("trevelations:purityfocus");
		depth = iconRegister.registerIcon("trevelations:puritydepth");
		orn = iconRegister.registerIcon("trevelations:purityorn");
	}

	@Override
	public IIcon getFocusDepthLayerIcon(ItemStack stack) {
		return depth;
	}

	@Override
	public IIcon getOrnament(ItemStack stack) {
		return orn;
	}

	@Override
	public int getFocusColor(ItemStack stack) {
		return 0x6698FF;
	}

	public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop) {

		ItemWandCasting wand = (ItemWandCasting) stack.getItem();
		EntityPurity purityOrb = new EntityPurity(world, player);
		if (!world.isRemote) {
			if (wand.consumeAllVis(stack, player, getVisCost(stack), true, false)) {
				world.spawnEntityInWorld(purityOrb);
				world.playSoundAtEntity(purityOrb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
			}
		}
		player.swingItem();
		return stack;
	}

	@Override
	public String getSortingHelper(ItemStack itemStack) {
		return "PURITY";
	}

	@Override
	public AspectList getVisCost(ItemStack itemstack) {
		return new AspectList().add(Aspect.AIR, 500).add(Aspect.EARTH, 500).add(Aspect.FIRE, 500).add(Aspect.WATER, 500).add(Aspect.ORDER, 500).add(Aspect.ENTROPY, 500);
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int i) {
		return new FocusUpgradeType[0];
	}
}
