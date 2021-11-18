package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.resolution.types.ResolvedType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CollectionInterfaceUsedChecker implements Checker {
    private final CompilationUnit n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        int coefficient= CodeSmellCategory.LOW.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        List<VariableDeclarator> variables = n.findAll(VariableDeclarator.class);

        for (VariableDeclarator variable :  variables) {
            ResolvedType type = variable.resolve().getType();

            if (type.isReference()) {
                if (type.asReferenceType().getTypeDeclaration().orElseThrow().isClass()) {
                    boolean isCollectionLCass = type.asReferenceType().getAllAncestors().stream().
                            anyMatch(resolvedReferenceType ->
                                    "java.util.Collection".equals(resolvedReferenceType.getQualifiedName())
                            );

                    if (isCollectionLCass) {
                        CodeSmell codeSmell = new CodeSmell();
                        codeSmell.setCategory(CodeSmellCategory.LOW);
                        codeSmell.setMessage("Collection interface must me used");
                        arg.getCodeSmells().add(codeSmell);
                        arg.setCurrentRate(arg.getCurrentRate()-coefficient);
                    }
                }
            }
        }
    }
}
