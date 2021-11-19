package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AbstractClassChecker implements Checker {

    private final ClassOrInterfaceDeclaration n;
    private final AnalyzeResult arg;


    @Override
    public void check() {
        int coefficient = CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        if (n.isInterface()) {
            return;
        }

        NodeList<Modifier> modifiers = n.getModifiers();

        if (!modifiers.contains(Modifier.abstractModifier())) {
            return;
        }

        if (modifiers.contains(Modifier.finalModifier())) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class cannot be declared as final");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }

        List<VariableDeclarator> variableDeclarators = n.findAll(VariableDeclarator.class);
        if (variableDeclarators.isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class without fields should be converted to interfaces");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }

        List<ConstructorDeclaration> constructorDeclarations = n.getConstructors();
        if (!constructorDeclarations.isEmpty()) {
            boolean hasdefaltOrPublicConstructor = false;
            for (ConstructorDeclaration cd : constructorDeclarations) {
                if (cd.getModifiers().contains(Modifier.publicModifier()) ||
                        cd.getModifiers().isEmpty()) {
                    hasdefaltOrPublicConstructor = true;
                }
            }
            if (!hasdefaltOrPublicConstructor) {
                CodeSmell codeSmell = new CodeSmell();
                codeSmell.setCategory(CodeSmellCategory.LOW);
                codeSmell.setMessage("Abstract class should not have private constructor");
                arg.getCodeSmells().add(codeSmell);
                arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
            }
        }

        List<MethodDeclaration> methodDeclarations = n.getMethods();
        int coefficient1 = CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient1);
        if (methodDeclarations.isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class must have methods");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());

        }
        boolean hasAbstractMethod = false;
        for (MethodDeclaration md : methodDeclarations) {
            if (md.getModifiers().contains(Modifier.abstractModifier())) {
                hasAbstractMethod = true;
            }
        }
        if (!hasAbstractMethod) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("An abstract class must have abstract methods");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
