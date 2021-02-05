package org.bobjiang.mvc.config;

import org.bobjiang.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**为了能方便地获取到原始的Admin对象，因此创建一个SecurityAdmin类，扩展User。
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-05 17:24
 */
public class SecurityAdmin extends User {

   // private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public SecurityAdmin(Admin admin,
                         //角色、权限集合
                         List<GrantedAuthority> authorities){
        super(admin.getUserName(),admin.getUserPswd(),authorities);

        this.originalAdmin = admin;
        // 为了保证安全性，擦除放入originalAdmin的对象的密码
        this.originalAdmin.setUserPswd(null);
    }

    public Admin getOriginalAdmin(){
        return this.originalAdmin;
    }
}
