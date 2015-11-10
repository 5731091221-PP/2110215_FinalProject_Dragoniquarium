package logic;

import java.awt.Graphics2D;

import render.IRenderable;
//import lib.ConfigurableOption;
import logic.RandomUtility;;

public abstract class TargetObject implements IRenderable{

	protected int x,y,z = 0;
	protected int radius;
	protected boolean destroyed = false;
	protected int speed = 0;
	protected int movingType;
	
	protected int xDestination;
	protected int yDestination;
	
	protected boolean isPointerOver = false;

	
	
	public TargetObject(int z, int radius, int speed, int movingType) {
		super();
		this.z = z;
		this.radius = radius;
		this.speed = speed;
		this.movingType = movingType;
		
		//TODO - generate first destination - maybe in subclass
		//TODO - how to fade in ?
		
//		this.x = movingParameter[0];
//		this.y = movingParameter[1];
		
	}
	
	public abstract void generateMovingDestination();
	
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
