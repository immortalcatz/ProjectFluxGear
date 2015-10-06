package mortvana.legacy.dependent.firstdegree.thaumicrevelations.block;

import mortvana.legacy.clean.core.common.FluxGearContent;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import net.minecraft.block.BlockStairs;

public class BlockWardenicQuartzStairs extends BlockStairs {

    public BlockWardenicQuartzStairs() {
        super(FluxGearContent.blockInfusedQuartzNormal, 0);
        setBlockName("blockInfusedQuartzStair");
        setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
        setLightOpacity(0);
    }
}
