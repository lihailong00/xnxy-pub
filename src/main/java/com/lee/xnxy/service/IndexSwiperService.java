package com.lee.xnxy.service;

import com.lee.xnxy.model.dao.index.IndexSwiper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.model.dto.ResponseResult;

/**
* @author 20882
* @description 针对表【xnxy_index_swiper】的数据库操作Service
* @createDate 2023-10-13 14:18:27
*/
public interface IndexSwiperService extends IService<IndexSwiper> {

    ResponseResult getIndexSwiper();
}
