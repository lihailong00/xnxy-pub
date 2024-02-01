package com.lee.xnxy;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.mapper.JwcPersonInfoMapper;
import com.lee.xnxy.model.bizRequest.login.LoginJwcBizRequest;
import com.lee.xnxy.model.dao.home.JwcPersonInfo;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.model.remote.FirstLoginJwcRespTO;
import com.lee.xnxy.service.CourseService;
import com.lee.xnxy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class CreateUserTest {
    @Resource
    private LoginService loginService;

//    @Test
    public void createUserAndGetUserInfo() throws InterruptedException, SysException {
        long[][] arr = new long[][]{
                {921103860401L, 921103860460L},

        };

        for (int i = 0; i < arr.length; i++) {
            long left = arr[i][0], right = arr[i][1];
            for (Long j = left; j <= right; j++) {
                LoginJwcBizRequest loginJwcBizRequest = new LoginJwcBizRequest();
                String username = j.toString();
                String password = j + "njust";
                loginJwcBizRequest.setUserId(-1L);
                loginJwcBizRequest.setUsername(username);
                loginJwcBizRequest.setPassword(password);
                try {
                    ResponseResult responseResult = loginService.loginJwcForSpyder(loginJwcBizRequest);
                    if (responseResult.getSuccess()) {
                        System.out.println(username + "\tsuccess");
                    } else {
                        System.out.println(username + "\t" + responseResult.getMsg());
                    }
                } catch (SysException e) {
                    System.out.println(username + "\t解析失败～");
                }
                Thread.sleep(300);
            }
        }

    }

//    @Test
    public void test2() throws IOException, InterruptedException, SysException {
        System.out.println(System.getProperty("user.dir"));
        String fileName = System.getProperty("user.dir") + "\\src\\test\\java\\com\\lee\\xnxy\\" +"username.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String username = line;
            String password = line + "njust";
            LoginJwcBizRequest loginJwcBizRequest = new LoginJwcBizRequest();
            loginJwcBizRequest.setUserId(-1L);
            loginJwcBizRequest.setUsername(username);
            loginJwcBizRequest.setPassword(password);
            ResponseResult responseResult = loginService.loginJwc(loginJwcBizRequest);
            System.out.println(username + " done");
            Thread.sleep(10000);
        }
    }

    @Resource
    private JwcPersonInfoMapper jwcPersonInfoMapper;


    @Resource
    private CourseService courseService;

//    @Test
    public void fillTeacherNameInScoreTable() throws SysException, InterruptedException {
        LambdaQueryWrapper<JwcPersonInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<JwcPersonInfo> jwcPersonInfoList = jwcPersonInfoMapper.selectList(lambdaQueryWrapper);
        for (JwcPersonInfo jwcPersonInfo : jwcPersonInfoList) {
            String username = jwcPersonInfo.getJwcUsername();
            String password = jwcPersonInfo.getPassword();
            Map<String, Object> verifyParam = new HashMap<String, Object>();
            verifyParam.put(CommonConstant.USERNAME, username);
            verifyParam.put(CommonConstant.PASSWORD, password);
            verifyParam.put("user_id", -1);

            String result;
            try {
                result = HttpUtil.post("http://localhost:8000/identifycode/", verifyParam);
            } catch (Exception e) {
                throw new SysException(CommonConstant.ERROR_CRAWLER, e);
            }
            try {
                FirstLoginJwcRespTO firstLoginJwcRespDataTO = JSONUtil.toBean(result, FirstLoginJwcRespTO.class);
                courseService.savePersonJwcInfo(firstLoginJwcRespDataTO);
                System.out.println(username + " ok");
            } catch (Exception e) {
                log.error(username + " 解析错误");
            }

            Thread.sleep(2000);
        }

    }
}
