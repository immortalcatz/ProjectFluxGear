package mortvana.projectfluxgear.legacy;

import java.util.ArrayList;

import mortvana.projectfluxgear.common.FluxGearContent;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.fluxgearcore.legacy.ContentRegistry;
import mortvana.fluxgearcore.legacy.block.BlockBase;
import mortvana.fluxgearcore.legacy.fluid.BlockFluidClassicWS;
import mortvana.fluxgearcore.legacy.util.BlockFluidReactive;
import mortvana.fluxgearcore.util.chemistry.reaction.ReactionSpec;

import mortvana.projectfluxgear.fluid.BlockFluidAcid;
import mortvana.projectfluxgear.fluid.BlockFluidSmog;
import mortvana.projectfluxgear.legacy.block.BlockBloodDonation;
import mortvana.projectfluxgear.legacy.block.BlockBloodEngine;
import mortvana.projectfluxgear.legacy.block.BlockFuelBurner;
import mortvana.projectfluxgear.legacy.block.BlockGunpowderEngine;
import mortvana.projectfluxgear.legacy.block.BlockNitrateEngine;
import mortvana.projectfluxgear.legacy.block.BlockOccultEngine;
import mortvana.projectfluxgear.legacy.block.CongealedBloodBlock;

//TODO: Write some sort of generic reflection proxy class that caches the results it finds.
// ^ Actually a really strong use case for a Singleton.

