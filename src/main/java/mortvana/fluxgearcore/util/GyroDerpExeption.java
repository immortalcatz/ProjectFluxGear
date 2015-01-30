package mortvana.fluxgearcore.util;

import java.util.Random;

public class GyroDerpExeption extends Exception{

    public GyroDerpExeption() {
        super();
    }

    @Override
    public void printStackTrace() {
        Random random = new Random();
        int line = random.nextInt(20);
        String derp = "Catctus";
        if (line ==  0) derp = "Gyro's unbalanced and his umbrella's a Psyduck~";
        if (line ==  1) derp = "Why does Gyro do the things?";
        if (line ==  2) derp = "*sigh*";
        if (line ==  3) derp = "GYROHOUSE!!!!";
        if (line ==  4) derp = "ALL THE MANYULLYN ACCORDION~";
        if (line ==  5) derp = "sqrt -Whenk... :V";
        if (line ==  6) derp = "Merge? Move? Wear pants?";
        if (line ==  7) derp = "Ask Gyro why he has so many interfaces...";
        if (line ==  8) derp = "I don't even...";
        if (line ==  9) derp = "...I only odd.";
        if (line == 10) derp = "I like 3.141592...";
        if (line == 11) derp = "It goes up to eleven.";
        if (line == 12) derp = "I came in like a twerking dragon...";
        if (line == 13) derp = "MANDATORY FUN!!!!";
        if (line == 14) derp = "Eat Wall Street";
        if (line == 15) derp = "Eat a Wall Street";
        if (line == 16) derp = "I'm high on Bismuth";
        if (line == 17) derp = "Now Rassilon had a buddy called Omega~ His interstellar science would amaze ya'!~";
        if (line == 18) derp = "Gyro, no, we store it in NBT :V";
        if (line == 19) derp = "The darkness heralds only one thing, the end of time itself.";
        if (line == 20) derp = "Eat weapons grade Plutonium";
        System.err.println(derp);
    }

    @Override
    public String getMessage() {
        return "Gyro ate the Congealed Blood Blocks again";
    }

}
