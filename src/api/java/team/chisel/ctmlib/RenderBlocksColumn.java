package team.chisel.ctmlib;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Used by {@link TextureType#CTMV}
 */
public class RenderBlocksColumn extends RenderBlocks {

	public TextureSubmap submap;
	public IIcon iconTop;
	private boolean inWorld;

	private IIcon sides[] = new IIcon[6];
	private CTM ctm = CTM.getInstance();

	public RenderBlocksColumn() {
		super();
	}

	boolean connected(IBlockAccess world, int x, int y, int z, Block block, int meta) {
		Block inWorld = ctm.getBlockOrFacade(world, x, y, z, -1);
		return inWorld != null && inWorld.equals(block) && ctm.getBlockOrFacadeMetadata(world, x, y, z, -1) == meta;
	}

	@Override
	public boolean renderStandardBlock(Block block, int x, int y, int z) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		inWorld = true;

		boolean yp = connected(blockAccess, x, y + 1, z, block, metadata);
		boolean yn = connected(blockAccess, x, y - 1, z, block, metadata);

		if (yp || yn) {
			sides[0] = iconTop;
			sides[1] = iconTop;

			if (yp && yn)
				sides[2] = submap.getSubIcon(0, 1);
			else if (yp)
				sides[2] = submap.getSubIcon(1, 1);
			else
				sides[2] = submap.getSubIcon(1, 0);

			sides[3] = sides[4] = sides[5] = sides[2];
		} else {
			boolean xp = connected(blockAccess, x + 1, y, z, block, metadata);
			boolean xn = connected(blockAccess, x - 1, y, z, block, metadata);

			if (xp && (connected(blockAccess, x + 1, y + 1, z, block, metadata) || connected(blockAccess, x + 1, y - 1, z, block, metadata)))
				xp = false;
			if (xn && (connected(blockAccess, x - 1, y + 1, z, block, metadata) || connected(blockAccess, x - 1, y - 1, z, block, metadata)))
				xn = false;

			if (xp || xn) {
				uvRotateEast = 2;
				uvRotateWest = 1;
				uvRotateTop = 1;
				uvRotateBottom = 1;

				sides[4] = iconTop;
				sides[5] = iconTop;

				// for some reason along the x axis the bottom texture on the ends is backwards. This flips it around.
				if (xp && xn) {
					sides[1] = submap.getSubIcon(0, 1);
					sides[0] = sides[1];
				} else if (xp) {
					sides[1] = submap.getSubIcon(1, 1);
					sides[0] = submap.getSubIcon(1, 0);
				} else {
					sides[1] = submap.getSubIcon(1, 0);
					sides[0] = submap.getSubIcon(1, 1);
				}
				sides[2] = sides[3] = sides[1];
			} else {
				boolean zp = connected(blockAccess, x, y, z + 1, block, metadata);
				boolean zn = connected(blockAccess, x, y, z - 1, block, metadata);

				if (zp && (connected(blockAccess, x, y + 1, z + 1, block, metadata) || connected(blockAccess, x, y - 1, z + 1, block, metadata)))
					zp = false;
				if (zp && (connected(blockAccess, x + 1, y, z + 1, block, metadata) || connected(blockAccess, x - 1, y, z + 1, block, metadata)))
					zp = false;
				if (zn && (connected(blockAccess, x, y + 1, z - 1, block, metadata) || connected(blockAccess, x, y - 1, z - 1, block, metadata)))
					zn = false;
				if (zn && (connected(blockAccess, x + 1, y, z - 1, block, metadata) || connected(blockAccess, x - 1, y, z - 1, block, metadata)))
					zn = false;

				if (zp || zn) {
					uvRotateSouth = 1;
					uvRotateNorth = 2;

					sides[2] = iconTop;
					sides[3] = iconTop;

					if (zp && zn)
						sides[0] = submap.getSubIcon(0, 1);
					else if (zp)
						sides[0] = submap.getSubIcon(1, 0);
					else
						sides[0] = submap.getSubIcon(1, 1);

					sides[1] = sides[4] = sides[5] = sides[0];
				} else {
					sides[0] = sides[1] = iconTop;
					sides[2] = sides[3] = sides[4] = sides[5] = submap.getSubIcon(0, 0);
				}
			}
		}

		boolean flag = super.renderStandardBlock(block, x, y, z);

		uvRotateSouth = 0;
		uvRotateEast = 0;
		uvRotateWest = 0;
		uvRotateNorth = 0;
		uvRotateTop = 0;
		uvRotateBottom = 0;

		inWorld = false;
		return flag;
	}

	@Override
	public void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceXNeg(block, x, y, z, getIcon(icon, 4));
	}

	@Override
	public void renderFaceXPos(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceXPos(block, x, y, z, getIcon(icon, 5));
	}

	@Override
	public void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceZNeg(block, x, y, z, getIcon(icon, 2));
	}

	@Override
	public void renderFaceZPos(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceZPos(block, x, y, z, getIcon(icon, 3));
	}

	@Override
	public void renderFaceYNeg(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceYNeg(block, x, y, z, getIcon(icon, 0));
	}

	@Override
	public void renderFaceYPos(Block block, double x, double y, double z, IIcon icon) {
		super.renderFaceYPos(block, x, y, z, getIcon(icon, 1));
	}

	private IIcon getIcon(IIcon defaultIcon, int side) {
		return sides[side] == null || !inWorld ? defaultIcon : sides[side];
	}
}