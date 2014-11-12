package it.mapeto2my.java_aop.factory_builders.impl;

import it.mapeto2my.java_aop.factories.IFactory;
import it.mapeto2my.java_aop.factories.models.memory_compz.JavaClassManager;
import it.mapeto2my.java_aop.factories.models.memory_compz.JavaSource;
import it.mapeto2my.java_aop.factory_builders.FactoryBuilder;
import it.mapeto2my.java_aop.utils.Utilities;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class MemoryFactoryBuilder implements FactoryBuilder{

	private String configFilePath;
	private static JavaFileManager fileManager;
	private static final String MEM_FACTORY_PACKAGE_NAME = "it.mapeto2my.java_aop.factories.impl.build";
	private static final String IFACTORY_PACKAGE_NAME = "it.mapeto2my.java_aop.factories";
	private static final String FMODEL_PACKAGE_NAME = "it.mapeto2my.java_aop.factories.models.json";
	
	private static final String CLASS_NAME = "MemoryFactory";
	private static final String FULL_NAME = MEM_FACTORY_PACKAGE_NAME + "." + CLASS_NAME;
	
	public MemoryFactoryBuilder(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	

	@Override
	public Object getFactory() throws Exception {
		// TODO Auto-generated method stub
		
		
		buildFactory(writeFactory());
		return loadFactory(null,null);
	}
	
	
	@Override
	public StringBuffer writeFactory() throws Exception {
		// TODO Auto-generated method stub
		
		Utilities.Target[] targetsInfos = 
				Utilities.getTargetsInfos(this.getClass()
												.getClassLoader()
													.getResourceAsStream(configFilePath));
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("package " + MEM_FACTORY_PACKAGE_NAME + ";\n\n");
		sBuffer.append("public class MemoryFactory implements " + IFACTORY_PACKAGE_NAME + ".IFactory{\n\n");
		
		for (Utilities.Target target : targetsInfos) {
			
			sBuffer.append("private " + FMODEL_PACKAGE_NAME + ".Flow targetOperationsFlow" + "_" + target.getTargetIdentifier() + ";\n");
			sBuffer.append("private Object target" + "_" + target.getTargetIdentifier() + ";\n\n\n");
			
		}
		
		sBuffer.append("public static MemoryFactory getSingleInstance(){\n");
		sBuffer.append("	return new MemoryFactory();\n");
		sBuffer.append("}\n\n");
		
		sBuffer.append("private MemoryFactory() {\n\n");
		
		for (Utilities.Target target : targetsInfos) {
			
			sBuffer.append("	targetOperationsFlow" + "_" + 
								target.getTargetIdentifier() + " = " + 
									"new " + FMODEL_PACKAGE_NAME + ".Flow(" + target.getInterceptorsClassName().length + ");\n\n");
			
			for(String interceptorClassName : target.getInterceptorsClassName()){
				sBuffer.append("	targetOperationsFlow" + "_" + 
						target.getTargetIdentifier() + ".addFlowable(new "+interceptorClassName+"());\n");
			}			
			
			sBuffer.append("\n\n	target" + "_" + target.getTargetIdentifier() + "="+ " new "+ target.getTargetClassName() + "(){\n\n");
			 
			Method[] targetClassMethods = Class.forName(target.getTargetClassName()).getDeclaredMethods();
			
			for (Method method : targetClassMethods) {
				
				sBuffer.append("		@Override\n");
				sBuffer.append("		public " + method.getReturnType().getName() + " " + method.getName() + "() {\n\n"); /* TEMP */
				
				sBuffer.append("			" + method.getReturnType().getName() + " retObject = null;\n\n");
				sBuffer.append("			try{\n\n");
				sBuffer.append("				retObject = (" + method.getReturnType().getName() + ")targetOperationsFlow_" + target.getTargetIdentifier());
				sBuffer.append(".flow(new " + target.getTargetClassName() + "(),new " + target.getTargetClassName()
						+ "().getClass().getMethod(\"" +  method.getName() +"\"));\n"); 
				sBuffer.append("			}catch(Exception ex){\n");
				sBuffer.append("				ex.printStackTrace();\n");
				sBuffer.append("			}\n");
				sBuffer.append("				return retObject;\n\n");
				sBuffer.append("		}\n\n");
			}
			
			sBuffer.append("	};\n\n");		
		}
		
		sBuffer.append("	}\n\n");
		
		sBuffer.append("	@Override\n");
		sBuffer.append("	public Object getTarget(String key) throws Exception{\n");
		sBuffer.append("		return MemoryFactory.class.getMethod(\"get_\" + key).invoke(this);\n");
		sBuffer.append("	}\n\n");
		
		for (Utilities.Target target : targetsInfos) {
			
			sBuffer.append("	public Object get" + "_" + target.getTargetIdentifier() + "(){\n");
			sBuffer.append("		return target_" + target.getTargetIdentifier() + ";\n");
			sBuffer.append("	}\n\n");		
		}
		
		sBuffer.append("}");
		
		return sBuffer;
	}

	
	@Override
	public int buildFactory(StringBuffer sBuffer) throws FileNotFoundException {
		
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			fileManager = new
	           JavaClassManager(compiler
	               .getStandardFileManager(null, null, null));
		
	  
        List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
        jfiles.add(new JavaSource(FULL_NAME, sBuffer));   
	       
	    compiler.getTask(null, fileManager, null, null,null, jfiles).call();
        
	    return 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IFactory loadFactory(String runtimeClassesFolder,
			Class<?> invokerClass) throws Exception {
		
		Class cls1  = fileManager.getClassLoader(null).loadClass(FULL_NAME);
	        		
	    return (IFactory) cls1.getMethod("getSingleInstance").invoke(null);
	}
	
}
