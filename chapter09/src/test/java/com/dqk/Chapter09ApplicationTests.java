package com.dqk;

import com.dqk.service.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter09ApplicationTests {
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMailTest() {
        String to="1448809291@qq.com";
        String subject="【纯文本邮件】标题";
        String text="Spring Boot纯文本邮件发送内容测试.....";
        // 发送简单邮件
        sendEmailService.sendSimpleEmail(to,subject,text);
    }

    @Test
    public void sendComplexEmailTest() {
        String to="1448809291@qq.com";
        String subject="SpringBoot邮件系统测试";
        // 定义邮件内容
        StringBuilder text = new StringBuilder();
        text.append("<html><head></head>");
        text.append("<body><h1>小黄收 </h1>");
        // cid为固定写法，rscId指定一个唯一标识
        String rscId = "img001";
        text.append("<img src='cid:" +rscId+"'/></body>");
        text.append("</html>");
        // 指定静态资源文件和附件路径
        String rscPath="C:\\Users\\king\\Pictures\\Saved Pictures\\狗头图.jpg";
        String filePath="C:\\Users\\king\\Desktop\\杜乾坤\\黄心桐简历.pdf";
        // 发送复杂邮件
        sendEmailService.sendComplexEmail(to,subject,text.toString(),filePath,rscId,rscPath);
    }


    @Test
    public void sendTemplateEmailTest() {
        String to="1448809291@qq.com";
        String subject="爱你哟";
        // 使用模板邮件定制邮件正文内容
        Context context = new Context();
        context.setVariable("username", "黄芯桐");
        context.setVariable("code", "1314520");
        // 使用TemplateEngine设置要处理的模板页面
        String emailContent = templateEngine.process("emailTemplate_vercode", context);
        // 发送模板邮件
        sendEmailService.sendTemplateEmail(to,subject,emailContent);
    }


}
