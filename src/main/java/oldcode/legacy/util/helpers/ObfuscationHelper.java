package oldcode.legacy.util.helpers;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.ReflectionHelper;

import oldcode.legacy.util.ObfuscationLibrary;

public class ObfuscationHelper {
	public static ResourceLocation getParticleTexture() {
		return ReflectionHelper.getPrivateValue(EffectRenderer.class, null, ObfuscationLibrary.PARTICLE_TEXTURES);
	}
}
