package mortvana.legacy.refactored.wierdscience.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;

@Deprecated
//TODO: I just wanna watch the Google burn...
public class TileEntityFuelBurner extends TileEntityGenerator {
    protected int internalTemp = 0;
    @Override
    protected void init () {
        super.init();
    }

    @Override
    public void writeToNBT (NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        //Write heat
        nbt.setInteger("Temperature", internalTemp);
    }

    @Override
    public void readFromNBT (NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        internalTemp = nbt.getInteger("Temperature");
    }

    public String getEnglishName () {
        return "Fuel Burner";
    }

    public String getGameRegistryName () {
        return "tileEntityFuelBurner";
    }

    public boolean isEnabled () {
        return true;
    }

    @Override
    public void onChunkUnload () {
        super.onChunkUnload();
    }

    @Override
    public void onKill () {
        super.onKill();
    }
}
