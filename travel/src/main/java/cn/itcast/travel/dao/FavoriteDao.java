package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("favoriteDao")
public interface FavoriteDao {
    /*
        根据rid和Uid查询收藏信息
        @param rid
        @param uid
        @return
     */
    @Select("select * from tab_favorite where rid=#{rid} and uid=#{uid}")
    public Favorite findByRidAndUid(int rid, int uid);

    /*
        根据rid查询收藏的次数
        @param rid
        @return
     */
    @Select("select count(*) from tab_favorite where rid=#{rid}")
    public int findCoundByRid(int rid);

    /*
        添加收藏
        @param rid
        @param uid
     */
    @Insert("insert into tab_favorite(rid,uid) values(#{rid},#{uid})")
    void add(int rid, int uid);
}
