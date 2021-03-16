package com.atguigu.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-22 17:41
 */
@Controller
public class PortalHandler {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/")
    public String showPortalPage(){
//        // 调用MySQLRemoteService提供的方法查询首页要显示的数据
//        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
//        // 如果操作成功，将得到的list加入请求域
//        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
//            List<PortalTypeVO> portalTypeVOList = resultEntity.getData();
//            modelMap.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_TYPE_LIST,portalTypeVOList);
//        }
        return "portal";
    }
}
