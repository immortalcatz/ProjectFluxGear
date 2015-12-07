package mortvana.legacy.dependent.firstdegree.projectfluxgear.block.fluid;

import cpw.mods.fml.common.registry.GameRegistry;

import cofh.core.fluid.BlockFluidCoFHBase;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.errored.projectfluxgear.util.BlockInformation;

public class BlockFluidGhastTears extends BlockFluidCoFHBase {

    public static final int LEVELS = 6;

    private static boolean enableSourceFloat = false;
    private static boolean effect = true;

    public BlockFluidGhastTears() {
        super("thermaltinkerer", FluxGearContent.fluidGhastTear, BlockInformation.materialFluidGhastTear, "ghastTears");
        setQuantaPerBlock(LEVELS);
        setTickRate(30);

        setHardness(100F);
        setLightOpacity(1);
        setParticleColor(0.9F, 0.9F, 0.85F);
    }

    @Override
    public boolean preInit() {
        GameRegistry.registerBlock(this, "FluidGhastTears");
        return true;
    }
}