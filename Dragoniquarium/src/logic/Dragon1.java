package logic;

import java.awt.Graphics2D;

import render.Resource;

public class Dragon1 extends DamageableObject {
	
	public Dragon1(int x, int y, int z) {
		super(x, y, 20, z, 1, 1, 0);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO
		g2d.drawImage(Resource.egg1Sprite, null, (int)x-radius, (int)y-radius);
	}
	
	

}
