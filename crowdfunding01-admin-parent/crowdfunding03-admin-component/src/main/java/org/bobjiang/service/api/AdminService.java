package org.bobjiang.service.api;

import com.github.pagehelper.PageInfo;
import org.bobjiang.entity.Admin;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-20 17:21
 */
public interface AdminService {
    /**
     * 保存Admin信息
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 根据id查询Admin
     * @param id
     * @return Admin
     */
    Admin queryAdmin(Integer id);

    /**
     * 得到所有Admin信息
     * @return List<Admin>
     */
    List<Admin> getAll();

    /**
     * 根据用户名称返回Admin信息
     * @param adminLoginAcct
     * @param password
     * @return
     */
    Admin getAdminByUsername(String adminLoginAcct,String password);

    /**
     *
     * @param keyword  关键字
     * @param pageNum  页面编号
     * @param pageSize 每页记录数
     * @return PageInfo<Admin>
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 根据Id删除admin信息
     * @param adminId
     */
    void removeById(Integer adminId);


    void updateAdmin(Admin admin);

//    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleList);
//
//    Admin getAdminByLoginAcct(String loginAcct);

}
