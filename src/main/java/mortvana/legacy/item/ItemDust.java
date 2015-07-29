package mortvana.legacy.item;

import java.util.List;

import mantle.items.abstracts.CraftingItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.FluxGearContent;

public class ItemDust extends CraftingItem {

	public ItemDust() {
        super(materialNames, getTextures(), "dusts/", "morttech", FluxGearContent.componentsTab);
    }

    private static String[] getTextures () {
        String[] names = new String[craftingTextures.length];
        for (int i = 0; i < craftingTextures.length; i++) {
            if (craftingTextures[i].equals("")) {
                names[i] = "";
            } else {
                names[i] = craftingTextures[i];
            }
        }
        return names;
    }

    static String[] materialNames = new String[] {"sawdust", "coal", "charcoal", "carbide", "flint", "clay", "ceramic", "iron", "gold", "bismuth", "aluminium", "nigelite", "copper", "tin", "zinc", "lead", "silver", "chromium", "titanium", "tungsten", "palladium", "platinum", "nickel", "manganese", "cobalt", "gallium", "indium", "cadmium", "tellurium", "vanadium", "emerald", "lapis", "diamond", "obsidian", "stone", "corundum", "ruby", "sapphire", "greenSapphire", "pinkSapphire", "purpleSapphire", "emery", "dioptase", "pyrope", "apatite", "amethyst", "topaz", "tanzanite", "malachite", "netherQuartz", "certusQuartz", "peridot", "mystic", "boron", "phosphorus", "sulfur", "milk", "uranium", "thorium", "plutonium", "neptunium", "protactinium", "actinium", "ferrousMetal", "radioactive", "enderpearl", "endstone", "saltpeter", "plastic"};

