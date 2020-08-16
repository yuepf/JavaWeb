package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("routeImgDao")
public interface RouteImgDao {
    /*
        根据route的id查询图片
        @param rid
        @return
     */
    @Select("select * from ")
    public List<RouteImg> findByRid(int rid);
}
