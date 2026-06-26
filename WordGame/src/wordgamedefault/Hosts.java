package wordgamedefault;

import java.util.Scanner;

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
	public boolean playAgain(Scanner input)
	{
		System.out.print(getFirstName() + " says 'Would you like to play again?' (yes or no) ");
		
		String playAgain = input.next();
		
		if (playAgain.equalsIgnoreCase("yes"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
