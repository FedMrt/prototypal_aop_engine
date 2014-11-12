package it.mapeto2my.java_aop.examples;

public class Box implements IBox{

	@Override
	public String info() {
		
		System.out.println("Yes .... I'm a box");
		return " .... and I return a value!!!";
	}

}
