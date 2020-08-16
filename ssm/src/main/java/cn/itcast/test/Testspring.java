package cn.itcast.test;

import cn.itcast.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Testspring {

    @Autowired
    private AccountService as;

    @Test
    public void run1(){
       // ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
       // AccountService as=ac.getBean("accountService",AccountService.class);

        as.findAll();
    }
}
