package exceptions;

/**
 * This exception will be raised when we try to do an impossible link with an
 * edge between two Nodes
 * 
 * @author nico
 *
 */
public class ImpossibleLinkException extends Exception {
	
	public ImpossibleLinkException(String s) {
		super(s);
	}
	
}
