package mortvana.melteddashboard.util.exectpions;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.science.MathHelper;

public class GyroDerpExeption extends Exception {

    public GyroDerpExeption() {
        super();
    }

    @Override
    public void printStackTrace() {
        MeltedDashboardCore.logger.error(derp[MathHelper.RANDOM.nextInt(derp.length)]);
    }

    @Override
    public String getMessage() {
        return "Gyro ate the Congealed Blood again...";
    }

	public String[] derp = new String[] {
			"Gyro's unbalanced and his umbrella's a Psyduck~",
			"Why does Gyro do the things?",
			"*sigh*",
			"GYROHOUSE!!!!",
			"ALL THE MANYULLYN ACCORDION~",
			"sqrt -Whenk... :V",
			"Merge? Move? Wear pants?",
			"Ask Gyro why he has so many interfaces...",
			"I don't even...",
			"...I only odd.",
			"I like 3.141592...",
			"It goes up to eleven.",
			"I came in like a twerking dragon...",
			"MANDATORY FUN!!!!",
			"Eat Wall Street?",
			"Eat *A* Wall Street!",
			"I'm high on Bismuth!",
			"Now Rassilon had a buddy called Omega~ His interstellar science would amaze ya'!~",
			"Gyro, no, we store it in NBT :V",
			"The darkness heralds only one thing, the end of time itself.",
			"Eat weapons grade Plutonium...",
			"Go eat some Polonium!",
			"Every cloud has a Strontium lining.",
			"You sir, are a person with a face!",
			"22 kids go into the water, 29 kids come out of the water, the Ice Cream Man eats the rest.",
			"Meglos, the Evil, Sentient Cactus!~",
			"sqrt(-1) like π",
			"I had class outside a funeral.",
			"Catctus.",
			"I like to burn fascists.",
			"It's true we named our children after dogs..."
	};
}
