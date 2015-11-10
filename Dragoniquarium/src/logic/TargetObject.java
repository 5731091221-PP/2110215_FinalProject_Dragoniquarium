package logic;

import java.awt.Graphics2D;

import render.IRenderable;
//import lib.ConfigurableOption;
import logic.RandomUtility;;

public abstract class TargetObject implements IRenderable{

	protected int x,y,z = 0;
	protected int radius;
	protected boolean destroyed = false;
	protected int xSpeed ,ySpeed = 0;
	protected int movingType;
	// 1 is normal, 2 is vertical only, 3 is horizontal only
	
	protected int xDestination;
	protected int yDestination;
	
	protected boolean isPointerOver = false;

	protected int currentAction;
	
	public TargetObject(int radius, int movingType, int z) {
		super();
		this.z = z;
		this.radius = radius;
//		this.xSpeed = xSpeed;
//		this.ySpeed = ySpeed;
		this.movingType = movingType;
		
		//TODO - generate first destination - maybe in subclass
		//TODO - how to fade in ?
		
//		this.x = movingParameter[0];
//		this.y = movingParameter[1];
		
	}
	
	public abstract void generateMovingDestination(int curX, int curY);
//	public abstract void move();
	public abstract void reachDestination();
	
	public boolean contains(int x,int y){
		return Math.hypot(x-this.x, y-this.y) <= radius+6;
	}
	
	public void setPointerOver(boolean isPointerOver) {
		this.isPointerOver = isPointerOver;
	}
	
	
	
	public void move(){
		if(destroyed) return;
		
		if(contains(xDestination,yDestination)) {
			//TODO - generate new destination
			reachDestination();
		}
		
		// TODO - check out of screen
		// TODO - how object create ?
		// TODO - how object fade in ?

	}

	@Override
	public int getZ() {
		return this.z;
	}

	@Override
	public boolean isVisible() {
		return !destroyed;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
		
}
