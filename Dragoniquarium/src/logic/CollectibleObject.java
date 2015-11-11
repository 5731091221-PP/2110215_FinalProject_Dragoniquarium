package logic;

//import lib.AudioUtility;

public abstract class CollectibleObject extends TargetObject{
	
	protected int xBucket, yBucket;
	protected int reward;
	protected int timeSpent = 300;
	
	public CollectibleObject(int radius, int movingType, int z, int reward) {
		super(radius, movingType, z);
		// TODO Auto-generated constructor stub
		this.reward = reward;
//		this.xSpeed = 5;
//		this.ySpeed = 0;
		generateMovingDestination(x, y);
	}
	
	@Override
	public void generateMovingDestination(int curX, int curY) {
		// TODO Auto-generated method stub
		xDestination = curX;
		yDestination = 720;
//		int timeSpent = 10;
		xSpeed = (xDestination - curX)/timeSpent;
		ySpeed = (yDestination - curY)/timeSpent;
		if (xSpeed == 0) {
			if(xDestination > curX) xSpeed = 1;
			else if (xDestination < curX) xSpeed = -1;
		}
		if (ySpeed == 0) {
			if (yDestination > curY) ySpeed = 1;
			else if(yDestination < curY) ySpeed = -1;
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
