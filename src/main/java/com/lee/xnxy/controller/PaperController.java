package com.lee.xnxy.controller;

import com.lee.xnxy.aop.DynamicValidate;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.converter.paper.DownloadPaperBizRequestConverter;
import com.lee.xnxy.converter.paper.ListPaperBizRequestConverter;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.paper.DownloadPaperBizRequest;
import com.lee.xnxy.model.bizRequest.paper.ListPaperBizRequest;
import com.lee.xnxy.model.request.paper.DownloadPaperRequest;
import com.lee.xnxy.model.request.paper.ListPaperRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.PaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("paper")
public class PaperController {
   @Resource
   private PaperService paperService;

    @PostMapping("list")
    public ResponseResult listPaper(@RequestBody @Valid ListPaperRequest listPaperRequest) {
        ListPaperBizRequest listPaperBizRequest = ListPaperBizRequestConverter.toListPaperBizRequest(listPaperRequest);
        return paperService.listPaper(listPaperBizRequest);
    }

    @PostMapping("download")
    @DynamicValidate
    public ResponseResult downloadPaper(@RequestBody @Valid DownloadPaperRequest downloadPaperRequest) throws Exception {
        DownloadPaperBizRequest downloadPaperBizRequest = DownloadPaperBizRequestConverter.toDownloadPaperBizRequest(downloadPaperRequest);
        return paperService.downloadPaper(downloadPaperBizRequest);
    }

    @GetMapping("download-by-token")
    @ApiOperation("通过token到浏览器下载paper，不需要经过拦截器")
    public ResponseResult downloadPaperByToken(@RequestParam("token") String token) throws SysException {
        return paperService.downloadPaperByToken(token);
    }
}
