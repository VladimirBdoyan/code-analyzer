package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public final class AbstractClassHasFieldsChecker {

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

        List<VariableDeclarator> variableDeclarators = n.findAll(VariableDeclarator.class);
        if (variableDeclarators.isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Abstract class without fields should be converted to interface");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
