package mortvana.legacy.common;

import java.util.*;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mortvana.melteddashboard.util.enums.EnumArmorType;
import mortvana.legacy.common.config.FluxGearConfig;
import mortvana.melteddashboard.util.FluxGearDamageSources;
import mortvana.legacy.util.item.ItemArmorFluxGear;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.research.*;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ThaumicContent {

	public static class ItemLoveRing extends Item implements IBauble {

		public ItemLoveRing() {

			super();
			setUnlocalizedName("itemLoveRing");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("trevelations:lovering");

		}

		@Override
		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

			world.playSoundAtEntity(player, "trevelations:abderp", 1, 1);

			return super.onItemRightClick(stack, world, player);

		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack) {return BaubleType.RING;}

		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

		@Override
		public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return false;}

	}

	public static class ItemWardenicBlade extends Item {

		public ItemWardenicBlade() {

			super();
			setUnlocalizedName("itemWardenicBlade");
			setCreativeTab(ProjectFluxGear.thaumicTab);
			setMaxStackSize(1);

			setFull3D();

		}

		@Override
		public boolean getShareTag() {return true;}

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}

		@Override
		public int getMaxDamage(ItemStack stack) {return 50;}

		@Override
		public boolean isDamageable() {return false;}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

		@Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack) {return EnumAction.block;}

		@Override
		public int getMaxItemUseDuration(ItemStack par1ItemStack) {return 72000;}

		@Override
		public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

			par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
			par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());

			super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		}

		@Override
		public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

			if (stack.getItemDamage() != stack.getMaxDamage()) {

				DamageSource damageSource = new FluxGearDamageSources("warden", player);

				entity.attackEntityFrom(damageSource, 5);

				WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);

				stack.setItemDamage(stack.getItemDamage() + 1);

			}

			return super.onLeftClickEntity(stack, player, entity);

		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("trevelations:wardensword");

		}

	}

	public static class ItemWardenArmor extends ItemArmorFluxGear implements ISpecialArmor, IVisDiscountGear {

		public ItemWardenArmor(EnumArmorType type, String name, String sheet, String icon) {
			super(materialWarden, 3, type, name, sheet, icon);
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
		}

		@Override
		public boolean getShareTag() {
			return true;
		}

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		}

		@Override
		public int getMaxDamage(ItemStack stack) {
			return 50;
		}

		@Override
		public boolean isDamageable() {
			return false;
		}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {
			return EnumRarity.epic;
		}

		@Override
		public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
			par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
			par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());
			super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		}

		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
			WardenicChargeHelper.getUpgrade(itemStack).onTick(world, player, itemStack);
			super.onArmorTick(world, player, itemStack);
		}

		@Override
		public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
			if (armor.getItemDamage() != armor.getMaxDamage()) {
				return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
			} else {
				return new ArmorProperties(0, 0, 0);
			}
		}

		@Override
		public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
			return getArmorMaterial().getDamageReductionAmount(slot);
		}

		@Override
		public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

		}

		@Override
		public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
			return 5;
		}

	}

	public static class ItemWaslieHammer extends Item {

		public ItemWaslieHammer() {

			super();
			setUnlocalizedName("itemWaslieHammer");
			setCreativeTab(ProjectFluxGear.thaumicTab);
			setMaxStackSize(1);
			canRepair = false;

		}

		@Override
		public EnumRarity getRarity(ItemStack stack) {

			return EnumRarity.rare;

		}

		@Override
		public boolean isItemTool(ItemStack stack) {

			return true;

		}

		@Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

			par3EntityPlayer.openGui(ProjectFluxGear.instance, 0, par2World, 0, 0, 0);

			return par1ItemStack;

		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("projectfluxgear:tool/wasliehammer");

		}

	}

	public static class ItemFocusIllumination extends ItemFocusBasic {

		private IIcon depth, orn;

		public ItemFocusIllumination() {

			super();
			setUnlocalizedName("itemFocusIllumination");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public void registerIcons(IIconRegister register) {

			icon = register.registerIcon("trevelations:purityfocus");
			depth = register.registerIcon("trevelations:puritydepth");
			orn = register.registerIcon("trevelations:purityorn");

		}

		@Override
		public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {return depth;}

		@Override
		public IIcon getOrnament(ItemStack itemstack) {return orn;}

		@Override
		public int getFocusColor(ItemStack itemstack) {return 0x6698FF;}


		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
			ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
			if (mop != null) {
				if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					if (!world.isRemote) {
						if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {

							int x = mop.blockX;
							int y = mop.blockY;
							int z = mop.blockZ;

							if (mop.sideHit == 0) {
								y--;
							}
							if (mop.sideHit == 1) {
								y++;
							}
							if (mop.sideHit == 2) {
								z--;
							}
							if (mop.sideHit == 3) {
								z++;
							}
							if (mop.sideHit == 4) {
								x--;
							}
							if (mop.sideHit == 5) {
								x++;
							}
							world.setBlock(x, y, z, ThaumicContent.blockWitor, 0, 2);
						}
					}
				}
			}
			player.swingItem();
			return itemstack;
		}

		@Override
		public String getSortingHelper(ItemStack itemStack) {return "ILLUMINATION";}

		@Override
		public AspectList getVisCost(ItemStack itemstack) {

			return new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50);

		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
			return new FocusUpgradeType[0];
		}


	}

	public static class ItemFocusPurity extends ItemFocusBasic {

		private IIcon depth, orn;

		public ItemFocusPurity() {

			super();
			setUnlocalizedName("itemFocusPurity");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public void registerIcons(IIconRegister register) {

			icon = register.registerIcon("trevelations:purityfocus");
			depth = register.registerIcon("trevelations:puritydepth");
			orn = register.registerIcon("trevelations:purityorn");

		}

		@Override
		public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {return depth;}

		@Override
		public IIcon getOrnament(ItemStack itemstack) {return orn;}

		@Override
		public int getFocusColor(ItemStack itemstack) {return 0x6698FF;}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {

			ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
			EntityPurity purityOrb = new EntityPurity(world, player);
			if (!world.isRemote) {
				if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {
					world.spawnEntityInWorld(purityOrb);
					world.playSoundAtEntity(purityOrb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
				}
			}
			player.swingItem();
			return itemstack;
		}

		@Override
		public String getSortingHelper(ItemStack itemStack) { return "PURITY"; }

		@Override
		public AspectList getVisCost(ItemStack itemstack) {
			return new AspectList().add(Aspect.AIR, 500).add(Aspect.EARTH, 500).add(Aspect.FIRE, 500).add(Aspect.WATER, 500).add(Aspect.ORDER, 500).add(Aspect.ENTROPY, 500);
		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int i) {
			return new FocusUpgradeType[0];
		}

	}

	public static class EntityPurity extends EntityThrowable {

		public EntityPurity(World par1World) {

			super(par1World);

		}

		public EntityPurity(World par1World, EntityLivingBase par2EntityLivingBase) {

			super(par1World, par2EntityLivingBase);

		}

		public EntityPurity(World par1World, double par2, double par4, double par6) {

			super(par1World, par2, par4, par6);

		}

		@Override
		protected float getGravityVelocity() {

			return 0.001F;

		}

		@Override
		public void onUpdate() {

			if (this.worldObj.isRemote) {

				for (int i = 0; i < 3; i++) {

					Thaumcraft.proxy.wispFX2(this.worldObj, this.posX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posY + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.3F, 2, true, false, 0.02F);

					double x2 = (this.posX + this.prevPosX) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					double y2 = (this.posY + this.prevPosY) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					double z2 = (this.posZ + this.prevPosZ) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;

					Thaumcraft.proxy.wispFX2(this.worldObj, x2, y2, z2, 0.3F, 2, true, false, 0.02F);

				}

			}

			super.onUpdate();

		}

		@Override
		protected void onImpact(MovingObjectPosition mop) {

			for (int i = 0; i < 9; i++) {

				float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 2, true, 0.02F);

				fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 0, true, 0.02F);

				fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 2, true, 0.02F);

			}

			if (!worldObj.isRemote) {

				PurityHelper.checkAndPurify(mop);
				setDead();

			}

		}

	}

	public static class ExubituraGenerator implements IWorldGenerator {

		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

			int X = chunkX * 16 + random.nextInt(128);
			int Z = chunkZ * 16 + random.nextInt(128);
			int Y = world.getHeightValue(X, Z);

			if (world.isAirBlock(X, Y, Z) && FluxGearContent.blockPlant.canBlockStay(world, X, Y, Z) && random.nextInt(1000) <= 10) {
				world.setBlock(X, Y, Z, FluxGearContent.blockPlant, 0, 2);
			}
		}
	}

	public static class ContainerHammer extends Container {

		public InventoryPlayer playerInv;
		public InventoryCrafting hammerInv;
		public IInventory resultInv;

		public ContainerHammer(EntityPlayer player) {

			playerInv = player.inventory;
			hammerInv = new InventoryCrafting(this, 2, 1);
			resultInv = new InventoryCraftResult();

			for (int hotbar = 0; hotbar < 9; hotbar++) {
				addSlotToContainer(new Slot(playerInv, hotbar, 8 + 18 * hotbar, 142));
			}
			for (int row = 0; row < 3; row++) {
				for (int collumn = 0; collumn < 9; collumn++) {
					addSlotToContainer(new Slot(playerInv, 9 + row * 9 + collumn, 8 + 18 * collumn, 84 + row * 18));
				}
			}

			addSlotToContainer(new SlotEssentia(hammerInv, 0, 80, 54));
			addSlotToContainer(new Slot(hammerInv, 1, 80, 33));
			addSlotToContainer(new SlotCrafting(player, hammerInv, resultInv, 0, 80, 12));

			onCraftMatrixChanged(hammerInv);

		}

		@Override
		public void onCraftMatrixChanged(IInventory craftingMatrix) {

			ItemStack essentia = craftingMatrix.getStackInSlot(0);
			ItemStack item = craftingMatrix.getStackInSlot(1);

			if (item != null) {
				if (!(item.getItem() instanceof ItemWardenArmor || item.getItem() instanceof ItemWardenicBlade)) {
					ItemStack repairedItem = new ItemStack(item.getItem());
					if (item.getItemDamage() != 0 && item.getItem().isRepairable()) {
						repairedItem.setItemDamage(0);
						resultInv.setInventorySlotContents(0, repairedItem);
					}
				} else if (essentia != null) {
					ItemStack infusedArmor = new ItemStack(item.getItem());
					String aspectKey = ((IEssentiaContainerItem) essentia.getItem()).getAspects(essentia).getAspects()[0].getName();
					if (WardenicChargeHelper.upgrades.containsKey(aspectKey)) {
						WardenicChargeHelper.setUpgradeOnStack(infusedArmor, aspectKey);
					}
					resultInv.setInventorySlotContents(0, infusedArmor);
				} else {
					resultInv.setInventorySlotContents(0, null);
				}
			} else {
				resultInv.setInventorySlotContents(0, null);
			}
		}

		@Override
		public void onContainerClosed(EntityPlayer player) {

			super.onContainerClosed(player);

			ItemStack essentia = this.hammerInv.getStackInSlotOnClosing(0);
			if (essentia != null) {
				player.dropPlayerItemWithRandomChoice(essentia, false);
			}

			ItemStack item = this.hammerInv.getStackInSlotOnClosing(1);
			if (item != null) {
				player.dropPlayerItemWithRandomChoice(item, false);
			}

		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {return true;}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int slot) {return null;}

		@Override
		public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {

			if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
				return null;
			}
			return super.slotClick(slot, button, flag, player);

		}

	}

	public static class PurityHelper {

		public static boolean isTainted(Entity entity) {
			if (entity instanceof ITaintedMob) {
				return true;
			}
			return false;
		}

		public static boolean isTainted(MovingObjectPosition mop) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				if (mop.entityHit != null) {
					return isTainted(mop.entityHit);
				}
			}
			return false;
		}

		public static void purifyEntity(Entity toPurify) {
			if (toPurify != null) {
				World world = toPurify.worldObj;
				if (isTainted(toPurify)) {
					if (!world.isRemote) {
						Entity purified = getPureState(toPurify);
						purified.setPositionAndRotation(toPurify.posX, toPurify.posY, toPurify.posZ, toPurify.rotationYaw, toPurify.rotationPitch);

						toPurify.setDead();
						world.spawnEntityInWorld(purified);
					}
				}
			}
		}

		public static void checkAndPurify(MovingObjectPosition mop) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				purifyEntity(mop.entityHit);
			}
		}

		public static Entity getPureState(Entity entity) {
			if (entity instanceof EntityTaintChicken) {
				return new EntityChicken(entity.worldObj);
			}
			if (entity instanceof EntityTaintCow) {
				return new EntityCow(entity.worldObj);
			}
			if (entity instanceof EntityTaintCreeper) {
				return new EntityCreeper(entity.worldObj);
			}
			if (entity instanceof EntityTaintPig) {
				return new EntityPig(entity.worldObj);
			}
			if (entity instanceof EntityTaintSheep) {
				return new EntitySheep(entity.worldObj);
			}
			if (entity instanceof EntityTaintSpider) {
				return new EntitySpider(entity.worldObj);
			}
			if (entity instanceof EntityTaintVillager) {
				return new EntityVillager(entity.worldObj);
			}
			return entity;
		}
	}

	public static class WardenicChargeHelper {

		public static HashMap<String, WardenicUpgrade> upgrades = new HashMap<String, WardenicUpgrade>();

		public static void addUpgrade(WardenicUpgrade upgrade) {
			addUpgrade(upgrade.aspect.getName(), upgrade);
		}

		public static void addUpgrade(String key, WardenicUpgrade upgrade) {
			upgrades.put(key, upgrade);
		}

		public static WardenicUpgrade getUpgrade(ItemStack stack) {
			if (stack.stackTagCompound != null) {
				if (stack.stackTagCompound.hasKey("upgrade")) {
					return upgrades.get(stack.stackTagCompound.getString("upgrade"));
				} else {
					return upgrades.get(WARDEN.getName());
				}
			} else {
				return upgrades.get(WARDEN.getName());
			}
		}

		public static void setUpgradeOnStack(ItemStack stack, String key) {
			if (stack.stackTagCompound == null) {
				stack.setTagCompound(new NBTTagCompound());
			}
			stack.stackTagCompound.setString("upgrade", key);
		}

	}

	public static class WardenicChargeEvents {

		private Random random = new Random();

		public static void init() {
			MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
		}

		@SubscribeEvent
		public void onTick(LivingEvent.LivingUpdateEvent event) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				for (int i = 0; i < 5; i++) {
					if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor || player.getEquipmentInSlot(i).getItem() instanceof ItemWardenicBlade) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) && (random.nextInt(50) == 49)) {
						player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);
					}
				}
			}
		}

		@SubscribeEvent
		public void onHurt(LivingHurtEvent event) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				for (int i = 1; i < 5; i++) {
					if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage())) {
						player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
						WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
					}
				}
			}
		}
	}

	public static class FluxGearResearchItem extends ResearchItem {

		public int warp = 0;

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public void setWarp(int warp) {
			this.warp = warp;
		}


		@Override
		@SideOnly(Side.CLIENT)
		public String getName() {
			return StatCollector.translateToLocal("fluxgearresearch." + key + ".name");
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getText() {
			return (FluxGearConfig.useThaumicTooltips ? StatCollector.translateToLocal(getPrefix()) : "") + StatCollector.translateToLocal("fluxgearresearch." + key + ".lore");
		}

		String getPrefix() {
			return "[PFG] ";
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(true, pages);
			return super.setPages(pages);
		}

		public ResearchItem setPages(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(checkInfusion, pages);
			return super.setPages(pages);
		}

		public void checkInfusion(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (checkInfusion && page.type == ResearchPage.PageType.INFUSION_CRAFTING) {
					if (parentsHidden == null || parentsHidden.length == 0)
						parentsHidden = new String[] { "INFUSION" };
					else {
						String[] newParents = new String[parentsHidden.length + 1];
						newParents[0] = "INFUSION";
						for (int i = 0; i < parentsHidden.length; i++) {
							newParents[i + 1] = parentsHidden[i];
						}
						parentsHidden = newParents;
					}
				}
			}
		}

		public void registerResearch() {
			registerResearchItem();
			if (warp != 0) {
				ThaumcraftApi.addWarpToResearch(key, warp);
			}
		}
	}

	public static class VortexFluxGearResearchItem extends FluxGearResearchItem {


		public static List<String> Blacklist = new ArrayList<String>();

		static {
			Blacklist.add("MINILITH");
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			List<String> requirements = parentsHidden == null || parentsHidden.length == 0 ? new ArrayList() : new ArrayList(Arrays.asList(parentsHidden));
			if (!isAutoUnlock())
				for (String categoryStr : ResearchCategories.researchCategories.keySet()) {
					ResearchCategoryList category = ResearchCategories.researchCategories.get(categoryStr);
					for (String tag : category.research.keySet()) {
						ResearchItem research = category.research.get(tag);
						if (research.isLost() || (research.parentsHidden == null && research.parents == null) || research.isVirtual() || research instanceof VortexFluxGearResearchItem || requirements.contains(tag))
							continue;
						if (research.getAspectTriggers() != null || research.getEntityTriggers() != null || research.getItemTriggers() != null) {
							continue;
						}
						if (research.category.equals("FLUXGEAR") || research.category.equals("TT_CATEGORY") || research.category.equals("TX") || /*research.category.equals("rotarycraft") || research.category.equals("chromaticraft") ||*/ research.category.equals("FORBIDDEN") || /*research.category.equals("automagy") ||*/ research.category.equals("MAGICBEES") || research.category.equals("RAILCRAFT") || /*research.category.equals("op style wands") ||*/ research.category.equals("AOBD") || research.category.equals("trevelations") || /*research.category.equals("thaumic horizons") ||*/ research.category.equals("BASICS") || research.category.equals("GOLEMANCY") || research.category.equals("ARTIFICE") || research.category.equals("ALCHEMY") || research.category.equals("THAUMATURGY")) {
							boolean found = false;
							for (String black : Blacklist)
								if (tag.startsWith(black)) {
									found = true;
								}
							if (tag.endsWith("VORTEX"))
								found = true;
							if (found)
								continue;
							requirements.add(tag);
						}
					}
				}
			parentsHidden = requirements.toArray(new String[requirements.size()]);
			return super.setPages(false, pages);
		}

		@Override
		String getPrefix() {
			return super.getPrefix() + ".vortex";
		}
	}
}
