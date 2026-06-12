package wordgamedefault;

public class Hosts extends Person{
	
	// Constructors
	public Hosts(String firstName)
	{
		super(firstName);
	}
	
	public Hosts(String firstName, String lastName)
	{
		super(firstName, lastName);
	}
	
	// This method accesses the Numbers class 
	// and generates a random number.
	public void randomizeNum()
	{
		Numbers num = new Numbers();
		num.generateNumber();
	}
}
