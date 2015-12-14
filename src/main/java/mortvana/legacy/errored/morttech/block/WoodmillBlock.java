package mortvana.legacy.errored.morttech.block;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.legacy.clean.morttech.block.tile.TileWoodmill;
import mortvana.legacy.dependent.firstdegree.morttech.client.gui.GuiWoodmill;

public class WoodmillBlock extends Block {

    public WoodmillBlock(Material m) {
        super(Material.iron);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileWoodmill();
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) {
            FMLClientHandler.instance().displayGuiScreen(player, new GuiWoodmill());
            return true;
        }
        return false;
    }
}
