package render;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GameAnimation {

	private BufferedImage image = null;
	private int frameCount,frameDelay;
	private int row,column;
	private int currentFrame,frameDelayCount;
	private int currentRow, currentColumn;
	private int frameWidth,frameHeight;
	private boolean playing = false;
	private boolean oneTime = false;
	private int x, y;
	
	public GameAnimation(BufferedImage image,int frameCount, int row, int column,int frameDelay){
		this.image = image;
		this.frameCount = frameCount;
		this.row = row;
		this.column = column;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.currentRow = 0;
		this.currentColumn = 0;
		this.frameDelayCount = 0;
		if (image != null) {
			this.frameWidth = image.getWidth()/column;
			this.frameHeight = image.getHeight()/row;
		} else {
			this.frameWidth = 0;
			this.frameHeight = 0;
		}
	}
	
	public GameAnimation(BufferedImage image,int frameCount, int row, int column,
							int frameDelay, int x, int y){
		this.image = image;
		this.frameCount = frameCount;
		this.row = row;
		this.column = column;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.currentRow = 0;
		this.currentColumn = 0;
		this.frameDelayCount = 0;
		this.x = x;
		this.y = y;
		this.oneTime = true;
		if (image != null) {
			this.frameWidth = image.getWidth()/column;
			this.frameHeight = image.getHeight()/row;
		} else {
			this.frameWidth = 0;
			this.frameHeight = 0;
		}
	}
	
	protected void centerAnimationAt(int x,int y){
		this.x = x-frameWidth/2;
		this.y = y-frameHeight/2;
	}
	
	public void play(){
		currentFrame = 0;
		currentRow = 0;
		currentColumn = 0;
		playing = true;
	}
	
	public void stop(){
		currentFrame = 0;
		currentRow = 0;
		currentColumn = 0;
		playing = false;
	}
	
	public void updateAnimation(){
		if(!playing) {
			return ;
		}
		if(frameDelayCount > 0 ) {
			frameDelayCount--;
			return ;
		}
		
		frameDelayCount = frameDelay;
		currentFrame++;
		currentRow++;
		
		if(currentRow == row) {
			currentRow = 0;
			currentColumn++;
		}
		
		if( currentFrame == frameCount) {
			currentFrame = 0;
			currentColumn = 0;
			currentRow = 0;
			if(oneTime) {
				stop();
			}
		}
	}

	public void draw(Graphics2D g2, int x, int y, boolean reflex) {
		if(image == null) {
			return ;
		}
		
		BufferedImage currentAnimation = image.getSubimage(currentColumn * frameWidth, 
											currentRow * frameHeight, frameWidth, frameHeight);
		if(reflex) {
			// Flip the image horizontally
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-currentAnimation.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			currentAnimation = op.filter(currentAnimation, null);
		}
		g2.drawImage(currentAnimation, null, x, y);
	}
	
	public void draw(Graphics2D g2) {
		if(image == null) {
			return ;
		}
		
		BufferedImage currentAnimation = image.getSubimage(currentColumn * frameWidth, 
				currentRow * frameHeight, frameWidth, frameHeight);
		
		g2.drawImage(currentAnimation, null, x, y);
	}
	
	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public int getFrameCount() {
		return frameCount;
	}

	public int getFrameDelay() {
		return frameDelay;
	}


	public void setFrameDelay(int frameDelay) {
		this.frameDelay = frameDelay;
	}


	public int getCurrentFrame() {
		return currentFrame;
	}


	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
		this.currentColumn = currentFrame/row;
		this.currentRow = currentFrame%row;
	}


	public int getFrameDelayCount() {
		return frameDelayCount;
	}


	public void setFrameDelayCount(int frameDelayCount) {
		this.frameDelayCount = frameDelayCount;
	}

	public int getFrameWidth() {
		return frameWidth;
	}


	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}


	public int getFrameHeight() {
		return frameHeight;
	}


	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}


	public boolean isPlaying() {
		return playing;
	}


	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
