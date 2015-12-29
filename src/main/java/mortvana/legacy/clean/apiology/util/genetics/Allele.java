package mortvana.legacy.clean.apiology.util.genetics;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleRegistry;
import mortvana.legacy.clean.apiology.common.LocalizationManager;
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

    public static IAlleleBeeSpecies getBaseSpecies(String name) {
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele("forestry.species" + name);
    }

    public static IAlleleBeeSpecies getExtraSpecies(String name) {
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele("extrabees.species." + name);
    }

    public static IAllele getBaseAllele(String name) {
        return AlleleManager.alleleRegistry.getAllele("forestry." + name);
    }

    public static IAllele getAllele(String name) {
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
        return uid;
    }

    @Override
    public boolean isDominant() {
        return dominant;
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
