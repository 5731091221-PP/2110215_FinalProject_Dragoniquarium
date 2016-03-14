package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import main.Main;
import render.DrawingUtility;
import render.GameAnimation;
import input.InputUtility;
import render.RenderableHolder;

public class GameLogic {
	
	private static GameLogic instance;
	private PlayerStatus player;
	protected List <TargetObject> onScreenObject =  new CopyOnWriteArrayList<TargetObject>();
	protected List <AttackObject> onScreenAttack = new CopyOnWriteArrayList<AttackObject>();
	protected List <Button> onScreenButton = new ArrayList<Button>();
	protected List <GameAnimation> onScreenAnimation = new CopyOnWriteArrayList<GameAnimation>();
	
	private int spawnDelayCounter ;
	
	public static boolean enemyOnScreen = false; 
	public TargetObject targetEnemy ; //MonsterObject
	
	private int spawnNumber = 0;
	private int[] spawnX = new int[5];
	private int[] spawnY = new int[5];
	private int[] spawnType = new int[5];
	
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
	}
	
	//Called before enter the game loop
	public synchronized void onStart(){
		player = new PlayerStatus();
		RenderableHolder.getInstance().add(player);
		createButton();
		spawnDelayCounter = 0;
		targetEnemy = null;
		enemyOnScreen = false;
		
		TargetObject dragon = new Dragon1(RandomUtility.random(300, 700), 0, zCounter);
		onScreenObject.add(dragon);
		RenderableHolder.getInstance().add(dragon);
		
		dragon = new Dragon1(RandomUtility.random(300, 700), 0, zCounter);
		onScreenObject.add(dragon);
		RenderableHolder.getInstance().add(dragon);
	}
	
	//Called after exit the game loop
	public synchronized void onExit(){
		onScreenObject.clear();
		onScreenAttack.clear();
		onScreenButton.clear();
		onScreenAnimation.clear();
		RenderableHolder.getInstance().clear();
		targetEnemy = null;
		enemyOnScreen = false;
		spawnNumber = 0;
	}
	
	public void logicUpdate() {
		
		if(player.isPause()){
			return;
		}
		
		// clear destroyed object
		for(TargetObject obj : onScreenObject) {
			if (obj.isDestroyed()) {
				onScreenObject.remove(obj);
				RenderableHolder.getInstance().getRenderableList().remove(obj);
			}
		}
		
		// clear destroy attack
		for(AttackObject obj : onScreenAttack) {
			if (obj.isDestroyed()) {
				onScreenAttack.remove(obj);
				RenderableHolder.getInstance().getRenderableList().remove(obj);
			}
		}
		
		// clear and update animation attack
		for(GameAnimation anim : onScreenAnimation) {
			if (!anim.isPlaying()) {
				onScreenAnimation.remove(anim);
				RenderableHolder.getInstance().getAnimationList().remove(anim);
			}
			anim.updateAnimation();
		}
		
		// check losing condition
		boolean lose = true;
		for(TargetObject obj : onScreenObject) {
			if(obj instanceof Dragon1) {
				lose = false;
				break;
			}
		}
		if(lose) {
			JOptionPane.showMessageDialog(null, "All Babigone is dead...", "Game Over", JOptionPane.DEFAULT_OPTION);
			Main.goToTitle();
		}
		
		
		// check if any enemy on screen
		checkEnemyOnScreen();
		
		// move and attack
		for(TargetObject obj : onScreenObject) {
			obj.move();
			if(obj instanceof EnemyObject) {
				((EnemyObject)obj).attack(onScreenAttack, zCounter);
			} else if(obj instanceof Dragon2) {
				((Dragon2)obj).attack(onScreenAttack, targetEnemy, zCounter);
			} else if(obj instanceof Dragon4) {
				((Dragon4)obj).attack(onScreenAttack, targetEnemy, zCounter);
			} else if(obj instanceof Dragon5) {
				((Dragon5)obj).attack(onScreenAttack, targetEnemy, zCounter);
			}
			
		}
		
		dragon1CreateEgg();
		
		processAttack();
		
		processShootAndCollect();
		
		// push button
		Button targetButton = null;
		boolean click = false;
		if(input.InputUtility.isMouseLeftClicked() ){
			click = true;
		}
		
		targetButton = getButtonAt(InputUtility.getMouseX(), InputUtility.getMouseY());
		if(targetButton != null && click ){
			targetButton.click(onScreenObject, player, zCounter);
		}
		
		// spawn enemy
		spawnDelayCounter++;
		
		if(spawnDelayCounter == 24*50 || spawnDelayCounter == 53*50 || spawnDelayCounter == 82*50 ||
			spawnDelayCounter == 110*50 || spawnDelayCounter == 143*50 || spawnDelayCounter == 170*50 ||
			spawnDelayCounter == 174*50 || spawnDelayCounter == 179*50) {
			TargetObject newEnemy;
			for(int i=0;i<spawnNumber;i++) {
				if(spawnType[i] == 1) {
					newEnemy = new Enemy1(spawnX[i], spawnY[i], zCounter);
				} else {
					newEnemy = new Enemy2(spawnX[i], spawnY[i], zCounter);
				}
				onScreenObject.add(newEnemy);
				RenderableHolder.getInstance().add(newEnemy);
			}
		}
		spawnDelayCounter %= 179*50;
		
		createWarpHole();
		
		zCounter++;
		if(zCounter == Integer.MAX_VALUE-1){
			zCounter = Integer.MIN_VALUE+1;
		}
	}
	
	
	// end logic update
	private void createWarpHole() {
		GameAnimation anim;

		if( spawnDelayCounter == 24*50-50 || spawnDelayCounter == 53*50-50 ||
				spawnDelayCounter == 170*50-50) {
			spawnNumber = 1;
			spawnX[0] = RandomUtility.random(300, 1000);
			spawnY[0] = RandomUtility.random(200, 400);
			spawnType[0] = 1;
			anim = DrawingUtility.createWarppingAnimation(spawnX[0], spawnY[0]);
			onScreenAnimation.add(anim);
			RenderableHolder.getInstance().add(anim);
		} else if ( spawnDelayCounter == 82*50-50 || spawnDelayCounter == 110*50-50 || 
					spawnDelayCounter == 143*50-50 || spawnDelayCounter == 174*50-50) {
			spawnNumber = RandomUtility.random(1, 2);
			for(int i=0 ; i<spawnNumber ; i++) {
				spawnX[i] = RandomUtility.random(300, 1000);
				spawnY[i] = RandomUtility.random(200, 400);
				spawnType[i] = 1;
				anim = DrawingUtility.createWarppingAnimation(spawnX[i], spawnY[i]);
				onScreenAnimation.add(anim);
				RenderableHolder.getInstance().add(anim);
			}
		} else if( spawnDelayCounter == 179*50-50 ) {
			spawnNumber = 1;
			spawnX[0] = RandomUtility.random(300, 1000);
			spawnY[0] = RandomUtility.random(200, 400);
			spawnType[0] = 2;
			anim = DrawingUtility.createWarppingAnimation(spawnX[0], spawnY[0]);
			onScreenAnimation.add(anim);
			RenderableHolder.getInstance().add(anim);
		}
		
	}
	
	
	private void checkEnemyOnScreen() {
		if(targetEnemy != null && !targetEnemy.isDestroyed()) {
			return ;
		}
		targetEnemy = null;
		enemyOnScreen = false;
		for(TargetObject obj : onScreenObject) {
			if (obj instanceof EnemyObject) {
				targetEnemy = obj;
				enemyOnScreen = true;
				break ;
			}
		}
	}
	
	// lay Egg
	private void dragon1CreateEgg() {
		for(TargetObject obj : onScreenObject) {
			if( obj instanceof Dragon1 ) {
				Dragon1 temp = (Dragon1)obj; 
				if(temp.layingEgg) {
					temp.layingEgg = false;
					createEgg(temp.x, temp.y-obj.radius);
				}
			} 
		}
	}
	
	private void processAttack() {
		// attack objects do damage
		for(AttackObject obj : onScreenAttack) {
			obj.move();
			if(obj.attackType == 1) {
				for(TargetObject target : onScreenObject) {
					if(target.destroyed || target instanceof EnemyObject) continue;
					if(target instanceof DamageableObject && target.contains(obj.x, obj.y, obj.radius)) {
						((DamageableObject)target).hit(obj.getAttack());
						GameAnimation anim = DrawingUtility.createAttack1DestroyAt((int)obj.x, (int)obj.y);
						onScreenAnimation.add(anim);
						RenderableHolder.getInstance().add(anim);
						if(target instanceof Dragon3 || target instanceof Dragon4 || 
							target instanceof Dragon5 || target instanceof Dragon2) {
							obj.destroyed = true;
							break;
						}
					}
				}
				
			} else if(obj.attackType == 2 || obj.attackType == 3 || obj.attackType == 4) {
				for(TargetObject target : onScreenObject) {
					if(target.destroyed ) continue;
					if(target instanceof EnemyObject && target.contains(obj.x, obj.y, obj.radius)) {
						((DamageableObject)target).hit(obj.getAttack());
						obj.destroyed = true;
						GameAnimation anim;
						if(obj.attackType == 2) {
							anim = DrawingUtility.createAttack2DestroyAt((int)obj.x, (int)obj.y);
						} else if(obj.attackType == 3) {
							createEgg(obj.x, obj.y);
							anim = DrawingUtility.createAttack3DestroyAt((int)obj.x, (int)obj.y);
						} else {
							anim = DrawingUtility.createAttack4DestroyAt((int)obj.x, (int)obj.y);
						}
						
						onScreenAnimation.add(anim);
						RenderableHolder.getInstance().add(anim);
						break;
					}
				}
			} 
		}
	}
	
	private void processShootAndCollect() {
		// attack object type 1 is clicked
		// check shoot and grab
		TargetObject target = null;
		if(!player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())){
			boolean shoot = false;
			if(input.InputUtility.isMouseLeftClicked() ){
				shoot = true;
			}
			
			target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
			if(target != null && shoot ){
				
				if(target instanceof CollectibleObject)	{
					((CollectibleObject)target).grab(player);
				}
				else if(target instanceof AttackObject) {
					((AttackObject)target).hitByPlayer();
				}
				else if(target instanceof EnemyObject) {
					((EnemyObject)target).isChased(InputUtility.getMouseX(), InputUtility.getMouseY());
					((EnemyObject)target).hit(1);
				}
				
			} 
		}
	}
	
	private Button getButtonAt(int x, int y) {
		Button but = null;
		for(Button target : onScreenButton) {
			if(target.contains(x, y)) {
				but = target;
				but.setPointerOver(true);
				break;
			}else{
				target.setPointerOver(false);
			}
		}
		return but;
	}
	
	private TargetObject getTopMostTargetAt(int x,int y){
		TargetObject obj = null;
		// find attack first
		for(TargetObject target : onScreenAttack){
			if(target.contains(x, y)){
				if(obj != null){
					if(target.getZ() > obj.getZ()){
						obj.setPointerOver(false);
						obj = target;
						obj.setPointerOver(true);
					}
				}else{
					obj = target;
					obj.setPointerOver(true);
				}
			}else{
				target.setPointerOver(false);
			}
		}
		if(obj != null) {
			return obj;
		}
		// find other object
		for(TargetObject target : onScreenObject){
			if(target.contains(x, y)){
				if(obj != null){
					if(target.getZ() > obj.getZ()){
						obj.setPointerOver(false);
						obj = target;
						obj.setPointerOver(true);
					}
				}else{
					obj = target;
					obj.setPointerOver(true);
				}
			}else{
				target.setPointerOver(false);
			}
		}
		return obj;
	}
	
	public void createEgg(double x, double y) {
		TargetObject egg = new Egg1((int)x, (int)y);
		onScreenObject.add(egg);
		RenderableHolder.getInstance().add(egg);
	}
	
	private void createButton() {
		
		Button button ;
		button = new Button(5, 15, 432, 80, 80, 500);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
		
		button = new Button(4, 15, 330, 80, 80, 500);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
		
		button = new Button(3, 15, 228, 80, 80, 400);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
		
		button = new Button(2, 15, 126, 80, 80, 300);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
		
		button = new Button(1, 15, 27, 80, 80, 200);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
		
		// end game button
		button = new Button(6, 15, 540, 147, 52, 2000);
		onScreenButton.add(button);
		RenderableHolder.getInstance().add(button);
	}
}
