package mortvana.legacy.util.bees;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;
import mortvana.legacy.util.LocalizationManager;

import java.util.ArrayList;

public enum BeeClassification implements IClassification {

    RADIOACTIVE("Radioactive", "Radiapis"),;

    private String uid;
    private String latin;
    private ArrayList<IAlleleSpecies> species;
    private IClassification parent;
    private EnumClassLevel level;

    private BeeClassification(String name, String scientific)
    {
        this.uid = "classification." + name.toLowerCase();
        this.latin = scientific;
        this.level = EnumClassLevel.GENUS;
        this.species = new ArrayList();
        this.parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
        AlleleManager.alleleRegistry.registerClassification(this);
    }

    @Override
    public EnumClassLevel getLevel()
    {
        return this.level;
    }

    @Override
    public String getUID()
    {
        return this.uid;
    }

    @Override
    public String getName()
    {
        return LocalizationManager.getLocalizedString(getUID());
    }

    @Override
    public String getScientific()
    {
        return this.latin;
    }

    @Override
    public String getDescription()
    {
        return LocalizationManager.getLocalizedString(getUID() + ".description");
    }

    @Override
    public IClassification[] getMemberGroups()
    {
        return null;
    }

    @Override
    public void addMemberGroup(IClassification group)
    {

    }

    @Override
    public IAlleleSpecies[] getMemberSpecies()
    {
        return this.species.toArray(new IAlleleSpecies[this.species.size()]);
    }

    @Override
    public void addMemberSpecies(IAlleleSpecies species)
    {
        if (!this.species.contains(species))
        {
            this.species.add(species);
        }
    }

    @Override
    public IClassification getParent()
    {
        return this.parent;
    }

    @Override
    public void setParent(IClassification parent)
    {
        this.parent = parent;
    }

}
