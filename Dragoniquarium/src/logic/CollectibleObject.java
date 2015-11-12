package logic;

//import lib.AudioUtility;

public abstract class CollectibleObject extends TargetObject{
	
	protected int xBucket, yBucket;
	protected int reward;
	protected int timeSpent = 300;
	protected boolean movingUp;
	
	public CollectibleObject(double x, double y, int radius, int z, int reward) {
		super(x, y, radius, z);
		this.reward = reward;
		this.movingUp = true;
		generateMovingDestination(this.x, this.y);
	}
	
	@Override
	public void generateMovingDestination(double curX, double curY) {
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
		if(destroyed) return;
		
		if(contains(xDestination,yDestination)) {
			reachDestination();
			return ;
		}
		if(movingUp) {
			y += (yDestination - y) * 0.05;
		} else {
			y += 2;
		}
	}
	
	public void grab(PlayerStatus player){
		if(destroyed) return;
		
//		AudioUtility.playSound("collect");
		collect(player);
		destroyed = true;
	}
	
	public abstract void collect(PlayerStatus player);
}
