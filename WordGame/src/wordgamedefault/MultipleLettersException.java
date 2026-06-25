package wordgamedefault;

public class MultipleLettersException extends Exception{
	
	public MultipleLettersException() {
		
	}

	@Override
	public String getMessage()
	{
		return ("More than one letter was entered");
	}
}
