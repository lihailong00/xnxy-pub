package com.lee.xnxy.service;

import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.paper.DownloadPaperBizRequest;
import com.lee.xnxy.model.bizRequest.paper.ListPaperBizRequest;
import com.lee.xnxy.model.dao.paper.Paper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 20882
* @description 针对表【xnxy_paper】的数据库操作Service
* @createDate 2023-12-09 14:42:17
*/
public interface PaperService extends IService<Paper> {

    ResponseResult listPaper(ListPaperBizRequest listPaperBizRequest);

    ResponseResult downloadPaper(DownloadPaperBizRequest downloadPaperBizRequest) throws Exception;

    ResponseResult downloadPaperByToken(String token) throws SysException;
}
