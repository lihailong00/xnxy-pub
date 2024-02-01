package com.lee.xnxy.service;

import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    ResponseResult uploadPaperCloud(MultipartFile file) throws SysException, BizException, IOException;

    ResponseResult uploadPictureCloud(MultipartFile file) throws SysException, BizException;
}
