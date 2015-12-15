package mortvana.legacy.errored.core;

import com.google.common.collect.Lists;
import mortvana.legacy.clean.thaumicrevelations.entity.EntityPurity;

import mortvana.projectfluxgear.thaumic.client.render.RenderPurity;
import mortvana.legacy.clean.morttech.block.tile.TileCrank;
import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import mortvana.legacy.clean.thaumicrevelations.client.particle.TRParticle;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.client.render.DualPassCubeRenderer;
import mortvana.legacy.clean.morttech.client.render.RenderCrank;
import mortvana.legacy.clean.thaumicrevelations.client.render.RenderFleshGolem;
import mortvana.legacy.clean.core.common.CommonProxy;
import mortvana.legacy.dependent.firstdegree.thaumicrevelations.entity.EntityFleshProjectile;
import mortvana.legacy.dependent.seconddegree.thaumicrevelations.entity.EntityFleshGolem;
import mortvana.legacy.clean.morttech.client.gui.GuiWoodmill;
import mortvana.legacy.errored.morttech.MTControls;
import mortvana.legacy.errored.morttech.ClientTickHandler;
import mortvana.legacy.clean.morttech.util.handlers.MTKeyHandler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;

import mantle.lib.client.MantleClientRegistry;
import tconstruct.world.TinkerWorld;

