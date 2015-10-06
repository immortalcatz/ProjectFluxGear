package mortvana.legacy.errored.fluxgearaddons.block.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.TileObjectSync;

public class TileEnergyStorageCore extends TileObjectSync {
	protected TileLocation[] stabilizers = new TileLocation[4];
	protected int tier = 0;
	protected boolean online = false;
	public float modelRotation = 0.0F;
	private double energy = 0.0D;
	private double capacity = 0.0D;
	private double lastTickCapacity = 0.0D;
	private int tick = 0;

	public TileEnergyStorageCore() {
		for(int i = 0; i < this.stabilizers.length; ++i) {
			this.stabilizers[i] = new TileLocation();
		}

	}

	public void func_145845_h() {
		if(this.online) {
			if(this.field_145850_b.isRemote) {
				this.modelRotation = (float)((double)this.modelRotation + 0.5D);
			}

			if(!this.field_145850_b.isRemote) {
				this.detectAndRendChanges();
			}

			++this.tick;
		}
	}

	public boolean tryActivate() {
		if(!this.findStablizers()) {
			return false;
		} else if(!this.setTier(false)) {
			return false;
		} else if(!this.testOrActivateStructureIfValid(false, false)) {
			return false;
		} else {
			this.online = true;
			if(!this.testOrActivateStructureIfValid(false, true)) {
				this.online = false;
				this.deactivateStabilizers();
				return false;
			} else {
				this.activateStabilizers();
				this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
				return true;
			}
		}
	}

	public boolean creativeActivate() {
		if(!this.findStablizers()) {
			return false;
		} else if(!this.setTier(false)) {
			return false;
		} else if(!this.testOrActivateStructureIfValid(true, false)) {
			return false;
		} else {
			this.online = true;
			if(!this.testOrActivateStructureIfValid(false, true)) {
				this.online = false;
				this.deactivateStabilizers();
				return false;
			} else {
				this.activateStabilizers();
				this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
				return true;
			}
		}
	}

	public boolean isStructureStillValid(boolean update) {
		if(!this.checkStabilizers()) {
			this.online = false;
		}

		if(!this.testOrActivateStructureIfValid(false, false)) {
			this.online = false;
		}

		if(!this.areStabilizersActive()) {
			this.online = false;
		}

		this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
		if(!this.online) {
			this.deactivateStabilizers();
		}

		if(update && !this.online) {
			this.reIntegrate();
		}

		return this.online;
	}

	private void reIntegrate() {
		for(int x = this.field_145851_c - 1; x <= this.field_145851_c + 1; ++x) {
			for(int y = this.field_145848_d - 1; y <= this.field_145848_d + 1; ++y) {
				for(int z = this.field_145849_e - 1; z <= this.field_145849_e + 1; ++z) {
					if(this.field_145850_b.getBlock(x, y, z) == ModBlocks.invisibleMultiblock) {
						if(this.field_145850_b.getBlockMetadata(x, y, z) == 0) {
							this.field_145850_b.setBlock(x, y, z, ModBlocks.draconiumBlock);
						} else if(this.field_145850_b.getBlockMetadata(x, y, z) == 1) {
							this.field_145850_b.setBlock(x, y, z, Blocks.redstone_block);
						}
					}
				}
			}
		}

	}

	private boolean findStablizers() {
		boolean flag = true;

		int z;
		for(z = this.field_145851_c; z <= this.field_145851_c + 11; ++z) {
			if(this.field_145850_b.getBlock(z, this.field_145848_d, this.field_145849_e) == ModBlocks.particleGenerator) {
				if(this.field_145850_b.getBlockMetadata(z, this.field_145848_d, this.field_145849_e) == 1) {
					flag = false;
				} else {
					this.stabilizers[0] = new TileLocation(z, this.field_145848_d, this.field_145849_e);
				}
				break;
			}

			if(z == this.field_145851_c + 11) {
				flag = false;
			}
		}

		for(z = this.field_145851_c; z >= this.field_145851_c - 11; --z) {
			if(this.field_145850_b.getBlock(z, this.field_145848_d, this.field_145849_e) == ModBlocks.particleGenerator) {
				if(this.field_145850_b.getBlockMetadata(z, this.field_145848_d, this.field_145849_e) == 1) {
					flag = false;
				} else {
					this.stabilizers[1] = new TileLocation(z, this.field_145848_d, this.field_145849_e);
				}
				break;
			}

			if(z == this.field_145851_c - 11) {
				flag = false;
			}
		}

		for(z = this.field_145849_e; z <= this.field_145849_e + 11; ++z) {
			if(this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d, z) == ModBlocks.particleGenerator) {
				if(this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, z) == 1) {
					flag = false;
				} else {
					this.stabilizers[2] = new TileLocation(this.field_145851_c, this.field_145848_d, z);
				}
				break;
			}

