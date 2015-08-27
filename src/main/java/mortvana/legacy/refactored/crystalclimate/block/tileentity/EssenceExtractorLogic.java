package mortvana.legacy.refactored.crystalclimate.block.tileentity;

import java.text.DecimalFormat;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityEnchantmentTable;

import mortvana.legacy.crystaltweaks.CrystalClimate;
import mortvana.melteddashboard.util.helpers.ChatHelper;
import mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper;


public class EssenceExtractorLogic extends TileEntityEnchantmentTable {
	/** Used by the render to make the book 'bounce' */
	public int tickCount;

	/** Value used for determining how the page flip should look. */
	public float pageFlip;

	/** The last tick's pageFlip value. */
	public float pageFlipPrev;
	public float field_70373_d;
	public float field_70374_e;

	/** The amount that the book is open. */
	public float bookSpread;

	/** The amount that the book is open. */
	public float bookSpreadPrev;
	public float bookRotation2;
	public float bookRotationPrev;
	public float bookRotation;
	private static Random rand = new Random();
	private String field_94136_s;

	public int essenceAmount;
	static DecimalFormat df = new DecimalFormat("##.##");

    /*public EssenceExtractorLogic()
    {
        df.setRoundingMode(RoundingMode.DOWN);
    }*/

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		if (func_94135_b()) {
			nbt.setString("CustomName", field_94136_s);
		}
		nbt.setInteger("Essence", essenceAmount);
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		if (nbt.hasKey("CustomName")) {
			field_94136_s = nbt.getString("CustomName");
		}

		essenceAmount = nbt.getInteger("Essence");
	}

	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
	 * ticks and creates a new spawn inside its implementation.
	 */
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) {
			particles();
		}
		bookRotation();
	}

	void particles() {
		for (int i = 0; i < 1; i++) {
			float xPos = rand.nextFloat() * 3 - 1, yPos = rand.nextFloat() * 2, zPos = rand.nextFloat() * 3 - 1;
			CrystalClimate.proxy.spawnParticle("essence", xCoord + xPos, yCoord + yPos, zCoord + zPos, (xPos - 0.5) * 0.05, 0.1, (zPos - 0.5) * 0.05);
		}
	}

	void bookRotation() {
		bookSpreadPrev = bookSpread;
		bookRotationPrev = bookRotation2;
		EntityPlayer entityplayer = worldObj.getClosestPlayer((double) ((float) xCoord + 0.5F), (double) ((float) yCoord + 0.5F), (double) ((float) zCoord + 0.5F), 3.0D);

		if (entityplayer != null) {
			double d0 = entityplayer.posX - (double) ((float) xCoord + 0.5F);
			double d1 = entityplayer.posZ - (double) ((float) zCoord + 0.5F);
			bookRotation = (float) Math.atan2(d1, d0);
			bookSpread += 0.1F;

			if (bookSpread < 0.5F || rand.nextInt(40) == 0) {
				float f = field_70373_d;

				/*do { //TODO Not sure if this actually does anything...
					this.field_70373_d += (float) (rand.nextInt(4) - rand.nextInt(4));
				} while (f == this.field_70373_d);*/
			}
		} else {
			bookRotation += 0.02F;
			bookSpread -= 0.1F;
		}

		while (bookRotation2 >= (float) Math.PI) {
			bookRotation2 -= ((float) Math.PI * 2F);
		}

		while (bookRotation2 < -(float) Math.PI) {
			bookRotation2 += ((float) Math.PI * 2F);
		}

		while (bookRotation >= (float) Math.PI) {
			bookRotation -= ((float) Math.PI * 2F);
		}

		while (bookRotation < -(float) Math.PI) {
			bookRotation += ((float) Math.PI * 2F);
		}

		float f1;

		for (f1 = bookRotation - bookRotation2; f1 >= (float) Math.PI; f1 -= ((float) Math.PI * 2F)) {
		}

		while (f1 < -(float) Math.PI) {
			f1 += ((float) Math.PI * 2F);
		}

		bookRotation2 += f1 * 0.4F;

		bookSpread = MathHelper.clampFloat(bookSpread, 0.0F, 1.0F);

		++tickCount;
		pageFlipPrev = this.pageFlip;
		float f2 = (field_70373_d - pageFlip) * 0.4F;
		float f3 = 0.2F;

		if (f2 < -f3) {
			f2 = -f3;
		}

		if (f2 > f3) {
			f2 = f3;
		}

		this.field_70374_e += (f2 - this.field_70374_e) * 0.9F;
		this.pageFlip += this.field_70374_e;
	}

	public String func_94133_a() {
		return func_94135_b() ? field_94136_s : "container.enchant";
	}

	public boolean func_94135_b() {
		return field_94136_s != null && field_94136_s.length() > 0;
	}

	public void func_94134_a(String par1Str) {
		field_94136_s = par1Str;
	}

	public void addEssence(EntityPlayer player) {
		int amount = player.experienceTotal;
		essenceAmount += amount;
		player.addExperienceLevel(-player.experienceLevel - 1);
	}

	public int removeEssence() {
		int amount = essenceAmount;
		essenceAmount = 0;
		return amount;
	}

	public void getEssenceMessage(EntityPlayer player) {
		ChatHelper.addChatMessage(player, "Total Essence: " + essenceAmount);
		ChatHelper.addChatMessage(player, "Stored Levels: " + getEssencelevels(essenceAmount));
	}

	public static String getEssencelevels(int essence) {
		if (essence < 255) { //0-14
			float ret = essence / 17f;
			return String.valueOf(df.format(ret));
		} else if (essence < 825) {
			int amount = essence - 255;
			int levels = 15;
			int offset = 3;

			int sub = 17 + offset;
			while (amount - sub >= 0) {
				amount -= sub;
				sub += offset;
				levels++;
			}

			float ret = levels + amount / (float) sub;
			return String.valueOf(df.format(ret));
		} else {
			int amount = essence - 825;
			int levels = 30;
			int offset = 7;

			int sub = 62 + offset;
			while (amount - sub >= 0) {
				amount -= sub;
				sub += offset;
				levels++;
			}

			float ret = levels + amount / (float) sub;
			return String.valueOf(df.format(ret));
		}
	}
}
