package com.lee.xnxy.converter.trade;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.trade.ListGoodsBizRequest;
import com.lee.xnxy.model.request.trade.ListGoodsRequest;
import org.apache.commons.lang3.StringUtils;

public class ListGoodsBizRequestConverter {
    private ListGoodsBizRequestConverter() {

    }

    public static ListGoodsBizRequest toListGoodsBizRequest(ListGoodsRequest listGoodsRequest) {
        ListGoodsBizRequest listGoodsBizRequest = new ListGoodsBizRequest();
        listGoodsBizRequest.setCond(listGoodsRequest.getCond());
        listGoodsBizRequest.setKeyword(listGoodsRequest.getKeyword());
        listGoodsBizRequest.setPageNumber(listGoodsRequest.getPageNumber());
        listGoodsBizRequest.setPageSize(CommonConstant.PAGE_SIZE);
        if (StringUtils.isNotEmpty(listGoodsRequest.getUserId())) {
            listGoodsBizRequest.setUserId(Long.valueOf(listGoodsRequest.getUserId()));
        }
        return listGoodsBizRequest;
    }
}
