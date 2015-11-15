package logic;

import java.awt.Graphics2D;

import render.Resource;

public class Dragon1 extends DamageableObject {
	
	public boolean layingEgg = false;
	
	public Dragon1(int x, int y, int z) {
		super(x, y, 20, z, 2, 1, 0);
		stateTime = 200;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		if(destroyed) return;
		
		if(GameLogic.enemyOnScreen) {
			performStateAction();
			return ;
		}
		if(hasDestination) {
			if(contains(xDestination,yDestination)) {
				hasDestination = false;
				reachDestination();
				return ;
			}
			
			x += (xDestination - x) * speed;
			y += (yDestination - y) * speed;
			return ;
		}
		
		if(state == 1) {
			stateTime--;
			if(stateTime == 0) {
				state = 3;
				stateTime = 100;
			}
		} else if(state == 3) {
			stateTime--;
			if(stateTime == 0) {
//				layEgg();
				layingEgg = true;
				state = 1;
				stateTime = 500;
			}
			return ;
		}
		
		calculateXaxis();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO
		g2d.drawImage(Resource.egg1Sprite, null, (int)x-radius, (int)y-radius);
	}

	@Override
	protected void performStateAction() {
		// TODO Auto-generated method stub
		
	}
	
	

}
