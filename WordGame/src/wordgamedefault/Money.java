package wordgamedefault;

public class Money implements Award{
	int incAmount = 15;
	int decAmount = -10;
	
	public int displayWinnings(Players player, Boolean didWin) {
		if (didWin == true)
		{
			
			System.out.println("'" + player.getFirstName() + ", You have won!'");
			System.out.println();
			
			return incAmount;
		}
		else
		{
			System.out.println("'" + player.getFirstName() + ", You have lost!'");
			System.out.println();
			
			return decAmount;
		}
	}


}
