package com.atguigu.crowd.Impl;

import com.atguigu.crowd.api.MemberService;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.po.MemberPOExample;
import com.atguigu.crowd.mapper.MemberPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-02-21 21:12
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberPOMapper memberPOMapper;

    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        MemberPOExample example = new MemberPOExample();

        MemberPOExample.Criteria criteria = example.createCriteria();

        criteria.andLoginacctEqualTo(loginacct);

        List<MemberPO> memberPOS = memberPOMapper.selectByExample(example);

        // 判断得到的List是否为空，为空则返回null，防止后面调用的时候触发空指针异常
        if (memberPOS == null || memberPOS.size() == 0){
            return null;
        }

        // List非空，则返回第一个（因为LoginAcct是唯一的）
        MemberPO memberPO = memberPOS.get(0);
        return memberPO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
