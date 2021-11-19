package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UtilityClassChecker implements Checker {

    private final ClassOrInterfaceDeclaration clazz;
    private final AnalyzeResult arg;


    @Override
    public void check() {
        if (clazz.isInterface()) {
            return;
        }

        List<MethodDeclaration> methodDeclarations = clazz.getMethods();
        for (MethodDeclaration md : methodDeclarations) {
            if (!md.getModifiers().contains(Modifier.staticModifier())) {
                return;
            }
        }

        int coefficient = CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        NodeList<Modifier> modifiers = clazz.getModifiers();

        if (!modifiers.contains(Modifier.finalModifier())) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("Util class must be final");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.MEDIUM.getCoefficient());
        }

        List<ConstructorDeclaration> cd = clazz.getConstructors();

        if (cd.size() != 1 || !cd.get(0).getModifiers().contains(Modifier.privateModifier())) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Util class mast be have unique private constructor");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
