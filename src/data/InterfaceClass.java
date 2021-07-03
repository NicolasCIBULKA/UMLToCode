package data;

import java.util.List;

/**
 * This class will represent the Interface class in an UML Graph
 * 
 * @author nico
 *
 */
public class InterfaceClass extends Node {

	// -------------------
	// Attributs
	// -------------------

	// -------------------
	// Methods
	// -------------------
	
	public InterfaceClass(String name,  List<Method> methodList) {
		super(name, methodList);
	}
	
	public InterfaceClass(String name) {
		super(name);
	}
}
