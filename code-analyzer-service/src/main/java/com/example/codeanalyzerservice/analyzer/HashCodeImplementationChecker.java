package com.example.codeanalyzerservice.analyzer;


import com.example.codeanalyzerservice.constants.JavaKeyWords;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class HashCodeImplementationChecker implements Checker {

    private final MethodDeclaration md;
    private final AnalyzeResult arg;


    @Override
    public void check() {
        if (!md.getBody().isPresent()) {
            return;
        }

        if (!md.getNameAsString().equals(JavaKeyWords.HASH_CODE)) {
            return;
        }

        int coefficient = CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate() + coefficient);
        arg.setMaxRate(arg.getMaxRate() + coefficient);

        BlockStmt body = md.getBody().get();
        List<ReturnStmt> returnStatements = body.findAll(ReturnStmt.class);

        if (returnStatements.size() > 1) {
            arg.setCurrentRate(arg.getCurrentRate() + coefficient);
            arg.setMaxRate(arg.getMaxRate() + coefficient);
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("Bad score: multiple return statements in hashCodeMethod");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.MEDIUM.getCoefficient());
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
                (sn) -> (!pkFields.contains(sn.asString()) && unFields.contains(sn.asString())));


        if (extraFields.size() > 0) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.LOW);
            codeSmell.setMessage("Bad code: Only @Id annotated fields must be in hashCode()");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate() - CodeSmellCategory.LOW.getCoefficient());
        }
    }
}
