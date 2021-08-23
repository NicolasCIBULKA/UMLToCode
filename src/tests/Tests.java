package tests;

import data.Project;
import engine.GraphEngine;
import exceptions.ElementAlreadyExistException;

public class Tests {

	public static void main(String[] args) {
		GraphEngine engine = new GraphEngine();
		System.out.println(engine.getProject().getName() + " \n");
		
		try {
			engine.addClass("class1");
		} catch (ElementAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
