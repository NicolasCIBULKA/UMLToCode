package graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import data.ClassNode;
import data.ComposingEdge;
import data.ImplementsEdge;
import data.InheritEdge;
import data.InterfaceClass;
import data.Method;
import data.Variable;
import engine.GraphEngine;
import exceptions.ElementAlreadyExistException;
import exceptions.ImpossibleLinkException;
import graphicalElements.ShapeClass;
import graphicalElements.ShapeLink;
import graphicalElements.ShapePanel;
import graphicalElements.ShapeUnit;

public class GUImain extends JFrame {

	// ---------------------
	// Attributs
	// ---------------------

	// JFrame
	JFrame frame;

	// JMenuBar
	JMenuBar menubar = new JMenuBar();

	JMenu file = new JMenu("File");
	JMenu export = new JMenu("Export");
	JMenu about = new JMenu("About");

	JMenuItem newItem = new JMenuItem("New Project");
	JMenuItem openItem = new JMenuItem("Open Project");
	JMenuItem saveItem = new JMenuItem("Save ...");
	JMenuItem saveInItem = new JMenuItem("Save In ...");
	JMenuItem exitItem = new JMenuItem("Exit");

	JMenuItem imageExport = new JMenuItem("Export as Image ...");
	JMenuItem codeExport = new JMenuItem("Export as usable Code ...");

	JMenuItem websiteAbout = new JMenuItem("Visit Website");

	// Icon Panel
	IconPanel iconPanel = new IconPanel();

	// JScrollPane
	ShapeUnit selectedShape;
	ShapePanel shapePanel;
	JScrollPane jScrollpane;

	// ClickManager
	private ClickManager clickManager = new ClickManager();

	// variable to create automatical names for Class or Interface
	int nodeNumber = 0;

	// Engine variable
	GraphEngine engine;

	// ---------------------
	// Methods
	// ---------------------

	public GUImain() {
		frame = this;
		initLayout();
	}

	/**
	 * Init the graphical elements
	 */
	public void initLayout() {

		// Create 3 areas for head menu, area to create elements and work area
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(3, 1));

		setMenuBar();

		// init engine

		engine = new GraphEngine();

		shapePanel = new ShapePanel();
		shapePanel.addMouseMotionListener(clickManager);
		shapePanel.addMouseListener(clickManager);
		jScrollpane = new JScrollPane(shapePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollpane.getVerticalScrollBar().setUnitIncrement(10);
		jScrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		contentPane.add(iconPanel, BorderLayout.NORTH);
		contentPane.add(jScrollpane, BorderLayout.CENTER);

		// shapePanel.addShapeClass(node, 100, 100);

		// set the jframe position and size
		frame.setTitle("UMLToCode - developpement version");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setLocation(0, 0);

		frame.pack();
		frame.setVisible(true);
		shapePanel.repaint();
	}

	/**
	 * Methods to create the menuBar
	 */
	private void setMenuBar() {
		file.add(newItem);
		file.add(openItem);
		file.add(saveItem);
		file.add(saveInItem);
		file.add(exitItem);

		export.add(imageExport);
		export.add(codeExport);

		about.add(websiteAbout);

		menubar.add(file);
		menubar.add(export);
		menubar.add(about);

		frame.setJMenuBar(menubar);
	}

	/**
	 * Class to detect clicks on the ShapePanel
	 * 
	 * @author nico
	 *
	 */
	public class ClickManager extends MouseAdapter {
		private int abs;
		private int ord;

		private List<ShapeClass> shapeBuffer = new ArrayList<ShapeClass>();

