package mortvana.legacy.item;

import ic2.api.tile.IWrenchable;
import ic2.api.util.Keys;
import mortvana.legacy.common.ProjectFluxGear;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WrenchSonic extends Item {

    public WrenchSonic() {
        super();
        setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)  
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("morttech:wrenchSonic");
    }
        
    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int blockId = world.getBlockId(x, y, z);
        Block block = Block.blocksList[blockId];
        if (block == null) {
            return false;
        }
        int metaData = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if ((tileEntity instanceof IWrenchable)) {
            IWrenchable wrenchable = (IWrenchable)tileEntity;
            if (Keys.instance.isAltKeyDown(entityPlayer)) {
                for (int step = 1; step < 6; step++) {
                    if (entityPlayer.isSneaking()) {
                        side = (wrenchable.getFacing() + 6 - step) % 6;
                    } else {
                        side = (wrenchable.getFacing() + step) % 6;
                    }
                    if (wrenchable.wrenchCanSetFacing(entityPlayer, side)) {
                        break;
                    }
                }
            } else if (entityPlayer.isSneaking()) {
                side += side % 2 * -2 + 1;
            }
            if (wrenchable.wrenchCanSetFacing(entityPlayer, side)){
                if (ProjectFluxGear.proxy.isSimulating()) {
                    wrenchable.setFacing((short)side);
                }
                return ProjectFluxGear.proxy.isSimulating();
            }
            if (wrenchable.wrenchCanRemove(entityPlayer)) {
                if (ProjectFluxGear.proxy.isSimulating()) {
                    boolean dropOriginalBlock = false;
                    if ((wrenchable.getWrenchDropRate() < 1.0F) && (overrideWrenchSuccessRate(itemstack))) {
                        dropOriginalBlock = true;
                    } else {
                        dropOriginalBlock = world.rand.nextFloat() <= wrenchable.getWrenchDropRate();
                    }
                    List<ItemStack> drops = block.getBlockDropped(world, x, y, z, metaData, 0);
                    if (dropOriginalBlock) {
                        ItemStack wrenchDrop = wrenchable.getWrenchDrop(entityPlayer);
                        if (wrenchDrop != null) {
                            if (drops.isEmpty()) {
                                drops.add(wrenchDrop);
                            } else {
                                drops.set(0, wrenchDrop);
                            }
                        }
                    }
                    world.setBlockToAir(x, y, z);
                }
                return ProjectFluxGear.proxy.isSimulating();
            }
        }
        if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
            return ProjectFluxGear.proxy.isSimulating();
        }
        return false;
    }

    public boolean overrideWrenchSuccessRate(ItemStack itemStack) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0:
                list.add("A sturdy, high-tech wrench.");
                list.add("It's made of many complex alloys.");
                list.add("Can easily manipulate machinery.");
                break;
        }
    }
}