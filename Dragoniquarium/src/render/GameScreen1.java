package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

public class GameScreen1 extends JComponent{

	private static final long serialVersionUID = -8381015030266312089L;

	public GameScreen1() {
		super();
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(1024, 640));
		setVisible(true);
		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, 1024, 640);
		
		//Preventing thread interference
		synchronized(RenderableHolder.getInstance()){
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
//				System.out.println(entity.getZ());
				if(entity.isVisible()){
					entity.draw(g2d);
				}
			}
			
		}
//		System.out.println("-----");
		
	}
	
	

}
