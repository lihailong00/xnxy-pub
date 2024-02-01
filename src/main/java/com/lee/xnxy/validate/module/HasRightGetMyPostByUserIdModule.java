package com.lee.xnxy.validate.module;

import com.lee.xnxy.constant.ValidateFailureConstant;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HasRightGetMyPostByUserIdModule {
    public void validate(String userId) {
        if (userId == null) {
            return;
        }
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long dtoUserId = userContextDTO.getUserId();
        if (Objects.equals(Long.parseLong(userId), dtoUserId)) {  // 只有自己才能查看自己的帖子
            return;
        }
        throw new ValidateException(ValidateFailureConstant.PERMISSION_DENIED);
    }
}
