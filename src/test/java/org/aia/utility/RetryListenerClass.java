package org.aia.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListenerClass implements IAnnotationTransformer {
   String env=System.getenv("Server");
	@Override
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		// IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
	if(env.equalsIgnoreCase("Local")) {
	 	testannotation.setRetryAnalyzer(FailedTestRun.class);
	       }else {
	    	   System.out.println("Please set correct environment variable");
	       }
	}

}
