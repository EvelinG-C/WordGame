package wordgamedefault;

import javax.swing.JOptionPane;

public class Hosts extends Person{
	
	// Constructors
	public Hosts(String firstName)
	{
		super(firstName);
	}
	
	public Hosts(String firstName, String lastName)
	{
		super(firstName, lastName);
	}
	
	// Assigns the gamePhrase
	public void setGamePhrase(String phrase)
	{
		Phrases.gamePhrase = phrase;
	}
	
	// Method that checks if the player wants to play again
	public boolean playAgain(Boolean didWin)
	{
		int response = GUI.restartPane.showConfirmDialog(null,"Would you like to play again?", "Confirm Option",JOptionPane.YES_NO_OPTION);
		
		if (response != JOptionPane.YES_OPTION)
		{
			String thanksString = "Thank you for playing!\n";
			GUI.dialogueArea.append(thanksString);
		}
		
		return response == JOptionPane.YES_OPTION;
	}
}
