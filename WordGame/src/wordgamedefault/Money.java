package wordgamedefault;

public class Money implements Award{
	
	// Data
	int incAmount = 15;
	int decAmount = -10;
	String playerInfo = "";
	
	// Method gets the playerInfo string
	public String getPlayerInfo()
	{
		return playerInfo;
	}
	
	// Method displays whether the player won or lost money
	public int displayWinnings(Players player, Boolean correct) {
		if (correct == true)
		{
			playerInfo = "You have guessed right!\n" 
							+ "You have gained $15\n\n";
			
			return incAmount;
		}
		else
		{
			playerInfo = "You have guessed wrong!\n"
							+ "You have lost $10\n\n";
			
			return decAmount;
		}
	}


}
