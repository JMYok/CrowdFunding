package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.util.ResultEntity;
import org.bobjiang.entity.Auth;
import org.bobjiang.entity.Role;
import org.bobjiang.service.api.AdminService;
import org.bobjiang.service.api.AuthService;
import org.bobjiang.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-26 18:26
 */
@Controller
public class AssignHandler {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    /**
     * 跳转到分配角色页面
     * @param adminId
     * @param modelMap
     * @return page
     */
    @RequestMapping("/assign/to/page.html")
    public String toAssignPage(@RequestParam("adminId") Integer adminId, ModelMap modelMap){

        // 得到对应当前adminId未被分配的角色（Role）List
        List<Role> UnAssignedRoleList = roleService.queryUnAssignedRoleList(adminId);

        // 得到对应当前adminId已被分配的角色（Role）List
        List<Role> AssignedRoleList = roleService.queryAssignedRoleList(adminId);

        // 将已选择的、未选择的放入modelMap
        modelMap.addAttribute("UnAssignedRoleList",UnAssignedRoleList);
        modelMap.addAttribute("AssignedRoleList",AssignedRoleList);

        // 请求转发到assign-role.jsp
        return "assign-role";
    }

    /**
     * 给用户分配角色
     * @param adminId
     * @param pageNum
     * @param keyword
     * @param roleIdList
     * @return page
     */
    @RequestMapping("/assign/do/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList
    ){

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        //重定向（减少数据库操作）返回信息页
        return "redirect:/admin/page/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @ResponseBody
    @RequestMapping("/assign/get/tree.json")
    public ResultEntity<List<Auth>> getAuthTree(){
        List<Auth> authList = authService.queryAuthList();

        return ResultEntity.successWithData(authList);
    }


}
