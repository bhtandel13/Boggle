import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
   Displays the current time once every second. - done
   reusable code
 */
public class GameTimer
{
	private Timer t;
	private int timeRemaining;
	private final int DELAY = 1000; // milliseconds between timer ticks
	
	public GameTimer(int startTime) {//can use this timer for multiple things
		timeRemaining = startTime;

		class CurrentTime implements ActionListener//inner class
		{
			public void actionPerformed(ActionEvent event)
			{
				timeRemaining--;
				if(timeRemaining <= 0) {
					t.stop();
				}
			}
		}
		CurrentTime listener = new CurrentTime();
		t = new Timer(DELAY, listener);
		t.start();
	}
	
	public int getTimeRemaining() {
		return timeRemaining;
	}
}