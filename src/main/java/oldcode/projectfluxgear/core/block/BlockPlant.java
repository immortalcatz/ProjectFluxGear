package oldcode.projectfluxgear.core.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.projectfluxgear.core.common.FluxGearContent;
import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class BlockPlant extends Block implements IPlantable {
	public BlockPlant() {
		super(Material.plants);
		setStepSound(Block.soundTypeGrass);
		setCreativeTab(ProjectFluxGear.generalTab);
		//setTickRandomly(true);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int par2) {
		switch (metadata) {
			case 0:
				return FluxGearContent.itemMaterial;
			default:
				return FluxGearContent.itemMaterial;
		}
	}

	@Override
	public int damageDropped(int metadata) {
		switch (metadata) {
			case 0:
				return 15000;
			default:
				return 0;
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int meta = getPlantMetadata(world, x, y, z);
		switch (meta) {
			case 0:
				return true;
			default:
				return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this);
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		/*int meta = getPlantMetadata(world, x, y, z);
		switch (meta) {
		}*/
		return EnumPlantType.Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon("fluxgear:exubitura");
	}
}
