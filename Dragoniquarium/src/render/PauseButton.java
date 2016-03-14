package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import logic.PlayerStatus;

public class PauseButton extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage pauseImage;
	private BufferedImage playImage;
	private boolean isPointerOver = false;
	public Color color = Color.RED;
	
	private static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	private static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
	public PauseButton(int x, int y) {
		pauseImage = DrawingUtility.getButton("pause");
		this.setBounds(x, y, pauseImage.getWidth(), pauseImage.getHeight());
		
		playImage =  DrawingUtility.getButton("play");
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				color = Color.RED;
				if(PlayerStatus.instance.isPause()) {
					PlayerStatus.instance.setPause(false);
					synchronized (PlayerStatus.instance) {
						PlayerStatus.instance.notifyAll();
					}
				} else {
					PlayerStatus.instance.setPause(true);
				}
				isPointerOver = false;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				isPointerOver = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				isPointerOver = true;
			}
		});
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setBackground(color);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		
		
		if(PlayerStatus.instance != null && PlayerStatus.instance.isPause()) {
			g2d.drawImage(playImage, null, 0, 0);
		} else {
			g2d.drawImage(pauseImage, null, 0, 0);
		}
		
		if(isPointerOver) {
			g2d.setComposite(transcluentWhite);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			g2d.setComposite(opaque);
		}
		
	}
	
	
}