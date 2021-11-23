package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import lombok.experimental.UtilityClass;


@UtilityClass
public final class OneLineVariablesDeclarationChecker {

    public static void check(CompilationUnit n, AnalyzeResult arg) {
        int coefficient = CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        n.findAll(VariableDeclarationExpr.class).forEach(variableDeclarationExpr -> {
            if (variableDeclarationExpr.getVariables().size() > 1) {
                CodeSmell codeSmell = new CodeSmell();
                codeSmell.setCategory(CodeSmellCategory.LOW);
                codeSmell.setMessage("More than two variables should not be declared in one line");
                arg.getCodeSmells().add(codeSmell);
                arg.setCurrentRate(arg.getCurrentRate() - coefficient);
            }
        });

        n.findAll(FieldDeclaration.class).forEach(fieldDeclaration -> {
            if (fieldDeclaration.getVariables().size() > 1) {
                CodeSmell codeSmell = new CodeSmell();
                codeSmell.setCategory(CodeSmellCategory.LOW);
                codeSmell.setMessage("More than two variables should not be declared in one line");
                arg.getCodeSmells().add(codeSmell);
                arg.setCurrentRate(arg.getCurrentRate() - coefficient);
            }
        });
    }
}
