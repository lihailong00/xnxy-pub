package com.lee.xnxy.model.request.paper;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListPaperRequest {
    @NotNull
    private Integer pageNumber;

    private String keyword;
}
