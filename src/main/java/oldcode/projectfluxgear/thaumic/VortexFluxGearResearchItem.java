package oldcode.projectfluxgear.thaumic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class VortexFluxGearResearchItem extends FluxGearResearchItem {


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
