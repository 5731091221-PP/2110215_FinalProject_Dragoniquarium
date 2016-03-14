package logic;

import java.awt.Graphics2D;
import java.util.List;

import render.DrawingUtility;
import render.GameAnimation;
import render.RenderableHolder;

public class Enemy1 extends EnemyObject {

	private GameAnimation flyingAnimation;
	private GameAnimation attackingAnimation;
	
	private boolean attacking = false;
	
	public Enemy1(int x, int y, int z) {
		super(x, y, 45, z, 1, 20, 0, 100);
		stateTime = 30;
		flyingAnimation = DrawingUtility.createEnemy1Animation();
		attackingAnimation = DrawingUtility.createEnemy1AnimationAttack1();
	}
	
	@Override
	public void move() {
		if(destroyed) return;
		if(state == 1) {
			flyingAnimation.updateAnimation();
		} else if(state == 3) {
			attackingAnimation.updateAnimation();
		}
		
		if(hasDestination) {
			moveIn();
			return ;
		}
		
		attacking = false;
		
		if(state == 1) {
			stateTime--;
			if(stateTime == 0) {
				state = 3;
				stateTime = 60;
				attackingAnimation.setCurrentFrame(0);
			}
		} else if(state == 3) {
			stateTime--;
			if(stateTime == 10) {
				attacking = true;
			}
			
			if(stateTime == 0) {
				state = 1;
				stateTime = RandomUtility.random(100, 200);
			}
		}
		
		calculateXaxis();
		calculateYaxis();
	}
	
	@Override
	public void attack(List <AttackObject> onScreenAttack, int zCounter) {
		if(!attacking) {
			return ;
		}
		
		AttackObject atk = new AttackObject(x+50, y+20, 20, zCounter, 1,
								RandomUtility.random(leftBorder, rightBorder), bottomBorder,
								RandomUtility.random(1,3), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);
		
		AttackObject atk2 = new AttackObject(x, y+30, 20, zCounter, 1,
								RandomUtility.random(leftBorder, rightBorder), bottomBorder, 
								RandomUtility.random(1,3), 1);
		onScreenAttack.add(atk2);
		RenderableHolder.getInstance().add(atk2);
		
		AttackObject atk3 = new AttackObject(x-50, y+20, 20, zCounter, 1,
								RandomUtility.random(leftBorder, rightBorder), bottomBorder, 
								RandomUtility.random(1,3), 1);
		onScreenAttack.add(atk3);
		RenderableHolder.getInstance().add(atk3);
		
	}

	@Override
	public void draw(Graphics2D g2d) {
//		g2d.fillOval((int)(x-radius), (int)(y-radius), 2*radius, 2*radius);
		int tempX = (int)x-radius;
		int tempY = (int)y-radius;
		if(state == 1) {
			flyingAnimation.draw(g2d, (int)x-radius-36, (int)y-radius, isLeft);
		} else if(state == 3) {
			if(isLeft) {
				tempX += 4;
			}
			attackingAnimation.draw(g2d, tempX-64,tempY-10, isLeft);
		}
	}


}
