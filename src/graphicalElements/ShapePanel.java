package graphicalElements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Float;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import data.ClassNode;
import data.ComposingEdge;
import data.Edge;
import data.InheritEdge;
import data.Method;
import data.Node;
import data.Variable;

/**
 * This panel contains all the graphical objects to show the graph
 * 
 * @author nico
 *
 */
public class ShapePanel extends JPanel {

	// ---------------------
	// Attributs
	// ---------------------

	// Panel
	private JPanel panel;

	// Constants
	private static final int ORIGINAL_WIDTH = 3000;
	private static final int ORIGINAL_HEIGHT = 1500;
	private static final int LINE_GAP = 20;
	private static final Dimension SHAPE_PANEL_SIZE = new Dimension(ORIGINAL_WIDTH, ORIGINAL_HEIGHT);

	// font
	private Font mainFont;

	// Maps for control
	private Map<ShapeLink, Edge> edgeMap = new HashMap<ShapeLink, Edge>();
	private Map<ShapeClass, Node> nodeMap = new HashMap<ShapeClass, Node>();

	// Colors

	// graphics

	private Graphics2D g2d;

	// ---------------------
	// Methods
	// ---------------------

	// Constructor

	public ShapePanel() {
		panel = this;
		panel.setDoubleBuffered(true);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setPreferredSize(SHAPE_PANEL_SIZE);
		mainFont = new Font(Font.DIALOG, Font.PLAIN, 15);
		panel.setFont(mainFont);
	}

	/**
	 * Method to repaint the panel
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Paint Links
		for (ShapeLink link : edgeMap.keySet()) {
			System.out.println("Link = " + link.getShapeSource() + " - " + link.getShapeDest());
			drawEdge(link);
		}

		// Paint Class
		for (ShapeClass shape : nodeMap.keySet()) {
			setSize(shape);
			drawNode(shape);
		}
	}

	// Add and Remove Shapes

	/**
	 * Create a new ShapeClass and update graphics
	 * 
	 * @param node
	 * @param abs
	 * @param ord
	 * @return
	 */
	public void addShapeClass(Node node, float abs, float ord) {
		ShapeClass newClass = new ShapeClass(node, abs, ord);
		getNodeMap().put(newClass, node);
		repaint();
	}

	/**
	 * Create a new ShapeLink and update Graphics
	 * 
	 * @param edge
	 * @return
	 */
	public void addShapeLink(Edge edge, ShapeClass shapeSource, ShapeClass shapeDest) {
		ShapeLink newLink = new ShapeLink(edge, shapeSource, shapeDest);
		getEdgeMap().put(newLink, edge);
		repaint();
	}

	/**
	 * Remove a shape, whatever is the shape type
	 * 
	 * @param shape
	 */
	public void removeShape(ShapeUnit shape) {
		if (shape instanceof ShapeClass) {
			nodeMap.remove(shape);
		} else {
			edgeMap.remove(shape);
		}
	}

	/**
	 * Clear all the panel
	 */
	public void clear() {
		edgeMap.clear();
		nodeMap.clear();
		repaint();
	}

	// Draw Elements

	/**
	 * Draw Node
	 * 
	 * @param shape
	 */
	private void drawNode(ShapeClass shape) {
		if (shape.getNode() instanceof ClassNode) {
			drawClass(shape);
		} else {
			drawInterface(shape);
		}
	}

	/**
	 * Draw Class and abstract class
	 * 
	 * @param shape
	 */
	private void drawClass(ShapeClass shape) {
		// main
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getMainShape());

