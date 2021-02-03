package org.bobjiang.service.api;

import org.bobjiang.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-27 16:26
 */
public interface AuthService {

    /**
     * 查询所有权限
     * @return
     */
    List<Auth> queryAuthList();

    /**
     * 根据角色id得到权限id
     * @param roleId
     * @return
     */
    List<Integer> getAuthByRoleId(Integer roleId);

    /**
     * 存储角色-权限关系
     * @param map
     */
    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    /**
     * 根据id查询已分配权限的名称
     * @param adminId
     * @return
     */
    List<String> getAuthNameByAdminId(Integer adminId);
}
