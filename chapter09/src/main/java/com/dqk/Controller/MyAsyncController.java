package com.dqk.Controller;

import com.dqk.service.MyAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class MyAsyncController {
    @Autowired
    private MyAsyncService myAsyncService;
    @GetMapping("/sendSMS")
    public String sendSMS() throws Exception{
        Long startTime = System.currentTimeMillis();
        myAsyncService.sendSMS();
        Long endTime = System.currentTimeMillis();
        System.out.println("主程序耗时:"+(endTime-startTime));
        return "success";
    }

    @GetMapping("/statistics")
    public String statistics() throws Exception {
        Long startTime = System.currentTimeMillis();
        Future<Integer> futureA = myAsyncService.processA();
        Future<Integer> futureB = myAsyncService.processB();
        int total = futureA.get() + futureB.get();
        System.out.println("异步任务数据统计汇总结果： "+total);
        Long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时： "+(endTime-startTime));
        return "success";
    }
}
