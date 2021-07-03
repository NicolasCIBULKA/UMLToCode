package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassNode extends Node {

	// -------------------
	// Attributs
	// -------------------

	private List<Variable> variableList;
	private Map<Variable, Boolean> variableGetterMap;
	private Map<Variable, Boolean> variableSetterMap;
	private boolean isAbstract;

	// -------------------
	// Methods
	// -------------------
	
	public ClassNode(String name) {
		super(name);
		this.variableList = new ArrayList<Variable>();
		this.variableGetterMap = new HashMap<Variable, Boolean>();
		this.variableSetterMap = new HashMap<Variable, Boolean>();
		this.isAbstract = false;
	}
	
	public ClassNode(String name, List<Method> methodList) {
		super(name, methodList);
		this.variableList = new ArrayList<Variable>();
		this.variableGetterMap = new HashMap<Variable, Boolean>();
		this.variableSetterMap = new HashMap<Variable, Boolean>();
		this.isAbstract = false;
	}
	
	public ClassNode(String name, List<Method> methodList, List<Variable> variableList) {
		super(name, methodList);
		this.variableList = variableList;
		this.variableGetterMap = new HashMap<Variable, Boolean>();
		this.variableSetterMap = new HashMap<Variable, Boolean>();
		this.isAbstract = false;
	}
	
	public ClassNode(String name, List<Method> methodList, List<Variable> variableList,
			Map<Variable, Boolean> variableGetterMap, Map<Variable, Boolean> variableSetterMap, boolean isAbstract) {
		super(name, methodList);
		this.variableList = variableList;
		this.variableGetterMap = variableGetterMap;
		this.variableSetterMap = variableSetterMap;
		this.isAbstract = isAbstract;
	}

	public List<Variable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}

	public Map<Variable, Boolean> getVariableGetterMap() {
		return variableGetterMap;
	}

	public void setVariableGetterMap(Map<Variable, Boolean> variableGetterMap) {
		this.variableGetterMap = variableGetterMap;
	}

	public Map<Variable, Boolean> getVariableSetterMap() {
		return variableSetterMap;
	}

	public void setVariableSetterMap(Map<Variable, Boolean> variableSetterMap) {
		this.variableSetterMap = variableSetterMap;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

}
