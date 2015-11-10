package logic;

//import lib.AudioUtility;

public abstract class CollectibleObject extends TargetObject{
	
	protected int xBucket, yBucket;
	protected int reward;
	
	public CollectibleObject(int radius, int movingType, int z, int reward) {
		super(radius, 2, z);
		// TODO Auto-generated constructor stub
		this.xSpeed = 5;
		this.ySpeed = 0;
		generateMovingDestination(x, y);
	}
	
	@Override
	public void generateMovingDestination(int curX, int curY) {
		// TODO Auto-generated method stub
		xDestination = 720;
		yDestination = y;
		
	}
	
	public void grab(PlayerStatus player){
		if(destroyed) return;
		
//		AudioUtility.playSound("collect");
		collect(player);
		destroyed = true;
	}
	
	public abstract void collect(PlayerStatus player);
}
