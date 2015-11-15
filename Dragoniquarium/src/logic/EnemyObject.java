package logic;

import java.awt.Graphics2D;
import java.util.List;

public abstract class EnemyObject extends DamageableObject {	
	
	public EnemyObject(int x, int y, int radius, int z, int movingType,
			int life, int defense, int attackDelay) {
		super(x, y, radius, z, movingType, life, defense);
	}

	abstract void attack(List <AttackObject> onScreenAttack, int zCounter);
	
	public void isChased(double xClick, double yClick) {
		
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
		if(speedAddX > 0) {
			isLeft = false;
		} else {
			isLeft = true;
		}
		
	}
	
	@Override
	protected void performStateAction() {
		// TODO Auto-generated method stub
		
	}
	

}
