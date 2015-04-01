package thermalexpansion.gui.container;

    import net.minecraft.inventory.Slot;

public abstract interface ISchematicContainer {

    public abstract void writeSchematic();

    public abstract boolean canWriteSchematic();

    public abstract Slot[] getCraftingSlots();

    public abstract Slot getResultSlot();
}
