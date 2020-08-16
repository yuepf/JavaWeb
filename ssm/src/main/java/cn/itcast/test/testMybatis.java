package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testMybatis {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    @Before //开始
    public void Before() throws IOException {
        // 加载配置文件
        in =Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        factory =new SqlSessionFactoryBuilder().build(in);
        // 获取SqlSession对象
        sqlSession =factory.openSession();
        // 获取dao的代理对象
        accountDao=sqlSession.getMapper(AccountDao.class);
    }
    @After
    public void After() throws IOException {
        sqlSession.commit();
        //  关闭资源
        sqlSession.close();
        in.close();
    }



    @Test
    public void findAll() throws Exception {
        List<Account> accounts=accountDao.findAll();
        for (Account account:accounts){
            System.out.println(account);
        }
    }

    @Test
    public void saveAccount() throws Exception {
        Account account=new Account();
        account.setName("文博");
        account.setMoney(50.0);
        accountDao.saveAccount(account);
    }
}
