package data;

import org.jgrapht.Graph;

/**
 * This class is the global storage of the project
 * 
 * @author nico
 *
 */
public class Project {

	// -------------------
	// Attributs
	// -------------------

	private String name; // Name of the project
	private Graph<Node, Edge> umlGraph; // Graph that will represent the UML

	// -------------------
	// Methods
	// -------------------

	public Graph<Node, Edge> getUMLGraph() {
		return umlGraph;
	}

	public Project(String name, Graph<Node, Edge> umlGraph) {
		this.name = name;
		this.umlGraph = umlGraph;
	}

	public void setUMLGraph(Graph<Node, Edge> umlGraph) {
		this.umlGraph = umlGraph;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
