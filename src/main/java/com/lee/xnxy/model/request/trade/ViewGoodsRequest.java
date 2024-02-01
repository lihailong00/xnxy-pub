package com.lee.xnxy.model.request.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("ViewGoodsRequest: 用户打开页面时，异步向后端发送一个消息，实现商品浏览量+1")
@Data
public class ViewGoodsRequest {
    @ApiModelProperty("商品id")
    private String goodsId;
}
