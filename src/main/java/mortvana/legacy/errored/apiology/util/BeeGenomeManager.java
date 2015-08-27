package mortvana.legacy.errored.apiology.util;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;
import mortvana.legacy.errored.apiology.util.BeeSpecies;
import mortvana.legacy.refactored.apiology.util.Allele;

public class BeeGenomeManager {

    //Base genome for my bees
    private static IAllele[] getTemplateModBase() {

        IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

        genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.DERP;
        genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedSlowest");
        genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
        genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
        genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
        genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
        genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
        genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
        genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
        genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");

        return genome;
    }

    public static IAllele[] addRainResist(IAllele[] genome) {
        genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");

        return genome;
    }

    public static IAllele[] getTemplateBaseRadioactive() {

        IAllele[] genome = getTemplateModBase();

        genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanLongest");
        genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityLow");
        genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
        genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
        genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceBoth2");
        genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolTrue");
        genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
        genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectRadiation");
        return genome;
    }

    public static IAllele[] getTemplateIrradiated() {
        IAllele[] genome = getTemplateBaseRadioactive();

        genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.IRRADIATED;
    }
}
