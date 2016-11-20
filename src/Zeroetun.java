/**
* @author icreatedanaccount
*/
import java.awt.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Zeroetun extends JFrame {

	private static JPanel panel = new JPanel(null);
	private static Random random = new Random();
	private Board aBoard;

	public Zeroetun() {
		this.add(panel);
		panel.setBackground(Color.BLACK);
		aBoard = new Board();
		add(aBoard, BorderLayout.CENTER);
	}

	public void scroll() {

		aBoard.startScroll();
	}

	public static void main(String[] args) {
		Zeroetun frame = new Zeroetun();
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(300, 200));
		frame.setLocationRelativeTo(null);
		frame.setTitle("Zeroetun");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.scroll();
	}
}
