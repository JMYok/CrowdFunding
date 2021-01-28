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
    List<Auth> queryAuthList();

    List<Integer> getAuthByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAuthNameByAdminId(Integer adminId);
}
