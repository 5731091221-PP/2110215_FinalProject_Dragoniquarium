package logic;

//import lib.AudioUtility;

public abstract class CollectibleObject extends TargetObject{
	
	protected int xBucket, yBucket;
	protected int reward;
	protected int timeSpent = 300;
	protected boolean movingUp;
	
	public CollectibleObject(int x, int y, int radius, int z, int reward) {
		super(x, y, radius, z);
		// TODO Auto-generated constructor stub
		this.reward = reward;
		this.movingUp = true;
//		this.xSpeed = 5;
//		this.ySpeed = 0;
		System.out.println("x = " + this.x + " y = " + this.y);
		generateMovingDestination(this.x, this.y);
		System.out.println("xDes = "+xDestination+ " yDes = "+yDestination);
	}
	
	@Override
	public void generateMovingDestination(int curX, int curY) {
//		System.out.println("In generate x = " + curX + " y = " + curY);
		if(movingUp) {
			xDestination = curX;
			yDestination = RandomUtility.random(200, 202);
		} else {
			yDestination = 800;
		}
//		int timeSpent = 10;
//		xSpeed = (xDestination - curX)/timeSpent;
//		ySpeed = (yDestination - curY)/timeSpent;
//		if (xSpeed == 0) {
//			if(xDestination > curX) xSpeed = 1;
//			else if (xDestination < curX) xSpeed = -1;
//		}
//		if (ySpeed == 0) {
//			if (yDestination > curY) ySpeed = 1;
//			else if(yDestination < curY) ySpeed = -1;
//		}
//		
	}
	
	@Override
	public void move() {
		System.out.println("move");
		if(destroyed) return;
		
		if(contains(xDestination,yDestination)) {
			reachDestination();
			System.out.println("reached");
			return ;
		}
		System.out.println("current x,y = " + x +" " +y);
		if(movingUp) {
			System.out.println("move up");
			y += (yDestination - y) * 0.05;
		} else {
			System.out.println("move down");
			y += 2;
			System.out.println("down");
		}
//		System.out.println("current x,y = " + x +" " +y);
	}
	
	public void grab(PlayerStatus player){
		if(destroyed) return;
		
//		AudioUtility.playSound("collect");
		collect(player);
		destroyed = true;
	}
	
	public abstract void collect(PlayerStatus player);
}
