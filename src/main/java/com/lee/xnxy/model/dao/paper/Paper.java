package com.lee.xnxy.model.dao.paper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.Data;

/**
 * 
 * @TableName xnxy_paper
 */
@TableName(value ="xnxy_paper")
@Data
public class Paper implements Serializable {
    /**
     * 试卷id
     */
    @TableId(value = "pa_id")
    private Long paId;

    /**
     * 试卷名
     */
    @TableField(value = "paper_name")
    private String paperName;

    /**
     * 试卷描述
     */
    @TableField(value = "paper_desc")
    private String paperDesc;

    /**
     * 上传者
     */
    @TableField(value = "author")
    private String author;

    /**
     * 试卷价格（点券）
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 查看次数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 下载次数
     */
    @TableField(value = "download_count")
    private Integer downloadCount;

    /**
     * 试卷更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 试卷图片链接
     */
    @TableField(value = "paper_image")
    private String paperImage;

    /**
     * 试卷内容（二进制格式）
     */
    @TableField(value = "paper_content")
    private byte[] paperContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Paper other = (Paper) that;
        return (this.getPaId() == null ? other.getPaId() == null : this.getPaId().equals(other.getPaId()))
            && (this.getPaperName() == null ? other.getPaperName() == null : this.getPaperName().equals(other.getPaperName()))
            && (this.getPaperDesc() == null ? other.getPaperDesc() == null : this.getPaperDesc().equals(other.getPaperDesc()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getViewCount() == null ? other.getViewCount() == null : this.getViewCount().equals(other.getViewCount()))
            && (this.getDownloadCount() == null ? other.getDownloadCount() == null : this.getDownloadCount().equals(other.getDownloadCount()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPaperImage() == null ? other.getPaperImage() == null : this.getPaperImage().equals(other.getPaperImage()))
            && (Arrays.equals(this.getPaperContent(), other.getPaperContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPaId() == null) ? 0 : getPaId().hashCode());
        result = prime * result + ((getPaperName() == null) ? 0 : getPaperName().hashCode());
        result = prime * result + ((getPaperDesc() == null) ? 0 : getPaperDesc().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getViewCount() == null) ? 0 : getViewCount().hashCode());
        result = prime * result + ((getDownloadCount() == null) ? 0 : getDownloadCount().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPaperImage() == null) ? 0 : getPaperImage().hashCode());
        result = prime * result + (Arrays.hashCode(getPaperContent()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paId=").append(paId);
        sb.append(", paperName=").append(paperName);
        sb.append(", paperDesc=").append(paperDesc);
        sb.append(", author=").append(author);
        sb.append(", price=").append(price);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", downloadCount=").append(downloadCount);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", paperImage=").append(paperImage);
        sb.append(", paperContent=").append(paperContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}