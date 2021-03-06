package mortvana.legacy.clean.core.util.helpers.helpers;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.Loader;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import mortvana.legacy.clean.core.util.block.subtile.SubtileEntity;
import mortvana.legacy.clean.core.util.block.subtile.SubtileSignature;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class SubtileHelper {

	protected static BiMap<String, Class<? extends SubtileEntity>> subtiles = HashBiMap.<String, Class<? extends SubtileEntity>> create();
	protected static Map<Class<? extends SubtileEntity>, SubtileSignature> subtileSignatures = new HashMap<Class<? extends SubtileEntity>, SubtileSignature>();
	public static Set<String> subtilesForCreativeMenu = new LinkedHashSet<String>();
	public static Map<String, String> subtileMods = new HashMap<String, String>();

	/**
	 * Registers a SubtileEntity, a new mechanism.
	 * If you call this after PostInit you're a failure and we are very disappointed in you.
	 */
	public static void registerSubTile(String key, Class<? extends SubtileEntity> subtileClass) {
		subtiles.put(key, subtileClass);
		subtileMods.put(key, Loader.instance().activeModContainer().getModId());
	}

	/**
	 * Registers a SubTileEntity's signature.
	 * @see SubtileSignature
	 */
	public static void registerSubtileSignature(Class<? extends SubtileEntity> subtileClass, SubtileSignature signature) {
		subtileSignatures.put(subtileClass, signature);
	}

	/**
	 * Gets the singleton signature for a SubTileEntity class. Registers a fallback if one wasn't registered
	 * before the call.
	 */
	public static SubtileSignature getSignatureForClass(Class<? extends SubtileEntity> subtileClass) {
		if(!subtileSignatures.containsKey(subtileClass))
			registerSubtileSignature(subtileClass, new SubtileSignature.BasicSignature(subtiles.inverse().get(subtileClass)));

		return subtileSignatures.get(subtileClass);
	}

	/**
	 * Gets the singleton signature for a SubTileEntity's name. Registers a fallback if one wasn't registered
	 * before the call.
	 */
	public static SubtileSignature getSignatureForName(String name) {
		Class<? extends SubtileEntity> subtileClass = subtiles.get(name);
		return getSignatureForClass(subtileClass);
	}

	public static String getSubtileStringMapping(Class<? extends SubtileEntity> clazz) {
		return subtiles.inverse().get(clazz);
	}


    //Moved form DummyHandler
    public static IIcon getSubTileIconForName(String name) {
            return Blocks.potatoes.getIcon(0, 0);
        }

    public static void registerBasicSignatureIcons(String name, IIconRegister register) {}
}
