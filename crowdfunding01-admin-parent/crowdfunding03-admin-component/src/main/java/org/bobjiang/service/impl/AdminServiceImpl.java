package org.bobjiang.service.impl;

import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.exception.LoginFailedException;
import com.bobjiang.crowd.util.CrowdUtil;
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

    public Admin getAdminByUsername(String adminLoginAcct,String password) {
        // 1.根据账号查对象
        AdminExample adminExample = new AdminExample();

        AdminExample.Criteria criteria = adminExample.createCriteria();

        // 在Criteria对象中封装查询的条件
        criteria.andLoginAcctEqualTo(adminLoginAcct);

        // 调用AdminMapper的方法来查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断Admin是否为null
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1){
            // 数据库的数据存在重复
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        // 前面判断完后无异常，取出admin对象
        Admin admin = admins.get(0);

        // 3.若Admin为null则抛出异常
        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.若Admin不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 5.将表单提交的明文密码进行比较
        String userPswdForm = CrowdUtil.md5(password);

        // 6.若比较不一致抛出异常
        if (!userPswdDB.equals(userPswdForm)){
            // 密码不匹配
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 7.若一致则返回对象
        return admin;
    }
}
