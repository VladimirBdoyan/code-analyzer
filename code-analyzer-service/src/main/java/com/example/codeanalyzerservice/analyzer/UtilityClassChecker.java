package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public final class UtilityClassChecker {

    public static void check(ClassOrInterfaceDeclaration n, AnalyzeResult arg) {
        if (n.isInterface()) {
            return;
        }

        List<MethodDeclaration> methodDeclarations = n.getMethods();
        for (MethodDeclaration md : methodDeclarations) {
            if (!md.getModifiers().contains(Modifier.staticModifier())) {
                return;
            }
        }

        int coefficient = CodeSmellCategory.MEDIUM.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + 2 * coefficient);
        arg.setMaxRate(arg.getMaxRate() + 2 * coefficient);

        NodeList<Modifier> modifiers = n.getModifiers();

        if (!modifiers.contains(Modifier.finalModifier())) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Util class must be final");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.MEDIUM.getCoefficient());
        }

        List<ConstructorDeclaration> cd = n.getConstructors();

        if (cd.size() != 1 || !cd.get(0).getModifiers().contains(Modifier.privateModifier())) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Util class mast be have unique private constructor");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
