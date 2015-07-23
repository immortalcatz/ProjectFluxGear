package mortvana.legacy.item;

import java.util.List;

import mantle.items.abstracts.CraftingItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
            list.add("Good for Making Paper or Charcoal.");
            list.add("Also good for Cleaning up Puke.");
            break;
        case 1:
            list.add("Bad, Dirty Coal... ");
            list.add("...Well it's Actually Lignite.");
            break;
        case 2:
            list.add("Finely Ground and Quite Sooty.");
            break;
        case 3:
            list.add("One day this may become a Diamond...");
            break;
        case 4:
            list.add("Sharp and Abrasive.");
            break;
        case 5:
            list.add("Soft and Moist.");
            break;
        case 6:
            list.add("Unfired Ceramic Powder.");
            break;
        case 7:
            list.add("UUM Dust:");
            list.add("Universally Used Metallic Dust");
            break;
        case 8:
            list.add("Overrated, but Shiny");
            break;
        case 9:
            list.add("There's no Bismuth...");
            list.add("...like Show Bismuth.");
            break;
        case 10:
            list.add("Most Common of all the Metals...");
            list.add("...Well, Non-Alkali Metals.");
            break;
        case 11:
            list.add("It goes up to Eleven...");
            break;
        case 12:
            list.add("Common and Conductive.");
            break;
        case 13:
            list.add("Brittle and Slightly Vitreous.");
            break;
        case 14:
            list.add("Brittle and Unstrurdy.");
            break;
        case 15:
            list.add("Toxic and Dense.");
            break;
        case 16:
            list.add("Most Conductive Elemental Metal...");
            list.add("Most Reflective too.");
            break;
        case 17:
            list.add("Hard, but not useful by itself.");
            break;
        case 18:
            list.add("High-Tech, but overrated.");
            break;
        case 19:
            list.add("Incredibly Hard, but Brittle.");
            break;
        case 20:
            list.add("The Period 5 PGM.");
            break;
        case 21:
            list.add("Rarest Element in the World.");
            break;
        case 22:
            list.add("It looks like Iron, but it isn't.");
            list.add("Thermally Contractive.");
            break;
        case 23:
            list.add("A Good Alloying Agent.");
            break;
        case 24:
            list.add("Slightly Blue, and Ferromagnetic.");
            break;
        case 25:
            list.add("Melts in your hand.");
            break;
        case 26:
            list.add("Good for Control Rods");
            break;
        case 27:
            list.add("Why not Catmium?");
            break;
        case 28:
            list.add("Useful for High-Tech Solars");
            break;
        case 29:
            list.add("Is this Crystalline Villager Blood?");
            break;
        case 30:
            list.add("<INSERT WITTY COMMENT HERE>");
            break;
        case 31:
            list.add("Blue Powder... Let's Centrifuge It!");
            break;
        case 32:
            list.add("Shiny Shards of Crystalline Carbon.");
            break;
        case 33:
            list.add("Volcanic Glass.");
            break;
        case 34:
            list.add("Just Stone Dust.");
            break;
        case 35:
            list.add("Uncolored Aluminium Oxide");
            break;
        case 36:
            list.add("Bane of Greg's Infrastructure.");
            break;
        case 37:
            list.add("<INSERT WITTY COMMENT HERE>");
            break;
        case 38:
            list.add("It was never");
            list.add("Supposed to be Emerald.");
            list.add("                          -Eloraam");
            break;
        case 39:
            list.add("NEC:");
            list.add("Not Enough Chromium.");
            break;
        case 40:
            list.add("THE PURPLE!!!!");
            list.add("IT TASTES LIKE BURNING!!!!");
            break;
        case 41:
            list.add("Quite Abrasive.");
            break;
        case 42:
            list.add("Mysticly Conductive.");
            break;
        case 43:
            list.add("The Dust of the Nether's Energy.");
            break;
        case 44:
            list.add("Fertilization Pulverization.");
            break;
        case 45:
            list.add("Purple Quartz... OF DOOM!!!!");
            break;
        case 46:
            list.add("Orange Crystal Powder");
            break;
        case 47:
            list.add("Ooh, Purple!");
            break;
        case 48:
            list.add("Ground Green Copper Gem.");
            break;
        case 49:
            list.add("The Dust of the Nether's Purity.");
            break;
        case 50:
            list.add("High-Tech Silicate!");
            break;
        case 51:
            list.add("Not so Gem-Grade Anymore!");
            break;
        case 52:
            list.add("Magical Powder, for Making Alloys.");
            break;
        case 53:
            list.add("INSERT WITTY COMMENT HERE");
            break;
        case 54:
            list.add("Kinda like Carbon.");
            break;
        case 55:
            list.add("So Stinky");
            break;
        case 56:
            list.add("Science Goes Moo");
            break;
        case 57:
            list.add("The Most Common Nuclear Fuel.");
            break;
        case 58:
            list.add("Radioactive Powder.");
            break;
        case 59:
            list.add("For the Doc Brown in All of Us!");
            break;
        case 60:
            list.add("Shouldn't it be Pink?");
            break;
        case 61:
            list.add("Uranium's Beta Decay Product.");
            break;
        case 62:
            list.add("Actinium!");
            break;
        case 63:
        	list.add("...<Something>, <Something>...");
            list.add("It's Ferromagnetic!");
            break;
        case 64:
            list.add("...Radioactive, Radioactive");
            list.add("Oohh, Ohh...");
            break;
        case 65:
        	list.add("Mmm... Teleporty Dust.");
        	break;
        case 66:
            list.add("I just bought some rocks from space!");
            break;
        case 67:
            list.add("Potassium Nitrate.");
            break;
        case 68:
        	list.add("Made from Real Latex.");
        	break;
        }
    }
}