package logic;

import java.awt.Graphics2D;

import render.Resource;

public class Egg1 extends CollectibleObject {
	
	public Egg1(int x, int y, int z) {
		super(x, y, 20, z, 30);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(Resource.egg1Sprite, null, (int)x-radius, (int)y-radius);
		// TODO
	}

	@Override
	public void collect(PlayerStatus player) {
		player.addEgg(reward);
	}

	@Override
	public void reachDestination() {
		System.out.println("top");
		if(movingUp) {
			movingUp = false;
			
			generateMovingDestination(x, y);
		} else {
			System.out.println("destroy");
			destroyed = true;
		}	
	}
	
	
	
}
