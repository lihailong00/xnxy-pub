package com.lee.xnxy.validate.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dao.paper.Paper;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.service.PaperService;
import com.lee.xnxy.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class HasMoneyDownloadPaperValidateModule {
    @Resource
    private PaperService paperService;

    @Resource
    private UserService userService;

    public void validate(Long userId, Long paperId, String downloadWay) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getMoney).eq(User::getUId, userId);
        User user = userService.getOne(userLambdaQueryWrapper);
        Integer money = user.getMoney();

        LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
        paperLambdaQueryWrapper.select(Paper::getPrice).eq(Paper::getPaId, paperId);
        Paper paper = paperService.getOne(paperLambdaQueryWrapper);

        if (Objects.equals(downloadWay, CommonConstant.DOWNLOAD_BY_EMAIL) && paper.getPrice() > money) {  // 邮件的下载券按原价
            throw new ValidateException(CommonConstant.MONEY_INSUFFICIENT);
        } else if (Objects.equals(downloadWay, CommonConstant.DOWNLOAD_BY_WEB) && money < 1) {  // web的下载券只要1
            throw new ValidateException(CommonConstant.MONEY_INSUFFICIENT);
        }
    }
}
