package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class DefaultPackageUsedChecker {

    public static void check(CompilationUnit n, AnalyzeResult arg) {
        int coefficient= CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        if (!n.getPackageDeclaration().isPresent()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Default package should not be used");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
