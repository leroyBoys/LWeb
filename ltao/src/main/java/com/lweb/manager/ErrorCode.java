package com.lweb.manager;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
public enum ErrorCode {
    URLLow(101),;
    private int code;
    ErrorCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
