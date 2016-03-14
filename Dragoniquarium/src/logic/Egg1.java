package logic;

import java.awt.Graphics2D;

import render.DrawingUtility;
import render.Resource;

public class Egg1 extends CollectibleObject {
	
	public Egg1(int x, int y) {
		super(x, y, 21, Integer.MAX_VALUE-2, 50);
	}

	@Override
	public void draw(Graphics2D g2d) {
//		g2d.fillOval((int)x-radius, (int)y-radius, 2*radius, 2*radius);
		if(isPointerOver) {
			g2d.setComposite(transcluentWhite);
		}
		
		g2d.drawImage(DrawingUtility.egg, null, (int)x-radius+2, (int)y-radius-4);
		
		g2d.setComposite(opaque);
	}

	@Override
	public void collect(PlayerStatus player) {
		//Resource.coinSound.play();
		player.addEgg(reward);
	}

	@Override
	public void reachDestination() {
		if(movingUp) {
			movingUp = false;
			
			generateMovingDestination(x, y);
		} else {
			destroyed = true;
		}	
	}
	
	
	
}
