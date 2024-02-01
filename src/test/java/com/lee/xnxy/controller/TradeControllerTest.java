package com.lee.xnxy.controller;

import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.request.trade.DeleteGoodsRequest;
import com.lee.xnxy.model.request.trade.ListGoodsRequest;
import com.lee.xnxy.model.request.trade.SaveGoodsRequest;
import com.lee.xnxy.model.request.trade.ViewGoodsRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
public class TradeControllerTest {
    @Resource
    private TradeController tradeController;

    @Test
    void listGoods() {
        ListGoodsRequest listGoodsRequest = new ListGoodsRequest();
        listGoodsRequest.setPageNumber(1);
        listGoodsRequest.setCond("time");
        ResponseResult responseResult = tradeController.listGoods(listGoodsRequest, "");
        assert responseResult.getSuccess();
    }

    @Test
    void saveGoods() throws SysException {
        SaveGoodsRequest saveGoodsRequest = new SaveGoodsRequest();
        saveGoodsRequest.setName("测试名字");
        saveGoodsRequest.setPrice(new BigDecimal(0));
        saveGoodsRequest.setContent("测试内容");
        ResponseResult responseResult = tradeController.saveGoods(saveGoodsRequest, "");
        assert responseResult.getSuccess();
    }

    @Test
    void deleteGoods() {
        DeleteGoodsRequest deleteGoodsRequest = new DeleteGoodsRequest();
        deleteGoodsRequest.setGoodsId("-1");
        ResponseResult responseResult = tradeController.deleteGoods(deleteGoodsRequest);
        assert responseResult != null;
    }

    @Test
    void viewGoods() {
        ViewGoodsRequest viewGoodsRequest = new ViewGoodsRequest();
        viewGoodsRequest.setGoodsId("1");
        ResponseResult responseResult = tradeController.viewGoods(viewGoodsRequest);
        assert responseResult.getSuccess();
    }
}
