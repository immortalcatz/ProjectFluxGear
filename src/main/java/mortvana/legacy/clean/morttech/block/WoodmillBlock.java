package mortvana.legacy.clean.morttech.block;

import cpw.mods.fml.client.FMLClientHandler;

import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.legacy.clean.morttech.block.tile.TileWoodmill;
import mortvana.legacy.clean.morttech.client.gui.GuiWoodmill;

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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) {
            FMLClientHandler.instance().displayGuiScreen(player, new GuiWoodmill(player.inventory, new WoodmillLogic()));
            return true;
        }
        return false;
    }
}
