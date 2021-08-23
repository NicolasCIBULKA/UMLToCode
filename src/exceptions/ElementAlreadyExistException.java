package exceptions;

/**
 * This exception is raised when we try to create an element in the graph with
 * an already existing name
 * 
 * @author nico
 *
 */
public class ElementAlreadyExistException extends Exception{

	public ElementAlreadyExistException(String s) {
		super(s);
	}
}
