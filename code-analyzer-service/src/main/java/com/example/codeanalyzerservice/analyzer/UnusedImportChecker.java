package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class UnusedImportChecker implements Checker {

    private final CompilationUnit n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        Set<String> imports = new HashSet<>();

        n.getImports().forEach(importDeclaration -> imports.add(importDeclaration.getNameAsString()));

        n.findAll(VariableDeclarator.class).forEach(variableDeclarator -> {
            Type type = variableDeclarator.getType();
            if (type.isClassOrInterfaceType()) {
                String packageName =  type.asClassOrInterfaceType()
                        .resolve()
                        .getTypeDeclaration()
                        .orElseThrow(RuntimeException::new)
                        .asReferenceType()
                        .getPackageName();

                imports.remove(packageName);
            }
        });

        if (!imports.isEmpty()) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("More than two variables should not be declared in one line");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
