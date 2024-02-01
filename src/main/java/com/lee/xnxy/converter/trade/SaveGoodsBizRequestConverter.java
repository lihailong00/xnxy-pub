package com.lee.xnxy.converter.trade;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.trade.SaveGoodsBizRequest;
import com.lee.xnxy.model.request.trade.SaveGoodsRequest;
import com.lee.xnxy.util.UserContextDTOUtil;

import java.time.LocalDateTime;

public class SaveGoodsBizRequestConverter {
    private SaveGoodsBizRequestConverter() {

    }

    public static SaveGoodsBizRequest toSaveGoodsBizRequest(SaveGoodsRequest saveGoodsRequest, String ip) {
        SaveGoodsBizRequest saveGoodsBizRequest = new SaveGoodsBizRequest();
        saveGoodsBizRequest.setUId(UserContextDTOUtil.getUserContextDTO().getUserId());
        saveGoodsBizRequest.setName(saveGoodsRequest.getName());
        saveGoodsBizRequest.setContent(saveGoodsRequest.getContent());
        saveGoodsBizRequest.setPrice(saveGoodsRequest.getPrice());
        saveGoodsBizRequest.setImageList(saveGoodsRequest.getImageList());
        saveGoodsBizRequest.setIp(ip);
        saveGoodsBizRequest.setHasDeleted(CommonConstant.NOT_DELETE);
        saveGoodsBizRequest.setCreateTime(LocalDateTime.now());
        return saveGoodsBizRequest;
    }
}
