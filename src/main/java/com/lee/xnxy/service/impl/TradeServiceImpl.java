package com.lee.xnxy.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.mapper.TradeMapper;
import com.lee.xnxy.model.bizRequest.trade.ListGoodsBizRequest;
import com.lee.xnxy.model.bizRequest.trade.SaveGoodsBizRequest;
import com.lee.xnxy.model.dto.trade.ListGoodsDTO;
import com.lee.xnxy.model.dao.trade.Trade;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.TradeService;
import com.lee.xnxy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 晓龙coding
* @description 针对表【trade】的数据库操作Service实现
* @createDate 2023-02-03 12:57:14
*/
@Service
@Slf4j
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade>
    implements TradeService {
    @Resource
    private UserService userService;

    @Resource
    private TradeMapper tradeMapper;

    @Override
    public ResponseResult listGoods(ListGoodsBizRequest listGoodsBizRequest) {
        List<Trade> trades = tradeMapper.listGoods(listGoodsBizRequest);
        List<ListGoodsDTO> listGoodsDTOList = tradeList2VO(trades);
        return ResponseResult.success(listGoodsDTOList);
    }

    private List<ListGoodsDTO> tradeList2VO(List<Trade> trades) {
        List<ListGoodsDTO> listGoodsDTOList = trades.parallelStream().map(trade -> {
            ListGoodsDTO listGoodsDTO = new ListGoodsDTO();
            listGoodsDTO.setGId(trade.getGId().toString());
            listGoodsDTO.setName(trade.getName());
            listGoodsDTO.setContent(trade.getContent());
            listGoodsDTO.setPrice(trade.getPrice());
            listGoodsDTO.setImageList(JSON.parseArray(trade.getImageList(), String.class));
            listGoodsDTO.setCreateTime(trade.getCreateTime());
            listGoodsDTO.setViewCount(trade.getViewCount());

            // 通过 uId 查询username
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUId, trade.getUId());
            User one = userService.getOne(queryWrapper);
            if (Objects.isNull(one)) {
                listGoodsDTO.setUsername("null user");
            } else {
                listGoodsDTO.setUsername(one.getUsername());
            }
            return listGoodsDTO;
        }).collect(Collectors.toList());
        return listGoodsDTOList;
    }

    @Override
    public ResponseResult saveGoods(SaveGoodsBizRequest saveGoodsBizRequest) {
        Trade trade = toTrade(saveGoodsBizRequest);
        this.save(trade);
        return ResponseResult.success();
    }

    private Trade toTrade(SaveGoodsBizRequest saveGoodsBizRequest) {
        Trade trade = new Trade();
        trade.setName(saveGoodsBizRequest.getName());
        trade.setContent(saveGoodsBizRequest.getContent());
        trade.setImageList(JSON.toJSONString(saveGoodsBizRequest.getImageList()));
        trade.setPrice(saveGoodsBizRequest.getPrice());
        trade.setUId(saveGoodsBizRequest.getUId());
        trade.setHasDeleted(saveGoodsBizRequest.getHasDeleted());
        trade.setCreateTime(saveGoodsBizRequest.getCreateTime());
        trade.setIp(saveGoodsBizRequest.getIp());
        return trade;
    }

    @Override
    public ResponseResult deleteGoods(Long gId) {
        LambdaUpdateWrapper<Trade> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Trade::getGId, gId);
        updateWrapper.set(Trade::getHasDeleted, CommonConstant.DELETE);
        boolean hasDeleted = this.update(updateWrapper);
        if (hasDeleted) {
            return ResponseResult.success();
        }
        return ResponseResult.fail(CommonConstant.SYSTEM_ERROR);
    }

    @Override
    public ResponseResult viewGoods(Long goodsId) {
        tradeMapper.viewGoods(goodsId);
        return ResponseResult.success();
    }
}




