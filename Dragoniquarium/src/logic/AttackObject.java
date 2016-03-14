package logic;

import java.awt.Graphics2D;

import render.DrawingUtility;
import render.GameAnimation;

public class AttackObject extends TargetObject {

	
	private int attack;
	private int life = 2;
	private double speed;
	
	private GameAnimation attackAnimation;
	
	public int attackType;
	/*
	 * type 1 : monster to dragon
	 * type 2 : dragon 2 to monster
	 * type 3 : dragon 4 to monster
	 * type 4 : dragon 5 to monster
	 */
	
	public int topBorder = 80;
	public int bottomBorder = 650;
	public int rightBorder = 1240;
	public int leftBorder = 260;
	
	public AttackObject(double x, double y, int radius, int z, int attack, 
			double xDes, double yDes, double speed, int attackType) {
		super(x, y, radius, z);
		this.attack = attack;
		this.speed = speed;
		this.attackType = attackType;
		this.xDestination = xDes;
		this.yDestination = yDes;
		generateSpeed();
		
		switch (attackType) {
		case 1:
			break;
		case 2:
			attackAnimation = DrawingUtility.createAttack2Animation();
			break;
		case 3:
			attackAnimation = DrawingUtility.createAttack3Animation();
			break;
		case 4:
			attackAnimation = DrawingUtility.createAttack4Animation();
			break;
		default:
			attackAnimation = DrawingUtility.createAttack3Animation();
			break;
		}
		
	}
	
	public void hitByPlayer() {
		if(attackType != 1) {
			return ;
		}
		life--;
		if(life <= 0) {
			destroyed = true;
		}
	}
	
	public int getAttack() {
		return attack;
	}

	private void generateSpeed() {
		xSpeed = (xDestination - x)/ Math.hypot(xDestination - x, yDestination - y) * speed;
		ySpeed = (yDestination - y)/ Math.hypot(xDestination - x, yDestination - y) * speed;
	}
	
	@Override
	public void move() {
		if(attackType != 1) {
			attackAnimation.updateAnimation();
		}
		
		x += xSpeed;
		y += ySpeed;
		if(x > rightBorder || x < leftBorder || y < topBorder || y > bottomBorder) {
			destroyed = true;
		}
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if(attackType == 1 && isPointerOver) {
			g2d.setComposite(transcluentWhite);
		}
		
		if(attackType == 1) {
			DrawingUtility.drawAttack1(g2d, (int)(x-radius)-8, (int)(y-radius)-8, isPointerOver);
		}else if(attackType == 3) {
			
			attackAnimation.draw(g2d, (int)(x-radius)-17, (int)(y-radius)-15, false);
			
		} else if(attackType == 4) {
			attackAnimation.draw(g2d, (int)(x-radius)-33, (int)(y-radius)-25, false);
		} else {
			attackAnimation.draw(g2d, (int)(x-radius)-20, (int)(y-radius)-20, false);
		}
		
		g2d.setComposite(opaque);
	}
	
	@Override
	public void generateMovingDestination(double curX, double curY) {}
	
	@Override
	public void reachDestination() {}
	
	

}
