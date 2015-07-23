package mortvana.legacy.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mantle.blocks.abstracts.InventoryBlock;
import mantle.blocks.iface.IActiveLogic;
import mantle.blocks.iface.IFacingLogic;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.legacy.block.tileentity.WoodmillLogic;
import mortvana.legacy.common.FluxGearContent;
import mortvana.legacy.common.ProjectFluxGear;

public class BlockMachine extends InventoryBlock {

    public BlockMachine(Material material)
    {
        super(Material.iron);
    }

    @Override
    public TileEntity createTileEntity (World world, int metadata)
    {
        switch (metadata)
        {
            case 0:
                return null; /*Alloy Furnace*/
            case 1:
                return null; /*Grindstone*/
            case 2:
                return null; /*Alloy Crucible*/
            case 3:
                return null; /*Stone Anvil*/
            case 4:
                return new WoodmillLogic();
            default:
                return null;
        }
    }

    @Override
    public TileEntity createNewTileEntity (World var1, int metadata){
        switch (metadata){
            case 0:
                return null; /*Alloy Furnace*/
            case 1:
                return null; /*Grindstone*/
            case 2:
                return null; /*Alloy Crucible*/
            case 3:
                return null; /*Alloy Caster*/
            case 4:
                return new WoodmillLogic();
            }
        return null;
        }

    @Override
    public Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        int md = world.getBlockMetadata(x, y, z);
        switch (md){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return null;
        }


}
    //TODO
    //Doesn't this need this
    public TileEntity getBlockTileEntity(){
        return null;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        BlockCrank cr = (BlockCrank) FluxGearContent.tileCrank;
        NBTTagCompound c = cr.p.data;

        c = new NBTTagCompound();
        int power = c.getInteger("power");
        System.out.println(c.getInteger("power" + "TTTTT"));

        return true;
    }

    @Override
    public Object getModInstance ()
    {
        return ProjectFluxGear.instance;
    }

    //TODO
    //Fix this so other machines will work
    @Override
    public String[] getTextureNames ()  {
        String[] textureNames = { "woodmill_front", "woodmill_front_active", "woodmill_side", "woodmill_top" };

        return textureNames;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta) {
        return icons[(meta) * 3 + getTextureIndex(side)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (IBlockAccess world, int x, int y, int z, int side)  {
        TileEntity logic = world.getTileEntity(x, y, z);
        short direction = (logic instanceof IFacingLogic) ? ((IFacingLogic) logic).getRenderDirection() : 0;
        int meta = world.getBlockMetadata(x, y, z) % 8;

        if (meta == 4) {
            if (side == direction)  {
                if (((IActiveLogic) logic).getActive())
                    return icons[1];
                else
                    return icons[0];
            }  else if (side > 1) {
                return icons[2];
            }
            return icons[3];
        }
        return icons[0];
    }

    public int getTextureIndex (int side) {
        if (side == 0 || side == 1)
            return 3;
        if (side == 3)
            return 0;

        return 2;
    }

    @Override
    public String getTextureDomain (int textureNameIndex) {
        return "morttech";
    }
}