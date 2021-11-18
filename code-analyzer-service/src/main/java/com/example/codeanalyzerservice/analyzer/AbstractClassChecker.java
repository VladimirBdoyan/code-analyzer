package com.example.codeanalyzerservice.analyzer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.List;

public class AbstractClassChecker implements Checker{
    private final ClassOrInterfaceDeclaration clazz;

    public AbstractClassChecker(ClassOrInterfaceDeclaration clazz) {
        this.clazz = clazz;
    }

    @Override
    public void check() {
        if (clazz.isInterface()){
            return;
        }

        NodeList<Modifier> modifiers=clazz.getModifiers();
        System.out.println("Analyzing class: " + clazz.getName());
        if (!modifiers.contains(Modifier.abstractModifier())){
            return;
        }

        if (modifiers.contains(Modifier.finalModifier())){
            System.out.println("An abstract class cannot be declared as final");
        }

        List<VariableDeclarator> variableDeclarators=clazz.findAll(VariableDeclarator.class);
        if (variableDeclarators.isEmpty()){
            System.out.println(clazz.getName()+": Abstract class without fields should be converted to interfaces");

        }

        List<ConstructorDeclaration> constructorDeclarations=clazz.getConstructors();
        if (!constructorDeclarations.isEmpty()) {
            boolean hasdefaltOrPublicConstructor = false;
            for (ConstructorDeclaration cd : constructorDeclarations) {
                if (cd.getModifiers().contains(Modifier.publicModifier())||
                        cd.getModifiers().isEmpty()){
                    hasdefaltOrPublicConstructor=true;
                }
            }
            if (!hasdefaltOrPublicConstructor){
                System.out.println("An abstract class must have non private constructor");
            }
        }

        List<MethodDeclaration> methodDeclarations=clazz.getMethods();
        if (methodDeclarations.isEmpty()){
            System.out.println("An abstract class must have methods");
            return;
        }
        boolean hasAbstractMethod=false;
        for (MethodDeclaration md:methodDeclarations){
            if (md.getModifiers().contains(Modifier.abstractModifier())){
                hasAbstractMethod=true;
            }
        }
        if (!hasAbstractMethod){
            System.out.println("An abstract class must have abstract methods");
        }
    }
}
