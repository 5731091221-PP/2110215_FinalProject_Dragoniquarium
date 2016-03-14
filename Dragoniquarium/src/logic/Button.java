package logic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import ui.HighScoreUtility;
import main.Main;
import render.IRenderable;
import render.RenderableHolder;

public class Button implements IRenderable{

	private int x, y;
	private int cost;
	private int width, height;
	private boolean visible = true;
	private boolean isPointerOver = false;
	
	private static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
	private static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
	private int type;
	/* type 1 to 5 - create dragon 1-5
	 * type 6 complete game
	 * type 7 pause
	*/
	public Button(int type, int x, int y, int width, int height,int cost) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cost = cost;
	}
	public void click(List <TargetObject> onScreenObject, PlayerStatus player,int zCounter) {
		if( 1<= type && type <= 5) {
			if( player.getEgg() < cost) {
				return ;
			} else {
				player.subtractEgg(cost);
			}
		}
		TargetObject dragon = null;
		switch (type) {
		case 1:
			dragon = new Dragon1(RandomUtility.random(300, 700), 0, zCounter);
			break;
		case 2:
			dragon = new Dragon2(RandomUtility.random(300, 700), 0, zCounter);
			break;
		case 3:
			dragon = new Dragon3(RandomUtility.random(300, 700), 0, zCounter);
			break;
		case 4:
			dragon = new Dragon4(RandomUtility.random(300, 700), 0, zCounter);
			break;
		case 5:
			dragon = new Dragon5(RandomUtility.random(300, 700), 0, zCounter);
			break;
			
		case 6:
			if( player.getEgg() >= cost) {
				HighScoreUtility.recordHighScore((player.getTimeSpent()*PlayerStatus.TIME_CLOCK)/1000);
				Main.goToTitle();
			}
			break;
		
		default:
			break;
		}
		
		if(dragon != null) {
			onScreenObject.add(dragon);
			RenderableHolder.getInstance().add(dragon);
		}
	}
	
	public boolean contains(double x, double y){
		return this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height ;
	}
	
	public void setPointerOver(boolean isPointerOver) {
		this.isPointerOver = isPointerOver;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if(isPointerOver) {
//			g2d.setColor(Color.RED);
			g2d.setComposite(transcluentWhite);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x, y, width, height);
			g2d.setComposite(opaque);
		} else {
			g2d.setColor(Color.GREEN);
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}
	
	

}
