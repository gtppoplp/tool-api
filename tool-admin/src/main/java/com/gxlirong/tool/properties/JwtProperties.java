package com.gxlirong.tool.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置
 *
 * @author lirong
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "customize.jwt")
public class JwtProperties {
    /**
     * JWT存储的请求头
     */
    private String tokenHeader;
    /**
     * JWT加解密使用的密钥
     */
    private String secret;
    /**
     * JWT的超期限时间(60*60*24)
     */
    private Long expiration;
    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;
}
