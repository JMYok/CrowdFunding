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
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByUsername(String adminLoginAcct,String password);

    /**
     *
     * @param keyword  关键字
     * @param pageNum  页面编号
     * @param pageSize 每页记录数
     * @return PageInfo<Admin>
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);
}
