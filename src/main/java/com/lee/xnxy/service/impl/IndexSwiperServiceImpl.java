package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.model.dao.index.IndexSwiper;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.IndexSwiperService;
import com.lee.xnxy.mapper.IndexSwiperMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 20882
* @description 针对表【xnxy_index_swiper】的数据库操作Service实现
* @createDate 2023-10-13 14:18:27
*/
@Service
public class IndexSwiperServiceImpl extends ServiceImpl<IndexSwiperMapper, IndexSwiper>
    implements IndexSwiperService {
    @Override
    public ResponseResult getIndexSwiper() {
        LambdaQueryWrapper<IndexSwiper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<IndexSwiper> indexSwiperList = this.list(lambdaQueryWrapper);
        return ResponseResult.success(indexSwiperList);
    }
}




