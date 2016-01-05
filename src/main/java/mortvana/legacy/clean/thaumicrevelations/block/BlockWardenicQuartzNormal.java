package mortvana.legacy.clean.thaumicrevelations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.library.FluxGearLibrary;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWardenicQuartzNormal extends Block {

    public IIcon topIcon;
    public IIcon botIcon;
    public IIcon sideIcon;

    public BlockWardenicQuartzNormal() {
        super(Material.rock);
        setUnlocalizedName("blockInfusedQuartzNormal");
        setCreativeTab(FluxGearLibrary.thaumicRevelationsTab);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        topIcon = register.registerIcon("trevelations:infusedquartztop");
        botIcon = register.registerIcon("trevelations:infusedquartzbot");
        sideIcon = register.registerIcon("trevelations:infusedquartzside");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 0) {
            return botIcon;
        } else if (side == 1) {
            return topIcon;
        } else {
            return sideIcon;
        }
    }
}