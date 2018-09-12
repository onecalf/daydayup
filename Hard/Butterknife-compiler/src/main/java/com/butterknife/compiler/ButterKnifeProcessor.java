package com.butterknife.compiler;


import com.butterknife.annotation.BindView;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {

    //支持的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //给到需要处理的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations() ){
            types.add(annotation.getCanonicalName());
        }

        return types;
    }

    /**
     * 代表的是，有注解就都会进来
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("========================>");
        System.out.println("========================>");
        System.out.println("========================>");
        System.out.println("========================>");

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);


        return false;
    }


    private Set<Class<? extends Annotation>> getSupportedAnnotations(){
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();

        //需要解析自定义的注解
        annotations.add(BindView.class);

        return annotations;
    }



}













