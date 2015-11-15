package logic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import render.RenderableHolder;
import render.Resource;

public class GameLogic {
	
	private static GameLogic instance;
	protected List <TargetObject> onScreenObject =  new CopyOnWriteArrayList<TargetObject>();
	
	private static final int SPAWN_DELAY = 100;
	private int spawnDelayCounter ;
	
	public static boolean enamyOnScreen = false;
	
	public static GameLogic getInstance() {
		return instance;
	}
	
	/*
	 * Reserved z
	 * MIN_VALUE : background
	 * MAX_VALUE-1 : animation effect
	 * MAX_VALUE : player's status
	 */
	private static int zCounter = Integer.MIN_VALUE+1;
	

	public GameLogic() {
//		player = new Player(); 
//		playerStatus = new PlayerStatus();
//		RenderableHolder.getInstance().add(player);
//		RenderableHolder.getInstance().add(playerStatus);
		spawnDelayCounter = 0;
		
	}
	
	public void logicUpdate() {
		
//		player.update();
//		System.out.println("logic update");
		
		for(TargetObject obj : onScreenObject) {
			if (obj.isDestroyed()) {
				onScreenObject.remove(obj);
				RenderableHolder.getInstance().getRenderableList().remove(obj);
			}
		}
		
		// lay Egg
		for(TargetObject obj : onScreenObject) {
			if( obj instanceof Dragon1 ) {
				Dragon1 temp = (Dragon1)obj; 
				if(temp.layingEgg) {
					temp.layingEgg = false;
					createEgg(temp.x, temp.y);
				}
			}
		}
		
		for(TargetObject obj : onScreenObject) {
			obj.move();
			if (obj.y >= 900 ) {
				obj.destroyed = true;
			}
		}
		// create egg
		// attack
		// update fire ball
		spawnDelayCounter++;
		if (spawnDelayCounter >= SPAWN_DELAY ) {
			spawnDelayCounter = 0;
//			TargetObject egg = new Egg1(RandomUtility.random(0, 1000), 600, 10);
			TargetObject egg = new Dragon1(RandomUtility.random(300, 700), 0, 10);
			onScreenObject.add(egg);
			RenderableHolder.getInstance().add(egg);
		}
		
	}
	
	public void createEgg(double x, double y) {
		TargetObject egg = new Egg1((int)x, (int)y, zCounter);
		onScreenObject.add(egg);
		RenderableHolder.getInstance().add(egg);
	}
	
}
