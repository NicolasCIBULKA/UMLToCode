package data;

import java.util.ArrayList;
import java.util.List;

public class Method {
	// -------------------
	// Attributs
	// -------------------

	private String name;
	private String visibility;
	private String returnType;
	private List<Variable> variableList;
	private boolean isAbstract;
	private boolean isStatic;

	// -------------------
	// Methods
	// -------------------

	// Constructor

	public Method(String name, String visibility, String returnType, List<Variable> variableList, boolean isAbstract,
			boolean isStatic) {
		this.name = name;
		this.visibility = visibility;
		this.returnType = returnType;
		this.variableList = variableList;
		this.isAbstract = isAbstract;
		this.isStatic = isStatic;
	}

	public Method(String name, String visibility, String returnType) {
		this.name = name;
		this.visibility = visibility;
		this.returnType = returnType;
		this.variableList = new ArrayList<Variable>();
		this.isAbstract = false;
		this.isStatic = false;
	}

	// Getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<Variable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String toString() {
		String res = visibility + " " + returnType + " " + name + " ( ";
		int index = 0;
		for(Variable var : variableList) {
			res += var.getType() + " " + var.getName();
			index++;
			if(index < variableList.size()) {
				res += ",";
			}
		}
		res+= ")";
		return res;
	}

}
