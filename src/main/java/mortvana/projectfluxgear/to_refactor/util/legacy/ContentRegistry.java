package mortvana.projectfluxgear.to_refactor.util.legacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import mortvana.projectfluxgear.to_refactor.util.legacy.fluid.BucketEventManager;
import mortvana.projectfluxgear.to_refactor.util.legacy.util.IReactionReceiver;
import mortvana.projectfluxgear.to_refactor.util.legacy.util.ISoundProvider;
import mortvana.projectfluxgear.to_refactor.util.chemistry.reaction.IReactionSpec;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
* Intended to wrap and hide all of the "tell Forge this object exists" sort of thing,
* including outgoing inter-mod interaction.
*
* Helps allow properties of blocks, items, etc... to be set declaratively in the class,
* rather than procedurally in some extremely bloated initialization method.
*
* Author: Gyro
*/
@Deprecated
public class ContentRegistry implements IFuelHandler {

    //My favorite superhero.
    public BucketEventManager bucketMan;
    
    ArrayList<Item> itemsToRegister;
    ArrayList<Block> blocksToRegister;
    ArrayList<Fluid> fluidsToRegister;
    ArrayList<TileEntity> tileentitiesToRegister;

    ArrayList<IRecipe> recipesToRegister;

    private ArrayList<IReactionReceiver> reactants;

    Configuration config;

    public Logger logger;

    CreativeTabs cTab;

    public static final String modID = "";

    public ArrayList<String> soundNames = new ArrayList<String>(24);

    //For Vanilla furnaces. (ItemID, DamageValue) to burn time.
    private HashMap<ImmutablePair<Item, Integer>, Integer> fuelDataSpecific = new HashMap<ImmutablePair<Item, Integer>, Integer>(10);
    //For Vanilla furnaces. Item ID to burn time, does not care about damage value.
    private HashMap<Item, Integer> fuelDataSimple = new HashMap<Item, Integer>(10);

    public ContentRegistry(Configuration setConfig, Logger setLogger, CreativeTabs setTab) {
        config = setConfig;
        logger = setLogger;
        cTab = setTab;

        bucketMan = new BucketEventManager();

        //Give it a large size hint.
        itemsToRegister = new ArrayList<Item>(64);
        blocksToRegister = new ArrayList<Block>(64);
        fluidsToRegister = new ArrayList<Fluid>(16);
        tileentitiesToRegister = new ArrayList<TileEntity>(32);
        //initToDo = new ArrayList<IDeferredInit>(64);
        recipesToRegister = new ArrayList<IRecipe>(128);
        reactants = new ArrayList<IReactionReceiver>(8);
    }

    public void GeneralRegister (Object reg) {
        //Don't just silently break if we're overwriting things.
        if (reg instanceof IReactionReceiver) {
            RegisterReactionReceiver((IReactionReceiver) reg);
        }
    }

    public void RegisterSounds (ISoundProvider prov) {
        soundNames.addAll(prov.getSounds());
    }

    //Automatically calls RegisterRegistrable if the fluid is a registrable.
    public boolean RegisterFluid (Fluid fluid) {
        GeneralRegister(fluid);
        //fluidsToRegister.add(fluid);

        FluidRegistry.registerFluid(fluid);
        return true;
    }

    //Automatically calls RegisterRegistrable if the block is a registrable and RegisterSounds if ISoundProvider.
    public boolean RegisterBlock (Block block) {
        GeneralRegister(block);
        if (block instanceof ISoundProvider) {
            RegisterSounds((ISoundProvider) block);
        }
        if (block instanceof ITileEntityProvider) {
            //Check to see if this provides us with associated tile entities.
            ITileEntityProvider ourBlock = (ITileEntityProvider) block;
            TileEntity addition = ourBlock.createNewTileEntity(null, 0);
            if (addition != null) {
                //Do configuration for configgable tile entities here.
                GeneralRegister(addition);
                tileentitiesToRegister.add(addition);
            }
        }
        blocksToRegister.add(block);
        return true;
    }

    protected void FinalizeTileEntities () {
        for (int i = 0; i < tileentitiesToRegister.size(); ++i) {
            TileEntity te = tileentitiesToRegister.get(i);
        }
    }

    //Registers everything we've been given so far with
    public void FinalizeContent () {
        FinalizeTileEntities();

        //This is where you can tell I'm used to C++.
        itemsToRegister = null;
        blocksToRegister = null;
        fluidsToRegister = null;
        tileentitiesToRegister = null;
        recipesToRegister = null;
        reactants = null;
    }

    //Chemistry stuff goes here:
    public void RegisterReactionReceiver (IReactionReceiver rec)
    {
        reactants.add(rec);
    }

    public void RegisterReaction (IReactionSpec reaction) {
        for (IReactionReceiver rec : reactants) {
            rec.registerReaction(reaction);
        }
    }

    @Override
    public int getBurnTime (ItemStack fuel) {
        if (fuelDataSimple.containsKey(fuel)) {
            return fuelDataSimple.get(fuel);
        }
        ImmutablePair<Integer, Integer> itemDef = new ImmutablePair(fuel, fuel.getItemDamage());
        if (fuelDataSpecific.containsKey(itemDef)) {
            return fuelDataSpecific.get(itemDef);
        }
        return 0;
    }
}