package com.lee.xnxy.mapper;

import com.lee.xnxy.model.bizRequest.trade.ListGoodsBizRequest;
import com.lee.xnxy.model.dao.trade.Trade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 晓龙coding
* @description 针对表【trade】的数据库操作Mapper
* @createDate 2023-02-03 13:30:54
* @Entity com.lee.xnxydev.pojo.Trade
*/
public interface TradeMapper extends BaseMapper<Trade> {
    List<Trade> listGoods(ListGoodsBizRequest listGoodsBizRequest);

    void viewGoods(Long goodsId);
}




