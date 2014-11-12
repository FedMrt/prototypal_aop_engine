package it.mapeto2my.java_aop.examples;

import it.mapeto2my.java_aop.factories.IFactory;
import it.mapeto2my.java_aop.factory_builders.impl.MemoryFactoryBuilder;



public class Main {

	public static void main(String[] args) throws Exception {
	
		IFactory iFactory =  (IFactory) new MemoryFactoryBuilder("it/mapeto2my/java_aop/examples/aop_conf.json").getFactory();
		IBox box = (IBox) iFactory.getTarget("box");
		System.out.println(box.info());
	}
}
