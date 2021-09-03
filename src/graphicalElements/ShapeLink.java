package graphicalElements;

import java.awt.Shape;
import data.Edge;

/**
 * 
 * @author nico
 *
 */
public class ShapeLink extends ShapeUnit {

	// ---------------------
	// Attributs
	// ---------------------

	private Edge edge;

	private Shape link;

	// ---------------------
	// Methods
	// ---------------------

	// Constructor
	public ShapeLink(Edge edge) {
		this.edge = edge;
	}
	
	// getters and setters

	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public Shape getLink() {
		return link;
	}

	public void setLink(Shape link) {
		this.link = link;
	}

}
