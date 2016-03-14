package logic;

import java.awt.Graphics2D;
import java.util.List;

import render.DrawingUtility;
import render.GameAnimation;
import render.RenderableHolder;

public class Enemy2 extends EnemyObject {

	private GameAnimation flyingAnimation;
	private GameAnimation attackingAnimation;
	
	private boolean attacking = false;
	
	public Enemy2(int x, int y, int z) {
		super(x, y, 70, z, 1, 60, 0, 100);
		stateTime = 30;
		flyingAnimation = DrawingUtility.createEnemy2Animation();
		attackingAnimation = DrawingUtility.createEnemy2AnimationAttack1();
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
		
//		attack up
		for(int i=0; i < RandomUtility.random(1, 2);i++) {
			AttackObject atk = new AttackObject(x + RandomUtility.random(-20,20), y-60, 20, zCounter, 1,
									RandomUtility.random(leftBorder, rightBorder), topBorder,
									RandomUtility.random(2,5), 1);
			onScreenAttack.add(atk);
			RenderableHolder.getInstance().add(atk);
		}
		
		// down
		// 1
		AttackObject atk = new AttackObject(x+110, y+50, 20, zCounter, 1,
								RandomUtility.random(leftBorder, rightBorder), bottomBorder,
								RandomUtility.random(2,5), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);
		
		// 2
		atk = new AttackObject(x-110, y+50, 20, zCounter, 1,
					RandomUtility.random(leftBorder, rightBorder), bottomBorder, 
					RandomUtility.random(2,5), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);

		// 3
		atk = new AttackObject(x+120, y+40, 20, zCounter, 1,
					RandomUtility.random(leftBorder, rightBorder), bottomBorder,
					RandomUtility.random(2,5), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);
		
		// 4
		atk = new AttackObject(x-120, y+40, 20, zCounter, 1,
					RandomUtility.random(leftBorder, rightBorder), bottomBorder, 
					RandomUtility.random(2,5), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);
		
		// 5
		atk = new AttackObject(x, y+65, 20, zCounter, 1,
				RandomUtility.random(leftBorder, rightBorder), bottomBorder, 
				RandomUtility.random(4,5), 1);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);
	}

	@Override
	public void draw(Graphics2D g2d) {
//		g2d.fillOval((int)(x-radius), (int)(y-radius), 2*radius, 2*radius);
		int tempX = (int)x-radius;
		int tempY = (int)y-radius;
		if(state == 1) {
			flyingAnimation.draw(g2d, tempX-91, tempY, isLeft);
		} else if(state == 3) {
			if(isLeft) {
				tempX += 8;
			}
			attackingAnimation.draw(g2d, tempX-147, tempY-21, isLeft);
		}
	}


}