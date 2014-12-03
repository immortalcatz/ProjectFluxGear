package mortvana.projectfluxgear.fluid;

import cofh.core.fluid.BlockFluidCoFHBase;
import cpw.mods.fml.common.registry.GameRegistry;
import mortvana.projectfluxgear.common.FluxGearContent;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import mortvana.fluxgearcore.util.chemistry.reaction.IBioactive;
import mortvana.fluxgearcore.legacy.util.IRegistrable;

public class BlockFluidSmog extends BlockFluidCoFHBase implements IBioactive, IRegistrable {

    public static final int LEVELS = 6;
    public static final Material materialFluidSmog = new MaterialLiquid(MapColor.brownColor);

    private static boolean enableSourceFloat = true;
    private static int maxSmogHeight = 256;

	public BlockFluidSmog(String fluidName) {
        super("thermaltinkerer", FluxGearContent.fluidSmog, materialFluidSmog, "smog");
        setQuantaPerBlock(LEVELS);
        setTickRate(20);

        setHardness(1F);
        setLightOpacity(0);
        //setParticleColor(1.0F, 0.9F, 0.05F);
	}

    @Override
    public boolean preInit() {

        GameRegistry.registerBlock(this, "FluidSmog");
        return true;
    }

    /*@Override
    public void updateTick(World world, int x, int y, int z, Random rand) {

        if (world.getBlockMetadata(x, y, z) == 0) {
            if (rand.nextInt(3) == 0) {
                if (shouldSourceBlockDisapate(world, x, y, z)) {
                    world.setBlock(x, y, z, null);
                    return;
                }
                if (shouldSourceBlockFloat(world, x, y, z)) {
                    world.setBlock(x, y + densityDir, z, this, 0, 3);
                    world.setBlockToAir(x, y, z);
                    return;
                }
            }
        } else if (y + densityDir > maxSmogHeight) {

            int quantaRemaining = quantaPerBlock - world.getBlockMetadata(x, y, z);
            int expQuanta = -101;
            int y2 = y - densityDir;

            if (world.getBlock(x, y2, z) == this || world.getBlock(x - 1, y2, z) == this || world.getBlock(x + 1, y2, z) == this
                    || world.getBlock(x, y2, z - 1) == this || world.getBlock(x, y2, z + 1) == this) {
                expQuanta = quantaPerBlock - 1;

            } else {
                int maxQuanta = -100;
                maxQuanta = getLargerQuanta(world, x - 1, y, z, maxQuanta);
                maxQuanta = getLargerQuanta(world, x + 1, y, z, maxQuanta);
                maxQuanta = getLargerQuanta(world, x, y, z - 1, maxQuanta);
                maxQuanta = getLargerQuanta(world, x, y, z + 1, maxQuanta);

                expQuanta = maxQuanta - 1;
            }
            // decay calculation
            if (expQuanta != quantaRemaining) {
                quantaRemaining = expQuanta;
                if (expQuanta <= 0) {
                    world.setBlockToAir(x, y, z);
                } else {
                    world.setBlockMetadataWithNotify(x, y, z, quantaPerBlock - expQuanta, 3);
                    world.scheduleBlockUpdate(x, y, z, this, tickRate);
                    world.notifyBlocksOfNeighborChange(x, y, z, this);
                }
            }
            return;
        }
        super.updateTick(world, x, y, z, rand);
    }*/

    protected boolean shouldSourceBlockFloat(World world, int x, int y, int z) {

        return enableSourceFloat && (world.getBlock(x, y + densityDir, z) == this && world.getBlockMetadata(x, y + densityDir, z) != 0);
    }


    @Override
	public boolean contactAffectCreature(EntityLivingBase affected) {
		return false;
	}

	@Override
	public boolean drinkAffectCreature(EntityLivingBase affected) {
		return breatheAffectCreature(affected);
	}

	@Override
	public boolean bloodstreamAffectCreature(EntityLivingBase affected) {
		return breatheAffectCreature(affected);
	}

	@Override
	public boolean breatheAffectCreature(EntityLivingBase affected) {
		if(affected != null) {
			affected.addPotionEffect(new PotionEffect(Potion.poison.id, 250, 1));
			affected.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 250, 1));
			return true;
		}
		return false;
	}

	@Override
	public boolean canContactAffectCreature() {
		return false;
	}

	@Override
	public boolean canDrinkAffectCreature() {
		return true;
	}

	@Override
	public boolean canBloodstreamAffectCreature() {
		return true;
	}

	@Override
	public boolean canBreatheAffectCreature() {
		return true;
	}
	@Override
	public String getEnglishName() {
		return this.getLocalizedName();
	}

	@Override
	public String getGameRegistryName() {
		return this.getUnlocalizedName();
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
