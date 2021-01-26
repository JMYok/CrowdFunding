package org.bobjiang.service.impl;

import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.exception.LoginAcctAlreadyInUseException;
import com.bobjiang.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.bobjiang.crowd.exception.LoginFailedException;
import com.bobjiang.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bobjiang.entity.Admin;
import org.bobjiang.entity.AdminExample;
import org.bobjiang.mapper.AdminMapper;
import org.bobjiang.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String ori_password = admin.getUserPswd();
        String MD5_password = CrowdUtil.md5(ori_password);
        admin.setUserPswd(MD5_password);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);


        try {
            int count = adminMapper.insert(admin);
            Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
            logger.info("受影响行数为："+count);
        }catch (Exception e){
            e.printStackTrace();

            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
            }
        }

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

    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.调用PageHelper的静态方法开启分页功能
        //重复体现了PageHelper“非侵入式”的设计，不需要更改原本的代码
        PageHelper.startPage(pageNum,pageSize);

        // 2.根据keyword搜索数据
        List<Admin> adminList = adminMapper.selectAdminByKeyWord(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<Admin>(adminList);
    }

    public void removeById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    public Admin queryAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public void updateAdmin(Admin admin) {
        // 利用try-catch块，处理更新管理员信息后的loginAcct已在数据库中存在的情况
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e){
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                // 当触发该异常时，抛出另一个针对更新时loginAcct已存在的异常
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
            }
        }
    }

    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 先清除旧的对应inner_admin_role表中对应admin_id的数据
        adminMapper.clearOldRelationship(adminId);

        // 如果roleIdList非空，则将该list保存到数据库表中，且admin_id=adminId
        if (roleIdList != null && roleIdList.size() > 0){
            adminMapper.saveAdminRoleRelationship(adminId,roleIdList);
        }
        // roleIdList为空，则清空后不做操作
    }
}
