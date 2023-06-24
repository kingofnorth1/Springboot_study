package com.dqk.Service;

import com.dqk.Repository.AuthorityRepository;
import com.dqk.Repository.CustomerRepository;
import com.dqk.domain.Authority;
import com.dqk.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    // 业务控制：使用唯一用户名查询用户信息
    public Customer getCustomer(String username){
        Customer customer=null;
        Object o = redisTemplate.opsForValue().get("customer_"+username);
        if(o!=null){
            customer=(Customer)o;
        }else {
            customer = customerRepository.findByUsername(username);
            if(customer!=null){
                redisTemplate.opsForValue().set("customer_"+username,customer);
            }
        }
        return customer;
    }

    // 业务控制：使用唯一用户名查询用户权限
    public List<Authority> getCustomerAuthority(String username){
        List<Authority> authorities=null;
        Object o = redisTemplate.opsForValue().get("authorities_"+username);
        if(o!=null){
            authorities=(List<Authority>)o;
        }else {
            authorities=authorityRepository.findAuthorityByUsername(username);
            if(authorities.size()>0){
                redisTemplate.opsForValue().set("authorities_"+username,authorities);
            }
        }
        return authorities;
    }

}
