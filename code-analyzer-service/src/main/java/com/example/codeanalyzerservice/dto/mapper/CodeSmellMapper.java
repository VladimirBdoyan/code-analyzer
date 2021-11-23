package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.CodeSmellDTO;
import com.example.codeanalyzerservice.entity.CodeSmell;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public final class CodeSmellMapper {

    public static CodeSmellDTO mapToDTO(CodeSmell entity){
        if (entity == null){
            return null;
        }

        CodeSmellDTO dto = new CodeSmellDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setCategory(entity.getCategory());

        return dto;
    }

    public static List<CodeSmellDTO> mapToListDTO(List<CodeSmell> codeSmells){
        if (codeSmells == null || codeSmells.isEmpty()) {
            return new ArrayList<>();
        }

        return codeSmells.stream().map(CodeSmellMapper::mapToDTO).collect(Collectors.toList());
    }
}
