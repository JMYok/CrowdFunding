package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.po.MemberPO;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-21 21:11
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);
}
