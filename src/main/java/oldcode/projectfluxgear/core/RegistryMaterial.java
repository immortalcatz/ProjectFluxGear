package oldcode.projectfluxgear.core;

import net.minecraft.item.Item;

public class RegistryMaterial {
	public RegistryMaterial (int id, MaterialTypeEnum type) {}

	public enum  MaterialTypeEnum {
		WORKABLE_METAL, //Steel, etc.
		INGOT_METAL, //Tellurium, etc.
		GEM, //Dioptase, etc.
		DUST, //Rust, etc.
	}

	public class MaterialFormArray {
		public MaterialFormArray (MaterialForm materialForm1, MaterialForm materialForm2, MaterialForm materialForm3, MaterialForm materialForm4, MaterialForm materialForm5, MaterialForm materialForm6, MaterialForm materialForm7, MaterialForm materialForm8) {}
	}

	public class MaterialForm {
		public MaterialForm(String key) {}
	}

	public class ItemRegistryBase extends Item {
		public ItemRegistryBase (MaterialFormArray types) {
		}
	}
}
