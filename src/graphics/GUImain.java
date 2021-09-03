package graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import data.ClassNode;
import data.InterfaceClass;
import data.Method;
import data.Variable;
import graphicalElements.ShapeClass;
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

		shapePanel = new ShapePanel();
		shapePanel.addMouseMotionListener(clickManager);
		shapePanel.addMouseListener(clickManager);
		jScrollpane = new JScrollPane(shapePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollpane.getVerticalScrollBar().setUnitIncrement(10);
		jScrollpane.getHorizontalScrollBar().setUnitIncrement(10);
		contentPane.add(iconPanel, BorderLayout.NORTH);
		contentPane.add(jScrollpane, BorderLayout.CENTER);
		ClassNode node = new ClassNode("test");

		node.getVariableList().add(new Variable("test", " String", "+"));
		node.getVariableList().add(new Variable("test", " String", "+"));
		node.getVariableList().add(new Variable("test", " String", "+"));
		node.getVariableList().add(new Variable("test", " String", "+"));
		node.getVariableList().add(new Variable("test", " String", "+"));

		node.getMethodList().add(new Method("test", "+", "void", node.getVariableList(), false, false));
		node.getMethodList().add(new Method("test", "+", "void", node.getVariableList(), false, false));
		node.getMethodList().add(new Method("test", "+", "void", node.getVariableList(), false, false));

		InterfaceClass node2 = new InterfaceClass("testInterface", node.getMethodList());
		shapePanel.getNodeMap().put(new ShapeClass(node2, 300, 500), node2);
		shapePanel.getNodeMap().put(new ShapeClass(node, 100, 100), node);
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

		public void mousePressed(MouseEvent e) {
			abs = e.getX();
			ord = e.getY();
			selectedShape = null;
			for(ShapeClass shape : shapePanel.getNodeMap().keySet()) {
				if(shape.getMainShape().contains(abs, ord)) {
					selectedShape = shape;
					System.out.println(shape.getNode().getName());
				}
			}
		}
		
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - getAbs();
			int dy = e.getY() - getOrd();
			
			float newX = ((ShapeClass) selectedShape).getAbs() + dx;
			float newY = ((ShapeClass) selectedShape).getOrd() + dy;
			System.out.println("dragged ");
			if(selectedShape != null && iconPanel.getCursorState().equals("hand")) { // TODO only if move is active
				if(selectedShape instanceof ShapeClass) {
					((ShapeClass) selectedShape).setAbs(dx);
					((ShapeClass) selectedShape).setOrd(dy);
					repaint();
				}
			}
//			abs+=dx;
//			ord+=dy;
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
