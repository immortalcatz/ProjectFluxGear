package mortvana.projectfluxgear.gui.config;

import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.common.FluxGearConfig;
import mortvana.projectfluxgear.common.ProjectFluxGear;

public class ConfigGui extends GuiConfig {
	public ConfigGui(GuiScreen guiScreen) {
		super(guiScreen, new ConfigElement(FluxGearConfig.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), ProjectFluxGear.modID, true, true,
				GuiConfig.getAbridgedConfigPath(FluxGearConfig.getConfig().toString()));
	}
}
