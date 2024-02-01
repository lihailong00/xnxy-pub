package com.lee.xnxy.model.request.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

@ApiModel("SaveGoodsRequest: 保存物品请求")
@Data
public class SaveGoodsRequest {
    @ApiModelProperty("商品名")
    @Length(min = 2, max = 10, message = "商品描述在2-10字之间")
    private String name;

    @ApiModelProperty("商品描述")
    @Length(min = 5, max = 200, message = "商品描述在5-200字之间")
    private String content;

    @ApiModelProperty("商品价格")
    @Digits(integer = 10, fraction = 2, message = "价格格式不正确")
    private BigDecimal price;

    @ApiModelProperty("商品图片链接。一件商品可能有零至多个图片链接")
    private List<String> imageList;
}
