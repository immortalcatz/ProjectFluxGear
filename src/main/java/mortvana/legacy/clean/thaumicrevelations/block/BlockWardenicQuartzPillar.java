package mortvana.legacy.clean.thaumicrevelations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.projectfluxgear.library.ContentLibrary;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWardenicQuartzPillar extends BlockRotatedPillar {

    public IIcon topIcon;
    public IIcon sideIcon;

    public BlockWardenicQuartzPillar() {
        super(Material.rock);
        setUnlocalizedName("blockInfusedQuartzPillar");
        setCreativeTab(ContentLibrary.thaumicRevelationsTab);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        topIcon = register.registerIcon("trevelations:infusedquartzpillartop");
        sideIcon = register.registerIcon("trevelations:infusedquartzpillarside");
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int par) {
        return sideIcon;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int par) {
        return topIcon;
    }
}