package mortvana.projectfluxgear.block;

import mortvana.projectfluxgear.block.logic.NamingTableLogic;
import mortvana.projectfluxgear.common.ProjectFluxGear;
import mortvana.fluxgearcore.inventory.InventoryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWoodenTileEntity extends InventoryBlock {

    public BlockWoodenTileEntity() {
        super(Material.wood);
        setCreativeTab(ProjectFluxGear.tab).setHardness(2.0F).setStepSound(Block.soundTypeWood);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (metadata) {
            case 0:
                return new NamingTableLogic();

            default:
                return null;
        }
    }

    @Override
    public Integer getGui(World world, int x, int y, int z, EntityPlayer entityplayer) {
        int metadata = world.getBlockMetadata(x, y, z);
        switch (metadata) {
            case 0:
                return 0;

            default:
                return null;
        }
    }

    @Override
    public Object getModInstance() {
        return ProjectFluxGear.instance;
    }

    @Override
    public String[] getTextureNames() {
        return new String[0];
    }

    @Override
    public String getTextureDomain(int textureNameIndex) {
        return null;
    }
}