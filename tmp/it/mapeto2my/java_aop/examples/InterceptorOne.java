package it.mapeto2my.java_aop.examples;

import it.mapeto2my.java_aop.factories.models.json.Flow;
import it.mapeto2my.java_aop.factories.models.json.Flowable;

import java.lang.reflect.Method;

public class InterceptorOne implements Flowable{

	@Override
	public Object intercept(Flow flow, Object target, Method targetMethod) throws Exception{

		Object retVal = null;
		
		System.out.println("*** InterceptorOne *** PRE ***");
	
		retVal = flow.flow(target,targetMethod);
		
		System.out.println("*** InterceptorOne *** POST ***");
	
		return retVal;
	}

}
