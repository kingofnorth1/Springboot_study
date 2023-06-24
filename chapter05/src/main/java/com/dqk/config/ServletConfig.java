//package com.dqk.config;
//
//import com.dqk.ServletComponent.MyFilter;
//import com.dqk.ServletComponent.MyListener;
//import com.dqk.ServletComponent.MyServlet;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * 嵌入式Servlet容器三大组件配置
// */
//@Configuration
//public class ServletConfig {
//    // 注册Servlet组件
//    @Bean
//    public ServletRegistrationBean getServlet(MyServlet myServlet){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(myServlet,"/myServlet");
//        return registrationBean;
//    }
//
//    //注册Filter组件
//    @Bean
//    public FilterRegistrationBean getFilter(MyFilter filter){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setUrlPatterns(Arrays.asList("/toLoginPage","/myFilter"));
//        return registrationBean;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean getServletListener(MyListener myListener){
//        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean(myListener);
//        return registrationBean;
//    }
//}
//
