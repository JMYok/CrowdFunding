package org.bobjiang.mvc.handler;

import com.bobjiang.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.bobjiang.entity.Role;
import org.bobjiang.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-24 12:10
 */
@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    /**
     * 以json形式显示分页后的role信息
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    //@PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping("/role/page/page.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword ) {
        // 从Service层得到pageInfo
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);

        // 返回ResultEntity，Data就是得到的pageInfo
        return ResultEntity.successWithData(pageInfo);
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/do/save.json")
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/do/update.json")
    public ResultEntity<String> updateRole(Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 删除角色
     * @param roleIdArrayList
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/do/remove.json")
    public ResultEntity<String> deleteRole(@RequestBody List<Integer> roleIdArrayList){
        roleService.removeById(roleIdArrayList);
        return ResultEntity.successWithoutData();
    }

}
