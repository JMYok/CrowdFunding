package org.bobjiang.service.api;

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
}
