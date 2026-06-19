package wordgamedefault;

import java.util.Random;
import java.util.Scanner;

public class Turn {
	
	public Boolean takeTurn(Players player, Hosts host, Scanner input)
	{
		Numbers num = new Numbers();
		Random randNum = new Random();
		Money money = new Money();
		Physical phys = new Physical();
		
		int guess;
		int giftNumber;
		
		System.out.println("----------------------------------");
		
		giftNumber = randNum.nextInt(0,2);
		
		if (giftNumber == 0)
		{
			System.out.println(player.getFirstName() + ", 'You are trying to win money!'");
		}
		else
		{
			System.out.println(player.getFirstName() + ", 'You are trying to win a gift!'");
		}
		
		System.out.print(host.getFirstName() + " says 'Enter a number between 0 and 100:' ");
		
		while(!input.hasNextInt())
		{
			System.out.print(host.getFirstName() + " says 'Please insert a number:' ");
			input.next();
		}
		
		guess = input.nextInt();
		
		Boolean trueOrFalse = num.compareNumber(guess);
		
		if (giftNumber == 0)
		{
			int newAmount = money.displayWinnings(player, trueOrFalse);
			
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			
			System.out.println(player.toString());
			
			System.out.println();
		}
		else if (giftNumber == 1)
		{
			int newAmount = phys.displayWinnings(player, trueOrFalse);
	
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			
			System.out.println(player.toString());
			
			System.out.println();
		}
		
		return trueOrFalse;
	}
}
