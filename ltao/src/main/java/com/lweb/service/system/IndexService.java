package com.lweb.service.system;


import org.springframework.stereotype.Service;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/15.
 */
@Service
public interface IndexService {
    Object getDefaultMain(int platformType, int page);
}
