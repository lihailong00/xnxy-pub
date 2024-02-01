package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.util.List;

@Data
public class ScoreTableAllTermDTO {
    private List<ScoreTableOneTermDTO> scoreTableOneTermDTOList;
}
