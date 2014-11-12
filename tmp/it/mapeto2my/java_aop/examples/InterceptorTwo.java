package it.mapeto2my.java_aop.examples;

import it.mapeto2my.java_aop.factories.models.json.Flow;
import it.mapeto2my.java_aop.factories.models.json.Flowable;

import java.lang.reflect.Method;

public class InterceptorTwo implements Flowable{

	@Override
	public Object intercept(Flow flow,Object target, Method targetMethod) throws Exception{
		
		Object retVal = null;
		
		System.out.println("*** InterceptorTwo *** PRE ***");
	
		retVal = flow.flow(target,targetMethod);
		
		System.out.println("*** InterceptorTwo *** POST ***");
	
		return retVal;
	}

}
