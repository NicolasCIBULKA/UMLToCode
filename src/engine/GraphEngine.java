package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DirectedPseudograph;

import data.ClassNode;
import data.ComposingEdge;
import data.Edge;
import data.ImplementsEdge;
import data.InheritEdge;
import data.InterfaceClass;
import data.Node;
import data.Project;
import exceptions.ElementAlreadyExistException;
import exceptions.ImpossibleLinkException;
import exceptions.ImpossibleModificationException;

/**
 * 
 * This class will do all the modifications to the graph that represent the UML
 * Scheme
 * 
 * @author nico
 *
 */
public class GraphEngine {

	// ---------------------------------
	// Attributes
	// ---------------------------------

	private Project project; // graph that contains the UML scheme
	private List<Node> nodeList; // Map to guarantee unicity of Nodes
	private List<Edge> edgeList; // List to guarantee unicity of Edges

	// ---------------------------------
	// Methods
	// ---------------------------------

	// ----- Constructors ------

	/**
	 * Constructor for new Project
	 */
	public GraphEngine() {
		this.project = new Project("Undefined Name", new DirectedPseudograph<Node, Edge>(Edge.class));
		this.nodeList = new ArrayList<Node>();
		this.edgeList = new ArrayList<Edge>();
	}

	/**
	 * Constructor for a new project with a defined name
	 * 
	 * @param name
	 */
	public GraphEngine(String name) {
		this.project = new Project(name, new DirectedPseudograph<Node, Edge>(Edge.class));
		this.nodeList = new ArrayList<Node>();
		this.edgeList = new ArrayList<Edge>();
	}

	/**
	 * Constructor for an existing project
	 * 
	 * @param name
	 * @param umlGraph
	 */
	public GraphEngine(String name, DirectedPseudograph<Node, Edge> umlGraph) {
		this.project = new Project(name, umlGraph);
		this.nodeList = this.createNodeList();
		this.edgeList = this.createEdgeList();
	}

	// ------ Generators for existing project constructor ------

	/**
	 * Create a Map of the nodes from the current project
	 * 
	 * @return
	 */
	private List<Node> createNodeList() {
		ArrayList<Node> nodeMap = new ArrayList<Node>();
		// A verification has been already done for the unicity of the nodes
		for (Node node : project.getUMLGraph().vertexSet()) {
			nodeMap.add(node);
		}

		return nodeMap;
	}

	/**
	 * Create a List of Edges from the current project scheme
	 * 
	 * @return
	 */
	private List<Edge> createEdgeList() {
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for (Edge edge : project.getUMLGraph().edgeSet()) {
			edgeList.add(edge);
		}
		return edgeList;
	}

	// ------ Methods to add the different nodes ------

	/**
	 * Create a new Class node in the graph
	 * 
	 * @param name
	 * @throws ElementAlreadyExistException
	 */
	public void addClass(String name) throws ElementAlreadyExistException {

		if (isNameTaken(name)) {
			throw new ElementAlreadyExistException(
					"ERROR - a node with the same name already exist, please use a different name");
		}

		Node node = new ClassNode(name);
		nodeList.add(node);
		this.getProject().getUMLGraph().addVertex(node);

	}

	/**
	 * Create an Interface node in the graph
	 * 
	 * @param name
	 * @throws ElementAlreadyExistException
	 */
	public void addInterface(String name) throws ElementAlreadyExistException {
		if (isNameTaken(name)) {
			throw new ElementAlreadyExistException(
					"ERROR - a node with the same name already exist, please use a different name");
		}

		Node node = new InterfaceClass(name);
		nodeList.add(node);
		this.getProject().getUMLGraph().addVertex(node);
	}

	// ----- Methods to add the different edges -----

	/**
	 * Create an inheritEdge between two Nodes
	 * 
	 * @param nameSource
	 * @param nameDestination
	 * @throws ImpossibleLinkException
	 */
	public void addInheritEdge(String nameSource, String nameDestination) throws ImpossibleLinkException {

		Node sourceNode = getNodeFromName(nameSource);
		Node destinationNode = getNodeFromName(nameDestination);

		if (nameSource == nameDestination || getProject().getUMLGraph().containsEdge(sourceNode, destinationNode)) {
			throw new ImpossibleLinkException("ERROR - Impossible to link together those two nodes");
		}

		InheritEdge edge = new InheritEdge(sourceNode, destinationNode);
		this.getProject().getUMLGraph().addEdge(sourceNode, destinationNode, edge);
		edgeList.add(edge);
	}

