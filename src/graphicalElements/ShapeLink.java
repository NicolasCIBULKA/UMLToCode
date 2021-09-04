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
	private ShapeClass shapeSource;
	private ShapeClass shapeDest;
	private Shape link;

	// ---------------------
	// Methods
	// ---------------------

	// Constructor
	public ShapeLink(Edge edge, ShapeClass shapeSource, ShapeClass shapeDest) {
		this.edge = edge;
		this.shapeSource = shapeSource;
		this.shapeDest = shapeDest;
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

	public ShapeClass getShapeSource() {
		return shapeSource;
	}

	public void setShapeSource(ShapeClass shapeSource) {
		this.shapeSource = shapeSource;
	}

	public ShapeClass getShapeDest() {
		return shapeDest;
	}

	public void setShapeDest(ShapeClass shapeDest) {
		this.shapeDest = shapeDest;
	}

}
