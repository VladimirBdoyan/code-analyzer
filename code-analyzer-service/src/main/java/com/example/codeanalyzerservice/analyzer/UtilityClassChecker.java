package com.example.codeanalyzerservice.analyzer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class UtilityClassChecker implements Checker{

    private final ClassOrInterfaceDeclaration clazz;

    public UtilityClassChecker(ClassOrInterfaceDeclaration clazz) {
        this.clazz = clazz;
    }

    @Override
    public void check() {

        if (clazz.isInterface()){
            return;
        }

        List<MethodDeclaration> methodDeclarations=clazz.getMethods();
        for (MethodDeclaration md:methodDeclarations){
            if(!md.getModifiers().contains(Modifier.staticModifier())){
                return;
            }
        }

        NodeList<Modifier> modifiers=clazz.getModifiers();
        System.out.println("Analyzing class: " + clazz.getName());
        if (!modifiers.contains(Modifier.finalModifier())){
            System.out.println("Util class must be final");
        }

        List<ConstructorDeclaration> cd=clazz.getConstructors();

        if (cd.size()!=1||!cd.get(0).getModifiers().contains(Modifier.privateModifier())){
            System.out.println("Util class mast be have unique private constructor");
        }



    }
}
