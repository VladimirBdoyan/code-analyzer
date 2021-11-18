package com.example.findbugs.dto.mapper;

import com.example.findbugs.dto.CodeSmellDTO;
import com.example.findbugs.entity.CodeSmell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CodeSmellMapper {
    private CodeSmellMapper(){}

    public static CodeSmellDTO mapToDTO(CodeSmell codeSmell){
        if (codeSmell==null){
            return null;
        }
        CodeSmellDTO codeSmellDTO=new CodeSmellDTO();
        codeSmellDTO.setMessage(codeSmell.getMessage());
        codeSmellDTO.setCategory(codeSmell.getCategory());
        codeSmellDTO.setFileName(codeSmell.getFileName());
        codeSmellDTO.setId(codeSmell.getId());
        return codeSmellDTO;
    }

    public static List<CodeSmellDTO> mapToListDTO(List<CodeSmell> codeSmells){
        if (codeSmells==null||codeSmells.isEmpty()){
            return new ArrayList<>();
        }

        return codeSmells.stream().map(codeSmell -> mapToDTO(codeSmell)).collect(Collectors.toList());
    }
}
