package com.lweb.cache.impl;

import com.dbbase.moudle.Shop;
import com.lqsmart.mysql.impl.LQDataSource;
import com.lweb.cache.DBExecuter;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/13.
 */
public class ShopDetailDbExecuter extends DBExecuter {

    @Override
    public Object getDataFromDB(LQDataSource dataSource, Object[] parater) {
        int id = (int) parater[0];
        Shop shop = dataSource.ExecuteQueryOne(Shop.class,"SELECT id,title,price,oprice,`area`,src,shopUrl,ticketUrl,shortUrl,tickDesc,shop_iid,tickDetail,size,colorUnit,`from`,sellid,sales,startTime,endTime FROM `shop` RIGHT JOIN(SELECT shopId,`from`,size,colorUnit,sellid,sales,startTime,endTime FROM `shop_extra` WHERE shopId = "+id+")AS b ON b.shopId = shop.id");
        return shop;
    }

    @Override
    public boolean updateDb(LQDataSource master, Object cache) {
        return false;
    }
}
