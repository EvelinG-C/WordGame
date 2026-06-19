package wordgamedefault;

import java.util.Random;

public class Physical implements Award{
	String[] physicalRewards = {"Artbook", "Hawaii Tickets", "Signed Posters", "Hoodie", "Blanket"};
	
	public int getRandomPrize()
	{
		Random rand = new Random();
		int randomGift = rand.nextInt(0,5);
		return randomGift;
	}
	
	public int displayWinnings(Players player, Boolean didWin)
	{
		if (didWin == true)
		{
			System.out.println("'" + player.getFirstName() + ", You have won!'");
			String reward = physicalRewards[getRandomPrize()];
			System.out.println("Prize won: " + reward);
			System.out.println();
			return 0;
		}
		else
		{
			System.out.println("'" + player.getFirstName() + ", You have lost!'");
			String reward = physicalRewards[getRandomPrize()];
			System.out.println("Prize you could have won: " + reward);
			System.out.println();
			return 0;
		}
	}
}
