package com.example.findbugs.analyzer;

import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.entity.enums.CodeSmellCategory;
import com.github.javaparser.ast.stmt.CatchClause;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NullPointerExceptionCaughtChecker implements Checker {
    private final CatchClause n;
    private final AnalyzeResult arg;


    @Override
    public void check() {
        int coefficient= CodeSmellCategory.HIGH.getCoefficient();
        arg.setCurrentRate(arg.getCurrentRate()+coefficient);
        arg.setMaxRate(arg.getMaxRate()+coefficient);

        if (n.getParameter().getType().asString().contains("NullPointerException")) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("NullPointer Exception should not be caught");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
