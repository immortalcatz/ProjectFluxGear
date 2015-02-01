package mortvana.projectfluxgear.trevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.trevelations.common.TRevelations;

public class ItemWardenChest extends ItemWardenArmor {

	public ItemWardenChest() {

		super(1);
		setUnlocalizedName("itemWardenChest");
		setCreativeTab(TRevelations.tabTRevelations);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {

		itemIcon = register.registerIcon("trevelations:wardenchest");

	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		return "trevelations:textures/models/warden_1.png";

	}

}
