package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        // 1.从redis中查询
            // 1.1 获取jedis客户端
        Jedis jedis=JedisUtil.getJedis();
            // 1.2 判断查询的集合是否为null
        //Set<String> categorys=jedis.zrange("category",0,-1);
            // 1.3 查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorys=jedis.zrangeWithScores("category",0,-1);

        List<Category> cs=null;
        // 2.判断查询的集合是否为null
        if (categorys ==null || categorys.size()==0){
            System.out.println("从数据库查询。。。");
            // 3.如果null,表示第一次查询需要从数据库查询,再将数据存入到redis中。
            cs=categoryDao.findAll();
            //将集合数据存入到redis中category的key
            for (int i=0;i<cs.size();i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            System.out.println("从redic中查询。。。。。");
            // 4.如果不为null，将set数据存入到list 。（因为：返回结果要为List<Category>，但现在结果为Set<String>。要转换动作）
            cs=new ArrayList<Category>();
            for (Tuple tuple:categorys){
                Category category=new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }


        return categoryDao.findAll();
    }
}
