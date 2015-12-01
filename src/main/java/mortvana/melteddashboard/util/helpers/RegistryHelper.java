package mortvana.melteddashboard.util.helpers;

import java.util.*;

import net.minecraft.item.Item;
import net.minecraft.util.RegistryNamespaced;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.RegistryDelegate;
import cpw.mods.fml.relauncher.ReflectionHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import com.google.common.base.Throwables;
import com.google.common.collect.*;

public class RegistryHelper {

	private RegistryHelper() {}

	private static class Replacer {
		private static IdentityHashMap<RegistryNamespaced, Multimap<String, Object>> replacements;
		private static Class<RegistryDelegate<?>> DelegateClass;

		@SuppressWarnings("unchecked")
		private static void overwrite(RegistryNamespaced registry, String name, Object newThing, Object oldThing) {
			BiMap map = ((BiMap) registry.registryObjects);
			registry.underlyingIntegerMap.func_148746_a(newThing, registry.getIDForObject(oldThing));
			map.remove(name);
			map.forcePut(name, newThing);
		}

		private static void alterDelegateChain(RegistryNamespaced registry, String id, Object object) {
			Multimap<String, Object> map = replacements.get(registry);
			List<Object> list = (List<Object>) map.get(id);
			for (Object obj : list) {
				alterDelegate(obj, list.get(list.size() - 1));
			}
		}

		private static void alterDelegate(Object object, Object replacement) {
			if (object instanceof Item) {
				RegistryDelegate<Item> delegate = ((Item) object).delegate;
				ReflectionHelper.setPrivateValue(DelegateClass, delegate, replacement, "referant");
				ReflectionHelper.setPrivateValue(DelegateClass, ((Item) replacement).delegate, delegate.name(), "name");
			}
		}


		static {
			replacements = new IdentityHashMap<RegistryNamespaced, Multimap<String, Object>>(2);
			MinecraftForge.EVENT_BUS.register(new RegistryHelper());
			try {
				DelegateClass = (Class<RegistryDelegate<?>>) Class.forName("cpw.mods.fml.common.registry.RegistryDelegate$Delegate");
			} catch (Throwable e) {
				Throwables.propagate(e);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void _(WorldEvent.Load event) {
		if (Replacer.replacements.size() > 0) {
			for (Map.Entry<RegistryNamespaced, Multimap<String, Object>> entry : Replacer.replacements.entrySet()) {
				RegistryNamespaced reg = entry.getKey();
				Multimap<String, Object> map = entry.getValue();
				for (String id : map.keySet()) {
					List<Object> list = (List<Object>) map.get(id);
					Object end = list.get(list.size() - 1);
					if (reg.getIDForObject(list.get(0)) != reg.getIDForObject(end)) {
						for (Object obj : list) {
							Object oldThing = reg.getObject(id);
							Replacer.overwrite(reg, id, obj, oldThing);
							Replacer.alterDelegate(oldThing, end);
						}
					}
				}
			}
		}
	}

	public static void overwriteEntry(RegistryNamespaced registry, String name, Object newThing) {
		Object oldThing = registry.getObject(name);
		Replacer.overwrite(registry, name, newThing, oldThing);
		Multimap<String, Object> reg = Replacer.replacements.get(registry);
		if (reg == null) {
			Replacer.replacements.put(registry, reg = ArrayListMultimap.create());
		}
		if (!reg.containsKey(name)) {
			reg.put(name, oldThing);
		}
		reg.put(name, newThing);
		Replacer.alterDelegateChain(registry, name, newThing);
	}

}
