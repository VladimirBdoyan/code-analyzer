package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
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
