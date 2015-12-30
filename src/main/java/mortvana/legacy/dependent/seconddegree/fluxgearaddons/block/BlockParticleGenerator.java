package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block;

import java.util.Random;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlock;

import mortvana.legacy.LegacyHelper;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyStorageCore;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileParticleGenerator;
import mortvana.legacy.clean.fluxgearaddons.client.particle.*;

public class BlockParticleGenerator extends FluxGearBlock {
	public static Block instance;

	public BlockParticleGenerator() {
		super(Material.iron, FluxGearContent.tabMaterials, 5.0F, 10.0F);
		setUnlocalizedName("particleGenerator").setStepSound(soundTypeStone).setLightOpacity(0);
		LegacyHelper.register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "machine_side");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			return false;
		} else if (player.isSneaking()) {
			if (activateEnergyStorageCore(world, x, y, z, player)) {
				return true;
			}

			TileParticleGenerator tile = isValidTile(world, x, y, z);
			if (tile != null) {
				tile.toggleInverted();
			}
		} else {
			FMLNetworkHandler.openGui(player, ProjectFluxGear.instance, 5, world, x, y, z);
		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileParticleGenerator tile = isValidTile(world, x, y, z);
		if (tile != null) {
			tile.signal = world.isBlockIndirectlyGettingPowered(x, y, z);
			world.markBlockForUpdate(x, y, z);
		}

	}

	@Override
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if (world.isRemote) {
			Random rand = world.rand;
			float modifier = 0.1F;
			double spawnX = (double) x + 0.5D;
			double spawnY = (double) y + 0.5D;
			double spawnZ = (double) z + 0.5D;

			for (int i = 0; i < 100; i++) {
				float motionX = modifier - 2.0F * modifier * rand.nextFloat();
				float motionY = modifier - 2.0F * modifier * rand.nextFloat();
				float motionZ = modifier - 2.0F * modifier * rand.nextFloat();
				ParticleCustom particle = new ParticleCustom(world, spawnX, spawnY, spawnZ, motionX, motionY, motionZ, 1.0F, false, 1);
				particle.red = rand.nextInt(255);
				particle.green = rand.nextInt(255);
				particle.blue = rand.nextInt(255);
				particle.maxAge = rand.nextInt(10);
				particle.fadeTime = 20;
				particle.fadeLength = 20;
				particle.gravity = 0.0F;
				ParticleHandler.spawnCustomParticle(particle);
			}
		}

	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileParticleGenerator();
	}

	public boolean activateEnergyStorageCore(World world, int x, int y, int z, EntityPlayer player) {
		int dim;
		TileEnergyStorageCore tile;
		for (dim = x - 11; dim <= x + 11; dim++) {
			if (world.getBlock(dim, y, z) == FluxGearContent.energyStorageCore) {
				tile = isValidCore(world, x, y, z);
				if (tile != null && !tile.isOnline()) {
					if ((player.capabilities.isCreativeMode && !tile.creativeActivate()) || !tile.tryActivate()) {
						if (world.isRemote) {
							player.addChatComponentMessage(new ChatComponentTranslation("msg.energyStorageCoreUTA.txt"));
						}
						return false;
					}
					return true;
				}
			}
		}

		for (dim = z - 11; dim <= z + 11; dim++) {
			if (world.getBlock(x, y, dim) == FluxGearContent.energyStorageCore) {
				tile = isValidCore(world, x, y, z);
				if (tile != null && !tile.isOnline()) {
					if ((player.capabilities.isCreativeMode && !tile.creativeActivate()) || !tile.tryActivate()) {
						if (world.isRemote) {
							player.addChatComponentMessage(new ChatComponentTranslation("msg.energyStorageCoreUTA.txt"));
						}
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (meta == 1) {
			TileParticleGenerator tile = isValidTile(world, x, y, z);
			if (tile != null && tile.getMaster() != null) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				tile.getMaster().isStructureStillValid(true);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	public TileParticleGenerator isValidTile(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator) tile : null;
	}

	public TileEnergyStorageCore isValidCore(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) tile : null;
	}
}
