package mortvana.legacy.errored.apiology.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.mojang.authlib.GameProfile;
import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IIconProvider;
import forestry.api.genetics.*;
import mortvana.legacy.errored.core.common.config.BeeConfig;
import mortvana.legacy.util.LocalizationManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public enum BeeSpecies implements IAlleleBeeSpecies, IIconProvider {
    //enumerate here
    ;

    public static void setupBeeSpecies(){}

    private String binomial;
    private String authority;
    private int primaryColour;
    private int secondaryColour;
    private EnumTemperature temperature;
    private EnumHumidity humidity;
    private boolean hasEffect;
    private boolean isSecret;
    private boolean isCounted;
    private boolean isActive;
    private boolean isNocturnal;
    private IClassification branch;
    private HashMap<ItemStack, Integer> products;
    private HashMap<ItemStack, Integer> specialties;
    private IAllele genomeTemplate[];
    private String uid;
    private boolean dominant;
    private int complexity;

    @SideOnly(Side.CLIENT)
    private IIcon[][] icons;

    private final static int defaultBodyColour = 0xFF7C26;

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
        this(speciesName, genusName, classification, firstColour, defaultBodyColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
        this(speciesName, genusName, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
        this(speciesName, genusName, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
        this(speciesName, genusName, classification, firstColour, defaultBodyColour, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesSecret, boolean isSpeciesCounted, boolean isSpeciesDominant) {
        uid = "mortvanabees.species" + speciesName;
        dominant = isSpeciesDominant;
        AlleleManager.alleleRegistry.registerAllele(this);
        binomial = genusName;
        authority = "Lord Mortvana von Magnequazar";
        primaryColour = firstColour;
        secondaryColour = secondColour;
        temperature = preferredTemp;
        humidity = preferredHumidity;
        hasEffect = hasGlowEffect;
        isSecret = isSpeciesSecret;
        isCounted = isSpeciesCounted;
        products = new HashMap<ItemStack, Integer>();
        specialties = new HashMap<ItemStack, Integer>();
        branch = classification;
        branch.addMemberSpecies(this);
        isActive = true;
        isNocturnal = false;
    }

    public BeeSpecies setGenome(IAllele genome[]) {
        genomeTemplate = genome;
        return this;
    }

    public IAllele[] getGenome() {
        return genomeTemplate;
    }

    public BeeSpecies addProduct(ItemStack produce, int percentChance) {
        products.put(produce, percentChance);
        return this;
    }

    public BeeSpecies addSpecialty(ItemStack produce, int percentChance) {
        specialties.put(produce, percentChance);
        return this;
    }

    public ItemStack getBeeItem(EnumBeeType beeType) {
        return BeeManager.beeRoot.getMemberStack(BeeManager.beeRoot.getBee(null, BeeManager.beeRoot.templateAsGenome(genomeTemplate)), beeType.ordinal());
    }

    @Override
    public String getName() {
        return LocalizationManager.getLocalizedString(getUID());
    }

    @Override
    public String getDescription() {
        return LocalizationManager.getLocalizedString(getUID() + ".description");
    }

    @Override
    public EnumTemperature getTemperature() {
        return temperature;
    }

    @Override
    public EnumHumidity getHumidity() {
        return humidity;
    }

    @Override
    public boolean hasEffect() {
        return hasEffect;
    }

    public BeeSpecies setInactive() {
        isActive = false;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean isSecret() {
        return isSecret;
    }

    @Override
    public boolean isCounted() {
        return isCounted;
    }

    @Override
    public String getBinomial() {
        return binomial;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public IClassification getBranch() {
        return this.branch;
    }

    @Override
    public HashMap<ItemStack, Integer> getProducts() {
        return products;
    }

    @Override
    public HashMap<ItemStack, Integer> getSpecialty() {
        return specialties;
    }

    @Override
    public String getUID() {
        return uid;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Override
    public IBeeRoot getRoot() {
        return BeeManager.beeRoot;
    }

    @Override
    public boolean isNocturnal() {
        return isNocturnal;
    }

    @Override
    public boolean isJubilant(IBeeGenome genome, IBeeHousing housing) {
        return true;
    }

    private BeeSpecies register() {
        BeeManager.beeRoot.registerTemplate(getGenome());
        if (!isActive) {
            AlleleManager.alleleRegistry.blacklistAllele(getUID());
        }
        return this;
    }

    @Override
    public int getIconColour(int renderPass) {
        int value = 0xffffff;
        if (renderPass == 0) {
            value = this.primaryColour;
        } else if (renderPass == 1) {
            value = this.secondaryColour;
        }
        return value;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIconProvider getIconProvider() {
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(EnumBeeType type, int renderPass)  {
        return icons[type.ordinal()][Math.min(renderPass, 2)];
    }

    @Override
    public int getComplexity() {
        return 1 + getMutationPathLength(this, new ArrayList<IAllele>());
    }

    private int getMutationPathLength(IAllele species, ArrayList<IAllele> excludeSpecies) {
        int own = 1;
        int highest = 0;
        excludeSpecies.add(species);

        for (IMutation mutation : getRoot().getPaths(species, EnumBeeChromosome.SPECIES)) {
            if (!excludeSpecies.contains(mutation.getAllele0())) {
                int otherAdvance = getMutationPathLength(mutation.getAllele0(), excludeSpecies);
                if (otherAdvance > highest) {
	                highest = otherAdvance;
                }
            }

            if (!excludeSpecies.contains(mutation.getAllele1())) {
                int otherAdvance = getMutationPathLength(mutation.getAllele1(), excludeSpecies);
                if (otherAdvance > highest) {
	                highest = otherAdvance;
                }
            }
        }

        return own + (highest > 0 ? highest : 0);
    }

    @Override
    public float getResearchSuitability(ItemStack itemStack) {
        float value = 0f;
        if(itemStack != null) {
            for (ItemStack product : products.keySet()) {
                if (itemStack.isItemEqual(product))  {
                    value = 1f;
                    break;
                }
            }
			if (value <= 0f) {
                for (ItemStack specialty : specialties.keySet()) {
                    if (specialty.isItemEqual(itemStack)) {
                        value = 1f;
                        break;
                    }
                }
                if (value <= 0f) {
                    //if (itemStack == BeeConfig.fHoneyDrop) {
                    //} else if (itemStack == BeeConfig.fHoneydew) {
                    //} else if (itemStack == BeeConfig.fBeeComb || itemStack == Config.combs) {
                    //    value = 4f;
                    //} else  {
                        for (Map.Entry<ItemStack, Float> catalyst : BeeManager.beeRoot.getResearchCatalysts().entrySet()) {
                            if (OreDictionary.itemMatches(itemStack, catalyst.getKey(), false)) {
                                value = catalyst.getValue();
                                break;
                            }
                        }
                    //}
                }
            }
        }
        return value;
    }

    @Override
    public ItemStack[] getResearchBounty(World world, GameProfile researcher, IIndividual individual, int bountyLevel) {
        System.out.println("Bounty level: " + bountyLevel);
        ArrayList<ItemStack> bounty = new ArrayList<ItemStack>();

        if (world.rand.nextFloat() < (10f / bountyLevel)) {
            Collection<? extends IMutation> resultantMutations = getRoot().getCombinations(this);
            if (resultantMutations.size() > 0) {
                IMutation[] candidates = resultantMutations.toArray(new IMutation[resultantMutations.size()]);
                bounty.add(AlleleManager.alleleRegistry.getMutationNoteStack(researcher, candidates[world.rand.nextInt(candidates.length)]));
            }
        }

        for (ItemStack product : this.products.keySet()) {
            ItemStack copy = product.copy();
            copy.stackSize = 1 + world.rand.nextInt(bountyLevel / 2);
            bounty.add(copy);
        }

        for (ItemStack specialty : this.specialties.keySet()) {
            ItemStack copy = specialty.copy();
            copy.stackSize = world.rand.nextInt(bountyLevel / 3);
            if (copy.stackSize > 0) {
                bounty.add(copy);
            }
        }

        return bounty.toArray(new ItemStack[bounty.size()]);
    }

    @Override
    public String getEntityTexture() {
        return "/gfx/forestry/entities/bees/honeyBee.png";
    }

    @Override
    public void registerIcons(IIconRegister itemMap) {
        this.icons = new IIcon[EnumBeeType.values().length][3];

        String root = getIconPath();

        IIcon body1 = itemMap.registerIcon(root + "body1");

        for (int i = 0; i < EnumBeeType.values().length; i++) {
            if(EnumBeeType.values()[i] == EnumBeeType.NONE) {
	            continue;
            }

            icons[i][0] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".outline");
            icons[i][1] = (EnumBeeType.values()[i] != EnumBeeType.LARVAE) ? body1 : itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body");
            icons[i][2] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body2");
        }
    }

    //private String getIconPath(){
    //    ForestryHelper.Name.toLowerCase() + ":bees/default/";
    //}

    /// --------- Unused Functions ---------------------------------------------

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(short texUID) {
        return icons[0][0];
    }
}


