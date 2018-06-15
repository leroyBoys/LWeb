package com.dbbase.moudle;

import com.lqsmart.mysql.entity.LQDBTable;
import com.lqsmart.mysql.entity.LQField;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/12.
 */
@LQDBTable(name = "shop")
public class Shop {
    @LQField(isPrimaryKey = true)
    private long id;
    @LQField
    private long shop_iid;
    @LQField
    private float price;
    @LQField
    private String area;
    @LQField
    private String src;//主图片地址
    @LQField
    private String shopUrl;//
    @LQField
    private String ticketUrl;
    @LQField
    private String shortUrl;
    @LQField
    private String reward;
    @LQField
    private String tickDetail;
    @LQField
    private int areaCode;
    @LQField
    private short size;
    @LQField
    private float sprice;
    @LQField
    private short colorUnit;
    @LQField
    private short from;
    @LQField(name = "cate")
    private int catId;
    @LQField
    private int sellid;
    @LQField
    private int sales;
    @LQField
    private int startTime;
    @LQField
    private int endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShop_iid() {
        return shop_iid;
    }

    public void setShop_iid(long shop_iid) {
        this.shop_iid = shop_iid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getTickDetail() {
        return tickDetail;
    }

    public void setTickDetail(String tickDetail) {
        this.tickDetail = tickDetail;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public float getSprice() {
        return sprice;
    }

    public void setSprice(float sprice) {
        this.sprice = sprice;
    }

    public short getColorUnit() {
        return colorUnit;
    }

    public void setColorUnit(short colorUnit) {
        this.colorUnit = colorUnit;
    }

    public short getFrom() {
        return from;
    }

    public void setFrom(short from) {
        this.from = from;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getSellid() {
        return sellid;
    }

    public void setSellid(int sellid) {
        this.sellid = sellid;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
