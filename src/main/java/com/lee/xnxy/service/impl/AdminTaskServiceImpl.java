package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.mapper.AdminTaskMapper;
import com.lee.xnxy.model.dao.index.AdminTask;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.AdminTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author bytedance
* @description 针对表【xnxy_admin_task】的数据库操作Service实现
* @createDate 2024-01-13 20:57:59
*/
@Service
public class AdminTaskServiceImpl extends ServiceImpl<AdminTaskMapper, AdminTask>
    implements AdminTaskService{

    @Override
    public ResponseResult listAdminTodo() {
        LambdaQueryWrapper<AdminTask> adminTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminTaskLambdaQueryWrapper.orderByDesc(AdminTask::getWeight);
        List<AdminTask> adminTaskList = this.list(adminTaskLambdaQueryWrapper);
        return ResponseResult.success(adminTaskList);
    }
}




