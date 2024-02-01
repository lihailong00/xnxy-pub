package com.lee.xnxy.controller;

import com.lee.xnxy.aop.DynamicValidate;
import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.converter.trade.ListGoodsBizRequestConverter;
import com.lee.xnxy.converter.trade.SaveGoodsBizRequestConverter;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.trade.ListGoodsBizRequest;
import com.lee.xnxy.model.bizRequest.trade.SaveGoodsBizRequest;
import com.lee.xnxy.model.request.trade.DeleteGoodsRequest;
import com.lee.xnxy.model.request.trade.SaveGoodsRequest;
import com.lee.xnxy.model.request.trade.ListGoodsRequest;
import com.lee.xnxy.model.request.trade.ViewGoodsRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.TradeService;
import com.lee.xnxy.util.IPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 晓龙coding
 */
@RestController
@RequestMapping("/trade")
@Api("商品模块")
public class TradeController {
    @Resource
    private TradeService tradeService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("/list")
    @LogAnnotation(module="商品", operator="展示所有商品")
    @ApiOperation("获得商品信息")
    @DynamicValidate
    ResponseResult listGoods(@RequestBody @Valid ListGoodsRequest listGoodsRequest, @RequestHeader("token") String token) {
        ListGoodsBizRequest listGoodsBizRequest = ListGoodsBizRequestConverter.toListGoodsBizRequest(listGoodsRequest);
        return tradeService.listGoods(listGoodsBizRequest);
    }

    @PostMapping("/create")
    @LogAnnotation(module="商品", operator="发布商品")
    @ApiOperation("保存商品")
    ResponseResult saveGoods(@RequestBody @Valid SaveGoodsRequest saveGoodsRequest, @RequestHeader("token") String token) throws SysException {
        String ip = IPUtil.getIpAddr(httpServletRequest);
        SaveGoodsBizRequest saveGoodsBizRequest = SaveGoodsBizRequestConverter.toSaveGoodsBizRequest(saveGoodsRequest, ip);
        return tradeService.saveGoods(saveGoodsBizRequest);
    }

    @DynamicValidate
    @PostMapping("/delete")
    @LogAnnotation(module="商品", operator="删除商品记录")
    @ApiOperation("删除商品")
    ResponseResult deleteGoods(@RequestBody @Valid DeleteGoodsRequest deleteGoodsRequest) {
        Long gId = Long.valueOf(deleteGoodsRequest.getGoodsId());
        return tradeService.deleteGoods(gId);
    }

    @PostMapping("view")
    @ApiOperation("查看商品时，发送一个异步请求，实现浏览数+1")
    ResponseResult viewGoods(@RequestBody @Valid ViewGoodsRequest viewGoodsRequest) {
        Long goodsId = Long.valueOf(viewGoodsRequest.getGoodsId());
        return tradeService.viewGoods(goodsId);
    }
}
