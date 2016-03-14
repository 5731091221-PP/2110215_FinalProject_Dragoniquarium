package main;

import input.InputUtility;

import javax.swing.JComponent;

import render.GameScreen1;
import render.GameTitle;
import render.GameWindow;
import logic.GameLogic;

public class Main {

	private static GameTitle titleScene;
	private static GameScreen1 gameScreen;
	private static GameWindow gameWindow;
	private static JComponent nextScene = null;
	
	public static void main(String[] args) {
		
		titleScene = new GameTitle();
		
		GameLogic logic = new GameLogic();
		gameScreen = new GameScreen1();
		
		gameWindow = new GameWindow(titleScene);
		
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameWindow.getCurrentScene().repaint();
			if(gameWindow.getCurrentScene() instanceof GameScreen1){
				logic.logicUpdate();
				InputUtility.postUpdate();
			}
			if(nextScene != null){
				if(gameWindow.getCurrentScene() instanceof GameScreen1)
					logic.onExit();
				gameWindow.switchScene(nextScene);
				if(nextScene instanceof GameScreen1)
					logic.onStart();
				nextScene = null;
			}
		}
	}
	
	public static void goToTitle(){
		nextScene = titleScene;
	}
	
	public static void newGame(){
		nextScene = gameScreen;
		if(gameScreen == null) System.out.println("null");
	}

}
