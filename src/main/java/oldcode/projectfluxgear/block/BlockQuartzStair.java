package oldcode.projectfluxgear.block;

import net.minecraft.block.BlockStairs;

import oldcode.projectfluxgear.thaumic.ThaumicContent;

public class BlockQuartzStair extends BlockStairs {


	public BlockQuartzStair() {

		super(ThaumicContent.blockInfusedQuartzNormal, 0);
		setBlockName("blockInfusedQuartzStair");
		setCreativeTab(TRevelations.tabTRevelations);
		setLightOpacity(0);

	}
}
