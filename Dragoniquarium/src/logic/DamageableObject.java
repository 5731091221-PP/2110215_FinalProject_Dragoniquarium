package logic;

public abstract class DamageableObject extends TargetObject {
	
	protected int life;
	protected int defense;
	
	public DamageableObject(int radius, int movingType, int z, int life, int defense) {
		super(radius, movingType, z);
		// TODO Auto-generated constructor stub
		generateMovingDestination(x, y);
		
		
	}
	
	private void generateSpeed() {
		this.xSpeed = 5;
		this.ySpeed = 0;
		
		// TODO
	}

	@Override
	public void generateMovingDestination(int curX, int curY) {
		// TODO check start and end point
		if (movingType == 1) {
			xDestination = RandomUtility.random(start, end);
			yDestination = RandomUtility.random(start, end);
		} else if (movingType == 3) {
			xDestination = 700;
			yDestination = RandomUtility.random(start, end);
		}
		
	}

	@Override
	public void reachDestination() {
		// TODO Auto-generated method stub
		generateMovingDestination(x, y);
		
		
	}
	
	
	
}