		public void mousePressed(MouseEvent e) {
			abs = e.getX();
			ord = e.getY();
			selectedShape = null;

			if (!iconPanel.getCursorState().equals("none")) {
				// search if an element is aimed
				for(ShapeLink shape : shapePanel.getEdgeMap().keySet()) {
					System.out.println(shape.isPointContained(abs, ord));
					if(shape.isPointContained(abs, ord)) {
						selectedShape = shape;
						System.out.println("wanted edge: "+shape.getEdge());
					}
				}
				for (ShapeClass shape : shapePanel.getNodeMap().keySet()) {
					if (shape.getMainShape().contains(abs, ord)) {
						selectedShape = shape;
						System.out.println("wanted node: " + shape.getNode().getName());
					}
				}

				if (iconPanel.getCursorState().equals("class")) {
					try {
						engine.addClass("Node" + nodeNumber);
						System.out.println(engine.getNodeList());
					} catch (ElementAlreadyExistException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					shapePanel.addShapeClass(engine.getNodeFromName("Node" + nodeNumber), abs, ord);
					nodeNumber++;
					repaint();
				} else if (iconPanel.getCursorState().equals("interface")) {
					try {
						engine.addInterface("Node" + nodeNumber);
					} catch (ElementAlreadyExistException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					shapePanel.addShapeClass(engine.getNodeFromName("Node" + nodeNumber), abs, ord);
					nodeNumber++;
					repaint();
				} else if (iconPanel.getCursorState().equals("delete object")) {
					if (selectedShape instanceof ShapeClass) {
						engine.removeNode(((ShapeClass) selectedShape).getNode());
						shapePanel.removeShape(selectedShape);
					}

					repaint();
				} else if (iconPanel.getCursorState().equals("composition")) {
					if (selectedShape instanceof ShapeClass) {
						shapeBuffer.add((ShapeClass) selectedShape);
						// System.out.println("Composing " + shapeBuffer.size() + " " +
						// shapeBuffer.get(1).getAbs());
						if (shapeBuffer.size() >= 2) {
							System.out.println(shapeBuffer.size());
							try {
								ComposingEdge edge = new ComposingEdge(((ShapeClass) shapeBuffer.get(0)).getNode(),
										((ShapeClass) shapeBuffer.get(1)).getNode(), 0, 0);
								engine.addComposingEdge(edge);
								ShapeClass source = ((ShapeClass) shapeBuffer.get(1));
								ShapeClass dest = ((ShapeClass) shapeBuffer.get(0));
								shapePanel.addShapeLink(edge, source, dest);
								shapeBuffer.clear();
							} catch (ImpossibleLinkException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							repaint();
						}
					}
				} else if (iconPanel.getCursorState().equals("heritage")) {
					if (selectedShape instanceof ShapeClass) {
						shapeBuffer.add((ShapeClass) selectedShape);
						// System.out.println("Composing " + shapeBuffer.size() + " " +
						// shapeBuffer.get(1).getAbs());
						if (shapeBuffer.size() >= 2) {
							System.out.println(shapeBuffer.size());
							try {
								InheritEdge edge = new InheritEdge(((ShapeClass) shapeBuffer.get(0)).getNode(),
										((ShapeClass) shapeBuffer.get(1)).getNode());
								engine.addInheritEdge(edge);
								ShapeClass source = ((ShapeClass) shapeBuffer.get(1));
								ShapeClass dest = ((ShapeClass) shapeBuffer.get(0));
								shapePanel.addShapeLink(edge, source, dest);
								shapeBuffer.clear();
							} catch (ImpossibleLinkException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							repaint();
						}
					}
				} else if (iconPanel.getCursorState().equals("implements")) {
					if (selectedShape instanceof ShapeClass) {
						shapeBuffer.add((ShapeClass) selectedShape);
						// System.out.println("Composing " + shapeBuffer.size() + " " +
						// shapeBuffer.get(1).getAbs());
						if (shapeBuffer.size() >= 2) {
							System.out.println(shapeBuffer.size());
							try {
								ImplementsEdge edge = new ImplementsEdge(((ShapeClass) shapeBuffer.get(0)).getNode(),
										((ShapeClass) shapeBuffer.get(1)).getNode());
								engine.addImplementsEdge(edge);
								ShapeClass source = ((ShapeClass) shapeBuffer.get(1));
								ShapeClass dest = ((ShapeClass) shapeBuffer.get(0));
								shapePanel.addShapeLink(edge, source, dest);
								shapeBuffer.clear();
							} catch (ImpossibleLinkException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							repaint();
						}
					}
				} else if (iconPanel.getCursorState().equals("selection")) {
					System.out.println(((ShapeClass) selectedShape).getNode());
				} else if (iconPanel.getCursorState().equals("delete")) {
					System.out.println("Delete Mode");
					if (selectedShape instanceof ShapeClass) {
						engine.removeNode(((ShapeClass) selectedShape).getNode());
						shapePanel.removeShape(selectedShape);
						System.out.println(engine.getEdgeList());
					} else if (selectedShape instanceof ShapeLink) {
						engine.removeEdge(((ShapeLink) selectedShape).getEdge());
						shapePanel.removeShape(selectedShape);
					}
					repaint();
				}
			}

		}

		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - abs;
			int dy = e.getY() - ord;

			System.out.println("dragged");
			if (selectedShape != null && iconPanel.getCursorState().equals("hand")) { // TODO only if move is active
				if (selectedShape instanceof ShapeClass) {
					((ShapeClass) selectedShape).setAbs(((ShapeClass) selectedShape).getAbs() + dx);
					((ShapeClass) selectedShape).setOrd(((ShapeClass) selectedShape).getOrd() + dy);
					repaint();
				}
			}
			abs += dx;
			ord += dy;
		}

		// getters and setters

		public int getAbs() {
			return abs;
		}

		public void setAbs(int abs) {
			this.abs = abs;
		}

		public int getOrd() {
			return ord;
		}

		public void setOrd(int ord) {
			this.ord = ord;
		}

	}

	public ClickManager getClickManager() {
		return clickManager;
	}

	public void setClickManager(ClickManager clickManager) {
		this.clickManager = clickManager;
	}

}
