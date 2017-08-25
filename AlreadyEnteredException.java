public class AlreadyEnteredException extends Exception 
{
	//CONSTANTS
	
	public static final String DEFAULT_MESSAGE = "You already entered this!";
	
	
	//CONSTRUCTORS
	
	//DESCRIPTION: Sets default message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets default message
	public AlreadyEnteredException()
	{
		super(DEFAULT_MESSAGE);
	}
	
	//DESCRIPTION: Sets message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets message
	public AlreadyEnteredException(String message)
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

