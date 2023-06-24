package com.dqk.test;

import com.dqk.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter01ApplicationTests {
//    @Test
//    public void contextLoads(){
//
//    }
    @Autowired
    private HelloController helloController;
    @Test
    public void HelloController(){
        String hello = helloController.Hello();
        System.out.println(hello);
    }
}
