package com.lee.xnxy.controller;

import com.lee.xnxy.model.request.paper.ListPaperRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PaperControllerTest {
    @Resource
    private PaperController paperController;
    @Test
    public void listPaper() {
        ListPaperRequest listPaperRequest = new ListPaperRequest();
        listPaperRequest.setPageNumber(1);
        listPaperRequest.setKeyword("线代");
        ResponseResult responseResult = paperController.listPaper(listPaperRequest);
        assert responseResult.getSuccess();
    }
}
