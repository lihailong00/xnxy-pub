package com.lee.xnxy.converter.paper;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.paper.ListPaperBizRequest;
import com.lee.xnxy.model.request.paper.ListPaperRequest;
import org.apache.commons.lang3.StringUtils;

public class ListPaperBizRequestConverter {
    private ListPaperBizRequestConverter() {

    }

    public static ListPaperBizRequest toListPaperBizRequest(ListPaperRequest listPaperRequest) {
        ListPaperBizRequest listPaperBizRequest = new ListPaperBizRequest();
        listPaperBizRequest.setPageNumber(listPaperRequest.getPageNumber());
        listPaperBizRequest.setPageSize(CommonConstant.PAGE_SIZE);
        listPaperBizRequest.setKeyword(getKeyWordForSearch(listPaperRequest.getKeyword()));
        return listPaperBizRequest;
    }

    private static String getKeyWordForSearch(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return keyword;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyword.length(); i++) {
            sb.append(keyword.charAt(i));
            sb.append("%");
        }
        return sb.toString();
    }
}
