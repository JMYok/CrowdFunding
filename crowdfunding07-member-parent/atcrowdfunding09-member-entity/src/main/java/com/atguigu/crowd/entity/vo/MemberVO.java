package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-03-15 21:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO {
    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phonenum;

    private String code;
}
