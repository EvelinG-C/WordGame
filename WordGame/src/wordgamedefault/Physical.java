package wordgamedefault;

import java.util.Random;

public class Physical implements Award{
	
	private String[] physicalRewards = {"Artbook", "Hawaii Tickets", "Dictionary", "Hoodie", "Blanket"};
	private String prizeMessage = "";
	private String physicalReward;
	
	// Method gets a random number that represents a prize
	public int getRandomPrize()
	{
		Random rand = new Random();
		int randomGift = rand.nextInt(0,5);
		return randomGift;
	}
	
	// Method gets the prizeMessage variable
	public String getPrizeMessage()
	{
		return prizeMessage;
	}
	
	// Method gets the physicalReward variable
	public String getPhysicalReward()
	{
		return physicalReward;
	}
	
	// Method sets the physicalReward variable to "none"
	public void setEmptyReward()
	{
		physicalReward = "none";
	}
	
	// Method displays whether player won a prize or not
	public int displayWinnings(Players player, Boolean didWin)
	{
		if (didWin == true)
		{
			physicalReward = physicalRewards[getRandomPrize()];
			prizeMessage = "You have guessed right!\n" + "Prize won: " + physicalReward + "\n\n";
			
			return 0;
		}
		else
		{
			physicalReward = null;
			String potentialReward = physicalRewards[getRandomPrize()];
			prizeMessage = "You have guessed wrong!\n" + "Prize you could have won: " + potentialReward + "\n\n";
			
			return 0;
		}
	}
}
