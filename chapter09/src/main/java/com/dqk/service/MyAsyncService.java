package com.dqk.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class MyAsyncService {
    @Async
    public void sendSMS() throws Exception{
        System.out.println("调用短信验证码业务方法...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        Long endTime = System.currentTimeMillis();
        System.out.println("短信业务执行完成耗时:"+(endTime-startTime));
    }

    /**
     * 模拟有返回值的异步任务处理
     * @return
     * @throws Exception
     */
    @Async
    public Future<Integer> processA() throws Exception {
        System.out.println("开始分析并统计业务A数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(4000);
        // 模拟定义一个假的统计结果
        int count=123456;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务A数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
    @Async
    public Future<Integer> processB() throws Exception {
        System.out.println("开始分析并统计业务B数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        // 模拟定义一个假的统计结果
        int count=654321;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务B数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
}
