package org.bobjiang.service.api;

import com.github.pagehelper.PageInfo;
import org.bobjiang.entity.Role;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-24 12:09
 */
public interface RoleService {

    /**
     * 得到分页信息
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    PageInfo<Role> getPageInfo(int pageNum, int pageSize, String keyword);

    /**
     * 保存角色信息
     * @param role
     */
    void saveRole(Role role);

    /**
     * 更新角色信息
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     * @param roleIdList
     */
    void removeById(List<Integer> roleIdList);

    /**
     * 根据adminId得到未分配角色信息
     * @param adminId
     * @return
     */
    List<Role> queryUnAssignedRoleList(Integer adminId);

    /**
     * 根据adminId得到已分配角色信息
     * @param adminId
     * @return
     */
    List<Role> queryAssignedRoleList(Integer adminId);
}
