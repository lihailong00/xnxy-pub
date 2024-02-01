package com.lee.xnxy.service;

import com.lee.xnxy.model.dao.index.AdminTask;
import com.lee.xnxy.model.dto.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author bytedance
* @description 针对表【xnxy_admin_task】的数据库操作Service
* @createDate 2024-01-13 20:57:59
*/
public interface AdminTaskService extends IService<AdminTask> {

    ResponseResult listAdminTodo();
}
