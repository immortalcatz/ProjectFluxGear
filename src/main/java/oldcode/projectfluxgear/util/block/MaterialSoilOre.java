package oldcode.projectfluxgear.util.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialSoilOre extends Material {
	public MaterialSoilOre() {
		super(MapColor.sandColor);
		setRequiresTool();
	}
}