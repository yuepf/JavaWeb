package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
@Repository("categoryDao")
public interface CategoryDao {
    /*
        查询所有
        @return
     */
    @Select("select * from tab_category")
    public List<Category> findAll();
}
