package oldcode.projectfluxgear.thaumic;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import oldcode.projectfluxgear.core.FluxGearConfig;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class FluxGearResearchItem extends ResearchItem {

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
		boolean checkInfusion = true;
		for (ResearchPage page : pages) {
			if (page.type == ResearchPage.PageType.TEXT) {
				page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
			}
		}
		checkInfusion(checkInfusion, pages);
		return super.setPages(pages);
	}

	public ResearchItem setPages(boolean checkInfusion, ResearchPage... pages) {
		boolean infusion = checkInfusion;
		for (ResearchPage page : pages) {
			if (page.type == ResearchPage.PageType.TEXT) {
				page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
			}
		}
		checkInfusion(infusion, pages);
		return super.setPages(pages);
	}

	public void checkInfusion(boolean checkInfusion, ResearchPage... pages) {
		boolean infusion = checkInfusion;
		for (ResearchPage page : pages) {
			if (infusion && page.type == ResearchPage.PageType.INFUSION_CRAFTING) {
				if (parentsHidden == null || parentsHidden.length == 0)
					parentsHidden = new String[] { "INFUSION" };
				else {
					String[] newParents = new String[parentsHidden.length + 1];
					newParents[0] = "INFUSION";
					for (int i = 0; i < parentsHidden.length; i++)
						newParents[i + 1] = parentsHidden[i];
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
