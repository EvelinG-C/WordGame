package wordgamedefault;

import java.util.Random;
import java.util.Scanner;

public class Turn {
	
	public Boolean takeTurn(Players player, Hosts host, Scanner input, Phrases phrase) 
	{
		Random randNum = new Random();
		Money money = new Money();
		Physical phys = new Physical();
		
		String guessLetter;
		int giftNumber;
		
		System.out.println("----------------------------------");
		System.out.println("'Here is the myterious word:' " + phrase.getPlayingPhrase());
		
		giftNumber = randNum.nextInt(0,2);
		
		if (giftNumber == 0)
		{
			System.out.println(player.getFirstName() + ", 'You are trying to win money!'");
		}
		else
		{
			System.out.println(player.getFirstName() + ", 'You are trying to win a gift!'");
		}
		
		phrase.getPlayingPhrase();

		System.out.print(host.getFirstName() + " says 'Enter a letter:' ");
		
		guessLetter = input.next();
		
		while (guessLetter.length() != 1 || !Character.isLetter(guessLetter.charAt(0)))
		{
			System.out.print("'Please enter one letter:' ");
			guessLetter = input.next();
		}
		
		try {
			phrase.findLetters(guessLetter);
		} catch (MultipleLettersException e) {
			System.out.println(e);
		}
		
		boolean trueOrFalse;
		
		if (!phrase.getPlayingPhrase().contains("_"))
		{
			trueOrFalse = true;
		}
		else
		{
			trueOrFalse = false;
		}
		
		if (giftNumber == 0)
		{
			int newAmount = money.displayWinnings(player, trueOrFalse);
			
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			
			System.out.println(player.toString());
			
			System.out.println("Current Progress: " + phrase.getPlayingPhrase());
			
			System.out.println();
		}
		else if (giftNumber == 1)
		{
			int newAmount = phys.displayWinnings(player, trueOrFalse);
	
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			
			System.out.println(player.toString());
			
			System.out.println("Current Progress: " + phrase.getPlayingPhrase());
			
			System.out.println();
		}
		
		return trueOrFalse;
	}
}
