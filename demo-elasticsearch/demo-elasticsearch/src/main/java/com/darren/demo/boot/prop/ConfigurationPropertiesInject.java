package com.darren.demo.boot.prop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author : darren
 * @date : 2022/1/20
 */
@Configuration
@Data
//@PropertySource("classpath:prop_demo/appConfig.properties")
public class ConfigurationPropertiesInject {

    //  @Value("${msg_server_ip}")
    @Setter
    @Getter
    public Integer aa;

    // @Bean("taskExecutor")
    public Executor taskExecutor(@Autowired ConfigurationPropertiesInject configurationPropertiesInject) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(configurationPropertiesInject.getAa());
        executor.initialize();
        return executor;
    }
}
