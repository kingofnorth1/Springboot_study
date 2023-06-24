package com.dqk;

import com.dqk.Repository.PersonRepository;
import com.dqk.domain.Address;
import com.dqk.domain.Family;
import com.dqk.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    private PersonRepository personRepository;
    @Test
    public void savePerson(){
        Person person = new Person("张","有才");
        Person person1 = new Person("James","Harden");
        Address address = new Address("北京","China");
        person.setAddress(address);
        List<Family> familyList = new ArrayList<>();
        Family dad = new Family("父亲","学良");
        Family mom = new Family("母亲","李香君");
        familyList.add(dad);
        familyList.add(mom);
        Person save = personRepository.save(person);
        Person save2 = personRepository.save(person1);
        System.out.println(save);
        System.out.println(save2);
    }

    @Test
    public void selectPerson(){
        List<Person> list = personRepository.findByAddress_City("北京");
        System.out.println(list);
    }

    @Test
    public void updatePerson(){
        Person person = personRepository.findByFirstnameAndLastname("张","有才").get(0);
        person.setLastname("小明");
        Person update = personRepository.save(person);
        System.out.println(update);
    }

    @Test
    public void deletePerson(){
        Person person = personRepository.findByFirstnameAndLastname("张","小明").get(0);
        personRepository.delete(person);
    }
}
