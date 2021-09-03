package tests;

import data.Project;
import engine.GraphEngine;
import exceptions.ElementAlreadyExistException;
import exceptions.ImpossibleLinkException;

public class Tests {

	public static void main(String[] args) {
		GraphEngine engine = new GraphEngine();
		System.out.println(engine.getProject().getName() + " \n");
		
		try {
			engine.addClass("class1");
			engine.addClass("class2");
			engine.addClass("class3");
			engine.addClass("class4");
			
			
			engine.addComposingEdge("class1", "class2");
			
			engine.addInheritEdge("class2", "class3");
			
			engine.removeEdge(engine.getEdgeList().get(0));
			
			engine.addComposingEdge("class1", "class2");
			
			engine.removeNode(engine.getNodeFromName("class1"));
			
			System.out.println("node List : "+ engine.getNodeList());
			System.out.println("edge list : "+ engine.getEdgeList());
		} catch (ElementAlreadyExistException | ImpossibleLinkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
