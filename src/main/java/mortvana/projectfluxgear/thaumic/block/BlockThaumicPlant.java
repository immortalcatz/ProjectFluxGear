package mortvana.projectfluxgear.thaumic.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import mortvana.melteddashboard.block.FluxGearBlock;

import mortvana.projectfluxgear.library.FluxGearLibrary;

public class BlockThaumicPlant extends FluxGearBlock {

	public BlockThaumicPlant() {
		super(Material.plants, FluxGearLibrary.thaumicRevelationsTab);
		setStepSound(Block.soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int lemon) {
		return metadata < 8 ? Item.getItemFromBlock(this) : FluxGearLibrary.generalItem;
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata < 8 ? metadata : 15000;
	}
}
