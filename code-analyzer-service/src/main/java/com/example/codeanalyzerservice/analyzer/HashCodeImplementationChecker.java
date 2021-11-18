package com.example.codeanalyzerservice.analyzer;


import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HashCodeImplementationChecker implements Checker {

    private MethodDeclaration md;
    private Object arg;

    public HashCodeImplementationChecker(MethodDeclaration md, Object arg) {
        this.md = md;
        this.arg = arg;
    }

    @Override
    public void check() {
        System.out.println("Analyzing method: " + md.getName());
        if (!md.getBody().isPresent()) {
            System.out.println("Empty method body");
        }

        if (!md.getNameAsString().equals("hashCode")) {
            return;
        }

        BlockStmt body = md.getBody().get();
        List<ReturnStmt> returnStatements = body.findAll(ReturnStmt.class);

        if (returnStatements.size() > 1) {
            System.out.println("Bad score: multiple return statements in hashCodeMethod");
        }

        ReturnStmt returnStmt = returnStatements.get(0);

        List<FieldDeclaration> fields = md.getParentNode()
                .get().findAll(FieldDeclaration.class,
                        (fieldDeclaration -> fieldDeclaration.getAnnotations().stream()
                                    .map(AnnotationExpr::getNameAsString).anyMatch((s) -> s.equals("Id"))
                        ));

        Set<String> pkFields = new HashSet<>();

        for (FieldDeclaration fd : fields) {
            for (VariableDeclarator vd : fd.getVariables()) {
                pkFields.add(vd.getNameAsString());
            }
        }

        List<FieldDeclaration> unnecessaryFields = md.getParentNode()
                .get().findAll(FieldDeclaration.class,
                        (fieldDeclaration -> fieldDeclaration.getAnnotations().stream()
                                .map(AnnotationExpr::getNameAsString).anyMatch((s) -> !s.equals("Id"))
                        ));

        Set<String> unFields = new HashSet<>();

        for (FieldDeclaration fd : unnecessaryFields) {
            for (VariableDeclarator vd : fd.getVariables()) {
                unFields.add(vd.getNameAsString());
            }
        }

        Expression expression = returnStmt.getExpression().get();

        List<SimpleName> extraFields = expression.findAll(SimpleName.class,
                (sn) -> (!pkFields.contains(sn.asString())&& unFields.contains(sn.asString())));



        if (extraFields.size() > 0) {
            System.out.println("Bad code: Only @Id annotated fields must be in hashCode()");
        }


//        Set<String> variableNames = new HashSet<>();
//        for (Statement statement : body.getStatements()) {
//            System.out.println(statement);
//            List<VariableDeclarator> varDeclarations = statement.findAll(VariableDeclarator.class);
//            Set<String> newVars = new HashSet<>();
//            for (VariableDeclarator variableDeclarator : varDeclarations) {
//                variableNames.add(variableDeclarator.getNameAsString());
//                newVars.add(variableDeclarator.getNameAsString());
//            }
//
//            for (Node node: statement.getChildNodes()) {
//                Set<String> usages = node.findAll(SimpleName.class, (n) ->
//                        variableNames.contains(n.asString()) && !newVars.contains(n.asString())
//                ).stream().map(SimpleName::asString).collect(Collectors.toSet());
//
//                variableNames.removeAll(usages);
//            }
//        }
//
//        for (String unusedVar : variableNames) {
//            System.out.println("Unused variable: " + unusedVar);
//        }
    }
}
