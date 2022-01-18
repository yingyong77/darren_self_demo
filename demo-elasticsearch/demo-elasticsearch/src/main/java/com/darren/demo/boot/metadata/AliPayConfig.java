package com.darren.demo.boot.metadata;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : darren
 * @date : 2022/1/10
 */
@Configuration
@ConfigurationProperties(prefix = "alipay.config")
public class AliPayConfig {

    @Getter
    @Setter
    private Long appId;

}
