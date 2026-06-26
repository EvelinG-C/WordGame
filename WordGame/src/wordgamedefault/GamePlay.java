package wordgamedefault;

import java.util.Scanner;

public class GamePlay {
	
	private static Players player;

	public static void main(String[] args) 
	{
		Phrases phraseClass = new Phrases();
		Players[] currentPlayers = new Players[3];
		
		Hosts host = new Hosts("Garfield");
		
		System.out.println(host.getFirstName() + " says 'I will now set the word for this game'");
		System.out.print("'Set the phrase with no special characters or digits:' ");
		Scanner scnr = new Scanner(System.in);
		
		// add meaures so that no digits or special characters are added
		String phrase = scnr.nextLine();
		
		boolean hasLettersSpecial = false;
		
		while(hasLettersSpecial == false)
		{
			if (phrase.matches(".*[^A-Za-z ].*"))
			{
				System.out.print("'Please enter a new phrase:' ");
				phrase = scnr.nextLine();
			}
			else
			{
				hasLettersSpecial = true;
			}
		}

		System.out.println();
		host.setGamePhrase(phrase);
		phraseClass.setPlayingPhrase(phrase);
		
		System.out.println(host.getFirstName() + " says 'Welcome to the Random Word Game!'");
		System.out.println();
		
		for (int i = 0; i < currentPlayers.length; i++)
		{
			int playerNum = i + 1;
			System.out.println("----------------------------------");
			System.out.print(host.getFirstName() + " says 'Player " + playerNum + ": Please enter your first name:' ");
			String firstName = scnr.next();
			
			System.out.print(host.getFirstName() + " says 'Would you like to enter your last name?' (yes or no) " );
			String answer = scnr.next();
			
			String lastName;
			if (answer.equalsIgnoreCase("yes"))
			{
				System.out.print(host.getFirstName() + " says 'What is your last name?' ");
				lastName = scnr.next();
				currentPlayers[i] = new Players(firstName, lastName);
				System.out.println();
			}
			else
			{
				currentPlayers[i] = new Players(firstName);
				System.out.println();
			}
		}
		
		Turn turn = new Turn();
		
		Boolean restartGame = true;
		System.out.println("----------------------------------");
		System.out.print(host.getFirstName() + " says 'Would you like to start the game?' (yes or no) ");
		String decision = scnr.next();
		System.out.println();
		if (decision.toLowerCase().equals("yes"))
		{
			restartGame = true;
		}
		else
		{
			restartGame = false;
		}
		
		while (restartGame == true)
		{
			boolean didtWin = false;
			while(didtWin == false)
			{
				for (Players person : currentPlayers)
				{
					if (didtWin == true)
					{
						continue;
					}
					
					didtWin = turn.takeTurn(person, host, scnr, phraseClass);
				}
			}
			
			System.out.println("----------------------------------");
			
			restartGame = host.playAgain(scnr);
			
			if(restartGame)
			{
				System.out.println(host.getFirstName() + " says 'I will insert the new phrase.'");
				System.out.print("'The new phrase is:' ");
				scnr.nextLine();
				String newPhrase = scnr.nextLine();
				host.setGamePhrase(newPhrase);
				phraseClass.setPlayingPhrase(newPhrase);
				System.out.println();
			}
			else
			{
				restartGame = false;
				System.out.println();
			}
		}
		
		System.out.println("----------------------------------");
		System.out.println(host.getFirstName() + " says 'Thanks for playing!'");
		
		scnr.close();	
	}

}
