package com.lee.xnxy.controller;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.converter.home.SaveUserInfoBizRequestConverter;
import com.lee.xnxy.enums.GenderEnums;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.bizRequest.home.SaveUserInfoBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.home.SaveUserInfoRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.HomeService;
import com.lee.xnxy.util.UserContextDTOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("home")
@Api("\"我的\"模块")
public class HomeController {
    @Resource
    private HomeService homeService;
    @PostMapping("get-my-info")
    @ApiOperation("获取个人信息")
    public ResponseResult getUserInfo(@RequestHeader("token") String token) throws BizException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        return homeService.getUserInfo(userId);
    }

    @PostMapping("save-my-info")
    @ApiOperation("修改个人信息")
    public ResponseResult saveUserInfo(@RequestBody @Valid SaveUserInfoRequest saveUserInfoRequest, @RequestHeader("token") String token) {
        String gender = saveUserInfoRequest.getGender();
        if (StringUtils.isNotEmpty(gender)
                && !Objects.equals(gender, GenderEnums.MALE.getName())
                && !Objects.equals(gender, GenderEnums.FEMALE.getName())) {
            return ResponseResult.fail(CommonConstant.GENDER_CHECK);
        }
        SaveUserInfoBizRequest saveUserInfoBizRequest = SaveUserInfoBizRequestConverter.toSaveUserInfoBizRequest(saveUserInfoRequest);
        return homeService.saveUserInfo(saveUserInfoBizRequest);
    }
}
