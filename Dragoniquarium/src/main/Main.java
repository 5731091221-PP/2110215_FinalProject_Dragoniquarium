package main;

import javax.swing.JComponent;
import javax.swing.JFrame;

import render.GameScreen1;
import logic.GameLogic;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Catch A Fruit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameLogic logic = new GameLogic();
		JComponent gameScreen = new GameScreen1();
		
		frame.getContentPane().add(gameScreen);
		frame.setVisible(true);
		frame.pack();
		gameScreen.requestFocus();
		//aaaaa
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameScreen.repaint();
			logic.logicUpdate();
		}
	}

}
