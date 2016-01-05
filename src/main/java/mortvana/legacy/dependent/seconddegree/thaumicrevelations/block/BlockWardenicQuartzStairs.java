package mortvana.legacy.dependent.seconddegree.thaumicrevelations.block;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;

import mortvana.projectfluxgear.library.FluxGearLibrary;

import net.minecraft.block.BlockStairs;

public class BlockWardenicQuartzStairs extends BlockStairs {

    public BlockWardenicQuartzStairs() {
        super(FluxGearContent.blockInfusedQuartzNormal, 0);
        setUnlocalizedName("blockInfusedQuartzStair");
        setCreativeTab(FluxGearLibrary.thaumicRevelationsTab);
        setLightOpacity(0);
    }
}
