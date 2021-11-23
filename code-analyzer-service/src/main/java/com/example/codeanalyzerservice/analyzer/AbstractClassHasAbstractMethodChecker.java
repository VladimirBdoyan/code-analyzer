package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AbstractClassHasAbstractMethodChecker {

    public static void check(ClassOrInterfaceDeclaration n, AnalyzeResult arg) {
        if (n.isInterface()) {
            return;
        }

        NodeList<Modifier> modifiers = n.getModifiers();

        if (!modifiers.contains(Modifier.abstractModifier())) {
            return;
        }

        int coefficient = CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        List<MethodDeclaration> methodDeclarations = n.getMethods();

        if (methodDeclarations.isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class should has methods");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
            return;
        }

        boolean hasAbstractMethod = false;
        for (MethodDeclaration md : methodDeclarations) {
            if (md.getModifiers().contains(Modifier.abstractModifier())) {
                hasAbstractMethod = true;
            }
        }

        if (!hasAbstractMethod) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class should has abstract methods");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
