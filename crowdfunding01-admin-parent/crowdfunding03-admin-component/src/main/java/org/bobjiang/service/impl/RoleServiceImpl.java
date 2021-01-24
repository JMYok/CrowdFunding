package org.bobjiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bobjiang.entity.Role;
import org.bobjiang.mapper.RoleMapper;
import org.bobjiang.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-24 12:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public PageInfo<Role> getPageInfo(int pageNum, int pageSize, String keyword) {

        PageHelper.startPage(pageNum,pageSize);

        List<Role> roleList = roleMapper.selectRoleByKeyWord(keyword);

        return new PageInfo<Role>(roleList);
    }

    public void saveRole(Role role) {
        roleMapper.insert(role);

        //角色名重复异常
    }

    public void updateRole(Role role) {

    }

    public void removeById(List<Integer> roleIdList) {

    }

    public List<Role> queryUnAssignedRoleList(Integer adminId) {
        return null;
    }

    public List<Role> queryAssignedRoleList(Integer adminId) {
        return null;
    }
}
