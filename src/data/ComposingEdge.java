package data;

public class ComposingEdge extends Edge {

	// -------------------
	// Attributs
	// -------------------

	private int sourceNodeCardinality;
	private int destinationNodeCardinality;

	// -------------------
	// Methods
	// -------------------

	public ComposingEdge(Node sourceNode, Node destinationNode, int sourceNodeCardinality,
			int destinationNodeCardinality) {
		super(sourceNode, destinationNode);
		this.sourceNodeCardinality = sourceNodeCardinality;
		this.destinationNodeCardinality = destinationNodeCardinality;
	}

	public int getSourceNodeCardinality() {
		return sourceNodeCardinality;
	}

	public void setSourceNodeCardinality(int sourceNodeCardinality) {
		this.sourceNodeCardinality = sourceNodeCardinality;
	}

	public int getDestinationNodeCardinality() {
		return destinationNodeCardinality;
	}

	public void setDestinationNodeCardinality(int destinationNodeCardinality) {
		this.destinationNodeCardinality = destinationNodeCardinality;
	}

}
