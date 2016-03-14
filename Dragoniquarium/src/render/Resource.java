package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;

public class Resource {
	
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	public static AudioClip coinSound;
	public static AudioClip backgroundSound;
	
	static {
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			coinSound = Applet.newAudioClip((loader.getResource("res/coin.wav")).toURI().toURL());
			backgroundSound = Applet.newAudioClip((loader.getResource("res/PeacefulForest.wav")).toURI().toURL());
			
		} catch (Exception e) {
			System.err.println("Cannot load resource");
			coinSound = null;
			backgroundSound = null;
		}
		
		
	}
	
	
}
