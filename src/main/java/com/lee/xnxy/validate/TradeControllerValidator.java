package com.lee.xnxy.validate;

import com.lee.xnxy.model.request.trade.DeleteGoodsRequest;
import com.lee.xnxy.model.request.trade.ListGoodsRequest;
import com.lee.xnxy.model.dao.trade.Trade;
import com.lee.xnxy.service.TradeService;
import com.lee.xnxy.util.UserContextDTOUtil;
import com.lee.xnxy.validate.module.CheckGoodsSortConditionValidateModule;
import com.lee.xnxy.validate.module.HasRightOperateValidateModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TradeControllerValidator {
    @Resource
    private TradeService tradeService;

    @Resource
    private HasRightOperateValidateModule hasRightOperateValidateModule;

    @Resource
    private CheckGoodsSortConditionValidateModule checkGoodsSortConditionValidateModule;


    public void deleteGoods(DeleteGoodsRequest deleteGoodsRequest) {
        Long operatorUserId = UserContextDTOUtil.getUserContextDTO().getUserId();
        Long goodsId = Long.valueOf(deleteGoodsRequest.getGoodsId());
        Trade trade = tradeService.getById(goodsId);
        if (trade != null) {
            Long belongUserId = trade.getUId();
            hasRightOperateValidateModule.validate(operatorUserId, belongUserId);
        }
    }

    public void listGoods(ListGoodsRequest listGoodsRequest, String token) {
        String condition = listGoodsRequest.getCond();
        checkGoodsSortConditionValidateModule.validate(condition);
    }
}
