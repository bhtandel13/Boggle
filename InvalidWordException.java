import java.util.InputMismatchException; 

//for word < 3 letters, word not in dictionary, word already used

public class InvalidWordException extends InputMismatchException {
	
	private static final long serialVersionUID = 1L;
	public InvalidWordException() {}
	public InvalidWordException(String message)
	{
		super(message);
	}

}
