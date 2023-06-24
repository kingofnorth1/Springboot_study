package com.dqk.config;

import com.dqk.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{  //继承网络安全配置适配器
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(200)
                .tokenRepository(tokenRepository());
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/detail/common/**").hasRole("common")
//                .antMatchers("/detail/vip/**").hasRole("vip")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//          密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        // 1、使用内存用户信息，作为测试使用
//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("shitou").password(encoder.encode("123456")).roles("common")
//                .and()
//                .withUser("李四").password(encoder.encode("123456")).roles("vip");

//        // 2、使用JDBC进行身份认证
//        String userSQL = "select username,password,valid from t_customer " +
//                "where username = ?";
//        String authoritySQL = "select c.username,a.authority from t_customer c,t_authority a," +
//                "t_customer_authority ca where ca.customer_id=c.id " +
//                "and ca.authority_id=a.id and c.username =?";
//        auth.jdbcAuthentication().passwordEncoder(encoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery(userSQL)
//                .authoritiesByUsernameQuery(authoritySQL);
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
