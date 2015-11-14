package input;

public class InputUtility {
	
	private static boolean keyPressed[] = new boolean[256];
	private static boolean keyTrigger[] = new boolean[256];
	
	public static boolean getKeyPressed(int key) {
		if(key < 0 || key >= 256) return false;
		return keyPressed[key];
	}
	public static void setKeyPressed(int key, boolean pressed) {
		if(key < 0 || key >= 256) return;
		keyPressed[key] = pressed;
	}
}
