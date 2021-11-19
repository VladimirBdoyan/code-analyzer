package com.example.codeanalyzerservice.analyzer;

import com.example.codeanalyzerservice.constants.JavaKeyWords;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
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

        if (n.getParameter().getType().asString().contains(JavaKeyWords.NULL_POINTER_EXCEPTION)) {
            CodeSmell codeSmell = new CodeSmell();
            codeSmell.setCategory(CodeSmellCategory.MEDIUM);
            codeSmell.setMessage("NullPointer Exception should not be caught");
            arg.getCodeSmells().add(codeSmell);
            arg.setCurrentRate(arg.getCurrentRate()-coefficient);
        }
    }
}
