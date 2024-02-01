package com.lee.xnxy.constant;

public class CommonConstant {
    // todo 每学期都要换！
    public static String NOW_TERM = "2023-2024-1";
    public static String WX_SERVER_ERROR_MESSAGE = "errmsg";

    public static String WX_SERVER_FAILURE_RESPONSE = "访问微信服务器异常";

    public static String DEFAULT_USER_PREFIX = "用户";

    public static String SESSION_KEY = "session_key";

    public static String OPENID = "openid";

    public static Integer DEFAULT_TOKEN_VALID_TIME = 7 * 24 * 3600;

    public static Integer PAGE_SIZE = 20;

    public static String UID = "UId";

    public static String TOKEN = "token";

    public static String USERNAME = "username";

    public static String PASSWORD = "password";

    public static String SYSTEM_ERROR = "系统错误！";

    public static String ERROR_GOODS_PARAM = "商品参数错误";

    public static String TIME = "time";

    public static String PRICE = "price";

    public static Integer NOT_DELETE = 0;

    public static Integer DELETE = 1;

    public static String UNKNOWN_USER = "未知用户";

    public static String ALREADY_LOGIN_JWC = "您已成功通过教务处验证，无需再次登录";

    public static String FALSE = "false";

    public static String SUCCESS = "success";

    public static String JWC_VERIFY_SUCCESS = "通过教务处验证";

    public static Integer JWC_AUTHORIZED = 1;

    public static String SEND_COMMENT_SUCCESS = "评论发布成功!";

    public static String SEND_COMMENT_FAIL = "评论发布失败，请联系管理员～";

    public static String USERNAME_EXIST = "用户名已存在";

    public static String GENDER_CHECK = "填写性别时，性别必须是男或女";

    public static String ERROR_CRAWLER = "爬虫服务异常";

    public static String ERROR_CRAWLER_PARSE = "爬虫解析异常";

    public static String EMPTY_JWC_USERNAME = "未找到用户教务处信息";

    public static String USER_ID_NOT_EXIST = "用户id不存在";

    public static String PAPER_NOT_FOUND = "未找到试卷";

    public static String LOGOUT_JWC = "成功退出教务处";

    public static String NOT_LOGIN_JWC = "还未登录教务处";

    public static String DOWNLOAD_BY_EMAIL = "email";

    public static String DOWNLOAD_BY_WEB = "web";

    public static String INVALID_DOWNLOAD_WAY = "下载方式非法";

    public static String MONEY_INSUFFICIENT = "点券不足";

    public static String INVALID_EMAIL = "邮件地址不正确";

    public static String IMAGE_RESOURCE_PREFIX = "images/";

    public static String PAPER_RESOURCE_PREFIX = "paper-resources/";  // 要根据实际情况变化

    public static String DOWNLOAD_TOKEN_INVALID = "链接已失效，请重新获取";

    public static String PLEASE_GRADE = "请评教";

    public static String DEFAULT_AVATAR = "https://tse1-mm.cn.bing.net/th/id/OIP-C.QSVZuY_JudCZRwV1vqFh1AAAAA?w=205&h=205&c=7&r=0&o=5&dpr=1.5&pid=1.7";

    public static Integer DEFAULT_MONEY = 500;

    public static Integer IS_LOGIN_JWC = 1001;

    public static String PNG = "png";

    public static String JPG = "jpg";

    public static String CONTENT_TYPE_PNG = "image/png";

    public static String CONTENT_TYPE_JPG = "image/jpg";
}
