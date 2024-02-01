package com.lee.xnxy.model.dao.paper;

import lombok.Data;
import org.springframework.core.io.ByteArrayResource;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 晓龙coding
 */
@Data
public class MyMail {
    /**
     * 邮件接收人
     */
    private String[] receiver;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件的文本内容
     */
    private String content;
    /**
     * 抄送人
     */
    private String[] cc;
    /**
     * 密送人
     */
    private String[] bcc;
    /**
     * 邮件附件的文件名
     */
    private String attachFileName;
    /**
     * 附件内容，可以是byte[]类型，可以是File类型
     * 如果文件在数据库中，则使用byte[]
     * 如果文件在服务器上，则使用File
     */
    private ByteArrayResource attachment;
    /**
     * 邮件内容内嵌图片
     */
    private Map<String, String> imageMap;
    /**
     * 是否是html文本
     */
    private boolean html = false;
}