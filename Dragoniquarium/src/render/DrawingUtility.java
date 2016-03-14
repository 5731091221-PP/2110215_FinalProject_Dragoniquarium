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
//			ClassLoader loader = DrawingUtility.class.getClassLoader();
//			BufferedImage image = ImageIO.read(loader.getResource(directory));
			BufferedImage image = ImageIO.read(DrawingUtility.class.getResource(directory));
			return image;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final BufferedImage egg = getImage("/res/Egg.png");
	public static final BufferedImage cursor = getImage("/res/AncientCursor.png");
	
	protected static final BufferedImage bg = getImage("/res/Background.jpg");
	protected static final BufferedImage grass = getImage("/res/Grass.png");
	protected static final BufferedImage lightRay = getImage("/res/LightRay.png");
	protected static final BufferedImage ui = getImage("/res/UI.png");
	protected static final BufferedImage titleBackGround = getImage("/res/titleBackGround.jpg");
	protected static final BufferedImage startButton = getImage("/res/NewGame.png");
	protected static final BufferedImage highScoreButton = getImage("/res/Scoreboard.png");
	protected static final BufferedImage startButton_pressed = getImage("/res/NewGamePressed.png");
	protected static final BufferedImage highScoreButton_pressed = getImage("/res/ScoreboardPressed.png");
	
	protected static final BufferedImage playButton = getImage("/res/PlayButton.png");
	protected static final BufferedImage pauseButton = getImage("/res/PauseButton.png");
	protected static final BufferedImage timeLineDragon = getImage("/res/TimeLineDragon.png");
	
	protected static final BufferedImage dragon1 = getImage("/res/dragon1.png");
	protected static final BufferedImage dragon1_egg = getImage("/res/dragon1_egg.png");
	protected static final BufferedImage dragon2 = getImage("/res/Dragon2.png");
	protected static final BufferedImage dragon2_attack = getImage("/res/Dragon2_Attack.png");
	protected static final BufferedImage dragon3 = getImage("/res/Dragon3.png");
	protected static final BufferedImage dragon3_def = getImage("/res/Dragon3_Def.png");
	protected static final BufferedImage dragon4 = getImage("/res/Dragon4.png");
	protected static final BufferedImage dragon4_attack = getImage("/res/Dragon4_Attack.png");
	protected static final BufferedImage dragon5 = getImage("/res/Dragon5.png");
	protected static final BufferedImage dragon5_attack = getImage("/res/Dragon5_Attack.png");
	
	protected static final BufferedImage Enemy1 = getImage("/res/Enemy1.png");
	protected static final BufferedImage Enemy1_attack = getImage("/res/Enemy1_Attack1.png");
	protected static final BufferedImage Enemy2 = getImage("/res/Enemy2.png");
	protected static final BufferedImage Enemy2_attack = getImage("/res/Enemy2_Attack1.png");
	
	protected static final BufferedImage attack1 = getImage("/res/FireBall1.png");
	protected static final BufferedImage attack2 = getImage("/res/FireBall2.png");
	protected static final BufferedImage attack3 = getImage("/res/FireBall3.png");
	protected static final BufferedImage attack4 = getImage("/res/FireBall4.png");
	
	protected static final BufferedImage attack1_boom = getImage("/res/FireBall1_Boom.png");
	protected static final BufferedImage attack2_boom = getImage("/res/FireBall2_Boom.png");
	protected static final BufferedImage attack3_boom = getImage("/res/FireBall3_Boom.png");
	protected static final BufferedImage attack4_boom = getImage("/res/FireBall4_Boom.png");
	
	protected static final BufferedImage warpping = getImage("/res/Warpping.png");
	
	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		
	public static BufferedImage getButton(String name) {
		if(name.equalsIgnoreCase("start")) {
			return startButton;
		} else if(name.equalsIgnoreCase("start pressed")) {
			return startButton_pressed;
		} else if(name.equalsIgnoreCase("high score")) {
			return highScoreButton;
		} else if(name.equalsIgnoreCase("high score pressed")) {
			return highScoreButton_pressed;
		} else if(name.equalsIgnoreCase("play")) {
			return playButton;
		} else if(name.equalsIgnoreCase("pause")) {
			return pauseButton;
		} 
		return null;
	}
	
	public static void drawAttack1(Graphics2D g2,int x,int y, boolean isPointerOver){
		g2.drawImage(attack1, null, x, y);
		if(isPointerOver) {
			
		}
	}
	
	public static void drawStatusBar(Graphics2D g2, int currentEgg, boolean pause, 
										int timeSpent, GameAnimation timeLineAnimation){
		g2.setColor(Color.WHITE);
		g2.setFont(standardFont);
		g2.drawString( "  " + currentEgg  /*+ " : " + (timeSpent*PlayerStatus.TIME_CLOCK)/1000*/, 20, 650);
		
		if(pause) {
			g2.setFont(standardFont);
		}
//		start at 260 
//		end at 1000
//		one time line equals to 740 time spent
		timeLineAnimation.draw(g2, 260 + (int)((timeSpent%(180*100))*740.0/(180*100)), 19, false);
	}
	public static GameAnimation createTimeLineAnimation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.timeLineDragon,12,1,12,6);
		anim.play();
		return anim;
	}
	
	// atk 2
	public static GameAnimation createAttack2Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.attack2,13,1,13,2);
		anim.play();
		return anim;
	}
	// atk 3
	public static GameAnimation createAttack3Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.attack3,13,1,13,3);
		anim.play();
		return anim;
	}
	// atk 4
	public static GameAnimation createAttack4Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.attack4,12,1,12,3);
		anim.play();
		return anim;
	}
	// TODO
	
	// 1
	public static GameAnimation createDragon1Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon1,12,1,12,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createDragon1AnimationLayingEgg() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon1_egg,12,1,12,4);
		anim.play();
		return anim;
	}
	
	// 2
	public static GameAnimation createDragon2Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon2,12,1,12,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createDragon2AnimationAttack() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon2_attack,12,2,6,4);
		anim.play();
		return anim;
	}
	
	// 3
	public static GameAnimation createDragon3Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon3,15,5,3,5);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createDragon3AnimationDef() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon3_def,12,3,4,3);
		anim.play();
		return anim;
	}
	// 4
	public static GameAnimation createDragon4Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon4,13,1,13,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createDragon4AnimationAttack() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon4_attack,13,1,13,4);
		anim.play();
		return anim;
	}
	
	// 5
	public static GameAnimation createDragon5Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon5,15,8,2,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createDragon5AnimationAttack() {
		GameAnimation anim = new GameAnimation(DrawingUtility.dragon5_attack,15,2,8,3);
		anim.play();
		return anim;
	}
	
	// enemy
	public static GameAnimation createEnemy1Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.Enemy1,12,2,6,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createEnemy1AnimationAttack1() {
		GameAnimation anim = new GameAnimation(DrawingUtility.Enemy1_attack,12,3,4,4);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createEnemy2Animation() {
		GameAnimation anim = new GameAnimation(DrawingUtility.Enemy2,12,2,6,3);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createEnemy2AnimationAttack1() {
		GameAnimation anim = new GameAnimation(DrawingUtility.Enemy2_attack,12,3,4,4);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createAttack1DestroyAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.attack1_boom,8,1, 8, 3, x, y);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createAttack2DestroyAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.attack2_boom,8,1, 8, 3, x, y);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createAttack3DestroyAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.attack3_boom,8,1, 8, 3, x, y);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createAttack4DestroyAt(int x,int y){
		GameAnimation anim = new GameAnimation(DrawingUtility.attack4_boom,8,1, 8, 3, x, y);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
	
	public static GameAnimation createWarppingAnimation(int x, int y) {
		GameAnimation anim = new GameAnimation(DrawingUtility.warpping,31,2, 16, 2, x, y);
		anim.centerAnimationAt(x,y);
		anim.play();
		return anim;
	}
}
