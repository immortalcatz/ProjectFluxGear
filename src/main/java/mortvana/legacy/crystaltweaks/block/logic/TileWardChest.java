package mortvana.legacy.crystaltweaks.block.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import thaumcraft.common.tiles.TileOwned;

public class TileWardChest extends TileOwned implements ISidedInventory {
	private static final String TAG_CUSTOM_NAME = "customName";
	private ItemStack[] inventorySlots = new ItemStack[54];
	public String customName;
	public double ticksExisted;

	public boolean canUpdate() {
		return true;
	}

	public void func_70316_g() {
		super.func_70316_g();
		this.ticksExisted += 1.0D;
	}

	public void func_70307_a(NBTTagCompound par1NBTTagCompound) {
		super.func_70307_a(par1NBTTagCompound);
		this.customName = par1NBTTagCompound.func_74779_i("customName");
		NBTTagList var2 = par1NBTTagCompound.func_74761_m("Items");
		this.inventorySlots = new ItemStack[func_70302_i_()];
		for (int var3 = 0; var3 < var2.func_74745_c(); var3++) {
			NBTTagCompound var4 = (NBTTagCompound) var2.func_74743_b(var3);
			byte var5 = var4.func_74771_c("Slot");
			if ((var5 >= 0) && (var5 < this.inventorySlots.length)) {
				this.inventorySlots[var5] = ItemStack.func_77949_a(var4);
			}
		}
	}

	public void func_70310_b(NBTTagCompound par1NBTTagCompound) {
		super.func_70310_b(par1NBTTagCompound);
		par1NBTTagCompound.func_74778_a("customName", this.customName == null ? "" : this.customName);
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.inventorySlots.length; var3++) {
			if (this.inventorySlots[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.func_74774_a("Slot", (byte) var3);
				this.inventorySlots[var3].func_77955_b(var4);
				var2.func_74742_a(var4);
			}
		}
		par1NBTTagCompound.func_74782_a("Items", var2);
	}

	public int func_70302_i_() {
		return this.inventorySlots.length;
	}

	public ItemStack func_70301_a(int i) {
		if (i >= this.inventorySlots.length) {
			return null;
		}
		return this.inventorySlots[i];
	}

	public ItemStack func_70298_a(int par1, int par2) {
		if (this.inventorySlots[par1] != null) {
			if (this.inventorySlots[par1].field_77994_a <= par2) {
				ItemStack stackAt = this.inventorySlots[par1];
				this.inventorySlots[par1] = null;
				return stackAt;
			}
			ItemStack stackAt = this.inventorySlots[par1].func_77979_a(par2);
			if (this.inventorySlots[par1].field_77994_a == 0) {
				this.inventorySlots[par1] = null;
			}
			return stackAt;
		}
		return null;
	}

	public ItemStack func_70304_b(int i) {
		return func_70301_a(i);
	}

	public void func_70299_a(int i, ItemStack itemstack) {
		this.inventorySlots[i] = itemstack;
	}

	public String func_70303_b() {
		return ModBlocks.wardChest.func_71917_a() + ".name";
	}

	public boolean func_94042_c() {
		return (this.customName != null) && (this.customName.length() > 0);
	}

	public int func_70297_j_() {
		return 64;
	}

	public boolean func_70300_a(EntityPlayer entityplayer) {
		return this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m, this.field_70327_n) == this;
	}

	public void func_70295_k_() {}

	public void func_70305_f() {}

	public boolean func_70315_b(int par1, int par2) {
		return this.field_70328_o;
	}

	public boolean func_94041_b(int i, ItemStack itemstack) {
		return false;
	}

	public int[] func_94128_d(int var1) {
		return new int[0];
	}

	public boolean func_102007_a(int i, ItemStack itemstack, int j) {
		return false;
	}

	public boolean func_102008_b(int i, ItemStack itemstack, int j) {
		return false;
	}
}