package wordgamedefault;

import java.util.Random;

public class Numbers 
{
	// data
	private int randomNum;
	
	// setter 
	public void setRandomNum(int num)
	{
		randomNum = num;
	}
	
	// getter
	public int getRandomNum()
	{
		return randomNum;
	}
	
	// Generates a number between 0 and 100
	public void generateNumber()
	{
		Random rand = new Random();
		int generatedNum = rand.nextInt(0, 101);
		setRandomNum(generatedNum);
	}
	
	// Compares the players number and the generated number
	public boolean compareNumber(int guess)
	{
		if (guess == getRandomNum())
		{
			System.out.println("Congratulations, you guessed the number!");
			return true;
		}
		if (guess > getRandomNum())
		{
			System.out.println("I'm sorry. That guess was too high.");
			return false;
		}
		if (guess < getRandomNum())
		{
			System.out.println("I'm sorry. That guess was too low.");
			return false;
		}
		return true;
	}
}
