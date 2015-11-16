package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class GameScreen1 extends JComponent{

	private static final long serialVersionUID = -8381015030266312089L;

	public GameScreen1() {
		super();
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(1280, 700));
		setVisible(true);
		
		addListener();
	}
	
	private void addListener(){
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1) {
					InputUtility.setMouseLeftDown(false);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftTriggered(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				InputUtility.setMouseOnScreen(false);				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				InputUtility.setMouseOnScreen(true);
			}
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}
				
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(!InputUtility.getKeyPressed(e.getKeyCode())){
					
					InputUtility.setKeyPressed(e.getKeyCode(), true);
					InputUtility.setKeyTriggered(e.getKeyCode(), true);
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, 1280, 700);
		
		// draw background
		g2d.drawImage(DrawingUtility.bg, null, 0, 0);
		
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
