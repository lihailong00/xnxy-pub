package com.lee.xnxy.model.bizRequest.paper;

import lombok.Data;


@Data
public class DownloadPaperBizRequest {
    private String downloadWay;

    private String receiverEmail;

    private String paperId;

    private String token;
}
