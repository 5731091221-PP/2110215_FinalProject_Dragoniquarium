package logic;

import java.awt.Graphics2D;

public class Enemy1 extends EnemyObject {

	
	
	public Enemy1(int x, int y, int radius, int z) {
		super(x, y, radius, z, 1, 20, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillOval((int)(x-radius), (int)(y-radius), 2*radius, 2*radius);
		
	}
	

}
