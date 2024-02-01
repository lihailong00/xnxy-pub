package com.lee.xnxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.bizRequest.trade.ListGoodsBizRequest;
import com.lee.xnxy.model.bizRequest.trade.SaveGoodsBizRequest;
import com.lee.xnxy.model.dao.trade.Trade;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 晓龙coding
* @description 针对表【trade】的数据库操作Service
* @createDate 2023-02-03 12:57:14
*/
public interface TradeService extends IService<Trade> {

    ResponseResult listGoods(ListGoodsBizRequest listGoodsBizRequest);

    ResponseResult saveGoods(SaveGoodsBizRequest saveGoodsBizRequest);

    ResponseResult deleteGoods(Long gId);

    ResponseResult viewGoods(Long goodsId);
}
