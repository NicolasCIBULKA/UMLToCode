package graphicalElements;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.List;

import data.Node;

/**
 * Generical element for all class elements
 * 
 * @author nico
 *
 */
public class ShapeClass extends ShapeUnit {
	// ---------------------
	// Attributs
	// ---------------------

	// general elements
	private Node node;
	private float abs;
	private float ord;

	// Const for shape size
	private float generalWidth = 150.0f;
	private float mainHeight = 50.0f;
	private float headHeight = 30.0f;
	private float variableHeight = 10.0f;
	private float methodsHeight = 10.0f;

	// shape elements
	private Shape headShape;
	private Shape variableShape;
	private Shape methodShape;
	private Shape mainShape;

	// ---------------------
	// Methods
	// ---------------------

	// Constructor
	public ShapeClass(Node node, float abs, float ord) {
		this.node = node;
		this.abs = abs;
		this.ord = ord;

		mainShape = new Rectangle2D.Float(abs, ord, generalWidth, mainHeight);
		headShape = new Rectangle2D.Float(abs, ord, generalWidth, headHeight);
		variableShape = new Rectangle2D.Float(abs, ord + headHeight, generalWidth, variableHeight);
		methodShape = new Rectangle2D.Float(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	// Moving methods

	/**
	 * Set the group abscissa
	 * 
	 * @param x
	 */
	public void setGroupAbscissa(float x) {
		setAbs(x);

		((Rectangle2D) mainShape).setRect(x, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(x, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(x, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(x, ord + headHeight + variableHeight, generalWidth, methodsHeight);

	}

	/**
	 * Set the group ordinate
	 * 
	 * @param y
	 */
	public void setGroupOrdinate(float y) {
		setOrd(y);

		((Rectangle2D) mainShape).setRect(abs, y, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, y, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, y + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, y + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	// Getters and setters

	public float getAbs() {
		return abs;
	}

	public void setAbs(float abs) {
		this.abs = abs;
	}

	public float getOrd() {
		return ord;
	}

	public void setOrd(float ord) {
		this.ord = ord;
	}

	public Shape getHeadShape() {
		return headShape;
	}

	public void setHeadShape(Shape headShape) {
		this.headShape = headShape;
	}

	public Shape getVariableShape() {
		return variableShape;
	}

	public void setVariableShape(Shape variableShape) {
		this.variableShape = variableShape;
	}

	public Shape getMethodShape() {
		return methodShape;
	}

	public void setMethodShape(Shape methodShape) {
		this.methodShape = methodShape;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Shape getMainShape() {
		return mainShape;
	}

	public void setMainShape(Shape mainShape) {
		this.mainShape = mainShape;
	}

	public float getGeneralWidth() {
		return generalWidth;
	}

	public void setGeneralWidth(float generalWidth) {
		this.generalWidth = generalWidth;

		((Rectangle2D) mainShape).setRect(abs, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	public float getMainHeight() {
		return mainHeight;
	}

	public void setMainHeight(float mainHeight) {
		this.mainHeight = mainHeight;

		((Rectangle2D) mainShape).setRect(abs, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	public float getHeadHeight() {
		return headHeight;
	}

	public void setHeadHeight(float headHeight) {
		this.headHeight = headHeight;

		((Rectangle2D) mainShape).setRect(abs, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	public float getVariableHeight() {
		return variableHeight;
	}

	public void setVariableHeight(float variableHeight) {
		this.variableHeight = variableHeight;

		((Rectangle2D) mainShape).setRect(abs, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}

	public float getMethodsHeight() {
		return methodsHeight;
	}

	public void setMethodsHeight(float methodsHeight) {
		this.methodsHeight = methodsHeight;

		((Rectangle2D) mainShape).setRect(abs, ord, generalWidth, mainHeight);
		((Rectangle2D) headShape).setRect(abs, ord, generalWidth, headHeight);
		((Rectangle2D) variableShape).setRect(abs, ord + headHeight, generalWidth, variableHeight);
		((Rectangle2D) methodShape).setRect(abs, ord + headHeight + variableHeight, generalWidth, methodsHeight);
	}
}
