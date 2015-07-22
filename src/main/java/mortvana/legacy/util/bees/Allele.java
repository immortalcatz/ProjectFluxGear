package mortvana.legacy.util.bees;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleRegistry;
import mortvana.legacy.util.LocalizationManager;
import net.minecraft.potion.Potion;

public class Allele implements IAllele {

    public static IAlleleBeeEffect forestryBaseEffect;

    public static IAlleleEffect effectRadiation;

    public static void setupAdditionalAlleles(){
        forestryBaseEffect = (IAlleleBeeEffect)getBaseAllele("effectNone");

        //Wither == Radiation for now...
        Allele.effectRadiation = new AlleleEffectPotion("Withering", Potion.wither, 10, false).setMalicious();
    }

    //Just in case in the future I change something
    public static void registerDeprecatedAlleleReplacements() {
        IAlleleRegistry registry = AlleleManager.alleleRegistry;
    }

    public static IAlleleBeeSpecies getBaseSpecies(String name)
    {
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("forestry.species").append(name).toString());
    }

    public static IAlleleBeeSpecies getExtraSpecies(String name)
    {
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele((new StringBuilder()).append("extrabees.species.").append(name).toString());
    }

    public static IAllele getBaseAllele(String name)
    {
        return AlleleManager.alleleRegistry.getAllele("forestry." + name);
    }

    public static IAllele getAllele(String name)
    {
        return AlleleManager.alleleRegistry.getAllele(name);
    }

    private String uid;
    private Boolean dominant;

    public Allele(String id, boolean isDominant){
        this.uid = "mortvanabees" + id;
        this.dominant = isDominant;
        AlleleManager.alleleRegistry.registerAllele(this);
    }

    @Override
    public String getUID() {
        return this.uid;
    }

    @Override
    public boolean isDominant() {
        return this.dominant;
    }

    @Override
    public String getName() {
        return LocalizationManager.getLocalizedString(getUID());
    }

    @Override
    public String getUnlocalizedName() {
        return null;
    }
}
