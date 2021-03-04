package com.atguigu.crowd.handler;

import com.atguigu.crowd.RedisRemoteService;
import com.atguigu.crowd.config.ShortMessageProperties;
import com.bobjiang.crowd.constant.CrowdConstant;
import com.bobjiang.crowd.util.CrowdUtil;
import com.bobjiang.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 发送验证码
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
}
