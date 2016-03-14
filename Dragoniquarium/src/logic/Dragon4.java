package logic;

import java.awt.Graphics2D;
import java.util.List;

import render.DrawingUtility;
import render.GameAnimation;
import render.RenderableHolder;

public class Dragon4 extends DamageableObject{

	private GameAnimation flyingAnimation;
	private GameAnimation attackingAnimation;
	
	private boolean attacking = false;
	
	public Dragon4(int x, int y, int z) {
		super(x, y, 40, z, 1, 3, 0);
		stateTime = 100;
		flyingAnimation = DrawingUtility.createDragon4Animation();
		attackingAnimation = DrawingUtility.createDragon4AnimationAttack();
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
				if(GameLogic.enemyOnScreen) {
					state = 3;
					stateTime = 60;
					attackingAnimation.setCurrentFrame(0);
				} else {
					stateTime = 100;
				}
				
			}
		} else if(state == 3) {
			stateTime--;
			if(stateTime == 6) {
				attacking = true;
			}
			
			if(stateTime == 0) {
				if(GameLogic.enemyOnScreen) {
					stateTime = 60;
					attackingAnimation.setCurrentFrame(0);
				} else {
					state = 1;
					stateTime = 100;
					isLeft = RandomUtility.random(0, 1) == 0 ? true : false;
					tickCountX = tickNeedX-1;
				}
				
			}
		}
		
		if(state == 1) {
			calculateXaxis();
		}
		calculateYaxis();
	}
	
	public void attack(List <AttackObject> onScreenAttack, TargetObject targetEnemy, int zCounter ) {
		if(targetEnemy == null) {
			return ;
		}
		
		if(targetEnemy.x > x) {
			isLeft = false;
		} else {
			isLeft = true;
		}
		
		if(!attacking) {
			return ;
		}
		double tempX = x;
		if(isLeft) {
			tempX -= 25;
		} else {
			tempX += 25;
		}
		
		AttackObject atk = new AttackObject(tempX, y, 15, zCounter, 4, targetEnemy.x, targetEnemy.y, 5, 3);
		onScreenAttack.add(atk);
		RenderableHolder.getInstance().add(atk);

	}
	
	@Override
	public void draw(Graphics2D g2d) {
//		g2d.fillOval((int)x-radius, (int)y-radius, 2*radius, 2*radius);	
		
		int tempX = (int)x-radius;
		int tempY = (int)y-radius;
		
		if(state == 1) {
			flyingAnimation.draw(g2d, tempX-6, tempY-10, isLeft);
		} else if(state == 3) {
			if(isLeft) {
				tempX -= 59;				
			} else {
				tempX -= 7;
			}
			attackingAnimation.draw(g2d, tempX, tempY-48, isLeft);
		}
	}

}