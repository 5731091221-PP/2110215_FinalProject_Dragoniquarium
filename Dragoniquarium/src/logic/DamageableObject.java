package logic;

public abstract class DamageableObject extends TargetObject {
	
	protected int life;
	protected int defense;
	protected double speed;
	
	public int topBorder = 20;
	public int bottomBorder = 580;
	public int rightBorder = 1000;
	public int leftBorder = 100;
	
//	private boolean movingIn;
	protected boolean hasDestination;
	
	protected int state;
	protected int stateTime;
	/*
	 * state 1 : normal move
	 * state 2 : idle
	 * state 3 : lay egg
	 * state 4 : attack
	 * state 5 : defense
	*/
	
	protected int movingType;
	/*
	 * movingType 1 for flying units
	 * movingType 2 for ground only units
	 * movingType 3 for Enemy
	*/
	
	public boolean isLeft;
	
	protected int tickCountX = 0;
	protected double speedAddX = 0;
	protected double targetSpeedX = 0;
	protected boolean stableX = false;
	protected boolean speedRisingX = false;
	protected int risingTickX = 0;
	protected int tickNeedX = 0;
	
	protected int tickCountY = 0;
	protected double speedAddY = 0;
	protected double targetSpeedY = 0;
	protected boolean stableY = false;
	protected boolean speedRisingY = false;
	protected int risingTickY = 0;
	protected int tickNeedY = 0;
	
	public DamageableObject(int x,int y, int radius, int z, int movingType, int life, int defense) {
		super(x, y, radius, z);
		// TODO Auto-generated constructor stub
		this.movingType = movingType;
		this.life = life;
		this.defense = defense;
		
		this.isLeft = RandomUtility.random(0, 1) == 0 ? true : false;
		this.state = 1;
//		this.movingIn = true;
		generateFirstDestination();
		this.speed = RandomUtility.random(0.03, 0.07);
			
	}
	
	public void setDestination(double xDes, double yDes) {
		hasDestination = false;
		this.xDestination = xDes;
		this.yDestination = yDes;
	}
	
	protected abstract void performStateAction();
	
	@Override
	public void move() {
		if(destroyed) return;
		if(GameLogic.enemyOnScreen) {
			performStateAction();
			return ;
		}
		if(hasDestination) {
			if(contains(xDestination,yDestination)) {
//				movingIn = false;
				hasDestination = false;
				reachDestination();
				return ;
			}
			
//			x += (xDestination - x)/ Math.hypot(xDestination - x, yDestination - y) * speed;
			x += (xDestination - x) * speed;
			y += (yDestination - y) * speed;
			return ;
		}
		
	/*	if(this instanceof Dragon1) {
			if(state == 1) {
				stateTime--;
				if(stateTime == 0) {
					state = 3;
				}
			}
		}*/
		
		
		if( movingType == 1) {
			calculateYaxis();
		}
		
		calculateXaxis();
		
	}
	
	protected void calculateXaxis(){
		
		if(tickCountX == tickNeedX) {
			if(isLeft) {
				targetSpeedX = RandomUtility.random(-4.0, -1.5);
			} else {
				targetSpeedX = RandomUtility.random(1.5, 4.0);
			}
			
			risingTickX = RandomUtility.random(100, 150);
			tickNeedX = 2*risingTickX + RandomUtility.random(100, 150);
			speedAddX = targetSpeedX/risingTickX;
			
			speedRisingX = true;
			stableX = false;
			tickCountX = 0;
			isLeft = !isLeft;
		}
		tickCountX++;
		
		if(tickCountX > 2*risingTickX) {
			stableX = true;
		} else if (tickCountX > risingTickX) {
			speedRisingX = false;
		}
		
		if(stableX) {
			if(speedAddX > 0) {
				xSpeed = 0.3;
			} else {
				xSpeed = -0.3;
			}
		} else if (speedRisingX){
			xSpeed += speedAddX;
		} else {
			xSpeed -= speedAddX;
		}
		
		x += xSpeed;
		
		if(x<leftBorder) {
			x = leftBorder;
		} else if( x > rightBorder) {
			x = rightBorder;
		}
	}
	
	protected void calculateYaxis(){
		
		if(tickCountY == tickNeedY) {
			targetSpeedY = RandomUtility.random(-2.0, 2.0);
			risingTickY = RandomUtility.random(100, 200);
			tickNeedY = 2*risingTickY + RandomUtility.random(50, 100);
			speedAddY = targetSpeedY/risingTickY;
			
			speedRisingY = true;
			stableY = false;
			tickCountY = 0;
		}
		tickCountY++;
		
		if(tickCountY > 2*risingTickY) {
			stableY = true;
		} else if (tickCountY > risingTickY) {
			speedRisingY = false;
		}
		
		if(stableY) {
			if(speedAddY > 0) {
				ySpeed = 0.2;
			} else {
				ySpeed = -0.2;
			}
		} else if (speedRisingY){
			ySpeed += speedAddY;
		} else {
			ySpeed -= speedAddY;
		}
		
		y += ySpeed;
		if(y < 20) {
			if(tickCountY < 2*risingTickY) tickCountY = 2*risingTickY;
			y = 20;
		} else if( y > 580) {
			if(tickCountY < 2*risingTickY) tickCountY = 2*risingTickY;
			y = 580;
		}
	}
	
	private void generateFirstDestination() {
		if(movingType == 1) {
			xDestination = RandomUtility.random(200, 800);
			yDestination = RandomUtility.random(150, 500);
		} else if(movingType == 2) {
			xDestination = RandomUtility.random(100, 900);
			yDestination = 560;
		}
		hasDestination = true;
	}
	
//	private void generateSpeed() {
//		// TODO
//	}

	@Override
	public void generateMovingDestination(double curX, double curY) {
		// TODO check start and end point
//		this.speed = RandomUtility.random(0.005, 0.05);
		if (movingType == 1) {
			xDestination = RandomUtility.random(Math.max(30, curX-200),Math.min(990, curX+200));
			yDestination = RandomUtility.random(Math.max(40, curY-100), Math.min(500, curY+100));
		} else if (movingType == 2) {
			xDestination = RandomUtility.random(Math.max(30, curX-200),Math.min(990, curX+200));
			yDestination = 560;
		}
		
		hasDestination = true;
	}

	@Override
	public void reachDestination() {
		// TODO Auto-generated method stub
//		generateMovingDestination(x, y);
		
	}
	
	public void hit(int damage) {
		//TODO
		damage = Math.max(damage-defense, 1);
		life -= damage;
		if(life <= 0) {
			destroyed = true;
		}
	}
	
}
