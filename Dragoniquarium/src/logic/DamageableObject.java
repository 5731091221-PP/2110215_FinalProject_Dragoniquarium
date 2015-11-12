package logic;

public abstract class DamageableObject extends TargetObject {
	
	protected int life;
	protected int defense;
	protected double speed;
	
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
	
	// bigger = more edgier, hectic
	protected static double MAX_WANDER_OFFSET = 0.1; // 0.3
	// bigger = faster turns
	protected static double MAX_WANDER_RADIUS = 3.0; // 3.5
	
	protected double wanderTheta;
	protected double wanderRadius;
	
	public DamageableObject(int x,int y, int radius, int z, int movingType, int life, int defense) {
		super(x, y, radius, z);
		// TODO Auto-generated constructor stub
		this.movingType = movingType;
		this.life = life;
		this.defense = defense;
		this.state = 1;
//		generateMovingDestination(x, y);
		generateFirstDestination();
		this.speed = RandomUtility.random(0.005, 0.05);
//		System.out.println(speed);
		
		
		wanderTheta = Math.random() * 2.0 * Math.PI;
		wanderRadius = Math.random() * MAX_WANDER_RADIUS;
	}
	
	@Override
	public void move() {
		if(destroyed) return;
//		if(contains(xDestination,yDestination)) {
//			reachDestination();
//			return ;
//		}
//		System.out.println(speed);
		
		
		double wander_offset = Math.random()*2.0*MAX_WANDER_OFFSET - MAX_WANDER_OFFSET;
		wanderTheta += wander_offset;
		   
		x += Math.cos(wanderTheta);
		y += Math.sin(wanderTheta);
		
		if(x<20) {
			x = 20;
			wanderTheta += 4.0 * wander_offset; //Math.PI;
		}
		if( x >1000) {
			x = 1000;
			wanderTheta += 4.0 * wander_offset; //Math.PI;
		}
		if(y<100) {
			y = 100;
			wanderTheta += 4.0 * wander_offset; //Math.PI;
		}
		if( y > 550) {
			y = 550;
			wanderTheta += 4.0 * wander_offset; //Math.PI;
		}
		
/*		int moveX, moveY;
		moveX = (int) ((xDestination - x) * speed);
		moveY = (int) ((yDestination - y) * speed);
		if(moveX == 0) {
			moveX = xDestination > x ? 1:-1;
		}
		if(moveY == 0) {
			moveY = yDestination > y ? 1:-1;
		}
		x += moveX;
		y += moveY;*/
		
		
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
