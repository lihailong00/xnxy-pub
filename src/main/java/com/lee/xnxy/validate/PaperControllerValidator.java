package com.lee.xnxy.validate;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.paper.DownloadPaperRequest;
import com.lee.xnxy.util.UserContextDTOUtil;
import com.lee.xnxy.validate.module.HasMoneyDownloadPaperValidateModule;
import com.lee.xnxy.validate.module.UserExistValidateModule;
import com.lee.xnxy.validate.module.ValidEmailModule;
import com.lee.xnxy.validate.module.ValidPaperDownloadWayModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class PaperControllerValidator {
    @Resource
    private HasMoneyDownloadPaperValidateModule hasMoneyDownloadPaperValidateModule;
    @Resource
    private UserExistValidateModule userExistValidateModule;

    @Resource
    private ValidPaperDownloadWayModule validPaperDownloadWayModule;

    @Resource
    private ValidEmailModule validEmailModule;


    public void downloadPaper(DownloadPaperRequest downloadPaperRequest) {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        String paperId = downloadPaperRequest.getPaperId();

        validPaperDownloadWayModule.validate(downloadPaperRequest.getDownloadWay());
        if (Objects.equals(downloadPaperRequest.getDownloadWay(), CommonConstant.DOWNLOAD_BY_EMAIL)) {
            validEmailModule.validate(downloadPaperRequest.getReceiverEmail());
        }
        userExistValidateModule.validate(userId);
        hasMoneyDownloadPaperValidateModule.validate(userId, Long.valueOf(paperId), downloadPaperRequest.getDownloadWay());
    }
}
