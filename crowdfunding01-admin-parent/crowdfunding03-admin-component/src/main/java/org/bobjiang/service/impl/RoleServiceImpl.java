package org.bobjiang.service.impl;

import org.bobjiang.mapper.RoleMapper;
import org.bobjiang.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-24 12:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


}
