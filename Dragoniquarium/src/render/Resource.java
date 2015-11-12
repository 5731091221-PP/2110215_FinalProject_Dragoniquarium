package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	public static BufferedImage egg1Sprite;
	public static AudioClip coinSound;
	
	static {
		try {
			
			ClassLoader loader = Resource.class.getClassLoader();
			egg1Sprite = ImageIO.read(loader.getResource("res/Egg1.png"));
			coinSound = Applet.newAudioClip((loader.getResource("res/coin.wav")).toURI().toURL());
			
		} catch (Exception e) {
			System.err.println("Cannot load resource");
			egg1Sprite = null;
			coinSound = null;
		}
		
		
	}
	
	
}
