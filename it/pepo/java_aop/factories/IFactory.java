package it.pepo.java_aop.factories;

public interface IFactory {

	Object getTarget(String key) throws Exception;
	
}
