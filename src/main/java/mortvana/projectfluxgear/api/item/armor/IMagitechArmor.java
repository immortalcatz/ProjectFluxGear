package mortvana.projectfluxgear.api.item.armor;

import net.minecraftforge.common.ISpecialArmor;

import mortvana.projectfluxgear.api.enchant.IStabilizing;

import WayofTime.alchemicalWizardry.api.items.interfaces.ILPGauge;
import forestry.api.apiculture.IArmorApiarist;
import thaumcraft.api.*;
import vazkii.botania.api.item.*;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaUsingItem;

public interface IMagitechArmor extends IRunicArmor, IVisDiscountGear, IRepairableExtended, IStabilizing, ILPGauge, ISpecialArmor, IArmorApiarist, IManaProficiencyArmor, IManaDiscountArmor, IPixieSpawner, IManaUsingItem, IPhantomInkable {}
