package com.atguigu.crowd.handler;

import com.atguigu.crowd.MySQLRemoteService;
import com.atguigu.crowd.RedisRemoteService;
import com.atguigu.crowd.config.ShortMessageProperties;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.vo.MemberVO;
import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.util.CrowdUtil;
import com.bobjiang.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-03-04 22:06
 */
@Controller
public class MemberHandler {

    @Autowired
    private ShortMessageProperties shortMessageProperties;

    //记住在主启动类上添加@EnableFeignClients，启用Feign客户端功能。RedisRemoteService是Feign客户端
    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    Logger logger = LoggerFactory.getLogger(MemberHandler.class);

    /**
     * 发送验证码
     * @param phoneNum
     * @return ResultEntity<String>
     */
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendShortMessage(@RequestParam("phoneNum") String phoneNum){

        // 调用工具类中的发送验证码的方法，可以从配置文件中读取配置的接口信息
        ResultEntity<String> sendResultEntity = CrowdUtil.sendShortMessage(
                shortMessageProperties.getHost(),
                shortMessageProperties.getPath(),
                phoneNum,
                shortMessageProperties.getAppCode(),
                shortMessageProperties.getParam(),
                shortMessageProperties.getSign(),
                shortMessageProperties.getSkin());
        // 判断-发送成功
        if (ResultEntity.SUCCESS.equals(sendResultEntity.getResult())){

            // 得到ResultEntity中的验证码
            String code = sendResultEntity.getData();

            // 将验证码存入到redis中（设置TTL，这里设置为5分钟）

            ResultEntity<String> redisResultEntity = redisRemoteService.setRedisKeyValueWithTimeoutRemote(
                    CrowdConstant.REDIS_CODE_PREFIX + phoneNum, code, 5, TimeUnit.MINUTES);

            // 判断存入redis是否成功
            if (ResultEntity.SUCCESS.equals(redisResultEntity.getResult())){
                // 存入成功，返回成功
                return ResultEntity.successWithoutData();
            } else {
                // 存入失败，返回redis返回的ResultEntity
                return redisResultEntity;
            }
        } else {
            // 发送验证码失败，返回发送验证码的ResultEntity
            return sendResultEntity;
        }
    }

    /**
     * 用户注册
     * @param memberVO
     * @param modelMap
     * @return
     */
    @RequestMapping("/auth/member/do/register.html")
    public String register(MemberVO memberVO, ModelMap modelMap){

        logger.debug("MemberVO before encode:"+memberVO.toString());

        //1.获取手机号
        String phoneNumber = memberVO.getPhonenum();

        //2.拼接redis中存储的验证码的key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNumber;

        logger.debug("Redis key:"+key);

        //3.从Redis读取Key对应的value
        ResultEntity<String> resultEntity = redisRemoteService.getRedisValueByKeyRemote(key);

        //4.检查查询操作是否成功
        String result = resultEntity.getResult();

        logger.debug("Redis select result:"+result);

        if(resultEntity.FAILED.equals(result)){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,resultEntity.getMessage());

            return "member-reg";
        }

        String redisCode = resultEntity.getData();

        if(redisCode == null){
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_CODE_NOT_EXIST);

            return "member-reg";
        }

        //5.如果能从Redis中查询到value 则比较表单验证码和Redis验证码
        String formCode = memberVO.getCode();

        if(Objects.equals(redisCode,formCode) == false) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_CODE_INVALID);

            return "member-reg";
        }

        //6.如果验证码一致，则删除Redis中的验证码
        redisRemoteService.removeRedisKeyByKeyRemote(key);

        //7.密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userpwd = memberVO.getUserpswd();

        String encode = bCryptPasswordEncoder.encode(userpwd);

        memberVO.setUserpswd(encode);

        //8.执行保存
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO,memberPO);
        logger.debug("MemberPO:"+memberPO.toString());
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveMemberRemote(memberPO);

        if(ResultEntity.FAILED.equals(saveResultEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,saveResultEntity.getMessage());

            return "member-reg";
        }

        return "member-login";

    }
}
