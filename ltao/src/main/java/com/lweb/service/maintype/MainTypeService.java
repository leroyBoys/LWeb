package com.lweb.service.maintype;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
@Service
public interface MainTypeService<T> {

    List<T> queryResults(int id, int page);
}
