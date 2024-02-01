package com.lee.xnxy.converter.home;

import com.lee.xnxy.enums.GenderEnums;
import com.lee.xnxy.model.bizRequest.home.SaveUserInfoBizRequest;
import com.lee.xnxy.model.request.home.SaveUserInfoRequest;
import com.lee.xnxy.util.UserContextDTOUtil;

import java.util.Objects;

public class SaveUserInfoBizRequestConverter {
    private SaveUserInfoBizRequestConverter() {

    }

    public static SaveUserInfoBizRequest toSaveUserInfoBizRequest(SaveUserInfoRequest saveUserInfoRequest) {
        SaveUserInfoBizRequest saveUserInfoBizRequest = new SaveUserInfoBizRequest();
        saveUserInfoBizRequest.setUsername(saveUserInfoRequest.getUsername());
        String gender = saveUserInfoRequest.getGender();
        if (Objects.equals(gender, GenderEnums.MALE.getName())) {
            saveUserInfoBizRequest.setGender(GenderEnums.MALE.getCode());
        } else if (Objects.equals(gender, GenderEnums.FEMALE.getName())) {
            saveUserInfoBizRequest.setGender(GenderEnums.FEMALE.getCode());
        } else {
            saveUserInfoBizRequest.setGender(GenderEnums.UNKNOWN.getCode());
        }
        saveUserInfoBizRequest.setPhotoImg(saveUserInfoRequest.getPhotoImg());
        Long userId = UserContextDTOUtil.getUserContextDTO().getUserId();
        saveUserInfoBizRequest.setUserId(userId);
        return saveUserInfoBizRequest;
    }
}
