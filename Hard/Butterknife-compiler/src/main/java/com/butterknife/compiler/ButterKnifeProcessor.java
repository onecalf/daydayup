package com.butterknife.compiler;


import com.butterknife.annotation.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;


@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {

    //支持的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //需要处理的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations() ){
            types.add(annotation.getCanonicalName());
        }

        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations(){
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();

        //需要解析自定义的注解
        annotations.add(BindView.class);

        return annotations;
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

        String filename = "/tmp/" + "butterKnife_" + System.currentTimeMillis() + ".txt";
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);

//        //1 解析属性  activity -> List<Element>
//        Map<Element,List<Element>> elementMap = new LinkedHashMap<>();
//
//        for (Element element : elements){
//            Element enclosingElement = element.getEnclosingElement();
//            List<Element> viewBindElements = elementMap.get(enclosingElement);
//            if(viewBindElements == null){
//                viewBindElements = new ArrayList<>();
//                elementMap.put(enclosingElement,viewBindElements);
//            }
//
//            viewBindElements.add(element);
//        }
//
//
//        //2 生成代码
//        for (Map.Entry<Element,List<Element>> entry : elementMap.entrySet()){
//            Element enclosingElement = entry.getKey();
//            List<Element> viewBindElement = entry.getValue();
//
//            String activityClassNameStr = enclosingElement.getSimpleName().toString();
//            TypeSpec.Builder classBuilder = TypeSpec.classBuilder("_ViewBinding")
//                    .addModifiers(Modifier.FINAL,Modifier.PUBLIC);
//
//        }


        return false;
    }





}













