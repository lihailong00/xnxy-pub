package com.lee.xnxy.controller;

import com.lee.xnxy.model.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping("test")
public class TestController {
    static int count = 0;

    @RequestMapping("")
    public String hello() {
        return "hello";
    }
    
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>(5);
        System.out.println("start");
        for (int i = 10; i > 0; i--) {
            blockingQueue.offer(i, 1, TimeUnit.SECONDS);
        }
        while (!blockingQueue.isEmpty()) {
            Integer num = blockingQueue.poll();
            System.out.println(num);
        }
    }

    @RequestMapping("thread")
    public ResponseResult func() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
//        threadPoolExecutor.execute();
        System.out.println("enter test!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
        if (count % 2 == 0) {
            return ResponseResult.success("ok");
        }
        return ResponseResult.fail("not");
    }
}
