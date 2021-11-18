package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.body.MethodDeclaration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MethodNameChecker implements Checker {
    private final MethodDeclaration n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);
        char c = n.getName().getIdentifier().charAt(0);

        if (Character.isUpperCase(c)) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.HIGH);
            codeSmell.setMessage("Class Name Must Start With Uppercase Character");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
