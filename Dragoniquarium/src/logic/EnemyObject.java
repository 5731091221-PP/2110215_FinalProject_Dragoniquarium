package logic;

import java.awt.Graphics2D;

public abstract class EnemyObject extends DamageableObject {

	
	
	public EnemyObject(int x, int y, int radius, int z, int movingType,
			int life, int defense) {
		super(x, y, radius, z, movingType, life, defense);
		// TODO Auto-generated constructor stub
	}

	abstract void attack();
	
	public void isChased(double xClick, double yClick) {
		
		System.out.println("isChased");
//		targetSpeedY = (y - yClick)/ Math.hypot(x - xClick, y - yClick) * 4;
		ySpeed = (y - yClick)/ Math.hypot(x - xClick, y - yClick) * 4;
		risingTickY = 50;
		tickNeedY = risingTickY;
		speedAddY = ySpeed/risingTickY;
		
		speedRisingY = false;
		stableY = false;
		tickCountY = 0;
		
//		targetSpeedX = (x - xClick)/ Math.hypot(x - xClick, y - yClick) * 4;
		xSpeed = (x - xClick)/ Math.hypot(x - xClick, y - yClick) * 4;
		risingTickX = 50;
		tickNeedX = risingTickX;
		speedAddX = xSpeed/risingTickX;
		
		speedRisingX = false;
		stableX = false;
		tickCountX = 0;
		
	}
	
	@Override
	protected void performStateAction() {
		// TODO Auto-generated method stub
		
	}
	

}
