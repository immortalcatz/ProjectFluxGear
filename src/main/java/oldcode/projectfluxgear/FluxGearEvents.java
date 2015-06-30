package oldcode.projectfluxgear;

import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import tconstruct.library.event.ToolCraftEvent.*;

import oldcode.experditio.util.helpers.LoadedHelper;
import mortvana.projectfluxgear.util.helpers.TinkersHelper;

public class FluxGearEvents {
	public static final int MANASTEEL_ID = 145;
	public static final int TERRASTEEL_ID = 146;

	@SubscribeEvent
	public void onToolCraft(NormalTool event) {
		NBTTagCompound toolNBT = event.toolTag.getCompoundTag("InfiTool");

		// Modifiers
		int modifiers = toolNBT.getInteger("Modifiers");
		modifiers += TinkersHelper.getPartCount(toolNBT, ExPConfig.tinkersID_Enderium);
		modifiers += TinkersHelper.getPartCount(toolNBT, ExPConfig.tinkersID_Lumium);
		modifiers += TinkersHelper.getPartCount(toolNBT, ExPConfig.tinkersID_Signalum) * 2;

		if (LoadedHelper.isBotaniaLoaded && LoadedHelper.isExtraTiCLoaded && ExPConfig.modifyTinkersBotania) {
			modifiers += TinkersHelper.getPartCount(toolNBT, MANASTEEL_ID);
			if (TinkersHelper.getPartCount(toolNBT, TERRASTEEL_ID) > 0) {
				modifiers += 3;
			}
		}

		toolNBT.setInteger("Modifiers", modifiers);
	}
}
