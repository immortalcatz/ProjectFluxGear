package oldcode.legacy.util.exectpions;

import java.util.Random;

public class SciRageException extends Exception {

	public SciRageException() {
		super();
	}

	@Override
	public void printStackTrace() {
		Random rand = new Random();
		int line = rand.nextInt(14);
		String foo = "OOPS!";
		if (line ==  0) foo = "WHAT THE FRENCH TOAST?";
		if (line ==  1) foo = "YOU MONKEY BUTT!";
		if (line ==  2) foo = "I WILL CUT YOU!";
		if (line ==  3) foo = "YOU ARE A FRENCH CACTUS";
		if (line ==  4) foo = "WE DID IT JACKSON! MWHAHAHAHA!";
		if (line ==  5) foo = "I WANT YOU TO DIE";
		if (line ==  6) foo = "GO KILL YOURSELF YOU HOE";
		if (line ==  7) foo = "WILL YOU JUMP OFF A CLIFF?";
		if (line ==  8) foo = "DO YOU WANT TO GET SHOT WITH A CAT?";
		if (line ==  9) foo = "WILL YOU SHOVE A CAT UP YOUR MONKEY?";
		if (line == 10) foo = "I'MA SPOOOOOOOOOOOOOOOON!!!!";
		if (line == 11) foo = "I am now SIRSPOOONZALOTTTTT";
		if (line == 12) foo = "http://goo.gl/izR9y0";
		if (line == 13) foo = "I DON'T THINK BEFORE I SAY!!!!";
		if (line == 14) foo = "MY DICK IS STUCK IN AN AUTONOMOUS ACTIVATOR!!!!";
		System.err.println(foo);
	}

	@Override
	public String getMessage() {
		return "Sci4me Raged Over Cutting A French Toast Monkey Cactus Up His Spoon";
	}
}