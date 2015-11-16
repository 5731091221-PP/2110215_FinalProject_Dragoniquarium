package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class DrawingUtility {

	protected static final Font standardFont = new Font("Tahoma",Font.BOLD,30);
	protected static final Font smallFont = new Font("Tahoma",Font.PLAIN,9);
	
	private static BufferedImage getImage(String directory){
		try {
			ClassLoader loader = DrawingUtility.class.getClassLoader();
			BufferedImage image = ImageIO.read(loader.getResource(directory));
			return image;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static final BufferedImage bg = getImage("res/background.jpg");
//	protected static final BufferedImage gun = getImage("res/img/gun.png");
//	protected static final BufferedImage gun_inf = getImage("res/img/gun_inf.png");
//	protected static final BufferedImage shootAnim = getImage("res/img/shootAnim.png");
	
	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
//	public static BufferedImage getShootanim() {
//		return shootAnim;
//	}
	
	public static void drawShootableObject(Graphics2D g2,int x,int y,int radius,String name,boolean isPointerOver){
		
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
		
		if(name.equalsIgnoreCase("simple")) {
			g2.setColor(Color.BLUE);
		} else if(name.equalsIgnoreCase("splitter")) {
			g2.setColor(Color.RED);
		} else if(name.equalsIgnoreCase("small")) {
			g2.setColor(Color.YELLOW);
		}
		
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);
		
		if(isPointerOver){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x-radius, y-radius, radius*2, radius*2);
			g2.setComposite(opaque);
		}
	}
	
/*	public static void drawItemGun(Graphics2D g2,int x,int y,int radius,String name,boolean isPointerOver){
		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
		g2.setColor(Color.GRAY);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);
		
		if(name.equalsIgnoreCase("gun")) {
			g2.drawImage(gun, null, x-15, y-15);
		} else if(name.equalsIgnoreCase("“gun_inf")) {
			g2.drawImage(gun_inf, null, x-15, y-15);
		}
		
		if(isPointerOver){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
			g2.setComposite(opaque);
		}
	}*/
	
	public static void drawItemBullet(Graphics2D g2,int x,int y,int radius,boolean isPointerOver){

		g2.setColor(Color.BLACK);
		g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
		g2.setColor(Color.GRAY);
		g2.fillOval(x-radius, y-radius, radius*2, radius*2);
		
		g2.setColor(Color.BLACK);
		g2.fillRect(x-20, y-10, 20, 20);
		g2.fillOval(x-20, y-10, 40, 20);
		
		if(isPointerOver){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillOval(x-radius-2, y-radius-2, radius*2+4, radius*2+4);
			g2.setComposite(opaque);
		}
	}
	
/*	public static void drawIconGun(Graphics2D g2,int bulletQuantity,int maxBullet,boolean isInfiniteBullet){
		if(gun == null || (isInfiniteBullet && gun_inf == null)) return;
		g2.setFont(DrawingUtility.smallFont);
		if(isInfiniteBullet){
			g2.drawImage(gun_inf,null,ConfigurableOption.screenWidth/2-15,5);
		}else{
			g2.drawImage(gun,null,ConfigurableOption.screenWidth/2-15,5);
			g2.setColor(Color.WHITE);
			g2.drawString(bulletQuantity+"/"+maxBullet,ConfigurableOption.screenWidth/2,35);
		}
	}*/
	
/*	public static void drawStatusBar(Graphics2D g2, int remainingSecond,int score,Gun gun,boolean pause){

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, ConfigurableOption.screenWidth, 40);
		
		g2.setColor(Color.WHITE);
		g2.setFont(standardFont);
		g2.drawString("TIME : "+remainingSecond, 5, 35);
		
		g2.drawString("SOCRE : "+score, ConfigurableOption.screenWidth/2 + 40, 35);
		
		if(gun != null){
			gun.render(g2);
		}
		
		if(pause) {
			g2.setFont(standardFont);
			g2.drawString("PAUSE", ConfigurableOption.screenWidth/2-40, ConfigurableOption.screenHeight/2);
		}
	}*/
	
/*	public static GameAnimation createShootingAnimationAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.shootAnim,7,1);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}*/
}
