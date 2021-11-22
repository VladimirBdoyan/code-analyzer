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
//        new DefaultPackageUsedChecker(n, arg).check();
//        new UnusedImportChecker(n, arg).check();
//        new CollectionInterfaceUsedChecker(n, arg).check();
//        new OneLineVariablesDeclarationChecker(n, arg).check();
    }

    @Override
    public void visit(MethodDeclaration n, AnalyzeResult arg) {
        super.visit(n, arg);
        new MethodNameChecker(n, arg).check();
        new OptionalAsMethodArgumentChecker(n, arg).check();
//        new ResourceClosedChecker(n, arg).check();
        new HashCodeImplementationChecker(n,arg).check();
        new UnusedVariableChecker(n,arg).check();
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, AnalyzeResult arg) {
        super.visit(n, arg);
        new ClassNameChecker(n, arg).check();
        new AbstractClassChecker(n,arg).check();
        new UtilityClassChecker(n,arg).check();
    }

    @Override
    public void visit(CatchClause n, AnalyzeResult arg) {
        super.visit(n, arg);
        new NullPointerExceptionCaughtChecker(n, arg).check();
    }
}