			if(z == this.field_145849_e + 11) {
				flag = false;
			}
		}

		for(z = this.field_145849_e; z >= this.field_145849_e - 11; --z) {
			if(this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d, z) == ModBlocks.particleGenerator) {
				if(this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, z) == 1) {
					flag = false;
				} else {
					this.stabilizers[3] = new TileLocation(this.field_145851_c, this.field_145848_d, z);
				}
				break;
			}

			if(z == this.field_145849_e - 11) {
				flag = false;
			}
		}

		return flag;
	}

	private boolean setTier(boolean force) {
		if(force) {
			return true;
		} else {
			int xPos = 0;
			int xNeg = 0;
			int yPos = 0;
			int yNeg = 0;
			int zPos = 0;
			int zNeg = 0;
			byte range = 5;

			int z;
			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c + z, this.field_145848_d, this.field_145849_e, false, false)) {
					xPos = z;
					break;
				}
			}

			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c - z, this.field_145848_d, this.field_145849_e, false, false)) {
					xNeg = z;
					break;
				}
			}

			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d + z, this.field_145849_e, false, false)) {
					yPos = z;
					break;
				}
			}

			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d - z, this.field_145849_e, false, false)) {
					yNeg = z;
					break;
				}
			}

			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d, this.field_145849_e + z, false, false)) {
					zPos = z;
					break;
				}
			}

			for(z = 0; z <= range; ++z) {
				if(this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d, this.field_145849_e - z, false, false)) {
					zNeg = z;
					break;
				}
			}

			if(zNeg == zPos && zNeg == yNeg && zNeg == yPos && zNeg == xNeg && zNeg == xPos) {
				this.tier = xPos;
				if(this.tier > 1) {
					++this.tier;
				}

				if(this.tier == 1 && this.testForOrActivateDraconium(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e, false, false)) {
					this.tier = 2;
				}

				return true;
			} else {
				return false;
			}
		}
	}

	private boolean testOrActivateStructureIfValid(boolean setBlocks, boolean activate) {
		switch(this.tier) {
			case 0:
				if(!this.testOrActivateRect(1, 1, 1, "air", setBlocks, activate)) {
					return false;
				}
				break;
			case 1:
				if(!this.testForOrActivateDraconium(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, setBlocks, activate) || !this.testForOrActivateDraconium(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, setBlocks, activate) || !this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, setBlocks, activate) || !this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, setBlocks, activate) || !this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, setBlocks, activate) || !this.testForOrActivateDraconium(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, setBlocks, activate)) {
					return false;
				}

				if(!this.isAir(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e, setBlocks) || !this.isAir(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e, setBlocks) || !this.isAir(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e, setBlocks) || !this.isAir(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e, setBlocks) || !this.isAir(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1, setBlocks)) {
					return false;
				}

				if(!this.isAir(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e + 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e - 1, setBlocks) || !this.isAir(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e + 1, setBlocks)) {
					return false;
				}
				break;
			case 2:
				if(!this.testOrActivateRect(1, 1, 1, "draconiumBlock", setBlocks, activate)) {
					return false;
				}
				break;
			case 3:
				if(!this.testOrActivateSides(1, "draconiumBlock", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(1, 1, 1, "redstone", setBlocks, activate)) {
					return false;
				}
				break;
			case 4:
				if(!this.testOrActivateSides(2, "draconiumBlock", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(2, 1, 1, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(1, 2, 1, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(1, 1, 2, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRings(2, 2, "draconiumBlock", setBlocks, activate)) {
					return false;
				}
				break;
			case 5:
				if(!this.testOrActivateSides(3, "draconiumBlock", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateSides(2, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(2, 2, 2, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRings(2, 3, "draconiumBlock", setBlocks, activate)) {
					return false;
				}
				break;
			case 6:
				if(!this.testOrActivateSides(4, "draconiumBlock", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateSides(3, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(3, 2, 2, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(2, 3, 2, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRect(2, 2, 3, "redstone", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRings(2, 4, "draconiumBlock", setBlocks, activate)) {
					return false;
				}

				if(!this.testOrActivateRings(3, 3, "draconiumBlock", setBlocks, activate)) {
					return false;
				}
		}

		return true;
	}

	private boolean testOrActivateRect(int xDim, int yDim, int zDim, String block, boolean set, boolean activate) {
		for(int x = this.field_145851_c - xDim; x <= this.field_145851_c + xDim; ++x) {
			for(int y = this.field_145848_d - yDim; y <= this.field_145848_d + yDim; ++y) {
				for(int z = this.field_145849_e - zDim; z <= this.field_145849_e + zDim; ++z) {
					if(block.equals("air")) {
						if((x != this.field_145851_c || y != this.field_145848_d || z != this.field_145849_e) && !this.isAir(x, y, z, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((x != this.field_145851_c || y != this.field_145848_d || z != this.field_145849_e) && !this.testForOrActivateRedstone(x, y, z, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((x != this.field_145851_c || y != this.field_145848_d || z != this.field_145849_e) && !this.testForOrActivateDraconium(x, y, z, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean testOrActivateRings(int size, int dist, String block, boolean set, boolean activate) {
		int y;
		int x;
		for(y = this.field_145848_d - size; y <= this.field_145848_d + size; ++y) {
			for(x = this.field_145849_e - size; x <= this.field_145849_e + size; ++x) {
				if(y == this.field_145848_d - size || y == this.field_145848_d + size || x == this.field_145849_e - size || x == this.field_145849_e + size) {
					if(block.equals("air")) {
						if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.isAir(this.field_145851_c + dist, y, x, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(this.field_145851_c + dist, y, x, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(this.field_145851_c + dist, y, x, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		for(y = this.field_145848_d - size; y <= this.field_145848_d + size; ++y) {
			for(x = this.field_145849_e - size; x <= this.field_145849_e + size; ++x) {
				if(y == this.field_145848_d - size || y == this.field_145848_d + size || x == this.field_145849_e - size || x == this.field_145849_e + size) {
					if(block.equals("air")) {
						if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.isAir(this.field_145851_c - dist, y, x, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(this.field_145851_c - dist, y, x, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(this.field_145851_c - dist, y, x, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		for(y = this.field_145851_c - size; y <= this.field_145851_c + size; ++y) {
			for(x = this.field_145849_e - size; x <= this.field_145849_e + size; ++x) {
				if(y == this.field_145851_c - size || y == this.field_145851_c + size || x == this.field_145849_e - size || x == this.field_145849_e + size) {
					if(block.equals("air")) {
						if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.isAir(y, this.field_145848_d + dist, x, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(y, this.field_145848_d + dist, x, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(y, this.field_145848_d + dist, x, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		for(y = this.field_145851_c - size; y <= this.field_145851_c + size; ++y) {
			for(x = this.field_145849_e - size; x <= this.field_145849_e + size; ++x) {
				if(y == this.field_145851_c - size || y == this.field_145851_c + size || x == this.field_145849_e - size || x == this.field_145849_e + size) {
					if(block.equals("air")) {
						if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.isAir(y, this.field_145848_d - dist, x, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(y, this.field_145848_d - dist, x, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(y, this.field_145848_d - dist, x, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		for(y = this.field_145848_d - size; y <= this.field_145848_d + size; ++y) {
			for(x = this.field_145851_c - size; x <= this.field_145851_c + size; ++x) {
				if(y == this.field_145848_d - size || y == this.field_145848_d + size || x == this.field_145851_c - size || x == this.field_145851_c + size) {
					if(block.equals("air")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.isAir(x, y, this.field_145849_e + dist, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.testForOrActivateRedstone(x, y, this.field_145849_e + dist, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.testForOrActivateDraconium(x, y, this.field_145849_e + dist, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		for(y = this.field_145848_d - size; y <= this.field_145848_d + size; ++y) {
			for(x = this.field_145851_c - size; x <= this.field_145851_c + size; ++x) {
				if(y == this.field_145848_d - size || y == this.field_145848_d + size || x == this.field_145851_c - size || x == this.field_145851_c + size) {
					if(block.equals("air")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.isAir(x, y, this.field_145849_e - dist, set)) {
							return false;
						}
					} else if(block.equals("redstone")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.testForOrActivateRedstone(x, y, this.field_145849_e - dist, set, activate)) {
							return false;
						}
					} else if(block.equals("draconiumBlock")) {
						if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.testForOrActivateDraconium(x, y, this.field_145849_e - dist, set, activate)) {
							return false;
						}
					} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
						LogHelper.error("Invalid String In Multiblock Structure Code!!!");
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean testOrActivateSides(int dist, String block, boolean set, boolean activate) {
		++dist;

		int y;
		int x;
		for(y = this.field_145848_d - 1; y <= this.field_145848_d + 1; ++y) {
			for(x = this.field_145849_e - 1; x <= this.field_145849_e + 1; ++x) {
				if(block.equals("air")) {
					if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.isAir(this.field_145851_c + dist, y, x, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(this.field_145851_c + dist, y, x, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((this.field_145851_c + dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(this.field_145851_c + dist, y, x, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		for(y = this.field_145848_d - 1; y <= this.field_145848_d + 1; ++y) {
			for(x = this.field_145849_e - 1; x <= this.field_145849_e + 1; ++x) {
				if(block.equals("air")) {
					if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.isAir(this.field_145851_c - dist, y, x, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(this.field_145851_c - dist, y, x, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((this.field_145851_c - dist != this.field_145851_c || y != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(this.field_145851_c - dist, y, x, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		for(y = this.field_145851_c - 1; y <= this.field_145851_c + 1; ++y) {
			for(x = this.field_145849_e - 1; x <= this.field_145849_e + 1; ++x) {
				if(block.equals("air")) {
					if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.isAir(y, this.field_145848_d + dist, x, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(y, this.field_145848_d + dist, x, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((y != this.field_145851_c || this.field_145848_d + dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(y, this.field_145848_d + dist, x, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		for(y = this.field_145851_c - 1; y <= this.field_145851_c + 1; ++y) {
			for(x = this.field_145849_e - 1; x <= this.field_145849_e + 1; ++x) {
				if(block.equals("air")) {
					if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.isAir(y, this.field_145848_d - dist, x, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateRedstone(y, this.field_145848_d - dist, x, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((y != this.field_145851_c || this.field_145848_d - dist != this.field_145848_d || x != this.field_145849_e) && !this.testForOrActivateDraconium(y, this.field_145848_d - dist, x, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		for(y = this.field_145848_d - 1; y <= this.field_145848_d + 1; ++y) {
			for(x = this.field_145851_c - 1; x <= this.field_145851_c + 1; ++x) {
				if(block.equals("air")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.isAir(x, y, this.field_145849_e + dist, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.testForOrActivateRedstone(x, y, this.field_145849_e + dist, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e + dist != this.field_145849_e) && !this.testForOrActivateDraconium(x, y, this.field_145849_e + dist, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		for(y = this.field_145848_d - 1; y <= this.field_145848_d + 1; ++y) {
			for(x = this.field_145851_c - 1; x <= this.field_145851_c + 1; ++x) {
				if(block.equals("air")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.isAir(x, y, this.field_145849_e - dist, set)) {
						return false;
					}
				} else if(block.equals("redstone")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.testForOrActivateRedstone(x, y, this.field_145849_e - dist, set, activate)) {
						return false;
					}
				} else if(block.equals("draconiumBlock")) {
					if((x != this.field_145851_c || y != this.field_145848_d || this.field_145849_e - dist != this.field_145849_e) && !this.testForOrActivateDraconium(x, y, this.field_145849_e - dist, set, activate)) {
						return false;
					}
				} else if(!block.equals("draconiumBlock") && !block.equals("redstone") && !block.equals("air")) {
					LogHelper.error("Invalid String In Multiblock Structure Code!!!");
					return false;
				}
			}
		}

		return true;
	}

	private boolean testForOrActivateDraconium(int x, int y, int z, boolean set, boolean activate) {
		if(!activate) {
			if(set) {
				this.field_145850_b.setBlock(x, y, z, ModBlocks.draconiumBlock);
				return true;
			} else {
				return this.field_145850_b.getBlock(x, y, z) == ModBlocks.draconiumBlock || this.field_145850_b.getBlock(x, y, z) == ModBlocks.invisibleMultiblock && this.field_145850_b.getBlockMetadata(x, y, z) == 0;
			}
		} else {
			return this.activateDraconium(x, y, z);
		}
	}

	private boolean testForOrActivateRedstone(int x, int y, int z, boolean set, boolean activate) {
		if(!activate) {
			if(set) {
				this.field_145850_b.setBlock(x, y, z, Blocks.redstone_block);
				return true;
			} else {
				return this.field_145850_b.getBlock(x, y, z) == Blocks.redstone_block || this.field_145850_b.getBlock(x, y, z) == ModBlocks.invisibleMultiblock && this.field_145850_b.getBlockMetadata(x, y, z) == 1;
			}
		} else {
			return this.activateRedstone(x, y, z);
		}
	}

	private boolean activateDraconium(int x, int y, int z) {
		if(!this.testForOrActivateDraconium(x, y, z, false, false)) {
			LogHelper.error("Failed to activate structure (activateDraconium)");
			return false;
		} else {
			this.field_145850_b.setBlock(x, y, z, ModBlocks.invisibleMultiblock, 0, 2);
			TileInvisibleMultiblock tile = this.field_145850_b.getTileEntity(x, y, z) != null && this.field_145850_b.getTileEntity(x, y, z) instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)this.field_145850_b.getTileEntity(x, y, z):null;
			if(tile != null) {
				tile.master = new TileLocation(this.field_145851_c, this.field_145848_d, this.field_145849_e);
			}

			return true;
		}
	}

	private boolean activateRedstone(int x, int y, int z) {
		if(!this.testForOrActivateRedstone(x, y, z, false, false)) {
			LogHelper.error("Failed to activate structure (activateRedstone)");
			return false;
		} else {
			this.field_145850_b.setBlock(x, y, z, ModBlocks.invisibleMultiblock, 1, 2);
			TileInvisibleMultiblock tile = this.field_145850_b.getTileEntity(x, y, z) != null && this.field_145850_b.getTileEntity(x, y, z) instanceof TileInvisibleMultiblock?(TileInvisibleMultiblock)this.field_145850_b.getTileEntity(x, y, z):null;
			if(tile != null) {
				tile.master = new TileLocation(this.field_145851_c, this.field_145848_d, this.field_145849_e);
			}

			return true;
		}
	}

	private boolean isAir(int x, int y, int z, boolean set) {
		if(set) {
			this.field_145850_b.setBlock(x, y, z, Blocks.air);
			return true;
		} else {
			return this.field_145850_b.getBlock(x, y, z) == Blocks.air;
		}
	}

	public boolean isOnline() {
		return this.online;
	}

	private void activateStabilizers() {
		for(int capacity = 0; capacity < this.stabilizers.length; ++capacity) {
			if(this.stabilizers[capacity] == null) {
				LogHelper.error("activateStabilizers stabalizers[" + capacity + "] == null!!!");
				return;
			}

			TileParticleGenerator tile = this.field_145850_b.getTileEntity(this.stabilizers[capacity].getXCoord(), this.stabilizers[capacity].getYCoord(), this.stabilizers[capacity].getZCoord()) != null && this.field_145850_b.getTileEntity(this.stabilizers[capacity].getXCoord(), this.stabilizers[capacity].getYCoord(), this.stabilizers[capacity].getZCoord()) instanceof TileParticleGenerator?(TileParticleGenerator)this.field_145850_b.getTileEntity(this.stabilizers[capacity].getXCoord(), this.stabilizers[capacity].getYCoord(), this.stabilizers[capacity].getZCoord()):null;
			if(tile == null) {
				LogHelper.error("Missing Tile Entity (Particle Generator)");
				return;
			}

			tile.stabilizerMode = true;
			tile.setMaster(new TileLocation(this.field_145851_c, this.field_145848_d, this.field_145849_e));
			this.field_145850_b.setBlockMetadataWithNotify(this.stabilizers[capacity].getXCoord(), this.stabilizers[capacity].getYCoord(), this.stabilizers[capacity].getZCoord(), 1, 2);
		}

		double var3 = 0.0D;
		switch(this.tier) {
			case 0:
				var3 = 4.55E7D;
				break;
			case 1:
				var3 = 2.73E8D;
				break;
			case 2:
				var3 = 1.64E9D;
				break;
			case 3:
				var3 = 9.88E9D;
				break;
			case 4:
				var3 = 5.93E10D;
				break;
			case 5:
				var3 = 3.56E11D;
				break;
			case 6:
				var3 = 2.14E12D;
		}

		this.capacity = var3;
		if(this.energy > var3) {
			this.energy = var3;
		}

	}

	public void deactivateStabilizers() {
		for(int i = 0; i < this.stabilizers.length; ++i) {
			if(this.stabilizers[i] == null) {
				LogHelper.error("activateStabilizers stabalizers[" + i + "] == null!!!");
			} else {
				TileParticleGenerator tile = this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) != null && this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) instanceof TileParticleGenerator?(TileParticleGenerator)this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()):null;
				if(tile != null) {
					tile.stabilizerMode = false;
					this.field_145850_b.setBlockMetadataWithNotify(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord(), 0, 2);
				}
			}
		}

	}

	private boolean areStabilizersActive() {
		for(int i = 0; i < this.stabilizers.length; ++i) {
			if(this.stabilizers[i] == null) {
				LogHelper.error("activateStabilizers stabalizers[" + i + "] == null!!!");
				return false;
			}

			TileParticleGenerator tile = this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) != null && this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) instanceof TileParticleGenerator?(TileParticleGenerator)this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()):null;
			if(tile == null) {
				return false;
			}

			if(!tile.stabilizerMode || this.field_145850_b.getBlockMetadata(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) != 1) {
				return false;
			}
		}

		return true;
	}

	private boolean checkStabilizers() {
		int i = 0;

		while(i < this.stabilizers.length) {
			if(this.stabilizers[i] == null) {
				return false;
			}

			TileParticleGenerator gen = this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) != null && this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()) instanceof TileParticleGenerator?(TileParticleGenerator)this.field_145850_b.getTileEntity(this.stabilizers[i].getXCoord(), this.stabilizers[i].getYCoord(), this.stabilizers[i].getZCoord()):null;
			if(gen != null && gen.stabilizerMode) {
				if(gen.getMaster().field_145851_c == this.field_145851_c && gen.getMaster().field_145848_d == this.field_145848_d && gen.getMaster().field_145849_e == this.field_145849_e) {
					++i;
					continue;
				}

				return false;
			}

			return false;
		}

		return true;
	}

	public int getTier() {
		return this.tier;
	}

	public void func_145841_b(NBTTagCompound compound) {
		super.func_145841_b(compound);
		compound.setBoolean("Online", this.online);
		compound.setShort("Tier", (short)this.tier);
		compound.setDouble("Energy", this.energy);

		for(int i = 0; i < this.stabilizers.length; ++i) {
			if(this.stabilizers[i] != null) {
				this.stabilizers[i].writeToNBT(compound, String.valueOf(i));
			}
		}

	}

	public void func_145839_a(NBTTagCompound compound) {
		this.online = compound.getBoolean("Online");
		this.tier = compound.getShort("Tier");
		this.energy = compound.getDouble("Energy");

		for(int capacity = 0; capacity < this.stabilizers.length; ++capacity) {
			if(this.stabilizers[capacity] != null) {
				this.stabilizers[capacity].readFromNBT(compound, String.valueOf(capacity));
			}
		}

		double var4 = 0.0D;
		switch(this.tier) {
			case 0:
				var4 = 4.55E7D;
				break;
			case 1:
				var4 = 2.73E8D;
				break;
			case 2:
				var4 = 1.64E9D;
				break;
			case 3:
				var4 = 9.88E9D;
				break;
			case 4:
				var4 = 5.93E10D;
				break;
			case 5:
				var4 = 3.56E11D;
				break;
			case 6:
				var4 = 2.14E12D;
		}

		this.capacity = var4;
		if(this.energy > var4) {
			this.energy = var4;
		}

		super.func_145839_a(compound);
	}

	public Packet func_145844_m() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.func_145841_b(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.func_145839_a(pkt.func_148857_g());
	}

	public int receiveEnergy(int maxReceive, boolean simulate) {
		int maxReceive1 = 2147483647;
		double energyReceived = Math.min(this.capacity - this.energy, (double)Math.min(maxReceive1, maxReceive));
		if(!simulate) {
			this.energy += energyReceived;
		}

		return (int)energyReceived;
	}

	public int extractEnergy(int maxExtract, boolean simulate) {
		int maxExtract1 = 2147483647;
		double energyExtracted = Math.min(this.energy, (double)Math.min(maxExtract1, maxExtract));
		if(!simulate) {
			this.energy -= energyExtracted;
		}

		return (int)energyExtracted;
	}

	public double getEnergyStored() {
		return this.energy;
	}

	public double getMaxEnergyStored() {
		return this.capacity;
	}

	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	private void detectAndRendChanges() {
		int diff = (int)Math.abs(this.lastTickCapacity - this.energy);
		if(diff > 100000) {
			this.lastTickCapacity = (Double)sendObject((byte)5, 0, this.energy);
		}

	}

	public void receiveObject(int index, Object object) {
		this.energy = (Double) object;
	}
}
