import java.util.InputMismatchException;

//for already used location and location out of bounds

public class InvalidLocationException extends InputMismatchException{
	
	private static final long serialVersionUID = 1L;
	public InvalidLocationException() {}
	public InvalidLocationException(String message) 
	{
		super(message);
	}
}
