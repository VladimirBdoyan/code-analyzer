package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.body.MethodDeclaration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OptionalAsMethodArgumentChecker implements Checker {
    private final MethodDeclaration n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.MEDIUM.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        boolean hasOptionalAsArgument = n.getParameters().stream()
                .anyMatch(parameter -> parameter.getType().asString().startsWith("Optional"));
        if (hasOptionalAsArgument) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("Optional should not be used as method argument");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
