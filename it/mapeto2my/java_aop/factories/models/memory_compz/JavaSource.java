package it.mapeto2my.java_aop.factories.models.memory_compz;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class JavaSource  extends SimpleJavaFileObject{

	private String sourceCode;
	
	public JavaSource(String fullName, StringBuffer sourceCode) {
		super(URI.create("string:///" + fullName.replace('.', '/')+ Kind.SOURCE.extension), Kind.SOURCE);
		
		this.sourceCode = sourceCode.toString();
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		
		return sourceCode;
	}
	
}
