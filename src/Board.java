
/**
* @author icreatedanaccount
*/

//IMPORTATIONS DES LIBRAIRIES
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private int NBR_OF_ROW = 25;
	private int NBR_OF_COL = 25;
	private JLabel[][] cellBoard;
	private LetterThread[][] threads;
	private ThreadGroup tgroup;
	private ArrayList<String> aWord;
	private static final int FONT_SIZE = 12;
	
	public Board() {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(NBR_OF_ROW, NBR_OF_COL));
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		cellBoard = new JLabel[NBR_OF_ROW][NBR_OF_COL];

		threads = new LetterThread[NBR_OF_ROW][NBR_OF_COL];

		tgroup = new ThreadGroup("threadgroup1");

		aWord = new ArrayList<String>();
		aWord.add(" ");
		aWord.add("K");
		aWord.add("e");
		aWord.add("e");
		aWord.add("p");
		aWord.add(" ");
		aWord.add("l");
		aWord.add("o");
		aWord.add("o");
		aWord.add("k");
		aWord.add("i");
		aWord.add("n");
		aWord.add("g");
		aWord.add(".");
		aWord.add(" ");

		for (int row = 0; row < NBR_OF_ROW; row++) {
			for (int column = 0; column < NBR_OF_COL; column++) {
				cellBoard[row][column] = new JLabel("");
				cellBoard[row][column].setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
				cellBoard[row][column].setForeground(Color.white);
				add(cellBoard[row][column]);
				threads[row][column] = new LetterThread(tgroup, "" + row + ", " + column, this, row, column);
			}
		}
	}

	public synchronized void refreshOne(int x, int y, String a) {
		cellBoard[x][y].setText("" + a);

	}

	// MÉTHODE REFRESH()
	/**
	 * Cette méthode rafraichit les cellules de la matrice.
	 */
	public void startScroll() {

		for (int row = 0; row < NBR_OF_ROW; row++) {
			for (int column = 0; column < NBR_OF_COL; column++) {
				threads[row][column].start();
			}
		}

		long start = System.nanoTime();
		double timeInSeconds = 0;
		long time = System.nanoTime() - start;
		while (timeInSeconds < 1000) {
			time = System.nanoTime() - start;
			timeInSeconds = time / 1e9;
			if ((timeInSeconds % 10) == 0) {
				System.out.println("Time elapsed : " + timeInSeconds + " sec");
				ShowMessage();
			}
		}
	}

	public void ShowMessage() {
		int displayColStart = NBR_OF_COL/2 - aWord.size()/2;
		int displayRowStart = NBR_OF_ROW/2;
		for (int column = displayColStart; column < displayColStart + aWord.size(); column++) {
			threads[displayRowStart][column].stop();
			threads[displayRowStart][column].setCharacterSpecial("" + aWord.get(column - displayColStart));
		}

		waitTime(0.5);
		for (int column = displayColStart; column < displayColStart + aWord.size(); column++) {
			threads[displayRowStart][column] = new LetterThread(tgroup, "" + displayRowStart + ", " + column, this, displayRowStart, column);
			threads[displayRowStart][column].start();
			waitTime(0.1);
		}
	}

	private void waitTime(double mili) {

		long start = System.nanoTime();
		double timeInSeconds = 0;
		long time = System.nanoTime() - start;
		while (timeInSeconds < mili) {
			time = System.nanoTime() - start;
			timeInSeconds = time / 1e9;
		}
	}

	// MÉTHODE printBoard()
	public void printBoard() {
		for (int row = 0; row < NBR_OF_ROW; row++) {
			for (int column = 0; column < NBR_OF_COL; column++) {
				System.out.print(cellBoard[row][column].toString());
			}
			System.out.println();
		}
	}
}
