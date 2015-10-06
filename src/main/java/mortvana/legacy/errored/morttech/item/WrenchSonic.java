package mortvana.legacy.errored.morttech.item;

import ic2.api.tile.IWrenchable;
import mortvana.legacy.errored.core.common.ProjectFluxGear;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if ((tileEntity instanceof IWrenchable)) {
            IWrenchable wrenchable = (IWrenchable)tileEntity;
            if (wrenchable.wrenchCanRemove(entityPlayer)) {
                if (ProjectFluxGear.proxy.isSimulating()) {
                    boolean dropOriginalBlock = false;
	                dropOriginalBlock = (wrenchable.getWrenchDropRate() < 1.0F) && ((overrideWrenchSuccessRate(itemstack)) || (world.rand.nextFloat() <= wrenchable.getWrenchDropRate()));
                    List<ItemStack> drops = block.getBlockDropped(world, x, y, z, metadata, 0);
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
            }
        }
	    return ProjectFluxGear.proxy.isSimulating();
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