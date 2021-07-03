package data;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

	// -------------------
	// Attributs
	// -------------------

	private String name;
	private List<Method> methodList;
	

	// -------------------
	// Methods
	// -------------------
	
	public Node(String name, List<Method> methodList) {
		this.name = name;
		this.methodList = methodList;
	}
	
	public Node(String name) {
		this.name = name;
		this.methodList = new ArrayList<Method>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public List<Method> getMethodList() {
		return methodList;
	}
	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}	

}
