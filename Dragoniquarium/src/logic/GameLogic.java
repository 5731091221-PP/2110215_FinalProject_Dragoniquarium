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
	
	public static GameLogic getInstance() {
		return instance;
	}

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
		
		
		for(TargetObject obj : onScreenObject) {
			obj.move();
			if (obj.y >= 900 ) {
				obj.destroyed = true;
			}
		}
		
		spawnDelayCounter++;
		if (spawnDelayCounter >= SPAWN_DELAY ) {
			spawnDelayCounter = 0;
//			TargetObject egg = new Egg1(RandomUtility.random(0, 1000), 600, 10);
			TargetObject egg = new Dragon1(RandomUtility.random(0, 1000), 200, 10);
			onScreenObject.add(egg);
			RenderableHolder.getInstance().add(egg);
		}
		
	}
	
	
}
