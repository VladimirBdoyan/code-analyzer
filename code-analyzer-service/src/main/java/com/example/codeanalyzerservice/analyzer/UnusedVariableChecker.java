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
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public class UnusedVariableChecker implements Checker {
    private final MethodDeclaration md;
    private final AnalyzeResult arg;


    @Override
    public void check() {
        int coefficient = CodeSmellCategory.MEDIUM.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        System.out.println("Analyzing method: " + md.getName());
        if (!md.getBody().isPresent()) {
            return;
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
        if(!variableNames.isEmpty()){
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("Variable must be used");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - coefficient);
        }
    }
}
