package mortvana.legacy.clean.weirdscience.util.block;

import net.minecraft.world.World;
@Deprecated
// TODO: Gyro, no, we store it in NBT :V
public interface IBlockMetaPower {
    //Tile Entity tells us power is on. We store this in metadata.
    void receivePowerOn(World world, int x, int y, int z);
    //Tile Entity tells us power is off. We store this in metadata.
    void receivePowerOff(World world, int x, int y, int z);
}