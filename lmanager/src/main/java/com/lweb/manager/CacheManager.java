package com.lweb.manager;

import com.lweb.entity.SystemMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/20.
 */
public class CacheManager {
    private static CacheManager ourInstance = new CacheManager();

    public static CacheManager getInstance() {
        return ourInstance;
    }
    private CacheManager() {

    }

    private Map<Integer,SystemMenu> menuMap = new HashMap<>();
    private List<SystemMenu> menuList = new ArrayList<>();

    public SystemMenu getSystemMenu(int id){
        return menuMap.get(id);
    }

    public List<SystemMenu> getSystemMenus(){
        return menuList;
    }

    public void addNewSystemMenu(SystemMenu menu){
        SystemMenu systemMenu = menuMap.get(menu.getId());
        if(systemMenu !=null){
            systemMenu.setIcon(menu.getIcon());
            systemMenu.setParentId(menu.getParentId());
            systemMenu.setText(menu.getText());
            systemMenu.setUrl(menu.getUrl());
            return;
        }
        systemMenu = menuMap.get(menu.getParentId());
        menuMap.put(menu.getId(),menu);
        if(systemMenu == null){
            menuList.add(menu);
            return;
        }

        if(systemMenu.getChildren() == null){
            systemMenu.setChildren(new ArrayList<>());
        }
        systemMenu.getChildren().add(menu);
    }

    public void initMenus(List<SystemMenu> menus) {

        Map<Integer,List<SystemMenu>> parentMap = new HashMap<>();
        for(SystemMenu menu:menus){
            menuMap.put(menu.getId(),menu);
            int parentId = 0;
            if(menu.getParentId() > 0 ){
                parentId = menu.getParentId();
            }

            List<SystemMenu> list = parentMap.get(parentId);
            if(list == null){
                list = new ArrayList<>();
                parentMap.put(parentId,list);
            }
            list.add(menu);
        }

        for(SystemMenu menu:menus){
            menu.setChildren(parentMap.get(menu.getId()));
        }

        this.menuList = parentMap.get(0);
    }
}
