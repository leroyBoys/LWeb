package com.lweb.service.maintype;

import com.dbbase.enums.MainType;
import com.lweb.service.maintype.impl.MainService;
import com.lweb.service.maintype.impl.SearchService;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
public enum MainTypeServiceExucter {
    Main(MainType.Main,new MainService()),
    Search(MainType.Search,new SearchService()),
    Article(MainType.Article,new MainService()),;
    private final MainType mainType;
    private final MainTypeService mainTypeService;

    MainTypeServiceExucter(MainType mainType, MainTypeService mainTypeService) {
        this.mainType = mainType;
        this.mainTypeService = mainTypeService;
    }

    public MainType getMainType() {
        return mainType;
    }

    public MainTypeService getMainTypeService() {
        return mainTypeService;
    }
}
