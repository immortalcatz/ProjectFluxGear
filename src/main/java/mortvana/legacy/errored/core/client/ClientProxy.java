package mortvana.legacy.errored.core.client;

import com.google.common.collect.Lists;
import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.legacy.errored.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.thaumic.client.render.RenderPurity;
import mortvana.legacy.clean.morttech.block.tile.TileCrank;
import mortvana.legacy.errored.morttech.block.tile.WoodmillLogic;
import mortvana.legacy.errored.thaumicrevelations.client.particle.TRParticle;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.client.render.DualPassCubeRenderer;
import mortvana.legacy.clean.morttech.client.render.RenderCrank;
import mortvana.legacy.clean.thaumicrevelations.client.render.RenderFleshGolem;
import mortvana.legacy.clean.core.common.CommonProxy;
import mortvana.legacy.errored.thaumicrevelations.entity.EntityFleshProjectile;
import mortvana.legacy.errored.thaumicrevelations.entity.FleshGolem;
import mortvana.legacy.dependent.seconddegree.morttech.client.gui.GuiWoodmill;
import mortvana.legacy.errored.morttech.util.MTControls;
import mortvana.legacy.dependent.firstdegree.morttech.util.handlers.ClientTickHandler;
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

		RenderingRegistry.registerEntityRenderingHandler(FluxGearContent.EntityPurity.class, new RenderPurity());
		RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
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
	public void registerTickHandler ()
	{
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		new ClientTickHandler();
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
		try
		{
			InputStream stream = ProjectFluxGear.class.getResourceAsStream(location);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			return doc;
		}
		catch (Exception e)
		{
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
		this.doSpawnParticle(particle, xPos, yPos, zPos, velX, velY, velZ);
	}

	public Minecraft mc = Minecraft.getMinecraft();

	/**We Don't Need No Stinking Particles!!!!*/
    public EntityFX doSpawnParticle (String par1Str, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        if (this.mc == null)
            this.mc = Minecraft.getMinecraft();

        if (this.mc.renderViewEntity != null && this.mc.effectRenderer != null)
        {
            int i = this.mc.gameSettings.particleSetting;

            if (i == 1 && mc.theWorld.rand.nextInt(3) == 0)
            {
                i = 2;
            }

            double d6 = this.mc.renderViewEntity.posX - par2;
            double d7 = this.mc.renderViewEntity.posY - par4;
            double d8 = this.mc.renderViewEntity.posZ - par6;
            EntityFX entityfx = null;

            if (par1Str.equals("hugeexplosion"))
            {
                this.mc.effectRenderer.addEffect(entityfx = new EntityHugeExplodeFX(mc.theWorld, par2, par4, par6, par8, par10, par12));
            }
            else if (par1Str.equals("largeexplode"))
            {
                this.mc.effectRenderer.addEffect(entityfx = new EntityLargeExplodeFX(mc.renderEngine, mc.theWorld, par2, par4, par6, par8, par10, par12));
            }
            else if (par1Str.equals("fireworksSpark"))
            {
                this.mc.effectRenderer.addEffect(entityfx = new EntityFireworkSparkFX(mc.theWorld, par2, par4, par6, par8, par10, par12, this.mc.effectRenderer));
            }

            if (entityfx != null)
            {
                return (EntityFX) entityfx;
            }
            else
            {
                double d9 = 16.0D;

                if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
                {
                    return null;
                }
                else if (i > 1)
                {
                    return null;
                }
                else
                {
                    if (par1Str.equals("bubble"))
                    {
                        entityfx = new EntityBubbleFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("suspended"))
                    {
                        entityfx = new EntitySuspendFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("depthsuspend"))
                    {
                        entityfx = new EntityAuraFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("townaura"))
                    {
                        entityfx = new EntityAuraFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("crit"))
                    {
                        entityfx = new EntityCritFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("smoke"))
                    {
                        entityfx = new EntitySmokeFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("mobSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, par2, par4, par6, 0.0D, 0.0D, 0.0D);
                        ((EntityFX) entityfx).setRBGColorF((float) par8, (float) par10, (float) par12);
                    }
                    else if (par1Str.equals("mobSpellAmbient"))
                    {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, par2, par4, par6, 0.0D, 0.0D, 0.0D);
                        ((EntityFX) entityfx).setAlphaF(0.15F);
                        ((EntityFX) entityfx).setRBGColorF((float) par8, (float) par10, (float) par12);
                    }
                    else if (par1Str.equals("spell"))
                    {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("instantSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                        ((EntitySpellParticleFX) entityfx).setBaseSpellTextureIndex(144);
                    }
                    else if (par1Str.equals("witchMagic"))
                    {
                        entityfx = new EntitySpellParticleFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                        ((EntitySpellParticleFX) entityfx).setBaseSpellTextureIndex(144);
                        float f = mc.theWorld.rand.nextFloat() * 0.5F + 0.35F;
                        ((EntityFX) entityfx).setRBGColorF(1.0F * f, 0.0F * f, 1.0F * f);
                    }
                    else if (par1Str.equals("note"))
                    {
                        entityfx = new EntityNoteFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("portal"))
                    {
                        entityfx = new EntityPortalFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("enchantmenttable"))
                    {
                        entityfx = new EntityEnchantmentTableParticleFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("explode"))
                    {
                        entityfx = new EntityExplodeFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("flame"))
                    {
                        entityfx = new EntityFlameFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("lava"))
                    {
                        entityfx = new EntityLavaFX(mc.theWorld, par2, par4, par6);
                    }
                    else if (par1Str.equals("footstep"))
                    {
                        entityfx = new EntityFootStepFX(mc.renderEngine, mc.theWorld, par2, par4, par6);
                    }
                    else if (par1Str.equals("splash"))
                    {
                        entityfx = new EntitySplashFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("largesmoke"))
                    {
                        entityfx = new EntitySmokeFX(mc.theWorld, par2, par4, par6, par8, par10, par12, 2.5F);
                    }
                    else if (par1Str.equals("cloud"))
                    {
                        entityfx = new EntityCloudFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("reddust"))
                    {
                        entityfx = new EntityReddustFX(mc.theWorld, par2, par4, par6, (float) par8, (float) par10, (float) par12);
                    }
                    else if (par1Str.equals("snowballpoof"))
                    {
                        entityfx = new EntityBreakingFX(mc.theWorld, par2, par4, par6, Items.snowball);
                    }
                    else if (par1Str.equals("dripWater"))
                    {
                        entityfx = new EntityDropParticleFX(mc.theWorld, par2, par4, par6, Material.water);
                    }
                    else if (par1Str.equals("dripLava"))
                    {
                        entityfx = new EntityDropParticleFX(mc.theWorld, par2, par4, par6, Material.lava);
                    }
                    else if (par1Str.equals("snowshovel"))
                    {
                        entityfx = new EntitySnowShovelFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("blueslime"))
                    {
                        entityfx = new EntityBreakingFX(mc.theWorld, par2, par4, par6, TRepo.strangeFood);
                    }
                    else if (par1Str.equals("heart"))
                    {
                        entityfx = new EntityHeartFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("angryVillager"))
                    {
                        entityfx = new EntityHeartFX(mc.theWorld, par2, par4 + 0.5D, par6, par8, par10, par12);
                        ((EntityFX) entityfx).setParticleTextureIndex(81);
                        ((EntityFX) entityfx).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }
                    else if (par1Str.equals("happyVillager"))
                    {
                        entityfx = new EntityAuraFX(mc.theWorld, par2, par4, par6, par8, par10, par12);
                        ((EntityFX) entityfx).setParticleTextureIndex(82);
                        ((EntityFX) entityfx).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }

                    if (entityfx != null)
                    {
                        this.mc.effectRenderer.addEffect((EntityFX) entityfx);
                    }

                    return (EntityFX) entityfx;
                }
            }
        }
        else
        {
            return null;
        }
    }


}
