package com.lee.xnxy.service.impl;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class CrawlerServiceImpl {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        try {
            Request getVerifyCodeRequest = new Request.Builder().url("http://202.119.81.113:8080/verifycode.servlet").build();
            byte[] imageBytes = null;
            try (Response response = client.newCall(getVerifyCodeRequest).execute()) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    imageBytes = response.body().bytes();
                    String savePath = "./codeImage.jpg";
                    // 将图片字节流写入文件
                    try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
                        outputStream.write(imageBytes);
                    } catch (IOException e) {
                        log.error("图片写入失败");
                    }
                    // 识别图片

                }
            }
            if (imageBytes != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("data", imageBytes);
                jsonObject.put("key", "EPssHMCF4gZlUOBvf7ba4P8I8B");

                RequestBody requestBodyWithKeyAndImage = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
                Request identifyVerifyCodeRequest = new Request.Builder()
                        .url("https://www.345api.cn/api/ocr/")
                        .post(requestBodyWithKeyAndImage)
                        .build();
                try (Response response = client.newCall(identifyVerifyCodeRequest).execute()){
                    ResponseBody responseBody = response.body();
                    assert responseBody != null;
                } catch (IOException e) {
                    log.error("系统异常");
                }
            }
        } catch (IOException e) {
            log.error("http连接关闭失败");
        }
    }

}
