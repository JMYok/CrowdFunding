package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MemberService;
import com.atguigu.crowd.entity.po.MemberPO;
import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-21 21:06
 */
@RestController
public class MemberProviderHandler {

    @Autowired
    private MemberService memberService;

    /**
     * 根据账号得到用户信息
     * @param loginacct
     * @return
     */
    @RequestMapping("/get/member/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    /**
     * 保存用户
     * @param memberPO
     * @return
     */
    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO){
        try {
           memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return ResultEntity.failed(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }
}
