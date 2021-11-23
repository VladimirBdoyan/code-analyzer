package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;

@Component
public class Visitor extends VoidVisitorAdapter<AnalyzeResult> {

    @Override
    public void visit(CompilationUnit n, AnalyzeResult arg) {
        super.visit(n, arg);
        DefaultPackageUsedChecker.check(n, arg);
        UnusedImportChecker.check(n, arg);
        CollectionInterfaceUsedChecker.check(n, arg);
        OneLineVariablesDeclarationChecker.check(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, AnalyzeResult arg) {
        super.visit(n, arg);
        MethodNameChecker.check(n, arg);
        OptionalAsMethodArgumentChecker.check(n, arg);
        ResourceClosedChecker.check(n, arg);
        EntityHashCodeImplementationChecker.check(n, arg);
        UnusedVariableChecker.check(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, AnalyzeResult arg) {
        super.visit(n, arg);
        ClassNameChecker.check(n, arg);
        AbstractClassHasAbstractMethodChecker.check(n, arg);
        AbstractClassHasFieldsChecker.check(n, arg);
        UtilityClassChecker.check(n, arg);
    }

    @Override
    public void visit(CatchClause n, AnalyzeResult arg) {
        super.visit(n, arg);
        NullPointerExceptionCaughtChecker.check(n, arg);
    }
}
