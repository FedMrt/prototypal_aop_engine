package it.pepo.java_aop.factories.models.json;

import java.lang.reflect.Method;

public class Flow {

	private Flowable[] flowables;
	private int flowStep;
	private int flowCount;
	
	public Flow(int flowableNumber) {
		
		this.flowables = new Flowable[flowableNumber];
	}


	public Object flow(Object target,Method targetMethod) throws Exception{
		
		Object returnedObj = null;
		
		if(flowStep == flowCount)
			returnedObj= targetMethod.invoke(target);
		else
			returnedObj = flowables[flowStep++].intercept(this,target,targetMethod);
		
		return returnedObj;
	}

	public void addFlowable(Flowable flowable){
		flowables[flowCount++] = flowable;
	}

	public Flowable[] getFlowables() {
		return flowables;
	}

	public void setFlowables(Flowable[] flowables) {
		this.flowables = flowables;
	}	
}
