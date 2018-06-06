package com.lweb.manager;


/**
 * Created by leroy:656515489@qq.com
 * 2018/6/5.
 */
public class ConfigManager {
    private final static ConfigManager instance = new ConfigManager();
    private ConfigManager(){}
    public static final ConfigManager instance(){
        return instance;
    }

}
