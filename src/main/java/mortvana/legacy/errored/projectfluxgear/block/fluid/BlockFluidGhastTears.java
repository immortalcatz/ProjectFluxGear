package mortvana.legacy.errored.projectfluxgear.block.fluid;

import cpw.mods.fml.common.registry.GameRegistry;

import cofh.core.fluid.BlockFluidCoFHBase;

import mortvana.legacy.errored.core.common.FluxGearContent;

public class BlockFluidGhastTears extends BlockFluidCoFHBase {

    public static final int LEVELS = 6;

    private static boolean enableSourceFloat = false;
    private static boolean effect = true;

    public BlockFluidGhastTears() {
        super("thermaltinkerer", FluxGearContent.fluidGhastTear, materialFluidGhastTear, "ghastTears");
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