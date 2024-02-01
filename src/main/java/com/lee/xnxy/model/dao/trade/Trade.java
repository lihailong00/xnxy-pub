package com.lee.xnxy.model.dao.trade;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @author 晓龙coding
 * @TableName xnxy_trade
 */
@TableName(value ="xnxy_trade")
@Data
public class Trade implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "g_id")
    private Long gId;

    /**
     * 发布者id
     */
    @TableField(value = "u_id")
    private Long uId;

    /**
     * 商品名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "content")
    private String content;

    /**
     * 商品图片链接数组
     */
    @TableField(value = "image_list")
    private String imageList;

    /**
     * 商品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 商品是否被删除
     */
    @TableField(value = "has_deleted")
    private Integer hasDeleted;

    /**
     * 商品创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 商品发布ip
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * ip属地
     */
    @TableField(value = "ip_area")
    private String ipArea;

    /**
     * 查看次数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (!Objects.equals(gId, trade.gId)) return false;
        if (!Objects.equals(uId, trade.uId)) return false;
        if (!Objects.equals(name, trade.name)) return false;
        if (!Objects.equals(content, trade.content)) return false;
        if (!Objects.equals(imageList, trade.imageList)) return false;
        if (!Objects.equals(price, trade.price)) return false;
        if (!Objects.equals(hasDeleted, trade.hasDeleted)) return false;
        if (!Objects.equals(createTime, trade.createTime)) return false;
        if (!Objects.equals(ip, trade.ip)) return false;
        if (!Objects.equals(ipArea, trade.ipArea)) return false;
        return Objects.equals(viewCount, trade.viewCount);
    }

    @Override
    public int hashCode() {
        int result = gId != null ? gId.hashCode() : 0;
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (imageList != null ? imageList.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (hasDeleted != null ? hasDeleted.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (ipArea != null ? ipArea.hashCode() : 0);
        result = 31 * result + (viewCount != null ? viewCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "gId=" + gId +
                ", uId=" + uId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", imageList='" + imageList + '\'' +
                ", price=" + price +
                ", hasDeleted=" + hasDeleted +
                ", createTime=" + createTime +
                ", ip='" + ip + '\'' +
                ", ipArea='" + ipArea + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }
}