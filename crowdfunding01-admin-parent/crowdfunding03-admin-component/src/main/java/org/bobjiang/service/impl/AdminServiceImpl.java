package org.bobjiang.service.impl;

import org.bobjiang.entity.Admin;
import org.bobjiang.entity.AdminExample;
import org.bobjiang.mapper.AdminMapper;
import org.bobjiang.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-20 17:20
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        int count = adminMapper.insert(admin);
        Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
        logger.info("受影响行数为："+count);
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
