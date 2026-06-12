package wordgamedefault;

import java.util.Scanner;

public class Turn {
	
	public Boolean takeTurn(Players player, Hosts host)
	{
		int guess;
		int incAmount = 10;
		int decAmount = 5;
		
		System.out.print(host.getFirstName() + " says 'Enter a number between 0 and 100: '");
		
		Scanner scnr = new Scanner(System.in);
		while(!scnr.hasNextInt())
		{
			System.out.print(host.getFirstName() + " says 'Please insert a number: '");
			scnr.next();
		}
		guess = scnr.nextInt();
		scnr.close();
		
		Numbers num = new Numbers();
		
		if (num.compareNumber(guess) == true)
		{
			num.compareNumber(guess);
			
			int newAmount = player.getCurrentMoney() + incAmount;
			
			player.setCurrentMoney(newAmount);
			
			player.toString();
			
			return true;
		}
		else
		{
			num.compareNumber(guess);
			
			int newAmount = player.getCurrentMoney() - decAmount;
			
			player.setCurrentMoney(newAmount);
			
			player.toString();
			
			return false;
		}
	}
}
