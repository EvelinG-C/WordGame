package wordgamedefault;

import java.util.Random;

public class Turn {
	public Boolean takeTurn(Players player, Hosts host, String letter, Phrases phrase) 
	{
		Random randNum = new Random();
		Money money = new Money();
		Physical phys = new Physical();
		
		int giftNumber;
		giftNumber = randNum.nextInt(0,2);
		String ogPlayingPhrase = phrase.getPlayingPhrase();
		
		if (giftNumber == 0)
		{
			String giftStat = player.getFirstName() + ", You are trying to win money!\n";
			GUI.dialogueArea.append(giftStat);
		}
		else
		{
			String giftStat = player.getFirstName() + ", You are trying to win a gift!\n";
			GUI.dialogueArea.append(giftStat);
		}
		
		phrase.getPlayingPhrase();
		
		if (letter.length() != 1 || !Character.isLetter(letter.charAt(0)))
		{
			String error = "Please enter one letter. Try Again.\n\n";
			GUI.dialogueArea.append(error);
			
			return false;
		}
		
		try {
			phrase.findLetters(letter);
		} catch (MultipleLettersException e) {
			System.out.println(e);
		}
		
		boolean wonGame;
		
		if (!phrase.getPlayingPhrase().contains("_"))
		{
			wonGame = true;
		}
		else
		{
			wonGame = false;
		}
		
		boolean guessedCorrect = true;
		if (ogPlayingPhrase.equals(phrase.getPlayingPhrase()))
		{
			guessedCorrect = false;
		}
		
		// 0 means money
		if (giftNumber == 0)
		{
			int newAmount = money.displayWinnings(player, guessedCorrect);
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			String playerInfo1 = player.toString() + money.getPlayerInfo();
			
			GUI.dialogueArea.append(playerInfo1);
		}
		// 1 means gift
		else if (giftNumber == 1)
		{
			int newAmount = phys.displayWinnings(player, wonGame);
			player.setCurrentMoney(player.getCurrentMoney() + newAmount);
			String playerInfo1 = player.toString() + phys.getPlayerPrize();
			
			GUI.dialogueArea.append(playerInfo1);
		}
		
		return wonGame;
	}
}
