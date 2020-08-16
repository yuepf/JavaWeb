package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;
import org.springframework.stereotype.Repository;

@Repository("sellerDao")
public interface SellerDao {
   public Seller findById(int id);
}
