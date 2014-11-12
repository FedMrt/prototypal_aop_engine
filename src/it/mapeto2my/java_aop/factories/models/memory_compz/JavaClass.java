package it.mapeto2my.java_aop.factories.models.memory_compz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class JavaClass  extends SimpleJavaFileObject{

	private ByteArrayOutputStream oStream;
	
	public JavaClass(String className, Kind kind) {
		super(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind);
		oStream = new ByteArrayOutputStream();
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		
		return oStream;
	}
	
	public byte[] getByteCode() {
		return oStream.toByteArray();
	}
	
}
