package com.lee.xnxy.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.lee.xnxy.config.MinioConfig;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

@Component
@Slf4j
public class MinioUtil {
    @Resource
    private MinioConfig prop;

    @Resource
    private MinioClient minioClient;

    /**
     * 文件上传
     * @param file 上传的文件
     * @param prefix 文件前缀
     * @param changeFileName 是否改变文件名
     * @return 上传后的文件名
     * @throws SysException 系统异常
     * @throws BizException 业务异常
     */
    public String upload(MultipartFile file, String prefix, boolean changeFileName) throws SysException, BizException {
        if (file == null || file.isEmpty()) {
            throw new BizException("文件不能为空");  // 可以有其他操作
        }
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new BizException("文件名不能为空");  // 可以有其他操作
        }

        // 图片文件都在/images 下, 试卷资源在/paper-resource 下
        String objectName;
        if (changeFileName) {
            String fileName = IdUtil.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            objectName = prefix + DateUtil.format(DateUtil.date(), "yyyy-MM-dd") + "+" + fileName;
        } else {
            objectName = prefix + originalFilename;
        }
        log.info("开始上传文件，文件大小为:{}, 修改后的文件名称为:{}", file.getSize(), file.getOriginalFilename());
        try (InputStream fileInputStream = file.getInputStream()) {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(prop.getBucketName())
                    .object(objectName)
                    .stream(fileInputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.info("文件上传失败,异常信息:{}", e.getMessage());
            throw new SysException("上传文件时，系统异常", e);  // 可以有其他操作
        }
        String url = "https://" + prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
        log.info("文件上传成功，url为：{}", url);
        return url;
    }

    /**
     * 文件上传，这种方式通常应用于后端上传文件。因为后端很难获取 MultipartFile
     * @param fileBytes 文件的字节流
     * @param originalFilename 文件名称
     * @param contentType 文件类型，例如image/png  image/jpg
     * @return 上传后的文件地址
     * @throws SysException
     * @throws BizException
     */
    public String upload(byte[] fileBytes, String prefix, boolean changeFileName, String originalFilename, String contentType) throws SysException, BizException {
        if (fileBytes.length == 0) {
            throw new BizException("文件不能为空");
        }
        if (StringUtils.isBlank(originalFilename)) {
            throw new BizException("文件名不能为空");
        }
        String objectName;
        if (changeFileName) {
            String fileName = IdUtil.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            objectName = prefix + DateUtil.format(DateUtil.date(), "yyyy-MM-dd") + "+" + fileName;
        } else {
            objectName = prefix + originalFilename;
        }
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(prop.getBucketName())
                    .object(objectName)
                    .stream(new ByteArrayInputStream(fileBytes), fileBytes.length, -1)
                    .contentType(contentType)
                    .build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            throw new SysException("上传文件时，系统异常", e);
        }
        String url = "https://" + prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
        return url;
    }

    /**
     * 文件下载
     * @param fileName 文件名称
     * @param res response
     */
    public void download(String fileName, HttpServletResponse res) throws SysException {
        String filePath = CommonConstant.PAPER_RESOURCE_PREFIX + fileName;
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(prop.getBucketName())
                .object(filePath).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                while ((len=response.read(buf)) != -1){
                    os.write(buf, 0, len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                res.setCharacterEncoding("utf-8");
                // 设置强制下载不打开
                // res.setContentType("application/force-download");
                res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                try (ServletOutputStream stream = res.getOutputStream()){
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            throw new SysException("下载异常", e);  // 可以有其他操作
        }
    }

    /**
     * 获取文件流
     *
     * @param filename 文件名
     * @return 二进制流
     */
    public byte[] getObjectByteArray(String filename) throws SysException {
        try {
            String targetFilename = CommonConstant.PAPER_RESOURCE_PREFIX + filename;
            GetObjectResponse objectResponse = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(prop.getBucketName())
                            .object(targetFilename)
                            .build());
            return IoUtil.readBytes(objectResponse);
        } catch (Exception e) {
            throw new SysException("获取文件流异常", e);
        }
    }
}