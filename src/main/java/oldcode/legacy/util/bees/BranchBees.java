package oldcode.legacy.util.bees;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;

import java.util.ArrayList;

public class BranchBees implements IClassification {
    private ArrayList<IClassification> groups = new ArrayList();
    private ArrayList<IAlleleSpecies> members = new ArrayList();
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
        return (IClassification[]) this.groups.toArray(new IClassification[0]);
    }

    @Override
    public void addMemberGroup(IClassification group) {
        this.groups.add(group);
        group.setParent(this);
    }

    @Override
    public IAlleleSpecies[] getMemberSpecies() {
        return (IAlleleSpecies[]) this.members.toArray(new IAlleleSpecies[0]);
    }

    @Override
    public void addMemberSpecies(IAlleleSpecies species) {
        this.members.add(species);
    }

    @Override
    public IClassification getParent() {
        return this.parent;
    }

    @Override
    public void setParent(IClassification parent) {
        this.parent = parent;
    }
}