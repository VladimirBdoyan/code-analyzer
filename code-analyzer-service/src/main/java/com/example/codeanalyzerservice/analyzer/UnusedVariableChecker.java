package com.example.codeanalyzerservice.analyzer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnusedVariableChecker implements Checker {
    private MethodDeclaration md;
    private Object arg;

    public UnusedVariableChecker(MethodDeclaration md, Object arg) {
        this.md = md;
        this.arg = arg;
    }

    @Override
    public void check() {
        System.out.println("Analyzing method: " + md.getName());
        if (!md.getBody().isPresent()) {
            System.out.println("Empty method body");
        }

        BlockStmt body = md.getBody().get();
        Set<String> variableNames = new HashSet<>();
        for (Statement statement : body.getStatements()) {
            System.out.println(statement);
            List<VariableDeclarator> varDeclarations = statement.findAll(VariableDeclarator.class);
            Set<String> newVars = new HashSet<>();
            for (VariableDeclarator variableDeclarator : varDeclarations) {
                variableNames.add(variableDeclarator.getNameAsString());
                newVars.add(variableDeclarator.getNameAsString());
            }

            for (Node node: statement.getChildNodes()) {
                Set<String> usages = node.findAll(SimpleName.class, (n) ->
                        variableNames.contains(n.asString()) && !newVars.contains(n.asString())
                ).stream().map(SimpleName::asString).collect(Collectors.toSet());

                variableNames.removeAll(usages);
            }
        }

        for (String unusedVar : variableNames) {
            System.out.println("Unused variable: " + unusedVar);
        }
    }
}
