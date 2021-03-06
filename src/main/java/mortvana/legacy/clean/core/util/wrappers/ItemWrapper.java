package mortvana.legacy.clean.core.util.wrappers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import mortvana.legacy.clean.core.util.helpers.ItemHelper;

public final class ItemWrapper {
	public Item item;
	public int metadata;

	public static ItemWrapper fromItemStack(ItemStack itemstack) {
		return new ItemWrapper(itemstack);
	}

	public ItemWrapper(Item var1, int var2) {
		this.item = var1;
		this.metadata = var2;
	}

	public ItemWrapper(ItemStack var1) {
		this.item = var1.getItem();
		this.metadata = ItemHelper.getMetadata(var1);
	}

	public ItemWrapper set(ItemStack var1) {
		if(var1 != null) {
			this.item = var1.getItem();
			this.metadata = ItemHelper.getMetadata(var1);
		} else {
			this.item = null;
			this.metadata = 0;
		}

		return this;
	}

	private int getId() {
		return Item.getIdFromItem(this.item);
	}

	public boolean isEqual(ItemWrapper var1) {
		if(var1 == null) {
			return false;
		} else {
			if(this.metadata == var1.metadata) {
				if(this.item == var1.item) {
					return true;
				}

				if(this.item != null && var1.item != null) {
					return this.item.delegate.get() == var1.item.delegate.get();
				}
			}

			return false;
		}
	}

	public boolean equals(Object var1) {
		return var1 instanceof ItemWrapper && this.isEqual((ItemWrapper) var1);
	}

	public int hashCode() {
		return this.metadata | this.getId() << 16;
	}

	public String toString() {
		StringBuilder var1 = new StringBuilder(this.getClass().getName());
		var1.append('@').append(System.identityHashCode(this)).append('{');
		var1.append("m:").append(this.metadata).append(", i:").append(this.item == null?null:this.item.getClass().getName());
		var1.append('@').append(System.identityHashCode(this.item)).append(", v:");
		var1.append(this.getId()).append('}');
		return var1.toString();
	}
}
