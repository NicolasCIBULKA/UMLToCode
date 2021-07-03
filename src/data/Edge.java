package data;

import org.jgrapht.graph.DefaultEdge;

public abstract class Edge extends DefaultEdge {

	// -------------------
	// Attributs
	// -------------------

	private Node sourceNode;
	private Node destinationNode;

	// -------------------
	// Methods
	// -------------------

	public Edge(Node sourceNode, Node destinationNode) {
		super();
		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
	}

	public Node getSourceNode() {
		return sourceNode;
	}

	public void setSourceNode(Node sourceNode) {
		this.sourceNode = sourceNode;
	}

	public Node getDestinationNode() {
		return destinationNode;
	}

	public void setDestinationNode(Node destinationNode) {
		this.destinationNode = destinationNode;
	}

}
