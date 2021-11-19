package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.constants.JavaKeyWords;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class ResourceClosedChecker implements Checker {

    private final MethodDeclaration n;
    private final AnalyzeResult arg;

    @Override
    public void check() {
        if (!n.getBody().isPresent()) {
            return;
        }

        int coefficient = CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        Set<String> closable = new HashSet<>();

        List<VariableDeclarationExpr> variables = n.getBody().get().findAll(VariableDeclarationExpr.class);
        for (VariableDeclarationExpr variable : variables) {
            variable.getVariables().forEach(variableDeclarator -> {
                ResolvedType type = variableDeclarator.resolve().getType();
                if (type.isReference()) {
                    List<ResolvedReferenceType> allInterfacesAncestors = type.asReferenceType().getAllInterfacesAncestors();
                    System.out.println(allInterfacesAncestors);
                    for (ResolvedReferenceType resolvedReferenceType : allInterfacesAncestors) {
                        if (JavaKeyWords.CLOSEABLE.equals(resolvedReferenceType.getQualifiedName())) {
                            closable.add(variableDeclarator.getName().asString());
                        }
                    }
                }
            });
        }

        List<ExpressionStmt> all = n.findAll(ExpressionStmt.class);

        int closeCallExpCount = 0;
        for (ExpressionStmt expressionStmt : all) {
            if (expressionStmt.getExpression().isMethodCallExpr()) {
                for (String var : closable) {
                    if (!expressionStmt
                            .getExpression().asMethodCallExpr()
                            .getName().getIdentifier().contains(var + ".close()")) {
                        closeCallExpCount++;
                    }
                }
            }
        }

        if (closable.size() != closeCallExpCount) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.HIGH);
            codeSmell.setMessage("More than two variables should not be declared in one line");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}

