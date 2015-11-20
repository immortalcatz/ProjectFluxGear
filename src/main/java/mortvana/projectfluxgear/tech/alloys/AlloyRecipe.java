package mortvana.projectfluxgear.tech.alloys;

public abstract class AlloyRecipe {
    public AlloyStackList inputs;
    public AlloyStackList outputs;

    public AlloyRecipe(IAlloyMaterial output, AlloyStack... materials) {

    }







    public AlloyStackList getRecipeOutput() {
        return outputs;
    }
}
