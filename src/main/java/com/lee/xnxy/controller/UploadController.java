package com.lee.xnxy.controller;

import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.UploadService;
import com.lee.xnxy.util.MinioUtil;
import com.lee.xnxy.util.QiNiuUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 将图片上传至七牛云
 * @author 晓龙coding
 */
@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Resource
    private UploadService uploadService;

    @PostMapping("image")
    @LogAnnotation(module="上传", operator="上传图片")
    @ApiOperation("上传图片到minio")
    @ResponseBody
    ResponseResult uploadPictureCloud(MultipartFile file) throws SysException, BizException {
        return uploadService.uploadPictureCloud(file);
    }

    @PostMapping("paper")
    @ApiOperation("上传pdf到minio")
    @ResponseBody
    ResponseResult uploadPaperCloud(MultipartFile file) throws SysException, BizException, IOException {
        return uploadService.uploadPaperCloud(file);
    }

    @RequestMapping("paper-upload-page")
    String goToUploadPaper() {
        return "upload-paper";
    }
}