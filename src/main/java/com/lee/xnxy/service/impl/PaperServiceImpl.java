package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.paper.DownloadPaperBizRequest;
import com.lee.xnxy.model.bizRequest.paper.ListPaperBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dto.paper.ListPaperDTO;
import com.lee.xnxy.model.dao.paper.MyMail;
import com.lee.xnxy.model.dao.paper.Paper;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.PaperService;
import com.lee.xnxy.mapper.PaperMapper;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.*;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ByteArrayResource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
* @author 20882
* @description 针对表【xnxy_paper】的数据库操作Service实现
* @createDate 2023-12-09 14:42:17
*/
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper>
    implements PaperService {

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private MyMailSender myMailSender;

    @Resource
    private UserService userService;

    @Resource
    private MinioUtil minioUtil;

    @Resource
    private HttpServletResponse httpServletResponse;

    @Override
    public ResponseResult listPaper(ListPaperBizRequest listPaperBizRequest) {
        List<Paper> paperList = paperMapper.listPaper(listPaperBizRequest);
        List<ListPaperDTO> listPaperDTOList = paperList.stream().map(this::toListPaperDTO).collect(Collectors.toList());
        return ResponseResult.success(listPaperDTOList);
    }

    private ListPaperDTO toListPaperDTO(Paper paper) {
        ListPaperDTO listPaperDTO = new ListPaperDTO();
        listPaperDTO.setPaId(String.valueOf(paper.getPaId()));
        listPaperDTO.setPaperName(paper.getPaperName());
        listPaperDTO.setPaperDesc(paper.getPaperDesc());
        listPaperDTO.setAuthor(paper.getAuthor());
        listPaperDTO.setPrice(paper.getPrice());
        listPaperDTO.setViewCount(paper.getViewCount());
        listPaperDTO.setDownloadCount(paper.getDownloadCount());
        listPaperDTO.setUpdateTime(paper.getUpdateTime());
        listPaperDTO.setPaperImage(paper.getPaperImage());
        return listPaperDTO;
    }

    @Override
    public ResponseResult downloadPaper(DownloadPaperBizRequest downloadPaperBizRequest) throws Exception {
        String paperId = downloadPaperBizRequest.getPaperId();

        LambdaQueryWrapper<Paper> paperLambdaQueryWrapper = new LambdaQueryWrapper<>();
        paperLambdaQueryWrapper.select(Paper::getPaperName, Paper::getPrice).eq(Paper::getPaId, paperId);
        Paper paper = this.getOne(paperLambdaQueryWrapper);
        if (paper == null) {
            return ResponseResult.fail(CommonConstant.PAPER_NOT_FOUND);
        }

        // 扣钱
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getMoney).eq(User::getUId, userId);
        // 不同方式的下载券不同
        int paperPrice = Objects.equals(downloadPaperBizRequest.getDownloadWay(), CommonConstant.DOWNLOAD_BY_EMAIL) ? paper.getPrice() : 1;
        User user = userService.getOne(userLambdaQueryWrapper);
        Integer money = user.getMoney();
        money -= paperPrice;
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getMoney, money).eq(User::getUId, userId);
        userService.update(lambdaUpdateWrapper);

        String downloadWay = downloadPaperBizRequest.getDownloadWay();

        // 生成token，用于web下载
        if (Objects.equals(downloadWay, CommonConstant.DOWNLOAD_BY_WEB)) {
            Integer token = DynamicDownloadUtil.getToken(paper.getPaperName());
            return ResponseResult.success(token);
        }

        // 发送邮件
        byte[] paperContent = minioUtil.getObjectByteArray(paper.getPaperName());
        sendMail(paper.getPaperName(), paperContent, downloadPaperBizRequest.getReceiverEmail());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult downloadPaperByToken(String token) throws SysException {
        boolean valid = DynamicDownloadUtil.checkToken(token);  // 检查token是否有效
        if (! valid) {
            return ResponseResult.fail(CommonConstant.DOWNLOAD_TOKEN_INVALID);
        }

        ConcurrentHashMap<Integer, DynamicDownloadUtil.TokenParam> tokenMap = DynamicDownloadUtil.TOKEN_MAP;
        DynamicDownloadUtil.TokenParam tokenParam = tokenMap.get(Integer.parseInt(token));
        String filename = tokenParam.getFilename();
        minioUtil.download(filename, httpServletResponse);
        return ResponseResult.success("下载成功！");
    }


    protected void sendMail(String paperName, byte[] paperContent, String receiverEmail) {
        MyMail mail = new MyMail();
        // 设置接收方
        mail.setReceiver(new String[]{receiverEmail});

        mail.setSubject("【小南校园】" + paperName);
        mail.setContent("请查收附件");

        // 设置附件名
        mail.setAttachFileName(paperName);
        // 设置附件内容
        mail.setAttachment(new ByteArrayResource(paperContent));

        myMailSender.send(mail);
    }

}




