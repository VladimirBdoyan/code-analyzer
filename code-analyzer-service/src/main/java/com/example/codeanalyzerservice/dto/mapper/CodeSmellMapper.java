package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.CodeSmellDTO;
import com.example.codeanalyzerservice.entity.CodeSmell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CodeSmellMapper {

    private CodeSmellMapper() {

    }

    public static CodeSmellDTO mapToDTO(CodeSmell codeSmell){
        if (codeSmell==null){
            return null;
        }

        CodeSmellDTO codeSmellDTO=new CodeSmellDTO();
        codeSmellDTO.setMessage(codeSmell.getMessage());
        codeSmellDTO.setCategory(codeSmell.getCategory());
        codeSmellDTO.setId(codeSmell.getId());
        return codeSmellDTO;
    }

    public static List<CodeSmellDTO> mapToListDTO(List<CodeSmell> codeSmells){
        if (codeSmells == null || codeSmells.isEmpty()) {
            return new ArrayList<>();
        }

        return codeSmells.stream().map(CodeSmellMapper::mapToDTO).collect(Collectors.toList());
    }
}
