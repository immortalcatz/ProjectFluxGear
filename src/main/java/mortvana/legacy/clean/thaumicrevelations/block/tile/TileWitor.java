package mortvana.legacy.clean.thaumicrevelations.block.tile;

import net.minecraft.tileentity.TileEntity;

import thaumcraft.common.Thaumcraft;

public class TileWitor extends TileEntity {

    public boolean canUpdate() {return true;}

    public void updateEntity() {
        super.updateEntity();
        if (worldObj.isRemote) {
            if (worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
                Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, yCoord + 0.5F, zCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, 0.5F, 0, true, -0.025F);
            }

            if (worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
                Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, yCoord + 0.5F, zCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, 0.25F, 2, true, -0.02F);
            }
        }
    }
}