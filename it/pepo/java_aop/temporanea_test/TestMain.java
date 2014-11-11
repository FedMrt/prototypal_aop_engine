package it.pepo.java_aop.temporanea_test;

import it.pepo.java_aop.factories.IFactory;
import it.pepo.java_aop.factory_builders.impl.MemoryFactoryBuilder;



public class TestMain {

	public static void main(String[] args) throws Exception {
	
		IFactory iFactory =  (IFactory) new MemoryFactoryBuilder("it/pepo/java_aop/temporanea_test/aop_conf.json").getFactory();
		IScatola scatola = (IScatola) iFactory.getTarget("demoscatola");
		scatola.info();
	}
}
