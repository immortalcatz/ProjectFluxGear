package mortvana.melteddashboard.util.helpers;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import cpw.mods.fml.client.FMLClientHandler;

import mortvana.melteddashboard.util.sound.SoundBase;

public class SoundHelper {

	public static final SoundHandler manager = FMLClientHandler.instance().getClient().getSoundHandler();

	public static void playSound(ISound sound) {
		manager.playSound(sound);
	}

	public static void playSound(String sound, float x, float y, float z, float volume, float pitch) {
		manager.playSound(new SoundBase(sound, x, y, z, volume, pitch));
	}

}
