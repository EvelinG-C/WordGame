package wordgamedefault;

import java.util.Scanner;

public class GamePlay {
	
	private static Person player;

	public static void main(String[] args) 
	{
		
		System.out.print("Please enter your first name: ");
		Scanner scnr = new Scanner(System.in);
		String firstName = scnr.next();
		
		System.out.print("Would you like to enter your last name? (yes or no) " );
		String answer = scnr.next();
		
		String lastName;
		if (answer.toLowerCase().equals("yes"))
		{
			System.out.print("What is your last name? ");
			lastName = scnr.next();
			player = new Person(firstName, lastName);
		}
		else
		{
			player = new Person(firstName);
		}
		
		Numbers number = new Numbers();
		number.generateNumber();
		
		int guess;
		
		System.out.print("Enter your guess: ");
		
		while(!scnr.hasNextInt())
		{
			System.out.print("Please insert a number: ");
			scnr.next();
		}
		guess = scnr.nextInt();
		
		boolean moreGuess = false; 
		
		while (moreGuess == false)
		{
			moreGuess = number.compareNumber(guess);
			
			if (moreGuess == true)
			{
				break;
			}
			
			System.out.print("Enter your guess: ");
			while(!scnr.hasNextInt())
			{
				System.out.print("Please enter a valid number: ");
				scnr.next();
			}
			guess = scnr.nextInt();
		}
		
		scnr.close();

	}

}
