package oldcode.legacy.util.runtime;

import cpw.mods.fml.common.ICrashCallable;
/**
 * Crash Callable for supported environments.
 * Originally in Mantle
 */
public class CallableSuppConfig implements ICrashCallable {

    private String modId;

    public CallableSuppConfig(String modId) {
        this.modId = modId;
    }

    @Override
    public String call () throws Exception {
        return "Environment healthy.";
    }

    @Override
    public String getLabel () {
        return modId + " Environment";
    }
}