	/**
	 * Create a composing edge between two classes
	 * 
	 * @param nameSource
	 * @param nameDestination
	 * @throws ImpossibleLinkException
	 */
	public void addComposingEdge(String nameSource, String nameDestination) throws ImpossibleLinkException {
		Node sourceNode = getNodeFromName(nameSource);
		Node destinationNode = getNodeFromName(nameDestination);

		if (nameSource == nameDestination || getProject().getUMLGraph().containsEdge(sourceNode, destinationNode)) {
			throw new ImpossibleLinkException("ERROR - Impossible to link together those two nodes");
		}

		if (!(sourceNode instanceof ClassNode) || !(destinationNode instanceof ClassNode)) {
			throw new ImpossibleLinkException("ERROR - Impossible to link together those two nodes");

		}

		ComposingEdge edge = new ComposingEdge(sourceNode, destinationNode, 0, 0);
		this.getProject().getUMLGraph().addEdge(sourceNode, destinationNode, edge);
		edgeList.add(edge);
	}

	/**
	 * Create an implements edge between two nodes
	 * 
	 * @param nameSource
	 * @param nameDestination
	 * @throws ImpossibleLinkException
	 */
	public void addImplementsEdge(String nameSource, String nameDestination) throws ImpossibleLinkException {
		Node sourceNode = getNodeFromName(nameSource);
		Node destinationNode = getNodeFromName(nameDestination);

		if (nameSource == nameDestination || getProject().getUMLGraph().containsEdge(sourceNode, destinationNode)) {
			throw new ImpossibleLinkException("ERROR - Impossible to link together those two nodes");
		}

		if (!(sourceNode instanceof InterfaceClass) && !(destinationNode instanceof ClassNode)) {
			throw new ImpossibleLinkException("ERROR - Impossible to link together those two nodes");
		}

		ImplementsEdge edge = new ImplementsEdge(sourceNode, destinationNode);
		this.getProject().getUMLGraph().addEdge(sourceNode, destinationNode, edge);
		edgeList.add(edge);
	}

	// ----- Modify and remove edges -----
	/**
	 * Remove an edge from the graph and the list
	 * 
	 * @param edge
	 */
	public void removeEdge(Edge edge) {
		Edge toRemoveEdge = null;
		for (Edge tempEdge : edgeList) {
			if (tempEdge.equals(edge)) {
				this.getProject().getUMLGraph().removeEdge(edge);
				toRemoveEdge = tempEdge;
			}
		}
		edgeList.remove(toRemoveEdge);
	}

	// ----- NodeList tests and getters -----

	/**
	 * Test if the name is already taken in the nodeList
	 * 
	 * @param name
	 * @return
	 */
	public boolean isNameTaken(String name) {
		for (Node node : nodeList) {
			if (node.getName().contentEquals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the node from the nodeList using the name
	 * 
	 * @param name
	 * @return
	 */
	public Node getNodeFromName(String name) {
		// We suppose the unicity of the name
		for (Node node : nodeList) {
			if (node.getName().contentEquals(name)) {
				return node;
			}
		}
		return null;
	}

	// ----- Modify all the nodes -----

	/**
	 * transform the method in an abstract one
	 * 
	 * @param name
	 */
	public void setOnAbstractClass(String name) {
		Node node = getNodeFromName(name);
		if (node instanceof ClassNode) {
			((ClassNode) node).setAbstract(true);
		}
	}

	/**
	 * Switch the class to a normal class
	 * 
	 * @param name
	 */
	public void unSetAbstractParameter(String name) {
		Node node = getNodeFromName(name);
		if (node instanceof ClassNode) {
			((ClassNode) node).setAbstract(false);
		}
	}

	/**
	 * Change the name of the node
	 * 
	 * @param currentName
	 * @param newName
	 */
	public void changeNodeName(String currentName, String newName) throws ImpossibleModificationException {
		if (isNameTaken(newName) && newName != currentName) {
			Node node = getNodeFromName(currentName);
			node.setName(newName);
		} else {
			throw new ImpossibleModificationException("ERROR - impossible to apply this changes");
		}
	}

	/**
	 * Remove a node and associated edges of the graph
	 * 
	 * @param node
	 */
	public void removeNode(Node node) {
		if (nodeList.contains(node)) {
			// We remove the edges that were connected to the node
			List<Edge> edgesToRemove = new ArrayList<Edge>();
			for (Edge edge : getEdgeList()) {
				if ((edge.getSourceNode()).equals(node) || (edge.getDestinationNode().equals(node))) {
					edgesToRemove.add(edge);
				}
			}
			for (Edge edge : edgesToRemove) {
				edgeList.remove(edge);
				getProject().getUMLGraph().removeEdge(edge);
			}

			// We remove the node
			nodeList.remove(node);
			getProject().getUMLGraph().removeVertex(node);
		}
	}

	// ------ Getters and setter ------

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeMap(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

}
