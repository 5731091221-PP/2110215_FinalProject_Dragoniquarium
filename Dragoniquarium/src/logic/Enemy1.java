package logic;

import java.awt.Graphics2D;
import java.util.List;

import render.RenderableHolder;

public class Enemy1 extends EnemyObject {

	private int attackTickCount = 0;
	private int attackDelay = 1000;
	
	public Enemy1(int x, int y, int radius, int z) {
		super(x, y, radius, z, 1, 20, 0, 100);
		// TODO Auto-generated constructor stub
	}

	@Override
	void attack(List <AttackObject> onScreenAttack, int zCounter) {
		if(attackTickCount < attackDelay) {
			attackTickCount++;
			return ;
		}
		
		attackTickCount = 0;
		for(int i=0;i<3;i++) {
			AttackObject atk = new AttackObject(x, y, 10, zCounter, 1,
					RandomUtility.random(0, 1024), 600, 3, 1);
			onScreenAttack.add(atk);
			RenderableHolder.getInstance().add(atk);
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillOval((int)(x-radius), (int)(y-radius), 2*radius, 2*radius);
		
	}
	

}
