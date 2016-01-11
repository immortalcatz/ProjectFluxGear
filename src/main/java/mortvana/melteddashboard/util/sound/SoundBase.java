package mortvana.melteddashboard.util.sound;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

public class SoundBase implements ISound {

	public SoundBase(String sound, float x, float y, float z, float volume, float pitch) {

	}

	@Override
	public ResourceLocation getSoundLocation() {
		return null;
	}

	@Override
	public boolean canRepeat() {
		return false;
	}

	@Override
	public int getRepeatDelay() {
		return 0;
	}

	@Override
	public float getVolume() {
		return 0;
	}

	@Override
	public float getPitch() {
		return 0;
	}

	@Override
	public float getXPosF() {
		return 0;
	}

	@Override
	public float getYPosF() {
		return 0;
	}

	@Override
	public float getZPosF() {
		return 0;
	}

	@Override
	public AttenuationType getAttenuationType() {
		return null;
	}
}
