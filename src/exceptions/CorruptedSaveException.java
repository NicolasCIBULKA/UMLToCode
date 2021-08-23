package exceptions;

/**
 * This exception will be raised when an error is detected in the file
 * @author nico
 *
 */
public class CorruptedSaveException extends Exception {

	public CorruptedSaveException(String s) {
		super(s);
	}
	
}
