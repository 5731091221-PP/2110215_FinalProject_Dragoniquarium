package logic;

import java.awt.Color;
import java.awt.Graphics2D;

public class AttackObject extends TargetObject {

	
	private int attack;
	private double speed;
	
	public AttackObject(double x, double y, int radius, int z, int attack, 
			double xDes, double yDes, double speed) {
		super(x, y, radius, z);
		this.attack = attack;
		this.speed = speed;
		this.xDestination = xDes;
		this.yDestination = yDes;
		generateSpeed();
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
		x += xSpeed;
		y += ySpeed;
		if(x > 1024 || x < 0 || y < 0 || y > 600) {
			destroyed = true;
		}
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillOval((int)(x-radius), (int)(y-radius), 10, 10);
		
	}
	
	@Override
	public void generateMovingDestination(double curX, double curY) {}
	
	@Override
	public void reachDestination() {}
	
	

}