// TODO: Put this in a proper spot
@Deprecated
public class WeirdScienceContent {
    public static final void RegisterContent (Configuration config, ContentRegistry cr, FMLPreInitializationEvent event) {
        //Constants.
        final int smogDetailDefault = 8;
        //Init fluids.
        BlockFluidAcid fluidAcid = new BlockFluidAcid("acid");
        BlockFluidAcid fluidBase = new BlockFluidAcid("base");
        BlockFluidSmog fluidSmog = new BlockFluidSmog("smog");
        Fluid fluidBlood = new Fluid("blood");
        fluidAcid.setUnlocalizedName("fluidAcid");
        //fluidSmog.setUnlocalizedName("fluidSmog");
        fluidBlood.setUnlocalizedName("fluidBlood");

        //Register fluids.
        cr.RegisterFluid(fluidAcid);
        cr.RegisterFluid(fluidBase);
        //cr.RegisterFluid(fluidSmog);
        cr.RegisterFluid(fluidBlood);

        //Init fluid blocks.
        //Fluids used must be registered first.
        BlockFluidClassicWS acidBlock = new BlockFluidReactive(fluidAcid);
        BlockFluidClassicWS baseBlock = new BlockFluidReactive(fluidBase);
        BlockFluidClassicWS bloodBlock = new BlockFluidClassicWS("Blood", Material.water, fluidBlood);

        //Ugly gas init code goes here.
        /*GasWrapper smogManager = new GasWrapper(new GasFactory() {
            public BlockGasBase Make (Configuration config, String name, Fluid fluid) {
                return new BlockGasSmog(config, name, fluid);
            }
        }, "Smog", fluidSmog, smogDetailDefault);
        cr.GeneralRegister(smogManager);
        //Slaving multiple block IDs to one set of behavior is such a pain in this game.
        ((BlockGasSmog) smogManager.blocks.get(0)).setBlockAcid(acidBlock); */

        /*acidBlock.setBlockTextureName("gui:placeholderacid");
        baseBlock.setBlockTextureName("gui:placeholderbase");
        bloodBlock.setBlockTextureName("gui:bloodStill");
        //smogManager.setTextureName("gui:smog");

        acidBlock.setBlockName("blockAcid");
        bloodBlock.setBlockName("blockBlood");

        //smogManager.setMBMax(1024);

        //Give fluids block IDs and icons.
        fluidAcid.setBlock(acidBlock);
        fluidBlood.setBlock(bloodBlock);
        fluidBase.setBlock(baseBlock);

        if (event.getSide() == Side.CLIENT) {
            fluidAcid.setIcons(acidBlock.getIcon(0, 0));
            fluidBase.setIcons(baseBlock.getIcon(0, 0));
            fluidBlood.setIcons(bloodBlock.getIcon(0, 0));
        }

        if (smogManager.isEnabled()) {
            if (event.getSide() == Side.CLIENT) {
                fluidSmog.setIcons(smogManager.blocks.get(0).getIcon(0, 0));
            }
            fluidSmog.setBlockID(smogManager.blocks.get(0).blockID);
        }*/

        //Register normal fluid blocks
        cr.RegisterBlock(acidBlock);
        cr.RegisterBlock(baseBlock);
        cr.RegisterBlock(bloodBlock);

        BlockBase aluminumSludge = new BlockBase(config, "Aluminosilicate Sludge", Material.clay);
        aluminumSludge.setBlockTextureName("gui:aluminosilicate_sludge");
        aluminumSludge.harvestType = "shovel";
        aluminumSludge.harvestLevel = 0;
        aluminumSludge.setHardness(0.3F);
        cr.RegisterBlock(aluminumSludge);

        //((BlockGasSmog) smogManager.blocks.get(0)).blockRust = blockRust;
        //((BlockGasSmog) smogManager.blocks.get(0)).metaRust = 0;

        //Init & register tile-entity-bearing blocks.

        BlockNitrateEngine nitrateEngineBlock = new BlockNitrateEngine(config, "Nitrate Engine", Material.rock);
        nitrateEngineBlock.setBlockName("blockNitrateEngine");
        //nitrateEngineBlock.setWaste(fluidSmog);
        cr.RegisterBlock(nitrateEngineBlock);

        BlockBloodEngine bloodEngineBlock = new BlockBloodEngine(config, "Hemoionic Dynamo", Material.rock);
        bloodEngineBlock.setBlockTextureName("gui:genericmachine");
        bloodEngineBlock.addTopTextureName("gui:genericmachine6_off");
        bloodEngineBlock.addTopTextureName("gui:genericmachine6_on");
        bloodEngineBlock.addSidesTextureName("gui:genericmachine_tank_0");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_1");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_2");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_3");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_4");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_5");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_6");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_7");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_8");
        cr.RegisterBlock(bloodEngineBlock);

        BlockBloodDonation donationBlock = new BlockBloodDonation(config, "Blood Donation Station", Material.rock);
        donationBlock.setBlockName("blockBloodDonation");
        donationBlock.setFluid(fluidBlood);
        donationBlock.setBlockTextureName("gui:genericmachine");
        donationBlock.addTopTextureName("gui:blooddonationtop");
        donationBlock.addTopTextureName("gui:blooddonationtop");
        donationBlock.addSidesTextureName("gui:genericmachine_tank_0");
        donationBlock.addSidesTextureName("gui:blood_tank_1");
        donationBlock.addSidesTextureName("gui:blood_tank_2");
        donationBlock.addSidesTextureName("gui:blood_tank_3");
        donationBlock.addSidesTextureName("gui:blood_tank_4");
        donationBlock.addSidesTextureName("gui:blood_tank_5");
        donationBlock.addSidesTextureName("gui:blood_tank_6");
        donationBlock.addSidesTextureName("gui:blood_tank_7");
        donationBlock.addSidesTextureName("gui:blood_tank_8");
        cr.RegisterBlock(donationBlock);

        BlockOccultEngine occultEngineBlock = new BlockOccultEngine(config, "Occult Engine", Material.rock);
        occultEngineBlock.setBlockTextureName("gui:occultengine_bottom");
        occultEngineBlock.addTopTextureName("gui:occultengine_top");
        occultEngineBlock.addSidesTextureName("gui:occultengine_empty");
        occultEngineBlock.addSidesTextureName("gui:occultengine_1");
        occultEngineBlock.addSidesTextureName("gui:occultengine_2");
        occultEngineBlock.addSidesTextureName("gui:occultengine_3");
        occultEngineBlock.addSidesTextureName("gui:occultengine_4");
        occultEngineBlock.addSidesTextureName("gui:occultengine_5");
        occultEngineBlock.addSidesTextureName("gui:occultengine_6");
        cr.RegisterBlock(occultEngineBlock);

        BlockGunpowderEngine gunpowderEngineBlock = new BlockGunpowderEngine(config, "Blast Engine", Material.rock);
        gunpowderEngineBlock.setBlockName("blockGunpowderEngine");
        cr.RegisterBlock(gunpowderEngineBlock);

        BlockFuelBurner fuelBurnerBlock = new BlockFuelBurner(config, "Fuel Burner", Material.rock);
        fuelBurnerBlock.setBlockName("blockFuelBurner");
        fuelBurnerBlock.setBlockTextureName("gui:retardcube");
        cr.RegisterBlock(fuelBurnerBlock);

        //Init and register items.
        //ItemFoodBase itemMelonPan = new ItemFoodBase(config, "Melonpan", 3, 0.6f);
        //itemMelonPan.setTextureName("gui:melonpan");
        //cr.RegisterItem(itemMelonPan);

        //itemAlum.congealedBlock = congealedBlock;

        //TileEntityGunpowderEngine.thermite = itemThermite;

        //Register chemistry.
        //Clay to slurry reaction.
        ReactionSpec clayDissolve = new ReactionSpec(fluidAcid, new ItemStack(Items.clay_ball), aluminumSludge, null);
        clayDissolve.soluteMin = 4; //Require 4 clay.
        clayDissolve.soluteAffected = true; //Delete the clay item when the reaction takes place.
        cr.RegisterReaction(clayDissolve);

        //Alum to aluminum dust reaction.
        ReactionSpec alumDissolve = new ReactionSpec(fluidBase, new ItemStack(/*itemAlum*/ Items.poisonous_potato), null, FluxGearContent.dustAluminium);
        alumDissolve.soluteAffected = true;
        alumDissolve.solventAffected = false;
        cr.RegisterReaction(alumDissolve);

        //Acids and bases kill grass dead.
        ReactionSpec grassDissolveAcid = new ReactionSpec();
        grassDissolveAcid.solvent = fluidAcid;
        grassDissolveAcid.solute = Blocks.grass;
        grassDissolveAcid.soluteTarget = Blocks.dirt;
        grassDissolveAcid.solventAffected = false;
        grassDissolveAcid.soluteAffected = true;
        cr.RegisterReaction(grassDissolveAcid);

        ReactionSpec grassDissolveBase = new ReactionSpec();
        grassDissolveBase.solvent = fluidBase;
        grassDissolveBase.solute = Blocks.grass;
        grassDissolveBase.soluteTarget = Blocks.dirt;
        grassDissolveBase.solventAffected = false;
        grassDissolveBase.soluteAffected = true;
        cr.RegisterReaction(grassDissolveBase);

        ArrayList<ItemStack> aluminiumIngots = OreDictionary.getOres("ingotAluminium");
        ArrayList<ItemStack> aluminiumOres = OreDictionary.getOres("oreAluminum");
        aluminiumOres.addAll(OreDictionary.getOres("oreBauxite"));
        //Register aluminum ingot dissolution.
        if (aluminiumIngots != null && aluminiumIngots.size() > 0) {
            for (ItemStack item : aluminiumIngots) {
                ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, new ItemStack(/*itemAlum*/Items.poisonous_potato));
                aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                aluminumDissolve.soluteAffected = true;
                aluminumDissolve.solventAffected = false;
                cr.RegisterReaction(aluminumDissolve);
            }
        }
        //Register aluminum ore dissolution.
        if (aluminiumOres != null && aluminiumOres.size() > 0) {
            for (ItemStack item : aluminiumOres) {
                /* Note the stack size of 2: This allows item doubling for those willing to spend the effort and fuel
                 * to go the ore -> aluminosillicate slurry -> alum -> dissolved alum -> aluminum dust -> aluminum ingot path.
                 */
                ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, new ItemStack(aluminumSludge, 2, 0));
                aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                aluminumDissolve.soluteAffected = true;
                aluminumDissolve.solventAffected = false;
                cr.RegisterReaction(aluminumDissolve);
            }
        }

        //Register furnace stuff.
        //Wood to ashes smelting.
        ArrayList<ItemStack> woodPlanks = OreDictionary.getOres("plankWood");
        if (woodPlanks != null && woodPlanks.size() > 0) {
            for (ItemStack item : woodPlanks) {
                GameRegistry.addSmelting(item, FluxGearContent.dustAshes, 0.0F);
            }
        }
    }
}
