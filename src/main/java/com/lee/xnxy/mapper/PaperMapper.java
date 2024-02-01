package com.lee.xnxy.mapper;

import com.lee.xnxy.model.bizRequest.paper.ListPaperBizRequest;
import com.lee.xnxy.model.dao.paper.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 20882
* @description 针对表【xnxy_paper】的数据库操作Mapper
* @createDate 2023-12-09 14:42:17
* @Entity
*/
public interface PaperMapper extends BaseMapper<Paper> {

    List<Paper> listPaper(ListPaperBizRequest listPaperBizRequest);
}




