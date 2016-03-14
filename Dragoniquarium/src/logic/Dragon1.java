package logic;

import java.awt.Graphics2D;

import render.DrawingUtility;
import render.GameAnimation;

public class Dragon1 extends DamageableObject {
	
	public boolean layingEgg = false;
	private GameAnimation walkingAnimation;
	private GameAnimation layingAnimation;
	
	public Dragon1(int x, int y, int z) {
		super(x, y, 40, z, 2, 1, 0);
		stateTime = RandomUtility.random(150, 250);
		walkingAnimation = DrawingUtility.createDragon1Animation();
		layingAnimation = DrawingUtility.createDragon1AnimationLayingEgg();
	}
	
	@Override
	public void move() {
		if(destroyed) return;
		
		
		if(state == 1) {
			walkingAnimation.updateAnimation();
		} else if(state == 3) {
			layingAnimation.updateAnimation();
		}
		
		if(hasDestination) {
			moveIn();
			return ;
		}
		if(state == 1) {
			stateTime--;
			if(stateTime == 0) {
				state = 3;
				stateTime = 50;
				layingAnimation.setCurrentFrame(0);
			}
		} else if(state == 3) {
			stateTime--;
			if(stateTime == 15) {
				layingEgg = true;
			}
			
			if(stateTime == 0) {
				state = 1;
				stateTime = RandomUtility.random(400, 600);
			}
			return ;
		}
		
		calculateXaxis();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		
//		g2d.fillOval((int)x-radius, (int)y-radius, radius*2, radius*2);
		
		int tempX = (int)x-radius;
		int tempY = (int)y-radius;
		
		if(state == 1) {
			
			walkingAnimation.draw(g2d, tempX-10, tempY-13, isLeft);
		} else if(state == 3) {
			tempX = (int)x-radius;
			
			if(isLeft) {
				tempX += 7;				
			} else {
				tempX -= 7;
			}
			layingAnimation.draw(g2d, tempX, tempY-19, isLeft);
		}
	}

}
