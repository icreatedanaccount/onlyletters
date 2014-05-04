
 /**
 * @author Pier-Luc Boudreau
 * Numero d'etudiant : 7011066
 * Cours : ITI 1521
 * Travail pratique : 2
 * Question : 2
 * DESCRIPTION DE LA CLASSE : 
 * Cette classe Board est un type spécialisé de JPanel qui servira de grille pour les m x n cellules du jeu
 * (objets de la classe Cell). La classe implémente la logique du jeu. L'objet Board est le gestionnaire des
 * événements générés par les objets Cell du jeu.
 *
 */

//IMPORTATIONS DES LIBRAIRIES
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Board extends JPanel{
  
  
  private int NBR_OF_ROW = 25;
  private int NBR_OF_COL = 25;
  private JLabel[][] cellBoard;
  private LetterThread[][] threads;
  private ThreadGroup tgroup;
  private boolean isON;
  private char character;
  private String[] aWord; 
  private static final int FONT_SIZE = 12;
  private Random r = new Random();
  
  
  public Board(){
    setBackground(Color.BLACK);
    setLayout(new GridLayout(NBR_OF_ROW,NBR_OF_COL));
    setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    
    cellBoard = new JLabel[NBR_OF_ROW][NBR_OF_COL];
    
    threads = new LetterThread[NBR_OF_ROW][NBR_OF_COL];
    
    tgroup = new ThreadGroup("threadgroup1");
    
    aWord = new String[18];
    aWord[0] = " ";
    aWord[1] = "I";
    aWord[2] = " ";
    aWord[3] = "w";
    aWord[4] = "i";
    aWord[5] = "l";
    aWord[6] = "l";
    aWord[7] = " ";
    aWord[8] = "f";
    aWord[9] = "i";
    aWord[10] = "n";
    aWord[11] = "d";
    aWord[12] = " ";
    aWord[13] = "y";
    aWord[14] = "o";
    aWord[15] = "u";
    aWord[16] = ".";
    aWord[17] = " ";

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
  
  
  public synchronized void refreshOne(int x, int y, String a){
	  cellBoard[x][y].setText(""+a);
	  
  }
  //MÉTHODE REFRESH()
   /**Cette méthode rafraichit les cellules de la matrice.
     *
     */
  public void startScroll(){
	  
	    for (int row = 0; row < NBR_OF_ROW; row++) {
	        for (int column = 0; column < NBR_OF_COL; column++) {
	          threads[row][column].start();
	        }
	      }
	    
	 long start = System.nanoTime();
	 double timeInSeconds = 0;
	 long time = System.nanoTime() - start;
	 while(timeInSeconds < 1000){
		   time = System.nanoTime() - start;
		  timeInSeconds = time/1e9;
		  if((timeInSeconds % 10) == 0){
			  System.out.println("Time elapsed : " + timeInSeconds + " sec");
			  ShowMessage();
		  }
	 }

   
  }
  
  public void ShowMessage(){
	    for (int column = 4; column <  4 + aWord.length; column++) {
	    	threads[12][column].stop();
	    	threads[12][column].setCharacterSpecial(""+ aWord[column-4]);
	      }
	    
	    	waitTime(0.5);
		 
		 for (int column = 4; column <  4 + aWord.length; column++) {
			 
			    LetterThread temp = threads[12][column];
		    	threads[12][column] = new LetterThread(tgroup, "" + 12 + ", " + column, this, 12, column);
		    	threads[12][column].start();
		    	waitTime(0.1);
		      }
	    
  }
  
  private void waitTime(double mili){
	    
		 long start = System.nanoTime();
		 double timeInSeconds = 0;
		 long time = System.nanoTime() - start;
		 while(timeInSeconds < mili){
			  time = System.nanoTime() - start;
			  timeInSeconds = time/1e9;
		 }
	  
  }
 
  
   //MÉTHODE printBoard()
  public void printBoard(){
    for (int row = 0; row < NBR_OF_ROW; row++) {
      for (int column = 0; column < NBR_OF_COL; column++) {
        System.out.print(cellBoard[row][column].toString());
      }
      System.out.println();
    }
    
  }
  
  
}

