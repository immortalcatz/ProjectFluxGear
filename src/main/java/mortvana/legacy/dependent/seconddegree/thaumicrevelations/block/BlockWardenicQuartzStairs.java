package mortvana.legacy.dependent.seconddegree.thaumicrevelations.block;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import net.minecraft.block.BlockStairs;

public class BlockWardenicQuartzStairs extends BlockStairs {

    public BlockWardenicQuartzStairs() {
        super(FluxGearContent.blockInfusedQuartzNormal, 0);
        setUnlocalizedName("blockInfusedQuartzStair");
        setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
        setLightOpacity(0);
    }
}