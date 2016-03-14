package logic;

import java.awt.Graphics2D;

import render.DrawingUtility;
import render.GameAnimation;

public class Dragon3 extends DamageableObject {
	
	private GameAnimation walkingAnimation;
	private GameAnimation guardingAnimation;
	
	public Dragon3(int x, int y, int z) {
		super(x, y, 55, z, 2, 5, 0);
		stateTime = 100;
		walkingAnimation = DrawingUtility.createDragon3Animation();
		guardingAnimation = DrawingUtility.createDragon3AnimationDef();
	}
	
	
	@Override
	public void move() {
		if(destroyed) return;
		
		
		if(state == 1) {
			walkingAnimation.updateAnimation();
		} else if(state == 3) {
			if(guardingAnimation.getCurrentFrame()!= guardingAnimation.getFrameCount()-1)
			guardingAnimation.updateAnimation();
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
			
			if (GameLogic.enemyOnScreen) {
				state = 3;
				radius = 95;
				stateTime = RandomUtility.random(100, 200);
				guardingAnimation.setCurrentFrame(0);
				return ;
			}
			if(stateTime == 0 ) {
				stateTime = RandomUtility.random(100, 200);
			}
			calculateXaxis();
		} else if(state == 3) {
			stateTime--;
			
			if(stateTime == 0 || GameLogic.enemyOnScreen) {
				if(GameLogic.enemyOnScreen) {
					stateTime = RandomUtility.random(100, 200);
				} else {
					radius = 55;
					state = 1;
					stateTime = RandomUtility.random(100, 200);
				}
			}
		}
		
		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
		int tempX = (int)x-radius;
		int tempY = (int)y-radius;
		
		if(state == 1) {
			walkingAnimation.draw(g2d, tempX, tempY, isLeft);
		} else if(state == 3) {
			guardingAnimation.draw(g2d, tempX-75, tempY+30, isLeft);
		}
	}
}