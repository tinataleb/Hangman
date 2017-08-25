public class EmptyInputException extends Exception 
{
	//CONSTANTS
	
	public static final String DEFAULT_MESSAGE = "You can't leave it blank";
	
	
	//CONSTRUCTORS
	
	//DESCRIPTION: Sets default message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets default message
	public EmptyInputException()
	{
		super(DEFAULT_MESSAGE);
	}
	
	//DESCRIPTION: Sets message
	//PRECONDITION: Instant variables must be declared
	//POSTCONDITION: Sets message
	public EmptyInputException(String message)
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

