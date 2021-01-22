package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.util.CrowdUtil;
import org.bobjiang.entity.Admin;
import org.bobjiang.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-20 20:38
 */

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    Logger log = LoggerFactory.getLogger(TestHandler.class);


    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap, HttpServletRequest request){

        boolean judge = CrowdUtil.judgeRequestType(request);
        String res = judge?"json":"页面";
        log.info("该请求为"+res+"请求");



        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList",adminList);

        System.out.println(10/0);
        
        return "target";
    }

    @ResponseBody
    @RequestMapping("/test/array/one.html")
    public String testAjaxOne(@RequestParam("array[]") List<Integer> array){

        for(Integer number:array){
             log.info("number="+number);
        }

        return "Success Bro!";
    }

    @RequestMapping("/test/array/two.html")
    public String testAjaxTwo(){
        //见笔记

        return "Success Bro!";
    }


    @ResponseBody
    @RequestMapping("/test/object.html")
    public String testAjaxThree(){
        //见笔记
        return "Success Bro!";
    }
}
