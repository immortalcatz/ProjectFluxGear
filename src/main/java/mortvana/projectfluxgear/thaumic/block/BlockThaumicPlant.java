package mortvana.projectfluxgear.thaumic.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.projectfluxgear.core.common.FluxGearCoreContent;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;

public class BlockThaumicPlant extends FluxGearBlock {

	public BlockThaumicPlant() {
		super(Material.plants, ThaumicRevelations.thaumicRevelationsTab);
		setStepSound(Block.soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int lemon) {
		return metadata < 8 ? Item.getItemFromBlock(this) : FluxGearCoreContent.generalItem;
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata < 8 ? metadata : 15000;
	}
}
