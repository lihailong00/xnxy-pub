package com.lee.xnxy.service.impl;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.dao.paper.Paper;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.PaperService;
import com.lee.xnxy.service.UploadService;
import com.lee.xnxy.util.MinioUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Component
public class UploadServiceImpl implements UploadService {
    @Resource
    private MinioUtil minioUtil;

    @Resource
    private PaperService paperService;

    public ResponseResult uploadPaperCloud(MultipartFile file) throws SysException, BizException, IOException {
        // 如何事务?
        try (InputStream inputStream = file.getInputStream();
             PDDocument document = PDDocument.load(inputStream)) {
            // 页数作为价格
            int numberOfPages = document.getNumberOfPages();
            // 第1页作为封面
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, CommonConstant.PNG, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String coverImage = minioUtil.upload(imageBytes, CommonConstant.IMAGE_RESOURCE_PREFIX, true, file.getOriginalFilename() + ".png", CommonConstant.CONTENT_TYPE_PNG);
            // 相关数据存入数据库
            Paper paper = new Paper();
            paper.setPaperName(file.getOriginalFilename());
            paper.setPrice(numberOfPages);
            paper.setAuthor("晓龙");
            paper.setUpdateTime(LocalDateTime.now());
            paper.setPaperImage(coverImage);
            paperService.save(paper);
        }
        String uploadUrl = minioUtil.upload(file, CommonConstant.PAPER_RESOURCE_PREFIX, false);
        return ResponseResult.success(uploadUrl);
    }

    @Override
    public ResponseResult uploadPictureCloud(MultipartFile file) throws SysException, BizException {
        String uploadUrl = minioUtil.upload(file, CommonConstant.IMAGE_RESOURCE_PREFIX, true);
        return ResponseResult.success(uploadUrl);
    }
}
