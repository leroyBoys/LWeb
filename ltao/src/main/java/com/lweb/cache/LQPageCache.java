package com.lweb.cache;

import com.dbbase.log.LogUtil;
import com.dbbase.moudle.Shop;
import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.MainData;
import com.lqsmart.util.LRUMap;
import com.lweb.cache.entity.LQCacheKey;
import com.lweb.cache.entity.PageCountCacheMap;
import com.lweb.cache.entity.PageDetail;
import com.lweb.manager.TimeCacheManager;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 分页cache
 * Created by leroy:656515489@qq.com
 * 2018/6/14.
 */
public class LQPageCache extends LQCache{
    private static final LQPageCache intance = new LQPageCache();
    /** 缓存最大页数 */
    private final int maxPageCount = 20;
    /**  缓存最大结果集数量（即固定条件下总数据量为一个cache） */
    private final int maxPageResultCount = 500;
    private LQPageCache() {
    }
    public static LQPageCache getIntance(){
        return intance;
    }

    private PageCountCacheMap pageResultCache = new PageCountCacheMap(maxPageResultCount,300);
    private WeakReference<LRUMap> cacheMap = new WeakReference<>(new LRUMap(maxPageCount));

    @Override
    public LRUMap getCacheMap() {
        LRUMap lruMap = cacheMap.get();
        if(lruMap == null){
            synchronized (this){
                lruMap = cacheMap.get();
                if(lruMap != null){
                    return lruMap;
                }

                lruMap = new LRUMap(maxPageCount);
                cacheMap =  new WeakReference<>(lruMap);
            }
        }

        return lruMap;
    }

    @Override
    protected Object getDataFromDB(LQCacheKey lqCacheKey, Object[] parater) {
        return getDataFromMysql(lqCacheKey,parater);
    }

    @Override
    protected String cacheKey(LQCacheKey lqCacheKey, Object[] parater) {
        PageDetail pageDetail = (PageDetail) parater[1];
        return merageKey(pageDetail.getT().getName()+"_"+pageDetail.getStart()+"_"+pageDetail.getEnd(),parater);
    }

    protected String pageCountCacheKey(PageDetail pageDetail, Object[] parater) {
        return merageKey(pageDetail.getT().getName(),parater);
    }

    private boolean isToLimit(PageDetail pageDetail){
        return pageDetail.getPageCurrent() > pageDetail.getMaxPageNum();
    }

    public void queryPageCount(PageDetail pageDetail, String queryCountSql,Object... parater){
        String key = pageCountCacheKey(pageDetail,parater);
        Object count = pageResultCache.get(key);
        if(count != null){
            pageDetail.setCount((Integer) count);
            return;
        }

        synchronized (queryCountSql){
            count = pageResultCache.get(key);
            if(count == null){
                count = getDataSourceConnection().getRandomSlave().ExecuteQueryOnlyOneValue(queryCountSql,parater);
                if(count == null){
                    count = 0;
                }
                pageDetail.setCount(((Long) count).intValue());
                pageResultCache.put(key,pageDetail.getCount());
            }
        }
    }

    public final List<MainData> getMainPages(int mainId, int page) {
        String sql = "SELECT * FROM `system_main_data` WHERE main_id = ? AND ? > startTime  AND  (  endTime = 0 or ? < endTime) ORDER BY sortNum DESC limit ?,?";
        int curTime = (int) (TimeCacheManager.instance().getCurTime()/1000);
        int pageSize = LQSystemCache.getIntance().getDefaultMainPageSize();
        PageDetail lqPage = new PageDetail(MainData.class,page,pageSize);
        queryPageCount(lqPage,"SELECT count(1) FROM `system_main_data` WHERE main_id = ? AND  ? > startTime  AND (  endTime = 0 or ? < endTime)",mainId,curTime,curTime);
        if(isToLimit(lqPage)){
            return null;
        }
        return getPageResult(lqPage,sql,mainId,curTime,curTime,lqPage.getStart(),lqPage.getEnd());
    }

    protected final <T> T getPageResult(PageDetail pageDetail, String sql,Object... parater){
        return getCache(LQCacheKey.Page,sql,pageDetail,parater);
    }

    public List<Shop> getSearchShopPages(int mainId, int page) {
        Main main = LQCache.getIntance().getCache(LQCacheKey.Main,mainId);
        if(main == null){
            LogUtil.error(LQPageCache.class,"getSearchShopPages:mainId:"+mainId+"cant not find data");
            return null;
        }else if(main.getCateIds() == null){
            LogUtil.error(LQPageCache.class,"getCateIds:mainId:"+mainId+"cant not find cates data");
            return null;
        }

        String inIds = "";
        for(int i = 0,size=main.getCateIds().length;i<size;i++){
            if(i != 0){
                inIds+= ",";
            }
            inIds+= main.getCateIds()[i];
        }


        String sql = "SELECT shopId FROM `shop_extra` WHERE cat IN ("+inIds+")";
        int curTime = (int) (TimeCacheManager.instance().getCurTime()/1000);
        int pageSize = LQSystemCache.getIntance().getDefaultShopPageSize();
        PageDetail lqPage = new PageDetail(Shop.class,page,pageSize);
        queryPageCount(lqPage,"SELECT id,title,price,oprice,`area`,src,shopUrl,ticketUrl,shortUrl,tickDesc,`from`,sales  FROM shop RIGHT JOIN (SELECT shopId,`from`,sales FROM `shop_extra` WHERE cat IN ("+inIds+") ORDER BY sortNum DESC LIMIT ) AS b ON b.shopId = shop.id )",mainId,curTime,curTime);
        if(isToLimit(lqPage)){
            return null;
        }
        return getPageResult(lqPage,sql,mainId,curTime,curTime,lqPage.getStart(),lqPage.getEnd());
    }
}
