package com.lee.xnxy.converter.posts;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.posts.ListPostsBizRequest;
import com.lee.xnxy.model.request.posts.ListPostsRequest;
import lombok.Data;

@Data
public class ListPostsBizRequestConverter {
    private ListPostsBizRequestConverter() {

    }

    public static ListPostsBizRequest toListPostsBizRequest(ListPostsRequest listPostsRequest) {
        ListPostsBizRequest listPostsBizRequest = new ListPostsBizRequest();
        listPostsBizRequest.setKeyword(listPostsRequest.getKeyword());
        listPostsBizRequest.setPageNumber(listPostsRequest.getPageNumber());
        listPostsBizRequest.setPageSize(CommonConstant.PAGE_SIZE);
        String userId = listPostsRequest.getUserId();
        if (userId != null) {
            listPostsBizRequest.setUserId(Long.valueOf(userId));
        }
        return listPostsBizRequest;
    }
}
