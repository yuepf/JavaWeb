package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {


    @Override //根据用户名查询用户信息
    public User findByUsername(String username) {
        User user= null;
        try {
            //1. 定义sql
            String sql="select * from tab_user where username=?";
            //2. 执行
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override //用户保存
    public void save(User user) {
        // 1.定义sql
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values (?,?,?,?,?,?,?,?,?)";
        // 2.执行
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /*
        根据激活码查询用户对象
        @param code
        @return
     */
    @Override
    public User findByCode(String code) {
        User user=null;
        try {
            String sql="select * from tab_user where code=?";
            user=template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
        修改指定用户激活状态
        @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }

    /*
        根据用户名和密码查询的方法
        @param username
        @param password
        @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user= null;
        try {
            //1. 定义sql
            String sql="select * from tab_user where username=? and password=?";
            //2. 执行
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (DataAccessException e) {

        }
        return user;
    }
}
