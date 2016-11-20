/**
* @author icreatedanaccount
*/
import java.util.Random;

public class LetterThread extends Thread {

	private String character;
	private Random r;
	private int refreshTime;
	private Board b;
	private int x, y;

	public LetterThread(ThreadGroup p, String name, Board b, int x, int y) {
		super(p, name);
		r = new Random();
		this.b = b;
		this.x = x;
		this.y = y;
		refreshTime = 5;
	}

	public void run() {

		while (true) {
			refreshTime = 10 + (int) (Math.random() * ((250 - 10) + 1));
			setCharacter("" + (char) (r.nextInt(26) + 'a'));
			b.refreshOne(x, y, character);

			try {
				sleep(refreshTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getCharacter() {
		return character;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public void setCharacterSpecial(String character) {

		b.refreshOne(x, y, character);
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void restartScrolling() {
		run();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
