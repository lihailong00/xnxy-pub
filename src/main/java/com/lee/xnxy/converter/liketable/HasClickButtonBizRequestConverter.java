package com.lee.xnxy.converter.liketable;

import com.lee.xnxy.model.bizRequest.liketable.HasClickButtonBizRequest;
import com.lee.xnxy.model.request.liketable.HasClickButtonRequest;
import com.lee.xnxy.util.UserContextDTOUtil;

public class HasClickButtonBizRequestConverter {
    private HasClickButtonBizRequestConverter() {

    }

    public static HasClickButtonBizRequest toHasClickButtonBizRequest(HasClickButtonRequest hasClickButtonRequest) {
        HasClickButtonBizRequest hasClickButtonBizRequest = new HasClickButtonBizRequest();
        hasClickButtonBizRequest.setLikeUserId(UserContextDTOUtil.getUserContextDTO().getUserId());
        hasClickButtonBizRequest.setPostId(Long.valueOf(hasClickButtonRequest.getPostId()));
        return hasClickButtonBizRequest;
    }
}
