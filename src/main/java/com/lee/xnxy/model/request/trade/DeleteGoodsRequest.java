package com.lee.xnxy.model.request.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("DeleteGoodsRequest: 删除物品")
@Data
public class DeleteGoodsRequest {
    @ApiModelProperty("物品id")
    private String goodsId;
}
