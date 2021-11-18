package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OneLineVariablesDeclarationChecker implements Checker {
    private final CompilationUnit n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        n.findAll(VariableDeclarationExpr.class).forEach(variableDeclarationExpr -> {
            if (variableDeclarationExpr.getVariables().size() > 1) {
                CodeSmell codeSmell = new CodeSmell();
                codeSmell.setCategory(CodeSmellCategory.LOW);
                codeSmell.setMessage("More than two variables should not be declared in one line");
                arg.getCodeSmells().add(codeSmell);
                arg.setCurrentRate(arg.getCurrentRate()-coefficient);
            }
        });

        n.findAll(FieldDeclaration.class).forEach(fieldDeclaration -> {
            if (fieldDeclaration.getVariables().size() > 1) {
                CodeSmell codeSmell = new CodeSmell();
                codeSmell.setCategory(CodeSmellCategory.LOW);
                codeSmell.setMessage("More than two variables should not be declared in one line");
                arg.getCodeSmells().add(codeSmell);
                arg.setCurrentRate(arg.getCurrentRate()-coefficient);
            }
        });
    }
}
