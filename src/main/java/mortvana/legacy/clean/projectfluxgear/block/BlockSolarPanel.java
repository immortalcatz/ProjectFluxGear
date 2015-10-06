package mortvana.legacy.clean.projectfluxgear.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSolarPanel extends Block implements ITileEntityProvider {

    public String modId;

    public BlockSolarPanel(String modID, String name, int maxPowerGen, int powerStorage) {
        super(Material.iron);
        this.isBlockContainer = true;
    }

    @Override
    public boolean onBlockEventReceived(World pWorld, int x, int y, int z, int eventNumber, int eventArgument) {
        super.onBlockEventReceived(pWorld, x, y, z, eventNumber, eventArgument);
        TileEntity tileentity = pWorld.getTileEntity(x, y, z);
        if (tileentity != null) {
            return tileentity.receiveClientEvent(eventNumber, eventArgument);
        }
        return false;
    }

    public String getModId() {
        return modId;
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s:%s", getModId(), unwrapUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getTextureName() {
        return unwrapUnlocalizedName(getUnlocalizedName());
    }

    public String unwrapUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
