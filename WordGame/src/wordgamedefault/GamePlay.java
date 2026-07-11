package wordgamedefault;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class GamePlay {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI main = new GUI();
				main.show();
			}
		});
	}

}
