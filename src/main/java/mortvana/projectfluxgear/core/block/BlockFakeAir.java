package mortvana.projectfluxgear.core.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.projectfluxgear.library.FluxGearLibrary;

import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockFakeAir extends FluxGearBlock {

    public BlockFakeAir() {
        super(Config.airyMaterial, FluxGearLibrary.debugTab); //TODO: Our air material
        setTickRandomly(true);
        setLightLevel(15);
        setStepSound(new SoundType("cloth", 0.0F, 1.0F));
        setUnlocalizedName("blockThaumicAir");
        setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
        setAllHardness(0);
    }

    // Can't touch this
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    // I'm different
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return null;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public int getLightValue() {
        return 15;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        blockIcon = register.registerIcon("thaumcraft:blank");
    }

    @SideOnly(Side.CLIENT)
    public void getIcon() {}

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (world.getBlock(x, y, z) == this) {
            world.scheduleBlockUpdate(x, y, z, this, 1);
        }
    }

    @Override
    public boolean requiresUpdates() {
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (world.isRemote) {
            if (world.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
                Thaumcraft.proxy.wispFX3(world, x + 0.5F, y + 0.5F, z + 0.5F, x + 0.3F + rand.nextFloat() * 0.4F, y + 0.5F, z + 0.3F + rand.nextFloat() * 0.4F, 0.5F, 0, true, -0.025F);
            }

            if (world.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
                Thaumcraft.proxy.wispFX3(world, x + 0.5F, y + 0.5F, z + 0.5F, x + 0.4F + rand.nextFloat() * 0.2F, y + 0.5F, z + 0.4F + rand.nextFloat() * 0.2F, 0.25F, 2, true, -0.02F);
            }
        }
    }}
