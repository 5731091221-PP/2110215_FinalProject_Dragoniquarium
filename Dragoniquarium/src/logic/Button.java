package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.Box.Filler;

import render.IRenderable;
import render.RenderableHolder;

public class Button implements IRenderable{

	private int x, y;
	private int z;
	private int width, height;
	private boolean visible = true;
	private boolean isPointerOver = false;
	
	private int type;
	/* type 1 to 5 - create dragon 1-5
	 * type 6 pause
	*/
	public Button(int type, int x, int y, int width, int height, int z) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.z = z;
	}
	public void click(List <TargetObject> onScreenObject, int zCounter) {
		switch (type) {
		case 1:
			TargetObject dragon = new Dragon1(RandomUtility.random(300, 700), 0, zCounter);
			onScreenObject.add(dragon);
			RenderableHolder.getInstance().add(dragon);
			break;
			
		case 2:
			
			break;
		default:
			break;
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
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, width, height);
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
