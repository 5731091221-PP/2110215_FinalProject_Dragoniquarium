package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import render.IRenderable;
import render.Resource;

public class PlayerStatus implements IRenderable{
	
	private int egg;

	public PlayerStatus() {
		super();
		this.egg = 200;
	}
	
	public void addEgg(int a) {
		egg += a;
	}
	
	public void subtractEgg(int a) {
		egg = Math.max(egg - a, 0);
	}

	public int getEgg() {
		return egg;
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 420, 640, 60);
		g2d.setColor(Color.white);
		
		String scoreText = "EGG: " + this.egg;
		g2d.setFont(Resource.standardFont);
		g2d.drawString(scoreText, 10, 455);
		
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 2147483647;
	}
	
	
}
