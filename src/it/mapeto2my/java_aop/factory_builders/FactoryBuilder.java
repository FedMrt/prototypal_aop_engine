package it.mapeto2my.java_aop.factory_builders;
import it.mapeto2my.java_aop.factories.IFactory;

import java.io.FileNotFoundException;


public interface FactoryBuilder {

	Object getFactory() throws Exception;
	
	StringBuffer writeFactory() throws Exception;

	int buildFactory(StringBuffer sBuffer) throws FileNotFoundException;
	
	IFactory loadFactory(String runtimeClassesFolder ,Class<?> invokerClass) throws Exception;
	
}
