package it.mapeto2my.java_aop.factories.models.memory_compz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

@SuppressWarnings("rawtypes")
public class JavaClassManager extends ForwardingJavaFileManager{

	private ClassLoader loader;
	private Map<String, JavaClass> javaClasses = new HashMap<String, JavaClass>(); 
	
	@SuppressWarnings("unchecked")
	public JavaClassManager(JavaFileManager fileManager) {
		super(fileManager);
		loader = new CustomClassLoader();
	}
	
	@Override
	public ClassLoader getClassLoader(Location location) {
		// TODO Auto-generated method stub
		return loader;
	}
	
	@Override
	public JavaFileObject getJavaFileForOutput(Location location,
			String className, Kind kind, FileObject sibling) throws IOException {
		
		javaClasses.put(className, new JavaClass(className, kind));
        return javaClasses.get(className);
	}
	
	private class CustomClassLoader extends ClassLoader{
		
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			// TODO Auto-generated method stub
			
			byte[] bytes = javaClasses.get(name).getByteCode(); 
			Class<?> retClass = super.defineClass(name, bytes, 0, bytes.length);

			return retClass;
		}
			
	}
	
}
