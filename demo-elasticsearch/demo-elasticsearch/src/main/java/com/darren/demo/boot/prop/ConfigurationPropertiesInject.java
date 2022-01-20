package com.darren.demo.boot.prop;

import lombok.Data;

/**
 * @author : darren
 * @date : 2022/1/20
 */
@Data
//@ConfigurationProperties(prefix = "url")
public class ConfigurationPropertiesInject {

    private String orderUrl;
}
