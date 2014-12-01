package mortvana.projectfluxgear.legacy;

import java.util.ArrayList;

import mortvana.fluxgearcore.legacy.ContentRegistry;
import mortvana.fluxgearcore.legacy.item.SubBucket;
import mortvana.fluxgearcore.legacy.util.crafting.DisableableRecipe;
import mortvana.projectfluxgear.fluid.BlockFluidAcid;
import mortvana.projectfluxgear.fluid.BlockFluidSmog;
import mortvana.projectfluxgear.legacy.item.Coagulant;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.projectfluxgear.legacy.block.BlockBloodDonation;
import mortvana.projectfluxgear.legacy.block.BlockBloodEngine;
import mortvana.projectfluxgear.legacy.block.BlockFuelBurner;
import mortvana.projectfluxgear.legacy.block.BlockGunpowderEngine;
import mortvana.projectfluxgear.legacy.block.BlockNitrateEngine;
import mortvana.projectfluxgear.legacy.block.BlockOccultEngine;
import mortvana.projectfluxgear.legacy.block.CongealedBloodBlock;
import mortvana.fluxgearcore.legacy.block.BlockBase;
import mortvana.fluxgearcore.legacy.fluid.BlockFluidClassicWS;
import mortvana.fluxgearcore.legacy.item.ItemBase;
import mortvana.fluxgearcore.legacy.item.ItemBucketWS;
import mortvana.fluxgearcore.legacy.util.chemistry.reaction.BlockFluidReactive;
import mortvana.fluxgearcore.legacy.util.chemistry.reaction.ReactionSpec;
import mortvana.fluxgearcore.legacy.util.crafting.SimpleRecipe;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

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
        /*GasWrapper smogManager = new GasWrapper(new GasFactory()
        {
            public BlockGasBase Make (Configuration config, String name, Fluid fluid)
            {
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

        if (event.getSide() == Side.CLIENT)
        {
            fluidAcid.setIcons(acidBlock.getIcon(0, 0));
            fluidBase.setIcons(baseBlock.getIcon(0, 0));
            fluidBlood.setIcons(bloodBlock.getIcon(0, 0));
        }
/*
        if (smogManager.isEnabled())
        {
            if (event.getSide() == Side.CLIENT)
            {
                fluidSmog.setIcons(smogManager.blocks.get(0).getIcon(0, 0));
            }
            fluidSmog.setBlockID(smogManager.blocks.get(0).blockID);
        }
*/
        //Register normal fluid blocks
        cr.RegisterBlock(acidBlock);
        cr.RegisterBlock(baseBlock);
        cr.RegisterBlock(bloodBlock);

        //Init & register basic blocks.
        CongealedBloodBlock congealedBlock = new CongealedBloodBlock(config, "Congealed Blood", Material.ground);
        congealedBlock.setBlockName("blockBloodCongealed");
        congealedBlock.setBlockTextureName("gui:congealedBloodBlock");
        cr.RegisterBlock(congealedBlock);

        BlockBase aluminumSludge = new BlockBase(config, "Aluminosilicate Sludge", Material.clay);
        aluminumSludge.setBlockTextureName("gui:aluminosilicate_sludge");
        aluminumSludge.harvestType = "shovel";
        aluminumSludge.harvestLevel = 0;
        aluminumSludge.setHardness(0.3F);
        cr.RegisterBlock(aluminumSludge);

        BlockBase blockRust = new BlockBase(config, "Rust", Material.iron);
        blockRust.setBlockTextureName("gui:rustblock");
        blockRust.harvestType = "pickaxe";
        blockRust.harvestLevel = 0;
        blockRust.setHardness(0.6F);
        cr.RegisterBlock(blockRust);

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

        Coagulant itemAlum = new Coagulant(config, "Alum");
        itemAlum.setTextureName("gui:coagulant");
        itemAlum.congealedBlock = congealedBlock;
        cr.RegisterItem(itemAlum);

        ItemBucketWS bucket = new ItemBucketWS(config, "Bucket");
        bucket.addSubBucket(new SubBucket("Blood Bucket", "gui:bloodbucket", bloodBlock));
        SubBucket bucketAcid = new SubBucket("Acid Bucket", "gui:acidbucket", acidBlock);
        bucket.addSubBucket(bucketAcid);
        SubBucket bucketBase = new SubBucket("Base Bucket", "gui:basebucket", baseBlock);
        bucket.addSubBucket(bucketBase);

        /*
        ItemBucketWS bucketBlood = new ItemBucketWS(config, "Blood Bucket", ItemBase.FindFreeItemID(), bloodBlock);
        bucketBlood.setTextureName("gui:bloodbucket");
        cr.RegisterItem(bucketBlood);

        ItemBucketWS bucketAcid = new ItemBucketWS(config, "Acid Bucket", ItemBase.FindFreeItemID(), acidBlock);
        bucketAcid.setTextureName("gui:acidbucket");
        cr.RegisterItem(bucketAcid);
        
        //heh
        ItemBucketWS bucketBase = new ItemBucketWS(config, "Lye Solution Bucket", ItemBase.FindFreeItemID(), baseBlock);
        bucketBase.setTextureName("gui:basebucket");
        cr.RegisterItem(bucketBase);*/
        cr.RegisterItem(bucket);

        ItemBase ingotAluminum = new ItemBase(config, "Aluminum Ingot");
        ingotAluminum.setTextureName("gui:aluminumingot");
        OreDictionary.registerOre("ingotAluminum", ingotAluminum);
        cr.RegisterItem(ingotAluminum);

        ItemBase dustAluminum = new ItemBase(config, "Aluminum Dust");
        dustAluminum.setTextureName("gui:aluminumdust");
        OreDictionary.registerOre("dustAluminum", dustAluminum);
        cr.RegisterItem(dustAluminum);

        ItemBase itemAshes = new ItemBase(config, "Ashes");
        itemAshes.setTextureName("gui:ashes");
        OreDictionary.registerOre("ashes", itemAshes);
        cr.RegisterItem(itemAshes);

        ItemBase itemRust = new ItemBase(config, "Rust Pile");
        itemRust.setTextureName("gui:rustpile");
        cr.RegisterItem(itemRust);

        blockRust.setItemDropped(new ItemStack(itemRust, 6, 0));
        blockRust.setDroppedRandomBonus(3);

        //TODO: Thermite item behavior.
        ItemBase itemThermite = new ItemBase(config, "Thermite");
        itemThermite.setTextureName("gui:thermiteitem");
        //20,000 is the fuel value of a lava bucket.
        int thermiteFuelValue = config.get("Thermite", "Furnace fuel value of Thermite", 5000).getInt();
        itemThermite.setFurnaceFuelValue(thermiteFuelValue);
        cr.RegisterItem(itemThermite);

        //TileEntityGunpowderEngine.thermite = itemThermite;

        //Register recipes.

        DisableableRecipe thermiteRecipe = new DisableableRecipe(new ItemStack(itemThermite, 1, 0), new Object[] { itemRust, dustAluminum }, true, false);
        //cr.RegisterRecipe(new DisableableRecipe(itemMelonPan, new Object[] { Item.bread, Item.melon }, true, false));
        cr.RegisterRecipe(new DisableableRecipe(new ItemStack(bucket, 1, bucketBase.getAssociatedMeta()), new Object[] { Items.water_bucket, itemAshes }, true, false));
        cr.RegisterRecipe(new DisableableRecipe(new ItemStack(bucket, 1, bucketAcid.getAssociatedMeta()), new Object[] { Items.water_bucket, Items.gunpowder }, true, false));
        cr.RegisterRecipe(thermiteRecipe);
        cr.RegisterRecipe(new SimpleRecipe(new ItemStack(blockRust, 1, 0), new Object[] { "rrr", "rrr", "rrr", 'r', itemRust }, false, false));

        //Machine recipes
        // Nitrate Engine
        cr.RegisterRecipe(new ShapedOreRecipe(new ItemStack(nitrateEngineBlock, 1, 0), "sss", "gcg", "sbs", 's', "stone", 'c', Items.slime_ball, 'g', "ingotGold", 'b', Items.bucket));
        // Blood Donation Station
        cr.RegisterRecipe(new ShapedOreRecipe(new ItemStack(donationBlock, 1, 0), "aba", "aga", "aba", 'a', "ingotAluminium", 'g', Blocks.glass, 'b', Items.bucket));
        // Blood Engine
        cr.RegisterRecipe(new ShapedOreRecipe(new ItemStack(bloodEngineBlock, 1, 0), "aba", "afa", "aaa", 'a', "ingotAluminium", 'f', Blocks.furnace, 'b', new ItemStack(Items.bucket)));
        // Occult Engine
        cr.RegisterRecipe(new ShapedOreRecipe(new ItemStack(occultEngineBlock, 1, 0), "gog", "oeo", "gog", 'e', bloodEngineBlock, 'o', Blocks.obsidian, 'g', "ingotGold"));
        // Blast Engine
        cr.RegisterRecipe(new ShapedOreRecipe(new ItemStack(gunpowderEngineBlock, 1, 0), "aia", "afa", "ana", 'a', "ingotAluminium", 'f', Blocks.furnace, 'n', Blocks.netherrack, 'i', Blocks.iron_bars));

        if (thermiteRecipe.isEnabled()) {

            GameRegistry.addShapedRecipe(new ShapelessOreRecipe(new ItemStack(itemThermite, 1, 0), "dustRust", "dustAluminium"));

            //Registers other aluminium dusts as items from which thermite can be made.
            ArrayList<ItemStack> aluminiumDusts = OreDictionary.getOres("dustAluminium");
            if (aluminiumDusts != null) {
                if (aluminiumDusts.size() > 0) {
                    for (ItemStack item : aluminiumDusts) {
                        cr.RegisterRecipe(new SimpleRecipe(new ItemStack(itemThermite, 1, 0), new Object[] { itemRust, item }, true, false));
                    }
                }
            }
        }

        //Register chemistry.
        //Clay to slurry reaction.
        ReactionSpec clayDissolve = new ReactionSpec(fluidAcid, new ItemStack(Items.clay_ball), aluminumSludge, null);
        clayDissolve.soluteMin = 4; //Require 4 clay.
        clayDissolve.soluteAffected = true; //Delete the clay item when the reaction takes place.
        cr.RegisterReaction(clayDissolve);

        //Alum to aluminum dust reaction.
        ReactionSpec alumDissolve = new ReactionSpec(fluidBase, new ItemStack(itemAlum), null, new ItemStack(dustAluminum));
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

        ArrayList<ItemStack> aluminumIngots = OreDictionary.getOres("ingotAluminum");
        ArrayList<ItemStack> aluminumOres = OreDictionary.getOres("oreAluminum");
        //aluminumOres.addAll(OreDictionary.getOres("oreBauxite"));
        //Register aluminum ingot dissolution.
        if (aluminumIngots != null)
        {
            if (aluminumIngots.size() > 0)
            {
                for (ItemStack item : aluminumIngots)
                {
                    ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, new ItemStack(itemAlum));
                    aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                    aluminumDissolve.soluteAffected = true;
                    aluminumDissolve.solventAffected = false;
                    cr.RegisterReaction(aluminumDissolve);
                }
            }
        }
        //Register aluminum ore dissolution.
        if (aluminumOres != null)
        {
            if (aluminumOres.size() > 0)
            {
                for (ItemStack item : aluminumOres)
                {
                    /* Note the stack size of 2: This allows item doubling for those willing to spend the effort and coal 
                     * to go the ore -> aluminosillicate slurry -> alum -> dissolved alum -> aluminum dust -> aluminum ingot path.
                     */
                    ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, new ItemStack(aluminumSludge, 2, 0));
                    aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                    aluminumDissolve.soluteAffected = true;
                    aluminumDissolve.solventAffected = false;
                    cr.RegisterReaction(aluminumDissolve);
                }
            }
        }

        //Register furnace stuff.
        GameRegistry.addSmelting(aluminumSludge, new ItemStack(itemAlum), 0.0F);
        GameRegistry.addSmelting(dustAluminum, new ItemStack(ingotAluminum), 0.0F);//plankWood 
        //Wood to ashes smelting.
        ArrayList<ItemStack> woodPlanks = OreDictionary.getOres("plankWood");
        if (woodPlanks != null)
        {
            if (woodPlanks.size() > 0)
            {
                for (ItemStack item : woodPlanks)
                {
                    GameRegistry.addSmelting(item, new ItemStack(itemAshes), 0.0F);
                }
            }
        }
        GameRegistry.addSmelting(blockRust, new ItemStack(Blocks.iron_block, 1, 0), 0.0F);

        boolean thermiteFuelEnabled = config.get("recipe", "Enable thermite as furnace fuel", true).getBoolean(true);
        if (thermiteFuelEnabled)
        {
        }

    }
}
