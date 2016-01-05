package mortvana.legacy.clean.thaumicrevelations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.projectfluxgear.library.FluxGearLibrary;

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
        super(Config.airyMaterial, FluxGearLibrary.thaumicRevelationsTab);
        setTickRandomly(true).setLightLevel(15).setStepSound(new SoundType("cloth", 0.0F, 1.0F)).setUnlocalizedName("blockThaumicAir").setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
    }

    // Can't touch this
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    // I'm different
    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return null;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        blockIcon = register.registerIcon("thaumcraft:blank");
    }

    @SideOnly(Side.CLIENT)
    public void getIcon() {}
}
