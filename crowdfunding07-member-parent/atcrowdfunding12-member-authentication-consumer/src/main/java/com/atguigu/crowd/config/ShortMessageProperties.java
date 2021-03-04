package com.atguigu.crowd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-03-04 22:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {
    private String host;
    private String path;
    private String appCode;
    private String param;
    private String sign;
    private String skin;

}
