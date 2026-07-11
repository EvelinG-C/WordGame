package wordgamedefault;

import java.util.Random;

public class Physical implements Award{
	
	private String[] physicalRewards = {"Artbook", "Hawaii Tickets", "Signed Posters", "Hoodie", "Blanket"};
	private String playerPrize = "";
	
	// Method gets a random number that represents a prize
	public int getRandomPrize()
	{
		Random rand = new Random();
		int randomGift = rand.nextInt(0,5);
		return randomGift;
	}
	
	// Method gets the playerPrize variable
	public String getPlayerPrize()
	{
		return playerPrize;
	}
	
	// Method displays whether player won a prize or not
	public int displayWinnings(Players player, Boolean didWin)
	{
		if (didWin == true)
		{
			String reward = physicalRewards[getRandomPrize()];
			playerPrize = "You have won!\n" + "Prize won: " + reward + "\n\n";
			
			return 0;
		}
		else
		{
			String reward = physicalRewards[getRandomPrize()];
			playerPrize = "You have not won!\n" + "Prize you could have won: " + reward + "\n\n";
			
			return 0;
		}
	}
}
