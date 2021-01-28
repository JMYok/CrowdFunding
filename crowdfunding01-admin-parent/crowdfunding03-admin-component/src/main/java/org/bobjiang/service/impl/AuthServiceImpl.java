package org.bobjiang.service.impl;

import org.bobjiang.entity.Auth;
import org.bobjiang.entity.AuthExample;
import org.bobjiang.mapper.AuthMapper;
import org.bobjiang.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-27 16:27
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    public List<Auth> queryAuthList() {
        return authMapper.selectByExample(new AuthExample());
    }

    public List<Integer> getAuthByRoleId(Integer roleId) {
        return null;
    }

    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {

    }

    public List<String> getAuthNameByAdminId(Integer adminId) {
        return null;
    }
}
