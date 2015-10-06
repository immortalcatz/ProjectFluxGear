package mortvana.legacy.clean.wierdscience.util.block.tile;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.errored.weirdscience.block.tileentity.BlockContainerBase;

@Deprecated
//TODO: Make some implosions
//A block that renders some of its sides with a tank texture that varies.
//Used for the Hemoionic Dynamo and the Blood Donation Block.
public abstract class BlockMetaTank extends BlockContainerBase {

	protected ArrayList<String> tankTexNames = new ArrayList<String>(8);
	protected ArrayList<String> topTexNames = new ArrayList<String>(2);

	@SideOnly(Side.CLIENT)
	protected ArrayList<IIcon> tankIcons;

	@SideOnly(Side.CLIENT)
	protected ArrayList<IIcon> topIcons;

	public void addSidesTextureName(String str) {
		tankTexNames.add(str);
	}
	public void addTopTextureName(String str) {
		topTexNames.add(str);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister IIconRegister) {
		super.registerBlockIcons(IIconRegister);

		//Register tank IIcons.
		tankIcons = new ArrayList<IIcon>(tankTexNames.size());
		for(int i = 0; (i < tankTexNames.size()) && (i < 16); ++i) {
			tankIcons.add(IIconRegister.registerIcon(tankTexNames.get(i)));
		}
		//Register top IIcons.
		topIcons = new ArrayList<IIcon>(topTexNames.size());
		for(int i = 0; (i < topTexNames.size()) && (i < 16); ++i) {
			topIcons.add(IIconRegister.registerIcon(topTexNames.get(i)));
		}
		if(topIcons.size() < tankIcons.size()) { //Fill in the blanks
			IIcon last = topIcons.get(topIcons.size() - 1);
			while(topIcons.size() < tankIcons.size()) {
				topIcons.add(last);
			}
		} else if(topIcons.size() > tankIcons.size()) { //Fill in the blanks
			IIcon last = tankIcons.get(tankIcons.size() - 1);
			while(topIcons.size() > tankIcons.size()) {
				tankIcons.add(last);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if(side == 1) { //Top
			return topIcons.get(metadata);
		} else if(side == 0) { //Bottom
			return this.blockIcon;
		} else { //Sides (tank)
			return tankIcons.get(metadata);
		}
	}

	public void setMetaByFillPercent(World world, int x, int y, int z, int fillPercent) {
		world.setBlockMetadataWithNotify(x, y, z, (fillPercent * (tankTexNames.size()-1))/100, 1|2);
	}
	public BlockMetaTank(String name, Material material) {
		super(name, material);
	}
	public BlockMetaTank(String name) {
		super(name);
	}
	public BlockMetaTank(Material material) {
		super(material);
	}
}