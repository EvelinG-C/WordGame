package wordgamedefault;

import java.util.Scanner;

public class GamePlay {
	
	private static Players player;

	public static void main(String[] args) 
	{
		
		Hosts host = new Hosts("Garfield");
		host.randomizeNum();
		
		System.out.println(host.getFirstName() + " says 'Welcome to the Random Word Game!'");
		System.out.print(host.getFirstName() + " says 'Please enter your first name:' ");
		Scanner scnr = new Scanner(System.in);
		String firstName = scnr.next();
		
		System.out.print(host.getFirstName() + " says 'Would you like to enter your last name?' (yes or no) " );
		String answer = scnr.next();
		
		String lastName;
		if (answer.toLowerCase().equals("yes"))
		{
			System.out.print(host.getFirstName() + " says 'What is your last name?' ");
			lastName = scnr.next();
			player = new Players(firstName, lastName);
		}
		else
		{
			player = new Players(firstName);
		}
		
		Turn turn = new Turn();
		
		Boolean restartGame = true;
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
			
			while(turn.takeTurn(player, host, scnr) == false)
			{
				continue;
			}
			
			System.out.print(host.getFirstName() + " says 'Would you like to restart the game?' ");
			decision = scnr.next();
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
		
		System.out.println(host.getFirstName() + " says 'Thanks for playing!'");
		
		scnr.close();

	}

}