    static String[] craftingTextures = new String[] {
/*0-4*/     "dust_sawdust",         "dust_coal",            "dust_charcoal",    "dust_carbide",         "dust_flint",
/*5-9*/     "dust_clay",            "dust_ceramic",         "dust_iron",        "dust_gold",            "dust_bismuth",
/*10-14*/   "dust_aluminium",       "dust_nigelite",        "dust_copper",      "dust_tin",             "dust_zinc",
/*15-19*/   "dust_lead",            "dust_silver",          "dust_chromium",    "dust_titanium",        "dust_tungsten",
/*20-24*/   "dust_palladium",       "dust_platinum",        "dust_nickel",      "dust_manganese",       "dust_cobalt",
/*25-29*/   "dust_gallium",         "dust_indium",          "dust_cadmium",     "dust_tellurium",       "dust_vanadium",
/*30-34*/   "dust_emerald",         "dust_lapis",           "dust_diamond",     "dust_obsidian",        "dust_stone",
/*35-39*/   "dust_corundum",        "dust_ruby",            "dust_sapphire",    "dust_greenSapphire",   "dust_pinkSapphire",
/*40-44*/   "dust_purpleSapphire",  "dust_emery",           "dust_dioptase",    "dust_pyrope",          "dust_apatite",
/*45-49*/   "dust_amethyst",        "dust_topaz",           "dust_tanzanite",   "dust_malachite",       "dust_netherQuartz",
/*50-54*/   "dust_certusQuartz",    "dust_peridot",         "dust_mystic",      "dust_boron",           "dust_phosphorus",
/*55-59*/   "dust_sulfur",          "dust_milk",            "dust_uranium",     "dust_thorium",         "dust_plutonium",
/*60-64*/   "dust_neptunium",       "dust_protactinium",    "dust_actinium",    "dust_ferrousMetal",    "dust_radioactive",
/*65-69*/   "dust_enderpearl",      "dust_endstone",        "dust_saltpeter",   "dust_plastic",
    };

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getItemDamage()) {
            //TODO
            //Finish This
        case 0:
            StatCollector.translateToLocal("Good for Making Paper or Charcoal.");
            StatCollector.translateToLocal("Also good for Cleaning up Puke.");
            break;
        case 1:
            StatCollector.translateToLocal("Bad, Dirty Coal... ");
            StatCollector.translateToLocal("...Well it's Actually Lignite.");
            break;
        case 2:
            StatCollector.translateToLocal("Finely Ground and Quite Sooty.");
            break;
        case 3:
            StatCollector.translateToLocal("One day this may become a Diamond...");
            break;
        case 4:
            StatCollector.translateToLocal("Sharp and Abrasive.");
            break;
        case 5:
            StatCollector.translateToLocal("Soft and Moist.");
            break;
        case 6:
            StatCollector.translateToLocal("Unfired Ceramic Powder.");
            break;
        case 7:
            StatCollector.translateToLocal("UUM Dust:");
            StatCollector.translateToLocal("Universally Used Metallic Dust");
            break;
        case 8:
            StatCollector.translateToLocal("Overrated, but Shiny");
            break;
        case 9:
            StatCollector.translateToLocal("There's no Bismuth...");
            StatCollector.translateToLocal("...like Show Bismuth.");
            break;
        case 10:
            StatCollector.translateToLocal("Most Common of all the Metals...");
            StatCollector.translateToLocal("...Well, Non-Alkali Metals.");
            break;
        case 11:
            StatCollector.translateToLocal("It goes up to Eleven...");
            break;
        case 12:
            StatCollector.translateToLocal("Common and Conductive.");
            break;
        case 13:
            StatCollector.translateToLocal("Brittle and Slightly Vitreous.");
            break;
        case 14:
            StatCollector.translateToLocal("Brittle and Unstrurdy.");
            break;
        case 15:
            StatCollector.translateToLocal("Toxic and Dense.");
            break;
        case 16:
            StatCollector.translateToLocal("Most Conductive Element...");
            StatCollector.translateToLocal("Most Reflective too.");
            break;
        case 17:
            StatCollector.translateToLocal("Hard, but not useful by itself.");
            break;
        case 18:
            StatCollector.translateToLocal("High-Tech, but overrated.");
            break;
        case 19:
            StatCollector.translateToLocal("Incredibly Hard, but Brittle.");
            break;
        case 20:
            StatCollector.translateToLocal("The Period 5 PGM.");
            break;
        case 21:
            StatCollector.translateToLocal("Rarest Element in the World.");
            break;
        case 22:
            StatCollector.translateToLocal("It looks like Iron, but it isn't.");
            StatCollector.translateToLocal("Thermally Contractive.");
            break;
        case 23:
            StatCollector.translateToLocal("A Good Alloying Agent.");
            break;
        case 24:
            StatCollector.translateToLocal("Slightly Blue, and Ferromagnetic.");
            break;
        case 25:
            StatCollector.translateToLocal("Melts in your hand.");
            break;
        case 26:
            StatCollector.translateToLocal("Good for Control Rods");
            break;
        case 27:
            StatCollector.translateToLocal("Why not Catmium?");
            break;
        case 28:
            StatCollector.translateToLocal("Useful for High-Tech Solars");
            break;
        case 29:
            StatCollector.translateToLocal("Is this Crystalline Villager Blood?");
            break;
        case 30:
            StatCollector.translateToLocal("<INSERT WITTY COMMENT HERE>");
            break;
        case 31:
            StatCollector.translateToLocal("Blue Powder... Let's Centrifuge It!");
            break;
        case 32:
            StatCollector.translateToLocal("Shiny Shards of Crystalline Carbon.");
            break;
        case 33:
            StatCollector.translateToLocal("Volcanic Glass.");
            break;
        case 34:
            StatCollector.translateToLocal("Just Stone Dust.");
            break;
        case 35:
            StatCollector.translateToLocal("Uncolored Aluminium Oxide");
            break;
        case 36:
            StatCollector.translateToLocal("Bane of Greg's Infrastructure.");
            break;
        case 37:
            StatCollector.translateToLocal("<INSERT WITTY COMMENT HERE>");
            break;
        case 38:
            StatCollector.translateToLocal("It was never");
            StatCollector.translateToLocal("Supposed to be Emerald.");
            StatCollector.translateToLocal("                          -Eloraam");
            break;
        case 39:
            StatCollector.translateToLocal("NEC:");
            StatCollector.translateToLocal("Not Enough Chromium.");
            break;
        case 40:
            StatCollector.translateToLocal("THE PURPLE!!!!");
            StatCollector.translateToLocal("IT TASTES LIKE BURNING!!!!");
            break;
        case 41:
            StatCollector.translateToLocal("Quite Abrasive.");
            break;
        case 42:
            StatCollector.translateToLocal("Mysticly Conductive.");
            break;
        case 43:
            StatCollector.translateToLocal("The Dust of the Nether's Energy.");
            break;
        case 44:
            StatCollector.translateToLocal("Fertilization Pulverization.");
            break;
        case 45:
            StatCollector.translateToLocal("Purple Quartz... OF DOOM!!!!");
            break;
        case 46:
            StatCollector.translateToLocal("Orange Crystal Powder");
            break;
        case 47:
            StatCollector.translateToLocal("Ooh, Purple!");
            break;
        case 48:
            StatCollector.translateToLocal("Ground Green Copper Gem.");
            break;
        case 49:
            StatCollector.translateToLocal("The Dust of the Nether's Purity.");
            break;
        case 50:
            StatCollector.translateToLocal("High-Tech Silicate!");
            break;
        case 51:
            StatCollector.translateToLocal("Not so Gem-Grade Anymore!");
            break;
        case 52:
            StatCollector.translateToLocal("Magical Powder, for Making Alloys.");
            break;
        case 53:
            StatCollector.translateToLocal("INSERT WITTY COMMENT HERE");
            break;
        case 54:
            StatCollector.translateToLocal("Kinda like Carbon.");
            break;
        case 55:
            StatCollector.translateToLocal("So Stinky");
            break;
        case 56:
            StatCollector.translateToLocal("Science Goes Moo");
            break;
        case 57:
            StatCollector.translateToLocal("The Most Common Nuclear Fuel.");
            break;
        case 58:
            StatCollector.translateToLocal("Radioactive Powder.");
            break;
        case 59:
            StatCollector.translateToLocal("For the Doc Brown in All of Us!");
            break;
        case 60:
            StatCollector.translateToLocal("Shouldn't it be Pink?");
            break;
        case 61:
            StatCollector.translateToLocal("Uranium's Beta Decay Product.");
            break;
        case 62:
            StatCollector.translateToLocal("Actinium!");
            break;
        case 63:
        	StatCollector.translateToLocal("...<Something>, <Something>...");
            StatCollector.translateToLocal("It's Ferromagnetic!");
            break;
        case 64:
            StatCollector.translateToLocal("...Radioactive, Radioactive");
            StatCollector.translateToLocal("Oohh, Ohh...");
            break;
        case 65:
        	StatCollector.translateToLocal("Mmm... Teleporty Dust.");
        	break;
        case 66:
            StatCollector.translateToLocal("I just bought some rocks from space!");
            break;
        case 67:
            StatCollector.translateToLocal("Potassium Nitrate.");
            break;
        case 68:
        	StatCollector.translateToLocal("Made from Real Latex.");
        	break;
        }
    }
}