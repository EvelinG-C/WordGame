package wordgamedefault;

public class Person 
{
	// data
	private String firstName;
	private String lastName;
	
	// Overloaded Constructors
	public Person(String fName)
	{
		firstName = fName;
		lastName = "";
	}
	
	public Person(String fName, String lName)
	{
		firstName = fName;
		lastName = lName;
	}
	
	// Setters 
	public void setFirstName(String fName)
	{
		firstName = fName;
	}
	
	public void setLastName(String lName)
	{
		lastName = lName;
	}
	
	// Getters 
	public String getFirstName() 
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
}
