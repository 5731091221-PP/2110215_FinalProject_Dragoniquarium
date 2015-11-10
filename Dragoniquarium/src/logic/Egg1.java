package logic;

import java.awt.Graphics2D;

import render.Resource;

public class Egg1 extends CollectibleObject {
	
	public Egg1(int x, int y, int z) {
		super(20, 2, z, 30);
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(Resource.egg1Sprite, null, x, y);
		// TODO
	}

	@Override
	public void collect(PlayerStatus player) {
		player.addEgg(reward);
	}

	@Override
	public void reachDestination() {
		destroyed = true;
	}
	
	
	
}
