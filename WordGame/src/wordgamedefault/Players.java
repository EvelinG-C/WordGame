package wordgamedefault;

public class Players extends Person{
	
	// data
	private int currentMoney;
	
	// Constructors
	public Players(String firstName)
	{
		super(firstName);
		currentMoney = 1000;
	}
	
	public Players(String firstName, String lastName)
	{
		super(firstName, lastName);
		currentMoney = 1000;
	}
	
	// Setter
	public void setCurrentMoney(int amount)
	{
		currentMoney = amount;
	}
	
	// Getter
	public int getCurrentMoney()
	{
		return currentMoney;
	}
	
	// toString Override method
	@Override
	public String toString()
	{
		return "Player's name: " + getFirstName() + " " + getLastName() + "\n"
				+ "Current Amount of Money: " + getCurrentMoney();
	}

}
