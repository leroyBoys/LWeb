package com.lweb.moudle;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/6.
 */
@Component
@ConfigurationProperties(prefix = "app")
@PropertySource(value = "classpath:config/config.properties")
public class AppConfig {
    private String loginURI;
    private String whiteURI;

    public String getLoginURI() {
        return loginURI;
    }

    public void setLoginURI(String loginURI) {
        this.loginURI = loginURI;
    }

    public String getWhiteURI() {
        return whiteURI;
    }

    public void setWhiteURI(String whiteURI) {
        this.whiteURI = whiteURI;
    }
}
