package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public final class UnusedVariableChecker {

    public static void check(MethodDeclaration n, AnalyzeResult arg) {
        int coefficient = CodeSmellCategory.MEDIUM.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        if (!n.getBody().isPresent()) {
            return;
        }

        BlockStmt body = n.getBody().get();
        Set<String> variableNames = new HashSet<>();
        for (Statement statement : body.getStatements()) {
            List<VariableDeclarator> varDeclarations = statement.findAll(VariableDeclarator.class);
            Set<String> newVars = new HashSet<>();
            for (VariableDeclarator variableDeclarator : varDeclarations) {
                variableNames.add(variableDeclarator.getNameAsString());
                newVars.add(variableDeclarator.getNameAsString());
            }

            for (Node node: statement.getChildNodes()) {
                Set<String> usages = node.findAll(SimpleName.class, (nd) ->
                        variableNames.contains(nd.asString()) && !newVars.contains(nd.asString())
                ).stream().map(SimpleName::asString).collect(Collectors.toSet());

                variableNames.removeAll(usages);
            }
        }

        if(!variableNames.isEmpty()){
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("Should not be unused variable");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - coefficient);
        }
    }
}
