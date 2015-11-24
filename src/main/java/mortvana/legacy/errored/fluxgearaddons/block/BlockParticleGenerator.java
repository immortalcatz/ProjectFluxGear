package mortvana.legacy.errored.fluxgearaddons.block;

import java.util.Random;

import mortvana.legacy.clean.core.util.block.BlockFluxGear;
import net.minecraft.block.Block;
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

import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileEnergyStorageCore;
import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileParticleGenerator;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.client.particle.*;
import mortvana.legacy.errored.core.common.ProjectFluxGear;

public class BlockParticleGenerator extends FluxGearBlock {
	public static Block instance;

	public BlockParticleGenerator() {
		setUnlocalizedName("particleGenerator");
		setCreativeTab(FluxGearContent.tabMaterials);
		setStepSound(soundTypeStone);
		setLightOpacity(0);
		ModBlocks.register(this);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ProjectFluxGear.RESOURCESPREFIX + "machine_side");
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			return false;
		} else {
			if (player.isSneaking()) {
				if (activateEnergyStorageCore(world, x, y, z, player)) {
					return true;
				}

				TileEntity tile = world.getTileEntity(x, y, z);
				TileParticleGenerator gen = tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator)tile : null;
				if (gen != null) {
					gen.toggleInverted();
				}
			} else {
				FMLNetworkHandler.openGui(player, ProjectFluxGear.instance, 5, world, x, y, z);
			}

			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return -1;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileEntity tile = world.getTileEntity(x, y, z);
		TileParticleGenerator gen = tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator) tile : null;
		if (gen != null) {
			gen.signal = world.isBlockIndirectlyGettingPowered(x, y, z);
			world.markBlockForUpdate(x, y, z);
		}

	}

	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if (world.isRemote) {
			Random rand = world.rand;
			float modifier = 0.1F;
			float SCALE = 1.0F;
			double spawnX = (double) x + 0.5D;
			double spawnY = (double) y + 0.5D;
			double spawnZ = (double) z + 0.5D;

			for (int i = 0; i < 100; ++i) {
				float MX = modifier - 2.0F * modifier * rand.nextFloat();
				float MY = modifier - 2.0F * modifier * rand.nextFloat();
				float MZ = modifier - 2.0F * modifier * rand.nextFloat();
				ParticleCustom particle = new ParticleCustom(world, spawnX, spawnY, spawnZ, MX, MY, MZ, SCALE, false, 1);
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

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean hasTileEntity(int meta) {
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileParticleGenerator();
	}

	private boolean activateEnergyStorageCore(World world, int x, int y, int z, EntityPlayer player) {
		int z1;
		TileEnergyStorageCore tile;
		for (z1 = x - 11; z1 <= x + 11; ++z1) {
			if (world.getBlock(z1, y, z) == FluxGearContent.energyStorageCore) {
				tile = world.getTileEntity(z1, y, z) != null && world.getTileEntity(z1, y, z) instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) world.getTileEntity(z1, y, z) : null;
				if (tile != null && !tile.isOnline()) {
					if (player.capabilities.isCreativeMode) {
						if (!tile.creativeActivate()) {
							if (world.isRemote) {
								player.addChatComponentMessage(new ChatComponentTranslation("msg.energyStorageCoreUTA.txt"));
							}
							return false;
						}
					} else if (!tile.tryActivate()) {
						if (world.isRemote) {
							player.addChatComponentMessage(new ChatComponentTranslation("msg.energyStorageCoreUTA.txt"));
						}
						return false;
					}
					return true;
				}
			}
		}

		for (z1 = z - 11; z1 <= z + 11; ++z1) {
			if (world.getBlock(x, y, z1) == FluxGearContent.energyStorageCore) {
				tile = world.getTileEntity(x, y, z1) != null && world.getTileEntity(x, y, z1) instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) world.getTileEntity(x, y, z1) : null;
				if (tile != null && !tile.isOnline()) {
					if (player.capabilities.isCreativeMode) {
						if (!tile.creativeActivate()) {
							if (world.isRemote) {
								player.addChatComponentMessage(new ChatComponentTranslation("msg.energyStorageCoreUTA.txt"));
							}
							return false;
						}
					} else if (!tile.tryActivate()) {
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

	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int meta) {
		if (meta == 1) {
			TileEntity tile = world.getTileEntity(x, y, z);
			TileParticleGenerator gen = tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator) tile : null;
			if (gen != null && gen.getMaster() != null) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				gen.getMaster().isStructureStillValid(true);
			}
		}
		super.breakBlock(world, x, y, z, p_149749_5_, meta);
	}
}
