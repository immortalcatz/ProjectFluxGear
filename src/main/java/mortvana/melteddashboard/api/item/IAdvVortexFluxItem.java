package mortvana.melteddashboard.api.item;

import mortvana.melteddashboard.util.enums.EnumVortexFluxSource;

public interface IAdvVortexFluxItem {

    boolean isPoweredByDefault(EnumVortexFluxSource source);

    double getModiferForSource(EnumVortexFluxSource source);
}
