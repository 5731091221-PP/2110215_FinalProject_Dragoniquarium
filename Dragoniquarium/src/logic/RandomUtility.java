package logic;

import java.util.Random;

public class RandomUtility {

	public static int random(int start, int end) {
		return (new Random()).nextInt(end - start + 1) + start;
	}
	
}