//The Client Proxy does all the things that should only be done client-side,
//like registering client-side handlers and renderers.
public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int dualPassCubeID;

	public static final TRParticle PARTICLE_HANDLER = new TRParticle();

	@Override
	public void registerRenderers() {
		dualPassCubeID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new DualPassCubeRenderer());

		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
		RenderingRegistry.registerEntityRenderingHandler(EntityFleshGolem.class, new RenderFleshGolem());
		RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));

		// This is for rendering entities and so forth later on
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrank.class, new RenderCrank());

	}

	@Override
	public void registerSound() {
		System.out.println("THERMAL TINKERER - WEIRD SCIENCE LEGACY DEBUG: REGISTER SOUND CALLED");
		MinecraftForge.EVENT_BUS.register(new EventSounds(ProjectFluxGear.sounds));
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
    public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == woodmillGui) {
			new GuiWoodmill(player.inventory, (WoodmillLogic) world.getTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public void registerTickHandler () {
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
	}

	public static Document potato;

	@Override
	public void readManuals () {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		potato = readManual("/assets/morttech/manuals/potato.xml", dbFactory);
		initManualIcons();
		initManualRecipes();
		initManualPages();
	}

	Document readManual (String location, DocumentBuilderFactory dbFactory) {
		try {
			InputStream stream = ProjectFluxGear.class.getResourceAsStream(location);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void initManualIcons () {
		MantleClientRegistry.registerManualIcon("item", new ItemStack(Location.nameOfItem, 1, 2));
	}

	public void initManualRecipes () {

	}

	void initManualPages () {

	}

	public static Document getManualFromStack (ItemStack stack) {
		switch (stack.getMetadata()) {
			case 0:
				return potato;
		}

		return null;
	}

	public static MTControls controlInstance;

	@Override
	public void registerKeys () {
		controlInstance = new MTControls();
		uploadKeyBindingsToGame(Minecraft.getMinecraft().gameSettings, controlInstance);

	}

	public void uploadKeyBindingsToGame (GameSettings settings, MTKeyHandler keyhandler) {
		ArrayList<KeyBinding> harvestedBindings = Lists.newArrayList();
		for (KeyBinding kb : keyhandler.keyBindings) {
			harvestedBindings.add(kb);
		}

		KeyBinding[] modKeyBindings = harvestedBindings.toArray(new KeyBinding[harvestedBindings.size()]);
		KeyBinding[] allKeys = new KeyBinding[settings.keyBindings.length + modKeyBindings.length];
		System.arraycopy(settings.keyBindings, 0, allKeys, 0, settings.keyBindings.length);
		System.arraycopy(modKeyBindings, 0, allKeys, settings.keyBindings.length, modKeyBindings.length);
		settings.keyBindings = allKeys;
		settings.loadOptions();
	}

	@Override
	public void spawnParticle (String particle, double xPos, double yPos, double zPos, double velX, double velY, double velZ) {
		doSpawnParticle(particle, xPos, yPos, zPos, velX, velY, velZ);
	}

	public Minecraft mc = Minecraft.getMinecraft();

	/**We Don't Need No Stinking Particles!!!!*/
    public EntityFX doSpawnParticle (String par1Str, double x, double y, double z, double motionX, double motionY, double motionZ) {
        if (mc == null) {
            mc = Minecraft.getMinecraft();
        }

        if (mc.renderViewEntity != null && mc.effectRenderer != null) {
            int i = mc.gameSettings.particleSetting;

            if (i == 1 && mc.theWorld.rand.nextInt(3) == 0) {
                i = 2;
            }

            double d6 = mc.renderViewEntity.posX - x;
            double d7 = mc.renderViewEntity.posY - y;
            double d8 = mc.renderViewEntity.posZ - z;
            EntityFX entityfx = null;

            if (par1Str.equals("hugeexplosion")) {
                mc.effectRenderer.addEffect(entityfx = new EntityHugeExplodeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ));
            } else if (par1Str.equals("largeexplode")) {
                mc.effectRenderer.addEffect(entityfx = new EntityLargeExplodeFX(mc.renderEngine, mc.theWorld, x, y, z, motionX, motionY, motionZ));
            } else if (par1Str.equals("fireworksSpark")) {
                mc.effectRenderer.addEffect(entityfx = new EntityFireworkSparkFX(mc.theWorld, x, y, z, motionX, motionY, motionZ, mc.effectRenderer));
            }

            if (entityfx != null) {
                return entityfx;
            } else {

                if (d6 * d6 + d7 * d7 + d8 * d8 > 256) {
                    return null;
                } else if (i > 1)  {
                    return null;
                } else {
                    if (par1Str.equals("bubble")) {
                        entityfx = new EntityBubbleFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("suspended")) {
                        entityfx = new EntitySuspendFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("depthsuspend")) {
                        entityfx = new EntityAuraFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("townaura")) {
                        entityfx = new EntityAuraFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("crit")) {
                        entityfx = new EntityCritFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("smoke")) {
                        entityfx = new EntitySmokeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("mobSpell")) {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
                        entityfx.setRBGColorF((float) motionX, (float) motionY, (float) motionZ);
                    } else if (par1Str.equals("mobSpellAmbient")) {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, x, y, z, 0.0D, 0.0D, 0.0D);
                        entityfx.setAlphaF(0.15F);
                        entityfx.setRBGColorF((float) motionX, (float) motionY, (float) motionZ);
                    } else if (par1Str.equals("spell"))  {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("instantSpell")) {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                        ((EntitySpellParticleFX) entityfx).setBaseSpellTextureIndex(144);
                    } else if (par1Str.equals("witchMagic")) {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                        ((EntitySpellParticleFX) entityfx).setBaseSpellTextureIndex(144);
                        float f = mc.theWorld.rand.nextFloat() * 0.5F + 0.35F;
                        entityfx.setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
                    } else if (par1Str.equals("note")) {
                        entityfx = new EntityNoteFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("portal")) {
                        entityfx = new EntityPortalFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("enchantmenttable")) {
                        entityfx = new EntityEnchantmentTableParticleFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("explode")) {
                        entityfx = new EntityExplodeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("flame")) {
                        entityfx = new EntityFlameFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("lava")) {
                        entityfx = new EntityLavaFX(mc.theWorld, x, y, z);
                    } else if (par1Str.equals("footstep")) {
                        entityfx = new EntityFootStepFX(mc.renderEngine, mc.theWorld, x, y, z);
                    } else if (par1Str.equals("splash")) {
                        entityfx = new EntitySplashFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("largesmoke")) {
                        entityfx = new EntitySmokeFX(mc.theWorld, x, y, z, motionX, motionY, motionZ, 2.5F);
                    } else if (par1Str.equals("cloud")) {
                        entityfx = new EntityCloudFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("reddust")) {
                        entityfx = new EntityReddustFX(mc.theWorld, x, y, z, (float) motionX, (float) motionY, (float) motionZ);
                    } else if (par1Str.equals("snowballpoof")) {
                        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, Items.snowball);
                    } else if (par1Str.equals("dripWater")) {
                        entityfx = new EntityDropParticleFX(mc.theWorld, x, y, z, Material.water);
                    } else if (par1Str.equals("dripLava")) {
                        entityfx = new EntityDropParticleFX(mc.theWorld, x, y, z, Material.lava);
                    } else if (par1Str.equals("snowshovel")) {
                        entityfx = new EntitySnowShovelFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("blueslime")) {
                        entityfx = new EntityBreakingFX(mc.theWorld, x, y, z, TinkerWorld.strangeFood);
                    } else if (par1Str.equals("heart")) {
                        entityfx = new EntityHeartFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                    } else if (par1Str.equals("angryVillager")) {
                        entityfx = new EntityHeartFX(mc.theWorld, x, y + 0.5D, z, motionX, motionY, motionZ);
                        entityfx.setParticleTextureIndex(81);
                        entityfx.setRBGColorF(1.0F, 1.0F, 1.0F);
                    } else if (par1Str.equals("happyVillager")) {
                        entityfx = new EntityAuraFX(mc.theWorld, x, y, z, motionX, motionY, motionZ);
                        entityfx.setParticleTextureIndex(82);
                        entityfx.setRBGColorF(1.0F, 1.0F, 1.0F);
                    }

                    if (entityfx != null) {
                        mc.effectRenderer.addEffect(entityfx);
                    }

                    return entityfx;
                }
            }
        } else {
            return null;
        }
    }


}
