package com.lee.xnxy.model.request.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ApiModel("ListGoodsRequest: 分页显示物品")
@Data
@NoArgsConstructor
public class ListGoodsRequest {
    @ApiModelProperty("排序方式，有time和price两种")
    @NonNull
    private String cond;

    @ApiModelProperty("搜索关键字，有时候为空，表示不使用搜索。")
    private String keyword;

    @ApiModelProperty("页码")
    private Integer pageNumber;

    @ApiModelProperty("物品所属用户id，查询个人物品时需要")
    private String userId;
}
