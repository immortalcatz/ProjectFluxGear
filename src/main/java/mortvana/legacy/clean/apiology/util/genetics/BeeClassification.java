package mortvana.legacy.clean.apiology.util.genetics;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;
import mortvana.legacy.clean.apiology.common.LocalizationManager;

import java.util.ArrayList;

public enum BeeClassification implements IClassification {

    RADIOACTIVE("Radioactive", "Radiapis"),;

    private String uid;
    private String latin;
    private ArrayList<IAlleleSpecies> species;
    private IClassification parent;
    private EnumClassLevel level;

    private BeeClassification(String name, String scientific) {
        uid = "classification." + name.toLowerCase();
        latin = scientific;
        level = EnumClassLevel.GENUS;
        species = new ArrayList<IAlleleSpecies>();
        parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
        AlleleManager.alleleRegistry.registerClassification(this);
    }

    @Override
    public EnumClassLevel getLevel() {
        return level;
    }

    @Override
    public String getUID() {
        return uid;
    }

    @Override
    public String getName() {
        return LocalizationManager.getLocalizedString(getUID());
    }

    @Override
    public String getScientific() {
        return latin;
    }

    @Override
    public String getDescription() {
        return LocalizationManager.getLocalizedString(getUID() + ".description");
    }

    @Override
    public IClassification[] getMemberGroups() {
        return null;
    }

    @Override
    public void addMemberGroup(IClassification group) {}

    @Override
    public IAlleleSpecies[] getMemberSpecies() {
        return species.toArray(new IAlleleSpecies[species.size()]);
    }

    @Override
    public void addMemberSpecies(IAlleleSpecies species) {
        if (!this.species.contains(species)) {
            this.species.add(species);
        }
    }

    @Override
    public IClassification getParent()  {
        return parent;
    }

    @Override
    public void setParent(IClassification parent) {
        this.parent = parent;
    }

}
