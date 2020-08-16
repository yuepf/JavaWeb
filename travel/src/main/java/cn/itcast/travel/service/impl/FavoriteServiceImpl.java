package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("favoriteServiceImpl")
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;
    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite=favoriteDao.findByRidAndUid(Integer.parseInt(rid),uid);
        return favorite !=null;//如果对象有值，则为true。反之false。
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
