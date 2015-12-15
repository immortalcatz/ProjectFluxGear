package mortvana.legacy.clean.crystalclimate.block.tile;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.legacy.errored.crystalclimate.CrystalClimate;
import mortvana.melteddashboard.util.helpers.MiscHelper;

public class TerraformerLogic extends TileEntity {
	Random random = new Random();
	boolean init;
	int type;

	@Override
	public void updateEntity() {
		if (!init) {
			init = true;
			type = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		}
		if (worldObj.isRemote) {
			switch (type) {
				case 0: //Freezer
					for (int i = 0; i < 32; i++)
						worldObj.spawnParticle("snowshovel", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 1: //Fumer
					for (int i = 0; i < 5; i++)
						worldObj.spawnParticle("lava", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 3; i++)
						worldObj.spawnParticle("explode", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 5; i++)
						worldObj.spawnParticle("smoke", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 2: //Waver
					for (int i = 0; i < 50; i++)
						worldObj.spawnParticle("splash", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 3: //Leecher
					for (int i = 0; i < 16; i++)
						worldObj.spawnParticle("enchantmenttable", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, -1, 0);
					break;
				case 4: //Grower
					for (int i = 0; i < 16; i++)
						CrystalClimate.proxy.spawnParticle("leaf", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 5: //Nether
					for (int i = 0; i < 40; i++)
						worldObj.spawnParticle("depthsuspend", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 3; i++)
						worldObj.spawnParticle("largesmoke", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 6: //Lighter
					break;
				case 7: //Crystal
					for (int i = 0; i < 8; i++)
						worldObj.spawnParticle("fireworksSpark", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 8: //Ender
					for (int i = 0; i < 16; i++)
						worldObj.spawnParticle("portal", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 6; i++)
						worldObj.spawnParticle("witchMagic", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 9: //Void
					for (int i = 0; i < 16; i++)
						worldObj.spawnParticle("portal", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 8; i++)
						worldObj.spawnParticle("dripWater", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					for (int i = 0; i < 8; i++)
						worldObj.spawnParticle("dripLava", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
					break;
				case 10: //Desert
					break;
			}

            /* Good combos:
             * smoke + flame, 20+5
             * portal + witchMagic, 16+6
             * mobSpellAmbient + spell + mobSpell, 20+3+3
             * lava + explode + smoke, 10+6+10
             * happyVillager + deathsuspend + enchantmenttable, 16+64+20
             * 
             * Good values: 
             * fireworksSpark, 16
             * depthsuspend, 40
             * enchantmenttable, 16, x5
             * explode, 110
             * splash, 50
             * snowshovel, 32
             * heart, 16
             */
		} else {
			if (worldObj.getTotalWorldTime() % 5 == 0) /*for (int i = 0; i < 2; i++)*/ {
				switch (type) {
					case 0:
						snow();
						break;
					case 1:
						fume();
						break;
					case 2:
						wave();
						break;
					case 5:
						corrupt();
						break;
				}
			}
		}
	}

	void snow() {
		int x = xCoord + random.nextInt(25) - 12, y = yCoord + random.nextInt(6) - 1, z = zCoord + random.nextInt(25) - 12;
		Block block = worldObj.getBlock(x, y, z);
		if (MiscHelper.isBlockEqual(block, Blocks.water, Blocks.flowing_water, CrystalClimate.finiteWater)) {
			worldObj.setBlock(x, y, z, Blocks.ice);
		} else if (block == Blocks.snow_layer) {
			int meta = worldObj.getBlockMetadata(x, y, z);
			if (meta < 6) {
				worldObj.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
			} else if (meta == 6) {
				worldObj.setBlock(x, y, z, Blocks.snow);
			}
		} else if (block == null || block.isReplaceable(worldObj, x, y, z)) {
			if (World.doesBlockHaveSolidTopSurface(worldObj, x, y - 1, z)) {
				worldObj.setBlock(x, y, z, Blocks.snow_layer);
			}
		}
	}

	void fume() {
		int x = xCoord + random.nextInt(25) - 12, y = yCoord + random.nextInt(6) - 1, z = zCoord + random.nextInt(25) - 12;
		Block block = worldObj.getBlock(x, y, z);
		if (block == Blocks.water || block == Blocks.flowing_water) {
			worldObj.setBlock(x, y, z, Blocks.lava);
		} else if (block == CrystalClimate.ash) {
			int meta = worldObj.getBlockMetadata(x, y, z);
			if (meta < 6) {
				worldObj.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
			} else if (meta == 6) {
				worldObj.setBlock(x, y, z, CrystalClimate.ashBlock);
			}
		} else if (block == null || block.isReplaceable(worldObj, x, y, z)) {
			if (World.doesBlockHaveSolidTopSurface(worldObj, x, y - 1, z)) {
				if (random.nextInt(10) == 0) {
					worldObj.setBlock(x, y, z, Blocks.lava);
				} else {
					worldObj.setBlock(x, y, z, CrystalClimate.ash);
				}
			}
		}
	}

	void wave() {
		int x = xCoord + random.nextInt(5) - 2, y = yCoord, z = zCoord + random.nextInt(5) - 2;

		Block block = worldObj.getBlock(x, y, z);
		if (block == CrystalClimate.finiteWater) {
			int meta = worldObj.getBlockMetadata(x, y, z);
			if (meta < 7)
				worldObj.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
		} else if (block == null || block.isReplaceable(worldObj, x, y, z)) {
			worldObj.setBlock(x, y, z, CrystalClimate.finiteWater);
		}
	}

	void corrupt() {
		int x = xCoord + random.nextInt(25) - 12, y = yCoord + random.nextInt(11) - 5, z = zCoord + random.nextInt(25) - 12;
		Block block = worldObj.getBlock(x, y, z);
		if (block != null) {
			if (block == Blocks.stone)
				worldObj.setBlock(x, y, z, Blocks.netherrack);
			else if (block == Blocks.obsidian)
				worldObj.setBlock(x, y, z, Blocks.lava);
			else if (block == Blocks.tallgrass)
				worldObj.setBlock(x, y, z, Blocks.deadbush);
			else {
				Material material = block.getMaterial();
				if (material == Material.water) {
					worldObj.setBlock(x, y, z, Blocks.lava);
				} else if (material == Material.ground) {
					worldObj.setBlock(x, y, z, Blocks.soul_sand);
				} else if (material == Material.grass) {
					if (block == Blocks.tallgrass) {
						worldObj.setBlock(x, y, z, Blocks.deadbush);
					}
					if (random.nextInt(3) == 0) {
						worldObj.setBlock(x, y, z, Blocks.netherrack);
					} else {
						worldObj.setBlock(x, y, z, Blocks.soul_sand);
					}
				} else if (material == Material.sand && block != Blocks.soul_sand) {
					worldObj.setBlock(x, y, z, Blocks.gravel);
				} else if (material == Material.plants) {
					worldObj.setBlock(x, y, z, Blocks.web);
				}
			}
		}
	}
}
