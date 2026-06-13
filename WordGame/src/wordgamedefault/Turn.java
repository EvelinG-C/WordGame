package wordgamedefault;

import java.util.Scanner;

public class Turn {
	
	public Boolean takeTurn(Players player, Hosts host, Scanner input)
	{
		int guess;
		int incAmount = 10;
		int decAmount = 5;
		
		System.out.println("----------------------------------");
		System.out.print(host.getFirstName() + " says 'Enter a number between 0 and 100:' ");
		
		while(!input.hasNextInt())
		{
			System.out.print(host.getFirstName() + " says 'Please insert a number:' ");
			input.next();
		}
		guess = input.nextInt();
		
		Numbers num = new Numbers();
		
		if (num.compareNumber(guess))
		{
			int newAmount = player.getCurrentMoney() + incAmount;
			
			player.setCurrentMoney(newAmount);
			
			System.out.println(player.toString());
			System.out.println();
			
			return true;
		}
		else
		{
			int newAmount = player.getCurrentMoney() - decAmount;
	
			player.setCurrentMoney(newAmount);
			
			System.out.println(player.toString());
			System.out.println();
			
			return false;
		}
	}
}
