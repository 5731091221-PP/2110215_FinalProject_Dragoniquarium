package logic;

public abstract class DamageableObject extends TargetObject {
	
	protected int life;
	protected int defense;
	protected double speed;
	
	private boolean movingIn;
	
	protected int state;
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
	*/
	
	private boolean isLeft;
	
	private int tickCountX = 0;
	
	private int tickCountY = 0;
	private double speedAddY = 0;
	private double targetSpeedY = 0;
	private boolean stable = false;
	private boolean speedRisingY = false;
	private int risingTickY = 0;
	private int tickNeedY = 0;
	
	public DamageableObject(int x,int y, int radius, int z, int movingType, int life, int defense) {
		super(x, y, radius, z);
		// TODO Auto-generated constructor stub
		this.movingType = movingType;
		this.life = life;
		this.defense = defense;
		
		this.isLeft = RandomUtility.random(0, 1) == 0 ? true : false;
		this.state = 1;
		this.movingIn = true;
		generateFirstDestination();
		this.speed = RandomUtility.random(0.005, 0.05);
			
	}
	
	@Override
	public void move() {
		if(destroyed) return;
		if(contains(xDestination,yDestination)) {
			movingIn = false;
			reachDestination();
			return ;
		}
		if(movingIn) {
			
			x += (xDestination - x) * speed;
			y += (yDestination - y) * speed;
			
			return ;
		}
		
		
		if(tickCountY == tickNeedY) {
			targetSpeedY = RandomUtility.random(-3.0, 3.0);
			risingTickY = RandomUtility.random(50, 100);
			tickNeedY = 2*risingTickY + RandomUtility.random(50, 100);
			speedAddY = targetSpeedY/risingTickY;
			
			speedRisingY = true;
			stable = false;
			tickCountY = 0;
		}
		tickCountY++;
		
		if(tickCountY > 2*risingTickY) {
			stable = true;
		} else if (tickCountY > risingTickY) {
			speedRisingY = false;
		}
		
		if(stable) {
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
		
		System.out.println(ySpeed + " " + speedAddY);
		y += ySpeed;
		
		if(x<20) {
			x = 20;
		} else if( x > 1000) {
			x = 1000;
		}
		
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
	}
	
	private void generateSpeed() {
		// TODO
	}

	@Override
	public void generateMovingDestination(double curX, double curY) {
		// TODO check start and end point
		this.speed = RandomUtility.random(0.005, 0.05);
		if (movingType == 1) {
			xDestination = RandomUtility.random(Math.max(30, curX-200),Math.min(990, curX+200));
			yDestination = RandomUtility.random(Math.max(40, curY-100), Math.min(500, curY+100));
		} else if (movingType == 2) {
			xDestination = RandomUtility.random(Math.max(30, curX-200),Math.min(990, curX+200));
			yDestination = 560;
		}
		
	}

	@Override
	public void reachDestination() {
		// TODO Auto-generated method stub
		generateMovingDestination(x, y);
	}
	
	public void hit() {
		//TODO
	}
	
}
