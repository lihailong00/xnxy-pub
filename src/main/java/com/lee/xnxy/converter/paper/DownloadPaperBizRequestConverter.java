package com.lee.xnxy.converter.paper;

import com.lee.xnxy.model.bizRequest.paper.DownloadPaperBizRequest;
import com.lee.xnxy.model.request.paper.DownloadPaperRequest;

public class DownloadPaperBizRequestConverter {
    private DownloadPaperBizRequestConverter() {

    }

    public static DownloadPaperBizRequest toDownloadPaperBizRequest(DownloadPaperRequest downloadPaperRequest) {
        DownloadPaperBizRequest downloadPaperBizRequest = new DownloadPaperBizRequest();
        downloadPaperBizRequest.setDownloadWay(downloadPaperRequest.getDownloadWay());
        downloadPaperBizRequest.setReceiverEmail(downloadPaperRequest.getReceiverEmail());
        downloadPaperBizRequest.setPaperId(downloadPaperRequest.getPaperId());

        return downloadPaperBizRequest;
    }
}
