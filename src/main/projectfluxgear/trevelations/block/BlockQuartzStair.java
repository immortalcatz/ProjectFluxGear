package mortvana.projectfluxgear.trevelations.block;

import net.minecraft.block.BlockStairs;

import mortvana.projectfluxgear.trevelations.common.ModContent;
import mortvana.projectfluxgear.trevelations.common.TRevelations;

public class BlockQuartzStair extends BlockStairs {


	public BlockQuartzStair() {

		super(ModContent.blockInfusedQuartzNormal, 0);
		setBlockName("blockInfusedQuartzStair");
		setCreativeTab(TRevelations.tabTRevelations);
		setLightOpacity(0);

	}
}
