package oldcode.projectfluxgear.fluid;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import cpw.mods.fml.common.registry.GameRegistry;

import cofh.core.fluid.BlockFluidCoFHBase;

import mortvana.projectfluxgear.to_refactor.FluxGearContent_;

public class BlockFluidGhastTear extends BlockFluidCoFHBase {

    public static final int LEVELS = 6;
    public static final Material materialFluidGhastTear = new MaterialLiquid(MapColor.snowColor);

    private static boolean enableSourceFloat = false;
    private static boolean effect = true;

    public BlockFluidGhastTear() {
        super("thermaltinkerer", FluxGearContent_.fluidGhastTear, materialFluidGhastTear, "ghastTear");
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
