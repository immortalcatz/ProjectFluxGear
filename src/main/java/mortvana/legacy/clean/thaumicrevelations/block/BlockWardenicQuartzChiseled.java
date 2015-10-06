package mortvana.legacy.clean.thaumicrevelations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWardenicQuartzChiseled extends Block {

    public IIcon topIcon;
    public IIcon botIcon;
    public IIcon sideIcon;

    public BlockWardenicQuartzChiseled() {
        super(Material.rock);
        setBlockName("blockInfusedQuartzChiseled");
        setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        topIcon = register.registerIcon("trevelations:infusedquartzchiseledtop");
        botIcon = register.registerIcon("trevelations:infusedquartzchiseledtop");
        sideIcon = register.registerIcon("trevelations:infusedquartzchiseledside");
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