package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.util.List;

@Data
public class ScoreTableOneTermDTO {
    private String term;
    private List<ScoreTableDetailDTO> scoreTableDetailDTOList;
}
