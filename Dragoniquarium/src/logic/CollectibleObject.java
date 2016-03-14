package logic;

import java.awt.AlphaComposite;

public abstract class CollectibleObject extends TargetObject{
	
	protected int xBucket, yBucket;
	protected int reward;
	protected int timeSpent = 300;
	protected boolean movingUp;
	
	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	
	public CollectibleObject(double x, double y, int radius, int z, int reward) {
		super(x, y, radius, z);
		this.reward = reward;
		this.movingUp = true;
		generateMovingDestination(this.x, this.y);
	}
	
	@Override
	public void generateMovingDestination(double curX, double curY) {
		if(movingUp) {
			xDestination = curX;
			yDestination = RandomUtility.random(200, 202);
		} else {
			yDestination = 800;
		}
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
		if(destroyed) {
			return;
		}
		collect(player);
		destroyed = true;
	}
	
	public abstract void collect(PlayerStatus player);
}
