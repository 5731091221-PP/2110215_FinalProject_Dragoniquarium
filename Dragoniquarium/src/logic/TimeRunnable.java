package logic;

public class TimeRunnable implements Runnable {

	private PlayerStatus player;
	
	public TimeRunnable(PlayerStatus player) {
		super();
		this.player = player;
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				if(player.isPause()) {
					synchronized (player) {
						player.wait();
					}
				}
				Thread.sleep(PlayerStatus.TIME_CLOCK);
				synchronized (player) {
					player.increaseTimeSpent(1);
				}
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}