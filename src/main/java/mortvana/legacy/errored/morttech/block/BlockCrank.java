package mortvana.legacy.errored.morttech.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.legacy.errored.morttech.block.tile.TileWoodmill;
import mortvana.legacy.dependent.firstdegree.morttech.block.tile.WoodmillLogic;

public class BlockCrank extends Block {

    public BlockCrank(Material material) {
        super(material);
    }

	private IIcon itemIcon;

	public BlockCrank() {
    	super(Material.wood);
    }

    private int power = 10;
    Packet132TileEntityData p = new Packet132TileEntityData();

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        NBTTagCompound c = p.data;

        c = new NBTTagCompound();
        c.setInteger("power", power);
        System.out.println(c.getInteger("power"));

        return true;
    }

    //TODO
    //Fixing...
    @Override
    public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entPlayer) {

        WoodmillLogic tile = (BlockMachine) world.getTileEntity(/*world, */xCoord, yCoord - 1, zCoord/*, entPlayer*/);
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        TileWoodmill tile = (TileWoodmill) world.getTileEntity(x, y - 1, z);
        if (tile != null) {
            tile.furnaceBurnTime += 10;
        }

        return true;
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
