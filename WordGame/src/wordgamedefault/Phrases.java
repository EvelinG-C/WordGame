package wordgamedefault;

public class Phrases 
{
	// Data Field
	public static String gamePhrase;
	private String playingPhrase;
	
	// Getter Methods
	public String getGamePhrase()
	{
		return gamePhrase;
	}
	
	public String getPlayingPhrase()
	{
		return playingPhrase;
	}
	
	// Setter method
	public void setPlayingPhrase(String word)
	{
		StringBuilder hiddenWord = new StringBuilder();
		
		for (int i = 0; i < word.length(); i++ )
		{
			// Character.isLetter checks if a char is a letter
			// word.CharAt() gets the character of the specific position of word
			if(Character.isLetter(word.charAt(i)))
			{
				// underscore is added to hiddenWord
				hiddenWord.append("_");
			}
			else
			{
				// adds the character to hiddenWord if not a letter
				hiddenWord.append(word.charAt(i));
			}
		}
		
		// turns back to string
		playingPhrase = hiddenWord.toString();
	}
	
	// Method to check if letter is a part of the word 
	// and throws the MultipleLettersException
	public void findLetters(String letter) 
			throws MultipleLettersException
	{
		// gets the length of the string
		int wordLength = letter.length();
		
		// checks if the length of string is more than one
		if (wordLength > 1)
		{
			throw new MultipleLettersException();
		}
		
		// temporary word based on the playingPhrase
		StringBuilder tempWord = new StringBuilder(playingPhrase);
		
		// for-loop to check if letter is in gamePhrase
		// also checks if word has multiple of the same letter
		for (int i = 0; i < gamePhrase.length(); i++)
		{
			if (gamePhrase.charAt(i) == letter.charAt(0))
			{
				tempWord.setCharAt(i, letter.charAt(0));
			}
		}
		
		// turns tempWord back to string 
		playingPhrase = tempWord.toString();
	}
}
