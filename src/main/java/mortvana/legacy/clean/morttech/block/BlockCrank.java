package mortvana.legacy.clean.morttech.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;

public class BlockCrank extends Block {

    public BlockCrank(Material material) {
        super(material);
    }

	private IIcon itemIcon;

	public BlockCrank() {
    	super(Material.wood);
    }

    public S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity();

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
	    WoodmillLogic tile = (WoodmillLogic) world.getTileEntity(x, y - 1, z);
	    if (tile != null) {
		    tile.power += 10;
	    }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
	    itemIcon = iconRegister.registerIcon("morttech:crank");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int par5) {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getMetadata())  {
            case 0:
                list.add("A simple, but useful wooden crank.");
                list.add("Used to power most simple machinery.");
                break;

        }
    }
}
