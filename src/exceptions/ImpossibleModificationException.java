package exceptions;

/**
 * This exception is raised when we try to do a forbidden operation on the graph
 * 
 * @author nico
 *
 */
public class ImpossibleModificationException extends Exception {

	public ImpossibleModificationException(String s) {
		super(s);
	}
}
