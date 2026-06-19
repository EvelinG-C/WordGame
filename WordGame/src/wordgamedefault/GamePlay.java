package wordgamedefault;

import java.util.Scanner;

public class GamePlay {
	
	private static Players player;

	public static void main(String[] args) 
	{
		Players[] currentPlayers = new Players[3];
		
		Hosts host = new Hosts("Garfield");
		host.randomizeNum();
		
		System.out.println(host.getFirstName() + " says 'Welcome to the Random Word Game!'");
		System.out.println();
		Scanner scnr = new Scanner(System.in);
		
		for (int i = 0; i < currentPlayers.length; i++)
		{
			int playerNum = i + 1;
			System.out.println("----------------------------------");
			System.out.print(host.getFirstName() + " says 'Player " + playerNum + ": Please enter your first name:' ");
			String firstName = scnr.next();
			
			System.out.print(host.getFirstName() + " says 'Would you like to enter your last name?' (yes or no) " );
			String answer = scnr.next();
			
			String lastName;
			if (answer.toLowerCase().equals("yes"))
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
			Boolean didWin = false;
			while(didWin == false)
			{
				for (Players person : currentPlayers)
				{
					if (didWin == true)
					{
						continue;
					}
					didWin = turn.takeTurn(person, host, scnr);
				}
			}
			
			System.out.println("----------------------------------");
			System.out.print(host.getFirstName() + " says 'Would you like to restart the game?' (yes or no) ");
			decision = scnr.next();
			System.out.println();
			if (decision.toLowerCase().equals("yes"))
			{
				host.randomizeNum();
				restartGame = true;
			}
			else
			{
				restartGame = false;
			}
		}
		
		System.out.println("----------------------------------");
		System.out.println(host.getFirstName() + " says 'Thanks for playing!'");
		
		scnr.close();

	}

}
