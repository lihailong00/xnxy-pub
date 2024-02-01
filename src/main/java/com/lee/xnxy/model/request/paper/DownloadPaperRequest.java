package com.lee.xnxy.model.request.paper;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class DownloadPaperRequest {
    private String downloadWay;

    private String receiverEmail;

    @NotEmpty
    private String paperId;
}
