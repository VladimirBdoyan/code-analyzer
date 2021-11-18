package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
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
        if (n.getBody().isEmpty()) {
            return;
        }

        int coefficient= CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        Set<String> closable = new HashSet<>();

        List<VariableDeclarationExpr> variables = n.getBody().get().findAll(VariableDeclarationExpr.class);
        for (VariableDeclarationExpr variable : variables) {
            variable.getVariables().forEach(variableDeclarator -> {
                ResolvedType type = variableDeclarator.resolve().getType();
                if (type.isReference()) {
                    List<ResolvedReferenceType> allInterfacesAncestors = type.asReferenceType().getAllInterfacesAncestors();
                    System.out.println(allInterfacesAncestors);
                    for (ResolvedReferenceType resolvedReferenceType : allInterfacesAncestors) {
                        if ("java.io.Closeable".equals(resolvedReferenceType.getQualifiedName())) {
                            closable.add(variableDeclarator.getName().asString());
                        }
                    }
                }
            });
        }

        System.out.println(closable);

        List<ExpressionStmt> all = n.findAll(ExpressionStmt.class);

        int methodCallExpCount = 0;
        for (ExpressionStmt expressionStmt : all) {
            if (expressionStmt.getExpression().isMethodCallExpr()) {
                System.out.println(expressionStmt.getExpression().asMethodCallExpr().getName());
                for (String var : closable) {
                    if(!expressionStmt
                            .getExpression().asMethodCallExpr()
                            .getName().getIdentifier().contains(var + ".close()")) {
                     methodCallExpCount++;
                    }
                }
            }
        }
    }
}

