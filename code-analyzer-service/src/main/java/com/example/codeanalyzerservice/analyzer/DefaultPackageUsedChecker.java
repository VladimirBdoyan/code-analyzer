package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class DefaultPackageUsedChecker implements Checker {
    private final CompilationUnit n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        if (n.getPackageDeclaration().isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Default package should not be used");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