		// head
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getHeadShape());

		// variable
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getVariableShape());

		// methods
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getMethodShape());

		// set strokes for small rectangles
		BasicStroke stroke = new BasicStroke(1);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(stroke);

		// get

		// Add Line between different elements
		g2d.draw(new Rectangle2D.Double(shape.getHeadShape().getBounds2D().getX() - 1,
				shape.getHeadShape().getBounds2D().getY() - 1, shape.getHeadShape().getBounds2D().getWidth() + 1,
				shape.getHeadShape().getBounds2D().getHeight()));
		g2d.draw(new Rectangle2D.Double(shape.getVariableShape().getBounds2D().getX() - 1,
				shape.getVariableShape().getBounds2D().getY() - 1,
				shape.getVariableShape().getBounds2D().getWidth() + 1,
				shape.getVariableShape().getBounds2D().getHeight()));
		g2d.draw(new Rectangle2D.Double(shape.getMethodShape().getBounds2D().getX() - 1,
				shape.getMethodShape().getBounds2D().getY() - 1, shape.getMethodShape().getBounds2D().getWidth() + 1,
				shape.getMethodShape().getBounds2D().getHeight() + 1));

		// set strokes for main rectangle
		BasicStroke mainStroke = new BasicStroke(2);
		g2d.setStroke(mainStroke);
		g2d.draw(new Rectangle2D.Double(shape.getMainShape().getBounds2D().getX() - 1,
				shape.getMainShape().getBounds2D().getY() - 1, shape.getMainShape().getBounds2D().getWidth() + 1,
				shape.getMainShape().getBounds2D().getHeight() + 1));

		// Set texts

		// Title
		int titleWidth = getFontMetrics(getFont()).stringWidth(shape.getNode().getName());
		float titleTab = (shape.getGeneralWidth() - titleWidth) / 2;
		g2d.setColor(Color.black);
		g2d.drawString(shape.getNode().getName(), shape.getAbs() + titleTab, shape.getOrd() + 20.0f);

		// attributs
		List<Variable> variableList = ((ClassNode) (shape.getNode())).getVariableList();
		int index = 1;
		for (Variable variable : variableList) {
			g2d.drawString(variable.toString(), shape.getAbs() + 5.0f, shape.getOrd() + 30.0f + (index * 20.0f));
			index++;
		}

		// Methods

		List<Method> methodList = ((ClassNode) (shape.getNode())).getMethodList();
		for (Method method : methodList) {
			g2d.drawString(method.toString(), shape.getAbs() + 5.0f, shape.getOrd() + 50.0f + (index * 20.0f));
			index++;
		}

	}

	/**
	 * Draw Interface
	 * 
	 * @param shape
	 */
	private void drawInterface(ShapeClass shape) {
		// main
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getMainShape());

		// head
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getHeadShape());

		// variable
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getVariableShape());

		// methods
		g2d.setColor(Color.WHITE);
		g2d.fill(shape.getMethodShape());

		// set strokes for small rectangles
		BasicStroke stroke = new BasicStroke(1);
		g2d.setColor(Color.BLUE);
		g2d.setStroke(stroke);

		// Add Line between different elements
		g2d.draw(new Rectangle2D.Double(shape.getHeadShape().getBounds2D().getX() - 1,
				shape.getHeadShape().getBounds2D().getY() - 1, shape.getHeadShape().getBounds2D().getWidth() + 1,
				shape.getHeadShape().getBounds2D().getHeight()));
		g2d.draw(new Rectangle2D.Double(shape.getVariableShape().getBounds2D().getX() - 1,
				shape.getVariableShape().getBounds2D().getY() - 1,
				shape.getVariableShape().getBounds2D().getWidth() + 1,
				shape.getVariableShape().getBounds2D().getHeight()));
		g2d.draw(new Rectangle2D.Double(shape.getMethodShape().getBounds2D().getX() - 1,
				shape.getMethodShape().getBounds2D().getY() - 1, shape.getMethodShape().getBounds2D().getWidth() + 1,
				shape.getMethodShape().getBounds2D().getHeight() + 1));

		// set strokes for main rectangle
		BasicStroke mainStroke = new BasicStroke(2);
		g2d.setStroke(mainStroke);
		g2d.draw(new Rectangle2D.Double(shape.getMainShape().getBounds2D().getX() - 1,
				shape.getMainShape().getBounds2D().getY() - 1, shape.getMainShape().getBounds2D().getWidth() + 1,
				shape.getMainShape().getBounds2D().getHeight() + 1));

		// Set texts

		// Title
		int titleWidth = getFontMetrics(getFont()).stringWidth(shape.getNode().getName());
		float titleTab = (shape.getGeneralWidth() - titleWidth) / 2;
		g2d.setColor(Color.black);
		g2d.drawString(shape.getNode().getName(), shape.getAbs() + titleTab, shape.getOrd() + 20.0f);

		// Methods
		int index = 1;
		List<Method> methodList = shape.getNode().getMethodList();
		for (Method method : methodList) {
			g2d.drawString(method.toString(), shape.getAbs() + 5.0f, shape.getOrd() + 50.0f + (index * 20.0f));
			index++;
		}
	}

	/**
	 * Set the size of the shape
	 * 
	 * @param shape
	 */
	private void setSize(ShapeClass shape) {
		int maxTextWidth = getFontMetrics(getFont()).stringWidth(shape.getNode().getName());
		// check width of variable
		if (shape.getNode() instanceof ClassNode) {
			List<Variable> variableList = ((ClassNode) shape.getNode()).getVariableList();
			for (Variable variable : variableList) {
				if (maxTextWidth < getFontMetrics(getFont()).stringWidth(variable.toString())) {
					maxTextWidth = getFontMetrics(getFont()).stringWidth(variable.toString());
				}
			}
		}

		// check width of methods
		List<Method> methodList = shape.getNode().getMethodList();
		for (Method method : methodList) {
			if (maxTextWidth < getFontMetrics(getFont()).stringWidth(method.toString())) {
				maxTextWidth = getFontMetrics(getFont()).stringWidth(method.toString());
			}
		}

		shape.setGeneralWidth(maxTextWidth + 20.0f);

		// check different heights
		if (shape.getNode() instanceof ClassNode) {
			shape.setVariableHeight(20.0f + ((ClassNode) shape.getNode()).getVariableList().size() * 20f);

		}
		shape.setMethodsHeight(20.0f + shape.getNode().getMethodList().size() * 20f);

		shape.setMainHeight(shape.getHeadHeight() + shape.getVariableHeight() + shape.getMethodsHeight());
	}

	public void drawEdge(ShapeLink link) {
		if (link.getEdge() instanceof ComposingEdge) {

			drawComposingEdge(link);
			System.out.println("draw composing edge");
		} else if (link.getEdge() instanceof InheritEdge) {
			drawInheritEdge(link);
		} else { // implements
			drawImplementsEdge(link);
		}
	}

	public void drawComposingEdge(ShapeLink link) {
		// get Position

		// draw Line
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2f));

		float sourceAbs = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterX();
		float sourceOrd = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterY();
		float destAbs = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterX();
		float destOrd = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterY();
		;
		link.setLink(new Line2D.Float(sourceAbs, sourceOrd, destAbs, destOrd));
		g2d.draw(link.getLink());
		// g2d.draw(new Line2D.Float(0,0,100,100));

	}

	public void drawInheritEdge(ShapeLink link) {
		// draw Line
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2f));

		float sourceAbs = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterX();
		float sourceOrd = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterY();
		float destAbs = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterX();
		float destOrd = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterY();
		;
		link.setLink(new Line2D.Float(sourceAbs, sourceOrd, destAbs, destOrd));
		g2d.draw(link.getLink());
	}

	public void drawImplementsEdge(ShapeLink link) {
		// draw Line
		float[] dashingPattern = {5f, 5f};
		
		
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 0.0f));

		float sourceAbs = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterX();
		float sourceOrd = (float) link.getShapeSource().getMainShape().getBounds2D().getCenterY();
		float destAbs = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterX();
		float destOrd = (float) link.getShapeDest().getMainShape().getBounds2D().getCenterY();
		;
		link.setLink(new Line2D.Float(sourceAbs, sourceOrd, destAbs, destOrd));
		g2d.draw(link.getLink());
	}

	// getters and setters
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Font getMainFont() {
		return mainFont;
	}

	public void setMainFont(Font mainFont) {
		this.mainFont = mainFont;
	}

	public Map<ShapeLink, Edge> getEdgeMap() {
		return edgeMap;
	}

	public void setEdgeMap(Map<ShapeLink, Edge> edgeMap) {
		this.edgeMap = edgeMap;
	}

	public Map<ShapeClass, Node> getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Map<ShapeClass, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

}
