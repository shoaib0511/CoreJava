package com.edu.abhi.annotations.processors;

import com.edu.abhi.annotations.Metrics;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.reflect.Method;
import java.util.Set;

@SupportedAnnotationTypes("com.edu.abhi.annotations.Metrics")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class MetricsProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> arg0,
			RoundEnvironment roundEnv) {

    	StringBuilder message = new StringBuilder();

    	for (Element elem : roundEnv.getElementsAnnotatedWith(Metrics.class)) {
    		Metrics implementation = elem.getAnnotation(Metrics.class);

        	message.append("Methods found in " + elem.getSimpleName().toString() + ":\n");

        	for (Method method : implementation.getClass().getMethods()) {
        		message.append(method.getName() + "\n");
	        }

        	processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message.toString());
    	}

		return false; // allow others to process this annotation type
	}
}
