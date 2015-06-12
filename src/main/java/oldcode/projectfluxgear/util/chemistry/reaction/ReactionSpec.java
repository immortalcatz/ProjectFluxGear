package oldcode.projectfluxgear.util.chemistry.reaction;

import net.minecraftforge.fluids.Fluid;

//TODO: Refactorinizate
//Extremely simplified from the complexity of real chemistry.
public class ReactionSpec implements IReactionSpec {

    //The fluid which is the primary driver of this reaction.
    public Fluid solvent;
    //Valid types: Item, Block, Fluid
    public Object solute;

    //Valid types: Item, Block, Fluid. What the solvent becomes after reaction.
    public Object solventTarget = null;
    //Valid types: Item, Block, Fluid. What the solute becomes after reaction.
    public Object soluteTarget = null;

    //Turn solvent into solventTarget?
    public boolean solventAffected = true;
    //Turn solute into soluteTarget?
    public boolean soluteAffected = true;

    //Minimum solvent quantity for the reaction to take place.
    public int solventMin = 0;
    //Minimum solvent quantity for the reaction to take place.
    public int soluteMin = 0;

    public Integer heatUpper = null;
    public Integer heatLower = null;
    public int heatChange = 0;

    public ReactionSpec() {
    }

    public ReactionSpec(Fluid solvent, Object solute, Object solventTarget, Object soluteTarget) {
        this.solvent = solvent;
        this.solute = solute;
        solventAffected = false;
        soluteAffected = false;
        if (solventTarget != null) {
            this.solventTarget = solventTarget;
            solventAffected = true;
        }
        if (soluteTarget != null) {
            this.soluteTarget = soluteTarget;
            soluteAffected = true;
        }
    }

    public ReactionSpec(Fluid solvent, Object solute, Object solventTarget, Object soluteTarget, boolean solventAffected, boolean soluteAffected) {
        this.solvent = solvent;
        this.solute = solute;
        this.solventAffected = false;
        this.soluteAffected = false;
        if (solventTarget != null) {
            this.solventTarget = solventTarget;
            this.solventAffected = solventAffected;
        }
        if (soluteTarget != null) {
            this.soluteTarget = soluteTarget;
            this.soluteAffected = soluteAffected;
        }
    }

    public ReactionSpec(Fluid solvent, Object solute, Object solventTarget, Object soluteTarget, boolean solventAffected, boolean soluteAffected, int solventMin, int soluteMin) {
        this.solvent = solvent;
        this.solute = solute;
        this.solventAffected = false;
        this.soluteAffected = false;
        this.solventMin = solventMin;
        this.soluteMin = soluteMin;
        if (solventTarget != null) {
            this.solventTarget = solventTarget;
            this.solventAffected = solventAffected;
        }
        if (soluteTarget != null) {
            this.soluteTarget = soluteTarget;
            this.soluteAffected = soluteAffected;
        }
    }

    public Fluid getSolvent () {
        return solvent;
    }

    public Object getSolute () {
        return solute;
    }

    public Object getSolventTarget () {
        return solventTarget;
    }

    public Object getSoluteTarget () {
        return soluteTarget;
    }

    public boolean isSolventAffected () {
        return solventAffected;
    }

    public boolean isSoluteAffected () {
        return soluteAffected;
    }

    public int getSolventMin () {
        return solventMin;
    }

    public int getSoluteMin () {
        return soluteMin;
    }

    /**
     * @return null if there is no lower bound.
     */
    public Integer getHeatLowerBound () {
        return null;
    }

    /**
     * @return null if there is no upper bound.
     */
    public Integer getHeatUpperBound () {
        return null;
    }

    /**
     * @return Positive for an exothermic reaction, negative for an endothermic reaction.
     * This is a consumption value for endothermic reactions: If it can't be supplied
     * with the necessary heat, it can't occur.
     */
    public int getHeatChangeProduced () {
        return heatChange;
    }

}