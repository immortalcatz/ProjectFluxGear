package mortvana.legacy.clean.apiology.util;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;

import java.util.ArrayList;

public class BranchBees implements IClassification {
    private ArrayList<IClassification> groups = new ArrayList<IClassification>();
    private ArrayList<IAlleleSpecies> members = new ArrayList<IAlleleSpecies>();
    private IClassification parent;

    public BranchBees() {
        AlleleManager.alleleRegistry.registerClassification(this);
    }

    @Override
    public EnumClassLevel getLevel() {
        return IClassification.EnumClassLevel.GENUS;
    }

    @Override
    public String getUID() {
        return "bees.mortvana";
    }

    @Override
    public String getName() {
        return "Technostatic";
    }

    @Override
    public String getScientific() {
        return "Technostatica";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public IClassification[] getMemberGroups() {
        return groups.toArray(new IClassification[10]);
    }

    @Override
    public void addMemberGroup(IClassification group) {
        groups.add(group);
        group.setParent(this);
    }

    @Override
    public IAlleleSpecies[] getMemberSpecies() {
        return members.toArray(new IAlleleSpecies[10]);
    }

    @Override
    public void addMemberSpecies(IAlleleSpecies species) {
        members.add(species);
    }

    @Override
    public IClassification getParent() {
        return parent;
    }

    @Override
    public void setParent(IClassification parent) {
        parent = parent;
    }
}