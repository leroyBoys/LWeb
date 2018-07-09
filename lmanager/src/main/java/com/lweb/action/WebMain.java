package com.lweb.action;

import com.dbbase.moudle.admin.Admin;
import com.dbbase.moudle.system.Main;
import com.dbbase.moudle.system.MainData;
import com.lgame.util.encry.MD5Tool;
import com.lgame.util.json.FastJsonTool;
import com.lqsmart.mysql.entity.LQPage;
import com.lweb.entity.QueryPage;
import com.lweb.manager.SessionManager;
import com.lweb.service.AdminService;
import com.lweb.service.WebMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/21.
 */
@RestController
public class WebMain {
    @Autowired
    private AdminService adminService;
    @Autowired
    private WebMainService webMainService;
    @GetMapping("/web/main/list")
    public Object mainList(QueryPage page,HttpServletRequest request){
        page.initConditions(request);
        return webMainService.getBaseMains(page);
    }

    @GetMapping("/web/main")
    public Object main(Integer id){
        return webMainService.getMainById(id==null?0:id);
    }

    @PostMapping("/web/main/save")
    public boolean saveMain(Main main){
        webMainService.saveMain(main);
        return true;
    }
    @PostMapping("/web/maindata/save")
    public boolean saveMainData(MainData mainData){
        webMainService.saveMainData(mainData);
        return true;
    }

    @PostMapping("/web/admin/list")
    public QueryPage getAdmins(@RequestBody QueryPage page, HttpServletRequest request){
        page.initConditions(request);
        return adminService.getAdmins(page);
    }

    @PostMapping("/web/admin/save")
    public boolean saveAdmin(Admin admin, HttpSession session){
        if(admin.getId() == 0 || admin.getId() != SessionManager.getInstance().getAdminFormSession(session).getId()){
            admin.setPassword(MD5Tool.GetMD5Code(admin.getName()+"admin"));
        }else {
            admin.setPassword(MD5Tool.GetMD5Code(admin.getPassword()));
        }
        System.out.println(FastJsonTool.getJsonFromBean(admin));
        adminService.saveAdmin(admin);
        return true;
    }
}
