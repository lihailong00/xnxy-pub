package com.lee.xnxy.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.util.JWTUtil;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 晓龙coding
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String token = httpServletRequest.getHeader("token");
        try {
            JWTUtil.checkToken(token);
        } catch (Exception e) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            Map<String, Object> ans = new HashMap<>();
            ans.put("code", 200);
            ans.put("success", false);
            ans.put("msg", "token验证失败, url=" + httpServletRequest.getRequestURI());
            ans.put("data", "");
            // Map 转 JSON
            ObjectMapper mapper = new ObjectMapper();
            String ansStr = "";
            try {
                ansStr = mapper.writeValueAsString(ans);
            } catch (JsonProcessingException ex) {
                logger.error("JWT功能异常: {}", ex.getMessage(), ex);
            }
            httpServletResponse.getWriter().print(ansStr);
            logger.error("未通过拦截器，token={}, url={}", token, httpServletRequest.getRequestURL());
            return false;
        }
        logger.info("通过拦截器，token={}，url={}", token, httpServletRequest.getRequestURL());
        setUserContextDTO(httpServletRequest);
        return true;
    }

    private void setUserContextDTO(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(CommonConstant.TOKEN);
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);

        Long uId = Long.parseLong(tokenInfo.getClaim(CommonConstant.UID).asString());
        String username = tokenInfo.getClaim(CommonConstant.USERNAME).asString();

        UserContextDTO userContextDTO = new UserContextDTO();
        userContextDTO.setUserId(uId);
        userContextDTO.setUsername(username);

        UserContextDTOUtil.setUserContextDTO(userContextDTO);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserContextDTOUtil.removeUserContextDTO();
    }
}