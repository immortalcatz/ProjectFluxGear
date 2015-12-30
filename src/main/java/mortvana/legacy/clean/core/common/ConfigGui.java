package mortvana.legacy.clean.core.common;

import java.util.Set;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ConfigGui extends GuiConfig implements IModGuiFactory {

	@SuppressWarnings("unchecked")
	public ConfigGui(GuiScreen guiScreen) {
		super(guiScreen, new ConfigElement(FluxGearConfig.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), ProjectFluxGear.MOD_ID, true, true, GuiConfig.getAbridgedConfigPath(FluxGearConfig.getConfig().toString()));
	}

	@Override
	public void initialize(Minecraft mcInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}
