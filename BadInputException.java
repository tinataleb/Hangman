public class BadInputException extends Exception 
{
	//CONSTANTS
	
	public static final String DEFAULT_MESSAGE = "You should enter a letter";
	
	
	//CONSTRUCTORS
	
	//DESCRIPTION: Sets default message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets default message
	public BadInputException()
	{
		super(DEFAULT_MESSAGE);
	}
	
	//DESCRIPTION: Sets message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets message
	public BadInputException(String message)
	{
		super(message);
	} 
	
	@Override 
	
	//DESCRIPTION: Concatenates a String with message
	//PRECONDITION: Instance variable must be initialized
	//POSTCONDITION: Returns the concatenated String
	public String getMessage()
	{
		return super.getMessage();
	}
}

